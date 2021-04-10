package defpackage;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: q0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4329q0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3148j50 f11106a;
    public final MK0 b;

    public AbstractC4329q0(String str, Drawable drawable, String str2, String str3, int i, int i2, MK0 mk0) {
        this.f11106a = new C3148j50(str, drawable, str2, str3, i, i2, this);
        this.b = mk0;
    }

    public abstract C4670s0 a();

    public void b(ViewGroup viewGroup) {
        RecyclerView recyclerView = (RecyclerView) viewGroup;
        MK0 mk0 = this.b;
        recyclerView.getContext();
        recyclerView.t0(new LinearLayoutManager(1, false));
        recyclerView.s0(null);
        if (mk0 != null) {
            recyclerView.i(mk0);
        }
    }
}
