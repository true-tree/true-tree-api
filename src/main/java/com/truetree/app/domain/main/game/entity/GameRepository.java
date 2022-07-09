package com.truetree.app.domain.main.game.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game,Long> {

    @Query("SELECT COUNT (g.id) >0 " +
            "FROM Game g " +
            "WHERE g.member.id = :memberId")
    boolean existByMemberId(Long memberId);

}
