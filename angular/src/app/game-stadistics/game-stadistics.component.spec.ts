import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameStadisticsComponent } from './game-stadistics.component';

describe('GameStadisticsComponent', () => {
  let component: GameStadisticsComponent;
  let fixture: ComponentFixture<GameStadisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameStadisticsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameStadisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
