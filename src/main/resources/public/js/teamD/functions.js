const selectedIngredients = new Set();
const countElement = document.getElementById("ingredient-count");

const searchBar = document.querySelector(".head-searchbar");
const searchInput = document.getElementById("search");

const ingredientItems = document.querySelectorAll('.ingredient-item');
const categoryBlocks = document.querySelectorAll('.ingredients-category-item');

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



// Toggle state
let showSelectedOnly = false;

fridgeButton.addEventListener("click", () => {
    showSelectedOnly = !showSelectedOnly; // flip the state

    ingredientItems.forEach(item => {
        const name = item.dataset.name.toLowerCase();

        if (showSelectedOnly) {
            // Show only selected
            item.style.display = selectedIngredients.has(name) ? '' : 'none';
        } else {
            // Show all
            item.style.display = '';
        }
    });

    // Hide empty categories if toggling
    categoryBlocks.forEach(category => {
        const items = category.querySelectorAll('.ingredient-item');
        const anyVisible = Array.from(items).some(i => i.style.display !== 'none');
        category.style.display = anyVisible ? '' : 'none';
    });

    // Optional: visual feedback for the button
    fridgeButton.classList.toggle("active", showSelectedOnly);
});