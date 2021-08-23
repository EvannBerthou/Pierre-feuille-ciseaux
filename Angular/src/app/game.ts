export interface Tour {
    id: Number;
    player: Number;
    coup: String;
}

export interface Game {
    id: Number;
    tour1: Tour;
    tour2: Tour;
    winner: Number;
    ended: Boolean;
    creationDate: Date;
}
