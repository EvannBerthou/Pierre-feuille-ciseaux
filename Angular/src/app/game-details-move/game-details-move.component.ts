import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-game-details-move',
  templateUrl: './game-details-move.component.html',
  styleUrls: ['./game-details-move.component.scss']
})
export class GameDetailsMoveComponent {
    @Input() move: any;

    getClass(play: string) {
        return this.move?.coup == play ? "active-move" : "inactive-move";
    }
}
