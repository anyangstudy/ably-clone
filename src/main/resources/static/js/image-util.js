const resizeImage = (setting) => {
    let file = setting.file
    let maxSize = setting.maxSize
    let reader = new FileReader()
    let image = new Image()
    let canvas = document.createElement("canvas")

    let resize = function () {
        let width = image.width
        let height = image.height

        if (width > height) {
            if (width > maxSize) {
                height *= maxSize / width
                width = maxSize
            }
        } else {
            if (height > maxSize) {
                width *= maxSize / height
                height = maxSize
            }
        }
        canvas.width = width
        canvas.height = height
        canvas.getContext("2d").drawImage(image, 0, 0, width, height)
        let dataurl = canvas.toDataURL("image/png")
        return dataurl
    };

    return new Promise(function (ok, no) {
        if ( !file.type.match(/image.*/)) {
            no(new Error("Not an image"))
            return
        }

        /*if ( file.size < 204800 ) {
            no(new Error('Not 204800byte over'))
            return
        }*/

        reader.onload = function ( e ) {
            image.onload = function () { return ok( resize() ) }
            image.src = e.target.result
        }
        reader.readAsDataURL( file )
    })
}

const dataURLtoFile = (dataurl, fileName) => {

    let arr = dataurl.split(","),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], fileName, {type:mime});
}


