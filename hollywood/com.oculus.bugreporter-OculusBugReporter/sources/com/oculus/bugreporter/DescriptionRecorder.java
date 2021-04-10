package com.oculus.bugreporter;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import java.io.File;
import java.io.IOException;

public class DescriptionRecorder {
    private static final String TAG = "DescriptionRecorder";
    private BugFileUtils mBugFileUtils;
    private final DescriptionRecorderCallback mCallback;
    private final Context mContext;
    private String mFilename;
    private MediaRecorder mRecorder;

    public interface DescriptionRecorderCallback {
        void onDescriptionRecordingStarted(boolean z);
    }

    public DescriptionRecorder(Context context, DescriptionRecorderCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
        this.mBugFileUtils = new BugFileUtils(context);
    }

    public void start(final String bugId) {
        new Thread(new Runnable() {
            /* class com.oculus.bugreporter.DescriptionRecorder.AnonymousClass1 */

            @Override // java.lang.Runnable
            public void run() {
                DescriptionRecorder.this.startIO(bugId);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startIO(String bugId) {
        new File(this.mBugFileUtils.reportDirname(bugId)).mkdirs();
        this.mFilename = this.mBugFileUtils.reportFilename(bugId, BugFileUtils.DESCRIPTION_RECORDING);
        this.mRecorder = new MediaRecorder();
        this.mRecorder.setAudioSource(1);
        this.mRecorder.setOutputFormat(2);
        this.mRecorder.setAudioEncoder(1);
        this.mRecorder.setOutputFile(this.mFilename);
        final boolean success = true;
        try {
            this.mRecorder.prepare();
            this.mRecorder.start();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            success = false;
        }
        new Handler(this.mContext.getMainLooper()).post(new Runnable() {
            /* class com.oculus.bugreporter.DescriptionRecorder.AnonymousClass2 */

            @Override // java.lang.Runnable
            public void run() {
                DescriptionRecorder.this.mCallback.onDescriptionRecordingStarted(success);
            }
        });
    }

    public String stop() {
        this.mRecorder.stop();
        this.mRecorder.release();
        this.mRecorder = null;
        return BugFileUtils.DESCRIPTION_RECORDING;
    }

    public void delete(String bugId) {
        new File(this.mBugFileUtils.reportFilename(bugId, BugFileUtils.DESCRIPTION_RECORDING)).delete();
    }
}
