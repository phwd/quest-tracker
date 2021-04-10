package com.oculus.http.core.common;

import X.AnonymousClass006;

public final class Authorization {
    public static final String HEADER = "Authorization";

    public static String generate(String str) {
        return AnonymousClass006.A07("Bearer ", str);
    }
}
