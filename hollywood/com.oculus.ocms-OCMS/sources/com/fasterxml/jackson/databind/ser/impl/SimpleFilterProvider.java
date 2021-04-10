package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleFilterProvider extends FilterProvider implements Serializable {
    private static final long serialVersionUID = -2825494703774121220L;
    protected boolean _cfgFailOnUnknownId;
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById;

    public SimpleFilterProvider() {
        this(new HashMap());
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
        this._cfgFailOnUnknownId = true;
        this._filtersById = map;
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }

    public BeanPropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean z) {
        this._cfgFailOnUnknownId = z;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return this._filtersById.remove(str);
    }

    @Override // com.fasterxml.jackson.databind.ser.FilterProvider
    public BeanPropertyFilter findFilter(Object obj) {
        BeanPropertyFilter beanPropertyFilter = this._filtersById.get(obj);
        if (beanPropertyFilter != null || (beanPropertyFilter = this._defaultFilter) != null || !this._cfgFailOnUnknownId) {
            return beanPropertyFilter;
        }
        throw new IllegalArgumentException("No filter configured with id '" + obj + "' (type " + obj.getClass().getName() + ")");
    }
}
