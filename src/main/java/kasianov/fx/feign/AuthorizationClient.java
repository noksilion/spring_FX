package kasianov.fx.feign;

import kasianov.fx.dto.impl.AccountCredentials;
import kasianov.fx.dto.impl.TokenDto;
import kasianov.fx.dto.impl.UserDtoForPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authClient", url = "http://185.203.243.38:8081")
public interface AuthorizationClient {

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenDto login (AccountCredentials accountCredentials);

    @PostMapping(value = "/singup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenDto signup (UserDtoForPost accountCredentials);
}
