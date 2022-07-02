package com.truetree.app.domain.coin.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 *
 * 임시용 비코 엔티티
 *
 */

@Getter
@NoArgsConstructor
@Table(name = "btcusdt")
@Entity
public class BtcUsdt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime openTime;

    private long open;
    private long high;
    private long close;
    private long volume;

//    @Column(name = "quote_av")
    private long quoteAv;
    private long trades;

    private long takerBuyVolume;

    private long takerBuyBaseAssetVolume;
    private String symbol;

    @Builder
    public BtcUsdt(LocalDateTime openTime, long open, long high, long close, long volume, long quoteAv, long trades, long takerBuyVolume, long takerBuyBaseAssetVolume, String symbol) {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.close = close;
        this.volume = volume;
        this.quoteAv = quoteAv;
        this.trades = trades;
        this.takerBuyVolume = takerBuyVolume;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.symbol = symbol;
    }
}
