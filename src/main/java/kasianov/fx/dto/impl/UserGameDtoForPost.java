package kasianov.fx.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGameDtoForPost implements Dto {
    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("hero_id")
    private Integer heroId;

    private Result result;
}
