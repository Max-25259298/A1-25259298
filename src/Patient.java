/** A patient attending the service, holding name and contact number. */
public class Patient {

    /** Patient's full name. */
    private final String patientName;

    /** Digits-only contact number. */
    private final String phoneNumber;

    /**
     * Creates a patient after validating the contact details.
     *
     * @param patientName full name, not blank
     * @param phoneNumber 8 to 12 digits
     */
    public Patient(String patientName, String phoneNumber) {
        if (patientName == null || patientName.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (phoneNumber == null || !validPhone(phoneNumber.trim())) {
            throw new IllegalArgumentException("Phone number must be 8 to 12 digits.");
        }
        this.patientName = patientName.trim();
        this.phoneNumber = phoneNumber.trim();
    }

    /** Checks the number is 8 to 12 digits and digits only. */
    private static boolean validPhone(String number) {
        if (number.length() < 8 || number.length() > 12) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /** Returns the patient name. */
    public String getPatientName() {
        return patientName;
    }

    /** Returns the contact number. */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient: " + patientName + " | Phone: " + phoneNumber;
    }
}
