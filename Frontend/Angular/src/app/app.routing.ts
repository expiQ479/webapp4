import { Routes, RouterModule } from '@angular/router';
import { GamePageComponent } from './game-page/game-page.component';


const appRoutes = [
    { path: 'games/:id', component: GamePageComponent }, 
    { path: '', redirectTo: 'index', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);