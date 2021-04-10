package org.chromium.content.browser;

import J.N;
import android.os.Handler;
import android.os.Looper;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppWebMessagePort implements MessagePort {

    /* renamed from: a  reason: collision with root package name */
    public static final C0942Pj0 f10906a = new C0942Pj0(0);
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public SA f;
    public R9 g;
    public C2784gy h;

    public AppWebMessagePort(R9 r9) {
        Objects.requireNonNull(r9);
        SA sa = VA.f9067a;
        this.f = sa;
        this.g = r9;
        int MbOQIpGw = N.MbOQIpGw(r9.F);
        CoreImpl coreImpl = (CoreImpl) sa;
        Objects.requireNonNull(coreImpl);
        C2784gy gyVar = new C2784gy(new C1709ak0(new Rp1(coreImpl, MbOQIpGw)));
        r9.G = gyVar;
        gyVar.f10037J = r9;
        this.h = gyVar;
    }

    public static AppWebMessagePort[] createFromNativeBlinkMessagePortDescriptors(long[] jArr) {
        int length = jArr.length;
        AppWebMessagePort[] appWebMessagePortArr = new AppWebMessagePort[length];
        for (int i = 0; i < length; i++) {
            appWebMessagePortArr[i] = new AppWebMessagePort(new R9(jArr[i]));
        }
        return appWebMessagePortArr;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean a() {
        return this.c;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void b(FE0 fe0, Handler handler) {
        if (this.b || this.c) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        this.d = true;
        if (fe0 == null) {
            this.h.I = null;
        } else {
            this.h.I = new Q9(Looper.getMainLooper(), fe0);
        }
        if (!this.e) {
            C2784gy gyVar = this.h;
            gyVar.H.a(gyVar.G, RA.c, gyVar.F);
            this.e = true;
        }
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean c() {
        return this.d;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void close() {
        if (this.c) {
            throw new IllegalStateException("Port is already transferred");
        } else if (!this.b) {
            this.b = true;
            this.g.a();
            R9 r9 = this.g;
            long j = r9.F;
            if (j != 0) {
                N.MWkkqfl2(j);
                r9.b();
            }
            this.h.close();
            this.g = null;
            this.h = null;
        }
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void d(String str, MessagePort[] messagePortArr) {
        if (this.b || this.c) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        C1889bk0[] bk0Arr = new C1889bk0[0];
        this.d = true;
        C1719an1 an1 = new C1719an1();
        C4994tv tvVar = new C4994tv();
        an1.d = tvVar;
        byte[] Mk6SEKCp = N.Mk6SEKCp(str);
        C4442qh qhVar = new C4442qh();
        if (Mk6SEKCp.length <= 65536) {
            qhVar.f11638a = 0;
            qhVar.b = Mk6SEKCp;
        } else {
            SA sa = VA.f9067a;
            C4612rh rhVar = new C4612rh();
            EU0 eu0 = EU0.c;
            CoreImpl coreImpl = (CoreImpl) sa;
            Objects.requireNonNull(coreImpl);
            ByteBuffer a2 = coreImpl.a(8);
            a2.putInt(0, 8);
            a2.putInt(4, eu0.f9606a);
            ResultAnd resultAnd = (ResultAnd) N.MZ0nRPS0(coreImpl, a2, (long) Mk6SEKCp.length);
            if (resultAnd.f10995a == 0) {
                HU0 hu0 = new HU0(coreImpl, ((Integer) resultAnd.b).intValue());
                rhVar.d = hu0;
                rhVar.e = Mk6SEKCp.length;
                ByteBuffer B = hu0.B(0, (long) Mk6SEKCp.length, FU0.c);
                B.put(Mk6SEKCp);
                rhVar.d.w(B);
                qhVar.f11638a = 1;
                qhVar.c = rhVar;
            } else {
                throw new C5475wl0(resultAnd.f10995a);
            }
        }
        tvVar.d = qhVar;
        C4994tv tvVar2 = an1.d;
        tvVar2.e = new HS0[0];
        tvVar2.l = new ZP[0];
        tvVar2.f = null;
        an1.g = new GS0[0];
        an1.h = new C0509Ih[0];
        an1.e = bk0Arr;
        an1.f = new C1889bk0[0];
        this.h.b(an1.c(this.f, f10906a));
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean isClosed() {
        return this.b;
    }

    public final long releaseNativeMessagePortDescriptor() {
        this.c = true;
        this.g.a();
        this.h = null;
        R9 r9 = this.g;
        this.g = null;
        long j = r9.F;
        r9.b();
        return j;
    }
}
