
export interface GameState {
    players: [ Player, Player ]; // a player array contains exactly two Players
    board: string[][];
    gameStatus: {
        endOfGame: boolean;
        winner: string;
    };
}

interface Player {
    name: string;
    type: "player1" | "player2"; // only "player1" and "player2" are valid options for this string
    hasTurn: boolean;
    score: number;
}

export interface Move{
    playerName: string;
    playerMove: number;
}
