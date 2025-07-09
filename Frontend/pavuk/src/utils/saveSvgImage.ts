import axios from "axios";
import ImageType from "../types/image-type";


async function saveSvgImageByData(svgData: string, name: string) {
    try {
        const payload = {
            name,
            svgData,
        };

        const response = await axios.post(
            `${import.meta.env.VITE_SERVER_PATH}clothing-item-images/save`, 
            payload, 
            { withCredentials: true }
        );

        if (response.status === 200) {
            return response.data as ImageType;
        }
    } 
    catch (error: any) {
        console.error(error.message);
    }
};

async function saveSvgImageById(id: string, name: string) {
    try {
        const svg = document.getElementById(id)?.querySelector("svg");
        if (svg) {
            const svgData = new XMLSerializer().serializeToString(svg);
            return await saveSvgImageByData(svgData, name);
        }
    } 
    catch (error: any) {
        console.error(error.message);
    }
};

export { saveSvgImageByData, saveSvgImageById };