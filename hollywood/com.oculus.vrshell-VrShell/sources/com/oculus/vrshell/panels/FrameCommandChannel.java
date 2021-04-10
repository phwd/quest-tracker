package com.oculus.vrshell.panels;

import android.text.TextUtils;
import com.oculus.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FrameCommandChannel {
    protected List<String> mQueuedCommands = new ArrayList();

    public void launch(String str) {
        launch(str, null);
    }

    public void launch(String str, String str2) {
        long nanoTime = System.nanoTime() / C.MICROS_PER_SECOND;
        StringBuilder sb = new StringBuilder();
        sb.append("launch ");
        sb.append(str);
        sb.append(" _oc_launch_timestamp=");
        sb.append(nanoTime);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(' ');
            sb.append(str2);
        }
        this.mQueuedCommands.add(sb.toString());
    }

    public void quit() {
        this.mQueuedCommands.add("quitAndHide");
    }

    public void playAudio(SoundType soundType) {
        this.mQueuedCommands.add(String.format(Locale.ROOT, "audio play %s", soundType.getAudioName()));
    }

    public boolean openKeyboard(String str, float f, float f2) {
        return openKeyboard(str, f, f2, "", "", "");
    }

    public boolean openKeyboard(String str, float f, float f2, String str2, String str3, String str4) {
        return openKeyboard(str, f, f2, str2, str3, str4, false, false, true, "OFF");
    }

    public boolean openKeyboard(String str, float f, float f2, String str2, String str3, String str4, boolean z, boolean z2, boolean z3, String str5) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return false;
        }
        String format = String.format(Locale.ROOT, "keyboard open %s InputU %.4f InputV %.4f RemoteKeyboardDisabled %b KeyboardTranscriptionDisabled %b KeyboardTypeAheadDisabled %b EnableDictationModeAutoTrigger %s", str, Float.valueOf(f), Float.valueOf(f2), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), str5);
        if (!TextUtils.isEmpty(str2)) {
            format = format + String.format(Locale.ROOT, " Language %s", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            format = format + String.format(Locale.ROOT, " InputType %s", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            format = format + String.format(Locale.ROOT, " ActionType %s", str4);
        }
        this.mQueuedCommands.add(format);
        return true;
    }

    public void sendCommand(String str) {
        this.mQueuedCommands.add(str);
    }

    public List<String> extractFrameCommands() {
        List<String> list = this.mQueuedCommands;
        this.mQueuedCommands = new ArrayList();
        return list;
    }
}
