import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration class for the PROG2004 Assessment 1 visit-booking system.
 *
 * <p>The main method runs a short scripted simulation: it introduces the
 * clinicians on the roster (Part 3), then books, filters, sorts and cancels
 * visits through the booker (Parts 4 to 6), printing every result to the
 * console. Both normal cases and error cases are shown.</p>
 *
 * <h2>Submission details</h2>
 * <ul>
 *     <li>Name: Max</li>
 *     <li>SCU account: 25259298</li>
 *     <li>GitHub repository link: https://github.com/Max-25259298/A1-25259298</li>
 *     <li>Demonstration video link (SCU OneDrive share): &lt;paste video link here&gt;</li>
 * </ul>
 *
 * <p>GenAI declaration: I acknowledge that I have used Generative AI tools
 * to complete this assessment in accordance with the conditions outlined in
 * the Assessment Brief. I used DeepSeek to summarise the assignment
 * requirements, explain OOP concepts, and guide my code design. I wrote all
 * source code and this submission myself.</p>
 */
public class AssignmentOne {

    /**
     * Entry point. Runs the roster display and the booking demonstration.
     *
     * @param args command-line arguments, unused
     */
    public static void main(String[] args) {
        showClinicians();
        runVisits();
    }

    /** Prints a section heading. */
    private static void printHeader(String title) {
        System.out.println("=== " + title + " ===");
    }

    /**
     * Part 3: build clinicians of several types, store them under the common
     * Clinician type, and print details plus role summary for each.
     */
    private static void showClinicians() {
        printHeader("PART 3 - Clinicians on the roster");

        List<Clinician> roster = new ArrayList<>();
        roster.add(new GeneralPractitioner("1101", "Dr Ivy Chen", null, true));
        roster.add(new GeneralPractitioner("1102", "Dr Leo Park", null, false));
        roster.add(new Dentist("2201", "Dr Ruby Ng", null, "Paediatric dentistry", true));
        roster.add(new Pharmacist("3301", "Sam Whitfield", null, "Community pharmacy", true));
        roster.add(new Paramedic("4401", "Noah Blake", null, "Response car", "Day shift"));

        for (Clinician member : roster) {
            System.out.println(member);
            System.out.println("    Role: " + member.roleSummary());
        }
        System.out.println();
    }

    /**
     * Parts 4 to 6: book visits, filter and sort them, show rejected cases,
     * then cancel real visits and print what remains.
     */
    private static void runVisits() {
        printHeader("PART 4-5 - Booking visits");

        VisitBooker booker = new VisitBooker();

        Clinician gpChen = new GeneralPractitioner("1101", "Dr Ivy Chen", null, true);
        Clinician gpPark = new GeneralPractitioner("1102", "Dr Leo Park", null, false);
        Clinician dentistNg = new Dentist("2201", "Dr Ruby Ng", null, "Paediatric dentistry", true);
        Clinician pharmacistWhit = new Pharmacist("3301", "Sam Whitfield", null, "Community pharmacy", true);
        Clinician paramedicBlake = new Paramedic("4401", "Noah Blake", null, "Response car", "Day shift");

        Patient lily = new Patient("Lily Adams", "0413000111");
        Patient tom = new Patient("Tom Becker", "0413000222");
        Patient zoe = new Patient("Zoe Carter", "0413000333");
        Patient ryan = new Patient("Ryan Diaz", "0413000444");
        Patient eva = new Patient("Eva Ellis", "0413000555");

        System.out.println(booker.reserve(lily, gpChen, 480));
        System.out.println(booker.reserve(tom, dentistNg, 510));
        System.out.println(booker.reserve(zoe, pharmacistWhit, 540));
        System.out.println(booker.reserve(ryan, gpPark, 570));
        System.out.println(booker.reserve(eva, paramedicBlake, 600));
        System.out.println();

        printHeader("Filter by clinician ID 1101");
        for (Visit visit : booker.searchById("1101")) {
            System.out.println(visit);
        }
        System.out.println();

        printHeader("Filter by phone 0413000222");
        for (Visit visit : booker.searchByContact("0413000222")) {
            System.out.println(visit);
        }
        System.out.println();

        printHeader("All visits, earliest first");
        for (Visit visit : booker.earliestFirst()) {
            System.out.println(visit);
        }
        System.out.println();

        printHeader("Subset for ID 1101, earliest first");
        for (Visit visit : booker.earliestFirstSubset(booker.searchById("1101"))) {
            System.out.println(visit);
        }
        System.out.println();

        printHeader("Rejected cases");
        Patient uma = new Patient("Uma Fox", "0413000999");
        System.out.println(booker.reserve(uma, gpChen, 480));
        System.out.println(booker.reserve(lily, gpPark, 1020));
        System.out.println(booker.cancelByIdAndSlot("2201", 840));
        System.out.println(booker.cancelByContact("0499999000"));
        System.out.println();

        printHeader("After cancellations");
        System.out.println(booker.cancelByIdAndSlot("1101", 480));
        System.out.println(booker.cancelByContact("0413000222"));
        System.out.println();
        for (Visit visit : booker.earliestFirst()) {
            System.out.println(visit);
        }
    }
}
