import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentLandingPageComponent } from './student-landing-page.component';

describe('StudentLandingPageComponent', () => {
  let component: StudentLandingPageComponent;
  let fixture: ComponentFixture<StudentLandingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentLandingPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentLandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
