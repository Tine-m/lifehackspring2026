
const MAX_ATTEMPTS = 3;
const TOTAL_WORDS = 98;

export class WordGame {

    constructor() {
        this.currentWord    = null;
        this.guesses        = [];
        this.currentGuess   = "";
        this.result         = null;
        this.wordsCompleted = 0;
    };


    async fetchWords (){
        const response = await fetch("/word");

        if(!response.ok){
            throw new Error(`Serverfejl: ${response.status}`);
        }

        this.currentWord  = await response.json();
        this.guesses      = [];
        this.currentGuess = "";
        this.result       = null;
    };

    async deleteWord (){
        if (!this.currentWord) return;

        await fetch ("/word/delete", {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(this.currentWord),
        });
    };

    /*resetGame (){
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
    }*/

    addLetter (letter) {
        if(!this.currentWord) return false;

        if(this.currentGuess.length >= this.currentWord.word.length){
            return false;
        }

        this.currentGuess += letter;
        return true;
    };

    removeLetter (){
        this.currentGuess = this.currentGuess.substring(0, this.currentGuess.length - 1);
    };

    submitGuess (){
        if (!this.currentWord) return {ok: false, reason: "no-word"};

        const target = this.currentWord.word;

        if (!this.currentGuess.length !== target.length) {
            return {ok: false, reason: "short"};
        }

        const statuses = this._evaluate(this.currentGuess, target);
        const guessWord = this.currentGuess;

        this.guesses.push({word: guessWord, statuses});
        this.currentGuess = "";

        const won = statuses.every(s => s === "correct");
        const lost = !won && this.guesses.length >= MAX_ATTEMPTS;

        if (won || lost) {
            this.result = won ? "won" : "lost";
        }

        return {ok: true, statuses, guessWord, won, lost, correctWord : target};
    }

    _evaluate (guess, target) {

        const result = Array(guess.length).fill("absent");
        const targetArr = target.split("");

        // Correct position
        guess.split("").forEach((ch, i) => {
            if (ch === targetArr[i]){
                result[i] = "correct";
                targetArr[i] = null;
            }
        });

        guess.split("").forEach((ch, i) => {
            if (result[i] === "correct") return;
            const ti = targetArr.indexOf(ch);
            if (ti !== -1){
                result[i] = "present";
                targetArr[ti] = null;
            }
        });

        return result;
    };

    get attemptsLeft()  {
        return MAX_ATTEMPTS - this.guesses.length;
    }

    get wordLength()    {
        return this.currentWord ?.wordLength ?? 0;
    }

    get totalWords()    {
        return TOTAL_WORDS;
    }

    get hasMoreWords()  {
        return this.wordsCompleted < TOTAL_WORDS;
    }


}