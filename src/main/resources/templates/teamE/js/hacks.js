const hackData = {
    soft: {
        label: 'Soft surfaces',
        hacks: {
            'red-wine': {
                label: 'Red wine / fruit stains',
                category: 'Soft surfaces',
                title: 'Remove a red wine or fruit stain',
                tagline: 'Salt, dish soap and white vinegar.',
                steps: [
                    'Add the salt and/or vinegar to the stain.',
                    'Let sit for at least 10 minutes.',
                    'Wash gently with dish soap and water.',
                    'Repeat if necessary.'
                ],
                science: 'The salt absorbs the liquid and thus also the pigment. The vinegar then begins to dissolve the organic component in the wine or fruit juice, thus removing some of the colour. The dish soap reduces the surface tension between the pigment and the fabric fiber, enabling the water to rinse the pigment away.'
            },
            'grease': {
                label: 'Oil or grease',
                category: 'Soft surfaces',
                title: 'Remove oil or grease from fabric',
                tagline: 'Using baby powder, cornstarch or chalk powder.',
                steps: [
                    'Cover the stain generously with baby powder or cornstarch.',
                    'Let it sit for at least 15 minutes to absorb the oil.',
                    'Rub the stain with dishwashing soap.',
                    'Rinse with water.'
                ],
                science: 'Powders lift and absorb the oil, making it easier to wash the remaining oil or grease out of your clothes.'
            }
        }
    },
    hard: {
        label: 'Hard surfaces',
        hacks: {
            'degreasing': {
                label: 'Degreasing surfaces',
                category: 'Hard surfaces',
                title: 'Degrease hard surfaces',
                tagline: 'Citric acid, hot water, dish soap, tea tree oil and a spray bottle.',
                steps: [
                    'Mix 200g citric a cid, 150 mL hot water, 20 mL dish soap and about 10-20 drops of tea tree oil into a spray bottle.',
                    'Shake well.',
                    'Spray onto affected surface, such as the inside of an oven, and let sit for at least 10 minutes.',
                    'Wipe down.'
                ],
                science: 'This solution works through a combination of acidic breakdown, surfactants and natural steam. It\'ll loosen grease and charred food without the need for heavy-duty cleaners.'
            },
            'limescale': {
                label: 'Limescale removal',
                category: 'Hard surfaces',
                title: 'Remove limescale from surfaces',
                tagline: 'Citric acid, hot water and a spray bottle.',
                steps: [
                    'Create a solution with 1 tablespoon of citric acid powder for each cup of hot water.',
                    '(Optional) Chop up some lemons and add to the mixture for aromatics.',
                    'Add solution to a spray bottle, shake before use.',
                    'Spray on affected surfaces, mirrors or glass and wipe/scrub as needed.'
                ],
                science: 'Citric acid is a mild acid making it gentle, but effective at cleaning the surfaces it\'s applied to, and safe for humans.'
            }
        }
    },
    jewellery: {
        label: 'Jewellery',
        hacks: {
            'silver-or-gold': {
                label: 'Tarnished silver or gold',
                category: 'Jewellery',
                title: 'Polish tarnished silver and gold',
                tagline: 'Aluminium foil, baking soda, salt and boiling water.',
                steps: [
                    'Line a bowl with aluminium foil, shiny side up.',
                    'Place silver or gold items on to the foil.',
                    'Cover all jewellery pieces with baking soda and add a pinch of salt.',
                    'Pour boiling water into the dish carefully.',
                    'Wait 10 minutes, then carefully wipe clean with a soft cloth.'
                ],
                science: 'Silver and gold are naturally resistant to corrosion by air, water and acid, while aluminium is more susceptible. Using the aluminium foil, ' +
                    'baking soda and hot water is a redox reaction that reverses tarnish. Sulphides on the jewellery transfers its sulphur to the aluminium foil ' +
                    'reducing the jewellery back to its metallic (shiny) state.'

            },
        }
    },
    mirror: {
        label: 'Mirror & glass',
        hacks: {
            'streak': {
                label: 'Streak-free glass',
                category: 'Mirror & glass',
                title: 'Clean mirrors and glass streak-free',
                tagline: 'Citric acid, hot water and a spray bottle.',
                steps: [
                    'Create a solution with 1 tablespoon of citric acid powder for each cup of hot water.',
                    '(Optional) Chop up some lemons and add to the mixture for aromatics.',
                    'Add solution to a spray bottle, shake before use.',
                    'Spray on affected glass surfaces or mirrors.',
                    'Wipe down with a lint-free cloth, or a newspaper.'
                ],
                science: 'Citric acid is a mild acid making it gentle, but effective at cleaning the surfaces it\'s applied to, and safe for humans.'
            }
        }
    },
}

