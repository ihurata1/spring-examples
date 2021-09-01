package com.hurata.dto;

import com.hurata.entity.Address;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class UserDto{

    private Long id;

    private String name;

    private String surname;

    private List<String> addresses;
}
