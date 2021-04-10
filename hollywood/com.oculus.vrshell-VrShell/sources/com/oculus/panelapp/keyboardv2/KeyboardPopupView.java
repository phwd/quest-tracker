package com.oculus.panelapp.keyboardv2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardView;

public class KeyboardPopupView extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    private KeyboardPanelApp panelApp;

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public void onHoverExit() {
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public void onKey(int i, int[] iArr) {
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public boolean onLongPress(Keyboard.Key key, boolean z) {
        return false;
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public void onPress(String str, boolean z, Keyboard.Key key) {
    }

    public KeyboardPopupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.keyboardViewStyle);
    }

    public KeyboardPopupView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public KeyboardPopupView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void initialize(KeyboardPanelApp keyboardPanelApp) {
        this.panelApp = keyboardPanelApp;
        setOnKeyboardActionListener(this);
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 10) {
            return super.onHoverEvent(motionEvent);
        }
        this.panelApp.hidePopupLayer();
        return true;
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public void onRelease(String str, boolean z, Keyboard.Key key) {
        if (key != null) {
            CharSequence charSequence = z ? key.shiftText : key.text;
            if (charSequence == null) {
                charSequence = key.text;
            }
            if (charSequence != null) {
                this.panelApp.getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/keyboard/press.ogg"));
                this.panelApp.commitTextInput(str, charSequence.toString(), KeyCode.NONE, KeyAction.TEXT, true);
                this.panelApp.getDefaultKeyboardActionListener().onRelease(str, z, key);
            }
        }
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
    public void onHoverEnter(Keyboard.Key key) {
        this.panelApp.getDefaultKeyboardActionListener().onHoverEnter(key);
        if (key.isExitKey) {
            this.panelApp.hidePopupLayer();
        }
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        super.removePressedState();
        return onTouchEvent;
    }
}
