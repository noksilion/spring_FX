package kasianov.fx.feign;

import kasianov.fx.dto.impl.AccountCredentials;
import kasianov.fx.dto.impl.GameDto;
import kasianov.fx.dto.impl.GameDtoForPost;
import kasianov.fx.dto.impl.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gameClient", url = "${application.host}")
public interface GameClient {

    @PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameDto createGame (@RequestHeader("Authorization") String token,
                        GameDtoForPost gameDtoForPost);
}
