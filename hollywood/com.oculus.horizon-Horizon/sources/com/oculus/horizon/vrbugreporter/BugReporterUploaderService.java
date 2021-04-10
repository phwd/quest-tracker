package com.oculus.horizon.vrbugreporter;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import X.AnonymousClass1ea;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import com.facebook.ultralight.Eager;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.vrbugreporter.BugReporterMethods;
import com.oculus.util.thread.ThreadUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import retrofit.mime.TypedFile;

public class BugReporterUploaderService extends JobService {
    public static final String TAG = "BugReporterUploaderService";
    public final CommandProcessor mCurrProcessor = new CommandProcessor();
    public JobParameters mParams;
    @Inject
    @Eager
    public BugReporterMethods mRestMethods;

    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        public CommandProcessor() {
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
        @Override // android.os.AsyncTask
        public final Void doInBackground(Void[] voidArr) {
            TypedFile typedFile;
            TypedFile typedFile2;
            TypedFile typedFile3;
            TypedFile typedFile4;
            String str;
            Object[] objArr;
            String str2;
            ArrayList<BugReport> A02 = BugReport.A02(BugReporterUploaderService.this.getApplicationContext());
            A02.size();
            int i = 0;
            boolean z = true;
            while (!isCancelled()) {
                if (i < A02.size()) {
                    BugReport bugReport = A02.get(i);
                    bugReport.A05();
                    BugReport.A03(bugReport);
                    if (bugReport.mMetadata.getProperty("description") != null) {
                        BugReporterMethods bugReporterMethods = BugReporterUploaderService.this.mRestMethods;
                        ThreadUtils.A02();
                        BugReport.A03(bugReport);
                        String property = bugReport.mMetadata.getProperty("description");
                        if (property == null) {
                            AnonymousClass0NO.A0E(BugReporterMethods.TAG, "could not read the description of bug report %s", bugReport.A05());
                        } else {
                            String format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date());
                            if (format == null) {
                                AnonymousClass0NO.A08(BugReporterMethods.TAG, "could not create a session identifier");
                            } else {
                                File file = new File(BugReport.A01(bugReport), "audio-clip.wav");
                                TypedFile typedFile5 = null;
                                if (file.exists()) {
                                    typedFile = new TypedFile("audio/m4a", file);
                                } else {
                                    typedFile = null;
                                }
                                File file2 = new File(BugReport.A01(bugReport), "screenshot.png");
                                if (file2.exists()) {
                                    typedFile2 = new TypedFile("image/png", file2);
                                } else {
                                    typedFile2 = null;
                                }
                                File file3 = new File(BugReport.A01(bugReport), "log.zip");
                                if (file3.exists()) {
                                    typedFile3 = new TypedFile(AnonymousClass1ea.MIME_PLAINTEXT, file3);
                                } else {
                                    typedFile3 = null;
                                }
                                File file4 = new File(BugReport.A01(bugReport), "oculus-details.txt");
                                if (file4.exists()) {
                                    typedFile4 = new TypedFile(AnonymousClass1ea.MIME_PLAINTEXT, file4);
                                } else {
                                    typedFile4 = null;
                                }
                                File file5 = new File(BugReport.A01(bugReport), "oculus-packages.txt");
                                if (file5.exists()) {
                                    typedFile5 = new TypedFile(AnonymousClass1ea.MIME_PLAINTEXT, file5);
                                }
                                try {
                                    BugReporterMethods.Methods methods = bugReporterMethods.mMethods;
                                    BugReport.A03(bugReport);
                                    methods.sendBugReport(bugReport.mMetadata.getProperty("appId"), BugReporterMethods.OCULUS_THIRD_PARTY_CATEGORY_ID, format, property, typedFile3, typedFile2, typedFile, typedFile4, typedFile5);
                                    if (!bugReport.A08()) {
                                        str = BugReporterUploaderService.TAG;
                                        objArr = new Object[]{bugReport.A05()};
                                        str2 = "could not delete bug report %s";
                                        AnonymousClass0NO.A0E(str, str2, objArr);
                                        z = false;
                                    }
                                } catch (Exception e) {
                                    AnonymousClass0NO.A0E(BugReporterMethods.TAG, "send report failed with %s", e.getLocalizedMessage(), e);
                                    ((IErrorReporter) AnonymousClass0J2.A03(0, 428, bugReporterMethods._UL_mInjectionContext)).A97(BugReporterMethods.TAG, "send bug report failed", e);
                                }
                            }
                        }
                        str = BugReporterUploaderService.TAG;
                        objArr = new Object[]{bugReport.A05()};
                        str2 = "could not upload bug report %s";
                        AnonymousClass0NO.A0E(str, str2, objArr);
                        z = false;
                    } else {
                        bugReport.A05();
                    }
                    i++;
                } else {
                    BugReporterUploaderService bugReporterUploaderService = BugReporterUploaderService.this;
                    bugReporterUploaderService.jobFinished(bugReporterUploaderService.mParams, true ^ z);
                    return null;
                }
            }
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

    public final void onCreate() {
        super.onCreate();
        this.mRestMethods = (BugReporterMethods) AnonymousClass117.A00(268, AnonymousClass0J2.get(this));
    }
}
