package com.oculus.platform;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;

public class OVRHeadsetMonitor {
    public final String TAG = "OVRPlatform-OVRHeadsetMonitor";
    public final Context mContext;

    private boolean getHeadsetActive(AudioManager audioManager) {
        AudioDeviceInfo[] devices;
        for (AudioDeviceInfo audioDeviceInfo : audioManager.getDevices(1)) {
            int type = audioDeviceInfo.getType();
            if (type == 8 || type == 7 || type == 3 || type == 4 || type == 11 || type == 12 || type == 22) {
                return true;
            }
        }
        return false;
    }

    public void Shutdown() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleHeadsetState() {
        boolean z;
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (!getHeadsetActive(audioManager)) {
            audioManager.setMode(3);
            z = true;
        } else {
            z = false;
            audioManager.setMode(0);
        }
        audioManager.setSpeakerphoneOn(z);
    }

    public OVRHeadsetMonitor(Context context) {
        this.mContext = context;
        ((AudioManager) context.getSystemService("audio")).registerAudioDeviceCallback(new AudioDeviceCallback() {
            /* class com.oculus.platform.OVRHeadsetMonitor.AnonymousClass1 */

            public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
                super.onAudioDevicesAdded(audioDeviceInfoArr);
                OVRHeadsetMonitor.this.handleHeadsetState();
            }

            public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
                super.onAudioDevicesRemoved(audioDeviceInfoArr);
                OVRHeadsetMonitor.this.handleHeadsetState();
            }
        }, null);
        handleHeadsetState();
    }
}
