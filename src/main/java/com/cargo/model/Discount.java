package com.cargo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Discount {

    private long count;

    private String name;

    private double priceFor;

}
