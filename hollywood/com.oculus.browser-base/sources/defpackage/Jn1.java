package defpackage;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: Jn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Jn1 {

    /* renamed from: a  reason: collision with root package name */
    public final C4935tb0 f8313a;
    public final Q31 b;

    public Jn1(Context context, RecyclerView recyclerView, Q31 q31) {
        C4935tb0 tb0 = new C4935tb0();
        this.f8313a = tb0;
        this.b = q31;
        JW0 jw0 = new JW0(tb0);
        jw0.v(0, new En1(context), new Fn1());
        recyclerView.q0(jw0);
        recyclerView.t0(new LinearLayoutManager(0, false));
    }
}
