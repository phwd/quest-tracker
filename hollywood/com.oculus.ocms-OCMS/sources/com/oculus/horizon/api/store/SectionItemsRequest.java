package com.oculus.horizon.api.store;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.provider.OculusContent;
import java.util.Map;

public class SectionItemsRequest extends ApiRequest<Map<String, SectionItemsResponse>> {
    public final String categoryId;
    public final String controllerFilter;
    public final Integer count;
    public final String cursor;
    public final String genreFilter;
    public final String ordering;
    public final String priceFilter;

    public SectionItemsRequest(Integer num, String str, String str2) {
        this.count = num;
        this.cursor = str;
        this.categoryId = str2;
        this.ordering = null;
        this.genreFilter = null;
        this.priceFilter = null;
        this.controllerFilter = null;
    }

    public SectionItemsRequest(Integer num, String str, String str2, String str3, String str4, String str5, String str6) {
        this.count = num;
        this.cursor = str;
        this.categoryId = str2;
        this.ordering = str3;
        this.genreFilter = str4;
        this.priceFilter = str5;
        this.controllerFilter = str6;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder put = ImmutableMap.builder().put("count", this.count.toString());
        if (!Strings.isNullOrEmpty(this.cursor)) {
            put.put(OculusContent.Paging.CURSOR, this.cursor);
        }
        if (!Strings.isNullOrEmpty(this.categoryId)) {
            put.put("category", this.categoryId);
        }
        if (!Strings.isNullOrEmpty(this.ordering)) {
            put.put("ordering", this.ordering);
        }
        if (!Strings.isNullOrEmpty(this.genreFilter)) {
            put.put("genre", this.genreFilter);
        }
        if (!Strings.isNullOrEmpty(this.priceFilter)) {
            put.put("price", this.priceFilter);
        }
        if (!Strings.isNullOrEmpty(this.controllerFilter)) {
            put.put("controller", this.controllerFilter);
        }
        return put.build();
    }
}
