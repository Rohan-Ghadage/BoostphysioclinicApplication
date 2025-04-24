package com.boostphysioclinic.boostphysioclinic;


import com.boostphysioclinic.boostphysioclinic.model.*;
import com.boostphysioclinic.boostphysioclinic.service.ClinicManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClinicManagerTest {

    private ClinicManager manager;
    private Patient patient;
    private Physiotherapist physio;
    private Treatment treatment;

    @BeforeEach
    void setUp() {
        manager = new ClinicManager();
        patient = new Patient("P001", "Test Patient", "Test Address", "1234567890");
        physio = new Physiotherapist("PT001", "Test Physio", "Test Address", "0987654321");
        physio.getExpertiseAreas().add("Physiotherapy");

        TimeSlot slot = new TimeSlot(LocalDateTime.of(2025, 5, 1, 10, 0), 60);
        treatment = new Treatment("Massage", slot, physio);
        physio.getTreatments().add(treatment);

        manager.getPatients().add(patient);
        manager.getPhysiotherapists().add(physio);
    }

    @Test
    void testAddPatient() {
        Patient newPatient = new Patient("P002", "New Patient", "Somewhere", "0000000000");
        assertTrue(manager.addPatient(newPatient));
        assertEquals(2, manager.getPatients().size());
    }

    @Test
    void testBookAppointment() {
        boolean success = manager.bookAppointment(patient, treatment);
        assertTrue(success);
        assertEquals(1, manager.getAppointments().size());
        assertEquals(AppointmentStatus.BOOKED, manager.getAppointments().get(0).getStatus());
    }

    @Test
    void testTimeSlotConflict() {
        TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2025, 5, 1, 10, 0), 60);
        TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2025, 5, 1, 10, 30), 60); // overlaps
        assertTrue(slot1.conflictsWith(slot2));
    }

    @Test
    void testAddAppointmentToPatient() {
        Appointment appt = new Appointment(patient, treatment, AppointmentStatus.BOOKED);
        patient.addAppointment(appt);
        assertEquals(1, patient.getAppointments().size());
        assertEquals("Massage", patient.getAppointments().get(0).getTreatment().getName());
    }

    @Test
    void testSetAppointmentStatus() {
        Appointment appt = new Appointment(patient, treatment, AppointmentStatus.BOOKED);
        appt.setStatus(AppointmentStatus.ATTENDED);
        assertEquals(AppointmentStatus.ATTENDED, appt.getStatus());
    }
}