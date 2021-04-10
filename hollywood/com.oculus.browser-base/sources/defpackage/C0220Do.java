package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Do  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0220Do {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f7911a;
    public final AbstractC0159Co b;
    public final String[] c;
    public final FileDescriptorInfo[] d;
    public final AbstractC2412eo e;
    public final List f;
    public C5653xo g;

    public C0220Do(Handler handler, AbstractC0159Co co, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, AbstractC2412eo eoVar, List list) {
        this.f7911a = handler;
        b();
        this.c = strArr;
        this.e = eoVar;
        this.b = co;
        this.d = fileDescriptorInfoArr;
        this.f = list;
    }

    public final boolean a(AbstractC5483wo woVar, boolean z, boolean z2) {
        Runnable runnable;
        Bundle bundle = new Bundle();
        Objects.requireNonNull((C0768Mo) this.b);
        ChildProcessLauncherHelperImpl.c(bundle);
        AbstractC2412eo eoVar = this.e;
        C5653xo c2 = eoVar.c(ContextUtils.getApplicationContext(), bundle, new C1559Zn(eoVar, woVar));
        this.g = c2;
        if (c2 == null) {
            if (!z2) {
                return false;
            }
            AbstractC2412eo eoVar2 = this.e;
            RunnableC5823yo yoVar = new RunnableC5823yo(this, woVar, z, z2);
            boolean isEmpty = eoVar2.b.isEmpty();
            eoVar2.b.add(yoVar);
            if (isEmpty && (runnable = eoVar2.f9880a) != null) {
                runnable.run();
            }
            return false;
        } else if (!z) {
            return true;
        } else {
            c();
            return true;
        }
    }

    public final boolean b() {
        return this.f7911a.getLooper() == Looper.myLooper();
    }

    public final void c() {
        Bundle bundle;
        C0098Bo bo = new C0098Bo(this);
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("org.chromium.base.process_launcher.extra.command_line", this.c);
        bundle2.putParcelableArray("org.chromium.base.process_launcher.extra.extraFiles", this.d);
        Objects.requireNonNull((C0768Mo) this.b);
        bundle2.putInt("com.google.android.apps.chrome.extra.cpu_count", N.MOiBJ1qS());
        bundle2.putLong("com.google.android.apps.chrome.extra.cpu_features", N.ML0T8q1U());
        C2303e80 e80 = C2474f80.f9900a.i;
        if (e80.c.m()) {
            Linker a2 = C2474f80.a(e80.c);
            synchronized (a2.b) {
                if (a2.g == 2) {
                    Linker.LibInfo libInfo = a2.c;
                    Objects.requireNonNull(libInfo);
                    bundle = new Bundle();
                    bundle.putParcelable("libinfo", libInfo);
                } else {
                    bundle = null;
                }
            }
            bundle2.putBundle("org.chromium.base.android.linker.shared_relros", bundle);
        }
        C5653xo xoVar = this.g;
        List list = this.f;
        if (xoVar.q) {
            AbstractC1220Ua0.f("ChildProcessConn", "Tried to setup a connection that already disconnected.", new Object[0]);
            bo.a(null);
            return;
        }
        try {
            TraceEvent.Y("ChildProcessConnection.setupConnection", null);
            xoVar.m = bo;
            xoVar.l = new C5313vo(bundle2, list);
            if (xoVar.p) {
                xoVar.d();
            }
        } finally {
            TraceEvent.f0("ChildProcessConnection.setupConnection");
        }
    }

    public boolean d(boolean z, boolean z2) {
        try {
            TraceEvent.Y("ChildProcessLauncher.start", null);
            C0037Ao ao = new C0037Ao(this, z, z2);
            C5653xo a2 = this.b.a(this.e, ao);
            this.g = a2;
            if (a2 != null) {
                c();
                return true;
            } else if (a(ao, z, z2) || z2) {
                TraceEvent.f0("ChildProcessLauncher.start");
                return true;
            } else {
                TraceEvent.f0("ChildProcessLauncher.start");
                return false;
            }
        } finally {
            TraceEvent.f0("ChildProcessLauncher.start");
        }
    }
}
