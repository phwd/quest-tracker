package com.oculus.panelapp.people.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.oculus.ocui.OCEventHandler;
import com.oculus.vrshell.panels.KeyboardHandler;

public class SearchEditText extends EditText implements KeyboardHandler.KeyboardListener {
    public KeyboardHandler.KeyboardListener mCallback;
    public OCEventHandler mEventHandler;

    public void destroy() {
        if (this.mCallback != null) {
            this.mCallback = null;
        }
        this.mEventHandler = null;
    }

    public /* synthetic */ void lambda$new$0$SearchEditText(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }

    public /* synthetic */ boolean lambda$new$1$SearchEditText(View view, MotionEvent motionEvent) {
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
        }
        clearFocus();
    }

    public SearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLongClickable(false);
        setTextIsSelectable(false);
        setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.people.views.$$Lambda$SearchEditText$fBuzfwDoLIcxw0C0PS2SU06prFc2 */

            public final void onClick(View view) {
                SearchEditText.this.lambda$new$0$SearchEditText(view);
            }
        });
        setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.people.views.$$Lambda$SearchEditText$txX8Hsltr8tl4r7L3dZIX5Hrdo2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SearchEditText.this.lambda$new$1$SearchEditText(view, motionEvent);
            }
        });
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setKeyboardListener(KeyboardHandler.KeyboardListener keyboardListener) {
        this.mCallback = keyboardListener;
    }
}
