function sliceProductListName(description: string) {
    const words = description.split(" ");

    let maxLength = 2;
    let result = words.slice(0, maxLength).join(" ");
    if (words.length > maxLength) {
        result = result + "...";
    }

    return result;
};

function sliceProductListDescription(description: string) {
    const words = description.split(" ");

    let maxLength = 3;
    let result = words.slice(0, maxLength).join(" ");
    if (words.length > maxLength) {
        result = result + "...";
    }

    return result;
};

export { sliceProductListName, sliceProductListDescription };