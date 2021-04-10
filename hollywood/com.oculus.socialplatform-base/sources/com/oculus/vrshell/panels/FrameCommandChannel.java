package com.oculus.vrshell.panels;

import X.AnonymousClass006;
import android.text.TextUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FrameCommandChannel {
    public List<String> mQueuedCommands = new ArrayList();

    public List<String> extractFrameCommands() {
        List<String> list = this.mQueuedCommands;
        this.mQueuedCommands = new ArrayList();
        return list;
    }

    public void playAudio(SoundType soundType) {
        this.mQueuedCommands.add(String.format(Locale.ROOT, "audio play %s", soundType.getAudioName()));
    }

    public void quit() {
        this.mQueuedCommands.add("quitAndHide");
    }

    public void sendCommand(String str) {
        this.mQueuedCommands.add(str);
    }

    public void launch(String str) {
        launch(str, null);
    }

    public void launch(String str, String str2) {
        StringBuilder sb = new StringBuilder("launch ");
        sb.append(str);
        sb.append(" _oc_launch_timestamp=");
        sb.append(System.nanoTime() / 1000000);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(' ');
            sb.append(str2);
        }
        this.mQueuedCommands.add(sb.toString());
    }

    public boolean openKeyboard(String str, float f, float f2) {
        return openKeyboard(str, f, f2, "", "", "");
    }

    public boolean openKeyboard(String str, float f, float f2, String str2, String str3, String str4) {
        return openKeyboard(str, f, f2, str2, str3, str4, false, false, true, "OFF");
    }

    public boolean openKeyboard(String str, float f, float f2, String str2, String str3, String str4, boolean z, boolean z2, boolean z3, String str5) {
        if (f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z || f > 1.0f || f2 < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z || f2 > 1.0f) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String format = String.format(locale, "keyboard open %s InputU %.4f InputV %.4f RemoteKeyboardDisabled %b KeyboardTranscriptionDisabled %b KeyboardTypeAheadDisabled %b EnableDictationModeAutoTrigger %s", str, Float.valueOf(f), Float.valueOf(f2), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), str5);
        if (!TextUtils.isEmpty(str2)) {
            format = AnonymousClass006.A07(format, String.format(locale, " Language %s", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            format = AnonymousClass006.A07(format, String.format(locale, " InputType %s", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            format = AnonymousClass006.A07(format, String.format(locale, " ActionType %s", str4));
        }
        this.mQueuedCommands.add(format);
        return true;
    }
}
