package com.google.common.base;

import java.util.AbstractList;

public class Joiner$3 extends AbstractList<Object> {
    public final /* synthetic */ Object val$first;
    public final /* synthetic */ Object[] val$rest;
    public final /* synthetic */ Object val$second;

    public Joiner$3(Object[] objArr, Object obj, Object obj2) {
        this.val$rest = objArr;
        this.val$first = obj;
        this.val$second = obj2;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        if (i == 0) {
            return this.val$first;
        }
        if (i != 1) {
            return this.val$rest[i - 2];
        }
        return this.val$second;
    }

    public int size() {
        return this.val$rest.length + 2;
    }
}
