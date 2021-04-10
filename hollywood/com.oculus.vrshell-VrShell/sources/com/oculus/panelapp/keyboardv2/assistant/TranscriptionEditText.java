package com.oculus.panelapp.keyboardv2.assistant;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import com.oculus.panelapp.keyboardv2.KeyAction;
import com.oculus.panelapp.keyboardv2.KeyCode;

public class TranscriptionEditText extends EditText {
    private int mPartialTextLength = 0;

    public TranscriptionEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void clear() {
        getText().clear();
    }

    public void resetTranscription() {
        clear();
        this.mPartialTextLength = 0;
    }

    public boolean updateTranscriptionText(String str, KeyCode keyCode, KeyAction keyAction, boolean z, boolean z2) {
        int selectionStart = getSelectionStart() - this.mPartialTextLength;
        int selectionEnd = getSelectionEnd();
        if (keyAction == KeyAction.TEXT) {
            if (z && selectionStart > 0) {
                selectionStart--;
            }
            getText().replace(selectionStart, selectionEnd, str);
            this.mPartialTextLength = z2 ? str.length() : 0;
        }
        return false;
    }
}
