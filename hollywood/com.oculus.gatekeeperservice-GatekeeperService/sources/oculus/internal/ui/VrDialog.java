package oculus.internal.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class VrDialog extends Dialog {
    private static final String TAG = "VrDialog";
    private final VrUiWrapper mVrLifecycle = new VrUiWrapper();
    private VrWindowAttachedStateListener mWindowAttachedListener;

    public VrDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVrLifecycle.onCreate(getContext(), getWindow());
        this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
            /* class oculus.internal.ui.VrDialog.AnonymousClass1 */

            public void run() {
                VrDialog.this.mVrLifecycle.onResume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mWindowAttachedListener.start();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mWindowAttachedListener.stop();
        if (this.mWindowAttachedListener.wasWindowAttached()) {
            this.mVrLifecycle.onPause();
        }
        this.mVrLifecycle.onDestroy();
        super.onStop();
    }

    public void hide() {
        this.mVrLifecycle.onPause();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mVrLifecycle.onWindowFocusChanged(hasFocus);
    }
}
