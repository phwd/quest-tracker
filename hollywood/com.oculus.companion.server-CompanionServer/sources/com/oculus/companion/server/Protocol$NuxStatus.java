package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.Internal;

public enum Protocol$NuxStatus implements Internal.EnumLite {
    NEW_DEVICE(0),
    APP_NUX_COMPLETE(1),
    SAFEMODE(2),
    DAY0_OTA_READY(3),
    DAY0_NO_OTA(4),
    WAITING_FOR_REBOOT(5),
    REBOOTING(6),
    NOTIFY_ENDPOINT(7),
    NUX_COMPLETE(8),
    WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD(9);
    
    private static final Internal.EnumLiteMap<Protocol$NuxStatus> internalValueMap = new Internal.EnumLiteMap<Protocol$NuxStatus>() {
        /* class com.oculus.companion.server.Protocol$NuxStatus.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$NuxStatus forNumber(int i) {
        switch (i) {
            case 0:
                return NEW_DEVICE;
            case 1:
                return APP_NUX_COMPLETE;
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return SAFEMODE;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return DAY0_OTA_READY;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return DAY0_NO_OTA;
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                return WAITING_FOR_REBOOT;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                return REBOOTING;
            case 7:
                return NOTIFY_ENDPOINT;
            case 8:
                return NUX_COMPLETE;
            case 9:
                return WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD;
            default:
                return null;
        }
    }

    private Protocol$NuxStatus(int i) {
        this.value = i;
    }
}
