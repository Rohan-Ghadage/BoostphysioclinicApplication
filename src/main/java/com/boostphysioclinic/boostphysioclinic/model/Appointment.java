package com.boostphysioclinic.boostphysioclinic.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Appointment {

    private Patient patient;
    private Treatment treatment;
    private AppointmentStatus status;
}
