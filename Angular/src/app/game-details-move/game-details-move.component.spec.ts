import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameDetailsMoveComponent } from './game-details-move.component';

describe('GameDetailsMoveComponent', () => {
  let component: GameDetailsMoveComponent;
  let fixture: ComponentFixture<GameDetailsMoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameDetailsMoveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameDetailsMoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
