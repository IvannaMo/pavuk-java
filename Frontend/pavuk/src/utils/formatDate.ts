function formatDate(date: string) {
    const tempDate = new Date(date);
    let dd = tempDate.getDate().toString();
    let mm = (tempDate.getMonth() + 1).toString();
    let yyyy = tempDate.getFullYear().toString();

    if (Number(dd) < 10) {
        dd = "0" + dd;
    } 
    if (Number(mm) < 10) {
        mm = "0" + mm;
    }

    return dd + "/" + mm + "/" + yyyy;
}

export default formatDate;