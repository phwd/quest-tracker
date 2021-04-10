package com.oculus.vrshell.panels;

import android.text.TextUtils;
import com.oculus.vrshell.sdk.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FrameCommandChannel {
    protected List<String> mQueuedCommands = new ArrayList();

    public void launch(String componentNameOrUri) {
        launch(componentNameOrUri, null);
    }

    public void launch(String componentNameOrUri, String extraUri) {
        StringBuilder command = new StringBuilder();
        command.append("launch ");
        command.append(componentNameOrUri);
        command.append(" _oc_launch_timestamp=");
        command.append(System.nanoTime() / 1000000);
        if (!TextUtils.isEmpty(extraUri)) {
            command.append(' ');
            command.append(extraUri);
        }
        this.mQueuedCommands.add(command.toString());
    }

    public void quit() {
        this.mQueuedCommands.add("quitAndHide");
    }

    public void playAudio(SoundType soundType) {
        this.mQueuedCommands.add(String.format(Locale.ROOT, "audio play %s", soundType.getAudioName()));
    }

    public boolean openKeyboard(String cookie, float inputU, float inputV) {
        return openKeyboard(cookie, inputU, inputV, BuildConfig.FLAVOR, BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    public boolean openKeyboard(String cookie, float inputU, float inputV, String optionalLanguage, String optionalInputType, String optionalActionType) {
        return openKeyboard(cookie, inputU, inputV, optionalLanguage, optionalInputType, optionalActionType, false, false, true);
    }

    public boolean openKeyboard(String cookie, float inputU, float inputV, String optionalLanguage, String optionalInputType, String optionalActionType, boolean remoteKeyboardDisabled, boolean keyboardTranscriptionDisabled, boolean keyboardTypeAheadDisabled) {
        if (inputU >= 0.0f && inputU <= 1.0f && inputV >= 0.0f) {
            if (inputV <= 1.0f) {
                String keyboardOpenCommand = String.format(Locale.ROOT, "keyboard open %s InputU %.4f InputV %.4f RemoteKeyboardDisabled %b KeyboardTranscriptionDisabled %b KeyboardTypeAheadDisabled %b", cookie, Float.valueOf(inputU), Float.valueOf(inputV), Boolean.valueOf(remoteKeyboardDisabled), Boolean.valueOf(keyboardTranscriptionDisabled), Boolean.valueOf(keyboardTypeAheadDisabled));
                if (!TextUtils.isEmpty(optionalLanguage)) {
                    keyboardOpenCommand = keyboardOpenCommand + String.format(Locale.ROOT, " Language %s", optionalLanguage);
                }
                if (!TextUtils.isEmpty(optionalInputType)) {
                    keyboardOpenCommand = keyboardOpenCommand + String.format(Locale.ROOT, " InputType %s", optionalInputType);
                }
                if (!TextUtils.isEmpty(optionalActionType)) {
                    keyboardOpenCommand = keyboardOpenCommand + String.format(Locale.ROOT, " ActionType %s", optionalActionType);
                }
                this.mQueuedCommands.add(keyboardOpenCommand);
                return true;
            }
        }
        return false;
    }

    public void sendCommand(String command) {
        this.mQueuedCommands.add(command);
    }

    public List<String> extractFrameCommands() {
        List<String> currentFrameCommands = this.mQueuedCommands;
        this.mQueuedCommands = new ArrayList();
        return currentFrameCommands;
    }
}
