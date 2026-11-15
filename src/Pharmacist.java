import java.util.List;

/** A pharmacist working in a dispensary section, possibly for controlled medicines. */
public class Pharmacist extends Clinician {

    /** Dispensary section, for example "Community pharmacy". */
    private final String dispensarySection;

    /** Whether authorised to supply controlled medicines. */
    private final boolean controlledDrugsAuthorized;

    /**
     * Creates a pharmacist.
     *
     * @param clinicianId              numeric ID
     * @param name                      full name
     * @param workingDays               working weekdays
     * @param dispensarySection         dispensary section
     * @param controlledDrugsAuthorized true if controlled medicines are authorised
     */
    public Pharmacist(String clinicianId, String name, List<String> workingDays,
                      String dispensarySection, boolean controlledDrugsAuthorized) {
        super(clinicianId, name, workingDays);
        if (dispensarySection == null || dispensarySection.isBlank()) {
            throw new IllegalArgumentException("Dispensary section must not be blank.");
        }
        this.dispensarySection = dispensarySection.trim();
        this.controlledDrugsAuthorized = controlledDrugsAuthorized;
    }

    @Override
    public String roleLabel() {
        return "Pharmacist";
    }

    @Override
    public String roleSummary() {
        String text = "Dispenses medicines in the " + dispensarySection + " section";
        if (controlledDrugsAuthorized) {
            return text + ", authorised for controlled medicines.";
        }
        return text + ", not authorised for controlled medicines.";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Section: " + dispensarySection
                + " | Controlled drugs: " + (controlledDrugsAuthorized ? "Yes" : "No");
    }
}
