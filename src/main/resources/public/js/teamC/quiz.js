document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('quizModal');
    const checkBtn = document.getElementById('checkBtn');
    let selectedButton = null //brugerens knap gemmes

    const answerButtons = document.querySelectorAll('.answer-btn');
    answerButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            //fjern selected-klasse fra de andre objekter
            answerButtons.forEach((b => b.classList.remove('selected')));

            //marker den knap der er trykket på
            btn.classList.add('selected');
            selectedButton = btn;
        });
    });
    //Check svar når check-knap klikkes
    checkBtn.addEventListener('click', () => {
        if (!selectedButton) {
            alert("Vælg venligst et svar først");
            return;
        }

        //Hent data fra den klikkede knap
        const isCorrect = selectedButton.getAttribute('data-is-correct') === 'true';
        const imgPath = selectedButton.getAttribute('data-img');
        const soundPath = selectedButton.getAttribute('data-sound');

        //Opdater modal-indhold
        document.getElementById('modalTitle').innerText = isCorrect ? "RIGTIGT! 😀" : "FORKERT! 💀";
        document.getElementById('modalTitle').style.color = isCorrect ? "#32CD32" : "#B22222";
        document.getElementById('modalImg').src = imgPath;

        //Afspil lyd
        const audio = new Audio (soundPath);
        audio.play().catch(error => {
            console.error("Lyd kunne ikke afspilles. Check om sti er korrekt:", error);
        });

        //Vis modal
        modal.style.display = 'block';
    });
});