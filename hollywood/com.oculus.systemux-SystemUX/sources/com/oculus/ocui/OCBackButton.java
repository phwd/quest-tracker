package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.common.ocui.R;

public final class OCBackButton extends OCButton {
    public OCBackButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.ocback_button);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.ocui.OCButton
    public void onClickHaptics() {
        OCEventHandler eventHandler = getEventHandler();
        if (eventHandler != null) {
            eventHandler.onBackButtonClick();
        }
    }
}
