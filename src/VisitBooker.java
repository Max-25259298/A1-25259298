import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores all visits, checks new bookings, and supports filtering, ordering
 * and cancellation. Every rejected action returns a warning message instead
 * of stopping the program.
 */
public class VisitBooker {

    /** Visits held by this booker. */
    private final List<Visit> visits = new ArrayList<>();

    /**
     * Books a visit after validation.
     *
     * @param patient    attending patient
     * @param clinician chosen clinician
     * @param slotMinutes slot minutes from midnight
     * @return success message, or a rejection warning
     */
    public String reserve(Patient patient, Clinician clinician, int slotMinutes) {
        if (patient == null) {
            return "Rejected: patient details are missing.";
        }
        if (clinician == null) {
            return "Rejected: a clinician must be chosen.";
        }
        if (!OpeningHours.validSlot(slotMinutes)) {
            return "Rejected: " + OpeningHours.label(slotMinutes) + " is outside clinic hours.";
        }
        for (Visit existing : visits) {
            if (existing.getClinician().equals(clinician)
                    && existing.getSlotMinutes() == slotMinutes) {
                return "Rejected: " + clinician.getName()
                        + " is already booked at " + OpeningHours.label(slotMinutes) + ".";
            }
        }
        visits.add(new Visit(patient, clinician, slotMinutes));
        return "OK: Booked " + patient.getPatientName()
                + " with " + clinician.getName()
                + " at " + OpeningHours.label(slotMinutes) + ".";
    }

    /** Returns an unmodifiable list of all visits. */
    public List<Visit> allVisits() {
        return Collections.unmodifiableList(visits);
    }

    /** Returns visits for the clinician with the given ID. */
    public List<Visit> searchById(String clinicianId) {
        List<Visit> matched = new ArrayList<>();
        for (Visit visit : visits) {
            if (visit.getClinician().getId().equals(clinicianId)) {
                matched.add(visit);
            }
        }
        return matched;
    }

    /** Returns visits for the patient with the given contact number. */
    public List<Visit> searchByContact(String phone) {
        List<Visit> matched = new ArrayList<>();
        for (Visit visit : visits) {
            if (visit.getPatient().getPhoneNumber().equals(phone)) {
                matched.add(visit);
            }
        }
        return matched;
    }

    /** Returns all visits sorted earliest first. */
    public List<Visit> earliestFirst() {
        List<Visit> ordered = new ArrayList<>(visits);
        Collections.sort(ordered);
        return ordered;
    }

    /** Sorts the supplied subset earliest first without touching stored data. */
    public List<Visit> earliestFirstSubset(List<Visit> subset) {
        List<Visit> ordered = new ArrayList<>(subset);
        Collections.sort(ordered);
        return ordered;
    }

    /**
     * Cancels the visit for a clinician ID and slot.
     *
     * @return success message, or a warning when none matches
     */
    public String cancelByIdAndSlot(String clinicianId, int slotMinutes) {
        for (int i = 0; i < visits.size(); i++) {
            Visit visit = visits.get(i);
            if (visit.getClinician().getId().equals(clinicianId)
                    && visit.getSlotMinutes() == slotMinutes) {
                visits.remove(i);
                return "OK: Cancelled visit for clinician ID " + clinicianId
                        + " at " + OpeningHours.label(slotMinutes) + ".";
            }
        }
        return "Rejected: no visit for clinician ID " + clinicianId
                + " at " + OpeningHours.label(slotMinutes) + ".";
    }

    /**
     * Cancels every visit for the given contact number.
     *
     * @return success message, or a warning when none matches
     */
    public String cancelByContact(String phone) {
        int before = visits.size();
        List<Visit> kept = new ArrayList<>();
        for (Visit visit : visits) {
            if (!visit.getPatient().getPhoneNumber().equals(phone)) {
                kept.add(visit);
            }
        }
        visits.clear();
        visits.addAll(kept);
        int removed = before - visits.size();
        if (removed == 0) {
            return "Rejected: no visit found for phone " + phone + ".";
        }
        return "OK: Cancelled " + removed + " visit(s) for phone " + phone + ".";
    }
}
