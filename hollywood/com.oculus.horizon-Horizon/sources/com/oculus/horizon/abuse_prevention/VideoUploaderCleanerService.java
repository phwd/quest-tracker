package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass0NO;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import java.io.File;
import java.io.IOException;

@TargetApi(21)
public class VideoUploaderCleanerService extends JobService {
    public static final String TAG = "VideoUploaderCleanerService";
    public static final int VIDEO_UPLOADER_CLEANER_JOB_ID = 8192;
    public final CommandProcessor mCurrProcessor = new CommandProcessor();
    public JobParameters mParams;
    public VideoUploaderCleanerServiceManager mVideoUploaderCleanerServiceManager = new VideoUploaderCleanerServiceManager();

    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        public CommandProcessor() {
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
        @Override // android.os.AsyncTask
        public final Void doInBackground(Void[] voidArr) {
            VideoUploaderCleanerService videoUploaderCleanerService = VideoUploaderCleanerService.this;
            File[] fileArr = {new File(videoUploaderCleanerService.getCacheDir(), AbuseReportFileUtils.RECORDING_PATH), new File(videoUploaderCleanerService.getCacheDir(), AbuseReportFileUtils.STAGING_PATH), new File(videoUploaderCleanerService.getCacheDir(), AbuseReportFileUtils.UPLOADING_PATH)};
            long currentTimeMillis = System.currentTimeMillis() - VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS;
            int i = 0;
            do {
                File file = fileArr[i];
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    AnonymousClass0NO.A0E(VideoUploaderCleanerServiceManager.TAG, "unable to listFiles for %s: not a directory", VideoUploaderCleanerServiceManager.A00(file));
                } else {
                    VideoUploaderCleanerServiceManager.A00(file);
                    for (File file2 : listFiles) {
                        if (isCancelled()) {
                            return null;
                        }
                        String A00 = VideoUploaderCleanerServiceManager.A00(file2);
                        if (!file2.isDirectory()) {
                            AnonymousClass0NO.A0F(VideoUploaderCleanerServiceManager.TAG, "found unexpected non-directory: %s", A00);
                        } else if (file2.lastModified() <= currentTimeMillis) {
                            try {
                                AbuseReportFileUtils.A03(file2);
                            } catch (IOException e) {
                                AnonymousClass0NO.A0K(VideoUploaderCleanerServiceManager.TAG, e, "error deleting dir %s", VideoUploaderCleanerServiceManager.A00(file2));
                            }
                        }
                    }
                }
                i++;
            } while (i < 3);
            VideoUploaderCleanerService videoUploaderCleanerService2 = VideoUploaderCleanerService.this;
            videoUploaderCleanerService2.jobFinished(videoUploaderCleanerService2.mParams, false);
            return null;
        }
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        this.mParams = jobParameters;
        this.mCurrProcessor.execute(new Void[0]);
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        this.mCurrProcessor.cancel(false);
        return true;
    }
}
