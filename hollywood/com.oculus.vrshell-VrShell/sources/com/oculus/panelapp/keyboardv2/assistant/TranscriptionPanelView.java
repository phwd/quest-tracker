package com.oculus.panelapp.keyboardv2.assistant;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.oculus.panelapp.keyboardv2.ActionType;
import com.oculus.panelapp.keyboardv2.KeyAction;
import com.oculus.panelapp.keyboardv2.KeyCode;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardLocale;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.KeyboardSize;
import com.oculus.panelapp.keyboardv2.KeyboardView;
import com.oculus.panelapp.keyboardv2.R;

public class TranscriptionPanelView extends LinearLayout {
    private static String TAG = "TranscriptionPanelView";
    private KeyboardView mSendTranscriptionSubView;
    private TranscriptionEditText mTranscriptionEditText;
    private TranscriptionVolumeIndicator mTranscriptionVolumeIndicator;

    public TranscriptionPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "TranscriptionPanelView created");
    }

    public void initialize(KeyboardView.OnKeyboardActionListener onKeyboardActionListener) {
        this.mTranscriptionEditText = (TranscriptionEditText) findViewById(R.id.keyboard_transcription_edittext);
        this.mTranscriptionVolumeIndicator = (TranscriptionVolumeIndicator) findViewById(R.id.keyboard_transcription_volume_indicator);
        this.mSendTranscriptionSubView = (KeyboardView) findViewById(R.id.keyboard_transcription_send_view);
        this.mSendTranscriptionSubView.setOnKeyboardActionListener(onKeyboardActionListener);
        this.mTranscriptionEditText.setCursorVisible(false);
        setVisibility(8);
    }

    public void setTranscriptionSendView(Context context, ActionType actionType, KeyboardLocale keyboardLocale, KeyboardSize keyboardSize, KeyboardPanelApp keyboardPanelApp) {
        this.mSendTranscriptionSubView.setKeyboard(new Keyboard(context, R.xml.transcription_send_48_48, actionType, keyboardLocale, keyboardSize, keyboardPanelApp, false));
    }

    public String getTranscriptionText() {
        return this.mTranscriptionEditText.getText().toString();
    }

    public void updateTranscriptionText(String str, KeyCode keyCode, KeyAction keyAction, boolean z, boolean z2) {
        if (this.mTranscriptionEditText.updateTranscriptionText(str, keyCode, keyAction, z, z2)) {
            setVisibility(8);
        }
    }

    public void updateTranscriptionVolumeLevel(float f) {
        this.mTranscriptionEditText.setHint("Start speaking...");
        this.mTranscriptionVolumeIndicator.setVolumne(f);
    }

    public void clear() {
        this.mTranscriptionEditText.clear();
    }

    public void resetTranscription() {
        this.mTranscriptionEditText.resetTranscription();
    }

    public void setSize(KeyboardSize keyboardSize) {
        int i;
        int i2;
        Resources resources = getContext().getResources();
        TranscriptionEditText transcriptionEditText = this.mTranscriptionEditText;
        if (keyboardSize == KeyboardSize.LARGE) {
            i = R.dimen.transcription_edit_text_width_large;
        } else {
            i = R.dimen.transcription_edit_text_width_small;
        }
        transcriptionEditText.setWidth(resources.getDimensionPixelSize(i));
        ViewGroup.LayoutParams layoutParams = this.mTranscriptionVolumeIndicator.getLayoutParams();
        if (keyboardSize == KeyboardSize.LARGE) {
            i2 = R.dimen.transcription_volume_indicator_width_large;
        } else {
            i2 = R.dimen.transcription_volume_indicator_width_small;
        }
        layoutParams.width = resources.getDimensionPixelSize(i2);
    }
}
