package defpackage;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;

/* renamed from: Tv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1209Tv extends BaseAdapter implements View.OnClickListener {
    public Context F;
    public ColorSuggestion[] G;
    public AbstractC1148Sv H;

    public View$OnClickListenerC1209Tv(Context context, ColorSuggestion[] colorSuggestionArr) {
        this.F = context;
        this.G = colorSuggestionArr;
    }

    public int getCount() {
        return ((this.G.length + 4) - 1) / 4;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (view instanceof LinearLayout) {
            linearLayout = (LinearLayout) view;
        } else {
            linearLayout = new LinearLayout(this.F);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            linearLayout.setOrientation(0);
            linearLayout.setBackgroundColor(-1);
            int dimensionPixelOffset = this.F.getResources().getDimensionPixelOffset(R.dimen.f17370_resource_name_obfuscated_RES_2131165356);
            for (int i2 = 0; i2 < 4; i2++) {
                View view2 = new View(this.F);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dimensionPixelOffset, 1.0f);
                layoutParams.setMarginStart(-1);
                if (i2 == 3) {
                    layoutParams.setMarginEnd(-1);
                }
                view2.setLayoutParams(layoutParams);
                view2.setBackgroundResource(R.drawable.f28870_resource_name_obfuscated_RES_2131230927);
                linearLayout.addView(view2);
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            View childAt = linearLayout.getChildAt(i3);
            int i4 = (i * 4) + i3;
            ColorSuggestion[] colorSuggestionArr = this.G;
            if (i4 >= colorSuggestionArr.length) {
                childAt.setTag(null);
                childAt.setContentDescription(null);
                childAt.setVisibility(4);
            } else {
                childAt.setTag(colorSuggestionArr[i4]);
                childAt.setVisibility(0);
                ColorSuggestion colorSuggestion = this.G[i4];
                ((GradientDrawable) ((LayerDrawable) childAt.getBackground()).findDrawableByLayerId(R.id.color_button_swatch)).setColor(colorSuggestion.f10843a);
                String str = colorSuggestion.b;
                if (TextUtils.isEmpty(str)) {
                    str = String.format("#%06X", Integer.valueOf(colorSuggestion.f10843a & 16777215));
                }
                childAt.setContentDescription(str);
                childAt.setOnClickListener(this);
            }
        }
        return linearLayout;
    }

    public void onClick(View view) {
        ColorSuggestion colorSuggestion;
        if (this.H != null && (colorSuggestion = (ColorSuggestion) view.getTag()) != null) {
            ((ColorPickerSimple) this.H).H.a(colorSuggestion.f10843a);
        }
    }
}
