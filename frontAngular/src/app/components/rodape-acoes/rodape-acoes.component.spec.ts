import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RodapeAcoesComponent } from './rodape-acoes.component';

describe('RodapeAcoesComponent', () => {
  let component: RodapeAcoesComponent;
  let fixture: ComponentFixture<RodapeAcoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RodapeAcoesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RodapeAcoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
