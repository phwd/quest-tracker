package defpackage;

import android.util.Pair;
import java.util.LinkedList;
import org.chromium.base.task.PostTask;

/* renamed from: rn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4630rn {

    /* renamed from: a  reason: collision with root package name */
    public LinkedList f11221a = new LinkedList();
    public volatile boolean b;
    public final Runnable c = new RunnableC4289pn(this);

    public void a(C3070if1 if1, Runnable runnable) {
        if (!this.b) {
            this.f11221a.add(new Pair(if1, runnable));
            return;
        }
        throw new IllegalStateException("Must not call add() after start()");
    }

    public void b(boolean z) {
        if (!this.b) {
            this.b = true;
            if (!this.f11221a.isEmpty()) {
                if (z) {
                    PostTask.c((C3070if1) ((Pair) this.f11221a.peek()).first, new RunnableC4460qn(this));
                } else {
                    PostTask.b((C3070if1) ((Pair) this.f11221a.peek()).first, this.c, 0);
                }
            }
        } else {
            throw new IllegalStateException("Cannot call start() several times");
        }
    }
}
