package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: WY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WY {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9154a;
    public final Tm1 b;
    public final Tab c;

    public WY(Context context, Tab tab) {
        this.f9154a = context;
        this.b = Um1.a(Profile.a(tab.l()));
        this.c = tab;
    }
}
