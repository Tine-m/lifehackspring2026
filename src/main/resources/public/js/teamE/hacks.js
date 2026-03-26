async function fetchIngredients(recipeId) {
    try {
        const response = await fetch(`/api/recipe/${recipeId}/ingredients`);
        if (!response.ok) throw new Error("Failed to fetch ingredients");
        return await response.json();
    } catch (err) {
        console.error("Error fetching ingredients:", err);
        return [];
    }
}

function getCategoryId(catKey) {
    const map = {
        "jewellery": 1,
        "mirror": 2,
        "hard": 3,
        "soft": 4
    };
    return map[catKey];
}

async function fetchRecipesByCategory(catKey) {
    const categoryId = getCategoryId(catKey);

    try {
        const res = await fetch(`/api/recipes/${categoryId}`);
        if (!res.ok) throw new Error("Failed to fetch recipes");
        return await res.json();
    } catch (err) {
        console.error("Error fetching recipes:", err);
        return [];
    }
}

const hackData = {
    soft: {
        label: 'Soft surfaces',
        hacks: {
            'red-wine': { label: 'Red wine / fruit stains', index: 2 },
            'grease': { label: 'Oil or grease', index: 1 },
            'blood': { label: 'Blood stains', index: 0 }
        }
    },
    hard: {
        label: 'Hard surfaces',
        hacks: {
            'degreasing': { label: 'Degreasing surfaces', index: 1 },
            'limescale': { label: 'Limescale removal', index: 0 }
        }
    },
    jewellery: {
        label: 'Jewellery',
        hacks: {
            'silver-or-gold': { label: 'Tarnished silver or gold', index: 0 }
        }
    },
    mirror: {
        label: 'Mirror & glass',
        hacks: {
            'streak': { label: 'Streak-free glass', index: 0 }
        }
    }
};

function buildBreadcrumb(catKey, hackKey) {
    const bc = document.getElementById('breadcrumb');

    let html = '<a href="hacks.html">Hacks</a>';

    if (catKey && hackData[catKey]) {
        html += ` <span class="sep">›</span> ${hackData[catKey].label}`;
    }

    if (hackKey && hackData[catKey]?.hacks[hackKey]) {
        html += ` <span class="sep">›</span> ${hackData[catKey].hacks[hackKey].label}`;
    }

    bc.innerHTML = html;
}

async function renderHack(catKey, hackKey) {
    const display = document.getElementById('hack-display');

    if (!catKey || !hackKey) {
        display.innerHTML = '<div class="empty-state"><p>Select a category and hack above to get started.</p></div>';
        return;
    }

    const recipes = await fetchRecipesByCategory(catKey);

    if (!recipes || recipes.length === 0) {
        display.innerHTML = '<div class="empty-state"><p>No recipes found.</p></div>';
        return;
    }

    const hack = hackData[catKey]?.hacks[hackKey];

    if (!hack) {
        display.innerHTML = '<div class="empty-state"><p>Hack not found.</p></div>';
        return;
    }

    const recipe = recipes[hack.index];

    if (!recipe) {
        display.innerHTML = '<div class="empty-state"><p>Recipe not found.</p></div>';
        return;
    }

    const recipeId = recipe.recipeId;
    const name = recipe.recipeName;
    const method = recipe.method;
    const science = recipe.whyItWorks;

    const ingredients = await fetchIngredients(recipeId);

    const ingredientsHtml = ingredients.length > 0
        ? `
        <div class="ingredients">
            <p class="instructions-label">Ingredients</p>
            <ul class="ingredients-list">
                ${ingredients.map(i => `
                    <li class="ingredient-item">
                      <span class="ingredient-name">${i.ingredientName || i.ingredient_name}</span>
                      <span class="ingredient-qty">${i.quantity || ''}</span>
                    </li>
                `).join('')}
            </ul>
        </div>
        `
        : '';

    const stepsArray = method
        ? method.split('\n').filter(s => s.trim() !== '')
        : [];

    const stepsHtml = stepsArray.map((s, i) => `
        <div class="step">
            <div class="step-num">${i + 1}</div>
            <p class="step-text">${s}</p>
        </div>
    `).join('');

    display.innerHTML = `
    <div class="hack-card">

      <div class="hack-card-header">
        <div class="cat-badge">${hackData[catKey].label}</div>
        <h1 class="hack-title">${name}</h1>
        <p class="hack-tagline">${name}</p>
        <button class="btn-save">&#9734; Save to favorites</button>
        <p class="login-note"><a href="#">Log in</a> to save hacks to your collection.</p>
      </div>

      <div class="hack-body">

        ${ingredientsHtml}

        <p class="instructions-label">Instructions</p>
        <div class="steps">${stepsHtml}</div>

        <div class="fun-fact">
          <div class="fun-fact-avatar">&#x1F9EA;</div>
          <div class="fun-fact-content">
            <p class="fun-fact-title">Why it works</p>
            <p class="fun-fact-text">${science}</p>
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
}

function onHackChange() {
    const catKey = document.getElementById('cat-select').value;
    const hackKey = document.getElementById('hack-select').value;

    buildBreadcrumb(catKey, hackKey);
    renderHack(catKey, hackKey);
}

document.addEventListener("DOMContentLoaded", () => {
    buildBreadcrumb('', '');
});