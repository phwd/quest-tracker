package org.chromium.chrome.browser.crash;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.base.PiiElider;
import org.chromium.components.crash.CrashKeys;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PureJavaExceptionReporter {

    /* renamed from: a  reason: collision with root package name */
    public File f10647a;
    public FileOutputStream b;
    public final String c = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    public final String d;

    public PureJavaExceptionReporter() {
        StringBuilder i = AbstractC2531fV.i("------------");
        i.append(UUID.randomUUID());
        i.append("\r\n");
        this.d = i.toString();
    }

    public final void a(String str, String str2) {
        b(this.d);
        b("Content-Disposition: form-data; name=\"" + str + "\"");
        b("\r\n\r\n" + str2 + "\r\n");
    }

    public final void b(String str) {
        try {
            this.b.write(AbstractC3153j7.b(str));
        } catch (IOException unused) {
        }
    }

    public void c(Throwable th) {
        P21 g0 = P21.g0();
        try {
            d(th);
            FileOutputStream fileOutputStream = this.b;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    this.b.close();
                } catch (Throwable unused) {
                    this.b = null;
                    this.f10647a = null;
                }
            }
            File file = this.f10647a;
            if (file != null) {
                new RunnableC1403Xa0(file).b(true);
            }
            g0.close();
            return;
        } catch (Throwable th2) {
            AbstractC0754Mh1.f8495a.a(th, th2);
        }
        throw th;
    }

    public final void d(Throwable th) {
        String str = null;
        try {
            this.f10647a = new File(new File(ContextUtils.getApplicationContext().getCacheDir(), "Crash Reports"), "chromium-browser-minidump-" + this.c + ".dmp");
            this.b = new FileOutputStream(this.f10647a);
            try {
                int myPid = Process.myPid();
                Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getRunningAppProcesses().iterator();
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
            } catch (SecurityException unused) {
            }
            if (str == null || !str.contains(":")) {
                str = "browser";
            }
            BuildInfo buildInfo = AbstractC0456Hk.f8178a;
            a("prod", "Chrome_Android");
            a("ptype", str);
            a("device", Build.DEVICE);
            a("ver", "89.0.4389.105");
            a("channel", "");
            a("android_build_id", Build.ID);
            a("model", Build.MODEL);
            a("brand", Build.BRAND);
            a("board", Build.BOARD);
            a("android_build_fp", buildInfo.j);
            a("sdk", String.valueOf(Build.VERSION.SDK_INT));
            a("gms_core_version", buildInfo.h);
            a("installer_package_name", buildInfo.g);
            a("abi_name", buildInfo.i);
            a("exception_info", PiiElider.sanitizeStacktrace(Log.getStackTraceString(th)));
            a("early_java_exception", "true");
            a("package", String.format("%s v%s (%s)", BuildInfo.f10582a, Long.valueOf(buildInfo.e), buildInfo.f));
            a("custom_themes", buildInfo.l);
            a("resources_version", buildInfo.m);
            AtomicReferenceArray atomicReferenceArray = CrashKeys.getInstance().b;
            for (int i = 0; i < atomicReferenceArray.length(); i++) {
                String str2 = (String) atomicReferenceArray.get(i);
                if (str2 != null) {
                    a(CrashKeys.f10834a[i], str2);
                }
            }
            b(this.d);
        } catch (FileNotFoundException unused2) {
            this.f10647a = null;
            this.b = null;
        }
    }
}
