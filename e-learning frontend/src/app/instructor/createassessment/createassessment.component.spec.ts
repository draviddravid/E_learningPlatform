import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateassessmentComponent } from './createassessment.component';

describe('CreateassessmentComponent', () => {
  let component: CreateassessmentComponent;
  let fixture: ComponentFixture<CreateassessmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateassessmentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateassessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
