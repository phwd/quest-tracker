package com.facebook.msys.mcd;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class MqttNetworkSessionPlugin {
    public static final MqttNetworkSessionPlugin sInstance = new MqttNetworkSessionPlugin();
    public volatile VrMsysMqttClientCallbacks mMqttClientCallbacks;

    @DoNotStrip
    public static native void onMqttConnected();

    @DoNotStrip
    public static native void onMqttConnecting();

    @DoNotStrip
    public static native void onMqttDisconnected();

    @DoNotStrip
    public static native void onMqttPubAck(int i);

    @DoNotStrip
    public static native void onMqttPubAckTimeout(int i);

    @DoNotStrip
    public static native void onMqttPubError(int i);

    @DoNotStrip
    public static native void onMqttPublishReceived(String str, byte[] bArr);

    @DoNotStrip
    public static native void registerNative(NetworkSession networkSession, AuthData authData, NotificationCenter notificationCenter, Mailbox mailbox, String str);

    @DoNotStrip
    public static native void unregisterNative(NetworkSession networkSession, AuthData authData);

    @DoNotStrip
    public static int onGetConnectionState() {
        return sInstance.mMqttClientCallbacks.onGetConnectionState();
    }

    @DoNotStrip
    public static int onPublish(String str, int i, byte[] bArr) {
        return sInstance.mMqttClientCallbacks.onPublish(str, i, bArr);
    }

    static {
        AnonymousClass1NZ.A00();
    }

    @DoNotStrip
    public static void onCancelPublish(int i) {
    }

    @DoNotStrip
    public static void subscribeToTopic(String str) {
    }

    @DoNotStrip
    public static void unsubscribeFromTopic(String str) {
    }
}
