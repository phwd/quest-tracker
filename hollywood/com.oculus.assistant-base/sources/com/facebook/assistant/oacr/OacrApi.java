package com.facebook.assistant.oacr;

import X.KJ;
import com.facebook.assistant.oacr.utils.StreamHandler;
import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;
import java.util.List;

public class OacrApi {
    public final HybridData mHybridData;

    public native StreamHandler executeTranscriptionRequest(String str, int i, boolean z, boolean z2, boolean z3, String str2, boolean z4, String str3, int i2);

    public native StreamHandler executeVoiceRequest(String str, int i, String str2, boolean z, boolean z2, boolean z3, int i2, String str3);

    public native void notifyCallingStateChange(boolean z, boolean z2, long j, String str, List list, int i, int i2, long j2);

    public native void onEvent(String str, ByteBuffer byteBuffer, String str2, boolean z, boolean z2);

    public native void onNetworkStatusChange(int i);

    public native void subscribeVoiceInteraction(OacrVoiceInteractionListener oacrVoiceInteractionListener);

    static {
        KJ.A05("oacr_api_jni", 0);
    }

    public OacrApi(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
