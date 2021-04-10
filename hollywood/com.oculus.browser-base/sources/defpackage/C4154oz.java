package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: oz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4154oz extends AbstractC1145St0 {
    public float R;
    public float S;
    public float T;
    public final float U;
    public final float V;
    public float W;

    public C4154oz(AbstractC0292Et0 et0, Context context, ViewGroup viewGroup, IJ ij) {
        super(et0, R.layout.f37490_resource_name_obfuscated_RES_2131624058, R.id.contextual_search_bar_banner_text_view, context, viewGroup, ij);
        float f = context.getResources().getDisplayMetrics().density;
        this.U = (float) context.getResources().getDimensionPixelOffset(R.dimen.f17750_resource_name_obfuscated_RES_2131165394);
        this.V = f * 56.0f;
        this.W = (float) et0.H();
    }
}
