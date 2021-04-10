package defpackage;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.Objects;

/* renamed from: zc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5957zc implements YH0 {

    /* renamed from: a  reason: collision with root package name */
    public final C4305ps0 f11754a;

    public C5957zc(C4305ps0 ps0) {
        this.f11754a = ps0;
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        C4305ps0 ps0 = this.f11754a;
        UH0 uh0 = (UH0) obj;
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        Objects.requireNonNull(ps0);
        RecyclerView recyclerView = ps0.a().b.b;
        if (recyclerView.getParent() != null) {
            Map map = AbstractC2417ep1.f9884a;
            ViewGroup viewGroup2 = (ViewGroup) recyclerView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(recyclerView);
            }
        }
        viewGroup.addView(recyclerView);
    }
}
