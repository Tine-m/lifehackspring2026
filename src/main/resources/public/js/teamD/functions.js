const selectedIngredients = new Set();
const countElement = document.getElementById("ingredient-count");

const searchBar = document.querySelector(".head-searchbar");
const searchInput = document.getElementById("search");

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
    });
});



searchBar.addEventListener("click", (e) => {
    if (!e.target.closest(".search-button")) {
        searchInput.focus();
    }
});

const categoryBlocks = document.querySelectorAll('.ingredients-category-item');

searchInput.addEventListener('input', () => {
    const query = searchInput.value.trim().toLowerCase();

    categoryBlocks.forEach(category => {
        const items = category.querySelectorAll('.ingredient-item');
        let anyVisible = false;

        items.forEach(item => {
            const name = item.dataset.name.toLowerCase();
            if (name.includes(query)) {
                item.style.display = '';
                anyVisible = true;
            } else {
                item.style.display = 'none';
            }
        });

        category.style.display = anyVisible ? '' : 'none';
    });
});