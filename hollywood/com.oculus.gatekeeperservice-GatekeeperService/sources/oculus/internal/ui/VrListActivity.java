package oculus.internal.ui;

import android.app.ListActivity;
import android.os.Bundle;

public class VrListActivity extends ListActivity {
    protected final VrUiWrapper mVrLifecycle = new VrUiWrapper();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
