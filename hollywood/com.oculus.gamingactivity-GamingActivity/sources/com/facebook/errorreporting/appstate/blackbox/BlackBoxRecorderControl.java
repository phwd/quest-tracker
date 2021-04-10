package com.facebook.errorreporting.appstate.blackbox;

import java.util.Map;
import javax.annotation.Nullable;

public interface BlackBoxRecorderControl {
    public static final int JAVA_CRASH = 1;

    void awaitForBlackBoxTraceCompletion(@Nullable Object obj);

    @Nullable
    Object captureBlackBoxTrace(Map<String, String> map, int i);
}
