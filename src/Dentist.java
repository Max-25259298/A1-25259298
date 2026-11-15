import java.util.List;

/** A dentist who works in a dental area and may cover emergencies. */
public class Dentist extends Clinician {

    /** Dental area of practice, for example "Paediatric dentistry". */
    private final String specialtyArea;

    /** Whether dental emergencies are covered. */
    private final boolean emergencyOnCall;

    /**
     * Creates a dentist.
     *
     * @param clinicianId     numeric ID
     * @param name             full name
     * @param workingDays      working weekdays
     * @param specialtyArea     dental area
     * @param emergencyOnCall  true if emergencies are covered
     */
    public Dentist(String clinicianId, String name, List<String> workingDays,
                   String specialtyArea, boolean emergencyOnCall) {
        super(clinicianId, name, workingDays);
        if (specialtyArea == null || specialtyArea.isBlank()) {
            throw new IllegalArgumentException("Dental area must not be blank.");
        }
        this.specialtyArea = specialtyArea.trim();
        this.emergencyOnCall = emergencyOnCall;
    }

    @Override
    public String roleLabel() {
        return "Dentist";
    }

    @Override
    public String roleSummary() {
        String text = "Specialises in " + specialtyArea;
        if (emergencyOnCall) {
            return text + " and covers dental emergencies.";
        }
        return text + " and does not cover emergencies.";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Area: " + specialtyArea
                + " | Emergency: " + (emergencyOnCall ? "Yes" : "No");
    }
}
