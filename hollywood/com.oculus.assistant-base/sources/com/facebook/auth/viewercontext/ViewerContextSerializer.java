package com.facebook.auth.viewercontext;

import X.C00162i;
import com.fasterxml.jackson.databind.JsonSerializer;

public class ViewerContextSerializer extends JsonSerializer {
    static {
        C00162i.A06.putIfAbsent(ViewerContext.class, new ViewerContextSerializer());
    }
}
