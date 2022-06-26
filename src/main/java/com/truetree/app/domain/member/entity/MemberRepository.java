package com.truetree.app.domain.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select distinct m from Member m join fetch m.memberAuthorityList r where m.id = :id")
    Optional<Member> findByMemberLoginData(@Param("id") Long memberId);

    Optional<Member> findBySnsId(String snsId);

}
