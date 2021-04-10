package org.chromium.content_public.browser;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface WebContents extends Parcelable {
    void D();

    ViewAndroidDelegate F();

    void G(int i);

    RenderFrameHost H();

    WindowAndroid I();

    void J(String str, ViewAndroidDelegate viewAndroidDelegate, AbstractC2432eu1 eu1, WindowAndroid windowAndroid, C3466kx1 kx1);

    void K(int i, int i2, int i3, int i4);

    void L();

    RenderFrameHost N();

    void O();

    int P(String str, boolean z, int i, boolean z2, ImageDownloadCallback imageDownloadCallback);

    void Q(AbstractC6022zx1 zx1);

    boolean R();

    void V(boolean z);

    void W(boolean z);

    void X(int i, int i2, boolean z);

    boolean Y();

    boolean Z();

    boolean a();

    void a0();

    MessagePort[] b0();

    void c0(AbstractC6022zx1 zx1);

    boolean d();

    void d0(String str, String str2, String str3, MessagePort[] messagePortArr);

    void destroy();

    String e();

    void e0(WindowAndroid windowAndroid);

    NavigationController f();

    void f0(boolean z);

    boolean g();

    void g0();

    int getHeight();

    String getTitle();

    int getWidth();

    void j0(Rect rect);

    Rect l();

    void l0(int i, String str);

    int m();

    void m0();

    EventForwarder n0();

    void o();

    void o0(boolean z);

    void p(OverscrollRefreshHandler overscrollRefreshHandler);

    void p0(int i, int i2);

    int q();

    void q0();

    UL0 s();

    void setSmartClipResultHandler(Handler handler);

    void stop();

    GURL u();

    float v();

    boolean w();

    void z();
}
