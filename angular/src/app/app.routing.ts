import { Routes, RouterModule } from '@angular/router';
import { GamePageComponent } from './game-page/game-page.component';
import { IndexComponent } from './index/index.component';
import { GameStadisticsComponent } from './game-stadistics/game-stadistics.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { ListPostComponent } from './list-post/list-post.component';
import { NewGameComponent } from './new-game/new-game.component';
import { GameListComponent } from './game-list/game-list.component';
import { ExpandedPostComponent } from './expanded-post/expanded-post.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { CreatePostPageComponent } from './create-post-page/create-post-page.component';
import { EditPostPageComponent } from './edit-post-page/edit-post-page.component';
import { AdminUpdatesComponent } from './admin-updates/admin-updates.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { SuccessPageComponent } from './success-page/success-page.component';
import { ErrorComponent } from './error/error.component';
import { Error404Component } from './error404/error404.component';
import { Error500Component } from './error500/error500.component';
import { ShowMoreGamesComponent } from './show-more-games/show-more-games.component';

/*const appRoutes = [
    { path: 'books', component: BookListComponent },
    { path: 'books/new', component: BookFormComponent },
    { path: 'books/:id', component: BookDetailComponent },    
    { path: 'books/edit/:id', component: BookFormComponent },
    { path: '', redirectTo: 'books', pathMatch: 'full' }
]*/

const appRoutes = [
    { path: '/', component: IndexComponent },
    { path: 'games/:id', component: GamePageComponent },
    { path: 'games/gameStadistics/:id', component: GameStadisticsComponent},
    { path: 'games/listPosts/:id', component: ListPostComponent},
    { path: 'games/listPosts/expandedPost/:id', component: ExpandedPostComponent},
    { path: 'games/listPosts/expandedPost/new', component: CreatePostPageComponent},
    { path: 'games/listPosts/expandedPost/edit/:id', component: EditPostPageComponent},
    { path: 'games/new', component: NewGameComponent},
    { path: 'games/edit/:id', component: EditGameComponent},
    { path: 'games', component: GameListComponent},
    { path: 'adminUpdates', component: AdminUpdatesComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegisterComponent},
    { path: 'profile/:id', component: ProfileComponent},
    { path: 'subscriptions', component: SubscriptionsComponent},
    { path: 'successPage', component: SuccessPageComponent},
    { path: 'error', component: ErrorComponent},
    { path: 'error-404', component: Error404Component},
    { path: 'error-500', component: Error500Component},
    { path: 'showmoregames', component: ShowMoreGamesComponent}
    //{ path: '', redirectTo: 'books', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);