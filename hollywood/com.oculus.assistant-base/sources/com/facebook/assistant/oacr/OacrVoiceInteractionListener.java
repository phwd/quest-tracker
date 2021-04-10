package com.facebook.assistant.oacr;

import java.nio.ByteBuffer;

public interface OacrVoiceInteractionListener {
    void onAction(String str, ByteBuffer byteBuffer, String str2, boolean z);

    void onError(String str, String str2);

    void onFinalTranscription(ByteBuffer byteBuffer, String str);

    void onInteractionComplete(boolean z, String str, String str2);

    void onIntermediateTranscription(ByteBuffer byteBuffer, String str);

    void onKeywordVerificationComplete(boolean z, String str);

    void onOnDeviceTts(String str, String str2);
}
