import { Routes, RouterModule } from '@angular/router';
import { GamePageComponent } from './game-page/game-page.component';
import { IndexPageComponent } from './index-page/index-page.component';


const appRoutes = [
    
    { path: 'games', component: GamePageComponent },
    { path: 'index', component: IndexPageComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);