const hacks = {
    soft: [{ val: 'red-wine', label: 'Red wine / fruit stains'}, { val: 'grease', label: 'Oil or grease'}],
    hard: [{ val: 'degreasing', label: 'Degreasing surfaces'}, { val: 'limescale', label: 'Limescale removal'}, { val: 'rust', label: 'Rust stains'}],
    jewellery: [{ val: 'silver', label: 'Tarnished silver'}, { val: 'gold', label: 'Gold cleaning'}, { val: 'gems', label: 'Gemstone cleaning' }],
    mirror: [{ val: 'streak', label: 'Streak-free glass'}, { val: 'hard-water', label: 'Hard water marks' }, { val: 'streak', label: 'Soap scum'}],
    decal: [{ val: 'kettle', label: 'Kettle descaling'}, {val: 'shower', label: 'Shower head descaling'}],
    };

function updateHackSelect() {
    const cat = document.getElementById('cat-select').value;
    const sel = document.getElementById('hack-select');
    sel.innerHTML= '<option value="">Select hack...</option>';
    if (cat && hacks[cat]) {
        hacks[cat].forEach(h => {
            const opt = document.createElement('option');
            opt.value = h.val;
            opt.textContent = h.label;
            sel.appendChild(opt);
        });
    }
}

function goToHack() {
    const cat = document.getElementById('cat-select').value;
    const hack = document.getElementById('hack-select').value;
    if (cat && hack) {
        window.location.href = `hacks.html?cat=${cat}&hack=${hack}`;
    }
}