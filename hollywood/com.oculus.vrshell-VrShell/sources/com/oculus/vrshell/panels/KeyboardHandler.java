package com.oculus.vrshell.panels;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import com.oculus.auth.service.contract.ServiceContract;

public class KeyboardHandler implements ViewTreeObserver.OnGlobalFocusChangeListener {
    private int mCompositionStart = 0;
    private boolean mIsComposingText = false;
    private final String mKeyboardCookie;
    private View mLastFocused = null;
    private final AndroidPanelApp mPanelApp;

    public interface KeyboardListener {
        void onKeyboardActionKey();
    }

    private static boolean isRegionalIndicatorCodePoint(int i) {
        return i >= 127462 && i <= 127487;
    }

    private static boolean isVariationSelectorCodePoint(int i) {
        return i >= 65024 && i <= 65039;
    }

    private static boolean isZWJ(int i) {
        return i == 8205;
    }

    private static boolean validateEditTextInputType(int i, int i2) {
        return (i & i2) == i2;
    }

    public KeyboardHandler(AndroidPanelApp androidPanelApp) {
        this.mPanelApp = androidPanelApp;
        this.mKeyboardCookie = androidPanelApp.getClass().getCanonicalName();
    }

    public void attachToView(View view) {
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
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

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r8.equals("BACKSPACE") == false) goto L_0x0041;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleKeyboardCommand(java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.KeyboardHandler.handleKeyboardCommand(java.lang.String, java.lang.String):void");
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
        } else if (isVariationSelectorCodePoint) {
            if (i <= charCount) {
                return charCount;
            }
            charCountToDeleteOnBackSpace = getCharCountToDeleteOnBackSpace(str, i - charCount);
        } else if (!z || i <= charCount) {
            return charCount;
        } else {
            charCountToDeleteOnBackSpace = getCharCountToDeleteOnBackSpace(str, i - charCount);
        }
        return charCount + charCountToDeleteOnBackSpace;
    }

    private static boolean followsZWJ(String str, int i) {
        if (i > 0) {
            return isZWJ(str.codePointBefore(i));
        }
        return false;
    }

    public void onGlobalFocusChanged(View view, View view2) {
        this.mLastFocused = view2;
        this.mIsComposingText = false;
        if (view2 instanceof EditText) {
            EditText editText = (EditText) view2;
            String str = (editText.getInputType() & 524288) == 524288 ? "true" : "false";
            String keyboardInputType = getKeyboardInputType(editText);
            FrameCommandChannel commandChannel = this.mPanelApp.getCommandChannel();
            commandChannel.sendCommand("keyboard open " + this.mKeyboardCookie + " InputType " + keyboardInputType + " KeyboardTypeAheadDisabled " + str);
            return;
        }
        FrameCommandChannel commandChannel2 = this.mPanelApp.getCommandChannel();
        commandChannel2.sendCommand("keyboard close " + this.mKeyboardCookie);
    }

    private String getKeyboardInputType(EditText editText) {
        return isInputTypePassword(editText.getInputType()) ? ServiceContract.EXTRA_PASSWORD : "text_default";
    }

    private static boolean isInputTypePassword(int i) {
        return validateEditTextInputType(i, 128) || validateEditTextInputType(i, 144) || validateEditTextInputType(i, 224);
    }

    private boolean shouldHandleInput(String str) {
        return (this.mLastFocused instanceof EditText) && str.equals(this.mKeyboardCookie);
    }
}
