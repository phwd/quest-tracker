package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TopSitesTileView extends SuggestionsTileView {
    public TopSitesTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView
    public void a(C0815Nh1 nh1) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.I.getLayoutParams();
        Resources resources = getResources();
        if (nh1.c == 1) {
            marginLayoutParams.width = resources.getDimensionPixelOffset(R.dimen.f26160_resource_name_obfuscated_RES_2131166235);
            marginLayoutParams.height = resources.getDimensionPixelSize(R.dimen.f26160_resource_name_obfuscated_RES_2131166235);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.f26120_resource_name_obfuscated_RES_2131166231);
        } else {
            marginLayoutParams.width = resources.getDimensionPixelSize(R.dimen.f26170_resource_name_obfuscated_RES_2131166236);
            marginLayoutParams.height = resources.getDimensionPixelSize(R.dimen.f26170_resource_name_obfuscated_RES_2131166236);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.f26140_resource_name_obfuscated_RES_2131166233);
        }
        this.I.setLayoutParams(marginLayoutParams);
    }
}
