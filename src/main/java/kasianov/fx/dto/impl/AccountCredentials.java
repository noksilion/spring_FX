package kasianov.fx.dto.impl;


import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCredentials implements Dto {
    private String email;
    private String password;
}