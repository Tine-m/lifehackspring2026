
const MAX_ATTEMPTS = 3;

export class WordGame {

    constructor() {
        this.allWords       = [];
        this.remainingWords = [];
        this.currentWord    = null;
        this.guesses        = [];
        this.currentInput   = "";
        this.result         = null;
    }
}