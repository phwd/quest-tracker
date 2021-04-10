package com.oculus.vrshell.home.module.audio;

import android.annotation.SuppressLint;
import android.media.AudioRecord;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.facebook.debug.log.BLog;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Recorder {
    private static final String TAG = Recorder.class.getSimpleName();
    private final File mPath;
    private boolean mRecording;

    /* access modifiers changed from: protected */
    public abstract int getChannelMask();

    /* access modifiers changed from: protected */
    public abstract int getEncoding();

    /* access modifiers changed from: protected */
    public abstract String getFileExtension();

    /* access modifiers changed from: protected */
    public abstract int getSampleRate();

    /* access modifiers changed from: protected */
    public abstract void onRecordingStart();

    /* access modifiers changed from: protected */
    public abstract void onRecordingStop(@NonNull File file);

    /* access modifiers changed from: protected */
    public abstract void writeFileFooter(@NonNull File file) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void writeFileHeader(@NonNull OutputStream outputStream) throws IOException;

    public Recorder(@NonNull File path) {
        this.mPath = path;
    }

    @SuppressLint({"StaticFieldLeak"})
    public boolean start(@NonNull String fileName) {
        if (this.mRecording) {
            return false;
        }
        final File outFile = new File(this.mPath, fileName + "." + getFileExtension());
        int sampleRate = getSampleRate();
        int channelMask = getChannelMask();
        int encoding = getEncoding();
        final int bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelMask, encoding) * 2;
        final AudioRecord recorder = new AudioRecord(1, sampleRate, channelMask, encoding, bufferSize);
        if (recorder.getState() != 1) {
            BLog.e(TAG, "uninitialized, aborting");
            return false;
        }
        try {
            recorder.startRecording();
            this.mRecording = true;
            new AsyncTask<Void, Void, Void>() {
                /* class com.oculus.vrshell.home.module.audio.Recorder.AnonymousClass1 */

                /* access modifiers changed from: protected */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x006d, code lost:
                    r5 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
                    r5 = r4;
                    r4 = r5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:0x0082, code lost:
                    r4 = th;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:39:0x0083, code lost:
                    r5 = null;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Void doInBackground(java.lang.Void... r10) {
                    /*
                    // Method dump skipped, instructions count: 133
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.module.audio.Recorder.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Void");
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void aVoid) {
                    super.onPostExecute((Object) aVoid);
                    if (recorder.getState() == 1) {
                        recorder.stop();
                        recorder.release();
                        Recorder.this.mRecording = false;
                        Recorder.this.onRecordingStop(outFile);
                    }
                }
            }.execute(new Void[0]);
            return true;
        } catch (IllegalStateException ise) {
            BLog.e(TAG, ise.getMessage(), ise);
            return false;
        }
    }

    public boolean stop() {
        if (!this.mRecording) {
            return false;
        }
        this.mRecording = false;
        return true;
    }
}
