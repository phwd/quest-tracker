package defpackage;

import J.N;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.JNIUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.LegacyLinker;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.library_loader.ModernLinker;

/* renamed from: f80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2474f80 {

    /* renamed from: a  reason: collision with root package name */
    public static C2474f80 f9900a = new C2474f80();
    public volatile boolean b;
    public volatile int c;
    public boolean d;
    public boolean e;
    public boolean f;
    public int g;
    public final Object h = new Object();
    public final C2303e80 i = new C2303e80(this);
    public final Object j = new Object();
    public Linker k;
    public boolean l;
    public boolean m;
    public long n;

    public static Linker a(C2474f80 f80) {
        Objects.requireNonNull(f80);
        return f80.d(ContextUtils.getApplicationContext().getApplicationInfo());
    }

    public void b() {
        if (!f()) {
            c();
            h();
        }
    }

    public void c() {
        synchronized (this.j) {
            g(ContextUtils.getApplicationContext().getApplicationInfo(), false);
            e();
        }
    }

    public final Linker d(ApplicationInfo applicationInfo) {
        Linker linker;
        synchronized (this.j) {
            if (this.k == null) {
                String str = applicationInfo.className;
                boolean z = str != null && str.contains("incrementalinstall");
                if (!this.e || z) {
                    this.k = new LegacyLinker();
                } else {
                    this.k = new ModernLinker();
                }
                AbstractC1220Ua0.d("LibraryLoader", "Using linker: %s", this.k.getClass().getName());
            }
            linker = this.k;
        }
        return linker;
    }

    public final void e() {
        int i2;
        Map map;
        int i3;
        int i4;
        if (!this.b) {
            if (this.g == 1) {
                P21 f0 = P21.f0();
                try {
                    if (AbstractC3983nz.f10523a.getBoolean("reached_code_profiler_enabled", false)) {
                        i4 = 10000;
                    } else {
                        i4 = AbstractC3983nz.f10523a.getInt("reached_code_sampling_interval", 0);
                    }
                    f0.close();
                    if (i4 > 0) {
                        AbstractC1575Zv.e().a("enable-reached-code-profiler");
                        AbstractC1575Zv.e().b("reached-code-sampling-interval-us", Integer.toString(i4));
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            List list = null;
            if (!this.m) {
                AtomicReference atomicReference = AbstractC1575Zv.f9383a;
                AbstractC1575Zv zv = (AbstractC1575Zv) atomicReference.get();
                atomicReference.set(new C1514Yv(zv != null ? zv.d() : null));
                this.m = true;
            }
            if (!N.M81WqFvs(this.g)) {
                AbstractC1220Ua0.a("LibraryLoader", "error calling LibraryLoaderJni.get().libraryLoaded", new Object[0]);
                throw new C2840hG0(1);
            } else if ("89.0.4389.105".equals(N.M$HdV9JM())) {
                AbstractC1220Ua0.d("LibraryLoader", "Loaded native library version number \"%s\"", "89.0.4389.105");
                C5134ul ulVar = AbstractC3100ip1.f10165a;
                C0339Fm0 fm0 = new C0339Fm0();
                ulVar.f11431a.writeLock().lock();
                try {
                    ulVar.f = fm0;
                    if (!ulVar.b.isEmpty()) {
                        map = ulVar.b;
                        ulVar.b = new HashMap();
                        i2 = ulVar.c.getAndSet(0);
                    } else {
                        i2 = 0;
                        map = null;
                    }
                    if (!ulVar.d.isEmpty()) {
                        list = ulVar.d;
                        ulVar.d = new ArrayList();
                        i3 = ulVar.e;
                        ulVar.e = 0;
                    } else {
                        i3 = 0;
                    }
                    ulVar.f11431a.readLock().lock();
                    if (map != null) {
                        try {
                            ulVar.g(map, i2);
                        } catch (Throwable th2) {
                            ulVar.f11431a.readLock().unlock();
                            throw th2;
                        }
                    }
                    if (list != null) {
                        ulVar.h(list, i3);
                    }
                    ulVar.f11431a.readLock().unlock();
                    N.MFFzPOVw();
                    C5649xm1 xm1 = TraceEvent.G;
                    if (xm1 != null) {
                        xm1.i.set(true);
                        xm1.k.set(false);
                        if (xm1.j.get()) {
                            ThreadUtils.d(new RunnableC5309vm1(xm1));
                        }
                    }
                    this.b = true;
                    return;
                } finally {
                    ulVar.f11431a.writeLock().unlock();
                }
            } else {
                AbstractC1220Ua0.a("LibraryLoader", "Expected native library version number \"%s\", actual native library version number \"%s\"", "89.0.4389.105", N.M$HdV9JM());
                throw new C2840hG0(3);
            }
        } else {
            return;
        }
        throw th;
    }

    public boolean f() {
        return this.b && this.c == 2;
    }

    public void g(ApplicationInfo applicationInfo, boolean z) {
        if (this.c < 1) {
            try {
                TraceEvent j0 = TraceEvent.j0("LibraryLoader.loadMainDexAlreadyLocked");
                try {
                    if (!this.f) {
                        this.d = true;
                        this.e = false;
                        this.f = true;
                    }
                    long uptimeMillis = SystemClock.uptimeMillis();
                    if (!m() || z) {
                        j(applicationInfo, z);
                    } else {
                        i(applicationInfo, AbstractC5648xm0.f11632a[0]);
                    }
                    this.n = SystemClock.uptimeMillis() - uptimeMillis;
                    this.c = 1;
                    if (j0 != null) {
                        j0.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } catch (UnsatisfiedLinkError e2) {
                throw new C2840hG0(2, e2);
            }
        } else {
            return;
        }
        throw th;
    }

    public void h() {
        if (this.c != 2) {
            synchronized (this.h) {
                if (this.c != 2) {
                    TraceEvent j0 = TraceEvent.j0("LibraryLoader.loadNonMainDex");
                    try {
                        if (!JNIUtils.isSelectiveJniRegistrationEnabled()) {
                            N.MIOj213u();
                        }
                        this.c = 2;
                        if (j0 != null) {
                            j0.close();
                        }
                        return;
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                } else {
                    return;
                }
            }
        } else {
            return;
        }
        throw th;
    }

    public final void i(ApplicationInfo applicationInfo, String str) {
        Linker d2 = d(applicationInfo);
        String str2 = applicationInfo.sourceDir;
        if (str2 != null && str2.startsWith("/system/")) {
            String str3 = applicationInfo.sourceDir;
            d2.e(str3);
            AbstractC1220Ua0.d("LibraryLoader", " Loading %s from within %s", str, str3);
        } else {
            AbstractC1220Ua0.d("LibraryLoader", "Loading %s", str);
        }
        try {
            d2.c(str, true);
        } catch (UnsatisfiedLinkError unused) {
            AbstractC1220Ua0.f("LibraryLoader", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
            d2.c(str, false);
        }
    }

    public final void j(ApplicationInfo applicationInfo, boolean z) {
        String str = applicationInfo.packageName;
        TraceEvent j0 = TraceEvent.j0("LibraryLoader.preloadAlreadyLocked");
        if (j0 != null) {
            j0.close();
        }
        String str2 = applicationInfo.sourceDir;
        boolean z2 = str2 != null && str2.startsWith("/system/");
        String[] strArr = AbstractC5648xm0.f11632a;
        for (String str3 : strArr) {
            if (!z2) {
                System.loadLibrary(str3);
            } else {
                boolean is64Bit = Process.is64Bit();
                StringBuilder j2 = AbstractC2531fV.j(applicationInfo.sourceDir, "!/");
                j2.append(String.format(Locale.US, "lib/%s/%s%s", is64Bit ? "arm64-v8a" : "armeabi-v7a", "", System.mapLibraryName(str3)));
                String sb = j2.toString();
                AbstractC1220Ua0.d("LibraryLoader", "libraryName: %s", sb);
                System.load(sb);
            }
        }
    }

    public void k() {
        if (this.d) {
            synchronized (this.j) {
                N.MiAWbgCC(this.n);
            }
        }
    }

    public void l(int i2) {
        int i3 = this.g;
        if (i2 != i3) {
            if (i3 == 0) {
                this.g = i2;
            } else {
                throw new IllegalStateException(String.format("Trying to change the LibraryProcessType from %d to %d", Integer.valueOf(this.g), Integer.valueOf(i2)));
            }
        }
    }

    public final boolean m() {
        return this.d;
    }
}
