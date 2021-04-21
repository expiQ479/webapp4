import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowMoreGamesComponent } from './show-more-games.component';

describe('ShowMoreGamesComponent', () => {
  let component: ShowMoreGamesComponent;
  let fixture: ComponentFixture<ShowMoreGamesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowMoreGamesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowMoreGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
