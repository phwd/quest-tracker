package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: vA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5211vA0 extends BA0 {
    public GridLayout W;
    public TextView a0;
    public final List b0 = new ArrayList();
    public Runnable c0 = new RunnableC5041uA0(this);
    public Handler d0 = new Handler();

    public C5211vA0(Context context, String str, AbstractView$OnClickListenerC5891zA0 za0, String str2) {
        super(context, str, za0, null);
        this.a0.setText(str2);
    }

    @Override // defpackage.BA0
    public void a(LinearLayout linearLayout) {
        Context context = linearLayout.getContext();
        Context context2 = linearLayout.getContext();
        TextView textView = new TextView(context2);
        this.a0 = textView;
        textView.setTextAppearance(textView.getContext(), R.style.f71850_resource_name_obfuscated_RES_2132017758);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.a0.setTextAlignment(3);
        this.a0.setTextColor(context2.getResources().getColor(R.color.f12600_resource_name_obfuscated_RES_2131099950));
        layoutParams.setMarginStart(context2.getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520));
        layoutParams.setMarginEnd(context2.getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520));
        this.a0.setVisibility(4);
        LinearLayout linearLayout2 = this.R;
        linearLayout2.addView(this.a0, linearLayout2.getChildCount() - 1, layoutParams);
        GridLayout gridLayout = new GridLayout(context, null);
        this.W = gridLayout;
        gridLayout.q(2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 8388613;
        linearLayout.addView(this.W, layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.T.getLayoutParams();
        layoutParams3.width = 0;
        layoutParams3.weight = 1.0f;
    }

    @Override // defpackage.BA0
    public void d(int i) {
        if (i == 5) {
            e(TextUtils.TruncateAt.END, false, null, false);
            this.S.setMaxLines(3);
        } else {
            e(TextUtils.TruncateAt.END, true, null, false);
            this.S.setMaxLines(1);
        }
        this.f7721J = i;
        g();
    }

    @Override // defpackage.BA0
    public void g() {
        if (this.I) {
            this.W.setVisibility(this.f7721J == 5 ? 0 : 8);
            super.g();
        }
    }

    public final CharSequence h(String str, String str2, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str);
        spannableStringBuilder.append((CharSequence) " ");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) str2);
        if (z) {
            spannableStringBuilder.setSpan(new StyleSpan(1), length, str2.length() + length, 0);
        }
        return spannableStringBuilder;
    }
}
