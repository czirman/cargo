package com.cargo.services;

import com.cargo.repositories.WatchRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class WatchService {

    private WatchRepository watchRepository;

    public Integer getValue(Collection<Integer> ids){
        watchRepository.findByIdIn(ids);
        return 1;
    }
}
