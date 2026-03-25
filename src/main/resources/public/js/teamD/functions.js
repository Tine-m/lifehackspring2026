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
    });
});