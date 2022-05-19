package com.group5.activitytrackergroup5task.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String cpassword;
}
