package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.socialplatform.R;

public final class OCBackButton extends OCButton {
    @Override // com.oculus.ocui.OCButton
    public void onClickHaptics() {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onBackButtonClick();
        }
    }

    public OCBackButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.ocback_button);
    }
}
