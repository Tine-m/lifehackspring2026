let favorites = [
    { id: 1, cat: 'soft',      catLabel: 'Soft surfaces',    title: 'Remove a red wine or fruit stain', desc: 'Salt, white vinegar and dish soap',       hackKey: 'red-wine',   date: '26-03-2026' },
    { id: 2, cat: 'soft',      catLabel: 'Soft surfaces',    title: 'Remove oil or grease',             desc: 'Baby powder, cornstarch or chalk powder', hackKey: 'grease',     date: '26-03-2026' },
    { id: 3, cat: 'hard',      catLabel: 'Hard surfaces',    title: 'Degreasing surfaces',              desc: 'White vinegar and baking soda',           hackKey: 'degreasing', date: '26-03-2026' },
    { id: 4, cat: 'hard',      catLabel: 'Hard surfaces',    title: 'Limescale removal',                desc: 'White vinegar or citric acid',            hackKey: 'limescale',  date: '26-03-2026' },
    { id: 5, cat: 'mirror',    catLabel: 'Mirror & glass',   title: 'Streak-free glass',                desc: 'White vinegar and microfibre cloth',      hackKey: 'streak',     date: '26-03-2026' },
    { id: 6, cat: 'jewellery', catLabel: 'Jewellery',        title: 'Clean tarnished silver & gold',    desc: 'Aluminium foil, baking soda, salt and boiling water.',      hackKey: 'silver-or-gold',     date: '26-03-2026' }
];

let activeFilter = 'all';

function updateCounts() {
    document.getElementById('count-all').textContent = favorites.length;
    ['soft', 'hard', 'jewellery', 'mirror'].forEach(cat => {
        const el = document.getElementById('count-' + cat);
        if (el) el.textContent = favorites.filter(f => f.cat === cat).length;
    });
}

function filterFavs(cat, clickedEl) {
    activeFilter = cat;
    document.querySelectorAll('.sidebar-item').forEach(el => el.classList.remove('active'));
    clickedEl.classList.add('active');
    renderFavs();
}

function sortFavs(method) {
    if (method === 'date')  favorites.sort((a, b) => b.date.localeCompare(a.date));
    if (method === 'cat')   favorites.sort((a, b) => a.catLabel.localeCompare(b.catLabel));
    renderFavs();
}

function removeFav(id) {
    favorites = favorites.filter(f => f.id !== id);
    updateCounts();
    renderFavs();
}

function renderFavs() {
    const container = document.getElementById('fav-items');
    const filtered = activeFilter === 'all' ? favorites : favorites.filter(f => f.cat === activeFilter);

    const titleMap = {
        all:       `All saved hacks (${favorites.length})`,
        soft:      `Soft surfaces (${favorites.filter(f => f.cat === 'soft').length})`,
        hard:      `Hard surfaces (${favorites.filter(f => f.cat === 'hard').length})`,
        jewellery: `Jewellery (${favorites.filter(f => f.cat === 'jewellery').length})`,
        mirror:    `Mirror & glass (${favorites.filter(f => f.cat === 'mirror').length})`,
    };
    document.getElementById('list-title').textContent = titleMap[activeFilter];

    if (filtered.length === 0) {
        container.innerHTML = `<div class="empty-fav"><p>No saved hacks in this category yet.<br><a href="hacks.html">Browse hacks</a> to add some.</p></div>`;
        return;
    }

    container.innerHTML = filtered.map(fav => `
    <div class="fav-item" id="fav-${fav.id}">
      <div class="fav-meta">
        <div class="cat-badge">${fav.catLabel.toUpperCase()}</div>
        <p class="fav-title">${fav.title}</p>
        <p class="fav-desc">${fav.desc}</p>
      </div>
      <div class="fav-actions">
        <a href="hacks.html?cat=${fav.cat}&hack=${fav.hackKey}" class="btn-view">View hack &rarr;</a>
        <button class="btn-remove" onclick="removeFav(${fav.id})">Remove &nbsp;&#x1F5D1;</button>
      </div>
    </div>
  `).join('');
}

updateCounts();
renderFavs();