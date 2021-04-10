package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.components.autofill.AutofillSuggestion;

/* renamed from: Cd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0131Cd extends ArrayAdapter {
    public final Context F;
    public final Set G;
    public final boolean H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f7824J;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0131Cd(Context context, List list, Set set, boolean z) {
        super(context, z ? R.layout.f36930_resource_name_obfuscated_RES_2131624002 : R.layout.f36920_resource_name_obfuscated_RES_2131624001);
        boolean z2;
        this.F = context;
        addAll(list);
        this.G = set;
        if (getCount() > 0) {
            z2 = false;
            Objects.requireNonNull((AbstractC2335eJ) getItem(0));
        } else {
            z2 = true;
        }
        this.H = z2;
        this.I = context.getResources().getDimensionPixelSize(R.dimen.f16610_resource_name_obfuscated_RES_2131165280);
        this.f7824J = z;
    }

    public final ImageView a(ImageView imageView, AbstractC2335eJ eJVar) {
        int i = ((AutofillSuggestion) eJVar).d;
        if (i == 0) {
            imageView.setVisibility(8);
            return null;
        }
        imageView.setImageDrawable(AbstractC5544x8.a(this.F, i));
        imageView.setVisibility(0);
        return imageView;
    }

    public boolean areAllItemsEnabled() {
        return this.H;
    }

    public final TextView b(AbstractC2335eJ eJVar, View view) {
        TextView textView = (TextView) view.findViewById(R.id.dropdown_item_tag);
        String str = ((AutofillSuggestion) eJVar).c;
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return null;
        }
        textView.setText(str);
        textView.setVisibility(0);
        return textView;
    }

    public final TextView c(AbstractC2335eJ eJVar, View view) {
        TextView textView = (TextView) view.findViewById(R.id.dropdown_label);
        Objects.requireNonNull(eJVar);
        textView.setEnabled(true);
        textView.setText(((AutofillSuggestion) eJVar).f10810a);
        return textView;
    }

    public final TextView d(AbstractC2335eJ eJVar, View view) {
        TextView textView = (TextView) view.findViewById(R.id.dropdown_sublabel);
        String str = ((AutofillSuggestion) eJVar).b;
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return null;
        }
        textView.setText(str);
        textView.setVisibility(0);
        return textView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        if (view == null) {
            view = LayoutInflater.from(this.F).inflate(this.f7824J ? R.layout.f36930_resource_name_obfuscated_RES_2131624002 : R.layout.f36920_resource_name_obfuscated_RES_2131624001, (ViewGroup) null);
            view.setBackground(new C1994cJ(null));
        }
        AbstractC2335eJ eJVar = (AbstractC2335eJ) getItem(i);
        if (this.f7824J) {
            TextView c = c(eJVar, view);
            d(eJVar, view);
            b(eJVar, view);
            ImageView a2 = a((ImageView) view.findViewById(R.id.end_dropdown_icon), eJVar);
            if (a2 != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a2.getLayoutParams();
                marginLayoutParams.width = -2;
                marginLayoutParams.height = -2;
                a2.setLayoutParams(marginLayoutParams);
            }
            if (((AutofillSuggestion) eJVar).h) {
                c.setSingleLine(false);
                int dimensionPixelSize = this.F.getResources().getDimensionPixelSize(R.dimen.f16700_resource_name_obfuscated_RES_2131165289);
                ((LinearLayout) view.findViewById(R.id.dropdown_label_wrapper)).setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
            }
            return view;
        }
        int dimensionPixelSize2 = this.F.getResources().getDimensionPixelSize(R.dimen.f16600_resource_name_obfuscated_RES_2131165279);
        C1994cJ cJVar = (C1994cJ) view.getBackground();
        if (i == 0) {
            cJVar.f9597a.setColor(0);
        } else {
            int dimensionPixelSize3 = this.F.getResources().getDimensionPixelSize(R.dimen.f16590_resource_name_obfuscated_RES_2131165278);
            dimensionPixelSize2 += dimensionPixelSize3;
            Rect rect = cJVar.b;
            rect.set(0, 0, rect.right, dimensionPixelSize3);
            Set set = this.G;
            if (set == null || !set.contains(Integer.valueOf(i))) {
                i2 = this.F.getResources().getColor(R.color.f12200_resource_name_obfuscated_RES_2131099910);
            } else {
                i2 = this.F.getResources().getColor(R.color.f12190_resource_name_obfuscated_RES_2131099909);
            }
            cJVar.f9597a.setColor(i2);
        }
        TextView b = b(eJVar, view);
        if (b != null) {
            Resources resources = this.F.getResources();
            Objects.requireNonNull(eJVar);
            b.setTextSize(0, resources.getDimension(R.dimen.f25980_resource_name_obfuscated_RES_2131166217));
            dimensionPixelSize2 += this.F.getResources().getDimensionPixelSize(R.dimen.f16620_resource_name_obfuscated_RES_2131165281);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dropdown_label_wrapper);
        if (((AutofillSuggestion) eJVar).h) {
            dimensionPixelSize2 = -2;
        }
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize2, 1.0f));
        TextView c2 = c(eJVar, view);
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) eJVar;
        c2.setSingleLine(!autofillSuggestion.h);
        if (autofillSuggestion.h) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            int paddingStart = c2.getPaddingStart();
            int paddingEnd = c2.getPaddingEnd();
            int i3 = this.I;
            c2.setPaddingRelative(paddingStart, i3, paddingEnd, i3);
        }
        if (autofillSuggestion.i) {
            c2.setTypeface(null, 1);
        } else {
            c2.setTypeface(null, 0);
        }
        c2.setTextColor(this.F.getResources().getColor(autofillSuggestion.f == -1 ? R.color.f12870_resource_name_obfuscated_RES_2131099977 : R.color.f11650_resource_name_obfuscated_RES_2131099855));
        c2.setTextSize(0, this.F.getResources().getDimension(R.dimen.f25940_resource_name_obfuscated_RES_2131166213));
        TextView d = d(eJVar, view);
        if (d != null) {
            d.setTextSize(0, this.F.getResources().getDimension(R.dimen.f25980_resource_name_obfuscated_RES_2131166217));
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.start_dropdown_icon);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.end_dropdown_icon);
        if (autofillSuggestion.e) {
            imageView2.setVisibility(8);
        } else {
            imageView.setVisibility(8);
        }
        if (!autofillSuggestion.e) {
            imageView = imageView2;
        }
        ImageView a3 = a(imageView, eJVar);
        if (a3 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) a3.getLayoutParams();
            marginLayoutParams2.width = -2;
            marginLayoutParams2.height = -2;
            int dimensionPixelSize4 = this.F.getResources().getDimensionPixelSize(R.dimen.f18880_resource_name_obfuscated_RES_2131165507);
            marginLayoutParams2.setMarginStart(dimensionPixelSize4);
            marginLayoutParams2.setMarginEnd(dimensionPixelSize4);
            a3.setLayoutParams(marginLayoutParams2);
        }
        return view;
    }

    public boolean isEnabled(int i) {
        if (i < 0 || i >= getCount()) {
            return false;
        }
        Objects.requireNonNull((AbstractC2335eJ) getItem(i));
        return true;
    }
}
