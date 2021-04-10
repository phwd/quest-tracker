package com.oculus.assistant.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class OculusAssistant {
    public static final String PACKAGE_NAME = "com.oculus.assistant";
    public static final String SERVICE = "com.oculus.assistant.service.AssistantService";

    public static Intent getServiceIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.assistant", SERVICE));
        intent.setAction("com.oculus.assistant.ENTRY_SERVICE");
        return intent;
    }

    public static OculusAssistantConnection bindService(Context context) {
        return bindService(context, new OculusAssistantConnection());
    }

    public static OculusAssistantConnection bindService(Context context, OculusAssistantConnection oculusAssistantConnection) {
        context.bindService(getServiceIntent(), oculusAssistantConnection, 1);
        return oculusAssistantConnection;
    }
}
