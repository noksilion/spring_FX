package kasianov.fx.feign;

import kasianov.fx.dto.impl.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gameClient", url = "${application.host}")
public interface GameClient {

    @PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameDto createGame (@RequestHeader("Authorization") String token,
                        GameDtoForPost gameDtoForPost);

    @GetMapping(value = "/games/unapproved_games", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UnapprovedGamesDto getUnapprovedGames (@RequestHeader("Authorization") String token);

    @GetMapping(value = "/games/approve_game", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void approveGame (@RequestHeader("Authorization") String token,
                         @RequestParam("user_game_id") Integer gameId);


}
