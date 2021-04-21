import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { IndexComponent } from './index/index.component';
import { GamePageComponent } from './game-page/game-page.component';
import { AdminUpdatesComponent } from './admin-updates/admin-updates.component';
import { CreatePostPageComponent } from './create-post-page/create-post-page.component';
import { EditGameComponent } from './edit-game/edit-game.component';
import { EditPostPageComponent } from './edit-post-page/edit-post-page.component';
import { Error404Component } from './error404/error404.component';
import { Error500Component } from './error500/error500.component';
import { ErrorComponent } from './error/error.component';
import { ExpandedPostComponent } from './expanded-post/expanded-post.component';
import { GameListComponent } from './game-list/game-list.component';
import { GameStadisticsComponent } from './game-stadistics/game-stadistics.component';
import { ListPostComponent } from './list-post/list-post.component';
import { LoginComponent } from './login/login.component';
import { NewGameComponent } from './new-game/new-game.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { ShowMoreGamesComponent } from './show-more-games/show-more-games.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { SuccessPageComponent } from './success-page/success-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    IndexComponent,
    GamePageComponent,
    AdminUpdatesComponent,
    CreatePostPageComponent,
    EditGameComponent,
    EditPostPageComponent,
    Error404Component,
    Error500Component,
    ErrorComponent,
    ExpandedPostComponent,
    GameListComponent,
    GameStadisticsComponent,
    ListPostComponent,
    LoginComponent,
    NewGameComponent,
    ProfileComponent,
    RegisterComponent,
    ShowMoreGamesComponent,
    SubscriptionsComponent,
    SuccessPageComponent
  ],
  imports: [
    BrowserModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
