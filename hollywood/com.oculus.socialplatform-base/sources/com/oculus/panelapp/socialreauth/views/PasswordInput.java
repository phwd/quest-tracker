package com.oculus.panelapp.socialreauth.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.oculus.ocui.OCEventHandler;
import com.oculus.vrshell.panels.KeyboardHandler;

public class PasswordInput extends EditText implements KeyboardHandler.KeyboardListener {
    public KeyboardHandler.KeyboardListener mCallback;
    public OCEventHandler mEventHandler;

    public void destroy() {
        if (this.mCallback != null) {
            this.mCallback = null;
        }
        if (this.mEventHandler != null) {
            this.mEventHandler = null;
        }
    }

    public /* synthetic */ void lambda$new$0$PasswordInput(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }

    public /* synthetic */ boolean lambda$new$1$PasswordInput(View view, MotionEvent motionEvent) {
        if (this.mEventHandler == null || motionEvent.getAction() != 9) {
            return false;
        }
        this.mEventHandler.onButtonEnter();
        return false;
    }

    @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
    public void onKeyboardActionKey() {
        KeyboardHandler.KeyboardListener keyboardListener = this.mCallback;
        if (keyboardListener != null) {
            keyboardListener.onKeyboardActionKey();
            clearFocus();
        }
    }

    public PasswordInput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$PasswordInput$gE4jMKb6UGkHLJSukMe1y_12sfo2 */

            public final void onClick(View view) {
                PasswordInput.this.lambda$new$0$PasswordInput(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$PasswordInput$_NhQ4SXkFiODKmgLVVZFR1Q1NA2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return PasswordInput.this.lambda$new$1$PasswordInput(view, motionEvent);
            }
        });
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnKeyboardAction(KeyboardHandler.KeyboardListener keyboardListener) {
        this.mCallback = keyboardListener;
    }
}
