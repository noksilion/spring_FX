package kasianov.fx.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto implements Dto {
    private Integer id;

    private String date;

    @JsonProperty("players")
    private List<UserGameDto> userGameList;
}
