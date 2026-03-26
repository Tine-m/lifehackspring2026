document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('quizModal');
    const checkBtn = document.getElementById('checkBtn');

    checkBtn.addEventListener('click', () => {
        // 1. Find den radio-knap, som brugeren har valgt
        const selected = document.querySelector('input[name="userAnswer"]:checked');

        // Hvis ingen er valgt, giv en hurtig besked
        if (!selected) {
            alert("Vælg venligst et svar først!");
            return;
        }

        // 2. Tjek om værdien er "correct" (som I har skrevet i jeres HTML value="")
        const correctAnswer = document.getElementById('correctAnswer').value;
        const isCorrect = (selected.value === correctAnswer);


        // 3. Sæt indholdet i modalen baseret på svaret
        const title = document.getElementById('modalTitle');
        const img = document.getElementById('modalImg');

        title.innerText = isCorrect ? "RIGTIGT! 😀" : "FORKERT! 💀";
        title.style.color = isCorrect ? "#32CD32" : "#B22222"; // Grøn eller Rød

        // Vi bruger jeres faste filnavne her
        img.src = isCorrect ? "/media/correct.jpg" : "/media/wrong.jpg";
        const soundPath = isCorrect ? "/media/correct.mp3" : "/media/wrong.mp3";

        // 4. Afspil lyd (med fix for den fejl du så tidligere)
        const audio = new Audio(soundPath);
        audio.play().catch(error => console.log("Lyd-fejl (kan ignoreres):", error));

        // 5. Vis modalen
        modal.style.display = 'block';
    });
});