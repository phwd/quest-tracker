package com.oculus.panelapp.dialog.commonsystemdialogs;

public class OculusLinkConnectionHelper {

    /* access modifiers changed from: private */
    public enum DismissalType {
        AvailableDialog,
        DisconnectedDialog
    }

    private static native long registerShouldDismissDialog(int i, Runnable runnable);

    public static native void unregisterShouldDismissDialog(long j);

    public static long registerShouldDismissAvailableDialog(Runnable runnable) {
        return registerShouldDismissDialog(DismissalType.AvailableDialog.ordinal(), runnable);
    }

    public static long registerShouldDismissDisconnectedDialog(Runnable runnable) {
        return registerShouldDismissDialog(DismissalType.DisconnectedDialog.ordinal(), runnable);
    }
}
