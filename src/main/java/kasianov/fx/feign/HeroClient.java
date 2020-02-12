package kasianov.fx.feign;

import kasianov.fx.dto.impl.HeroDto;
import kasianov.fx.dto.impl.UserDtoViewAllUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "heroClient", url = "${application.host}")
public interface HeroClient {

    @GetMapping(value = "/heroes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<HeroDto> getAllHeroes(@RequestHeader("Authorization") String token);
}
