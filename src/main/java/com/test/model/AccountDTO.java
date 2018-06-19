package com.test.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.password.Password;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString(exclude = "passsword")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "account")
public class AccountDTO {

    @NotBlank
    @PartitionKey
    private int guestid;
    @NotBlank
    @Size(max = 254)
    private String firstName;

    @NotBlank
    @Size(max = 254)
    private String lastName;

    @NotBlank
    @Size(max = 254)
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    private String mobile;

    @Password
    private String passsword;

    private Date createTimestamp = new Date();

}
