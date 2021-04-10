package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    private ShortcutManagerCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isRequestPinShortcutSupported(@androidx.annotation.NonNull android.content.Context r6) {
        /*
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 26
            if (r3 < r4) goto L_0x0014
            java.lang.Class<android.content.pm.ShortcutManager> r2 = android.content.pm.ShortcutManager.class
            java.lang.Object r2 = r6.getSystemService(r2)
            android.content.pm.ShortcutManager r2 = (android.content.pm.ShortcutManager) r2
            boolean r2 = r2.isRequestPinShortcutSupported()
        L_0x0013:
            return r2
        L_0x0014:
            java.lang.String r3 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r3 = androidx.core.content.ContextCompat.checkSelfPermission(r6, r3)
            if (r3 != 0) goto L_0x0013
            android.content.pm.PackageManager r3 = r6.getPackageManager()
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r5 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r4.<init>(r5)
            java.util.List r3 = r3.queryBroadcastReceivers(r4, r2)
            java.util.Iterator r3 = r3.iterator()
        L_0x002f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0013
            java.lang.Object r0 = r3.next()
            android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
            android.content.pm.ActivityInfo r4 = r0.activityInfo
            java.lang.String r1 = r4.permission
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x004d
            java.lang.String r4 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x002f
        L_0x004d:
            r2 = 1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcut, @Nullable final IntentSender callback) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcut.toShortcutInfo(), callback);
        }
        if (!isRequestPinShortcutSupported(context)) {
            return false;
        }
        Intent intent = shortcut.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
        if (callback == null) {
            context.sendBroadcast(intent);
            return true;
        }
        context.sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            /* class androidx.core.content.pm.ShortcutManagerCompat.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                try {
                    callback.sendIntent(context, 0, null, null, null);
                } catch (IntentSender.SendIntentException e) {
                }
            }
        }, null, -1, null, null);
        return true;
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcut) {
        Intent result = null;
        if (Build.VERSION.SDK_INT >= 26) {
            result = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcut.toShortcutInfo());
        }
        if (result == null) {
            result = new Intent();
        }
        return shortcut.addToIntent(result);
    }
}
