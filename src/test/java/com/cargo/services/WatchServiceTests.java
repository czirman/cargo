package com.cargo.services;

import com.cargo.builders.entity.WatchesBuilderWrapper;
import com.cargo.repositories.WatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchServiceTests {

    @Mock
    private WatchRepository watchRepository;

    @InjectMocks
    WatchService watchService = new WatchService(watchRepository);

    @ParameterizedTest
    @MethodSource("provideListOfId")
    void calculateValue_whenNoDiscount_thenReturnPrice(List<Integer> ids) {
        //given
        when(watchRepository.findByIdIn(any())).thenReturn(WatchesBuilderWrapper.withNoDiscountList());

        //when
        Double actual = watchService.getValue(ids);

        //then
        assertEquals(110, actual);
    }

    @Test
    void calculateValue_whenDiscount_thenReturnPriceDiscount() {
        //given
        when(watchRepository.findByIdIn(any())).thenReturn(WatchesBuilderWrapper.withDiscountList());

        //when

        //Double actual = watchService.getValue(Arrays.asList(1, 2, 2));
        List<Integer> ids = Stream.of(1,2,2,2).collect(Collectors.toList());
        Double actual = watchService.getValue(ids);

        //then
        assertEquals(300, actual);
    }

    @Test
    void simple_test() {
        int count = 2;
        int getCount = 2;

        int a = 1%2;
        int b = 2%2;
        int c = 3%2;

        int d = 1/2;
        int e = 2/2;
        int f = 3/2;
    }


    private static Stream<Arguments> provideListOfId() {
        return Stream.of(
                Arguments.of(Arrays.asList(4, 3, 4))
            /*Arguments.of(Arrays.asList(1,2,3,4)),
            Arguments.of(Arrays.asList(4,4,4,4,1,2,3))*/
        );
    }

}