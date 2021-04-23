import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { GamePageComponent } from './game-page/game-page.component';
import { routing } from './app.routing';

@NgModule({
  declarations: [AppComponent,HeaderComponent,FooterComponent, GamePageComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, /*routing*/],
  providers: [],
  bootstrap: [AppComponent] 
})
export class AppModule { }
