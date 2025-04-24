package com.boostphysioclinic.boostphysioclinic.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Treatment {

    private String name;
    private TimeSlot timeSlot;
    private Physiotherapist physiotherapist;

    public boolean isAvailable(List<Appointment> appointments) {
        return appointments.stream()
                .noneMatch(a -> a.getTreatment().equals(this));
    }
}
