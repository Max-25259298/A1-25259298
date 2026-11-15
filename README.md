# PROG2004 Assessment 1 — Visit Booking System

- Name: Max
- SCU account: 25259298
- GitHub repository: https://github.com/Max-25259298/A1-25259298

A short Java console application that models clinicians at a health service and
manages their visits. There is no UI, file storage, or database; the `main`
method runs a scripted demonstration that prints normal and rejected cases to
the console.

## Source files

| File | Role |
| --- | --- |
| `Clinician.java` | Abstract base class holding shared ID, name and working days |
| `GeneralPractitioner.java` | GP that may accept walk-ins |
| `Dentist.java` | Dentist with dental area and emergency cover |
| `Pharmacist.java` | Pharmacist with dispensary section and controlled-drug authorisation |
| `Paramedic.java` | Paramedic with response vehicle and shift pattern |
| `Patient.java` | Patient name and contact number |
| `Visit.java` | One visit linking a patient, clinician and slot; `Comparable` |
| `OpeningHours.java` | Fixed 08:00–16:00 timetable, 30-minute slots |
| `VisitBooker.java` | Booking, filtering, ordering and cancellation |
| `AssignmentOne.java` | Main demonstration class |

## How to run

```text
javac -encoding UTF-8 -d out src\*.java
java -cp out AssignmentOne
```

## Checklist

- [x] Abstract base class with numeric ID, name, working days and controlled access
- [x] Four concrete clinician types, including one general practitioner
- [x] Common-type roster storing all subtypes; details and role summary printed
- [x] Visit class linking an independent Patient to any clinician subtype
- [x] Validation of incomplete or illegal bookings; double booking rejected
- [x] Filter by clinician ID and patient contact; whole list and subset sorting
- [x] Cancellation by ID plus slot, and by contact number; warnings on failure
- [x] Main demonstration covering normal and error cases
- [ ] WakaTime activity report
- [ ] Short demonstration video link added to the comments
