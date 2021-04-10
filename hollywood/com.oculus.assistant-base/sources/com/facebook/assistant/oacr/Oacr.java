package com.facebook.assistant.oacr;

import X.KJ;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.facebook.assistant.oacr.sanitizer.RuntimeSanitizerHolder;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.jni.HybridData;
import com.facebook.proxygen.EventBase;
import com.facebook.proxygen.HTTPClient;
import com.facebook.xanalytics.XAnalyticsHolder;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class Oacr {
    public HybridData mHybridData;

    public interface AudioPlayer {
        WritableByteChannel play(String str, String str2, String str3);
    }

    public interface DeviceContextProvider {
        ByteBuffer build();
    }

    public interface ReadinessStatusCallback {
        void onFailure(String str);

        void onOnDeviceReady();

        void onPassThroughReady();
    }

    public interface ResourceStatusCallback {
        void onDownloadFinish();

        void onDownloadProgress(double d);

        void onDownloadRequested(int i);

        void onDownloadStart();
    }

    public interface TtsVoiceConfigurationCallback {
        void onConfigUpdate(ByteBuffer byteBuffer, ByteBuffer byteBuffer2);
    }

    public static native HybridData initHybrid(HTTPClient hTTPClient, AssistantLogger assistantLogger, EventBase eventBase, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, AndroidAsyncExecutorFactory androidAsyncExecutorFactory2, ByteBuffer byteBuffer, ReadinessStatusCallback readinessStatusCallback, ResourceStatusCallback resourceStatusCallback, ByteBuffer byteBuffer2, DeviceContextProvider deviceContextProvider, AudioPlayer audioPlayer, TtsVoiceConfigurationCallback ttsVoiceConfigurationCallback, MobileConfigProvider mobileConfigProvider, XAnalyticsHolder xAnalyticsHolder, RuntimeSanitizerHolder runtimeSanitizerHolder, boolean z);

    private native void shutdownNative();

    public native AccountsApi requestAccountsApi();

    public native OacrApi requestOacrApi();

    public native ResourceApi requestResourceApi();

    public native TtsApi requestTtsApi();

    public synchronized void shutdown() {
        shutdownNative();
    }

    public abstract class MobileConfigProvider {
        public abstract void logExposure(OacrMCName oacrMCName);

        public void logExposureForNative(int i) {
            logExposure(OacrMCName.values()[i]);
        }
    }

    static {
        KJ.A05("oacr_api_jni", 0);
    }

    public Oacr(HTTPClient hTTPClient, AssistantLogger assistantLogger, EventBase eventBase, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, AndroidAsyncExecutorFactory androidAsyncExecutorFactory2, ByteBuffer byteBuffer, ReadinessStatusCallback readinessStatusCallback, ResourceStatusCallback resourceStatusCallback, ByteBuffer byteBuffer2, DeviceContextProvider deviceContextProvider, AudioPlayer audioPlayer, TtsVoiceConfigurationCallback ttsVoiceConfigurationCallback, MobileConfigProvider mobileConfigProvider, XAnalyticsHolder xAnalyticsHolder, RuntimeSanitizerHolder runtimeSanitizerHolder, boolean z) {
        this.mHybridData = initHybrid(hTTPClient, assistantLogger, eventBase, androidAsyncExecutorFactory, androidAsyncExecutorFactory2, byteBuffer, readinessStatusCallback, resourceStatusCallback, byteBuffer2, deviceContextProvider, audioPlayer, ttsVoiceConfigurationCallback, mobileConfigProvider, xAnalyticsHolder, runtimeSanitizerHolder, z);
    }
}
