package kasianov.fx.feign;

import kasianov.fx.dto.impl.GameStatistic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "statisticClient", url = "${application.host}")
public interface StatisticClient {

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameStatistic getStatsVsUserOnHero(@RequestHeader("Authorization") String token,
                                             @RequestParam("userId") Integer userId,
                                             @RequestParam("heroId") Integer heroId);

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameStatistic getStatsOnHero(@RequestHeader("Authorization") String token,
                                       @RequestParam("heroId") Integer heroId);

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameStatistic getStatsVsUser(@RequestHeader("Authorization") String token,
                                       @RequestParam("userId") Integer userId);

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameStatistic getStatsGeneral(@RequestHeader("Authorization") String token);

}
