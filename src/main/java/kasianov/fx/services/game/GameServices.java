package kasianov.fx.services.game;

import kasianov.fx.dto.impl.GameDto;
import kasianov.fx.dto.impl.Result;
import kasianov.fx.dto.impl.UserGameDtoForPost;
import kasianov.fx.exceptions.BaseError;

import java.util.List;

public interface GameServices {
    GameDto saveGame(Integer currentUserHeroId,
                     Result currentUserResult,
                     List<UserGameDtoForPost> enemyUserGames) ;
}
