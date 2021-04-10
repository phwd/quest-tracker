package org.chromium.content.browser.webcontents;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content.browser.ContentUiEventHandler;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebContentsImpl extends Vy1 implements WebContents, RenderFrameHostDelegate {
    public static final Parcelable.Creator CREATOR = new C5512wx1();
    public static UUID F = UUID.randomUUID();
    public final List G = new ArrayList();
    public long H;
    public NavigationController I;

    /* renamed from: J  reason: collision with root package name */
    public WebContentsObserverProxy f10935J;
    public SmartClipCallback K;
    public EventForwarder L;
    public TL0 M;
    public C3466kx1 N;
    public String O;
    public boolean P;
    public Throwable Q;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SmartClipCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Handler f10936a;

        public SmartClipCallback(Handler handler) {
            this.f10936a = handler;
        }
    }

    public WebContentsImpl(long j, NavigationController navigationController) {
        this.H = j;
        this.I = navigationController;
    }

    public static void addAccessibilityNodeAsChild(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotNode accessibilitySnapshotNode2) {
        accessibilitySnapshotNode.s.add(accessibilitySnapshotNode2);
    }

    public static void addRenderFrameHostToArray(RenderFrameHost[] renderFrameHostArr, int i, RenderFrameHost renderFrameHost) {
        renderFrameHostArr[i] = renderFrameHost;
    }

    public static void addToBitmapList(List list, Bitmap bitmap) {
        list.add(bitmap);
    }

    public static WebContentsImpl create(long j, NavigationController navigationController) {
        return new WebContentsImpl(j, navigationController);
    }

    public static AccessibilitySnapshotNode createAccessibilitySnapshotNode(int i, int i2, int i3, int i4, boolean z, String str, int i5, int i6, float f, boolean z2, boolean z3, boolean z4, boolean z5, String str2) {
        AccessibilitySnapshotNode accessibilitySnapshotNode = new AccessibilitySnapshotNode(str, str2);
        if (((double) f) >= 0.0d) {
            accessibilitySnapshotNode.j = i5;
            accessibilitySnapshotNode.k = i6;
            accessibilitySnapshotNode.f = f;
            accessibilitySnapshotNode.l = z2;
            accessibilitySnapshotNode.m = z3;
            accessibilitySnapshotNode.n = z4;
            accessibilitySnapshotNode.o = z5;
            accessibilitySnapshotNode.i = true;
        }
        accessibilitySnapshotNode.f10937a = i;
        accessibilitySnapshotNode.b = i2;
        accessibilitySnapshotNode.c = i3;
        accessibilitySnapshotNode.d = i4;
        accessibilitySnapshotNode.e = z;
        return accessibilitySnapshotNode;
    }

    public static List createBitmapList() {
        return new ArrayList();
    }

    public static RenderFrameHost[] createRenderFrameHostArray(int i) {
        return new RenderFrameHost[i];
    }

    public static Rect createSize(int i, int i2) {
        return new Rect(0, 0, i, i2);
    }

    public static void createSizeAndAddToList(List list, int i, int i2) {
        list.add(new Rect(0, 0, i, i2));
    }

    public static List createSizeList() {
        return new ArrayList();
    }

    public static void onAccessibilitySnapshot(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotCallback accessibilitySnapshotCallback) {
        accessibilitySnapshotCallback.a(accessibilitySnapshotNode);
    }

    public static void onEvaluateJavaScriptResult(String str, JavaScriptCallback javaScriptCallback) {
        javaScriptCallback.a(str);
    }

    public static void onSmartClipDataExtracted(String str, String str2, int i, int i2, int i3, int i4, SmartClipCallback smartClipCallback) {
        Rect rect = new Rect(i, i2, i3, i4);
        TL0 tl0 = WebContentsImpl.this.M;
        rect.offset(0, (int) (tl0.k / tl0.j));
        Bundle bundle = new Bundle();
        bundle.putString("url", WebContentsImpl.this.u().h());
        bundle.putString("title", WebContentsImpl.this.getTitle());
        bundle.putString("text", str);
        bundle.putString("html", str2);
        bundle.putParcelable("rect", rect);
        Message obtain = Message.obtain(smartClipCallback.f10936a, 0);
        obtain.setData(bundle);
        obtain.sendToTarget();
    }

    public static void setAccessibilitySnapshotSelection(AccessibilitySnapshotNode accessibilitySnapshotNode, int i, int i2) {
        accessibilitySnapshotNode.p = true;
        accessibilitySnapshotNode.q = i;
        accessibilitySnapshotNode.r = i2;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void D() {
        r0();
        N.MlfwWHGJ(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public ViewAndroidDelegate F() {
        C5852yx1 yx1 = this.N.f10317a;
        if (yx1 == null) {
            return null;
        }
        return yx1.b;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void G(int i) {
        r0();
        N.MkBVGSRs(this.H, this, i);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public RenderFrameHost H() {
        r0();
        return (RenderFrameHost) N.MT2cFaRc(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public WindowAndroid I() {
        r0();
        return (WindowAndroid) N.MunY3e38(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void J(String str, ViewAndroidDelegate viewAndroidDelegate, AbstractC2432eu1 eu1, WindowAndroid windowAndroid, C3466kx1 kx1) {
        this.O = str;
        this.N = kx1;
        C5852yx1 yx1 = new C5852yx1(null);
        yx1.f11713a = new Rr1();
        this.N.f10317a = yx1;
        TL0 tl0 = new TL0();
        this.M = tl0;
        tl0.b = 0.0f;
        tl0.f8952a = 0.0f;
        tl0.g = 1.0f;
        this.P = true;
        r0();
        this.N.f10317a.b = viewAndroidDelegate;
        N.MgyWdCWB(this.H, this, viewAndroidDelegate);
        e0(windowAndroid);
        C3115iu1 c = C3115iu1.c(this);
        GestureListenerManagerImpl.s0(c.F).f10912J = eu1;
        ((ContentUiEventHandler) c.F.v0(ContentUiEventHandler.class, AbstractC1337Vy.f9118a)).G = eu1;
        this.M.j = windowAndroid.I.e;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void K(int i, int i2, int i3, int i4) {
        if (this.K != null) {
            r0();
            TL0 tl0 = this.M;
            float f = tl0.j;
            N.MHF1rPTW(this.H, this, this.K, (int) (((float) i) / f), (int) (((float) (i2 - ((int) tl0.k))) / f), (int) (((float) i3) / f), (int) (((float) i4) / f));
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void L() {
        r0();
        N.MQnLkNkP(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public RenderFrameHost N() {
        r0();
        return (RenderFrameHost) N.MjidYpBx(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void O() {
        r0();
        SparseArray sparseArray = WebContentsAccessibilityImpl.F;
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) v0(WebContentsAccessibilityImpl.class, AbstractC4662rx1.f11237a);
        if (webContentsAccessibilityImpl != null) {
            webContentsAccessibilityImpl.o();
        }
        SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(this);
        if (r != null) {
            r.restoreSelectionPopupsIfNecessary();
        }
        N.MtakfqIH(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int P(String str, boolean z, int i, boolean z2, ImageDownloadCallback imageDownloadCallback) {
        r0();
        return N.Mi3V1mlO(this.H, this, str, z, i, z2, imageDownloadCallback);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void Q(AbstractC6022zx1 zx1) {
        WebContentsObserverProxy webContentsObserverProxy = this.f10935J;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.H.c(zx1);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean R() {
        r0();
        return N.M93b11tE(this.H, this);
    }

    @Override // defpackage.XF, defpackage.WF
    public void U(float f) {
        long j = this.H;
        if (j != 0) {
            this.M.j = f;
            N.MqhGkzSt(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void V(boolean z) {
        r0();
        N.M4fkbrQM(this.H, this, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void W(boolean z) {
        long j = this.H;
        if (j != 0) {
            N.M6R_ShZs(j, this, z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void X(int i, int i2, boolean z) {
        N.MjgOFo_o(this.H, this, i, i2, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean Y() {
        r0();
        return N.MS0xMYL9(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean Z() {
        r0();
        return N.MkIL2bW9(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean a() {
        r0();
        return N.MZbfAARG(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void a0() {
        r0();
        N.MSOsA4Ii(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public MessagePort[] b0() {
        C0942Pj0 pj0 = AppWebMessagePort.f10906a;
        long[] MZ2WfWkn = N.MZ2WfWkn();
        return new AppWebMessagePort[]{new AppWebMessagePort(new R9(MZ2WfWkn[0])), new AppWebMessagePort(new R9(MZ2WfWkn[1]))};
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public void c(RenderFrameHostImpl renderFrameHostImpl) {
        this.G.add(renderFrameHostImpl);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void c0(AbstractC6022zx1 zx1) {
        if (this.f10935J == null) {
            this.f10935J = new WebContentsObserverProxy(this);
        }
        this.f10935J.H.b(zx1);
    }

    public final void clearNativePtr() {
        this.Q = new RuntimeException("clearNativePtr");
        this.H = 0;
        this.I = null;
        WebContentsObserverProxy webContentsObserverProxy = this.f10935J;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.destroy();
            this.f10935J = null;
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean d() {
        r0();
        return N.MtSTkEp2(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void d0(String str, String str2, String str3, MessagePort[] messagePortArr) {
        for (MessagePort messagePort : messagePortArr) {
            if (messagePort.isClosed() || messagePort.a()) {
                throw new IllegalStateException("Port is already closed or transferred");
            } else if (messagePort.c()) {
                throw new IllegalStateException("Port is already started");
            }
        }
        if (str3.equals("*")) {
            str3 = "";
        }
        N.MZFXk0el(this.H, this, str, str2, str3, messagePortArr);
    }

    public int describeContents() {
        return 0;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void destroy() {
        if (ThreadUtils.i()) {
            long j = this.H;
            if (j != 0) {
                N.MxxzO9Pe(j);
                return;
            }
            return;
        }
        throw new IllegalStateException("Attempting to destroy WebContents on non-UI thread");
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String e() {
        r0();
        return N.MrqMRJsG(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void e0(WindowAndroid windowAndroid) {
        r0();
        N.MOKG_Wbb(this.H, this, windowAndroid);
        Zy1.t0(this).x(windowAndroid);
        WebContentsObserverProxy webContentsObserverProxy = this.f10935J;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.a(windowAndroid);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public NavigationController f() {
        return this.I;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void f0(boolean z) {
        long j = this.H;
        if (j != 0) {
            N.M9QxNoTJ(j, this, z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean g() {
        long j = this.H;
        return j == 0 || N.M5A4vDoy(j, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void g0() {
        r0();
        N.M6c69Eq5(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int getHeight() {
        r0();
        return N.MRVeP4Wk(this.H, this);
    }

    public final long getNativePointer() {
        return this.H;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getTitle() {
        r0();
        return N.M7OgjMU8(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int getWidth() {
        r0();
        return N.MB0i5_ri(this.H, this);
    }

    @Override // defpackage.XF, defpackage.WF
    public void h0(int i) {
        int i2;
        long j = this.H;
        if (j != 0) {
            if (i == 0) {
                i2 = 0;
            } else if (i == 1) {
                i2 = 90;
            } else if (i == 2) {
                i2 = 180;
            } else if (i == 3) {
                i2 = -90;
            } else {
                throw new IllegalStateException("Display.getRotation() shouldn't return that value");
            }
            N.MlztHl3v(j, this, i2);
        }
    }

    @Override // org.chromium.content.browser.framehost.RenderFrameHostDelegate
    public void i0(RenderFrameHostImpl renderFrameHostImpl) {
        this.G.remove(renderFrameHostImpl);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void j0(Rect rect) {
        long j = this.H;
        if (j != 0) {
            N.MtjP03pj(j, this, rect.top, rect.left, rect.bottom, rect.right);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public Rect l() {
        r0();
        return (Rect) N.MN9JdEk5(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void l0(int i, String str) {
        r0();
        N.MseJ7A4a(this.H, this, i, str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int m() {
        r0();
        return N.MGZCJ6jO(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void m0() {
        long j = this.H;
        if (j != 0) {
            N.M0iG1Oc2(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public EventForwarder n0() {
        if (this.L == null) {
            r0();
            this.L = (EventForwarder) N.MJJFrmZs(this.H, this);
        }
        return this.L;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void o() {
        long j = this.H;
        if (j != 0) {
            N.MgcGzQax(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void o0(boolean z) {
        r0();
        N.M12SiBFk(this.H, this, z);
    }

    public final void onDownloadImageFinished(ImageDownloadCallback imageDownloadCallback, int i, int i2, String str, List list, List list2) {
        imageDownloadCallback.a(i, i2, str, list, list2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void p(OverscrollRefreshHandler overscrollRefreshHandler) {
        r0();
        N.MTTB8znA(this.H, this, overscrollRefreshHandler);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void p0(int i, int i2) {
        r0();
        N.M7tTrJ_X(this.H, this, i, i2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int q() {
        r0();
        return N.MOzDgqoz(this.H);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void q0() {
        long j = this.H;
        if (j != 0) {
            N.Mzsx8Sk2(j, this);
        }
    }

    public final void r0() {
        if (this.H == 0) {
            throw new IllegalStateException("Native WebContents already destroyed", this.Q);
        }
    }

    public void s0() {
        r0();
        N.MpfMxfut(this.H, this);
    }

    public final void setMediaSession(MediaSessionImpl mediaSessionImpl) {
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void setSmartClipResultHandler(Handler handler) {
        if (handler == null) {
            this.K = null;
        } else {
            this.K = new SmartClipCallback(handler);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void stop() {
        r0();
        N.M$$25N5$(this.H, this);
    }

    public void t0() {
        r0();
        N.MhIiCaN7(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public GURL u() {
        r0();
        return (GURL) N.M8927Uaf(this.H, this);
    }

    public Context u0() {
        WindowAndroid I2 = I();
        if (I2 != null) {
            return (Context) I2.f11022J.get();
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public float v() {
        r0();
        return N.MoQgY_pw(this.H, this);
    }

    public Qr1 v0(Class cls, AbstractC5682xx1 xx1) {
        Rr1 rr1;
        C5852yx1 yx1;
        if (!this.P) {
            return null;
        }
        C3466kx1 kx1 = this.N;
        if (kx1 == null || (yx1 = kx1.f10317a) == null) {
            rr1 = null;
        } else {
            rr1 = yx1.f11713a;
        }
        if (rr1 == null) {
            return null;
        }
        Qr1 c = rr1.c(cls);
        if (c == null) {
            c = rr1.e(cls, (Qr1) xx1.a(this));
        }
        return (Qr1) cls.cast(c);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean w() {
        r0();
        return N.MZao1OQG(this.H, this);
    }

    /* renamed from: w0 */
    public RenderWidgetHostViewImpl s() {
        RenderWidgetHostViewImpl renderWidgetHostViewImpl;
        long j = this.H;
        if (j == 0 || (renderWidgetHostViewImpl = (RenderWidgetHostViewImpl) N.Mj9slq6o(j, this)) == null || renderWidgetHostViewImpl.a()) {
            return null;
        }
        return renderWidgetHostViewImpl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong("version", 0);
        bundle.putParcelable("processguard", new ParcelUuid(F));
        bundle.putLong("webcontents", this.H);
        parcel.writeBundle(bundle);
    }

    public void x0() {
        r0();
        N.MYRJ_nNk(this.H, this);
    }

    public void y0() {
        r0();
        N.MgbVQff0(this.H, this);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void z() {
        r0();
        SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(this);
        if (r != null) {
            r.hidePopupsAndPreserveSelection();
        }
        N.MHNkuuGQ(this.H, this);
    }
}
