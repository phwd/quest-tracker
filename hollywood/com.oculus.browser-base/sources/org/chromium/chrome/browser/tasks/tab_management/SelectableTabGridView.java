package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SelectableTabGridView extends TR0 {
    public SelectableTabGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = false;
    }

    @Override // defpackage.VR0
    public void f() {
        super.onClick(this);
    }

    @Override // defpackage.TR0, defpackage.VR0
    public void j(boolean z) {
    }

    @Override // defpackage.TR0, defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        Drawable drawable = getResources().getDrawable(R.drawable.f35110_resource_name_obfuscated_RES_2131231551, getContext().getTheme());
        ImageView imageView = (ImageView) d(R.id.action_button);
        if (imageView != null) {
            imageView.setBackground(new InsetDrawable(drawable, (int) getResources().getDimension(R.dimen.f24950_resource_name_obfuscated_RES_2131166114)));
            removeView(this.R);
        } else {
            imageView = this.T;
            imageView.setVisibility(0);
            imageView.setBackground(new InsetDrawable(drawable, (int) getResources().getDimension(R.dimen.f24960_resource_name_obfuscated_RES_2131166115), (int) getResources().getDimension(R.dimen.f24970_resource_name_obfuscated_RES_2131166116), (int) getResources().getDimension(R.dimen.f24960_resource_name_obfuscated_RES_2131166115), (int) getResources().getDimension(R.dimen.f24970_resource_name_obfuscated_RES_2131166116)));
            this.S.setBackground(null);
        }
        imageView.getBackground().setLevel(getResources().getInteger(R.integer.f35900_resource_name_obfuscated_RES_2131492887));
        imageView.setImageDrawable(L6.a(getContext(), R.drawable.f29720_resource_name_obfuscated_RES_2131231012));
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(isChecked());
    }
}
