/**
 * One visit linking a patient to a clinician at a fixed slot. The clinician
 * reference uses the common Clinician type, so any subclass can be booked.
 * Implements Comparable so visits can be ordered earliest to latest.
 */
public class Visit implements Comparable<Visit> {

    /** Patient attending. */
    private final Patient patient;

    /** Clinician the patient sees. */
    private final Clinician clinician;

    /** Slot as minutes from midnight, for example 510 = 08:30. */
    private final int slotMinutes;

    /**
     * Creates a visit after confirming details and a valid slot.
     *
     * @param patient    attending patient
     * @param clinician chosen clinician
     * @param slotMinutes slot as minutes from midnight
     */
    public Visit(Patient patient, Clinician clinician, int slotMinutes) {
        if (patient == null) {
            throw new IllegalArgumentException("Visit must have a patient.");
        }
        if (clinician == null) {
            throw new IllegalArgumentException("Visit must have a clinician.");
        }
        if (!OpeningHours.validSlot(slotMinutes)) {
            throw new IllegalArgumentException("Invalid slot: " + OpeningHours.label(slotMinutes));
        }
        this.patient = patient;
        this.clinician = clinician;
        this.slotMinutes = slotMinutes;
    }

    /** Returns the patient. */
    public Patient getPatient() {
        return patient;
    }

    /** Returns the clinician. */
    public Clinician getClinician() {
        return clinician;
    }

    /** Returns the slot minutes. */
    public int getSlotMinutes() {
        return slotMinutes;
    }

    /** Orders visits by time, earliest first. */
    @Override
    public int compareTo(Visit other) {
        return Integer.compare(this.slotMinutes, other.slotMinutes);
    }

    @Override
    public String toString() {
        return "At " + OpeningHours.label(slotMinutes)
                + " | " + patient
                + " | " + clinician.roleLabel()
                + " " + clinician.getName()
                + " (ID " + clinician.getId() + ")";
    }
}
