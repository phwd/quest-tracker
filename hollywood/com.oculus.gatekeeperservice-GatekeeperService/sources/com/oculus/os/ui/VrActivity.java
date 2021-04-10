package com.oculus.os.ui;

import android.app.Activity;
import android.os.Bundle;
import oculus.internal.ui.VrUiWrapper;

public class VrActivity extends Activity {
    protected VrUiWrapper mVrLifecycle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVrLifecycle = new VrUiWrapper(supportHomeButton());
        this.mVrLifecycle.onCreate(this, getWindow());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mVrLifecycle.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mVrLifecycle.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mVrLifecycle.onDestroy();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mVrLifecycle.onWindowFocusChanged(hasFocus);
    }

    /* access modifiers changed from: protected */
    public boolean supportHomeButton() {
        return false;
    }
}
