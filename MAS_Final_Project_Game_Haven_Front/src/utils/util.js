export const formatDate = (date) => {
    const dateFormat = new Intl.DateTimeFormat("US", {
                timeStyle: "short",
                dateStyle: "short",
              }); 

    const formattedDate =  date == null ? null : dateFormat.format(new Date(date)); 

    return formattedDate;
}

export const formatDate2 = (date) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(date).toLocaleDateString(undefined, options);
  };

export const renderImage = (src, alt, maxWidth = '150px', maxHeight = '200px') => {
    const defaultImage = `${process.env.PUBLIC_URL}/questionMark.jpg`;

    return <img src={src || defaultImage} alt={alt} style={{ maxWidth, maxHeight }} />;
  };
