import { CanActivateFn } from '@angular/router';

export const studentGuard: CanActivateFn = (route, state) => {
  const user = JSON.parse(localStorage.getItem('loggedInUser') || '{}');

  if (user?.role === 'STUDENT') {
    return true;
  } else {
    alert('Access Denied: student only!');
    window.history.back();
    return false; 
  }
};
