import { Routes, RouterModule } from '@angular/router';
import { GamePageComponent } from './game-page/game-page.component';
import { IndexComponent } from './index/index.component';



const appRoutes = [
    { path: '/', component: IndexComponent },
    { path: 'games/:id', component: GamePageComponent }
    //{ path: '', redirectTo: 'books', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);