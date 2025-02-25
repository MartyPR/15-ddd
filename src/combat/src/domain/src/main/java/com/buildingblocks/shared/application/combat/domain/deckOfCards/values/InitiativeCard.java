package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class InitiativeCard implements IValueObject {
    private final Integer value;

    private InitiativeCard(Integer value) {
        this.value = value;
    }

    public static InitiativeCard of(Integer value) {
        return new InitiativeCard(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }
}
