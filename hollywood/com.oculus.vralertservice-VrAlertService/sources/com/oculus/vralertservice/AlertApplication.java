package com.oculus.vralertservice;

import android.app.Application;
import android.util.Slog;
import oculus.internal.ui.VrAlertDialog;

public class AlertApplication extends Application {
    private static VrAlertDialog CurrentDialog = null;
    private static AlertLevel CurrentLevel = AlertLevel.NONE;
    private static final String TAG = "AlertApplication";

    /* access modifiers changed from: package-private */
    public enum AlertLevel {
        NONE(0),
        FAN_MALFUNCTION(1),
        EXTERNAL_TEMPERATURE(2),
        SOC_TEMPERATURE(3),
        BATTERY_TEMPERATURE(3);
        
        private final int mSeverity;

        public boolean isMoreSevereThan(AlertLevel alertLevel) {
            return this.mSeverity > alertLevel.mSeverity;
        }

        private AlertLevel(int i) {
            this.mSeverity = i;
        }
    }

    static synchronized void clearAlert(AlertLevel alertLevel, VrAlertDialog vrAlertDialog) {
        synchronized (AlertApplication.class) {
            if (vrAlertDialog == CurrentDialog) {
                String str = TAG;
                Slog.i(str, "Clearing " + CurrentLevel + " to " + alertLevel);
                CurrentDialog = null;
                CurrentLevel = alertLevel;
            }
        }
    }

    static synchronized boolean moveTo(AlertLevel alertLevel) {
        synchronized (AlertApplication.class) {
            if (!alertLevel.isMoreSevereThan(CurrentLevel)) {
                return false;
            }
            CurrentLevel = alertLevel;
            if (CurrentDialog != null) {
                CurrentDialog.dismiss();
            }
            CurrentDialog = null;
            return true;
        }
    }

    static synchronized boolean setCurrentDialog(AlertLevel alertLevel, VrAlertDialog vrAlertDialog) {
        synchronized (AlertApplication.class) {
            if (alertLevel != CurrentLevel) {
                return false;
            }
            String str = TAG;
            Slog.i(str, "Switching from " + CurrentLevel + " to " + alertLevel);
            if (CurrentDialog != null) {
                CurrentDialog.dismiss();
            }
            CurrentLevel = alertLevel;
            CurrentDialog = vrAlertDialog;
            vrAlertDialog.show();
            return true;
        }
    }
}
