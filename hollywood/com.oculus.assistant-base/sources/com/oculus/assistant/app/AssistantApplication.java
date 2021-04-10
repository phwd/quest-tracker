package com.oculus.assistant.app;

import X.C0396Vs;
import android.app.Application;
import android.content.Intent;
import com.oculus.assistant.service.AssistantDumpService;
import com.oculus.os.Version;

public class AssistantApplication extends Application {
    public final void onCreate() {
        super.onCreate();
        C0396Vs.A00(this);
        if (C0396Vs.A01(this) && Version.CURRENT_SDK_VERSION >= 13) {
            startService(new Intent(this, AssistantDumpService.class));
        }
    }
}
