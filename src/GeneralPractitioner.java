import java.util.List;

/** A general practitioner who may also accept walk-in patients. */
public class GeneralPractitioner extends Clinician {

    /** Whether walk-in patients are seen without an appointment. */
    private final boolean takesWalkIns;

    /**
     * Creates a general practitioner.
     *
     * @param clinicianId  numeric ID
     * @param name          full name
     * @param workingDays   working weekdays
     * @param takesWalkIns  true if walk-ins are accepted
     */
    public GeneralPractitioner(String clinicianId, String name, List<String> workingDays,
                              boolean takesWalkIns) {
        super(clinicianId, name, workingDays);
        this.takesWalkIns = takesWalkIns;
    }

    @Override
    public String roleLabel() {
        return "General Practitioner";
    }

    @Override
    public String roleSummary() {
        if (takesWalkIns) {
            return "Sees booked patients and walk-ins.";
        }
        return "Sees booked patients only.";
    }

    @Override
    public String toString() {
        return commonDetails() + " | Walk-ins: " + (takesWalkIns ? "Yes" : "No");
    }
}
