package org.chromium.chrome.browser.findinpage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FindToolbar$FindQuery extends Os1 implements View.OnKeyListener {

    /* renamed from: J  reason: collision with root package name */
    public BQ f10674J;

    public FindToolbar$FindQuery(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnKeyListener(this);
    }

    @Override // defpackage.C4011o8
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (this.f10674J.i()) {
            editorInfo.imeOptions |= 16777216;
        }
        return onCreateInputConnection;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            getKeyDispatcherState().startTracking(keyEvent, this);
            return true;
        } else if (keyEvent.getAction() != 1) {
            return false;
        } else {
            getKeyDispatcherState().handleUpEvent(keyEvent);
            if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                return false;
            }
            this.f10674J.d(true);
            return true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 66 && i != 133 && (i != 35 || !keyEvent.isCtrlPressed())) {
            return super.onKeyDown(i, keyEvent);
        }
        BQ.a(this.f10674J, !keyEvent.isShiftPressed());
        return true;
    }

    public boolean onTextContextMenuItem(int i) {
        ClipData primaryClip;
        if (i != 16908322 || (primaryClip = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip()) == null) {
            return super.onTextContextMenuItem(i);
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (int i3 = 0; i3 < primaryClip.getItemCount(); i3++) {
            sb.append(primaryClip.getItemAt(i3).coerceToText(getContext()));
        }
        int length = getText().length();
        if (isFocused()) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            int max = Math.max(0, Math.min(selectionStart, selectionEnd));
            length = Math.max(0, Math.max(selectionStart, selectionEnd));
            i2 = max;
        }
        Selection.setSelection(getText(), length);
        getText().replace(i2, length, sb.toString());
        return true;
    }
}
