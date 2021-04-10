package com.oculus.vrshell.panels;

import android.support.v4.media.subtitle.Cea708CCParser;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

public class KeyboardHandler implements ViewTreeObserver.OnGlobalFocusChangeListener {
    private int mCompositionStart = 0;
    private boolean mIsComposingText = false;
    private final String mKeyboardCookie;
    private View mLastFocused = null;
    private final AndroidPanelApp mPanelApp;

    public interface KeyboardCommandListener {
        void onKeyboardCommand(String str);
    }

    public KeyboardHandler(AndroidPanelApp panelApp) {
        this.mPanelApp = panelApp;
        this.mKeyboardCookie = panelApp.getClass().getCanonicalName();
    }

    public void attachToView(View view) {
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    public void handleKeyboardText(String cookie, String text) {
        if (shouldHandleInput(cookie)) {
            EditText view = (EditText) this.mLastFocused;
            if (this.mIsComposingText) {
                if (this.mCompositionStart <= view.getSelectionStart()) {
                    view.getText().replace(this.mCompositionStart, view.getSelectionStart(), text);
                } else {
                    view.getText().insert(view.getSelectionStart(), text);
                }
                this.mIsComposingText = false;
            } else if (view.getSelectionEnd() != view.getSelectionStart()) {
                view.getText().replace(view.getSelectionStart(), view.getSelectionEnd(), text);
            } else {
                view.getText().insert(view.getSelectionStart(), text);
            }
        }
    }

    public void handleKeyboardCompose(String cookie, String composeText) {
        if (shouldHandleInput(cookie)) {
            EditText view = (EditText) this.mLastFocused;
            if (view.getSelectionEnd() != view.getSelectionStart()) {
                view.getText().delete(view.getSelectionStart(), view.getSelectionEnd());
            }
            if (!this.mIsComposingText) {
                this.mIsComposingText = true;
                this.mCompositionStart = view.getSelectionStart();
            }
            if (this.mCompositionStart <= view.getSelectionStart()) {
                view.getText().replace(this.mCompositionStart, view.getSelectionStart(), composeText);
            } else {
                view.getText().insert(view.getSelectionStart(), composeText);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r8.equals("BACKSPACE") == false) goto L_0x0031;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleKeyboardCommand(java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.panels.KeyboardHandler.handleKeyboardCommand(java.lang.String, java.lang.String):void");
    }

    public static int getCharCountToDeleteOnBackSpace(String text, int cursorIndex) {
        boolean isZWJSequence = false;
        if (cursorIndex < 1 || cursorIndex > text.length()) {
            return 0;
        }
        int codePointBefore = text.codePointBefore(cursorIndex);
        int codePointBeforeCharCount = Character.charCount(codePointBefore);
        boolean isVariationSelectorCodePoint = isVariationSelectorCodePoint(codePointBefore);
        boolean isRegionalIndicatorCodePoint = isRegionalIndicatorCodePoint(codePointBefore);
        if (isZWJ(codePointBefore) || followsZWJ(text, cursorIndex - codePointBeforeCharCount)) {
            isZWJSequence = true;
        }
        if (isRegionalIndicatorCodePoint) {
            if (cursorIndex <= codePointBeforeCharCount) {
                return codePointBeforeCharCount;
            }
            int codePointBeforeRegionalIndicator = text.codePointBefore(cursorIndex - codePointBeforeCharCount);
            if (isRegionalIndicatorCodePoint(codePointBeforeRegionalIndicator)) {
                return Character.charCount(codePointBeforeRegionalIndicator) + codePointBeforeCharCount;
            }
            return codePointBeforeCharCount;
        } else if (isVariationSelectorCodePoint) {
            if (cursorIndex > codePointBeforeCharCount) {
                return getCharCountToDeleteOnBackSpace(text, cursorIndex - codePointBeforeCharCount) + codePointBeforeCharCount;
            }
            return codePointBeforeCharCount;
        } else if (!isZWJSequence || cursorIndex <= codePointBeforeCharCount) {
            return codePointBeforeCharCount;
        } else {
            return getCharCountToDeleteOnBackSpace(text, cursorIndex - codePointBeforeCharCount) + codePointBeforeCharCount;
        }
    }

    private static boolean isRegionalIndicatorCodePoint(int codePoint) {
        return codePoint >= 127462 && codePoint <= 127487;
    }

    private static boolean isVariationSelectorCodePoint(int codePoint) {
        return codePoint >= 65024 && codePoint <= 65039;
    }

    private static boolean isZWJ(int codePoint) {
        return codePoint == 8205;
    }

    private static boolean followsZWJ(String text, int cursorIndex) {
        if (cursorIndex > 0) {
            return isZWJ(text.codePointBefore(cursorIndex));
        }
        return false;
    }

    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        this.mLastFocused = newFocus;
        this.mIsComposingText = false;
        if (newFocus instanceof EditText) {
            EditText focusedField = (EditText) newFocus;
            String typeAheadDisabled = "false";
            if ((focusedField.getInputType() & 524288) == 524288) {
                typeAheadDisabled = "true";
            }
            String inputType = getKeyboardInputType(focusedField);
            FrameCommandChannel commandChannel = this.mPanelApp.getCommandChannel();
            commandChannel.sendCommand("keyboard open " + this.mKeyboardCookie + " InputType " + inputType + " KeyboardTypeAheadDisabled " + typeAheadDisabled);
            return;
        }
        FrameCommandChannel commandChannel2 = this.mPanelApp.getCommandChannel();
        commandChannel2.sendCommand("keyboard close " + this.mKeyboardCookie);
    }

    private String getKeyboardInputType(EditText editText) {
        if (isInputTypePassword(editText.getInputType())) {
            return "password";
        }
        return "text_default";
    }

    private static boolean isInputTypePassword(int inputType) {
        return validateEditTextInputType(inputType, 128) || validateEditTextInputType(inputType, Cea708CCParser.Const.CODE_C1_SPA) || validateEditTextInputType(inputType, 224);
    }

    private static boolean validateEditTextInputType(int editTextInputType, int inputTypeConstant) {
        return (editTextInputType & inputTypeConstant) == inputTypeConstant;
    }

    private boolean shouldHandleInput(String cookie) {
        return (this.mLastFocused instanceof EditText) && cookie.equals(this.mKeyboardCookie);
    }
}
