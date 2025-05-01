package com.example.demo.adapterts.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSummaryDTO {

    private Long id;
    private String name;
    private String email;

}
