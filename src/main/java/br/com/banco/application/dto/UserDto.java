package br.com.banco.application.dto;

import javax.persistence.Column;
import java.math.BigDecimal;

public class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String cpf;
    public String email;
    public BigDecimal income;
    public String password;

    public UserDto(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
