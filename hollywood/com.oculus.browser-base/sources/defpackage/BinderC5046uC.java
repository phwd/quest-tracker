package defpackage;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.browser.customtabs.CustomTabsSessionToken;

/* renamed from: uC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC5046uC extends AY {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WY0 f11397a;

    public BinderC5046uC(WY0 wy0) {
        this.f11397a = wy0;
    }

    public final PendingIntent c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("android.support.customtabs.extra.SESSION_ID");
        bundle.remove("android.support.customtabs.extra.SESSION_ID");
        return pendingIntent;
    }

    public final boolean d(AbstractC5947zY zYVar, PendingIntent pendingIntent) {
        CustomTabsSessionToken customTabsSessionToken = new CustomTabsSessionToken(zYVar, pendingIntent);
        try {
            C4876tC tCVar = new C4876tC(this, customTabsSessionToken);
            synchronized (this.f11397a.F) {
                ((C5607xY) zYVar).f11613a.linkToDeath(tCVar, 0);
                this.f11397a.F.put(((C5607xY) zYVar).f11613a, tCVar);
            }
            return this.f11397a.a(customTabsSessionToken);
        } catch (RemoteException unused) {
            return false;
        }
    }
}
