package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Map;
import java.util.Objects;

/* renamed from: yc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5787yc implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0798Nc f11690a;

    public C5787yc(C0798Nc nc) {
        this.f11690a = nc;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        C4305ps0 ps0 = this.f11690a.e.G;
        Context context = viewGroup.getContext();
        Objects.requireNonNull(ps0);
        ViewGroup viewGroup2 = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.f40120_resource_name_obfuscated_RES_2131624321, (ViewGroup) null);
        RecyclerView recyclerView = ps0.a().b.b;
        if (recyclerView.getParent() != null) {
            Map map = AbstractC2417ep1.f9884a;
            ViewGroup viewGroup3 = (ViewGroup) recyclerView.getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(recyclerView);
            }
        }
        viewGroup2.addView(recyclerView);
        return viewGroup2;
    }
}
