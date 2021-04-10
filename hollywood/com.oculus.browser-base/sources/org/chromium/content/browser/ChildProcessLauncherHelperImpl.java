package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserManager;
import android.text.TextUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.base.task.PostTask;
import org.chromium.content.app.SandboxedProcessService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChildProcessLauncherHelperImpl {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10910a;
    public static LY0 b;
    public static LY0 c;
    public static AbstractC2412eo d;
    public static C1317Vo e;
    public static final Map f = new HashMap();
    public static AbstractC2412eo g;
    public static int h = -1;
    public static ComponentCallbacks2C5632xh i;
    public static boolean j;
    public final C1317Vo k;
    public final ComponentCallbacks2C5632xh l;
    public final boolean m;
    public final boolean n;
    public boolean o;
    public final AbstractC0159Co p;
    public final C0220Do q;
    public long r;
    public long s;
    public int t = 1;
    public boolean u;
    public final Object v = new Object();
    public int w;

    public ChildProcessLauncherHelperImpl(long j2, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, boolean z, boolean z2, IBinder iBinder) {
        List list;
        C0768Mo mo = new C0768Mo(this);
        this.p = mo;
        this.r = j2;
        this.m = z;
        this.n = z2;
        AbstractC2412eo b2 = b(ContextUtils.getApplicationContext(), z);
        Handler handler = LauncherThread.c;
        if (iBinder == null) {
            list = null;
        } else {
            list = Arrays.asList(iBinder);
        }
        this.q = new C0220Do(handler, mo, strArr, fileDescriptorInfoArr, b2, list);
        AbstractC1215Ty.a(strArr, "type");
        if (z) {
            this.k = e;
            this.l = i;
            this.w = -1;
            return;
        }
        this.k = null;
        this.l = null;
        this.w = -2;
    }

    public static ChildProcessLauncherHelperImpl a(int i2) {
        return (ChildProcessLauncherHelperImpl) f.get(Integer.valueOf(i2));
    }

    public static AbstractC2412eo b(Context context, boolean z) {
        AbstractC2412eo eoVar;
        String str;
        AbstractC2412eo doVar;
        String str2;
        if (!z) {
            if (g == null) {
                g = AbstractC2412eo.b(context, LauncherThread.c, null, ContextUtils.getApplicationContext().getPackageName(), "org.chromium.content.app.PrivilegedProcessService", "org.chromium.content.browser.NUM_PRIVILEGED_SERVICES", false, false, true);
            }
            return g;
        }
        if (d == null) {
            String packageName = ContextUtils.getApplicationContext().getPackageName();
            RunnableC0525Io io = new RunnableC0525Io();
            if (h != -1) {
                if (!TextUtils.isEmpty(null)) {
                    str2 = null;
                } else {
                    str2 = SandboxedProcessService.class.getName();
                }
                eoVar = new C2071co(new Handler(), io, packageName, str2, false, false, false, h, null);
            } else {
                Object obj = C5653xo.f11634a;
                if (AbstractC4952th.c()) {
                    Handler handler = LauncherThread.c;
                    AbstractC2412eo.a(context, packageName, "org.chromium.content.app.SandboxedProcessService");
                    BuildInfo buildInfo = AbstractC0456Hk.f8178a;
                    int i2 = Build.VERSION.SDK_INT;
                    boolean z2 = true;
                    boolean z3 = i2 == 29 && buildInfo.j.startsWith("OnePlus/");
                    if (i2 != 29 || z3 || ((UserManager) ContextUtils.getApplicationContext().getSystemService("user")).isSystemUser()) {
                        if (!SysUtils.isLowEndDevice() && !z3) {
                            z2 = false;
                        }
                        String str3 = z2 ? "1" : "0";
                        if (z2) {
                            str = null;
                        } else {
                            str = AbstractC2531fV.f("org.chromium.content.app.SandboxedProcessService", "1");
                        }
                        doVar = new Cdo(handler, io, packageName, AbstractC2531fV.f("org.chromium.content.app.SandboxedProcessService", str3), str, false, false, false, 100, null);
                    } else {
                        doVar = new C1729ao(handler, io, packageName, "org.chromium.content.app.SandboxedProcessService", false, false, false, 100, null);
                    }
                    eoVar = doVar;
                } else {
                    eoVar = AbstractC2412eo.b(context, LauncherThread.c, io, packageName, "org.chromium.content.app.SandboxedProcessService", "org.chromium.content.browser.NUM_SANDBOXED_SERVICES", false, false, false);
                }
            }
            d = eoVar;
            Object obj2 = C5653xo.f11634a;
            if (AbstractC4952th.c()) {
                e = new C1317Vo();
            } else {
                e = new C1317Vo(d.e());
            }
        }
        return d;
    }

    public static Bundle c(Bundle bundle) {
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        C2303e80 e80 = C2474f80.f9900a.i;
        if (!e80.b) {
            if (e80.c.m()) {
                Linker a2 = C2474f80.a(e80.c);
                synchronized (a2.b) {
                    a2.d = true;
                    a2.b();
                }
            }
            e80.b = true;
        }
        if (e80.c.m()) {
            Linker a3 = C2474f80.a(e80.c);
            synchronized (a3.b) {
                a3.b();
                long j2 = a3.e;
                if (j2 != 0) {
                    bundle.putLong("org.chromium.base.android.linker.base_load_address", j2);
                }
            }
        }
        return bundle;
    }

    public static ChildProcessLauncherHelperImpl createAndStart(long j2, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, boolean z) {
        boolean z2;
        String a2 = AbstractC1215Ty.a(strArr, "type");
        if ("renderer".equals(a2) || (!"gpu-process".equals(a2) && !"network".equals(AbstractC1215Ty.a(strArr, "service-sandbox-type")))) {
            z2 = true;
        } else {
            z2 = false;
        }
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = new ChildProcessLauncherHelperImpl(j2, strArr, fileDescriptorInfoArr, z2, z, "gpu-process".equals(a2) ? new BinderC4583rW() : null);
        childProcessLauncherHelperImpl.q.d(true, true);
        childProcessLauncherHelperImpl.s = System.currentTimeMillis();
        if (z2 && !f10910a) {
            f10910a = true;
            if (e != null && N.MyYLH6Fg()) {
                C1317Vo vo = e;
                vo.K = true;
                vo.e();
                if (!vo.L) {
                    vo.G.postDelayed(vo.f9106J, 1000);
                    vo.L = true;
                }
            }
        }
        return childProcessLauncherHelperImpl;
    }

    public static FileDescriptorInfo makeFdInfo(int i2, int i3, boolean z, long j2, long j3) {
        ParcelFileDescriptor fromFd;
        if (z) {
            fromFd = ParcelFileDescriptor.adoptFd(i3);
        } else {
            try {
                fromFd = ParcelFileDescriptor.fromFd(i3);
            } catch (IOException e2) {
                AbstractC1220Ua0.a("ChildProcLH", "Invalid FD provided for process connection, aborting connection.", e2);
                return null;
            }
        }
        return new FileDescriptorInfo(i2, fromFd, j2, j3);
    }

    public static void stop(int i2) {
        ChildProcessLauncherHelperImpl a2 = a(i2);
        if (a2 != null) {
            C5653xo xoVar = a2.q.g;
            int i3 = xoVar.r;
            xoVar.n();
            xoVar.i();
        }
    }

    public final void dumpProcessStack(int i2) {
        AbstractC5267vY vYVar;
        ChildProcessLauncherHelperImpl a2 = a(i2);
        if (a2 != null && (vYVar = a2.q.g.n) != null) {
            try {
                vYVar.w();
            } catch (RemoteException e2) {
                AbstractC1220Ua0.a("ChildProcessConn", "Failed to dump process stack.", e2);
            }
        }
    }

    public final void getTerminationInfoAndStop(long j2) {
        int i2;
        int[] iArr;
        String str;
        int i3;
        C5653xo xoVar = this.q.g;
        if (xoVar != null) {
            synchronized (this.v) {
                i2 = this.w;
            }
            Object obj = C5653xo.f11634a;
            synchronized (obj) {
                int[] iArr2 = xoVar.H;
                if (iArr2 != null) {
                    iArr = Arrays.copyOf(iArr2, 4);
                } else {
                    iArr = Arrays.copyOf(C5653xo.b, 4);
                    int i4 = xoVar.E;
                    if (i4 != 0) {
                        iArr[i4] = iArr[i4] - 1;
                    }
                }
            }
            synchronized (obj) {
                str = xoVar.f11635J;
            }
            if (str != null && !this.o) {
                this.o = true;
                PostTask.b(Zo1.b, new RunnableC0586Jo(str), 0);
            }
            synchronized (obj) {
                i3 = xoVar.F;
            }
            N.MJcoZ9pW(j2, i3, xoVar.h(), xoVar.f(), str != null, iArr[3], iArr[2], iArr[1], i2);
            LauncherThread.c.post(new RunnableC0647Ko(this));
        }
    }

    public final void setPriority(int i2, boolean z, boolean z2, boolean z3, long j2, boolean z4, boolean z5, int i3) {
        ComponentCallbacks2C5632xh xhVar;
        if (a(i2) != null) {
            C5653xo xoVar = this.q.g;
            boolean Mudil8Bg = N.Mudil8Bg("BackgroundMediaRendererHasModerateBinding");
            int i4 = ((!z || j2 != 0) && i3 != 2 && (!z2 || Mudil8Bg)) ? ((!z || j2 <= 0 || !z4) && !z5 && i3 != 1 && (!z2 || !Mudil8Bg) && !z3) ? 0 : 1 : 2;
            if (z && !this.u && (xhVar = this.l) != null && !(!xhVar.F.add(xoVar))) {
                xoVar.a();
            }
            this.u = z;
            if (!(this.t == i4 || i4 == 0)) {
                if (i4 == 1) {
                    xoVar.a();
                } else if (i4 == 2) {
                    if (!xoVar.g()) {
                        AbstractC1220Ua0.f("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(xoVar.r));
                    } else {
                        if (xoVar.z == 0) {
                            ((ServiceConnectionC2244dp) xoVar.w).a();
                            xoVar.o();
                        }
                        xoVar.z++;
                    }
                }
            }
            C1317Vo vo = this.k;
            if (vo != null) {
                int b2 = vo.b(xoVar);
                C1134So so = (C1134So) vo.I.get(b2);
                so.b = z;
                so.c = j2;
                so.d = z4;
                so.e = i3;
                vo.c(b2);
                ComponentCallbacks2C5632xh xhVar2 = this.l;
                if (xhVar2 != null) {
                    xhVar2.c();
                }
            }
            int i5 = this.t;
            if (!(i5 == i4 || i5 == 0)) {
                RunnableC0708Lo lo = new RunnableC0708Lo(i5, xoVar);
                if (System.currentTimeMillis() - this.s < 1000) {
                    LauncherThread.c.postDelayed(lo, 500);
                } else {
                    lo.run();
                }
            }
            this.t = i4;
        }
    }
}
