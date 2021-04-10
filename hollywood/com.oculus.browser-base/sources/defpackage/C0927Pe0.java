package defpackage;

import android.media.MediaRoute2Info;
import java.util.function.Predicate;

/* renamed from: Pe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0927Pe0 implements Predicate {
    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        return !((MediaRoute2Info) obj).isSystemRoute();
    }
}
