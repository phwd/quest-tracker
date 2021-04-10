package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.base.Callback;

/* renamed from: w4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5362w4 extends AbstractC4277pj {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4448qj f11516a;
    public Callback b;
    public final RecyclerView c;
    public final LinearLayout d;
    public final AbstractC0576Jj e = new C5022u4(this);

    public C5362w4(Context context, AbstractC4448qj qjVar) {
        this.f11516a = qjVar;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.f36840_resource_name_obfuscated_RES_2131623993, (ViewGroup) null);
        this.d = linearLayout;
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.sheet_item_list);
        this.c = recyclerView;
        recyclerView.getContext();
        recyclerView.t0(new LinearLayoutManager(1, false));
        recyclerView.s0(null);
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
        ((C5638xj) this.f11516a).r(this.e);
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.d;
    }

    @Override // defpackage.AbstractC4277pj
    public float i() {
        return -2.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        return -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f46630_resource_name_obfuscated_RES_2131951980;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f46640_resource_name_obfuscated_RES_2131951981;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f46650_resource_name_obfuscated_RES_2131951982;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f46660_resource_name_obfuscated_RES_2131951983;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return null;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        return this.c.computeVerticalScrollOffset();
    }

    @Override // defpackage.AbstractC4277pj
    public boolean s() {
        return false;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean t() {
        return false;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean u() {
        return false;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return false;
    }
}
