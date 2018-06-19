package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Setter
@Getter
public class AccountResponseDTO {
    private int guestid;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
}
