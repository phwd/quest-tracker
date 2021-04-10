package com.oculus.panellib;

import org.json.JSONObject;

public class ShellIPC {
    private static long NativeHandle = 0;

    public interface Listener {
    }

    public static native void addToCommandBuffer(String str);

    public static native void registerListener(String str, Listener listener);

    public static void dialogShow(JSONObject description) {
        addToCommandBuffer(String.format("dialog show %s", description.toString()));
    }

    public static void dialogHide() {
        addToCommandBuffer("dialog hide");
    }

    public static void showKeyboard(String keyboardCookie) {
        addToCommandBuffer(String.format("keyboard open %s", keyboardCookie));
    }

    public static void hideKeyboard(String keyboardCookie) {
        addToCommandBuffer(String.format("keyboard close %s", keyboardCookie));
    }

    public static void tooltipShow(JSONObject tooltipConfigurationJson) {
        if (tooltipConfigurationJson != null) {
            addToCommandBuffer(String.format("tooltip show %s", tooltipConfigurationJson.toString()));
        }
    }

    public static void tooltipHide() {
        addToCommandBuffer("tooltip hide");
    }

    public static void launchDeepURI(String uri, String deep) {
        addToCommandBuffer(String.format("launch %s %s", uri, deep));
    }
}
