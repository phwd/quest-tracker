package com.oculus.os.vrlockscreen;

import android.content.Context;
import android.util.AttributeSet;
import oculus.internal.widget.VerifyLockPatternView;

public class VerifyNewLockPatternView extends VerifyLockPatternView {
    private String mHelpText = "";

    public VerifyNewLockPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void reset() {
        VerifyNewLockPatternView.super.reset();
        this.mHelpTextView.setText(this.mHelpText);
    }

    public void setHelpText(String helpText) {
        this.mHelpText = helpText;
        this.mHelpTextView.setText(this.mHelpText);
    }
}
