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
public class UserGameDto implements Dto {
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("hero_id")
    private Integer heroId;
    @JsonProperty("hero_name")
    private String heroName;
    private Result result;
}
