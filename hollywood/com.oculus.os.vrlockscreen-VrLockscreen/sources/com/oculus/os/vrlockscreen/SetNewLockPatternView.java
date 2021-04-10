package com.oculus.os.vrlockscreen;

import android.content.Context;
import android.util.AttributeSet;
import oculus.internal.widget.AbstractLockPatternView;

public class SetNewLockPatternView extends AbstractLockPatternView {
    private Callback mCallback;
    private String mHelpText = "";
    private boolean mShouldClearHelpOnPatternDetected = true;

    public interface Callback {
        void onValidPatternDetected(String str);
    }

    public SetNewLockPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void onLegalPatternEntered(String pattern) {
        if (this.mShouldClearHelpOnPatternDetected) {
            this.mHelpTextView.setText("");
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onValidPatternDetected(pattern);
        }
    }

    public void disableClearingHelpOnPatternDetected() {
        this.mShouldClearHelpOnPatternDetected = false;
    }

    public void reset() {
        SetNewLockPatternView.super.reset();
        this.mHelpTextView.setText(this.mHelpText);
    }

    public void setHelpText(String helpText) {
        this.mHelpText = helpText;
        this.mHelpTextView.setText(this.mHelpText);
    }
}
