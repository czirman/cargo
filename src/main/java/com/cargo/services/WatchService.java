package com.cargo.services;

import com.cargo.entity.Watches;
import com.cargo.repositories.WatchRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WatchService {

    private WatchRepository watchRepository;

    public Double getValue(final List<Integer> ids) {
        Double result = 0d;
        List<Watches> watchesList = watchRepository.findByIdIn(ids);

        Map<Integer, Long> watchCounts = ids.stream()
                .collect(Collectors.groupingBy(foo -> foo.intValue(), Collectors.counting()));

        double calculatedPrice = ids.stream()
                .distinct()
                //.map(watchId -> watchCounts.get(watchId) * getWatchById(watchesList, watchId))
                .mapToDouble(watches -> watches.doubleValue())
                .sum();

        result = calculatedPrice;

        return result;
    }

    private Watches getWatchById(List<Watches> watches, Integer id) {
        return watches.stream().filter(watch -> watch.getId() == id)
                .findFirst().get();
    }

    private void calculatedDiscountedWatches(final List<Integer> ids, final List<Watches> watches) {
        for (Integer id : ids) {
            Watches watch = getWatchById(watches, id);
            /*  if (isDiscountForId()){
            }*/
        }
    }

    private Boolean isDiscountForId(final Map<Integer, Long> watchCounts, final Watches checkedWatch, final Integer id) {

        Boolean result = false;
        Long watchCount = watchCounts.get(id);

        if (!checkedWatch.getDiscount().trim().isEmpty()) {

            String[] discountInfo = checkedWatch.getDiscount().split("\\ ", -1);
            return watchCount >= Long.valueOf(discountInfo[0]);
        }

        return result;
    }
}
