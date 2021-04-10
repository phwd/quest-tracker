package org.chromium.chrome.browser.ntp.snippets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SectionHeaderView extends LinearLayout implements View.OnClickListener {
    public TextView F;
    public TextView G;
    public ListMenuButton H;
    public C3891nR0 I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10714J;
    public boolean K;

    public SectionHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, FJ0.z0, 0, 0);
        try {
            this.K = obtainStyledAttributes.getBoolean(0, false);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public final void a() {
        C3891nR0 nr0;
        C4935tb0 tb0;
        R80 r80;
        AbstractC4228pP.a(4);
        ListMenuButton listMenuButton = this.H;
        if (listMenuButton != null && (tb0 = (nr0 = this.I).f10491J) != null && (r80 = nr0.K) != null) {
            C4914tR0 tr0 = new C4914tR0(this, new C1237Ug(listMenuButton.getContext(), tb0, r80));
            ListMenuButton listMenuButton2 = this.H;
            listMenuButton2.d();
            listMenuButton2.M = tr0;
            ListMenuButton listMenuButton3 = this.H;
            listMenuButton3.O = true;
            listMenuButton3.e();
        }
    }

    public void b() {
        int i;
        C3891nR0 nr0 = this.I;
        if (nr0 != null) {
            this.F.setText(nr0.I);
            if (this.I.v()) {
                if (!this.f10714J) {
                    this.G.setText(this.I.M ? R.string.f52680_resource_name_obfuscated_RES_2131952585 : R.string.f61800_resource_name_obfuscated_RES_2131953497);
                }
                int i2 = 0;
                if (this.K) {
                    boolean z = !this.I.M;
                    if (z) {
                        i = getResources().getDimensionPixelSize(R.dimen.f19410_resource_name_obfuscated_RES_2131165560);
                    } else {
                        setBackgroundResource(0);
                        i = 0;
                    }
                    ValueAnimator ofInt = ValueAnimator.ofInt(getPaddingLeft(), i);
                    ofInt.addUpdateListener(new C4404qR0(this));
                    ofInt.addListener(new C4574rR0(this, z));
                    ofInt.setDuration(200L);
                    ofInt.start();
                    return;
                }
                if (!this.I.M) {
                    i2 = R.drawable.f29410_resource_name_obfuscated_RES_2131230981;
                }
                setBackgroundResource(i2);
            }
        }
    }

    public void onClick(View view) {
        this.I.w();
        AbstractC4228pP.a(3);
        if (this.I.M) {
            AbstractC3535lK0.a("Suggestions.ExpandableHeader.Expanded");
        } else {
            AbstractC3535lK0.a("Suggestions.ExpandableHeader.Collapsed");
        }
        G31.a();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (TextView) findViewById(R.id.header_title);
        this.G = (TextView) findViewById(R.id.header_status);
        ListMenuButton listMenuButton = (ListMenuButton) findViewById(R.id.header_menu);
        this.H = listMenuButton;
        int i = 0;
        boolean z = listMenuButton != null;
        this.f10714J = z;
        if (z) {
            listMenuButton.setOnClickListener(new View$OnClickListenerC4062oR0(this));
            if (this.K) {
                i = getResources().getDimensionPixelSize(R.dimen.f19420_resource_name_obfuscated_RES_2131165561);
            }
            post(new RunnableC4233pR0(this, i));
        }
    }
}
