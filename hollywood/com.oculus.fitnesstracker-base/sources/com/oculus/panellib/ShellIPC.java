package com.oculus.panellib;

import org.json.JSONObject;

public class ShellIPC {
    private static long NativeHandle;

    public interface Listener {
    }

    public static native void addToCommandBuffer(String str);

    public static native void registerListener(String str, Listener listener);

    public static void dialogShow(JSONObject jSONObject) {
        addToCommandBuffer(String.format("dialog show %s", jSONObject.toString()));
    }

    public static void dialogHide() {
        addToCommandBuffer("dialog hide");
    }

    public static void showKeyboard(String str) {
        addToCommandBuffer(String.format("keyboard open %s", str));
    }

    public static void hideKeyboard(String str) {
        addToCommandBuffer(String.format("keyboard close %s", str));
    }

    public static void tooltipShow(JSONObject jSONObject) {
        if (jSONObject != null) {
            addToCommandBuffer(String.format("tooltip show %s", jSONObject.toString()));
        }
    }

    public static void tooltipHide() {
        addToCommandBuffer("tooltip hide");
    }

    public static void launchDeepURI(String str, String str2) {
        addToCommandBuffer(String.format("launch %s %s", str, str2));
    }
}
