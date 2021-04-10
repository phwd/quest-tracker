package org.chromium.components.page_info;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PageInfoRowView extends FrameLayout {
    public static final /* synthetic */ int F = 0;
    public final ChromeImageView G = ((ChromeImageView) findViewById(R.id.page_info_row_icon));
    public final TextView H = ((TextView) findViewById(R.id.page_info_row_title));
    public final TextView I = ((TextView) findViewById(R.id.page_info_row_subtitle));

    public PageInfoRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.f40200_resource_name_obfuscated_RES_2131624329, (ViewGroup) this, true);
    }

    public void a(C0113Bv0 bv0) {
        int i = 8;
        setVisibility(bv0.f7772a ? 0 : 8);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.G.setImageResource(bv0.b);
        if (bv0.g) {
            int b = AbstractC4656rv1.b(displayMetrics, 2.0f);
            this.G.setPadding(b, b, b, b);
        }
        ChromeImageView chromeImageView = this.G;
        Resources resources = getResources();
        int i2 = bv0.c;
        if (i2 == 0) {
            i2 = R.color.f11220_resource_name_obfuscated_RES_2131099812;
        }
        chromeImageView.setImageTintList(ColorStateList.valueOf(resources.getColor(i2)));
        this.H.setText(bv0.d);
        this.H.setVisibility(bv0.d != null ? 0 : 8);
        CharSequence charSequence = bv0.e;
        this.I.setText(charSequence);
        TextView textView = this.I;
        if (charSequence != null) {
            i = 0;
        }
        textView.setVisibility(i);
        if (!(bv0.d == null || bv0.e == null)) {
            this.H.setPadding(0, 0, 0, AbstractC4656rv1.b(displayMetrics, 4.0f));
        }
        if (bv0.f != null) {
            setClickable(true);
            setFocusable(true);
            getChildAt(0).setOnClickListener(new View$OnClickListenerC0052Av0(bv0));
        }
    }
}
