package com.facebook.errorreporting.appstate.blackbox;

import java.util.Map;

public interface BlackBoxRecorderControl {
    void awaitForBlackBoxTraceCompletion(Object obj);

    Object captureBlackBoxTrace(Map<String, String> map, int i);
}
