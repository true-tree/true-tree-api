package com.truetree.app.domain.coin.service;

import com.truetree.app.domain.coin.dto.response.BitCoinResponseDTO;
import com.truetree.app.domain.coin.entity.BtcUsdt;
import com.truetree.app.domain.coin.repository.BitCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BitCoinService {
    private final int ELEMENT_COUNT = 30;
    private final BitCoinRepository bitCoinRepository;

    @Transactional(readOnly = true)
    public List<BitCoinResponseDTO> getBitCoinData(Integer level) {
        List<BtcUsdt> btcUsdtList = bitCoinRepository.findAllByIdBetween(0,300);

        level -= 1;

        List<BitCoinResponseDTO> bitCoinResponseDTOList = btcUsdtList.subList(level * ELEMENT_COUNT, (level + 1) * ELEMENT_COUNT)
                .stream()
                .map(BitCoinResponseDTO::new)
                .collect(Collectors.toList());
        return bitCoinResponseDTOList;
    }

    @Transactional(readOnly = true)
    public long getAmount(){
        return bitCoinRepository.count();
    }


}
