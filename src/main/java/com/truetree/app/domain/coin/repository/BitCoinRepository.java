package com.truetree.app.domain.coin.repository;

import com.truetree.app.domain.coin.entity.BitCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitCoinRepository extends JpaRepository<BitCoin, Long> {
    List<BitCoin> findAllByIdBetween(long start, long end);
}
