package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: qP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4400qP0 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f11139a;
    public final Context b;
    public final Runnable c;
    public final Runnable d;
    public final Callback e;
    public final View$OnLayoutChangeListenerC5940zU0 f;
    public final Tab g;

    public C4400qP0(Context context, UH0 uh0, Runnable runnable, Runnable runnable2, Tab tab, View$OnLayoutChangeListenerC5940zU0 zu0, Callback callback) {
        this.d = runnable;
        this.c = runnable2;
        this.b = context;
        this.f11139a = uh0;
        this.g = tab;
        this.f = zu0;
        this.e = callback;
        uh0.m(AbstractC5590xP0.f11607a, new C4058oP0(this));
    }
}
