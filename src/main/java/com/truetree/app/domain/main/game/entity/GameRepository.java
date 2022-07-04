package com.truetree.app.domain.main.game.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Long> {

    @Query(value = "select distinct g from Game g join fetch g.memberId where g.memberId.id = :memberId")
    Optional<Game> findByMemberId_Id(Long memberId);

}
