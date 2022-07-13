package com.truetree.app.web;

import com.truetree.app.domain.coin.dto.response.BitCoinResponseDTO;
import com.truetree.app.domain.coin.service.BitCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @GetMapping("/cpu/test")
    public List<Integer> list() {
        List<Integer> list = new ArrayList<>();
        int size = 100000;
        Random random = new Random();
        for (int i=0; i<size; i++) {
            list.add(i);
        }
        int filterInt = random.nextInt(size);
        return list.stream()
                .filter(i -> filterInt == i)
                .collect(Collectors.toList());
    }

}
