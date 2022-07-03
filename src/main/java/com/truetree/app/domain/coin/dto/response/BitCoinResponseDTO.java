package com.truetree.app.domain.coin.dto.response;

import com.truetree.app.domain.coin.entity.BtcUsdt;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BitCoinResponseDTO {
    private final Long id;

    private final LocalDateTime openTime;

    private final long open;
    private final long low;
    private final long high;
    private final long close;
    private final long volume;
    private final long quoteAv;
    private final long trades;
    private final long takerBuyVolume;
    private final long takerBuyBaseAssetVolume;
    private final String symbol;

    public BitCoinResponseDTO(BtcUsdt btcUsdt) {
        this.id = btcUsdt.getId();
        this.openTime = btcUsdt.getOpenTime();
        this.open = btcUsdt.getOpen();
        this.high = btcUsdt.getHigh();
        this.low = btcUsdt.getLog();
        this.close = btcUsdt.getClose();
        this.volume = btcUsdt.getVolume();
        this.quoteAv = btcUsdt.getQuoteAv();
        this.trades = btcUsdt.getTrades();
        this.takerBuyVolume = btcUsdt.getTakerBuyVolume();
        this.takerBuyBaseAssetVolume = btcUsdt.getTakerBuyBaseAssetVolume();
        this.symbol = btcUsdt.getSymbol();
    }
}
