package com.ebcr.dtos;

import com.ebcr.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateOrUpdateUserDTO {
    @NotNull
  private String firstName;
    @NotNull
  private String lastName;
    @NotNull
  private String userName;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotNull
    @Pattern(regexp = "[0-9]{9,12}",  message = "Your phone is not a valid tel we expect 2507***, or 07*** or 7***")
  private String phoneNumber;
    @NotNull
  private String email;
    @NotNull
    @Pattern(regexp = "[0-9]{16}" , message = "Your national id is not full we expected 16 characters of 0-9 integers")
  private String nationalId;
    @NotNull
  private Gender gender;
    @NotNull
  private String password;


}
