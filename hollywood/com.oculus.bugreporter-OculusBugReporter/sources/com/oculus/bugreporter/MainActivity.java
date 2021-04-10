package com.oculus.bugreporter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends Activity {
    private UiController mUiController;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        this.mUiController = new UiController(this, (ViewGroup) findViewById(R.id.main_view));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.mUiController.onFreshReport(intent);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mUiController.onFreshReport(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mUiController.onPause();
    }
}
