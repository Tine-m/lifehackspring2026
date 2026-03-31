const canvas = document.getElementById("panel");
const ctx = canvas.getContext("2d");
const colorBox = document.getElementById("display");

const width = canvas.width;
const height = canvas.height;

const topLeft = [255, 255, 255];
const topRight = [117, 76, 36];
const bottomLeft = [188, 212, 230];
const bottomRight = [58, 46, 46];

const image = ctx.createImageData(width, height);
const data = image.data;

for (let y = 0; y < height; y++) {
    const v = y / (height - 1);
    for (let x = 0; x < width; x++) {
        const u = x / (width - 1);

        const r = topLeft[0] * (1 - u) * (1 - v) +
            topRight[0] * u * (1 - v) +
            bottomLeft[0] * (1 - u) * v +
            bottomRight[0] * u * v;

        const g = topLeft[1] * (1 - u) * (1 - v) +
            topRight[1] * u * (1 - v) +
            bottomLeft[1] * (1 - u) * v +
            bottomRight[1] * u * v;

        const b = topLeft[2] * (1 - u) * (1 - v) +
            topRight[2] * u * (1 - v) +
            bottomLeft[2] * (1 - u) * v +
            bottomRight[2] * u * v;

        const idx = (y * width + x) * 4;
        data[idx] = Math.round(r);
        data[idx + 1] = Math.round(g);
        data[idx + 2] = Math.round(b);
        data[idx + 3] = 255; // alpha
    }
}
ctx.putImageData(image, 0, 0);

canvas.addEventListener("mousemove", e => {
    if (e.buttons !== 1) return;

    const rect = canvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    const pixel = ctx.getImageData(x, y, 1, 1).data;
    colorBox.style.background = `rgb(${pixel[0]},${pixel[1]},${pixel[2]})`;
});