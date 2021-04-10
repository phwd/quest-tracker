package com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId {
    public final Object id;
    public Object item;

    public void bindItem(Object obj) throws IOException {
        if (this.item == null) {
            this.item = obj;
            return;
        }
        throw new IllegalStateException("Already had POJO for id (" + this.id.getClass().getName() + ") [" + this.id + "]");
    }
}
