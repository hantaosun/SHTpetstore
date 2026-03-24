package com.sht.admin.pojo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Product implements Serializable {
    private String productId;
    private String categoryId;
    private String name;
    private String description;
}
