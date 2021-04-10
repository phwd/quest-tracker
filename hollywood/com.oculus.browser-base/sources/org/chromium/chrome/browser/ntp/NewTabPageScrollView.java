package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ScrollView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NewTabPageScrollView extends ScrollView {
    public GestureDetector F = new GestureDetector(getContext(), new C1438Xn0(this));

    public NewTabPageScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20 || keyCode == 62) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    public void focusableViewAvailable(View view) {
        if (!hasFocus()) {
            super.focusableViewAvailable(view);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.imeOptions = 33554432;
        return super.onCreateInputConnection(editorInfo);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.F.onTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            this.F.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
