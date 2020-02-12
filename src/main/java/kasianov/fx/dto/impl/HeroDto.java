package kasianov.fx.dto.impl;

import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroDto implements Dto {
    private  Integer id;

    private  String name;

    private  Integer castleId;

    @Override
    public String toString() {
        return name;
    }
}
