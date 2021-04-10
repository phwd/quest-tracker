package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewStructure;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertDialogEditText extends C4011o8 {
    public String I;

    public AlertDialogEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f4110_resource_name_obfuscated_RES_2130968857);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
    }

    public void onProvideAutofillStructure(ViewStructure viewStructure, int i) {
        if (!TextUtils.isEmpty(this.I)) {
            viewStructure.setWebDomain(this.I);
        }
        super.onProvideAutofillStructure(viewStructure, i);
    }
}
