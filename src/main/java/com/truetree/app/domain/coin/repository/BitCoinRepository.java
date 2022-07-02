package com.truetree.app.domain.coin.repository;

import com.truetree.app.domain.coin.entity.BtcUsdt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitCoinRepository extends JpaRepository<BtcUsdt, Long> {
    List<BtcUsdt> findAllByIdBetween(long start, long end);
}
