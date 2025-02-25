package com.buildingblocks.shared.application.combat.domain.enemy.events;

import com.buildingblocks.shared.application.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class BeCured extends DomainEvent {
    private final String enemyId;
    private final Integer amountCured;

    public BeCured(String characterId, Integer amountCured) {
        super(EventsEnum.BE_CURED.name());
        this.enemyId = characterId;
        this.amountCured = amountCured;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public Integer getAmountCured() {
        return amountCured;
    }
}
