package com.fasterxml.jackson.databind.ser;

public abstract class FilterProvider {
    public abstract BeanPropertyFilter findFilter(Object obj);
}
