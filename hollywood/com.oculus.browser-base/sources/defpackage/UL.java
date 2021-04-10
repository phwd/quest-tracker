package defpackage;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* renamed from: UL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UL {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f9023a = Pattern.compile("^\\d+-[a-zA-Z0-9_\\-]+-(\\d+)\\.(temp_stacktrace|stacktrace)$");
    public static final TL[] b = {TL.ACRA_CRASH_REPORT, TL.NATIVE_CRASH_REPORT, TL.ANR_REPORT};
    public static UL c;
    public static int d = 5;
    public static int e = 20;
    public static AtomicBoolean f = new AtomicBoolean(false);
    public boolean g = false;
    public ArrayList h = new ArrayList();
    public final Map i = new HashMap();
    public final Map j = new HashMap();
    public C3798mu0 k;
    public Map l = new ConcurrentHashMap();
    public Map m = new ConcurrentHashMap();
    public boolean n = false;
    public final Object o = new Object();
    public Context p;
    public File q = null;
    public final OW0 r = new OW0(e);
    public String s;
    public String t;
    public boolean u;
    public String v;
    public final Time w = new Time();
    public boolean x = false;
    public boolean y;

    public static void e(File file) {
        if (file != null && !file.delete() && file.exists()) {
            String str = AbstractC1585a.f9392a;
            StringBuilder i2 = AbstractC2531fV.i("Could not delete error report: ");
            i2.append(file.getName());
            Log.w(str, i2.toString());
        }
    }

    public static File h(Context context, String str, String str2) {
        File file;
        if (str.equals("Crashpad")) {
            file = new File(context.getCacheDir() + "/" + str);
        } else {
            file = context.getDir(str, 0);
        }
        return new File(file, str2);
    }

    public static String q(File file) {
        if (!file.exists()) {
            return "NO_FILE";
        }
        try {
            FileReader fileReader = new FileReader(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        bufferedReader.close();
                        fileReader.close();
                        return readLine;
                    }
                    bufferedReader.close();
                    fileReader.close();
                    return null;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } catch (Throwable th2) {
                AbstractC0754Mh1.f8495a.a(th, th2);
            }
        } catch (Exception unused) {
            return null;
        }
        throw th;
        throw th;
    }

    public static String t(Display display) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        StringBuilder i2 = AbstractC2531fV.i("width=");
        i2.append(display.getWidth());
        i2.append('\n');
        i2.append("height=");
        i2.append(display.getHeight());
        i2.append('\n');
        i2.append("pixelFormat=");
        i2.append(display.getPixelFormat());
        i2.append('\n');
        i2.append("refreshRate=");
        i2.append(display.getRefreshRate());
        i2.append("fps");
        i2.append('\n');
        i2.append("metrics.density=x");
        i2.append(displayMetrics.density);
        i2.append('\n');
        i2.append("metrics.scaledDensity=x");
        i2.append(displayMetrics.scaledDensity);
        i2.append('\n');
        i2.append("metrics.widthPixels=");
        i2.append(displayMetrics.widthPixels);
        i2.append('\n');
        i2.append("metrics.heightPixels=");
        i2.append(displayMetrics.heightPixels);
        i2.append('\n');
        i2.append("metrics.xdpi=");
        i2.append(displayMetrics.xdpi);
        i2.append('\n');
        i2.append("metrics.ydpi=");
        i2.append(displayMetrics.ydpi);
        return i2.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0074 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0075 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(defpackage.TL... r13) {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UL.a(TL[]):boolean");
    }

    public final int b(Context context) {
        File[] k2 = k("acra-reports", ".stacktrace", ".temp_stacktrace");
        String m2 = m();
        if (m2 == null) {
            m2 = "n/a";
        }
        int i2 = 0;
        for (File file : k2) {
            if (i2 >= 5) {
                e(file);
            } else {
                String name = file.getName();
                String str = AbstractC1585a.f9392a;
                try {
                    C2141dB n2 = n(context, file, TL.ACRA_CRASH_REPORT, 1048576);
                    if (n2 != null) {
                        n2.put((Enum) XL0.ACRA_REPORT_TYPE, (Object) "ACRA_CRASH_REPORT");
                        n2.put((Enum) XL0.ACRA_REPORT_FILENAME, (Object) name);
                        n2.put((Enum) XL0.UPLOADED_BY_PROCESS, (Object) m2);
                        Log.i(str, "Sending file " + name);
                        r(n2);
                        e(file);
                    }
                    i2++;
                } catch (RuntimeException e2) {
                    Log.e(AbstractC1585a.f9392a, "Failed to send crash reports", e2);
                    e(file);
                } catch (IOException e3) {
                    Log.e(AbstractC1585a.f9392a, "Failed to load crash report for " + name, e3);
                    e(file);
                } catch (C1651aM0 e4) {
                    Log.e(AbstractC1585a.f9392a, "Failed to send crash report for " + name, e4);
                }
            }
        }
        return i2;
    }

    public final int c(Context context, TL tl) {
        boolean z;
        String str;
        C1651aM0 e2;
        File file;
        Throwable th;
        Log.i(AbstractC1585a.f9392a, "#checkAndSendCrashAttachments - start");
        int i2 = 0;
        if (new File(AbstractC2531fV.h(new StringBuilder(), tl.f8951J, "/.noupload")).exists()) {
            return 0;
        }
        File[] k2 = k(tl.f8951J, tl.M);
        if (k2.length > 0) {
            C2141dB dBVar = new C2141dB();
            dBVar.put((Enum) XL0.ACRA_REPORT_TYPE, (Object) tl.name());
            try {
                i("crash attachment", new SL(this, null), AbstractC1585a.d, dBVar, null, null);
            } catch (Exception e3) {
                StringBuilder i3 = AbstractC2531fV.i("retrieve exception: ");
                i3.append(e3.getMessage());
                try {
                    dBVar.f(XL0.REPORT_LOAD_THROW, i3.toString(), null);
                } catch (IOException unused) {
                }
            }
            String str2 = (String) dBVar.get(XL0.CUSTOM_DATA);
            int i4 = 0;
            for (File file2 : k2) {
                if (i4 >= 5) {
                    e(file2);
                } else {
                    String name = file2.getName();
                    Log.i(AbstractC1585a.f9392a, "#checkAndSendCrashAttachments - trying to send file: " + name);
                    try {
                        String o2 = o(file2);
                        if (o2 != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str2);
                            f(sb, "jni_exception", o2);
                            dBVar.put((Enum) XL0.CUSTOM_DATA, (Object) sb.toString());
                        } else {
                            dBVar.put((Enum) XL0.CUSTOM_DATA, (Object) str2);
                        }
                        file = file2;
                        str = name;
                        try {
                            C2141dB n2 = n(context, file2, tl, tl.K);
                            if (n2 != null) {
                                z = false;
                                dBVar.f(XL0.REPORT_ID, str.substring(0, str.lastIndexOf(46)), null);
                                dBVar.f(tl.L, (String) n2.get(tl.L), null);
                                dBVar.e(n2);
                                dBVar.f(XL0.EXCEPTION_CAUSE, "crash attachment", null);
                                dBVar.put((Enum) XL0.ACRA_REPORT_FILENAME, (Object) str);
                                r(dBVar);
                                e(file);
                                i4++;
                            }
                        } catch (C1651aM0 e4) {
                            e2 = e4;
                            String str3 = AbstractC1585a.f9392a;
                            StringBuilder k3 = AbstractC2531fV.k("Failed to send crash attachment report ", str, " because ");
                            k3.append(e2.toString());
                            Log.e(str3, k3.toString(), e2);
                            i2 = i4;
                            String str4 = AbstractC1585a.f9392a;
                            StringBuilder i5 = AbstractC2531fV.i("#checkAndSendCrashAttachments - finish, sent: ");
                            i5.append(Integer.toString(i2));
                            Log.i(str4, i5.toString());
                            return i2;
                        } catch (Throwable th2) {
                            th = th2;
                            String str5 = AbstractC1585a.f9392a;
                            StringBuilder k4 = AbstractC2531fV.k("Failed on crash attachment file ", str, " because ");
                            k4.append(th.toString());
                            Log.e(str5, k4.toString(), th);
                            e(file);
                            i2 = i4;
                            String str42 = AbstractC1585a.f9392a;
                            StringBuilder i52 = AbstractC2531fV.i("#checkAndSendCrashAttachments - finish, sent: ");
                            i52.append(Integer.toString(i2));
                            Log.i(str42, i52.toString());
                            return i2;
                        }
                    } catch (C1651aM0 e5) {
                        e2 = e5;
                        str = name;
                        String str32 = AbstractC1585a.f9392a;
                        StringBuilder k32 = AbstractC2531fV.k("Failed to send crash attachment report ", str, " because ");
                        k32.append(e2.toString());
                        Log.e(str32, k32.toString(), e2);
                        i2 = i4;
                        String str422 = AbstractC1585a.f9392a;
                        StringBuilder i522 = AbstractC2531fV.i("#checkAndSendCrashAttachments - finish, sent: ");
                        i522.append(Integer.toString(i2));
                        Log.i(str422, i522.toString());
                        return i2;
                    } catch (Throwable th3) {
                        th = th3;
                        file = file2;
                        str = name;
                        String str52 = AbstractC1585a.f9392a;
                        StringBuilder k42 = AbstractC2531fV.k("Failed on crash attachment file ", str, " because ");
                        k42.append(th.toString());
                        Log.e(str52, k42.toString(), th);
                        e(file);
                        i2 = i4;
                        String str4222 = AbstractC1585a.f9392a;
                        StringBuilder i5222 = AbstractC2531fV.i("#checkAndSendCrashAttachments - finish, sent: ");
                        i5222.append(Integer.toString(i2));
                        Log.i(str4222, i5222.toString());
                        return i2;
                    }
                }
                z = false;
            }
            i2 = i4;
        }
        String str42222 = AbstractC1585a.f9392a;
        StringBuilder i52222 = AbstractC2531fV.i("#checkAndSendCrashAttachments - finish, sent: ");
        i52222.append(Integer.toString(i2));
        Log.i(str42222, i52222.toString());
        return i2;
    }

    public int d() {
        int i2;
        int i3;
        boolean z = true;
        boolean a2 = a(TL.NATIVE_CRASH_REPORT);
        this.g = a2;
        if (!a2) {
            if (!a(TL.ACRA_CRASH_REPORT, TL.ANR_REPORT)) {
                z = false;
            }
        }
        if (z) {
            Log.i(AbstractC1585a.f9392a, "Crash reports found on application start");
            Context context = this.p;
            TL[] tlArr = b;
            synchronized (this) {
                i2 = 0;
                for (TL tl : tlArr) {
                    if (TL.ACRA_CRASH_REPORT == tl) {
                        i3 = b(context);
                    } else {
                        i3 = c(context, tl);
                    }
                    i2 += i3;
                }
                String str = AbstractC1585a.f9392a;
            }
            return i2;
        }
        Log.i(AbstractC1585a.f9392a, "No crash reports found on application start");
        return 0;
    }

    public final void f(StringBuilder sb, String str, String str2) {
        String str3 = null;
        String replace = str != null ? str.replace("\n", "\\n") : null;
        if (str2 != null) {
            str3 = str2.replace("\n", "\\n");
        }
        sb.append(replace);
        sb.append(" = ");
        sb.append(str3);
        sb.append("\n");
    }

    public final void g(StringBuilder sb, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            f(sb, (String) entry.getKey(), (String) entry.getValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0470, code lost:
        if (r5 != null) goto L_0x0477;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0475, code lost:
        if (r5 != null) goto L_0x0477;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017a A[SYNTHETIC, Splitter:B:103:0x017a] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x03a6  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x03b2  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x03ca  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x03e8  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0401  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0452 A[Catch:{ IOException -> 0x0475, NoSuchElementException -> 0x0470, all -> 0x0464 }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0453 A[Catch:{ IOException -> 0x0475, NoSuchElementException -> 0x0470, all -> 0x0464 }] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x046a  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x047d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0093 A[SYNTHETIC, Splitter:B:41:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void i(java.lang.String r23, java.lang.Throwable r24, defpackage.XL0[] r25, defpackage.C2141dB r26, java.io.Writer r27, java.util.Map r28) {
        /*
        // Method dump skipped, instructions count: 1755
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UL.i(java.lang.String, java.lang.Throwable, XL0[], dB, java.io.Writer, java.util.Map):void");
    }

    public final String j(Class cls, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toString(System.currentTimeMillis()));
        sb.append("-");
        sb.append(cls.getSimpleName());
        if (this.s != null) {
            StringBuilder i2 = AbstractC2531fV.i("-");
            i2.append(this.s);
            str2 = i2.toString();
        } else {
            str2 = "";
        }
        return AbstractC2531fV.h(sb, str2, str);
    }

    public File[] k(String str, String... strArr) {
        File file;
        if (this.p == null) {
            Log.e(AbstractC1585a.f9392a, "Trying to get ACRA reports but ACRA is not initialized.");
            return new File[0];
        }
        if (str.equals("Crashpad")) {
            file = new File(this.p.getCacheDir() + "/" + str);
        } else {
            file = this.p.getDir(str, 0);
        }
        if (file == null) {
            Log.w(AbstractC1585a.f9392a, "Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
            return new File[0];
        }
        String str2 = AbstractC1585a.f9392a;
        file.getAbsolutePath();
        File[] listFiles = file.listFiles(new QL(this, strArr));
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles, new RL(this));
        return listFiles;
    }

    public final String l() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return "yes";
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return "yes";
            }
        } catch (Exception e2) {
            Log.e(AbstractC1585a.f9392a, "Failed to find Superuser.pak", e2);
        }
        Map<String, String> map = System.getenv();
        if (map == null) {
            return "no";
        }
        for (String str2 : map.get("PATH").split(":")) {
            try {
                if (new File(AbstractC2531fV.f(str2, "/su")).exists()) {
                    return "yes";
                }
            } catch (Exception e3) {
                Log.e(AbstractC1585a.f9392a, "Failed to find su binary in the PATH", e3);
            }
        }
        return "no";
    }

    public final String m() {
        if (this.u) {
            return this.v;
        }
        this.v = null;
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.p.getSystemService("activity");
        if (activityManager == null) {
            return this.v;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return this.v;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                this.v = next.processName;
                break;
            }
        }
        this.u = true;
        return this.v;
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0356  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final defpackage.C2141dB n(android.content.Context r19, java.io.File r20, defpackage.TL r21, long r22) {
        /*
        // Method dump skipped, instructions count: 858
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UL.n(android.content.Context, java.io.File, TL, long):dB");
    }

    public final String o(File file) {
        String str;
        Exception e2;
        String str2 = null;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bytes = "----------------------------\r\n".getBytes();
            long length = file.length() - ((long) bytes.length);
            randomAccessFile.seek(length);
            if (randomAccessFile.readLine().equals("----------------------------")) {
                int min = (int) Math.min((long) (bytes.length + 20480), file.length());
                randomAccessFile.seek(length - ((long) min));
                byte[] bArr = new byte[min];
                randomAccessFile.readFully(bArr);
                int length2 = min - bytes.length;
                while (true) {
                    if (length2 < 0) {
                        break;
                    }
                    boolean z = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= bytes.length) {
                            z = true;
                            break;
                        } else if (bArr[length2 + i2] != bytes[i2]) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z) {
                        break;
                    }
                    length2--;
                }
                if (length2 != -1) {
                    int length3 = length2 + bytes.length;
                    str = new String(bArr, length3, min - length3);
                    try {
                        str2 = str.replace("\n", " ");
                    } catch (Exception e3) {
                        e2 = e3;
                    }
                } else {
                    throw new IllegalArgumentException("Boundary start not found in exception record.");
                }
            }
            randomAccessFile.close();
            return str2;
        } catch (Exception e4) {
            str = null;
            e2 = e4;
            String str3 = AbstractC1585a.f9392a;
            StringBuilder i3 = AbstractC2531fV.i("Failed on extracting JNI Exception from file ");
            i3.append(file.getName());
            i3.append(" because ");
            i3.append(e2.toString());
            Log.e(str3, i3.toString(), e2);
            return str;
        }
    }

    public final void p(C2141dB dBVar, Writer writer) {
        Map map;
        long j2;
        String str;
        String deviceId;
        synchronized (this.j) {
            if (this.j.isEmpty()) {
                this.j.put(XL0.BUILD, AbstractC2683gL0.a(Build.class));
                this.j.put(XL0.JAIL_BROKEN, l());
                this.j.put(XL0.INSTALLATION_ID, AbstractC1773b20.a(this.p));
                Map map2 = this.j;
                XL0 xl0 = XL0.TOTAL_MEM_SIZE;
                try {
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                    j2 = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
                } catch (Exception unused) {
                    j2 = -1;
                }
                map2.put(xl0, Long.toString(j2));
                if (this.k.a("android.permission.READ_PHONE_STATE") && (deviceId = ((TelephonyManager) this.p.getSystemService("phone")).getDeviceId()) != null) {
                    this.j.put(XL0.DEVICE_ID, deviceId);
                }
                this.j.put(XL0.DISPLAY, t(((WindowManager) this.p.getSystemService("window")).getDefaultDisplay()));
                this.j.put(XL0.ENVIRONMENT, AbstractC2683gL0.b(Environment.class));
                this.j.put(XL0.DEVICE_FEATURES, AbstractC5392wE.a(this.p));
                this.j.put(XL0.SETTINGS_SYSTEM, AbstractC2357eT0.b(this.p));
                this.j.put(XL0.SETTINGS_SECURE, AbstractC2357eT0.a(this.p));
                this.j.put(XL0.IS_LOW_RAM_DEVICE, Boolean.toString(((ActivityManager) this.p.getSystemService("activity")).isLowRamDevice()));
                Map map3 = this.j;
                XL0 xl02 = XL0.ANDROID_RUNTIME;
                String property = System.getProperty("java.boot.class.path");
                if (property != null) {
                    if (property.contains("/system/framework/core-libart.jar")) {
                        str = "ART";
                    } else if (property.contains("/system/framework/core.jar")) {
                        str = "DALVIK";
                    }
                    map3.put(xl02, str);
                }
                str = "UNKNOWN";
                map3.put(xl02, str);
            }
            map = this.j;
        }
        for (Map.Entry entry : map.entrySet()) {
            try {
                dBVar.f((XL0) entry.getKey(), (String) entry.getValue(), writer);
            } catch (IOException unused2) {
            }
        }
    }

    public final void r(C2141dB dBVar) {
        Iterator it = this.h.iterator();
        boolean z = false;
        while (it.hasNext()) {
            AbstractC2182dR dRVar = (AbstractC2182dR) it.next();
            try {
                ((C1854bY) dRVar).a(dBVar);
                z = true;
            } catch (C1651aM0 e2) {
                if (z) {
                    String str = AbstractC1585a.f9392a;
                    StringBuilder i2 = AbstractC2531fV.i("ReportSender of class ");
                    i2.append(dRVar.getClass().getName());
                    i2.append(" failed but other senders completed their task. ACRA will not send this report again.");
                    Log.w(str, i2.toString());
                } else {
                    throw e2;
                }
            }
        }
    }

    public final String s(Throwable th) {
        if (th == null) {
            th = new Exception("Report requested by developer");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        AbstractC0754Mh1.f8495a.d(th, printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:64|(4:65|66|67|68)|74|75|(1:77)(1:94)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x011d */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ea A[SYNTHETIC, Splitter:B:59:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void u(java.lang.Throwable r15) {
        /*
        // Method dump skipped, instructions count: 358
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.UL.u(java.lang.Throwable):void");
    }
}
