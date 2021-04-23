import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from 'src/app/services/game.service';
import { Game } from './interfaces/game.model';


@Component({
  selector: 'app-root',
  
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  imgUrl = "assets/logo.png"  
  game: Game;
  //router: Router;
  constructor(private gameService: GameService ,private router: Router){ }

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
  gotoGamePage() {this.router.navigate(['games/:id']);}
}
