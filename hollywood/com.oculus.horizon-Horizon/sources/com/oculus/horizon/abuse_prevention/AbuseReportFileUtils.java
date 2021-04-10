package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.C07590uC;
import android.content.Context;
import java.io.File;
import java.io.IOException;

public class AbuseReportFileUtils {
    public static final String RECORDING_PATH;
    public static final String REPORTS_PATH = "reports";
    public static final String STAGING_PATH;
    public static final String TAG = "AbuseReportFileUtils";
    public static final String UPLOADING_PATH;
    public static final String VIDEO_RECORDING_FILE = "vid.mp4";

    static {
        String str = File.separator;
        RECORDING_PATH = AnonymousClass006.A07(REPORTS_PATH, str, "rec");
        STAGING_PATH = AnonymousClass006.A07(REPORTS_PATH, str, "stage");
        UPLOADING_PATH = AnonymousClass006.A07(REPORTS_PATH, str, "upload");
    }

    public static File A00(Context context, String str) {
        return new File(context.getCacheDir(), AnonymousClass006.A07(RECORDING_PATH, File.separator, str));
    }

    public static void A01(Context context, String str) {
        File A00 = A00(context, str);
        if (A00.exists() && A00.isDirectory()) {
            new File(A00, VIDEO_RECORDING_FILE).delete();
            A00.delete();
        }
    }

    public static void A02(Context context, String str) throws IOException {
        File A00 = A00(context, str);
        if (A00.exists() && A00.isDirectory()) {
            File file = new File(context.getCacheDir(), AnonymousClass006.A07(STAGING_PATH, File.separator, str));
            A04(file);
            C07590uC.A00(A00, file);
        }
    }

    public static void A03(File file) throws IOException {
        String str;
        Object[] objArr;
        String str2;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            str = TAG;
            objArr = new Object[]{file.getCanonicalPath()};
            str2 = "not deleting %s: already deleted";
        } else {
            for (File file2 : listFiles) {
                if (!file2.isFile()) {
                    AnonymousClass0NO.A0F(TAG, "found unexpected non-file: %s", file2.getCanonicalPath());
                } else if (!file2.delete()) {
                    AnonymousClass0NO.A0E(TAG, "could not delete file: %s", file2.getCanonicalPath());
                }
            }
            if (!file.delete()) {
                str = TAG;
                objArr = new Object[]{file.getCanonicalPath()};
                str2 = "could not delete directory: %s";
            } else {
                return;
            }
        }
        AnonymousClass0NO.A0E(str, str2, objArr);
    }

    public static void A04(File file) throws IOException {
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException(AnonymousClass006.A05("Failed to create directory: ", file.getAbsolutePath()));
        }
    }
}
