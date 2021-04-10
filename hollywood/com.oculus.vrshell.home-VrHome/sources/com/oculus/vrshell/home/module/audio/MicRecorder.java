package com.oculus.vrshell.home.module.audio;

import android.content.Context;
import android.util.Pair;
import com.facebook.debug.log.BLog;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MicRecorder extends RCTBaseJavaModule {
    private static final String MODULE_NAME = "Mic";
    private static final String[] REQUIRED_PERMISSIONS = {"android.permission.RECORD_AUDIO"};
    private static final String TAG = MicRecorder.class.getSimpleName();
    private final Context mContext;
    private Recorder mRecorder;

    private String[] getAllRevokedPermissions() {
        ArrayList<String> res = new ArrayList<>();
        String[] strArr = REQUIRED_PERMISSIONS;
        for (String permission : strArr) {
            if (!(this.mContext.checkCallingPermission(permission) == 0)) {
                res.add(permission);
            }
        }
        return (String[]) res.toArray(new String[res.size()]);
    }

    private Recorder createRecorder(File outputFile) {
        return new WavRecorder(outputFile) {
            /* class com.oculus.vrshell.home.module.audio.MicRecorder.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.vrshell.home.module.audio.Recorder
            public int getSampleRate() {
                return 44100;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.vrshell.home.module.audio.Recorder
            public int getEncoding() {
                return 2;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.vrshell.home.module.audio.Recorder
            public int getChannelMask() {
                return 16;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.vrshell.home.module.audio.Recorder
            public void onRecordingStart() {
                MicRecorder.nativeEmitEvent(MicRecorder.this.RVRCtxTag, "onStartRecording");
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.vrshell.home.module.audio.Recorder
            public void onRecordingStop(File recordedFile) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("outFilePath", recordedFile.toString());
                    MicRecorder.nativeEmitEventWithJsonData(MicRecorder.this.RVRCtxTag, "onStopRecording", jsonObject.toString());
                } catch (JSONException je) {
                    BLog.e(MicRecorder.TAG, je.getMessage(), je);
                }
            }
        };
    }

    public MicRecorder(Context context) {
        this.mContext = context;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public void shutdownModule() {
        stopRecording();
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("startRecording", "ss"));
        list.add(new Pair<>("stopRecording", ""));
        return list;
    }

    public synchronized void startRecording(String path, String outputFileName) {
        if (this.mRecorder == null) {
            String[] revokedPermissions = getAllRevokedPermissions();
            int length = revokedPermissions.length;
            for (int i = 0; i < length; i++) {
                BLog.w(TAG, revokedPermissions[i] + " revoked");
            }
            this.mRecorder = createRecorder(new File(path));
            if (!this.mRecorder.start(outputFileName)) {
                this.mRecorder = null;
            }
        }
    }

    public synchronized void stopRecording() {
        if (this.mRecorder != null) {
            this.mRecorder.stop();
            this.mRecorder = null;
        }
    }
}
