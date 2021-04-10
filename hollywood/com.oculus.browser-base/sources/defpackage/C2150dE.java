package defpackage;

import java.util.LinkedList;
import java.util.Queue;
import org.chromium.base.ThreadUtils;

/* renamed from: dE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2150dE {

    /* renamed from: a  reason: collision with root package name */
    public static C2150dE f9760a;
    public final Queue b = new LinkedList();

    public static C2150dE b() {
        Object obj = ThreadUtils.f10596a;
        if (f9760a == null) {
            f9760a = new C2150dE();
        }
        return f9760a;
    }

    public void a(Runnable runnable) {
        Object obj = ThreadUtils.f10596a;
        this.b.add(runnable);
    }
}
