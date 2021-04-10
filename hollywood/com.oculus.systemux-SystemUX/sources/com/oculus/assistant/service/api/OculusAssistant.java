package com.oculus.assistant.service.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class OculusAssistant {
    public static Intent getServiceIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.assistant", "com.oculus.assistant.service.AssistantService"));
        return intent;
    }

    public static AssistantServiceConnection bindService(Context context) {
        return bindService(context, new AssistantServiceConnection());
    }

    public static AssistantServiceConnection bindService(Context context, AssistantServiceConnection assistantServiceConnection) {
        context.bindService(getServiceIntent(), assistantServiceConnection, 1);
        return assistantServiceConnection;
    }
}
