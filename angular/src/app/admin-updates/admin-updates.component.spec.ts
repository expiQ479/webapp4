import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdatesComponent } from './admin-updates.component';

describe('AdminUpdatesComponent', () => {
  let component: AdminUpdatesComponent;
  let fixture: ComponentFixture<AdminUpdatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUpdatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUpdatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
