package com.oculus.osupdaterapi;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.coordinatorlayout.R$styleable;

/* access modifiers changed from: package-private */
public final class OsUpdaterImpl {
    static OsUpdater$UpdaterState getUpdaterState(String str) {
        if (str == null) {
            return OsUpdater$UpdaterState.STATE_UNKNOWN;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1977205868:
                if (str.equals("ota_update_in_progress")) {
                    c = 2;
                    break;
                }
                break;
            case -1917034805:
                if (str.equals("Check for updates stopped due to no wifi connection")) {
                    c = 6;
                    break;
                }
                break;
            case -1665620570:
                if (str.equals("state_not_allowed_by_system")) {
                    c = 4;
                    break;
                }
                break;
            case -252387059:
                if (str.equals("waiting_for_reboot")) {
                    c = 1;
                    break;
                }
                break;
            case 475616557:
                if (str.equals("state_ota_disabled")) {
                    c = 3;
                    break;
                }
                break;
            case 1772672651:
                if (str.equals("Device is not configured for AB updates")) {
                    c = 5;
                    break;
                }
                break;
            case 1969912373:
                if (str.equals("state_ready_to_check_for_ota")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return OsUpdater$UpdaterState.STATE_READY_TO_CHECK_FOR_OTA;
            case 1:
                return OsUpdater$UpdaterState.STATE_WAITING_FOR_REBOOT;
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return OsUpdater$UpdaterState.STATE_UPDATE_IN_PROGRESS;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return OsUpdater$UpdaterState.STATE_OTA_DISABLED_BY_USER;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return OsUpdater$UpdaterState.STATE_NOT_ALLOWED_BY_SYSTEM;
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                return OsUpdater$UpdaterState.STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                return OsUpdater$UpdaterState.STATE_WIFI_DISABLED;
            default:
                return OsUpdater$UpdaterState.STATE_UNKNOWN;
        }
    }

    /* renamed from: com.oculus.osupdaterapi.OsUpdaterImpl$1  reason: invalid class name */
    class AnonymousClass1 extends ResultReceiver {
        final /* synthetic */ OsUpdater$UpdaterStatusCallback val$updaterStatusCallback;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.val$updaterStatusCallback.onReceive(new UpdaterStatus(bundle));
        }
    }

    /* renamed from: com.oculus.osupdaterapi.OsUpdaterImpl$2  reason: invalid class name */
    class AnonymousClass2 extends ResultReceiver {
        final /* synthetic */ OsUpdater$UpdaterOtaAvailabilityCallback val$otaAvailabilityCallback;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.val$otaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(i, bundle));
        }
    }

    /* renamed from: com.oculus.osupdaterapi.OsUpdaterImpl$3  reason: invalid class name */
    class AnonymousClass3 extends ResultReceiver {
        final /* synthetic */ OsUpdater$UpdaterOtaAvailabilityCallback val$otaAvailabilityCallback;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.val$otaAvailabilityCallback.onReceive(new UpdaterOtaAvailability(i, bundle));
        }
    }
}
