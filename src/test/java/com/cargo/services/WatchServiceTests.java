package com.cargo.services;

import com.cargo.builders.entity.WatchesBuilderWrapper;
import com.cargo.repositories.WatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchServiceTests {

    /* @Mock
     private WatchRepository watchRepository;*/
    @Mock
    private WatchRepository watchRepository;

    /* @InjectMocks
     WatchService watchService = new WatchService(watchRepository);*/
    @InjectMocks
    WatchService watchService = new WatchService(watchRepository);

    @Test
    void calculateValue_whenNoDiscount_thenReturnPrice() {
        //given
        when(watchRepository.findByIdIn(any())).thenReturn(WatchesBuilderWrapper.withNoDiscountList());

        //when
        Double actual = watchService.getValue(Arrays.asList(4, 3, 4));

        //then
        assertEquals(110, actual);
    }

    @Test
    void calculateValue_whenDiscount_thenReturnPriceDiscount() {
        //given
        when(watchRepository.findByIdIn(any())).thenReturn(WatchesBuilderWrapper.withDefaultList());

        //when
        Double actual = watchService.getValue(Arrays.asList(1, 2));

        //then
        assertEquals(260, actual);
    }

}