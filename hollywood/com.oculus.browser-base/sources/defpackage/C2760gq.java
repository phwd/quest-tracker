package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: gq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2760gq {

    /* renamed from: a  reason: collision with root package name */
    public static C2760gq f10024a;
    public final NE0 b = new NE0();
    public C5157us1 c;
    public boolean d;
    public boolean e;

    public C2760gq() {
        Objects.requireNonNull(AppHooks.get());
        this.c = new C5157us1();
    }

    public static C2760gq a() {
        Object obj = ThreadUtils.f10596a;
        if (f10024a == null) {
            f10024a = new C2760gq();
        }
        return f10024a;
    }
}
