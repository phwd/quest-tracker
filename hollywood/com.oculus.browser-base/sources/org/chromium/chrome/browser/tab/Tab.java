package org.chromium.chrome.browser.tab;

import android.content.Context;
import android.view.View;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface Tab {
    void A(AbstractC1404Xa1 xa1);

    void B();

    AbstractC5792yd1 C();

    void D(boolean z);

    LoadUrlParams E();

    int F();

    boolean G();

    float H();

    void I(AbstractC1404Xa1 xa1);

    boolean J();

    void K(boolean z);

    boolean L();

    Rr1 M();

    void N(int i);

    void O(WindowAndroid windowAndroid, C61 c61);

    boolean P();

    AbstractC5818ym0 Q();

    void R(boolean z);

    boolean a();

    View b();

    int c(LoadUrlParams loadUrlParams);

    boolean d();

    void destroy();

    void e();

    boolean f();

    boolean g();

    Context getContext();

    int getId();

    String getTitle();

    GURL getUrl();

    boolean h();

    WindowAndroid i();

    boolean isHidden();

    boolean isInitialized();

    boolean isNativePage();

    boolean isUserInteractable();

    void j();

    boolean k();

    WebContents l();

    int m();

    String n();

    boolean o();

    boolean p();

    void q();

    void r();

    @Deprecated
    String s();

    void t();

    AbstractViewGroup$OnHierarchyChangeListenerC1520Yy u();

    boolean v();

    boolean x();

    void y(int i);

    boolean z();
}
