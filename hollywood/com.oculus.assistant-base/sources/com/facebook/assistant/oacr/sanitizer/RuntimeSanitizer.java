package com.facebook.assistant.oacr.sanitizer;

import X.BO;
import X.KJ;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;
import java.util.List;

public final class RuntimeSanitizer extends RuntimeSanitizerHolder {
    public static final BO Companion = new BO();
    public static RuntimeSanitizer instance = new RuntimeSanitizer();

    private final native HybridData initHybrid();

    public final native void addMessageContent(List list, int i, ByteBuffer byteBuffer);

    public final native void addSupplementalContacts(ByteBuffer byteBuffer);

    public final native String sanitizeEventJson(String str, long j, String str2);

    public final native String sanitizeString(String str);

    public final native void updateContacts(ByteBuffer byteBuffer);

    public final native void updateStringReplacementRuleConfigs(List list);

    static {
        KJ.A05("oacr_runtime_sanitizer_jni", 0);
    }

    public RuntimeSanitizer() {
        this.mHybridData = initHybrid();
    }
}
