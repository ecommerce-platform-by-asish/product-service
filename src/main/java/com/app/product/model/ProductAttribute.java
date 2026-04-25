package com.app.product.model;

import java.io.Serializable;

public record ProductAttribute(String name, String value, String unit) implements Serializable {}
