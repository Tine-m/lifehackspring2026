
const MAX_ATTEMPTS = 3;

export class WordGame {

    constructor() {
        this.allWords       = [];
        this.remainingWords = [];
        this.currentWord    = null;
        this.guesses        = [];
        this.currentGuess   = "";
        this.result         = null;
    };

    async loadWords (){
        const response = await fetch("/api/words");

        if(!response.ok){
            throw new Error(`Serverfejl: ${response.status}`);
        }

        const data = await response.json();

        this.allWords = data.map(word => ({
            word: word.word,
            hint: word.hint,
            category: word.category,
            length: word.length ?? word.word.length,
        }));

        this.remainingWords = [...this.allWords];
    }

    resetGame (){
        this.allWords       = [];
        this.remainingWords = [];
        this.currentWord    = null;
        this.guesses        = [];
        this.currentGuess   = "";
        this.result         = null;
    }

    startGame (){
        if (this.remainingWords.length === 0) return false;

        const randomIndex = Math.floor(Math.random() * this.remainingWords.length);

        this.currentWord = this.remainingWords[randomIndex];
        this.guesses = [];
        this.currentGuess = "";
        this.result = null;

        return true;
    }

    addLetter (letter) {
        if(!this.currentWord) return false;

        if(this.currentGuess.length >= this.currentWord.word.length){
            return false;
        }

        this.currentGuess += letter;
        return true;
    }

    removeLetter (){
        this.currentGuess = this.currentGuess.substring(0, this.currentGuess.length - 1);
    }

}