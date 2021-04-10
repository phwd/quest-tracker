package com.oculus.panelapp.debug.settings;

import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GatekeeperSetting extends Setting {
    private static final String GK_ACTION_CLEAR = "clear";
    private static final String GK_ACTION_DISABLED = "disabled";
    private static final String GK_ACTION_ENABLED = "enabled";
    private static List<String> gkValues = Arrays.asList(GK_ACTION_ENABLED, GK_ACTION_DISABLED, GK_ACTION_CLEAR);
    private Consumer<String> mCallback;
    private final Context mContext;
    private boolean mCurrentValue;
    private final String mGKName;

    public GatekeeperSetting(Context context, String str, boolean z) {
        super(new SettingInfo(str, SettingType.STRING, gkValues));
        this.mContext = context;
        this.mGKName = str;
        this.mCurrentValue = z;
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
        Consumer<String> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(this.mCurrentValue ? GK_ACTION_ENABLED : GK_ACTION_DISABLED);
        }
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
        if (!GK_ACTION_CLEAR.equals(str)) {
            this.mCurrentValue = GK_ACTION_ENABLED.equals(str);
        }
        Intent intent = new Intent("com.oculus.vrshell.automation.intent.action.GATEKEEPER");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("gk_action", str);
        intent.putExtra("gk_name", this.mGKName);
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void bind(Consumer<String> consumer) {
        this.mCallback = consumer;
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void unbind() {
        this.mCallback = null;
    }
}
