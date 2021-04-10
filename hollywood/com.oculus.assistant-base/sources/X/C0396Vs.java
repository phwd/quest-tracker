package X;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import com.facebook.acra.AppComponentStats;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.Vs  reason: case insensitive filesystem */
public final class C0396Vs {
    public static final AtomicBoolean A00 = new AtomicBoolean(false);

    public static void A00(Context context) {
        if (!A00.getAndSet(true)) {
            C0139Dd.A00(2);
            UnifiedTelemetryLogger.getInstance().init(context);
            try {
                Application application = (Application) context.getApplicationContext();
                if (BX.A00 == null) {
                    BX.A00 = application;
                    if (A01(context)) {
                        synchronized (C0212Ka.class) {
                            KK[] kkArr = {null};
                            int i = 2;
                            if (!C0212Ka.A02) {
                                StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                                try {
                                    if (new File("/data/local/tmp/ctscan_test_running").exists()) {
                                        Log.d("FbSoLoader", "disabling background fsync due to CT-Scan");
                                        i = 0;
                                    }
                                    KN.A00(new File(AnonymousClass08.A04(context.getApplicationInfo().dataDir, "/app_libs")));
                                    KJ.A03(context, i);
                                    C0212Ka.A01 = new ArrayList();
                                    KK kk = kkArr[0];
                                    if (kk != null) {
                                        KJ.A04(kk);
                                        C0212Ka.A01.add(kk.toString());
                                    }
                                    KJ.A04(new AnonymousClass84(context));
                                    C0212Ka.A01.add("lib-assets");
                                    KZ kz = KZ.XZ;
                                    KJ.A04(new AnonymousClass2M(context, kz));
                                    C0212Ka.A01.add(kz.getOutputDirectoryName());
                                    KZ kz2 = KZ.ZSTD;
                                    KJ.A04(new AnonymousClass2M(context, kz2));
                                    C0212Ka.A01.add(kz2.getOutputDirectoryName());
                                    KZ kz3 = KZ.SUPERPACK_XZ;
                                    KJ.A04(new AnonymousClass2M(context, kz3));
                                    C0212Ka.A01.add(kz3.getOutputDirectoryName());
                                    KZ kz4 = KZ.SUPERPACK_ZSTD;
                                    KJ.A04(new AnonymousClass2M(context, kz4));
                                    C0212Ka.A01.add(kz4.getOutputDirectoryName());
                                    KZ kz5 = KZ.SUPERPACK_BR;
                                    KJ.A04(new AnonymousClass2M(context, kz5));
                                    C0212Ka.A01.add(kz5.getOutputDirectoryName());
                                    C0212Ka.A00 = context;
                                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                                    C0212Ka.A02 = true;
                                } catch (IOException e) {
                                    Log.e("FbSoLoader", "IOException during init", e);
                                    throw new RuntimeException(e);
                                } catch (Throwable th) {
                                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                                    C0212Ka.A02 = true;
                                    throw th;
                                }
                            }
                        }
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("ApplicationHolder#set previously called");
            } catch (IllegalStateException unused) {
            }
        }
    }

    public static boolean A01(Context context) {
        String str;
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    str = next.processName;
                    break;
                }
            }
        }
        str = null;
        return context.getPackageName().equals(str);
    }
}
