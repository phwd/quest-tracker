package defpackage;

import J.N;

/* renamed from: hr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2935hr0 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC3960nr0 f10104a;

    public static AbstractC3960nr0 a() {
        if (f10104a == null) {
            AbstractC3960nr0 nr0 = (AbstractC3960nr0) N.Ml3x2KBq();
            if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                nr0 = new PG(nr0);
            }
            f10104a = nr0;
        }
        return f10104a;
    }
}
