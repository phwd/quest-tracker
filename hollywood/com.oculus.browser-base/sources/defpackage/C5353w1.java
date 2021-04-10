package defpackage;

import J.N;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* renamed from: w1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5353w1 extends AbstractC4277pj {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11512a = {R.id.account_picker_header_title, R.id.account_picker_header_title, R.id.account_picker_header_title, R.id.account_picker_signin_in_progress_title, R.id.incognito_interstitial_title, R.id.account_picker_general_error_title, R.id.account_picker_auth_error_title};
    public final Activity b;
    public final C4673s1 c;
    public final View d;
    public final ViewFlipper e;
    public final RecyclerView f;
    public final View g;
    public final ButtonCompat h;

    public C5353w1(Activity activity, C4673s1 s1Var) {
        this.b = activity;
        this.c = s1Var;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.f36650_resource_name_obfuscated_RES_2131623974, (ViewGroup) null);
        this.d = inflate;
        ViewFlipper viewFlipper = (ViewFlipper) inflate.findViewById(R.id.account_picker_state_view_flipper);
        this.e = viewFlipper;
        w(viewFlipper, 0, R.id.account_picker_state_no_account);
        w(viewFlipper, 1, R.id.account_picker_state_collapsed);
        w(viewFlipper, 2, R.id.account_picker_state_expanded);
        w(viewFlipper, 3, R.id.account_picker_state_signin_in_progress);
        w(viewFlipper, 4, R.id.account_picker_state_incognito_interstitial);
        w(viewFlipper, 5, R.id.account_picker_state_general_error);
        w(viewFlipper, 6, R.id.account_picker_state_auth_error);
        RecyclerView recyclerView = (RecyclerView) viewFlipper.getChildAt(2).findViewById(R.id.account_picker_account_list);
        this.f = recyclerView;
        recyclerView.getContext();
        recyclerView.t0(new LinearLayoutManager(1, false));
        this.g = viewFlipper.getChildAt(1).findViewById(R.id.account_picker_selected_account);
        ButtonCompat buttonCompat = (ButtonCompat) viewFlipper.getChildAt(1).findViewById(R.id.account_picker_dismiss_button);
        this.h = buttonCompat;
        if ("hide".equals(N.MMltG$kc("MobileIdentityConsistencyVar", "dismiss_button"))) {
            buttonCompat.setVisibility(8);
        }
        x(viewFlipper.getChildAt(0), R.string.f62070_resource_name_obfuscated_RES_2131953524);
        x(viewFlipper.getChildAt(5), R.string.f62040_resource_name_obfuscated_RES_2131953521);
        x(viewFlipper.getChildAt(6), R.string.f47000_resource_name_obfuscated_RES_2131952017);
    }

    public static void w(ViewFlipper viewFlipper, int i, int i2) {
        if (viewFlipper.getChildAt(i).getId() != i2) {
            throw new IllegalArgumentException(AbstractC2531fV.w("Match failed with ViewState:", i));
        }
    }

    public static void x(View view, int i) {
        ((ButtonCompat) view.findViewById(R.id.account_picker_continue_as_button)).setText(i);
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.d;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return -1.0f;
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
        return R.string.f49160_resource_name_obfuscated_RES_2131952233;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f62010_resource_name_obfuscated_RES_2131953518;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f62020_resource_name_obfuscated_RES_2131953519;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f62020_resource_name_obfuscated_RES_2131953519;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return null;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean r() {
        C4673s1 s1Var = this.c;
        UH0 uh0 = s1Var.H;
        SH0 sh0 = AbstractC5183v1.e;
        int f2 = uh0.f(sh0);
        if (f2 == 2) {
            s1Var.H.l(sh0, 1);
            return true;
        } else if (f2 != 4) {
            return false;
        } else {
            s1Var.H.l(sh0, 2);
            return true;
        }
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }
}
