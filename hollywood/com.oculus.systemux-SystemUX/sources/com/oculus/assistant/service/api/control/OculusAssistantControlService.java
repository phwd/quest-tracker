package com.oculus.assistant.service.api.control;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.assistantutils.audio.AudioDataUtil;
import com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource;
import com.oculus.assistant.service.api.remoteaudiosource.RemoteAudioSource;
import java.util.HashMap;

public class OculusAssistantControlService {
    private static final String TAG = "Assistant:OACS";
    private final HashMap<RemoteAudioSource, String> mAudioSources = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IOculusAssistantControlService mService;

    public OculusAssistantControlService(IOculusAssistantControlService iOculusAssistantControlService) {
        this.mService = iOculusAssistantControlService;
    }

    public void activateAssistant() {
        try {
            this.mService.activateAssistant();
        } catch (RemoteException e) {
            Log.e(TAG, "activateAssistant: ", e);
        }
    }

    public void stopAssistant() {
        try {
            this.mService.stopAssistant();
        } catch (RemoteException e) {
            Log.e(TAG, "stopAssistant: ", e);
        }
    }

    public boolean isAutorecordingEnabled() {
        try {
            return this.mService.isAutorecordingEnabled();
        } catch (RemoteException e) {
            Log.e(TAG, "stopAssistant: ", e);
            return false;
        }
    }

    public void addAudioSource(final RemoteAudioSource remoteAudioSource, int i) {
        if (remoteAudioSource != null) {
            try {
                this.mAudioSources.put(remoteAudioSource, this.mService.addAudioSource(new IRemoteAudioSource.Stub() {
                    /* class com.oculus.assistant.service.api.control.OculusAssistantControlService.AnonymousClass1 */
                    private int mReadMethod;

                    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
                    public int read(byte[] bArr, int i, int i2) throws RemoteException {
                        int i3;
                        try {
                            int i4 = this.mReadMethod;
                            if (i4 == 0) {
                                return remoteAudioSource.read(bArr, i, i2);
                            }
                            if (i4 == 1) {
                                float[] readPcmFloatData = remoteAudioSource.readPcmFloatData((int) (((float) i2) / 2.0f));
                                for (int i5 = 0; i5 < readPcmFloatData.length; i5++) {
                                    AudioDataUtil.encodePcm(readPcmFloatData[i5], bArr, (i5 * 2) + i);
                                }
                                i3 = readPcmFloatData.length;
                            } else if (i4 == 2) {
                                short[] readPcmShortData = remoteAudioSource.readPcmShortData((int) (((float) i2) / 2.0f));
                                for (int i6 = 0; i6 < readPcmShortData.length; i6++) {
                                    AudioDataUtil.encodePcm(readPcmShortData[i6], bArr, (i6 * 2) + i);
                                }
                                i3 = readPcmShortData.length;
                            } else if (i4 == 3) {
                                Float[] readPcmBoxedFloatData = remoteAudioSource.readPcmBoxedFloatData((int) (((float) i2) / 2.0f));
                                for (int i7 = 0; i7 < readPcmBoxedFloatData.length; i7++) {
                                    AudioDataUtil.encodePcm(readPcmBoxedFloatData[i7].floatValue(), bArr, (i7 * 2) + i);
                                }
                                return 0;
                            } else if (i4 == 4) {
                                Short[] readPcmBoxedShortData = remoteAudioSource.readPcmBoxedShortData((int) (((float) i2) / 2.0f));
                                for (int i8 = 0; i8 < readPcmBoxedShortData.length; i8++) {
                                    AudioDataUtil.encodePcm(readPcmBoxedShortData[i8].shortValue(), bArr, (i8 * 2) + i);
                                }
                                i3 = readPcmBoxedShortData.length;
                            } else if (i4 != 5) {
                                return 0;
                            } else {
                                byte[] readPcmByteArray = remoteAudioSource.readPcmByteArray((int) (((float) i2) / 2.0f));
                                if (readPcmByteArray.length > bArr.length) {
                                    Log.e(OculusAssistantControlService.TAG, "Buffer overflow.");
                                }
                                int i9 = 0;
                                while (i9 < bArr.length && i9 < readPcmByteArray.length) {
                                    bArr[i9] = readPcmByteArray[i9];
                                    i9++;
                                }
                                return Math.min(bArr.length, readPcmByteArray.length);
                            }
                            return i3 * 2;
                        } catch (Exception e) {
                            Log.e(OculusAssistantControlService.TAG, "Error reading from remote source.", e);
                            return 0;
                        }
                    }

                    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
                    public boolean isSourceAvailable() {
                        return remoteAudioSource.isSourceAvailable();
                    }

                    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
                    public void open() {
                        remoteAudioSource.open();
                        this.mReadMethod = remoteAudioSource.getSupportedReadType();
                    }

                    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
                    public void close() {
                        remoteAudioSource.close();
                    }

                    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
                    public String getSourceName() {
                        return remoteAudioSource.getSourceName();
                    }
                }, i));
            } catch (RemoteException e) {
                Log.e(TAG, "addAudioSource: ", e);
            }
        }
    }

    public void removeAudioSource(RemoteAudioSource remoteAudioSource) {
        if (remoteAudioSource != null) {
            try {
                if (this.mAudioSources.containsKey(remoteAudioSource)) {
                    this.mService.removeAudioSource(this.mAudioSources.get(remoteAudioSource));
                }
            } catch (RemoteException e) {
                Log.e(TAG, "removeAudioSource: ", e);
            }
        }
    }
}
