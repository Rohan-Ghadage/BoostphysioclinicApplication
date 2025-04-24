package com.boostphysioclinic.boostphysioclinic.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Member {

    private String id;
    private String fullName;
    private String address;
    private String phone;
}
