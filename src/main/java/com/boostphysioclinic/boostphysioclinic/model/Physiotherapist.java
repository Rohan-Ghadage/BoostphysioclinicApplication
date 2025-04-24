package com.boostphysioclinic.boostphysioclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Physiotherapist extends Member{

    private List<String> expertiseAreas = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();

    public Physiotherapist(String id, String fullName, String address, String phone) {
        super(id, fullName, address, phone);
    }

}
