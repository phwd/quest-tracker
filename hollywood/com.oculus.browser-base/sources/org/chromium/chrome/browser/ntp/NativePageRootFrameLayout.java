package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NativePageRootFrameLayout extends FrameLayout {
    public NativePageRootFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.imeOptions = 33554432;
        return super.onCreateInputConnection(editorInfo);
    }
}
