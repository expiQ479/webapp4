import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';
import { Game } from './interfaces/game.model';


@Component({
  selector: 'app ',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  imgUrl = "assets/logo.png"  
  game: Game;

  constructor(private gameService: GameService){ }

  ngOnInit() {
    this.getGames();
  }

  getGames(){
    this.gameService.getGameById(1).subscribe(
      game => {
        this.game = game as Game;
      }
    );
  }

}
