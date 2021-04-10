package defpackage;

import java.util.Objects;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* renamed from: yh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5802yh {

    /* renamed from: a  reason: collision with root package name */
    public static final CC f11693a = new CC(24, 0);

    public static WatcherImpl a(PW pw) {
        if (pw.l() == null) {
            return null;
        }
        Objects.requireNonNull((CoreImpl) pw.l());
        return new WatcherImpl();
    }

    public static boolean b(int i) {
        return (i & 1) > 0;
    }
}
