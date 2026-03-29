let currentWord;
let wordsAmount = 0;
let wordsGuessed = 0;

const categoryBox = document.querySelector("#category");
const lettersBox = document.querySelector("#letters");
const hintBox = document.querySelector("#hint");
const displayBox = document.querySelector("#display-screen");
const progressTracker = document.querySelector("#progressTracker");
const keyboard = document.querySelector("#keyboard");

const keyboardRows = [
    ["Q","W","E","R","T","Y","U","I","O","P","Del"],
    ["A","S","D","F","G","H","J","K","L"],
    ["Z","X","C","V","B","N","M","Enter"]
];

document.addEventListener("DOMContentLoaded", startGame);

//button functionality
document.body.addEventListener("click", (e) => {
    if (e.target.classList.contains("btn-home")){
        window.location.href = "/codle"
    }

    if (e.target.id === "btn-start"){
        window.location.href = "/codle/play"
    }

    if (e.target.id === "btn-lifehack"){
        window.location.href = "/"
    }

    if (e.target.classList.contains("btn-continue")){
        let popUps = document.querySelectorAll(".pop-up.active");
        popUps.forEach(popUp => {
            popUp.classList.remove("active");
            popUp.classList.add("hidden");
        });
        window.location.href = "/codle/play";
    }
});

//Start game
async function startGame(){
    await loadWord();
    displayBox.style.setProperty("--letters", currentWord.wordLength);
    console.log(currentWord); //test kode
    await getWordsAmount();
    updateBoxesInfo();
    createLetterCards();
    createKeyboard();
}

//call /word endpoint to get Word
async function loadWord(){
    const word = await fetch("/word");
    currentWord = await word.json();
}

//Update layout info
function updateBoxesInfo(){
    categoryBox.textContent = "Kategori: " + currentWord.category;
    lettersBox.textContent = currentWord.wordLength + " bogstaver";
    hintBox.textContent = currentWord.hint;
    progressTracker.textContent = wordsGuessed + " / " + wordsAmount + " færdige";

}

function createLetterCards(){
    displayBox.innerHTML = "";

    for (let i=0; i<currentWord.wordLength; i++){
        const card = document.createElement("div");
        card.classList.add("letter-box");
        card.textContent = "_";
        displayBox.appendChild(card);
    }
}

async function getWordsAmount(){
    const response = await fetch("/words-Amount");
    wordsAmount = await response.json();
}

//create keyboard
function createKeyboard() {
    keyboardRows.forEach(row => {
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("keyboard-row");

        row.forEach(key => {
            const btn = document.createElement("button");
            btn.textContent = key;
            btn.classList.add("keyboard-key");
            btn.addEventListener("click", () => keyPress());
            rowDiv.appendChild(btn);
        });
        keyboard.appendChild(rowDiv);
    });
}