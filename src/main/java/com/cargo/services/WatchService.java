

package com.cargo.services;

import com.cargo.entity.Watches;
import com.cargo.model.Discount;
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

        /*TODO  refactor foo ant other method */
        Map<Integer, Long> watchCounts = ids.stream()
                .collect(Collectors.groupingBy(foo -> foo.intValue(), Collectors.counting()));

        Double calculatedDiscount = calculatedDiscount(ids, watchesList, watchCounts);

        for (Integer id : ids) {
            Watches watch = getWatchById(watchesList, id);
            if (isDiscountForId(watchCounts, watch, id)) {

            }
        }

        double calculatedPrice = ids.stream()
                .distinct()
                .map(watchId -> watchCounts.get(watchId) * getWatchById(watchesList, watchId).getPrice())
                .mapToDouble(watches -> watches.doubleValue())
                .sum();


        return calculatedPrice+calculatedDiscount;
    }

    private Double calculatedDiscount(final List<Integer> ids, final List<Watches> watches, final Map<Integer, Long> watchCounts) {
        Double sumDiscount = 0d;

        for (Map.Entry<Integer, Long> entry : watchCounts.entrySet()) {
            Watches checkedWatch = getWatchById(watches, entry.getKey());
            if (isDiscountForId(watchCounts, checkedWatch, entry.getKey())) {
                Discount discount = extractDiscount(checkedWatch, entry.getKey());
                removeAndCalculate(discount, ids, entry.getValue(), entry.getKey());
                sumDiscount += calculateMultiplyDiscount(discount, entry.getValue());
            }
        }
        return sumDiscount;
    }

    private void removeAndCalculate(final Discount discount, List<Integer> ids, final long count, final int idWatch) {
        long multiplyDiscount = count / discount.getCount();
        long nrToRemove = discount.getCount() * multiplyDiscount;
        for (long i = 1; i <= nrToRemove; i++) {
            Integer idToRemove = getIndexById(ids, idWatch);
            ids.remove(idToRemove);
        }
    }

    private Double calculateMultiplyDiscount(final Discount discount, final long count) {
        Double result = 0d;
        long multiplyDiscount = count / discount.getCount();
        return multiplyDiscount * discount.getPriceFor();
    }

    private Discount extractDiscount(final Watches extractedWatch, final Integer id) {
        String[] discountInfo = extractedWatch.getDiscount().split("\\ ", -1);
        return Discount.builder()
                .count(Integer.valueOf(discountInfo[0]))
                .name(extractedWatch.getName())
                .priceFor(Double.valueOf(discountInfo[2]))
                .build();
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

    private Watches getWatchById(final List<Watches> watches, final Integer id) {
        return watches.stream().filter(watch -> watch.getId() == id)
                .findFirst().get();
    }

    private Integer getIndexById(final List<Integer> ids, final Integer id) {
        return ids.stream().filter(idStream -> idStream == id)
                .findFirst().get();

    }

}