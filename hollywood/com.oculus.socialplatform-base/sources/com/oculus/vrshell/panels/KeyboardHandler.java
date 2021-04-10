package com.oculus.vrshell.panels;

import X.AnonymousClass006;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import com.oculus.panelapp.messenger.views.ThreadView;

public class KeyboardHandler implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public int mCompositionStart = 0;
    public boolean mIsComposingText = false;
    public final String mKeyboardCookie;
    public View mLastFocused = null;
    public final AndroidPanelApp mPanelApp;

    public interface KeyboardListener {
        void onKeyboardActionKey();
    }

    public static int getCharCountToDeleteOnBackSpace(String str, int i) {
        int charCountToDeleteOnBackSpace;
        boolean z = false;
        if (i < 1 || i > str.length()) {
            return 0;
        }
        int codePointBefore = str.codePointBefore(i);
        int charCount = Character.charCount(codePointBefore);
        boolean isVariationSelectorCodePoint = isVariationSelectorCodePoint(codePointBefore);
        boolean isRegionalIndicatorCodePoint = isRegionalIndicatorCodePoint(codePointBefore);
        if (isZWJ(codePointBefore) || followsZWJ(str, i - charCount)) {
            z = true;
        }
        if (isRegionalIndicatorCodePoint) {
            if (i <= charCount) {
                return charCount;
            }
            int codePointBefore2 = str.codePointBefore(i - charCount);
            if (!isRegionalIndicatorCodePoint(codePointBefore2)) {
                return charCount;
            }
            charCountToDeleteOnBackSpace = Character.charCount(codePointBefore2);
        } else if ((!isVariationSelectorCodePoint && !z) || i <= charCount) {
            return charCount;
        } else {
            charCountToDeleteOnBackSpace = getCharCountToDeleteOnBackSpace(str, i - charCount);
        }
        return charCount + charCountToDeleteOnBackSpace;
    }

    public static boolean isRegionalIndicatorCodePoint(int i) {
        return i >= 127462 && i <= 127487;
    }

    public static boolean isVariationSelectorCodePoint(int i) {
        return i >= 65024 && i <= 65039;
    }

    public static boolean isZWJ(int i) {
        return i == 8205;
    }

    public static boolean validateEditTextInputType(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean followsZWJ(String str, int i) {
        if (i > 0) {
            return isZWJ(str.codePointBefore(i));
        }
        return false;
    }

    public static boolean isInputTypePassword(int i) {
        if ((i & 128) == 128 || (i & 144) == 144 || (i & 224) == 224 || 0 != 0) {
            return true;
        }
        return false;
    }

    private boolean shouldHandleInput(String str) {
        if (!(this.mLastFocused instanceof EditText) || !str.equals(this.mKeyboardCookie)) {
            return false;
        }
        return true;
    }

    public void onGlobalFocusChanged(View view, View view2) {
        String str;
        this.mLastFocused = view2;
        this.mIsComposingText = false;
        if (view2 instanceof EditText) {
            EditText editText = (EditText) view2;
            if ((editText.getInputType() & 524288) == 524288) {
                str = "true";
            } else {
                str = "false";
            }
            this.mPanelApp.mFrameCommandChannel.sendCommand(AnonymousClass006.A0D(ThreadView.KEYBOARD_OPEN, this.mKeyboardCookie, " InputType ", getKeyboardInputType(editText), " KeyboardTypeAheadDisabled ", str));
            return;
        }
        this.mPanelApp.mFrameCommandChannel.sendCommand(AnonymousClass006.A07("keyboard close ", this.mKeyboardCookie));
    }

    public KeyboardHandler(AndroidPanelApp androidPanelApp) {
        this.mPanelApp = androidPanelApp;
        this.mKeyboardCookie = androidPanelApp.getClass().getCanonicalName();
    }

    private String getKeyboardInputType(EditText editText) {
        if (isInputTypePassword(editText.getInputType())) {
            return "password";
        }
        return "text_default";
    }

    public void attachToView(View view) {
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    public void handleKeyboardCommand(String str, String str2) {
        Editable text;
        int selectionStart;
        if (shouldHandleInput(str)) {
            this.mIsComposingText = false;
            EditText editText = (EditText) this.mLastFocused;
            int hashCode = str2.hashCode();
            if (hashCode != -1956726602) {
                if (hashCode != -1905312150) {
                    if (hashCode == -154864545 && str2.equals("BACKSPACE")) {
                        if (editText.getSelectionEnd() != editText.getSelectionStart()) {
                            text = editText.getText();
                            selectionStart = editText.getSelectionStart();
                        } else if (editText.getSelectionStart() > 0) {
                            int charCountToDeleteOnBackSpace = getCharCountToDeleteOnBackSpace(editText.getText().toString(), editText.getSelectionStart());
                            text = editText.getText();
                            selectionStart = editText.getSelectionStart() - charCountToDeleteOnBackSpace;
                        } else {
                            return;
                        }
                        text.delete(selectionStart, editText.getSelectionEnd());
                    }
                } else if (str2.equals("DISMISS")) {
                    editText.clearFocus();
                }
            } else if (str2.equals("ACTION_KEY") && (editText instanceof KeyboardListener)) {
                ((KeyboardListener) editText).onKeyboardActionKey();
            }
        }
    }

    public void handleKeyboardCompose(String str, String str2) {
        if (shouldHandleInput(str)) {
            EditText editText = (EditText) this.mLastFocused;
            if (editText.getSelectionEnd() != editText.getSelectionStart()) {
                editText.getText().delete(editText.getSelectionStart(), editText.getSelectionEnd());
            }
            if (!this.mIsComposingText) {
                this.mIsComposingText = true;
                this.mCompositionStart = editText.getSelectionStart();
            }
            if (this.mCompositionStart <= editText.getSelectionStart()) {
                editText.getText().replace(this.mCompositionStart, editText.getSelectionStart(), str2);
            } else {
                editText.getText().insert(editText.getSelectionStart(), str2);
            }
        }
    }

    public void handleKeyboardText(String str, String str2) {
        if (shouldHandleInput(str)) {
            EditText editText = (EditText) this.mLastFocused;
            if (this.mIsComposingText) {
                if (this.mCompositionStart <= editText.getSelectionStart()) {
                    editText.getText().replace(this.mCompositionStart, editText.getSelectionStart(), str2);
                } else {
                    editText.getText().insert(editText.getSelectionStart(), str2);
                }
                this.mIsComposingText = false;
            } else if (editText.getSelectionEnd() != editText.getSelectionStart()) {
                editText.getText().replace(editText.getSelectionStart(), editText.getSelectionEnd(), str2);
            } else {
                editText.getText().insert(editText.getSelectionStart(), str2);
            }
        }
    }
}
