package com.cargo.services;

import com.cargo.entity.Watches;
import com.cargo.repositories.WatchRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class WatchService {

    private WatchRepository watchRepository;

    public Integer getValue(Collection<Integer> ids){
        List<Watches> watchesList = watchRepository.findByIdIn(ids);
        return 1;
    }
}
