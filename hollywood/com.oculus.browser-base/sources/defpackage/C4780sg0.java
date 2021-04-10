package defpackage;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import com.oculus.browser.R;

/* renamed from: sg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4780sg0 extends AbstractC3072ig0 {
    public final View d0;
    public final ImageView e0;
    public final ProgressBar f0;
    public final TextView g0;
    public final RelativeLayout h0;
    public final CheckBox i0;
    public final float j0;
    public final int k0;
    public final View.OnClickListener l0 = new View$OnClickListenerC4610rg0(this);
    public final /* synthetic */ C4950tg0 m0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4780sg0(C4950tg0 tg0, View view) {
        super(tg0.R, view, (ImageButton) view.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R.id.mr_cast_volume_slider));
        this.m0 = tg0;
        this.d0 = view;
        this.e0 = (ImageView) view.findViewById(R.id.mr_cast_route_icon);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_cast_route_progress_bar);
        this.f0 = progressBar;
        this.g0 = (TextView) view.findViewById(R.id.mr_cast_route_name);
        this.h0 = (RelativeLayout) view.findViewById(R.id.mr_cast_volume_layout);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.mr_cast_checkbox);
        this.i0 = checkBox;
        checkBox.setButtonDrawable(AbstractC4783sh0.f(tg0.R.Q, R.drawable.f33870_resource_name_obfuscated_RES_2131231427));
        AbstractC4783sh0.l(tg0.R.Q, progressBar);
        this.j0 = AbstractC4783sh0.d(tg0.R.Q);
        Resources resources = tg0.R.Q.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.f21070_resource_name_obfuscated_RES_2131165726, typedValue, true);
        this.k0 = (int) typedValue.getDimension(displayMetrics);
    }

    public void A(boolean z, boolean z2) {
        int i = 0;
        this.i0.setEnabled(false);
        this.d0.setEnabled(false);
        this.i0.setChecked(z);
        if (z) {
            this.e0.setVisibility(4);
            this.f0.setVisibility(0);
        }
        if (z2) {
            C4950tg0 tg0 = this.m0;
            RelativeLayout relativeLayout = this.h0;
            if (z) {
                i = this.k0;
            }
            tg0.s(relativeLayout, i);
        }
    }

    public boolean z(C2392eh0 eh0) {
        if (eh0.h()) {
            return true;
        }
        C2222dh0 b = this.m0.R.L.b(eh0);
        if (b != null) {
            C0141Cg0 cg0 = b.f9800a;
            if ((cg0 != null ? cg0.b : 1) == 3) {
                return true;
            }
        }
        return false;
    }
}
