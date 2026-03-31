document.getElementById('favoritForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = new FormData(this);

    fetch('/teamI_copi', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(message => {
            console.log("Server svar:", message);

            const popup = document.getElementById('popupMessage');
            popup.textContent = message;
            popup.style.display = 'block';

            setTimeout(() => {
                popup.style.display = 'none';
            }, 3000);
        })
        .catch(error => {
            console.error('Fejl:', error);
        });
});