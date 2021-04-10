package com.oculus.vrshell.config;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.config.BootConfig;

public final class AutomationAndDebugCommandHandlers {
    private static final String ACTION_AUTOMATION_GATEKEEPER = "com.oculus.vrshell.automation.intent.action.GATEKEEPER";
    private static final String ACTION_GATEKEEPER = "com.oculus.vrshell.intent.action.GATEKEEPER";
    private static final String SHELL_GATEKEEPER_ACTION_CLEAR = "clear";
    private static final String SHELL_GATEKEEPER_ACTION_CLEARALL = "clearAll";
    private static final String SHELL_GATEKEEPER_ACTION_DISABLE = "disable";
    private static final String SHELL_GATEKEEPER_ACTION_ENABLE = "enable";
    private static final String SHELL_GATEKEEPER_ARG_CLEARALL_SERVER = "server";
    private static final String TAG = LoggingUtil.tag(AutomationAndDebugCommandHandlers.class);

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean handleNotification(android.content.Context r5, android.content.Intent r6) {
        /*
            java.lang.String r0 = r6.getAction()
            int r1 = r0.hashCode()
            r2 = 52739784(0x324bec8, float:4.8414275E-37)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L_0x001f
            r2 = 1181084085(0x4665e9b5, float:14714.427)
            if (r1 == r2) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r1 = "com.oculus.vrshell.intent.action.GATEKEEPER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = r3
            goto L_0x002a
        L_0x001f:
            java.lang.String r1 = "com.oculus.vrshell.automation.intent.action.GATEKEEPER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = r4
            goto L_0x002a
        L_0x0029:
            r0 = -1
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            if (r0 == r4) goto L_0x002f
            return r3
        L_0x002f:
            boolean r5 = handleShellGatekeeper(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.config.AutomationAndDebugCommandHandlers.handleNotification(android.content.Context, android.content.Intent):boolean");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean handleShellGatekeeper(Context context, Intent intent) {
        char c;
        String stringExtra = intent.getStringExtra("gk_action");
        String stringExtra2 = intent.getStringExtra("gk_name");
        Log.d(TAG, stringExtra2 + stringExtra);
        switch (stringExtra.hashCode()) {
            case -1298848381:
                if (stringExtra.equals(SHELL_GATEKEEPER_ACTION_ENABLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 94746189:
                if (stringExtra.equals(SHELL_GATEKEEPER_ACTION_CLEAR)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 790268948:
                if (stringExtra.equals(SHELL_GATEKEEPER_ACTION_CLEARALL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1671308008:
                if (stringExtra.equals(SHELL_GATEKEEPER_ACTION_DISABLE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            BootConfig.clearCachedGKs(context, SHELL_GATEKEEPER_ARG_CLEARALL_SERVER.equals(stringExtra2) ? BootConfig.CachedGKsType.ServerGKs : BootConfig.CachedGKsType.OverrideGKs);
        } else if (c == 1 || c == 2) {
            BootConfig.cacheOverrideGK(context, stringExtra2, SHELL_GATEKEEPER_ACTION_ENABLE.equals(stringExtra));
        } else if (c == 3) {
            BootConfig.clearOverrideGK(context, stringExtra2);
        }
        return true;
    }
}
