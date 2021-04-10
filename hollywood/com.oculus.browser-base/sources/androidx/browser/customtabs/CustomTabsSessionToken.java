package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CustomTabsSessionToken {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC5947zY f9465a;
    public final PendingIntent b;
    public final C5216vC c;

    public CustomTabsSessionToken(AbstractC5947zY zYVar, PendingIntent pendingIntent) {
        C5216vC vCVar;
        if (zYVar == null && pendingIntent == null) {
            throw new IllegalStateException("CustomTabsSessionToken must have either a session id or a callback (or both).");
        }
        this.f9465a = zYVar;
        this.b = pendingIntent;
        if (zYVar == null) {
            vCVar = null;
        } else {
            vCVar = new C5216vC(this);
        }
        this.c = vCVar;
    }

    public static CustomTabsSessionToken b(Intent intent) {
        Bundle extras = intent.getExtras();
        AbstractC5947zY zYVar = null;
        if (extras == null) {
            return null;
        }
        IBinder binder = extras.getBinder("android.support.customtabs.extra.SESSION");
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("android.support.customtabs.extra.SESSION_ID");
        if (binder == null && pendingIntent == null) {
            return null;
        }
        if (binder != null) {
            zYVar = AbstractBinderC5777yY.c(binder);
        }
        return new CustomTabsSessionToken(zYVar, pendingIntent);
    }

    public final IBinder a() {
        AbstractC5947zY zYVar = this.f9465a;
        if (zYVar != null) {
            return ((C5607xY) zYVar).f11613a;
        }
        throw new IllegalStateException("CustomTabSessionToken must have valid binder or pending session");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomTabsSessionToken)) {
            return false;
        }
        CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) obj;
        PendingIntent pendingIntent = customTabsSessionToken.b;
        PendingIntent pendingIntent2 = this.b;
        boolean z = true;
        boolean z2 = pendingIntent2 == null;
        if (pendingIntent != null) {
            z = false;
        }
        if (z2 != z) {
            return false;
        }
        if (pendingIntent2 != null) {
            return pendingIntent2.equals(pendingIntent);
        }
        return a().equals(customTabsSessionToken.a());
    }

    public int hashCode() {
        PendingIntent pendingIntent = this.b;
        if (pendingIntent != null) {
            return pendingIntent.hashCode();
        }
        return a().hashCode();
    }
}
