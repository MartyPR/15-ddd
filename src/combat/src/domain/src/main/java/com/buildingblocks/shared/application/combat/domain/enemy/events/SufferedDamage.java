package com.buildingblocks.shared.application.combat.domain.enemy.events;

import com.buildingblocks.shared.application.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class SufferedDamage extends DomainEvent {
    private final String enemyId;
    private final Integer amountDamage;

    public SufferedDamage(String characterId, Integer amountDamage) {
        super(EventsEnum.DAMAGE_SUFFERED.name());
        this.enemyId = characterId;
        this.amountDamage = amountDamage;
    }

    public String getCharacterId() {
        return enemyId;
    }

    public Integer getAmountDamage() {
        return amountDamage;
    }
}
