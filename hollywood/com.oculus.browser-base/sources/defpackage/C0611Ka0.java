package defpackage;

import android.content.ComponentName;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: Ka0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0611Ka0 extends AbstractC2243do1 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8373a;
    public final /* synthetic */ C4649rt0 b;
    public final /* synthetic */ long c;
    public final /* synthetic */ C0672La0 d;

    public C0611Ka0(C0672La0 la0, C4649rt0 rt0, long j) {
        this.d = la0;
        this.b = rt0;
        this.c = j;
    }

    @Override // defpackage.AbstractC2243do1
    public void a(ComponentName componentName, boolean z) {
        if (!this.f8373a) {
            this.f8373a = true;
            C0672La0 la0 = this.d;
            C4649rt0 rt0 = this.b;
            long j = this.c;
            Objects.requireNonNull(la0);
            PostTask.b(Zo1.c, new RunnableC0550Ja0(la0, rt0, componentName, z, j), 0);
        }
    }
}
