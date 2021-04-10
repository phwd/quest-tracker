package com.oculus.panelapp.keyboardv2.assistant;

import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;

public class KeyboardAssistant {
    private static final String TAG = "KeyboardAssistant";
    private final KeyboardPanelApp mKeyboardPanelApp;
    private KeyboardTranscription mKeyboardTranscription;
    private KeyboardTypeahead mKeyboardTypeahead;

    public KeyboardAssistant(KeyboardPanelApp keyboardPanelApp) {
        this.mKeyboardPanelApp = keyboardPanelApp;
        this.mKeyboardTranscription = new KeyboardTranscription(keyboardPanelApp);
        this.mKeyboardTypeahead = new KeyboardTypeahead(keyboardPanelApp);
    }

    public KeyboardTranscription getKeyboardTranscription() {
        return this.mKeyboardTranscription;
    }

    public KeyboardTypeahead getKeyboardTypeahead() {
        return this.mKeyboardTypeahead;
    }
}
