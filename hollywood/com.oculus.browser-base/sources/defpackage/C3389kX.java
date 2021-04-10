package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: kX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3389kX extends C2165dJ {
    public final int F;
    public TextView G;

    public C3389kX(Context context, int i, int i2, List list, Object obj) {
        super(context, i, i2, list);
        insert(obj, 0);
        this.F = i2;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        TextView textView = view == null ? null : (TextView) view.findViewById(this.F);
        this.G = textView;
        if (textView != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.setPaddingRelative(view.getPaddingStart(), 0, view.getPaddingEnd(), 0);
            this.G.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            AbstractC3153j7.i(this.G, R.style.f71850_resource_name_obfuscated_RES_2132017758);
        }
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        if (i == 0) {
            AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
            dropDownView.setPaddingRelative(dropDownView.getPaddingStart(), getContext().getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520), dropDownView.getPaddingEnd(), dropDownView.getPaddingBottom());
        }
        return dropDownView;
    }
}
