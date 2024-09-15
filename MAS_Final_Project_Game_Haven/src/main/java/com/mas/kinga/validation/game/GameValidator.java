package com.mas.kinga.validation.game;

import com.mas.kinga.models.GAME_TYPE;
import com.mas.kinga.models.Game;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;

import static com.mas.kinga.models.GAME_TYPE.BOARD_GAME;
import static com.mas.kinga.models.GAME_TYPE.VIDEO;

public class GameValidator implements ConstraintValidator<ValidGame, Game> {
    @Override
    public boolean isValid(Game game, ConstraintValidatorContext context) {
        EnumSet<GAME_TYPE> validGameTypes = EnumSet.of(BOARD_GAME, VIDEO);
        Set<GAME_TYPE> gameType = game.getGameType();

        //Game can only have one of two types: BOARD_GAME or VIDEO
        if (game == null || game.getGameType() == null || !validGameTypes.containsAll(gameType))
            return false; //TODO do sprawdzenia

        //BOARD_GAME
        if (gameType.contains(BOARD_GAME)) {
            if (!gameType.contains(VIDEO) && (game.getLicenseKeys() != null && game.getPlatforms() != null)) { //obecność pól możliwa tylko dla VIDEO
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("BOARD_GAME should not have license keys nor platforms")
                        .addPropertyNode("licenseKeys")
                        .addConstraintViolation();
                return false;
            } else if (game.getGameElements() != null) {
                if (!game.getGameElements().stream().filter(ge -> ge == null || ge.getGame() != game).toList().isEmpty()) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("BOARD_GAME elements must have assigned this game and the elements cannot be null. Game can have zero elements")
                            .addPropertyNode("gameElements")
                            .addConstraintViolation();
                    return false;
                }
                // nie potrzebna walidacja, na dodanie tego samego elementu do gry, bo przechowywane są jako set
            }

        }

        //VIDEO
        if (gameType.contains(VIDEO)) {
            if (gameType.contains(BOARD_GAME) && game.getGameElements() != null) { //obecność pola możliwa tylko dla BOARD_GAME
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("VIDEO_GAME should not have game elements.")
                        .addPropertyNode("gameElements")
                        .addConstraintViolation();
                return false;
            } else if (game.getLicenseKeys() == null || game.getLicenseKeys().isEmpty() || game.getPlatforms() == null || game.getPlatforms().isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("VIDEO_GAME should have licenseKeys and available Paltforms.")
                        .addPropertyNode("licenseKeys + available Paltforms")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(ValidGame constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
