package oculus.internal.ui;

import android.content.Context;
import android.view.Window;
import oculus.internal.ui.VrReactDialog;

/* access modifiers changed from: package-private */
/* compiled from: VrUiWrapper */
public interface VrUiInterface {
    void onClickAction(int i);

    void onCreate(Context context, Window window);

    void onDestroy();

    void onPause();

    void onResume();

    void onWindowFocusChanged(boolean z);

    void recreate(Context context, Window window);

    void setConfiguration(VrReactDialog.DialogConfiguration dialogConfiguration);

    void useReactLibrary(ReactDialogEventHandler reactDialogEventHandler);
}
