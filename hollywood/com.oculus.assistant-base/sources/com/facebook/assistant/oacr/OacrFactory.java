package com.facebook.assistant.oacr;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.facebook.assistant.oacr.Oacr;
import com.facebook.assistant.oacr.sanitizer.RuntimeSanitizerHolder;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.proxygen.EventBase;
import com.facebook.proxygen.HTTPClient;
import com.facebook.xanalytics.XAnalyticsHolder;
import java.nio.ByteBuffer;

public class OacrFactory {
    public static Oacr createOacr(HTTPClient hTTPClient, AssistantLogger assistantLogger, EventBase eventBase, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, AndroidAsyncExecutorFactory androidAsyncExecutorFactory2, ByteBuffer byteBuffer, Oacr.ReadinessStatusCallback readinessStatusCallback, Oacr.ResourceStatusCallback resourceStatusCallback, ByteBuffer byteBuffer2, Oacr.DeviceContextProvider deviceContextProvider, Oacr.AudioPlayer audioPlayer, Oacr.TtsVoiceConfigurationCallback ttsVoiceConfigurationCallback, Oacr.MobileConfigProvider mobileConfigProvider, XAnalyticsHolder xAnalyticsHolder, RuntimeSanitizerHolder runtimeSanitizerHolder, boolean z) {
        return new Oacr(hTTPClient, assistantLogger, eventBase, androidAsyncExecutorFactory, androidAsyncExecutorFactory2, byteBuffer, readinessStatusCallback, resourceStatusCallback, byteBuffer2, deviceContextProvider, audioPlayer, ttsVoiceConfigurationCallback, mobileConfigProvider, xAnalyticsHolder, runtimeSanitizerHolder, z);
    }
}
