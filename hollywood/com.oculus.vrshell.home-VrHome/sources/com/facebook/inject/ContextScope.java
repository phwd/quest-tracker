package com.facebook.inject;

import android.content.Context;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContextScope implements Scope {
    private static final Map<Context, Map<Integer, Object>> contextKeyedScopeCache = Collections.synchronizedMap(new HashMap());
}
