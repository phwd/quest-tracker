package com.oculus.panelapp.anytimeui.v2.tablet.internalsettings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import com.oculus.vrshell.panels.KeyboardHandler;

public final class InternalSettingsEditText extends EditText implements KeyboardHandler.KeyboardListener {
    public InternalSettingsEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
    public void onKeyboardActionKey() {
        clearFocus();
    }
}
