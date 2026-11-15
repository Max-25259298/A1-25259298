import java.util.List;

/** A paramedic who responds in a vehicle on a particular shift pattern. */
public class Paramedic extends Clinician {

    /** Response vehicle, for example "Ambulance". */
    private final String responseVehicle;

    /** Shift pattern, for example "Day shift". */
    private final String shiftType;

    /**
     * Creates a paramedic.
     *
     * @param clinicianId   numeric ID
     * @param name           full name
     * @param workingDays    working weekdays
     * @param responseVehicle response vehicle
     * @param shiftType      shift pattern
     */
    public Paramedic(String clinicianId, String name, List<String> workingDays,
                     String responseVehicle, String shiftType) {
        super(clinicianId, name, workingDays);
        if (responseVehicle == null || responseVehicle.isBlank()) {
            throw new IllegalArgumentException("Response vehicle must not be blank.");
        }
        if (shiftType == null || shiftType.isBlank()) {
            throw new IllegalArgumentException("Shift type must not be blank.");
        }
        this.responseVehicle = responseVehicle.trim();
        this.shiftType = shiftType.trim();
    }

    @Override
    public String roleLabel() {
        return "Paramedic";
    }

    @Override
    public String roleSummary() {
        return "Responds in the " + responseVehicle.toLowerCase()
                + " on the " + shiftType.toLowerCase() + ".";
    }

    @Override
    public String toString() {
        return commonDetails()
                + " | Vehicle: " + responseVehicle
                + " | Shift: " + shiftType;
    }
}
