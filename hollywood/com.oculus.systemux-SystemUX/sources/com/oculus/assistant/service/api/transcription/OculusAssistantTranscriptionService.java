package com.oculus.assistant.service.api.transcription;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

public class OculusAssistantTranscriptionService {
    private static final String TAG = "Assistant:OATS";
    private final IOculusAssistantTranscriptionService mTranscriptionService;

    public OculusAssistantTranscriptionService(IOculusAssistantTranscriptionService iOculusAssistantTranscriptionService) {
        this.mTranscriptionService = iOculusAssistantTranscriptionService;
    }

    public void configure(Bundle bundle) {
        try {
            this.mTranscriptionService.configure(bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "configure: ", e);
        }
    }

    public void startTranscriptionSession() {
        try {
            this.mTranscriptionService.startTranscriptionSession();
        } catch (RemoteException e) {
            Log.e(TAG, "startTranscriptionSession: ", e);
        }
    }

    public void stopTranscriptionSession() {
        try {
            this.mTranscriptionService.stopTranscriptionSession();
        } catch (RemoteException e) {
            Log.e(TAG, "stopTranscriptionSession: ", e);
        }
    }

    public void logEventTranscriptionUsed(String str, String str2) {
        try {
            this.mTranscriptionService.logEvent(AssistantTranscriptionContract.TRANSCRIPTION_USED, new Bundle());
        } catch (RemoteException e) {
            Log.e(TAG, "logEventTranscriptionUsed: ", e);
        }
    }

    public void logEventTranscriptionNotUsed(String str) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString(AssistantTranscriptionContract.NOT_USED_REASON, str);
            this.mTranscriptionService.logEvent(AssistantTranscriptionContract.TRANSCRIPTION_NOT_USED, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "logEventTranscriptionUsed: ", e);
        }
    }
}
