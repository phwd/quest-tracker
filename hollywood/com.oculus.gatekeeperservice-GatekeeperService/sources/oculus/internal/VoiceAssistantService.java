package oculus.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.IVoiceAssistantService;

public class VoiceAssistantService implements IBinder.DeathRecipient {
    private static final String TAG = VoiceAssistantService.class.getSimpleName();
    private static final String VOICE_SERVICE = "VoiceAssistantService";
    private static VoiceAssistantService sInstance;
    private IVoiceAssistantService mService;

    private static class InstanceHolder {
        static final VoiceAssistantService INSTANCE = new VoiceAssistantService();

        private InstanceHolder() {
        }
    }

    public static VoiceAssistantService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private VoiceAssistantService() {
        ensureServiceConnected();
    }

    private boolean ensureServiceConnected() {
        if (this.mService != null) {
            return true;
        }
        IBinder b = ServiceManager.getService(VOICE_SERVICE);
        this.mService = IVoiceAssistantService.Stub.asInterface(b);
        if (this.mService == null) {
            Log.w(TAG, "Failed to get VoiceAssistantService");
            return false;
        }
        try {
            b.linkToDeath(this, 0);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "linkToDeath failed", e);
            return true;
        }
    }

    public void binderDied() {
        Log.d(TAG, "Remote service died, resetting mService to null");
        this.mService = null;
    }

    public byte[] getMicData() {
        if (!ensureServiceConnected()) {
            return null;
        }
        MicrophoneData micData = new MicrophoneData();
        try {
            this.mService.getMicData(micData);
            return micData.getData();
        } catch (Exception e) {
            Log.e(TAG, "Exception while calling VoiceAssistantService getMicData method: ", e);
            return null;
        }
    }

    public void initialize(boolean useWakeWord) {
        if (ensureServiceConnected()) {
            try {
                this.mService.initialize(useWakeWord);
            } catch (Exception e) {
                Log.e(TAG, "Exception while calling VoiceAssistantService initialize method: ", e);
            }
        }
    }

    public void resetWakeWordRecognition() {
        if (ensureServiceConnected()) {
            try {
                this.mService.resetWakeWordRecognition();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception while calling VoiceAssistantService resetWakeWordRecognition method: ", e);
            }
        }
    }

    public void stopWakeWordRecognition() {
        if (ensureServiceConnected()) {
            try {
                this.mService.stopWakeWordRecognition();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception while calling VoiceAssistantService stopWakeWordRecognition method: ", e);
            }
        }
    }

    public void stopAudioCapture() {
        if (ensureServiceConnected()) {
            try {
                this.mService.stopAudioCapture();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception while calling VoiceAssistantService stopAudioCapture method: ", e);
            }
        }
    }

    public void startAudioCapture() {
        if (ensureServiceConnected()) {
            try {
                this.mService.startAudioCapture();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception while calling VoiceAssistantService startAudioCapture method: ", e);
            }
        }
    }
}
