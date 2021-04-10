package com.oculus.assistant.testui;

import android.app.Activity;
import android.os.Bundle;
import com.oculus.assistant.assistantutils.SystemUXUtil;

public class AssistantVRShellActivity extends Activity {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
        startActivity(SystemUXUtil.A05(".testui.AssistantTestActivity"));
    }
}
