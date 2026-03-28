
const MAX_ATTEMPTS = 3;

const CATEGORY_COLORS = {
    OOP:      { bg: "rgba(59, 130, 246, 0.2)",  border: "rgba(59, 130, 246, 0.6)",  text: "#60A5FA" },
    Java:     { bg: "rgba(34, 197, 94, 0.2)",   border: "rgba(34, 197, 94, 0.6)",   text: "#4ADE80" },
    Database: { bg: "rgba(251, 113, 133, 0.2)", border: "rgba(251, 113, 133, 0.6)", text: "#FB7185" },
    Web:      { bg: "rgba(255, 177, 67, 0.2)",  border: "rgba(255, 177, 67, 0.6)",  text: "#FFB143" },
    Git:      { bg: "rgba(232, 121, 249, 0.2)", border: "rgba(232, 121, 249, 0.6)", text: "#E879F9" },
};

const KEYBOARD_ROWS = [
    ["Q","W","E","R","T","Y","U","I","O","P"],
    ["A","S","D","F","G","H","J","K","L"],
    ["ENTER","Z","X","C","V","B","N","M","DELETE"],
];

export class GameMapper {

    mapToRoundData (game) {
        return{
            category: game.currentWord.category,
            categoryColor: CATEGORY_COLORS[game.currentWord.category] ?? CATEGORY_COLORS.OOP,

            wordLength: game.wordLength,
            lettersLabel:  `${game.wordLength} bogstaver`,

            hint: game.currentWord.hint,

            attemptsLeft: game.attemptsLeft,

            grid: this._mapGrid(game),

            keyboard: this._mapKeyboard(game),

        };
    };

    mapToProgressBar (game){
        return{
            label: `${game.wordsCompleted} / ${game.totalWords} færdige`,
        };
    };


    mapToGuessResult (submitResult, game){
        return {
            guessIndex: game.guesses.length - 1,
            statuses: submitResult.statuses,
            wordLength: game.wordLength,
            isRoundOver: submitResult.won || submitResult.lost,
            won: submitResult.won,
            correctWord: submitResult.correctWord,
            gameDelay: (game.wordLength - 1) * 120 + 500,
        };
    };

    mapToGamePopUp (won, correctWord){
        return{
            won, correctWord,
        };
    };

    mapToCompleteScreen (game){
        return{
            total: game.totalWords,
        };
    };

    _mapGrid (game){
        const wordLen = game.wordLength;
        const tileSize = Math.min(52, Math.floor(400 / wordLen));
        const rows = [];

        for (let r = 0; r < MAX_ATTEMPTS; r++){
            const tiles = [];

            for (let c = 0; c < wordLen; c++){
                if (r < game.guesses.length){
                    const guess = game.guesses[r];

                    tiles.push({
                        letter: guess.word[c] ?? "",
                        status: guess.statuses[c],
                        size: tileSize,
                        revealed: true,
                    });

                } else if (r === game.guesses.length){
                    const letter = game.currentGuess[c] ?? "";
                    tiles.push({
                        letter,
                        status: null,
                        size: tileSize,
                        filled: letter !== "",
                        revealed: false,
                    });

                } else {
                    tiles.push({
                        letter: "",
                        status: null,
                        size: tileSize,
                        revealed: false,
                    });
                }
            }

            rows.push(tiles);

        }
        return rows;
    }


    _mapKeyboard (game){
        const statuses = {};

        game.guesses.forEach(g=> {
            g.word.split("").forEach((letter, position) => {
                const newStatus = g.statuses[position];
                const oldStatus = statuses[letter];

                if (oldStatus === "correct") return;
                if (oldStatus === "present" && newStatus !== "correct") return;

                statuses[letter] = newStatus;
            });
        });

        return KEYBOARD_ROWS.map(row =>
            row.map(key => ({
                key,
                status: statuses[key] ?? null,
                wide: key === "ENTER" || key === "DELETE",
            }))
        );
    }

}