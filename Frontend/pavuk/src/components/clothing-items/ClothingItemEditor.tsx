import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { createOrder } from "../../state/orders/orders-slice";
import { getFabrics } from "../../state/fabrics/fabrics-slice";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { saveSvgImageById }  from "../../utils/saveSvgImage";
import { createClothingItem } from "../../state/clothing-items/clothing-items-slice";
import ClothingItemType, { ClothingItemTypeEnum } from "../../types/clothing-item-type";
import FabricType from "../../types/fabric-type";
import ColorType from "../../types/color-type";
import axios from "axios";
import "./ClothingItemEditor.css";


type SvgLayer = {
  layerName: string,
  layerData: string,
};

type SvgLayers = {
  svgLayers: { [key: string]: SvgLayer };
  svgLayerGroups: { [key: string]: string[] };
  svgStyles: string,
};


function ClothingItemEditor() {
  const dispatch = useDispatch<AppDispatch>();
  const location = useLocation();
  const navigate = useNavigate();
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const { clothingItem } = location.state as { clothingItem: ClothingItemType };
  const primaryImage = Array.from(clothingItem.images).find(image => image.isPrimary);
  const primaryImageUrl = primaryImage ? primaryImage.url : Array.from(clothingItem.images)[0]!.url;
  const fabrics: Array<FabricType> = useSelector((state: RootState) => state.fabrics.fabrics);
  
  const [primaryImageSvg, setPrimaryImageSvg] = useState<string>("");
  const [primaryImageSvgLayers, setPrimaryImageSvgLayers] = useState<SvgLayers>({ svgLayers: {}, svgLayerGroups: {}, svgStyles: "" });
  const [selectedClothingPart, setSelectedClothingPart] = useState<string>("");
  const [selectedFabric, setSelectedFabric] = useState<FabricType>(fabrics[0]);  
  const [fabricColors, setFabricColors] = useState<ColorType[]>([]);
  const [selectedFabricColor, setSelectedFabricColor] = useState<ColorType>(fabricColors[0]);

  const [clothingParts, setClothingParts] = useState<{ [key: string]: { selectedFabric: FabricType | null, selectedFabricColor: ColorType | null } }>({});
  
  const updateClothingPart = (partName: string, fabric: FabricType | null, color: ColorType | null) => {
    setClothingParts(prevState => ({
      ...prevState,
      [partName]: { selectedFabric: fabric, selectedFabricColor: color }
    }));
  };
  

  useEffect(() => {
    const fetchSvg = async () => {
      const response = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}${primaryImageUrl}`,
        { withCredentials: true }
      );
      setPrimaryImageSvg(response.data);

      const svgDoc = new DOMParser().parseFromString(response.data, "image/svg+xml");
      const svgDocStyle = svgDoc.querySelector("style");
      if (svgDoc && svgDocStyle) {
        const svgStyles = svgDoc.querySelector("style") ? svgDocStyle.innerHTML : "";

        const svgLayers: { [key: string]: SvgLayer } = {};
        const svgLayerGroups: { [key: string]: string[] } = {};
        svgDoc.querySelectorAll("[id]").forEach((svgLayer) => {
          const id = svgLayer.getAttribute("id")!;

          const layerData = new XMLSerializer().serializeToString(svgLayer);
          svgLayers[id] = {
            layerName: id,
            layerData,
          };

          const commonName = id.replace("-line", "");
          if (!svgLayerGroups[commonName]) {
            svgLayerGroups[commonName] = [];
          }
          svgLayerGroups[commonName].push(id);
        });

        setPrimaryImageSvgLayers({ svgLayers, svgLayerGroups, svgStyles });
        setSelectedClothingPart(Object.keys(svgLayerGroups)[0]);
      }
    };

    fetchSvg();
    dispatch(getFabrics());
  }, [primaryImageUrl]);


  const changeStrokeColor = (hex: string, amount = 0.2) => {
    const value = parseInt(hex.replace("#", ""), 16);
  
    const r = Math.max(0, Math.min(255, (value >> 16) - Math.round(255 * amount)));
    const g = Math.max(0, Math.min(255, ((value >> 8) & 0x00ff) - Math.round(255 * amount)));
    const b = Math.max(0, Math.min(255, (value & 0x0000ff) - Math.round(255 * amount)));
  
    return `#${(r << 16 | g << 8 | b).toString(16).padStart(6, "0")}`;
  };

  const clothingHandler = (clothingPartName: string) => {
    setSelectedClothingPart(clothingPartName);

    if (selectedFabric && selectedFabricColor) {
      const clothingEditorImage = document.getElementById("clothing-editor-image");
      if (clothingEditorImage) {
        const clothingEditorImageSvg = clothingEditorImage.querySelector("svg");
        const clothingEditorImageOverlay = document.getElementById("clothing-editor-image-overlay");
        if (clothingEditorImageSvg && clothingEditorImageOverlay) {
          clothingEditorImageOverlay.setAttribute("viewBox", clothingEditorImageSvg.getAttribute("viewBox")!);

          const patternId = `pattern-${clothingPartName}`;
          const existingPattern = clothingEditorImageOverlay.querySelector(`#${patternId}`);
          if (existingPattern) {
            existingPattern.remove();
          }

          const pattern = document.createElementNS("http://www.w3.org/2000/svg", "pattern");
          const patternSize = "700";
          pattern.setAttribute("id", patternId);
          pattern.setAttribute("patternUnits", "userSpaceOnUse");
          pattern.setAttribute("width", patternSize);
          pattern.setAttribute("height", patternSize);

          const patternImage = document.createElementNS("http://www.w3.org/2000/svg", "image");
          patternImage.setAttribute("href", `${import.meta.env.VITE_SERVER_PATH}${selectedFabric.image.url}`);
          patternImage.setAttribute("width", patternSize);
          patternImage.setAttribute("height", patternSize);
          pattern.appendChild(patternImage);
          clothingEditorImageOverlay.appendChild(pattern);
          
          if (clothingPartName === clothingEditorImageSvg.id) {
            clothingEditorImage.querySelectorAll("path[id]").forEach((clotingItemPart) => {
              const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
              const pathData = (clotingItemPart!.getAttribute("d"))!;
              path.setAttribute("d", pathData);
              path.setAttribute("style", `fill: url(#${patternId})`);
              clothingEditorImageOverlay!.appendChild(path);

              (clotingItemPart as HTMLElement).setAttribute("style", `fill: ${selectedFabricColor?.hex}`);
            });

            clothingEditorImage.querySelectorAll(`path[id*="-line"]`).forEach((clotingItemPartLine) => {
              (clotingItemPartLine as HTMLElement).setAttribute("style", `stroke: ${changeStrokeColor(selectedFabricColor.hex)}`);
            });
          }
          else {
            const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
            const pathData = (clothingEditorImage.querySelector(`#${clothingPartName}`)!.getAttribute("d"))!;
            path.setAttribute("d", pathData);
            path.setAttribute("style", `fill: url(#${patternId})`);
            clothingEditorImageOverlay!.appendChild(path);

            clothingEditorImage.querySelectorAll(`path[id^="${clothingPartName}"]`).forEach((clotingItemPart) => {
              (clotingItemPart as HTMLElement).setAttribute("style", `fill: ${selectedFabricColor?.hex}`);
            });

            clothingEditorImage.querySelectorAll(`path[id^="${clothingPartName}-line"]`).forEach((clotingItemPartLine) => {
              (clotingItemPartLine as HTMLElement).setAttribute("style", `stroke: ${changeStrokeColor(selectedFabricColor.hex)}`);
            });
          }

          updateClothingPart(clothingPartName, selectedFabric, selectedFabricColor);
        }
      }
    }
  };


  useEffect (() => {
    setSelectedFabric(fabrics[0]);
  }, [fabrics]);

  useEffect(() => {
    if (selectedFabric) {
      const colors = Array.from(selectedFabric!.colors);

      const getLuminosity = (hex: string) => {
        const r = parseInt(hex.slice(1, 3), 16);
        const g = parseInt(hex.slice(3, 5), 16);
        const b = parseInt(hex.slice(5, 7), 16);
        return 0.21 * r + 0.72 * g + 0.07 * b;
      };

      const getColorCategory = (hex: string) => {
        const r = parseInt(hex.slice(1, 3), 16);
        const g = parseInt(hex.slice(3, 5), 16);
        const b = parseInt(hex.slice(5, 7), 16);

        if (r > 200 && g > 200 && b > 200) {
          return "light";
        }
        if (r > 150 && g > 150 && b > 150) {
          return "pastel";
        }
        if (r > 100 && g > 100 && b > 100) {
          return "bright";
        }
        if (r > 100 && g > 100 && b > 50 && Math.abs(r - g) < 50 && b < 100) {
          return "brown";
        }
        if (r > 200 && g > 180 && b > 150) {
          return "beige";
        }

        return "dark";
      };

      const sortedColors = colors.sort((color1, color2) => {
        const category1 = getColorCategory(color1.hex);
        const category2 = getColorCategory(color2.hex);
        
        const categoryOrder = {
          "light": 0,
          "pastel": 1,
          "bright": 2,
          "brown": 3,
          "beige": 4,
          "dark": 5
        };

        if (categoryOrder[category1] !== categoryOrder[category2]) {
          return categoryOrder[category1] - categoryOrder[category2];
        }

        return getLuminosity(color2.hex) - getLuminosity(color1.hex);
      });

      setFabricColors(sortedColors);
      setSelectedFabricColor(sortedColors[0]);

      clothingHandler(selectedClothingPart);
    }
  }, [selectedFabric]);
  
  const fabricHandler = (fabric: FabricType) => {
    setSelectedFabric(fabric);
    updateClothingPart(selectedClothingPart, fabric, selectedFabricColor);
  };


  useEffect(() => {
    clothingHandler(selectedClothingPart);
  }, [selectedFabricColor]);

  const colorHandler = (fabricColor: ColorType) => {
    setSelectedFabricColor(fabricColor);
    updateClothingPart(selectedClothingPart, selectedFabric, fabricColor);
  };


  const createOrderHandler = async () => {
    const postService = { id: 1, name: "Нова пошта" };
    const shippingInfo = { country: "Україна", region: "Одеська область", city: "Одеса", postalCode: "65001", postService };
    
    const savedSvg = await saveSvgImageById("clothing-editor-image", `${currentUser.email}`);
    if (savedSvg) {
      const updatedClothingItem = {
        ...clothingItem,
        images: [savedSvg],
        type: ClothingItemTypeEnum.USER,
      };

      const resultAction1 = await dispatch(createClothingItem(updatedClothingItem));
      if (createClothingItem.fulfilled.match(resultAction1)) {
        const createdClothingItem = resultAction1.payload;
        const order = { user: currentUser, clothingItem: createdClothingItem, shippingInfo };
  
        const resultAction2 = await dispatch(createOrder(order));
        if (createOrder.fulfilled.match(resultAction2)) {
          const createdOrder = resultAction2.payload;
          navigate(`/orders/confirmation/${createdOrder!.id}`, { state: { order: createdOrder } });
        }
      }
    }
  };


  return (
    <>
      <section className="clothing-editor-section">
        <div className="clothing-editor w-full h-full px-40">
          <div className="h-full aspect-square px-24 py-12">
            <div className="relative h-full w-full">
              <div className="absolute flex items-center aspect-square h-full">
                <svg id="clothing-editor-image-overlay" xmlns="http://www.w3.org/2000/svg"></svg>
              </div>
              <div id="clothing-editor-image" dangerouslySetInnerHTML={{ __html: primaryImageSvg }}></div>
            </div>
          </div>

          <div className="w-full py-9">
            <div className="clothing-editor-order-creation h-full w-full">
              <div className="overflow-hidden flex-grow flex flex-col">
                <div className="clothing-editor-tools h-full overflow-y-auto">
                  <div className="h-0">
                    <div className="flex flex-col px-8 pt-7 pb-4 gap-3">
                      <div className="flex flex-col">
                        <p className="clothing-editor-tools-heading text-base mb-1">Деталі одягу</p>
                        <div className="clothing-editor-clothing-part-container">
                          {Object.entries(primaryImageSvgLayers.svgLayerGroups).map(([commonName, layerNames]) => (
                            <div 
                              key={commonName} 
                              className={`clothing-editor-clothing-part box-border w-9 h-9 p-[4px] pt-[5px] ${
                                selectedClothingPart === commonName ? "clothing-editor-clothing-part-selected" : ""
                              }`}
                              onClick={() => clothingHandler(commonName)}
                            >
                              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1094 1094">
                                <style>{primaryImageSvgLayers.svgStyles}</style>
                                {layerNames.map((layerName) => (
                                  <g key={layerName} dangerouslySetInnerHTML={{ __html: primaryImageSvgLayers.svgLayers[layerName].layerData }}/>
                                ))}
                              </svg>
                            </div>
                          ))}
                        </div>
                      </div>

                      <div className="flex flex-col">
                        <p className="clothing-editor-tools-heading text-base mb-1">Тканина</p>
                        <div className="clothing-editor-fabric-container">
                          {fabrics.map((fabric) => (
                            <img 
                              key={fabric.id}
                              className={`clothing-editor-fabric w-9 h-9 ${
                                selectedFabric?.id === fabric.id ? "clothing-editor-fabric-selected" : ""
                              }`}
                              src={`${import.meta.env.VITE_SERVER_PATH}${fabric.image.url}`} 
                              alt={fabric.name}
                              onClick={() => fabricHandler(fabric)}
                            />
                          ))}
                        </div>
                      </div>

                      <div className="flex flex-col">
                        <p className="clothing-editor-tools-heading text-base mb-1">Колір</p>
                        <div className="clothing-editor-color-container max-w-[100%]">
                          {fabricColors.map((fabricColor) => (
                            <div
                              key={fabricColor.id}
                              className={`clothing-editor-color box-border w-9 h-9 ${
                                selectedFabricColor?.id === fabricColor.id ? "clothing-editor-color-selected" : ""
                              }`}
                              style={{ backgroundColor: fabricColor.hex }}
                              onClick={() => colorHandler(fabricColor)} 
                            ></div>
                          ))}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div className="clothing-editor-info">
                <div className="px-8 pt-4 pb-7">
                  <h4 className="clothing-editor-heading text-2xl">{clothingItem.name}</h4>
                  <p className="clothing-editor-subtitle text-lg">{clothingItem.description}</p>
                  <p className="clothing-editor-price mt-2">
                    <span className="text-xl">{clothingItem.price}</span>
                    <span className="clothing-editor-price-currency text-lg">₴</span>
                  </p>

                  <div className="flex justify-end mt-7">
                    <button className="clothing-editor-button text-lg px-8 py-3" onClick={createOrderHandler}>
                      Підтвердити
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default ClothingItemEditor;