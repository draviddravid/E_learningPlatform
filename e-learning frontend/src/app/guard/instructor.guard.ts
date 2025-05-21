import { CanActivateFn } from '@angular/router';

export const instructorGuard: CanActivateFn = (route, state) => {
  const user = JSON.parse(localStorage.getItem('loggedInUser') || '{}');

  if (user?.role === 'INSTRUCTOR') {
    return true;
  } else {
    alert('Access Denied: Instructor only!');
    window.history.back();
    return false; 
  }
};
