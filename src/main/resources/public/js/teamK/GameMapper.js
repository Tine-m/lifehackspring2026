
const MAX_ATTEMPTS = 3;

const CATEGORY_COLORS = {
    OOP:      { bg: "rgba(33,150,243,0.13)",  border: "rgba(33,150,243,0.4)",  text: "#2196F3" },
    Java:     { bg: "rgba(76,175,80,0.13)",   border: "rgba(76,175,80,0.4)",   text: "#4CAF50" },
    Database: { bg: "rgba(244,67,54,0.13)",   border: "rgba(244,67,54,0.4)",   text: "#F44336" },
    Web:      { bg: "rgba(255,235,59,0.13)",  border: "rgba(255,235,59,0.4)",  text: "#FFEB3B" },
    Git:      { bg: "rgba(156,39,176,0.13)",  border: "rgba(156,39,176,0.4)",  text: "#9C27B0" },
};

const KEYBOARD_ROWS = [
    ["Q","W","E","R","T","Y","U","I","O","P"],
    ["A","S","D","F","G","H","J","K","L"],
    ["ENTER","Z","X","C","V","B","N","M","DELETE"],
];

export class GameMapper {

    mapToProgressBar (game){
        return{
           label: `${game.wordsCompleted} / ${game.totalWords} færdige`,
        };
    };

    mapToRoundData (game) {
        return{
            category: game.currentWord.category,
            categoryColor: CATEGORY_COLORS[game.currentWord.category] ?? CATEGORY_COLORS.OOP,

            hint: `"${game.currentWord.hint}"`,
            wordLength: `${game.wordLength} bogstaver,`,

            grid: this.mapGrid(game),
            keyboard: this.createKeyboard(game),

        };
    };


}