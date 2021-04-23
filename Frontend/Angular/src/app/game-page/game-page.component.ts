import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from '../interfaces/game.model';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game-page',
  templateUrl: './game-page.component.html',
  
})
export class GamePageComponent {
  
game:Game;

  constructor(private router: Router, activatedRoute:ActivatedRoute, private gameService: GameService) {
    let id = activatedRoute.snapshot.params['id'];
    /*this.gameService.getGameById(id).subscribe(
      game => {
        this.game = game as Game;
      }
    );*/
   }

}
/*<a [routerLink] ="['games/:id',game.gameTitle]">
  {{game.gameTitle}}
  </a>*/