package oculus.internal.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import oculus.internal.ui.ClearActivityUtils;

public class VrProgressDialog extends ProgressDialog {
    private static final String TAG = "VrProgressDialog";
    private final ClearActivityUtils.ForcePauseUtil mForcePauseUtil = new ClearActivityUtils.ForcePauseUtil();
    private final VrUiWrapper mVrLifecycle = new VrUiWrapper();
    private VrWindowAttachedStateListener mWindowAttachedListener;

    public VrProgressDialog(Context context) {
        super(context, 16974917);
    }

    public VrProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
        this.mForcePauseUtil.setForcePauseBackgroundActivity(shouldPauseActivity);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVrLifecycle.onCreate(getContext(), getWindow());
        this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
            /* class oculus.internal.ui.VrProgressDialog.AnonymousClass1 */

            public void run() {
                VrProgressDialog.this.mVrLifecycle.onResume();
            }
        });
    }

    public void onStart() {
        this.mForcePauseUtil.onStart(getContext());
        this.mWindowAttachedListener.start();
        super.onStart();
    }

    public void onStop() {
        this.mWindowAttachedListener.stop();
        if (this.mWindowAttachedListener.wasWindowAttached()) {
            this.mVrLifecycle.onPause();
        }
        this.mVrLifecycle.onDestroy();
        this.mForcePauseUtil.onStop(getContext());
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
