package com.truetree.app.web;

import com.truetree.app.domain.coin.dto.response.BitCoinResponseDTO;
import com.truetree.app.domain.coin.service.BitCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * Test Controller
 *
 * 추후 삭제
 *
 * @author pursue503
 */


@RequiredArgsConstructor
@RestController
public class TestController {

    private final BitCoinService bitCoinService;

    @GetMapping("/get/coin/{count}")
    public List<BitCoinResponseDTO> getCoinData(@PathVariable("count") Integer range) {
        return bitCoinService.getBitCoinData(range);
    }

}
