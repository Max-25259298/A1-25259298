import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Shared base class for every clinician at the service. Holds the details
 * common to all roles: a numeric ID, a name, and working weekdays. It is
 * abstract, so a generic unspecialised clinician cannot be created directly.
 */
public abstract class Clinician {

    /** Digits-only identifier, for example "1101". */
    private final String clinicianId;

    /** Clinician's full name. */
    private String name;

    /** Weekdays the clinician works. */
    private final List<String> workingDays;

    /**
     * Creates a clinician after validating the shared details.
     *
     * @param clinicianId numeric-only ID
     * @param name         full name, not blank
     * @param workingDays  working weekdays; Monday, Friday, Sunday if empty
     */
    public Clinician(String clinicianId, String name, List<String> workingDays) {
        if (clinicianId == null || !digitsOnly(clinicianId)) {
            throw new IllegalArgumentException("Clinician ID must contain digits only.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Clinician name must not be blank.");
        }
        this.clinicianId = clinicianId;
        this.name = name.trim();

        if (workingDays == null || workingDays.isEmpty()) {
            this.workingDays = new ArrayList<>();
            this.workingDays.add("Monday");
            this.workingDays.add("Friday");
            this.workingDays.add("Sunday");
        } else {
            // Defensive copy keeps the internal list safe from outside changes.
            this.workingDays = new ArrayList<>(workingDays);
        }
    }

    /** Checks text is non-empty and digits only. */
    private static boolean digitsOnly(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /** Returns the clinician ID. */
    public String getId() {
        return clinicianId;
    }

    /** Returns the clinician name. */
    public String getName() {
        return name;
    }

    /** Updates the name after a non-blank check. */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Clinician name must not be blank.");
        }
        this.name = name.trim();
    }

    /** Returns an unmodifiable copy of the working weekdays. */
    public List<String> getWorkingDays() {
        return Collections.unmodifiableList(workingDays);
    }

    /** Short role label, implemented by each subclass. */
    public abstract String roleLabel();

    /** One-line summary of the role's distinctive capability. */
    public abstract String roleSummary();

    /** Shared detail line reused by subclass toString methods. */
    protected String commonDetails() {
        return "ID: " + clinicianId
                + " | Name: " + name
                + " | Role: " + roleLabel()
                + " | Days: " + String.join(", ", workingDays);
    }

    /** Equal when the two clinicians carry the same ID. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Clinician)) {
            return false;
        }
        Clinician other = (Clinician) o;
        return this.clinicianId.equals(other.clinicianId);
    }

    @Override
    public int hashCode() {
        return clinicianId.hashCode();
    }

    @Override
    public String toString() {
        return commonDetails();
    }
}
