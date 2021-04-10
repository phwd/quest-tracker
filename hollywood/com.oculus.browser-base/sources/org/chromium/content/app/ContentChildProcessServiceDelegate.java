package org.chromium.content.app;

import J.N;
import android.content.Context;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.Surface;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.base.JNIUtils;
import org.chromium.base.UnguessableToken;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.common.SurfaceWrapper;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentChildProcessServiceDelegate implements AbstractC1903bp {

    /* renamed from: a  reason: collision with root package name */
    public KY f10905a;
    public int b;
    public long c;
    public SparseArray d;

    public ContentChildProcessServiceDelegate() {
        if (!BuildInfo.a()) {
            Thread.setDefaultUncaughtExceptionHandler(new C3664m60());
        }
    }

    public final void a() {
        C2474f80 f80 = C2474f80.f9900a;
        synchronized (f80.j) {
            f80.e();
        }
        N.M0zXFFiu(this);
    }

    public void b(Context context) {
        boolean z;
        C2474f80 f80 = C2474f80.f9900a;
        synchronized (f80.j) {
            z = f80.l;
        }
        if (z) {
            a();
            return;
        }
        JNIUtils.f10587a = Boolean.TRUE;
        C2474f80 f802 = C2474f80.f9900a;
        C2303e80 e80 = f802.i;
        if (e80.c.m()) {
            synchronized (e80.c.j) {
                Linker a2 = C2474f80.a(e80.c);
                long j = e80.f9835a;
                synchronized (a2.b) {
                    a2.d = false;
                    a2.b();
                    a2.e = j;
                }
            }
        }
        e80.b = true;
        synchronized (f802.j) {
            if (f802.c != 0) {
                if (context != ContextUtils.getApplicationContext()) {
                    throw new IllegalStateException("Attempt to load again from alternate context.");
                }
            }
            f802.g(context.getApplicationInfo(), false);
        }
        f802.h();
        f802.k();
        a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.os.Bundle r4, java.util.List r5) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.app.ContentChildProcessServiceDelegate.c(android.os.Bundle, java.util.List):void");
    }

    public final void forwardSurfaceForSurfaceRequest(UnguessableToken unguessableToken, Surface surface) {
        KY ky = this.f10905a;
        if (ky == null) {
            AbstractC1220Ua0.a("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return;
        }
        try {
            ky.H(unguessableToken, surface);
        } catch (RemoteException e) {
            AbstractC1220Ua0.a("ContentCPSDelegate", "Unable to call forwardSurfaceForSurfaceRequest: %s", e);
        } finally {
            surface.release();
        }
    }

    public final SurfaceWrapper getViewSurface(int i) {
        KY ky = this.f10905a;
        if (ky == null) {
            AbstractC1220Ua0.a("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return null;
        }
        try {
            return ky.S(i);
        } catch (RemoteException e) {
            AbstractC1220Ua0.a("ContentCPSDelegate", "Unable to call getViewSurface: %s", e);
            return null;
        }
    }

    public final void setFileDescriptorsIdsToKeys(int[] iArr, String[] strArr) {
        this.d = new SparseArray();
        for (int i = 0; i < iArr.length; i++) {
            this.d.put(iArr[i], strArr[i]);
        }
    }
}
