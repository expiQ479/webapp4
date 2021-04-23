import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from '../interfaces/game.model';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-index-page',
  templateUrl: './index-page.component.html',
  
})
export class IndexPageComponent  {

  game:Game;

  constructor(private router: Router, activatedRoute:ActivatedRoute, private gameService: GameService) {
    let id = activatedRoute.snapshot.params['id'];

  
  }
  gotoGamePage() {this.router.navigate(['games']);}
}
