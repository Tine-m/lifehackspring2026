const canvas = document.getElementById('pieCanvas');
const ctx = canvas.getContext('2d');

let angles = [0, 120, 240];
let draggingIndex = null;

function draw() {
    ctx.clearRect(0,0,400,400);
    const cx = 200;
    const cy = 200;
    const radius = 150;

    let sorted = [...angles].sort((a,b)=>a-b);

    const colors = [
        'rgb(253, 255, 245)',
        'rgb(117, 76, 36)',
        'rgb(188, 212, 230)'
    ];

    for (let i = 0; i < 3; i++) {
        let start = sorted[i] * Math.PI/180;
        let end = sorted[(i+1)%3] * Math.PI/180;

        if (i === 2) end += Math.PI*2;

        ctx.beginPath();
        ctx.moveTo(cx, cy);
        ctx.arc(cx, cy, radius, start, end);
        ctx.fillStyle = colors[i];
        ctx.fill();
    }

    angles.forEach((angle, i) => {
        let rad = angle * Math.PI/180;
        let x = cx + Math.cos(rad)*radius;
        let y = cy + Math.sin(rad)*radius;

        ctx.beginPath();
        ctx.arc(x, y, 8, 0, Math.PI*2);
        ctx.fillStyle = 'white';
        ctx.fill();
    });
}

function getAngle(x,y) {
    const cx = 200;
    const cy = 200;
    let angle = Math.atan2(y-cy, x-cx) * 180/Math.PI;
    return (angle + 360) % 360;
}

canvas.addEventListener('mousedown', (e)=>{
    const rect = canvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    angles.forEach((angle, i)=>{
        let rad = angle * Math.PI/180;
        let px = 200 + Math.cos(rad)*150;
        let py = 200 + Math.sin(rad)*150;

        if (Math.hypot(px-x, py-y) < 10) {
            draggingIndex = i;
        }
    });
});

canvas.addEventListener('mousemove', (e)=>{
    if (draggingIndex !== null) {
        const rect = canvas.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        angles[draggingIndex] = getAngle(x,y);
        draw();
    }
});

canvas.addEventListener('mouseup', ()=>{
    draggingIndex = null;
    saveToServer();
});

canvas.addEventListener('mouseleave', ()=>{
    draggingIndex = null;
});

function getValues() {
    let sorted = [...angles].sort((a,b)=>a-b);
    let values = [];

    for (let i = 0; i < 3; i++) {
        let diff = (sorted[(i+1)%3] - sorted[i] + 360) % 360;
        values.push(diff);
    }
    return values;
}

function saveToServer() {
    fetch('/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ values: getValues() })
    });
}

draw();