package defpackage;

import com.oculus.browser.VrShellDelegate;
import com.oculus.browser.WebVRNavigationDescriptor;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* renamed from: rw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4659rw1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VrShellDelegate f11236a;

    public C4659rw1(VrShellDelegate vrShellDelegate) {
        this.f11236a = vrShellDelegate;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onPageLoadFailed, %s", tab.getUrl().h());
        VrShellDelegate vrShellDelegate = this.f11236a;
        Objects.requireNonNull(vrShellDelegate);
        Object[] objArr = new Object[3];
        objArr[0] = tab.getUrl().h();
        objArr[1] = Integer.valueOf(i);
        WebVRNavigationDescriptor webVRNavigationDescriptor = vrShellDelegate.U;
        objArr[2] = Integer.valueOf(webVRNavigationDescriptor != null ? webVRNavigationDescriptor.getStatus() : -1);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onLoadFailed of %s, err = %d, nav status = %d", objArr);
        WebVRNavigationDescriptor webVRNavigationDescriptor2 = vrShellDelegate.U;
        if (webVRNavigationDescriptor2 != null && webVRNavigationDescriptor2.b(tab)) {
            vrShellDelegate.forceExitVR();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onPageLoadFinished, %s", tab.getUrl().h());
        VrShellDelegate vrShellDelegate = this.f11236a;
        Objects.requireNonNull(vrShellDelegate);
        Object[] objArr = new Object[2];
        objArr[0] = tab.getUrl().h();
        WebVRNavigationDescriptor webVRNavigationDescriptor = vrShellDelegate.U;
        objArr[1] = Integer.valueOf(webVRNavigationDescriptor != null ? webVRNavigationDescriptor.getStatus() : -1);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onLoadFinished of %s, nav status = %d", objArr);
        WebVRNavigationDescriptor webVRNavigationDescriptor2 = vrShellDelegate.U;
        if (webVRNavigationDescriptor2 == null || !webVRNavigationDescriptor2.b(tab)) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onLoadFinished: seems like there's nothing to do...", new Object[0]);
            return;
        }
        vrShellDelegate.U.f = 3;
        vrShellDelegate.q();
        vrShellDelegate.A();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "VrShellDelegate onWebContentsSwapped", new Object[0]);
        VrShellDelegate vrShellDelegate = this.f11236a;
        if (vrShellDelegate.P == tab) {
            vrShellDelegate.z();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void l(Tab tab) {
        StringBuilder i = AbstractC2531fV.i("VrShellDelegate onCloseContents ");
        i.append(this.f11236a.P == tab);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", i.toString(), new Object[0]);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("VrShellDelegate onClosingStateChanged ");
        sb.append(z);
        sb.append(" ");
        sb.append(this.f11236a.P == tab);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", sb.toString(), new Object[0]);
        if (z) {
            VrShellDelegate vrShellDelegate = this.f11236a;
            if (vrShellDelegate.P == tab) {
                vrShellDelegate.forceExitVR();
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        if (tab.Q() != null || C3372kO0.W(tab)) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "VrShellDelegate onContentChanged", new Object[0]);
            this.f11236a.z();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        StringBuilder i = AbstractC2531fV.i("VrShellDelegate onDestroyed ");
        i.append(this.f11236a.P == tab);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", i.toString(), new Object[0]);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Navigation to %s finished, isMainFr %b, isSameDoc %b, isErr %b, hasCommitted %b, isFragment %b, pageTrans %d, errCode %d, http %d, navHandle = %s", navigationHandle.e.h(), Boolean.valueOf(navigationHandle.f10940a), Boolean.valueOf(navigationHandle.c), Boolean.valueOf(navigationHandle.g), Boolean.valueOf(navigationHandle.f), Boolean.valueOf(navigationHandle.h), navigationHandle.d, Integer.valueOf(navigationHandle.j), Integer.valueOf(navigationHandle.k), navigationHandle.toString());
        if (!navigationHandle.c) {
            this.f11236a.s(navigationHandle);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void v(Tab tab, NavigationHandle navigationHandle) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Navigation to %s redirected, isMainFr %b, isSameDoc %b, isErr %b, hasCommitted %b, isFragment %b, pageTrans %d, errCode %d, http %d, navHandle = %s", navigationHandle.e.h(), Boolean.valueOf(navigationHandle.f10940a), Boolean.valueOf(navigationHandle.c), Boolean.valueOf(navigationHandle.g), Boolean.valueOf(navigationHandle.f), Boolean.valueOf(navigationHandle.h), navigationHandle.d, Integer.valueOf(navigationHandle.j), Integer.valueOf(navigationHandle.k), navigationHandle.toString());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Navigation to %s started, isMainFr %b, isSameDoc %b, isErr %b, navHandle = %s", navigationHandle.e.h(), Boolean.valueOf(navigationHandle.f10940a), Boolean.valueOf(navigationHandle.c), Boolean.valueOf(navigationHandle.g), navigationHandle.toString());
        if (!navigationHandle.c) {
            VrShellDelegate vrShellDelegate = this.f11236a;
            Objects.requireNonNull(vrShellDelegate);
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onNavigationStart to %s, error page = %b, inVR = %b", navigationHandle.e.h(), Boolean.valueOf(navigationHandle.g), Boolean.valueOf(vrShellDelegate.L));
            if (!navigationHandle.f10940a) {
                return;
            }
            if (navigationHandle.g) {
                vrShellDelegate.forceExitVR();
                return;
            }
            WebVRNavigationDescriptor webVRNavigationDescriptor = new WebVRNavigationDescriptor(tab, navigationHandle);
            vrShellDelegate.U = webVRNavigationDescriptor;
            webVRNavigationDescriptor.f = 2;
            vrShellDelegate.q();
        }
    }
}
