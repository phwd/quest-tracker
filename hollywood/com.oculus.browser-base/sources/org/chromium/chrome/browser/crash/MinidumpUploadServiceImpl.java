package org.chromium.chrome.browser.crash;

import J.N;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PersistableBundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.JavaExceptionReporter;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MinidumpUploadServiceImpl extends XY0 {
    public static AtomicBoolean b = new AtomicBoolean();
    public static AtomicBoolean c = new AtomicBoolean();

    public static boolean browserCrashMetricsInitialized() {
        return b.get();
    }

    public static String c(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        IOException e;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        O21.a(bufferedReader);
                        return "Other";
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        AbstractC1220Ua0.f("MinidmpUploadService", "Error while reading crash file %s: %s", str, e.toString());
                        O21.a(bufferedReader);
                        return "Other";
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                    }
                }
            } while (!readLine.equals("Content-Disposition: form-data; name=\"ptype\""));
            bufferedReader.readLine();
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null) {
                O21.a(bufferedReader);
                return "Other";
            } else if (readLine2.equals("browser")) {
                O21.a(bufferedReader);
                return "Browser";
            } else if (readLine2.equals("renderer")) {
                O21.a(bufferedReader);
                return "Renderer";
            } else if (readLine2.equals("gpu-process")) {
                O21.a(bufferedReader);
                return "GPU";
            } else {
                O21.a(bufferedReader);
                return "Other";
            }
        } catch (IOException e3) {
            bufferedReader = null;
            e = e3;
            AbstractC1220Ua0.f("MinidmpUploadService", "Error while reading crash file %s: %s", str, e.toString());
            O21.a(bufferedReader);
            return "Other";
        } catch (Throwable th3) {
            th = th3;
            O21.a(bufferedReader2);
            throw th;
        }
    }

    public static void d() {
        WF0 a2 = WF0.a();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean("isClientInMetricsSample", a2.c.d("in_metrics_sample", true));
        persistableBundle.putBoolean("isUploadEnabledForTests", AbstractC1575Zv.e().g("force-dump-upload"));
        JobInfo.Builder extras = new JobInfo.Builder(43, new ComponentName(ContextUtils.getApplicationContext(), Abstractjob.JobServiceC2424es.class)).setExtras(persistableBundle);
        int i = ZY0.F;
        AbstractC1220Ua0.d("MinidumpJobService", "Scheduling upload of all pending minidumps.", new Object[0]);
        ((JobScheduler) ContextUtils.getApplicationContext().getSystemService("jobscheduler")).schedule(extras.setRequiredNetworkType(2).setBackoffCriteria(1800000, 1).build());
    }

    public static boolean didBrowserCrashRecently() {
        return c.get();
    }

    public static void e(File file) {
        C1619aB aBVar = new C1619aB(ContextUtils.getApplicationContext().getCacheDir());
        Intent intent = new Intent(ContextUtils.getApplicationContext(), AbstractIntentServiceC0335Fk0.class);
        intent.setAction("com.google.android.apps.chrome.crash.ACTION_UPLOAD");
        intent.putExtra("minidump_file", file.getAbsolutePath());
        intent.putExtra("upload_log", new File(aBVar.b(), "uploads.log").getAbsolutePath());
        ContextUtils.getApplicationContext().startService(intent);
    }

    public static void tryUploadCrashDumpWithLocalId(String str) {
        File file;
        if (str == null || str.isEmpty()) {
            AbstractC1220Ua0.f("MinidmpUploadService", "Cannot force crash upload since local crash id is absent.", new Object[0]);
            return;
        }
        File file2 = null;
        File[] c2 = new C1619aB(ContextUtils.getApplicationContext().getCacheDir()).c(null);
        int length = c2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                file = null;
                break;
            }
            file = c2[i];
            if ((file.getName().contains(".dmp") || file.getName().contains(".skipped") || file.getName().contains(".forced")) && file.getName().split("\\.")[0].endsWith(str)) {
                break;
            }
            i++;
        }
        if (file == null) {
            AbstractC1220Ua0.f("MinidmpUploadService", AbstractC2531fV.f("Could not find a crash dump with local ID ", str), new Object[0]);
            return;
        }
        if (file.getName().contains(".up")) {
            StringBuilder i2 = AbstractC2531fV.i("Refusing to reset upload attempt state for a file that has already been successfully uploaded: ");
            i2.append(file.getName());
            AbstractC1220Ua0.f("CrashFileManager", i2.toString(), new Object[0]);
        } else {
            String path = file.getPath();
            int e = C1619aB.e(path);
            if (e < 0) {
                e = 0;
            }
            if (e > 0) {
                path = path.replace(".try" + e, ".try0");
            }
            File file3 = new File(path.replace(".skipped", ".forced").replace(".dmp", ".forced"));
            if (file.renameTo(file3)) {
                file2 = file3;
            }
        }
        if (file2 == null) {
            StringBuilder i3 = AbstractC2531fV.i("Could not rename the file ");
            i3.append(file.getName());
            i3.append(" for re-upload");
            AbstractC1220Ua0.f("MinidmpUploadService", i3.toString(), new Object[0]);
            return;
        }
        d();
    }

    @Override // defpackage.XY0
    public void a(Intent intent) {
        String str;
        String str2;
        String str3;
        if (intent != null) {
            if (!"com.google.android.apps.chrome.crash.ACTION_UPLOAD".equals(intent.getAction())) {
                StringBuilder i = AbstractC2531fV.i("Got unknown action from intent: ");
                i.append(intent.getAction());
                AbstractC1220Ua0.f("MinidmpUploadService", i.toString(), new Object[0]);
                return;
            }
            String stringExtra = intent.getStringExtra("minidump_file");
            if (stringExtra == null || stringExtra.isEmpty()) {
                AbstractC1220Ua0.f("MinidmpUploadService", "Cannot upload crash data since minidump is absent.", new Object[0]);
                return;
            }
            File file = new File(stringExtra);
            if (!file.isFile()) {
                AbstractC1220Ua0.f("MinidmpUploadService", AbstractC2531fV.g("Cannot upload crash data since specified minidump ", stringExtra, " is not present."), new Object[0]);
                return;
            }
            int e = C1619aB.e(stringExtra);
            if (e < 0) {
                e = 0;
            }
            if (e >= 3 || e < 0) {
                AbstractC1220Ua0.a("MinidmpUploadService", AbstractC2531fV.g("Giving up on trying to upload ", stringExtra, " after failing to read a valid attempt number."), new Object[0]);
                return;
            }
            new File(intent.getStringExtra("upload_log"));
            WF0.a();
            Exception exc = new Exception("Oculus Browser should not be using MinidumpUploadCallable");
            int i2 = JavaExceptionReporter.f10588a;
            N.MLlibBXh(false, exc);
            Integer num = 0;
            int intValue = num.intValue();
            if (intValue == 0) {
                String c2 = c(stringExtra.replace("dmp", "up").replace("forced", "up"));
                if ("Browser".equals(c2)) {
                    c.set(true);
                }
                PU0 pu0 = C2482fB.f9903a.b;
                c2.hashCode();
                char c3 = 65535;
                switch (c2.hashCode()) {
                    case -430201629:
                        if (c2.equals("Renderer")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case 70796:
                        if (c2.equals("GPU")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 76517104:
                        if (c2.equals("Other")) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case 1815593736:
                        if (c2.equals("Browser")) {
                            c3 = 3;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        str3 = "renderer_crash_success_upload";
                        break;
                    case 1:
                        str3 = "gpu_crash_success_upload";
                        break;
                    case 2:
                        str3 = "other_crash_success_upload";
                        break;
                    case 3:
                        str3 = "browser_crash_success_upload";
                        break;
                    default:
                        throw new IllegalArgumentException(AbstractC2531fV.f("Process type unknown: ", c2));
                }
                pu0.c(str3);
            } else if (intValue == 1) {
                int i3 = e + 1;
                if (i3 == 3) {
                    String c4 = c(stringExtra);
                    if ("Browser".equals(c4)) {
                        c.set(true);
                    }
                    PU0 pu02 = C2482fB.f9903a.b;
                    c4.hashCode();
                    char c5 = 65535;
                    switch (c4.hashCode()) {
                        case -430201629:
                            if (c4.equals("Renderer")) {
                                c5 = 0;
                                break;
                            }
                            break;
                        case 70796:
                            if (c4.equals("GPU")) {
                                c5 = 1;
                                break;
                            }
                            break;
                        case 76517104:
                            if (c4.equals("Other")) {
                                c5 = 2;
                                break;
                            }
                            break;
                        case 1815593736:
                            if (c4.equals("Browser")) {
                                c5 = 3;
                                break;
                            }
                            break;
                    }
                    switch (c5) {
                        case 0:
                            str2 = "renderer_crash_failure_upload";
                            break;
                        case 1:
                            str2 = "gpu_crash_failure_upload";
                            break;
                        case 2:
                            str2 = "other_crash_failure_upload";
                            break;
                        case 3:
                            str2 = "browser_crash_failure_upload";
                            break;
                        default:
                            throw new IllegalArgumentException(AbstractC2531fV.f("Process type unknown: ", c4));
                    }
                    pu02.c(str2);
                }
                String path = file.getPath();
                int e2 = C1619aB.e(path);
                if (e2 >= 0) {
                    int i4 = e2 + 1;
                    str = path.replace(AbstractC2531fV.w(".try", e2), ".try" + i4);
                } else {
                    str = AbstractC2531fV.g(path, ".try", "1");
                }
                if (!file.renameTo(new File(str))) {
                    str = null;
                }
                if (str == null) {
                    AbstractC1220Ua0.f("MinidmpUploadService", AbstractC2531fV.f("Failed to rename minidump ", stringExtra), new Object[0]);
                } else if (i3 < 3) {
                    PostTask.b(Zo1.f9374a, new RunnableC0213Dk0(ContextUtils.getApplicationContext(), WF0.a(), null), 0);
                }
            }
        }
    }

    @Override // defpackage.XY0
    public void b() {
        this.f9216a.setIntentRedelivery(true);
    }
}
