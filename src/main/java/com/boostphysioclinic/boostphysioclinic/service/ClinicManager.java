package com.boostphysioclinic.boostphysioclinic.service;

import com.boostphysioclinic.boostphysioclinic.model.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class ClinicManager {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Physiotherapist> physiotherapists = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    // Add a patient
    public boolean addPatient(Patient patient) {
        if (getPatientById(patient.getId()) != null) return false; // for Unique ID
        patients.add(patient);
        return true;
    }

    // Remove a patient
    public boolean removePatient(String patientId) {
        Patient p = getPatientById(patientId);
        if (p == null) return false;
        patients.remove(p);
        appointments.removeIf(a -> a.getPatient().equals(p)); // cleanup appointments
        return true;
    }

    // Book an appointment
    public boolean bookAppointment(Patient patient, Treatment treatment) {
        // prevent duplicate booking or time conflict
        for (Appointment a : appointments) {
            if (a.getPatient().equals(patient)) {
                if (a.getTreatment().equals(treatment)) return false; // duplicate
                if (a.getTreatment().getTimeSlot().conflictsWith(treatment.getTimeSlot())) return false; // conflict
            }
        }
        Appointment appointment = new Appointment(patient, treatment, AppointmentStatus.BOOKED);
        appointments.add(appointment);
        patient.addAppointment(appointment);
        return true;
    }

    // Cancel an appointment
    public boolean cancelAppointment(Patient patient, Treatment treatment) {
        Optional<Appointment> match = appointments.stream()
                .filter(a -> a.getPatient().equals(patient) && a.getTreatment().equals(treatment) && a.getStatus() == AppointmentStatus.BOOKED)
                .findFirst();

        match.ifPresent(a -> a.setStatus(AppointmentStatus.CANCELLED));
        return match.isPresent();
    }

    // Mark as attended
    public boolean attendAppointment(Patient patient, Treatment treatment) {
        Optional<Appointment> match = appointments.stream()
                .filter(a -> a.getPatient().equals(patient) && a.getTreatment().equals(treatment) && a.getStatus() == AppointmentStatus.BOOKED)
                .findFirst();

        match.ifPresent(a -> a.setStatus(AppointmentStatus.ATTENDED));
        return match.isPresent();
    }

    // Generate Report
    public void generateReport() {
        System.out.println("=== Boost Physio Clinic Report ===");
        for (Physiotherapist pt : physiotherapists) {
            System.out.println("Physiotherapist: " + pt.getFullName());
            List<Appointment> ptAppointments = appointments.stream()
                    .filter(a -> a.getTreatment().getPhysiotherapist().equals(pt))
                    .toList();
            for (Appointment a : ptAppointments) {
                System.out.printf("  Treatment: %-25s Patient: %-15s Time: %-20s Status: %s\n",
                        a.getTreatment().getName(),
                        a.getPatient().getFullName(),
                        a.getTreatment().getTimeSlot().getDateTime(),
                        a.getStatus());
            }
        }

        // Sort physiotherapists by attended appointments
        System.out.println("\n=== Physiotherapists Sorted by Attended Appointments ===");
        physiotherapists.stream()
                .sorted((a, b) -> {
                    long countA = countAttendedAppointments(a);
                    long countB = countAttendedAppointments(b);
                    return Long.compare(countB, countA); // Descending
                })
                .forEach(pt -> {
                    long count = countAttendedAppointments(pt);
                    System.out.println(pt.getFullName() + " - " + count + " attended appointments");
                });
    }

    private long countAttendedAppointments(Physiotherapist pt) {
        return appointments.stream()
                .filter(a -> a.getTreatment().getPhysiotherapist().equals(pt) && a.getStatus() == AppointmentStatus.ATTENDED)
                .count();
    }

    // Helpers
    public Patient getPatientById(String id) {
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Physiotherapist getPhysiotherapistByName(String name) {
        return physiotherapists.stream().filter(p -> p.getFullName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Physiotherapist> searchByExpertise(String area) {
        return physiotherapists.stream()
                .filter(p -> p.getExpertiseAreas().contains(area))
                .collect(Collectors.toList());
    }

    public void initializeData() {
        // Sample Physiotherapists
        Physiotherapist helen = new Physiotherapist("PT001", "Helen Smith", "123 Clinic Rd", "9876543210");
        helen.getExpertiseAreas().addAll(List.of("Physiotherapy", "Rehabilitation"));

        Physiotherapist john = new Physiotherapist("PT002", "John Doe", "456 Clinic Rd", "8765432109");
        john.getExpertiseAreas().addAll(List.of("Osteopathy", "Massage"));

        Physiotherapist maya = new Physiotherapist("PT003", "Maya Patel", "789 Clinic Rd", "7654321098");
        maya.getExpertiseAreas().addAll(List.of("Pool Rehabilitation", "Physiotherapy"));

        physiotherapists.addAll(List.of(helen, john, maya));

        // Treatments for Helen
        helen.getTreatments().add(new Treatment("Neural Mobilisation", new TimeSlot(LocalDateTime.of(2025, 5, 1, 10, 0), 60), helen));
        helen.getTreatments().add(new Treatment("Massage", new TimeSlot(LocalDateTime.of(2025, 5, 3, 14, 0), 60), helen));

        // Treatments for John
        john.getTreatments().add(new Treatment("Acupuncture", new TimeSlot(LocalDateTime.of(2025, 5, 2, 9, 0), 60), john));
        john.getTreatments().add(new Treatment("Massage", new TimeSlot(LocalDateTime.of(2025, 5, 3, 10, 0), 60), john));

        // Treatments for Maya
        maya.getTreatments().add(new Treatment("Pool Rehabilitation", new TimeSlot(LocalDateTime.of(2025, 5, 4, 11, 0), 60), maya));
        maya.getTreatments().add(new Treatment("Mobilisation of Spine", new TimeSlot(LocalDateTime.of(2025, 5, 4, 13, 0), 60), maya));

        // Sample Patients
        for (int i = 1; i <= 10; i++) {
            patients.add(new Patient("P00" + i, "Patient " + i, "Area " + i, "90000000" + i));
        }
    }

}
