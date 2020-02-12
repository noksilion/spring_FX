package kasianov.fx.dto.impl;


import com.fasterxml.jackson.annotation.JsonProperty;
import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.validation.annotation.Validated;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoForPost implements Dto {

    private  String userName;

    private  String email;

    private  String password;
}
