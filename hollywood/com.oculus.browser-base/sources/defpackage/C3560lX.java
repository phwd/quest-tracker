package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: lX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3560lX extends C3389kX {
    public C3560lX(Context context, int i, int i2, List list, Object obj) {
        super(context, i, i2, list, obj);
    }

    @Override // defpackage.C3389kX
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        if (i == getCount() - 1) {
            if (this.G == null) {
                this.G = (TextView) dropDownView.findViewById(this.F);
            }
            this.G.setCompoundDrawablesWithIntrinsicBounds(C0636Ki1.b(getContext(), R.drawable.f34580_resource_name_obfuscated_RES_2131231498, R.color.f11230_resource_name_obfuscated_RES_2131099813), (Drawable) null, (Drawable) null, (Drawable) null);
            this.G.setCompoundDrawablePadding(getContext().getResources().getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519));
            AbstractC3153j7.i(this.G, R.style.f71250_resource_name_obfuscated_RES_2132017698);
            TextView textView = this.G;
            Map map = AbstractC2417ep1.f9884a;
            textView.setTypeface(Typeface.create("sans-serif-medium", 0));
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            dropDownView.setPaddingRelative(dropDownView.getPaddingStart(), dropDownView.getPaddingTop(), dropDownView.getPaddingEnd(), getContext().getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520));
        }
        return dropDownView;
    }
}
