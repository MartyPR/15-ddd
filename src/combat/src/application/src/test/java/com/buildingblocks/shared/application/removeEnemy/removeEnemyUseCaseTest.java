package com.buildingblocks.shared.application.removeEnemy;

import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyAdded;
import com.buildingblocks.shared.application.combat.removeEnemy.removeEnemyRequest;
import com.buildingblocks.shared.application.combat.removeEnemy.removeEnemyUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

class removeEnemyUseCaseTest {

    private IEventsRepository repository;
    private removeEnemyUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new removeEnemyUseCase(repository);
    }

    @Test
    void executeSuccess() {
        // Arrange
        String aggregateId = "combat123";
        String enemyId = "enemy567";

        EnemyAdded enemyAddedEvent = new EnemyAdded(enemyId,"Goblin", 10, 5);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(enemyAddedEvent));

        removeEnemyRequest request = new removeEnemyRequest(aggregateId, enemyId);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);

                    assertEquals(aggregateId, response.getCombatId());

                    assertNotNull(response.getEnemies());
                    assertEquals(0, response.getEnemies().size());
                })
                .verifyComplete();


        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}