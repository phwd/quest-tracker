package com.facebook.assistant.oacr;

import X.KJ;
import com.facebook.jni.HybridData;

public class TtsApi {
    public final HybridData mHybridData;

    public native void executeTtsRequest(String str, boolean z, String str2);

    public native void onTtsVoiceSelected(String str, String str2);

    static {
        KJ.A05("oacr_api_jni", 0);
    }

    public TtsApi(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
