package org.chromium.chrome.browser.explore_sites;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesTileView extends AbstractC5127ui1 {

    /* renamed from: J  reason: collision with root package name */
    public final int f10669J;

    public ExploreSitesTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.K);
        this.f10669J = obtainStyledAttributes.getDimensionPixelSize(0, getResources().getDimensionPixelSize(R.dimen.f18010_resource_name_obfuscated_RES_2131165420));
        obtainStyledAttributes.recycle();
    }
}
