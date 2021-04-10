package com.oculus.panelapp.keyboardv2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardView;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardTranscription;
import com.oculus.panelapp.keyboardv2.assistant.TranscriptionFeedbackView;
import com.oculus.panelapp.keyboardv2.assistant.TranscriptionPanelView;
import com.oculus.panelapp.keyboardv2.assistant.TypeaheadPanelView;

public class KeyboardPanelView extends LinearLayout {
    private static String TAG = "KeyboardPanelView";
    private boolean mIsInOverlay;
    private Keyboard mMainKeyboard;
    private KeyboardView mMainKeyboardView;
    private KeyboardPanelApp mPanelApp;
    private TranscriptionFeedbackView mTranscriptionFeedbackView;
    private TranscriptionPanelView mTranscriptionPanelView;
    private final boolean mTranscriptionSendViewEnabled = false;
    private TypeaheadPanelView mTypeaheadPanelView;

    public KeyboardPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "KeyboardView created");
    }

    public void initialize(KeyboardView.OnKeyboardActionListener onKeyboardActionListener, boolean z) {
        this.mMainKeyboardView = (KeyboardView) findViewById(R.id.keyboard_input_view);
        this.mMainKeyboardView.setOnKeyboardActionListener(onKeyboardActionListener);
        this.mTranscriptionPanelView = (TranscriptionPanelView) findViewById(R.id.keyboard_transcription_panel_view);
        this.mTranscriptionPanelView.initialize(onKeyboardActionListener);
        this.mTranscriptionFeedbackView = (TranscriptionFeedbackView) findViewById(R.id.keyboard_transcription_feedback_view);
        this.mTranscriptionFeedbackView.initialize();
        this.mTypeaheadPanelView = (TypeaheadPanelView) findViewById(R.id.keyboard_typeahead_panel_view);
        this.mTypeaheadPanelView.initialize(onKeyboardActionListener);
        this.mIsInOverlay = z;
    }

    public void setKeyboard(Context context, int i, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, final KeyboardPanelApp keyboardPanelApp, int i2, boolean z, boolean z2) {
        this.mPanelApp = keyboardPanelApp;
        this.mTranscriptionPanelView.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.keyboardv2.KeyboardPanelView.AnonymousClass1 */

            public void onClick(View view) {
                KeyboardPanelView.this.mTranscriptionPanelView.resetTranscription();
                keyboardPanelApp.stopTranscription();
            }
        });
        this.mTypeaheadPanelView.setTypeaheadView(context, actionType, keyboardLocale, keyboardSize, keyboardPanelApp);
        this.mTranscriptionPanelView.setSize(keyboardSize);
        this.mTranscriptionPanelView.clear();
        this.mTranscriptionPanelView.setVisibility(8);
        this.mTypeaheadPanelView.setVisibility(8);
        if (z2) {
            this.mTranscriptionFeedbackView.setVisibility(8);
        }
        this.mMainKeyboard = new Keyboard(context, i, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, z);
        if (i2 != -1) {
            this.mMainKeyboard.setKeyToHighlight(i2);
        }
        if (this.mIsInOverlay) {
            this.mMainKeyboard.disableKey(R.id.activate_remote_input);
        }
        if (!keyboardPanelApp.getAllowEmoji()) {
            this.mMainKeyboard.disableKey(R.id.activate_emoji_keyboard);
        }
        if (!keyboardPanelApp.getAllowImeComposition()) {
            this.mMainKeyboard.disableKeysByTag("composing_language");
        }
        setInputHandler(keyboardPanelApp, i);
        this.mMainKeyboardView.setKeyboard(this.mMainKeyboard);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setInputHandler(com.oculus.panelapp.keyboardv2.KeyboardPanelApp r7, int r8) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.KeyboardPanelView.setInputHandler(com.oculus.panelapp.keyboardv2.KeyboardPanelApp, int):void");
    }

    public Keyboard getMainKeyboard() {
        return this.mMainKeyboard;
    }

    /* access modifiers changed from: protected */
    public KeyboardView getMainKeyboardView() {
        return this.mMainKeyboardView;
    }

    public TranscriptionPanelView getTranscriptionPanelView() {
        return this.mTranscriptionPanelView;
    }

    public TypeaheadPanelView getTypeaheadPanelView() {
        return this.mTypeaheadPanelView;
    }

    public void setShiftState(ShiftState shiftState) {
        this.mMainKeyboardView.setShiftState(shiftState);
    }

    public void toggleShiftState() {
        this.mMainKeyboardView.toggleShiftState();
    }

    public void flipShifted() {
        Keyboard keyboard = this.mMainKeyboard;
        if (keyboard == null) {
            return;
        }
        if (keyboard.getShiftState() == ShiftState.OFF) {
            this.mMainKeyboardView.setShiftState(ShiftState.ON);
        } else {
            this.mMainKeyboardView.setShiftState(ShiftState.OFF);
        }
    }

    public void invalidateKey(Keyboard.Key key) {
        this.mMainKeyboardView.invalidateKey(key);
    }

    public void invalidateAllKeys() {
        this.mMainKeyboardView.invalidateAllKeys();
    }

    public void showTranscriptionFeedback(KeyboardTranscription keyboardTranscription) {
        this.mTranscriptionFeedbackView.show(keyboardTranscription);
    }

    public boolean isTranscriptionFeedbackVisible() {
        return this.mTranscriptionFeedbackView.getVisibility() == 0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        KeyboardPanelApp keyboardPanelApp;
        boolean z = motionEvent.getX() < 0.0f || motionEvent.getX() > ((float) getWidth()) || motionEvent.getY() < 0.0f || motionEvent.getY() > ((float) getHeight());
        if (motionEvent.getAction() == 1 && z && (keyboardPanelApp = this.mPanelApp) != null) {
            keyboardPanelApp.onBackButton();
        }
        return true;
    }
}
