package com.facebook.common.componentmap;

import javax.annotation.Nullable;

public final class ComponentMap {
    @Nullable
    public static String getNameForType(int i) {
        if (i != 0) {
            return null;
        }
        return "com.facebook.crudolib.urimap.runtime.DummyComponentMapActivity";
    }

    private ComponentMap() {
    }
}
