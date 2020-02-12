package kasianov.fx.feign;

import kasianov.fx.dto.impl.UserDtoViewAllUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "userClient", url = "${application.host}")
public interface UserClient {

    @GetMapping(value = "/users/all_users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDtoViewAllUsers> getAllEnemyUsers(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/users/logged_user_id", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Integer getCurrentUserId(@RequestHeader("Authorization") String token);
}
