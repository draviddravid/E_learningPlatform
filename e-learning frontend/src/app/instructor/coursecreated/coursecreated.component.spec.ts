import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursecreatedComponent } from './coursecreated.component';

describe('CoursecreatedComponent', () => {
  let component: CoursecreatedComponent;
  let fixture: ComponentFixture<CoursecreatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoursecreatedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoursecreatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
