package com.oculus.common.packagescache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.oculus.common.packagescache.PackagesListener;
import java.util.List;
import javax.annotation.Nullable;

public class PackagesCacheReceiver extends BroadcastReceiver implements InjectableComponentWithoutContext {
    private static final List<String> sAcceptedActions = ImmutableList.of("android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED");
    private InjectionContext _UL_mInjectionContext;

    private static final void _UL_injectMe(Context context, PackagesCacheReceiver packagesCacheReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), packagesCacheReceiver);
        } else {
            FbInjector.injectMe(PackagesCacheReceiver.class, (InjectableComponentWithoutContext) packagesCacheReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, PackagesCacheReceiver packagesCacheReceiver) {
        packagesCacheReceiver._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r6 = getPackageAction(r6, isReplacing(r7));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r6, android.content.Intent r7) {
        /*
            r5 = this;
            _UL_injectMe(r6, r5)
            if (r7 != 0) goto L_0x0006
            return
        L_0x0006:
            java.lang.String r6 = r7.getAction()
            java.util.List<java.lang.String> r0 = com.oculus.common.packagescache.PackagesCacheReceiver.sAcceptedActions
            boolean r0 = r0.contains(r6)
            if (r0 != 0) goto L_0x0013
            return
        L_0x0013:
            android.net.Uri r0 = r7.getData()
            if (r0 != 0) goto L_0x001a
            return
        L_0x001a:
            java.lang.String r0 = r0.getEncodedSchemeSpecificPart()
            boolean r1 = com.google.common.base.Strings.isNullOrEmpty(r0)
            if (r1 == 0) goto L_0x0025
            return
        L_0x0025:
            boolean r7 = isReplacing(r7)
            com.oculus.common.packagescache.PackagesListener$PackageAction r6 = getPackageAction(r6, r7)
            if (r6 != 0) goto L_0x0030
            return
        L_0x0030:
            r7 = 0
            int r1 = com.oculus.common.packagescache.PackagesCacheModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_packagescache_PackagesListener_ULGT__ULSEP_BINDING_ID
            com.facebook.inject.InjectionContext r2 = r5._UL_mInjectionContext
            java.lang.Object r7 = com.facebook.inject.FbInjector.lazyInstance(r7, r1, r2)
            java.util.Set r7 = (java.util.Set) r7
            java.util.Iterator r7 = r7.iterator()
        L_0x003f:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r7.next()
            com.oculus.common.packagescache.PackagesListener r1 = (com.oculus.common.packagescache.PackagesListener) r1
            r2 = 1
            int r3 = com.oculus.executors.ExecutorsModule.UL_id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID
            com.facebook.inject.InjectionContext r4 = r5._UL_mInjectionContext
            java.lang.Object r2 = com.facebook.inject.FbInjector.lazyInstance(r2, r3, r4)
            com.oculus.executors.OculusThreadExecutor r2 = (com.oculus.executors.OculusThreadExecutor) r2
            com.oculus.common.packagescache.PackagesCacheReceiver$1 r3 = new com.oculus.common.packagescache.PackagesCacheReceiver$1
            r3.<init>(r1, r6, r0)
            r2.runOnUiThread(r3)
            goto L_0x003f
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.common.packagescache.PackagesCacheReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    private static boolean isReplacing(Intent intent) {
        return intent.getBooleanExtra("android.intent.extra.REPLACING", false);
    }

    @Nullable
    private static PackagesListener.PackageAction getPackageAction(String str, boolean z) {
        if ("android.intent.action.PACKAGE_REPLACED".equals(str)) {
            return PackagesListener.PackageAction.UPDATED;
        }
        if ("android.intent.action.PACKAGE_ADDED".equals(str)) {
            return PackagesListener.PackageAction.ADDED;
        }
        if (!z && "android.intent.action.PACKAGE_REMOVED".equals(str)) {
            return PackagesListener.PackageAction.REMOVED;
        }
        return null;
    }
}
