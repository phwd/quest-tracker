package com.facebook.gk.store;

import com.facebook.common.util.TriState;
import java.util.SortedMap;

public interface GkAccessorByName {
    boolean exists(String str);

    TriState get(String str);

    boolean get(String str, boolean z);

    TriState getActual(String str);

    SortedMap<String, String> getAllGatekeepers();

    boolean isInitialized(String str);

    boolean isOverridden(String str);
}
