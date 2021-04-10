package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonWithEditText extends RadioButtonWithDescription {
    public EditText K;
    public List L = new ArrayList();

    public RadioButtonWithEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, FJ0.t0, 0, 0);
        String string = obtainStyledAttributes.getString(0);
        if (string != null) {
            this.K.setHint(string);
        }
        this.K.setInputType(obtainStyledAttributes.getInt(1, 1));
        obtainStyledAttributes.recycle();
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public int c() {
        return R.layout.f41120_resource_name_obfuscated_RES_2131624421;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public TextView d() {
        return (TextView) findViewById(R.id.edit_text);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void f(boolean z) {
        super.f(z);
        this.K.clearFocus();
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void i() {
        super.i();
        EditText editText = (EditText) ((TextView) findViewById(R.id.edit_text));
        this.K = editText;
        editText.addTextChangedListener(new QJ0(this));
        this.K.setOnEditorActionListener(new OJ0(this));
        this.K.setOnFocusChangeListener(new PJ0(this));
    }

    public final /* synthetic */ boolean j() {
        this.K.clearFocus();
        return false;
    }

    public final void k(boolean z) {
        if (z) {
            g(true);
            this.K.setCursorVisible(true);
            return;
        }
        this.K.setCursorVisible(false);
        C3493l60.F.d(this.K);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setLabeledBy(this.K);
    }
}
