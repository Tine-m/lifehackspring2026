let currentWord;
let currentWordArray;
let userWordGuess = "";

const totalTries = 3;
let userTries = 0;

//progress tracker
let wordsAmount = 0;
let wordsGuessed = 0;

const categoryBox = document.querySelector("#category");
const lastGuess = document.querySelector("#last-guess");
const lettersBox = document.querySelector("#letters");
const hintBox = document.querySelector("#hint");
const displayBox = document.querySelector("#display-screen");
const progressTracker = document.querySelector("#progressTracker");
const keyboard = document.querySelector("#keyboard");
const popUpWin = document.querySelector("#pop-up-win");
const popUpLose = document.querySelector("#pop-up-lose");
const wordAnswer = document.querySelector("#word-answer");
const attemptsTracker = document.querySelector("#attempts-tracker");

const keyboardRows = [
    ["Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Del"],
    ["A", "S", "D", "F", "G", "H", "J", "K", "L"],
    ["Z", "X", "C", "V", "B", "N", "M", "Enter"]
];

document.addEventListener("DOMContentLoaded", startGame);

//button functionality
document.body.addEventListener("click", (e) => {
    if (e.target.classList.contains("btn-home")) {
        window.location.href = "/codle"
    }

    if (e.target.id === "btn-start" || e.target.id === "btn-restart") {
        window.location.href = "/codle/play"
    }

    if (e.target.id === "btn-lifehack") {
        window.location.href = "/"
    }

    if (e.target.classList.contains("btn-continue")) {
        let popUps = document.querySelectorAll(".pop-up.active");
        popUps.forEach(popUp => {
            popUp.classList.remove("active");
            popUp.classList.add("hidden");
        });
        window.location.href = "/codle/play";
    }
});

//Start game
async function startGame() {
    await loadWord();
    displayBox.style.setProperty("--letters", currentWord.wordLength);
    console.log(currentWord); //test kode
    await getWordsAmount();
    await getWordsGuessed();
    updateBoxesInfo();
    createLetterCards();
    createKeyboard();
}

//call /word endpoint to get Word
async function loadWord() {
    const word = await fetch("/word");
    currentWord = await word.json();
    currentWordArray = currentWord.word.split("");
}

async function deleteWord() {
    await fetch(`/word/delete/${currentWord.id}`, {method: "DELETE"});
}

//Update layout info
function updateBoxesInfo() {
    categoryBox.textContent = "Kategori: " + currentWord.category;
    lettersBox.textContent = currentWord.wordLength + " bogstaver";
    hintBox.textContent = currentWord.hint;
    progressTracker.textContent = wordsGuessed + " / " + wordsAmount + " færdige";
    attemptsTracker.textContent = userTries + " / " + totalTries + " forsøg";
}

async function incrementProgress() {
    await fetch("/increment-guessed-words", {method: "POST"});
}

async function getWordsGuessed() {
    const amount = await fetch("/words-Amount-Guessed");
    wordsGuessed = await amount.text();
}

function createLetterCards() {
    displayBox.innerHTML = "";

    for (let i = 0; i < currentWord.wordLength; i++) {
        const card = document.createElement("div");
        card.classList.add("letter-box");
        card.textContent = "_";
        displayBox.appendChild(card);
    }
}

async function getWordsAmount() {
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
            btn.addEventListener("click", () => keyPress(key));
            rowDiv.appendChild(btn);
        });
        keyboard.appendChild(rowDiv);
    });
}

async function keyPress(key) {
    if (key === "Del" && userWordGuess.length > 0) {
        userWordGuess = userWordGuess.slice(0, -1);
        updateLetterCards(userWordGuess);
    } else if (key === "Enter") {
        if (userTries < 3){userTries++}
        attemptsTracker.textContent = userTries + " / " + totalTries + " forsøg";
        checkLetters(userWordGuess);
        lastGuess.textContent = "Sidste gæt: " + userWordGuess;
        await checkForWin();
    } else if (userWordGuess.length < currentWord.word.length &&
        key !== "Del" && key !== "Enter") {
        userWordGuess += key;
        updateLetterCards(userWordGuess);
    }
}

function updateLetterCards(word) {
    const letterBox = document.querySelectorAll(".letter-box");
    letterBox.forEach((card, index) => {
        card.textContent = word[index] || "_";
    });
}


function checkLetters(userGuess) {
    const keys = document.querySelectorAll(".keyboard-key");
    let userGuessArray = userGuess.split("");
    let letterBoxes = document.querySelectorAll(".letter-box");

    keys.forEach(key => {
        const color = getComputedStyle(key).backgroundColor;
        if (color === "rgb(0, 128, 0)" || color === "rgb(255, 177, 67)") {
            key.style.backgroundColor = "whitesmoke";
        }
    });

    for (let i = 0; i < userGuessArray.length; i++) {
        let letter = userGuessArray[i];

        for (let k = 0; k < keys.length; k++) {
            if (keys[k].textContent === letter) {
                if (letter === currentWordArray[i]) {
                    keys[k].style.backgroundColor = "green";
                    letterBoxes[i].style.backgroundColor = "green";
                } else if (currentWordArray.includes(letter)) {
                    keys[k].style.backgroundColor = "#FFB143";
                    letterBoxes[i].style.backgroundColor = "#FFB143"
                } else {
                    keys[k].style.backgroundColor = "grey";
                    letterBoxes[i].style.backgroundColor = "grey";
                }
                break;
            }
        }
    }
}

async function checkForWin() {
    if (userWordGuess === currentWord.word) {
        popUpWin.classList.remove("hidden");
        popUpWin.classList.add("active");
        await deleteWord();
        await incrementProgress();

    } else if (userTries === totalTries) {
        wordAnswer.textContent = currentWord.word;
        popUpLose.classList.remove("hidden");
        popUpLose.classList.add("active");
    }
}
