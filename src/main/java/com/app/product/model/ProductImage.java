package com.app.product.model;

import java.io.Serializable;
import java.util.List;

public record ProductImage(String thumbnail, String primary, List<String> gallery)
    implements Serializable {}
