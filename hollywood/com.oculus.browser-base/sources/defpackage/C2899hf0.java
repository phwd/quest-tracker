package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.oculus.browser.R;
import java.util.List;

/* renamed from: hf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2899hf0 extends ArrayAdapter implements AdapterView.OnItemClickListener {
    public final LayoutInflater F;
    public final Drawable G;
    public final Drawable H;
    public final Drawable I;

    /* renamed from: J  reason: collision with root package name */
    public final Drawable f10090J;
    public final /* synthetic */ DialogC3240jf0 K;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2899hf0(DialogC3240jf0 jf0, Context context, List list) {
        super(context, 0, list);
        this.K = jf0;
        this.F = LayoutInflater.from(context);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R.attr.f6430_resource_name_obfuscated_RES_2130969089, R.attr.f6520_resource_name_obfuscated_RES_2130969098, R.attr.f6490_resource_name_obfuscated_RES_2130969095, R.attr.f6480_resource_name_obfuscated_RES_2130969094});
        this.G = obtainStyledAttributes.getDrawable(0);
        this.H = obtainStyledAttributes.getDrawable(1);
        this.I = obtainStyledAttributes.getDrawable(2);
        this.f10090J = obtainStyledAttributes.getDrawable(3);
        obtainStyledAttributes.recycle();
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007f, code lost:
        if (r0 != null) goto L_0x00b1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2899hf0.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public boolean isEnabled(int i) {
        return ((C2392eh0) getItem(i)).g;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C2392eh0 eh0 = (C2392eh0) getItem(i);
        if (eh0.g) {
            eh0.m();
            this.K.dismiss();
        }
    }
}
