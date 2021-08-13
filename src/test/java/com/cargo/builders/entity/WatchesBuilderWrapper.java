package com.cargo.builders.entity;

import com.cargo.entity.Watches;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WatchesBuilderWrapper {

    public static Watches withDefaults() {
        return Watches.builder()
            .id(1)
            .name("Rolex")
            .price(100d)
            .discount("3 for 200")
            .build();
    }

    public static List<Watches> withDiscountList() {
        return Arrays.asList(
            withDefaults(),
            withDefaults()
                .toBuilder()
                .id(2)
                .name("Michael Kors")
                .price(80d)
                .discount("2 for 120")
                .build()
        );
    }
    public static List<Watches> withNoDiscountList() {
        return Arrays.asList(
            withDefaults()
                .toBuilder()
                .id(3)
                .name("Swatch")
                .price(50d)
                .discount("")
                .build(),
            withDefaults()
                .toBuilder()
                .id(4)
                .name("Casio")
                .discount("")
                .price(30d)
                .build()
        );
    }

}
