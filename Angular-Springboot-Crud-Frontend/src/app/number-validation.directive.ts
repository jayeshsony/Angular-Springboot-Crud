import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appNumberValidation]'
})
export class NumberValidationDirective {

  @HostListener('keydown', ['$event']) onKeyDown(event: KeyboardEvent) {
    const forbiddenKeys = ['e', '-', '.'];
    if (forbiddenKeys.includes(event.key) && event.target instanceof HTMLInputElement && event.target.type === 'number') {
      event.preventDefault();
    }
  }

}