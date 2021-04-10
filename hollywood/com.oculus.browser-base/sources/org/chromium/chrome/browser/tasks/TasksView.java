package org.chromium.chrome.browser.tasks;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.ntp.IncognitoDescriptionView;
import org.chromium.components.browser_ui.widget.CoordinatorLayoutForPointer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TasksView extends CoordinatorLayoutForPointer {
    public final Context g0;
    public FrameLayout h0;
    public AppBarLayout i0;
    public H7 j0;
    public C3205jQ0 k0;
    public IncognitoDescriptionView l0;
    public View.OnClickListener m0;
    public boolean n0;
    public boolean o0;
    public CompoundButton.OnCheckedChangeListener p0;
    public int q0 = 0;
    public View.OnClickListener r0;
    public Yo1 s0;

    public TasksView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g0 = context;
    }

    public final void A(int i, int i2, View view, int i3) {
        int c = AbstractC4089od0.c((-i3) - i, 0, i2);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int i4 = i - c;
        if (layoutParams.height != i4) {
            layoutParams.height = i4;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, c, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setLayoutParams(layoutParams);
        }
    }

    public void B(boolean z) {
        this.n0 = z;
        IncognitoDescriptionView incognitoDescriptionView = this.l0;
        if (incognitoDescriptionView != null) {
            incognitoDescriptionView.H = z;
            incognitoDescriptionView.O.setVisibility(z ? 0 : 8);
            incognitoDescriptionView.a();
        }
    }

    public void C(int i) {
        int i2;
        String str;
        this.q0 = i;
        IncognitoDescriptionView incognitoDescriptionView = this.l0;
        if (incognitoDescriptionView != null) {
            int i3 = 0;
            boolean z = i != 0;
            boolean z2 = !z;
            incognitoDescriptionView.P.setEnabled(z2);
            ImageView imageView = incognitoDescriptionView.Q;
            if (!z) {
                i3 = 8;
            }
            imageView.setVisibility(i3);
            incognitoDescriptionView.R.setEnabled(z2);
            incognitoDescriptionView.S.setEnabled(z2);
            Resources resources = incognitoDescriptionView.getContext().getResources();
            StringBuilder sb = new StringBuilder();
            sb.append(resources.getString(R.string.f55880_resource_name_obfuscated_RES_2131952905));
            if (!z) {
                incognitoDescriptionView.S.setText(sb.toString());
                return;
            }
            if (i == 1) {
                i2 = R.drawable.f29660_resource_name_obfuscated_RES_2131231006;
                str = resources.getString(R.string.f54350_resource_name_obfuscated_RES_2131952752);
            } else if (i == 3) {
                i2 = R.drawable.f34890_resource_name_obfuscated_RES_2131231529;
                str = resources.getString(R.string.f55830_resource_name_obfuscated_RES_2131952900);
            } else {
                return;
            }
            incognitoDescriptionView.Q.setImageResource(i2);
            sb.append("\n");
            sb.append(str);
            incognitoDescriptionView.S.setText(sb.toString());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.s0.b();
        y();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.h0 = (FrameLayout) findViewById(R.id.carousel_tab_switcher_container);
        this.k0 = new C3205jQ0(getContext(), this);
        this.i0 = (AppBarLayout) findViewById(R.id.task_surface_header);
        this.s0 = new Yo1(this);
        C2777gv1.b(this.i0, this.s0, 0, getResources().getDimensionPixelSize(R.dimen.f23150_resource_name_obfuscated_RES_2131165934));
        y();
        G7 g7 = (G7) findViewById(R.id.scroll_component_container).getLayoutParams();
        g7.f8065a = 1;
        Q21 q21 = AbstractC2793h01.f10042a;
        if (q21.c().equals("omniboxonly") || q21.c().equals("trendyterms")) {
            String c = AbstractC2793h01.h.c();
            char c2 = 65535;
            int hashCode = c.hashCode();
            if (hashCode != -988146728) {
                if (hashCode != 115029) {
                    if (hashCode == 107947501 && c.equals("quick")) {
                        c2 = 0;
                    }
                } else if (c.equals("top")) {
                    c2 = 2;
                }
            } else if (c.equals("pinned")) {
                c2 = 1;
            }
            if (c2 == 0) {
                g7.f8065a = 5;
            } else if (c2 == 1) {
                g7.f8065a = 0;
            }
            ((LinearLayout.LayoutParams) g7).bottomMargin = AbstractC4656rv1.a(getContext(), 4.0f);
        }
        TextView textView = (TextView) findViewById(R.id.tab_switcher_title_description);
        AbstractC3153j7.i(textView, R.style.f72260_resource_name_obfuscated_RES_2132017799);
        AbstractC3153j7.i((TextView) findViewById(R.id.more_tabs), R.style.f72170_resource_name_obfuscated_RES_2132017790);
        int dimensionPixelSize = this.g0.getResources().getDimensionPixelSize(R.dimen.f16960_resource_name_obfuscated_RES_2131165315);
        int paddingTop = textView.getPaddingTop();
        int paddingEnd = textView.getPaddingEnd();
        int paddingBottom = textView.getPaddingBottom();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        textView.setPaddingRelative(dimensionPixelSize, paddingTop, paddingEnd, paddingBottom);
    }

    public final void y() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i0.findViewById(R.id.mv_tiles_container).getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.h0.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f17690_resource_name_obfuscated_RES_2131165388);
        if (getResources().getConfiguration().orientation == 2) {
            marginLayoutParams.leftMargin = dimensionPixelSize;
            marginLayoutParams.rightMargin = dimensionPixelSize;
            marginLayoutParams2.leftMargin = dimensionPixelSize;
            marginLayoutParams2.rightMargin = dimensionPixelSize;
            return;
        }
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
        marginLayoutParams2.leftMargin = getResources().getDimensionPixelSize(R.dimen.f25360_resource_name_obfuscated_RES_2131166155);
        marginLayoutParams2.rightMargin = 0;
    }

    public ViewGroup z() {
        return (ViewGroup) findViewById(R.id.tasks_surface_body);
    }
}
