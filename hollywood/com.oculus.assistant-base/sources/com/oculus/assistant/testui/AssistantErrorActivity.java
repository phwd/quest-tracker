package com.oculus.assistant.testui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.oculus.assistant.R;

public class AssistantErrorActivity extends Activity {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.assistant_error_layout);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("ERROR_TITLE");
        String stringExtra2 = intent.getStringExtra("ERROR_MESSAGE");
        setTitle(stringExtra);
        ((TextView) findViewById(R.id.error_message)).setText(stringExtra2);
    }
}
