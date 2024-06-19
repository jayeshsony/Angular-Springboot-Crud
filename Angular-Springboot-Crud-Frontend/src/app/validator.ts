import { AbstractControl, ValidatorFn } from '@angular/forms';

// Validator function to validate mobile numbers
export function mobileNumberValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
        // Check if control has a value
        if (control.value) {
            // Remove all non-digit characters from the input value
            const cleanedValue = control.value.replace(/\D/g, '');

            // If cleaned value is different from original value, update the control's value
            if (cleanedValue !== control.value) {
                control.patchValue(cleanedValue, { emitEvent: false });
            }
        }

        // Return null if the value is valid
        return null;
    };
}

export function numberLengthValidator(minLength: number, maxLength: number): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      if (!control.value) {
        return null; // Handle if the control value is empty or null
      }
  
      const stringValue = control.value.toString();
      if (stringValue.length < minLength || stringValue.length > maxLength) {
        return { 'numberLength': true }; // Validation failed
      }
      
      return null; // Validation passed
    };
  }