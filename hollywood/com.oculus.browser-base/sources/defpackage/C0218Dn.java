package defpackage;

import android.app.NotificationChannelGroup;
import android.content.res.Resources;

/* renamed from: Dn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0218Dn {

    /* renamed from: a  reason: collision with root package name */
    public final String f7909a;
    public final int b;

    public C0218Dn(String str, int i) {
        this.f7909a = str;
        this.b = i;
    }

    public NotificationChannelGroup a(Resources resources) {
        return new NotificationChannelGroup(this.f7909a, resources.getString(this.b));
    }
}
