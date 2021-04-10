package com.oculus.horizon.api.store;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.Map;

public class SectionItemsRequest extends ApiRequest<Map<String, SectionItemsResponse>> {
    public final String categoryId;
    public final String controllerFilter;
    public final Integer count;
    public final String cursor;
    public final String genreFilter;
    public final String ordering;
    public final String priceFilter;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("count", this.count.toString());
        String str = this.cursor;
        if (!Strings.isNullOrEmpty(str)) {
            A01.put("cursor", str);
        }
        String str2 = this.categoryId;
        if (!Strings.isNullOrEmpty(str2)) {
            A01.put("category", str2);
        }
        String str3 = this.ordering;
        if (!Strings.isNullOrEmpty(str3)) {
            A01.put("ordering", str3);
        }
        String str4 = this.genreFilter;
        if (!Strings.isNullOrEmpty(str4)) {
            A01.put("genre", str4);
        }
        String str5 = this.priceFilter;
        if (!Strings.isNullOrEmpty(str5)) {
            A01.put("price", str5);
        }
        String str6 = this.controllerFilter;
        if (!Strings.isNullOrEmpty(str6)) {
            A01.put("controller", str6);
        }
        return A01.build();
    }

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
}
