package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: mE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3687mE extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3858nE f10407a;

    public C3687mE(C3858nE nEVar, AbstractC3516lE lEVar) {
        this.f10407a = nEVar;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        C3858nE nEVar = this.f10407a;
        if (nEVar.b.N && navigationHandle.f10940a) {
            nEVar.h();
        }
    }
}
