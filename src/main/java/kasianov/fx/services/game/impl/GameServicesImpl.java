package kasianov.fx.services.game.impl;

import kasianov.fx.dto.impl.GameDto;
import kasianov.fx.dto.impl.GameDtoForPost;
import kasianov.fx.dto.impl.Result;
import kasianov.fx.dto.impl.UserGameDtoForPost;
import kasianov.fx.exceptions.BaseError;
import kasianov.fx.feign.GameClient;
import kasianov.fx.feign.UserClient;
import kasianov.fx.services.game.GameServices;
import kasianov.fx.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameServicesImpl implements GameServices {

    private GameClient gameClient;
    private Store tokenStore;
    private UserClient userClient;

    @Autowired
    public GameServicesImpl(GameClient gameClient, Store tokenStore, UserClient userClient) {
        this.gameClient = gameClient;
        this.tokenStore = tokenStore;
        this.userClient = userClient;
    }

    @Override
    public GameDto saveGame(Integer currentUserHeroId,
                            Result currentUserResult,
                            List<UserGameDtoForPost> enemyUserGames) {
        Integer currentUserId = userClient.getCurrentUserId(tokenStore.getToken());
        enemyUserGames.add(UserGameDtoForPost.builder()
                .heroId(currentUserHeroId)
                .userId(currentUserId)
                .result(currentUserResult)
                .build());
        GameDtoForPost gameDtoForPost = GameDtoForPost.builder()
                .userGameList(enemyUserGames)
                .build();
        return gameClient.createGame(tokenStore.getToken(), gameDtoForPost);
    }
}
