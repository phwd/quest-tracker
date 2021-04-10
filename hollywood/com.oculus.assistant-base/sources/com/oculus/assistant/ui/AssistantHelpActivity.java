package com.oculus.assistant.ui;

import X.C0209Jx;
import X.ZG;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AssistantHelpActivity extends Activity {
    public final void onCreate(Bundle bundle) {
        String str;
        String queryParameter;
        super.onCreate(bundle);
        Intent intent = new Intent();
        String stringExtra = getIntent().getStringExtra("intent_cmd");
        if (stringExtra == null || !stringExtra.startsWith(ZG.PREFIX) || (queryParameter = C0209Jx.A00(stringExtra).getQueryParameter("component")) == null || !queryParameter.equals("attention")) {
            str = "com.oculus.assistant.ACTIVATE_ASSISTANT_HELP";
        } else {
            str = "com.oculus.assistant.ACTIVATE_ASSISTANT";
        }
        sendBroadcast(intent.setAction(str));
        finish();
    }
}