function buildBreadcrumb (catKey, hackKey) {
    const bc = document.getElementById('breadcrumb');
    const catLabel = catKey ? hackData[catKey]?.label : null;
    const hackLabel = hackKey ? hackData[catKey]?.hacks[hackKey]?.label : null;
    let html = '<a href="hacks.html">Hacks</a>';
    if (catLabel) {
        html += ' <span class="sep">›</span> ';
        if (hackLabel) {
            html += `<a href="hacks.html?cat=${catKey}">${catLabel}</a>`;
            html += ` <span class="sep">›</span> <span class="current">${hackLabel}</span>`;
        } else {
            html += `<span class="current">${catLabel}</span>`;
        }
    }
    bc.innerHTML = html;
}

function renderHack(catKey, hackKey) {
    const display = document.getElementById('hack-display');
    if (!catKey || !hackKey) {
        display.innerHTML = '<div class="empty-state"><p>Select a category and hack above to get started.</p></div>';
        return;
    }
    const hack = hackData[catKey]?.hacks[hackKey];
    if (!hack) { display.innerHTML = '<div class="empty-state"><p>Hack not found.</p></div>'; return; }

    const stepsHtml = hack.steps.map((s, i) =>
        `<div class="step"><div class="step-num">${i + 1}</div><p class="step-text">${s}</p></div>`
    ).join('');

    display.innerHTML = `
    <div class="hack-card">
      <div class="hack-card-header">
        <div class="cat-badge">${hack.category}</div>
        <h1 class="hack-title">${hack.title}</h1>
        <p class="hack-tagline">${hack.tagline}</p>
        <button class="btn-save">&#9734; Save to favorites</button>
        <p class="login-note"><a href="#">Log in</a> to save hacks to your collection.</p>
      </div>
      <div class="hack-body">
        <p class="instructions-label">Instructions</p>
        <div class="steps">${stepsHtml}</div>
        <div class="fun-fact">
          <div class="fun-fact-avatar">&#x1F9EA;</div>
          <div class="fun-fact-content">
            <p class="fun-fact-title">Why it works</p>
            <p class="fun-fact-text">${hack.science}</p>
          </div>
        </div>
      </div>
    </div>
  `;
}

function populateHackSelect(catKey) {
    const sel = document.getElementById('hack-select');
    sel.innerHTML = '<option value="">Select hack...</option>';
    if (catKey && hackData[catKey]) {
        Object.entries(hackData[catKey].hacks).forEach(([key, h]) => {
            const opt = document.createElement('option');
            opt.value = key;
            opt.textContent = h.label;
            sel.appendChild(opt);
        });
    }
}

function onCatChange() {
    const catKey = document.getElementById('cat-select').value;
    populateHackSelect(catKey);
    buildBreadcrumb(catKey, null);
    renderHack(catKey, null);
    updateURL(catKey, null);
}

function onHackChange() {
    const catKey = document.getElementById('cat-select').value;
    const hackKey = document.getElementById('hack-select').value;
    buildBreadcrumb(catKey, hackKey);
    renderHack(catKey, hackKey);
    updateURL(catKey, hackKey);
}

function updateURL(cat, hack) {
    const params = new URLSearchParams();
    if (cat) params.set('cat', cat);
    if (hack) params.set('hack', hack);
    history.replaceState(null, '', '?' + params.toString());
}

const params = new URLSearchParams(window.location.search);
const initCat = params.get('cat') || '';
const initHack = params.get('hack') || '';
if (initCat) {
    document.getElementById('cat-select').value = initCat;
    populateHackSelect(initCat);
}
if (initHack) {
    document.getElementById('hack-select').value = initHack;
}
buildBreadcrumb(initCat, initHack);
renderHack(initCat, initHack);