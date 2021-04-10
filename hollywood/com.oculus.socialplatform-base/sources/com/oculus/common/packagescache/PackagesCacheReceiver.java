package com.oculus.common.packagescache;

import X.AnonymousClass0RD;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.common.collect.ImmutableList;
import com.oculus.common.packagescache.PackagesListener;
import java.util.List;
import javax.annotation.Nullable;

public class PackagesCacheReceiver extends BroadcastReceiver implements AnonymousClass0RD {
    public static final List<String> sAcceptedActions = ImmutableList.A08("android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED");
    public AnonymousClass0RE _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, PackagesCacheReceiver packagesCacheReceiver) {
        packagesCacheReceiver._UL_mInjectionContext = new AnonymousClass0RE(2, r2);
    }

    @Nullable
    public static PackagesListener.PackageAction getPackageAction(String str, boolean z) {
        if ("android.intent.action.PACKAGE_REPLACED".equals(str)) {
            return PackagesListener.PackageAction.UPDATED;
        }
        if ("android.intent.action.PACKAGE_ADDED".equals(str)) {
            return PackagesListener.PackageAction.ADDED;
        }
        if (z || !"android.intent.action.PACKAGE_REMOVED".equals(str)) {
            return null;
        }
        return PackagesListener.PackageAction.REMOVED;
    }

    public static boolean isReplacing(Intent intent) {
        return intent.getBooleanExtra("android.intent.extra.REPLACING", false);
    }

    public static final void _UL_injectMe(Context context, PackagesCacheReceiver packagesCacheReceiver) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), packagesCacheReceiver);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        r5 = getPackageAction(r1, isReplacing(r9));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            _UL_injectMe(r8, r7)
            if (r9 == 0) goto L_0x0059
            java.lang.String r1 = r9.getAction()
            java.util.List<java.lang.String> r0 = com.oculus.common.packagescache.PackagesCacheReceiver.sAcceptedActions
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0059
            android.net.Uri r0 = r9.getData()
            if (r0 == 0) goto L_0x0059
            java.lang.String r6 = r0.getEncodedSchemeSpecificPart()
            boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r6)
            if (r0 != 0) goto L_0x0059
            boolean r0 = isReplacing(r9)
            com.oculus.common.packagescache.PackagesListener$PackageAction r5 = getPackageAction(r1, r0)
            if (r5 == 0) goto L_0x0059
            r2 = 0
            r1 = 4
            X.0RE r0 = r7._UL_mInjectionContext
            java.lang.Object r0 = X.AnonymousClass0VF.A03(r2, r1, r0)
            java.util.Set r0 = (java.util.Set) r0
            java.util.Iterator r4 = r0.iterator()
        L_0x0039:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0059
            java.lang.Object r3 = r4.next()
            com.oculus.common.packagescache.PackagesListener r3 = (com.oculus.common.packagescache.PackagesListener) r3
            r2 = 1
            r1 = 40
            X.0RE r0 = r7._UL_mInjectionContext
            java.lang.Object r1 = X.AnonymousClass0VF.A03(r2, r1, r0)
            com.oculus.executors.OculusThreadExecutor r1 = (com.oculus.executors.OculusThreadExecutor) r1
            com.oculus.common.packagescache.PackagesCacheReceiver$1 r0 = new com.oculus.common.packagescache.PackagesCacheReceiver$1
            r0.<init>(r3, r5, r6)
            r1.runOnUiThread(r0)
            goto L_0x0039
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.common.packagescache.PackagesCacheReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
