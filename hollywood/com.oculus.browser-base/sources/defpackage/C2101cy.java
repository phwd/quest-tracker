package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: cy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2101cy {

    /* renamed from: a  reason: collision with root package name */
    public final int f9733a;
    public final /* synthetic */ C2271dy b;

    public C2101cy(C2271dy dyVar, int i) {
        this.b = dyVar;
        this.f9733a = i;
    }

    public void a(int i) {
        Object obj = ThreadUtils.f10596a;
        C2271dy.a(this.f9733a);
        C2271dy.c(i);
        this.b.f9821a.put(Integer.valueOf(this.f9733a), Integer.valueOf(i));
        C2271dy dyVar = this.b;
        Objects.requireNonNull(dyVar);
        if ((dyVar.f9821a.size() == 4) && this.b.c != null) {
            PostTask.b(Zo1.f9374a, new RunnableC1930by(this), 0);
        }
    }
}
