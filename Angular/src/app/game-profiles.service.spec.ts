import { TestBed } from '@angular/core/testing';

import { GameProfilesService } from './game-profiles.service';

describe('GameProfilesService', () => {
  let service: GameProfilesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameProfilesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
