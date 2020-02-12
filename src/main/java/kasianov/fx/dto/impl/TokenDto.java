package kasianov.fx.dto.impl;

import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto implements Serializable, Dto {
    private String token;
}
