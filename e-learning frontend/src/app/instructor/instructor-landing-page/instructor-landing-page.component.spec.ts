import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorLandingComponent } from './instructor-landing-page.component';

describe('InstructorLandingPageComponent', () => {
  let component: InstructorLandingComponent;
  let fixture: ComponentFixture<InstructorLandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstructorLandingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InstructorLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
