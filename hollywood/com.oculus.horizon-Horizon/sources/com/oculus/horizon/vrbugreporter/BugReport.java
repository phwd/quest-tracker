package com.oculus.horizon.vrbugreporter;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import android.content.Context;
import android.os.Build;
import com.oculus.util.thread.ThreadUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.annotation.Nullable;

public class BugReport {
    public static final String BUG_REPORT_DIRECTORY = "vrbugreports";
    public static final String TAG = "BugReport";
    public final Context mContext;
    public Date mDate;
    public final Properties mMetadata = new Properties();

    @Nullable
    public static BugReport A00(Context context) {
        BugReport bugReport = new BugReport(context, new Date());
        try {
            if (!A01(bugReport).mkdirs()) {
                AnonymousClass0NO.A08(TAG, "could not create artifact directory");
                return null;
            }
            bugReport.A05();
            return bugReport;
        } catch (SecurityException e) {
            AnonymousClass0NO.A0B(TAG, "permission to the bug report directory denied", e);
            return null;
        }
    }

    public static final File A01(BugReport bugReport) {
        return new File(bugReport.mContext.getFilesDir(), AnonymousClass006.A05("vrbugreports/", bugReport.A05()));
    }

    public static ArrayList<BugReport> A02(Context context) {
        File[] listFiles;
        ArrayList<BugReport> arrayList = new ArrayList<>();
        ThreadUtils.A02();
        try {
            File file = new File(context.getFilesDir(), BUG_REPORT_DIRECTORY);
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    Date parse = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).parse(file2.getName());
                    if (parse != null) {
                        arrayList.add(new BugReport(context, parse));
                    }
                }
            }
        } catch (SecurityException e) {
            AnonymousClass0NO.A0B(TAG, "permission to the bug report directory denied", e);
        } catch (ParseException e2) {
            AnonymousClass0NO.A0B(TAG, "invalid folder in the bug report directory", e2);
            return arrayList;
        }
        return arrayList;
    }

    public final String A05() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(this.mDate);
    }

    public final void A06() {
        String A07 = AnonymousClass006.A07("Build Version: ", Build.FINGERPRINT, "\n");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(A01(this), "oculus-details.txt"));
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                try {
                    outputStreamWriter.write(A07);
                    outputStreamWriter.close();
                    fileOutputStream.close();
                    return;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "failed to write details file", e);
        }
    }

    public BugReport(Context context, Date date) {
        this.mContext = context;
        this.mDate = date;
    }

    public static void A03(BugReport bugReport) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(A01(bugReport), "metadata.txt"));
            try {
                bugReport.mMetadata.load(fileInputStream);
                fileInputStream.close();
                return;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "failed to readFileContent bug report metadata", e);
        }
    }

    public static void A04(BugReport bugReport) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(A01(bugReport), "metadata.txt"));
            try {
                bugReport.mMetadata.store(fileOutputStream, "Bug report metadata");
                fileOutputStream.close();
                return;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "failed to write bug report metadata", e);
        }
    }

    public final void A07(String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(A01(this), "oculus-packages.txt"));
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                try {
                    outputStreamWriter.write(str);
                    outputStreamWriter.close();
                    fileOutputStream.close();
                    return;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "failed to write processes file", e);
        }
    }

    public final boolean A08() {
        if (!A01(this).exists()) {
            return true;
        }
        if (A01(this).listFiles() != null) {
            File[] listFiles = A01(this).listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = listFiles[i];
                if (!file.delete()) {
                    break;
                }
                file.getName();
                i++;
            }
        }
        if (A01(this).delete()) {
            A05();
            return true;
        }
        return false;
    }
}
