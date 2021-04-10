package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: eD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2318eD0 extends Z2 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9840a;
    public final Tab b;
    public final /* synthetic */ C2831hD0 c;

    public C2318eD0(C2831hD0 hd0, Activity activity, Tab tab, C2148dD0 dd0) {
        this.c = hd0;
        this.f9840a = activity;
        this.b = tab;
    }

    @Override // defpackage.Z2
    public void b(Tab tab) {
        if (this.b != tab) {
            this.c.f(this.f9840a, 4);
        }
    }
}
