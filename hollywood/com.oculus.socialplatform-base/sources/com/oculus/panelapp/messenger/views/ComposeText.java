package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.oculus.ocui.OCEventHandler;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.KeyboardHandler;

public class ComposeText extends EditText implements KeyboardHandler.KeyboardListener {
    public KeyboardHandler.KeyboardListener mCallback;
    public OCEventHandler mEventHandler;

    public float getLeftFadingEdgeStrength() {
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public float getRightFadingEdgeStrength() {
        return 1.0f;
    }

    public void destroy() {
        if (this.mCallback != null) {
            this.mCallback = null;
        }
        if (this.mEventHandler != null) {
            this.mEventHandler = null;
        }
    }

    public /* synthetic */ void lambda$new$0$ComposeText(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }

    public /* synthetic */ boolean lambda$new$1$ComposeText(View view, MotionEvent motionEvent) {
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

    public ComposeText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ComposeText$cTEqIgoC7NoW9Dwo9N54IEC24Fw2 */

            public final void onClick(View view) {
                ComposeText.this.lambda$new$0$ComposeText(view);
            }
        });
        setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ComposeText$r9nntVSp9EWptTARgMN9Mciz3aI2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return ComposeText.this.lambda$new$1$ComposeText(view, motionEvent);
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
