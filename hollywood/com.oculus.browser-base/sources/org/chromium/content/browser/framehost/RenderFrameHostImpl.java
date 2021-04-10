package org.chromium.content.browser.framehost;

import J.N;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RenderFrameHostImpl implements RenderFrameHost {

    /* renamed from: a  reason: collision with root package name */
    public long f10925a;
    public final RenderFrameHostDelegate b;
    public final boolean c;
    public final C3826n30 d;

    public RenderFrameHostImpl(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z, int i) {
        this.f10925a = j;
        this.b = renderFrameHostDelegate;
        this.c = z;
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        this.d = new C3826n30(new C1709ak0(new Rp1(coreImpl, i)));
        renderFrameHostDelegate.c(this);
    }

    public static RenderFrameHostImpl create(long j, RenderFrameHostDelegate renderFrameHostDelegate, boolean z, int i) {
        return new RenderFrameHostImpl(j, renderFrameHostDelegate, z, i);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public boolean a() {
        return this.c;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public boolean b() {
        long j = this.f10925a;
        if (j == 0) {
            return false;
        }
        return N.M6cbowZq(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public int c(String str, Origin origin) {
        long j = this.f10925a;
        if (j == 0) {
            return 21;
        }
        return N.M2ouq_qG(j, this, str, origin);
    }

    public final void clearNativePtr() {
        this.f10925a = 0;
        this.b.i0(this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public Origin d() {
        long j = this.f10925a;
        if (j == 0) {
            return null;
        }
        return (Origin) N.Mcdslkop(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public void e() {
        N.M$j92GA1(this.f10925a, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public boolean f(int i) {
        long j = this.f10925a;
        return j != 0 && N.MqDsGZSU(j, this, i);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public int g(String str, Origin origin) {
        long j = this.f10925a;
        if (j == 0) {
            return 21;
        }
        return N.M5dgGNo$(j, this, str, origin);
    }

    public final long getNativePointer() {
        return this.f10925a;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public C3826n30 h() {
        return this.d;
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public String i() {
        long j = this.f10925a;
        if (j == 0) {
            return null;
        }
        return N.MBg$jIAu(j, this);
    }

    @Override // org.chromium.content_public.browser.RenderFrameHost
    public void j(Callback callback) {
        long j = this.f10925a;
        if (j == 0) {
            ((ET0) callback).onResult(null);
        } else {
            N.MUV0o0vB(j, this, callback);
        }
    }
}
