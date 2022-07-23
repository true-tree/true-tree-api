package com.truetree.app.web;

import com.truetree.app.domain.coin.dto.response.BitCoinResponseDTO;
import com.truetree.app.domain.coin.service.BitCoinService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@Api(tags = "테스트용 코인 데이터")
@RequiredArgsConstructor
@RestController
public class TestController {

    private final BitCoinService bitCoinService;

    @Operation(summary = "테스트용 코인 데이터 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK")
    })
    @GetMapping("/get/coin/{count}")
    public List<BitCoinResponseDTO> getCoinData(@Parameter(description = "코인 데이터 시작 번호",required = true,example = "2")
                                                    @PathVariable("count") Integer range) {

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
