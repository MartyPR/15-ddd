package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Result implements IValueObject {
    private final String value;

    private Result(String value) {
        this.value = value;
    }

    public static Result of(String value) {
        Result result = new Result(value);
        result.validate();
        return result;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }
}
