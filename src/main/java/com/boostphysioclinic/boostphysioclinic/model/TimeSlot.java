package com.boostphysioclinic.boostphysioclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimeSlot {

    private LocalDateTime dateTime;
    private int durationMinutes;

    public boolean conflictsWith(TimeSlot other) {
        LocalDateTime end = this.dateTime.plusMinutes(durationMinutes);
        LocalDateTime otherEnd = other.dateTime.plusMinutes(other.durationMinutes);
        return this.dateTime.isBefore(otherEnd) && other.dateTime.isBefore(end);
    }
}
