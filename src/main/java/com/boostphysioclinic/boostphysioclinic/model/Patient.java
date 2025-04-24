package com.boostphysioclinic.boostphysioclinic.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends Member{

    private List<Appointment> appointments = new ArrayList<>();

    public Patient(String id, String fullName, String address, String phone) {
        super(id, fullName, address, phone);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }



}
