function sliceClothingItemName(description: string) {
    const words = description.split(" ");

    let maxLength = 3;
    let result = words.slice(0, maxLength).join(" ");
    if (words.length > maxLength) {
        result = result + "...";
    }

    return result;
};

function sliceClothingItemDescription(description: string) {
    const words = description.split(" ");

    let maxLength = 10;
    let result = words.slice(0, maxLength).join(" ");
    if (words.length > maxLength) {
        result = result + "...";
    }

    return result;
};

export { sliceClothingItemName, sliceClothingItemDescription };