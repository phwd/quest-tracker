package defpackage;

import android.text.Editable;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: Uc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface AbstractC1225Uc {
    void announceForAccessibility(CharSequence charSequence);

    void append(CharSequence charSequence);

    Editable getEditableText();

    int getHighlightColor();

    int getSelectionEnd();

    int getSelectionStart();

    Editable getText();

    boolean isFocused();

    void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent);

    void setCursorVisible(boolean z);

    void setSelection(int i, int i2);
}
