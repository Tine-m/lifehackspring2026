const selectedIngredients = new Set();
const countElement = document.getElementById("ingredient-count");

document.querySelectorAll('.ingredient-item').forEach(item => {
    item.addEventListener('click', () => {
        const name = item.dataset.name.toLowerCase();

        if (selectedIngredients.has(name)) {
            selectedIngredients.delete(name);
            item.classList.remove('selected');
        } else {
            selectedIngredients.add(name);
            item.classList.add('selected');
        }
        countElement.textContent = selectedIngredients.size;
        filter()
    });
});


function filter() {
    if (selectedIngredients.size === 0){
        renderRecipes(allRecipes)
        return;
    }

    const matching = allRecipes
        .filter(recipe => {
        const match = recipe.ingredients.filter(i =>
            selectedIngredients.has(i.toLowerCase())).length;

        const missing = recipe.ingredients.length - match;

        return match > 0 && missing <= 2
    }).sort((a, b) => {
        const aMatch = a.ingredients.filter(i => selectedIngredients.has(i.toLowerCase())).length
        const bMatch = b.ingredients.filter(i => selectedIngredients.has(i.toLowerCase())).length
        return bMatch - aMatch
        })

    renderRecipes(matching);

}

function renderRecipes(recipes) {
    const container = document.querySelector('.section-block-grid');
    if (selectedIngredients.size > 0) {
        document.getElementById('recipe-count').textContent = recipes.length;
        document.getElementById('reccipe-count').textContent = recipes.length;
    }
    container.innerHTML = recipes.map(recipe => `
        <div class="product-list-item">
            <div class="product-item-image">
                <img class="product-item-img" src="${recipe.imageUrl}">
            </div>
            <div class="product-item-text">
                <div class="product-item-text-head">
                    <h4 class="product-item-title">${recipe.title}</h4>
                </div>
                <div class="product-item-sub-text">
                    <a href="${recipe.url}">valdemarsro.dk</a>
                </div>
                <div class="product-item-sub-text">
                    retten tager <span>${recipe.totalTime}</span> min at lave
                </div>
                ${recipe.missing?.length > 0 ? `
                    <div class="product-item-missing">
                        Mangler: ${recipe.missing.join(', ')}
                    </div>` : `
                    <div class="product-item-complete"></div>`
    }
            </div>
        </div>
    `).join('');
}

renderRecipes(allRecipes);