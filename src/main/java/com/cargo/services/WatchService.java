package com.cargo.services;

import com.cargo.entity.Watches;
import com.cargo.repositories.WatchRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WatchService {

    private WatchRepository watchRepository;

    public Double getValue(final Collection<Integer> ids) {
        Double result = 0d;
        List<Watches> watchesList = watchRepository.findByIdIn(ids);

        Map<Integer, Long> watchCounts = ids.stream()
                .collect(Collectors.groupingBy(foo -> foo.intValue(), Collectors.counting()));

        double calculatedPrice = ids.stream()
                .distinct()
                .map(watchId -> watchCounts.get(watchId) * getValueById(watchesList, watchId))
                .mapToDouble(watches -> watches.doubleValue())
                .sum();

        double noDiscount = watchesList.stream()
                .mapToDouble(watches -> watches.getPrice())
                .sum();

        result = calculatedPrice;

        return result;
    }

    private Double getValueById(List<Watches> watches, Integer id) {
        return watches.stream().filter(watch -> watch.getId() == id)
                .map(watch -> watch.getPrice()).findFirst().get();
    }


    private Boolean isDiscountForId(final Integer id, final List<Watches> watches) {
        Boolean result = false;
        long watchCount = watches.stream()
                .filter(watchesArg -> watchesArg.getId() == id).count();
        Watches checkedWatch = watches.get(id);
        if (!checkedWatch.getDiscount().trim().isEmpty()) {
            String[] array = checkedWatch.getDiscount().split("\\ ", -1);
        }

/*        String[] array = values.split("\\|", -1);
        checkedWatch.getDiscount().s*/

        return result;
    }
}
