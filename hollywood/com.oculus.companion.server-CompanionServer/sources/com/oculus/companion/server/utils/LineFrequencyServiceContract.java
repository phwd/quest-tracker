package com.oculus.companion.server.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public class LineFrequencyServiceContract {
    public void startLineFrequencyService(Context context, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.linefrequencyservice", "com.oculus.linefrequencyservice.LineFrequencyService"));
        intent.setAction(str);
        context.startServiceAsUser(intent, UserHandle.SYSTEM);
    }
}
