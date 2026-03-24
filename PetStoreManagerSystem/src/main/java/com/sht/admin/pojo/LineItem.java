package com.sht.admin.pojo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class LineItem implements Serializable {
    private int orderId;
    private int lineNumber;
    private String itemId;
    private int quantity;
    private BigDecimal unitPrice;
    private Item item;

    public BigDecimal getTotal() {
        if (unitPrice == null) return BigDecimal.ZERO;
        return unitPrice.multiply(new BigDecimal(quantity));
    }
}
