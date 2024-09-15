package com.mas.kinga.validation.gameElement;

import com.mas.kinga.models.GAME_TYPE;
import com.mas.kinga.models.Game;
import com.mas.kinga.models.GameElement;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GameElementValidator implements ConstraintValidator<ValidGameElement, GameElement> {
    @Override
    public boolean isValid(GameElement gameElement, ConstraintValidatorContext context) {
        Game game = gameElement.getGame();
        if(gameElement == null || game == null || !game.getGameType().contains(GAME_TYPE.BOARD_GAME)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Game element must be assigned to a boardGame.")
                    .addPropertyNode("game - gameElement composition")
                    .addConstraintViolation();
            return false;
        }
        if(game.getGameElements() != null && game.getGameElements().contains(gameElement)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Game element must be assigned to a boardGame that doesnt already have this element")
                    .addPropertyNode("game - gameElement composition2")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidGameElement constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
