package org.chromium.components.javascript_dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JavascriptDialogCustomView extends LinearLayout {
    public EditText F;
    public CheckBox G;

    public JavascriptDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean a() {
        return this.G.isChecked();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (EditText) findViewById(R.id.js_modal_dialog_prompt);
        this.G = (CheckBox) findViewById(R.id.suppress_js_modal_dialogs);
    }
}
