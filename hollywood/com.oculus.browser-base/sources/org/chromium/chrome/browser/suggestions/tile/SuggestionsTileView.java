package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SuggestionsTileView extends AbstractC5127ui1 {

    /* renamed from: J  reason: collision with root package name */
    public SX0 f10769J;

    public SuggestionsTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(C0815Nh1 nh1) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.I.getLayoutParams();
        Resources resources = getResources();
        int i = nh1.c;
        if (i == 2 || i == 3) {
            marginLayoutParams.width = resources.getDimensionPixelSize(R.dimen.f26210_resource_name_obfuscated_RES_2131166240);
            marginLayoutParams.height = resources.getDimensionPixelSize(R.dimen.f26210_resource_name_obfuscated_RES_2131166240);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.f26200_resource_name_obfuscated_RES_2131166239);
        } else {
            marginLayoutParams.width = resources.getDimensionPixelSize(R.dimen.f26170_resource_name_obfuscated_RES_2131166236);
            marginLayoutParams.height = resources.getDimensionPixelSize(R.dimen.f26170_resource_name_obfuscated_RES_2131166236);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.f26140_resource_name_obfuscated_RES_2131166233);
        }
        this.I.setLayoutParams(marginLayoutParams);
    }
}
