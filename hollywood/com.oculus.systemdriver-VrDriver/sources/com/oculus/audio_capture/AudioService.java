package com.oculus.audio_capture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.aidl.OVRMediaServiceInterface;

public class AudioService {
    private static final String SERVICE_PACKAGE = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String STREAM_APP_PACKAGE = "com.oculus.horizon";
    private static final String TAG = AudioService.class.getSimpleName();
    private static Object sLock = new Object();
    private static ServiceConnection sServiceConnection = new ServiceConnection() {
        /* class com.oculus.audio_capture.AudioService.AnonymousClass1 */

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (AudioService.sLock) {
                String str = AudioService.TAG;
                Log.d(str, "Connected to audio capture service: " + name);
                OVRMediaServiceInterface unused = AudioService.sServiceInterface = OVRMediaServiceInterface.Stub.asInterface(service);
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            synchronized (AudioService.sLock) {
                Log.d(AudioService.TAG, "Disconnected from audio capture service");
                OVRMediaServiceInterface unused = AudioService.sServiceInterface = null;
            }
        }
    };
    private static OVRMediaServiceInterface sServiceInterface = null;
    private static boolean shouldUnbindFromService = false;

    private static boolean isServiceBound() {
        boolean z;
        synchronized (sLock) {
            z = sServiceInterface != null;
        }
        return z;
    }

    public static void bind(Context context) {
        synchronized (sLock) {
            if (!isServiceBound()) {
                Log.d(TAG, "Binding to audio capture service");
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(STREAM_APP_PACKAGE, 128);
                    String str = TAG;
                    Log.d(str, "Horizon version: " + packageInfo.versionName);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d(TAG, "Cannot get Horizon version");
                }
                Intent serviceIntent = new Intent();
                serviceIntent.setComponent(new ComponentName(STREAM_APP_PACKAGE, SERVICE_PACKAGE));
                shouldUnbindFromService = context.bindService(serviceIntent, sServiceConnection, 1);
            }
        }
    }

    public static void sendPCMData(Context context, int numChannels, int samplesPerSec, int bitsPerSample, short[] pcmData, long trackID, float volume) {
        bind(context);
        if (sServiceInterface != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("num_channels", numChannels);
            bundle.putInt("samples_per_second", samplesPerSec);
            bundle.putInt("bits_per_sample", bitsPerSample);
            bundle.putShortArray("pcm_data", pcmData);
            bundle.putLong("track_id", trackID);
            bundle.putFloat("volume_float", volume);
            try {
                sServiceInterface.sendLivestreamingRawPCMData(bundle);
            } catch (RemoteException exception) {
                Log.d(TAG, "The server doesn't want your PCMs", exception);
            }
        }
    }

    public static void unbind(Context context) {
        if (shouldUnbindFromService) {
            context.unbindService(sServiceConnection);
            shouldUnbindFromService = false;
        }
    }
}
