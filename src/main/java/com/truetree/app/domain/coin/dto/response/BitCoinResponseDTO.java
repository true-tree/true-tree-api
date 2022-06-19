package com.truetree.app.domain.coin.dto.response;

import com.truetree.app.domain.coin.entity.BitCoin;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BitCoinResponseDTO {
    private final Long id;

    private final LocalDateTime openTime;

    private final long open;
    private final long high;
    private final long close;
    private final long volume;
    private final long quoteAv;
    private final long trades;
    private final long takerBuyVolume;
    private final long takerBuyBaseAssetVolume;
    private final String symbol;

    public BitCoinResponseDTO(BitCoin bitCoin) {
        this.id = bitCoin.getId();
        this.openTime = bitCoin.getOpenTime();
        this.open = bitCoin.getOpen();
        this.high = bitCoin.getHigh();
        this.close = bitCoin.getClose();
        this.volume = bitCoin.getVolume();
        this.quoteAv = bitCoin.getQuoteAv();
        this.trades = bitCoin.getTrades();
        this.takerBuyVolume = bitCoin.getTakerBuyVolume();
        this.takerBuyBaseAssetVolume = bitCoin.getTakerBuyBaseAssetVolume();
        this.symbol = bitCoin.getSymbol();
    }
}
