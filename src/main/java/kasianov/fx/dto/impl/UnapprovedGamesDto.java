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
public class UnapprovedGamesDto implements Dto {
    @JsonProperty("confirming_user_id")
    private Integer confirmingUserId;

    @JsonProperty("unapproved_games")
    private List<GameDto> gameDtos;
}