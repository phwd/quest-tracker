package com.oculus.common.packagescache;

import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.oculus.common.packagescache.PackagesListener;
import com.oculus.executors.OculusThreadExecutor;
import java.util.List;
import java.util.Set;

public class PackagesCacheReceiver extends BroadcastReceiver implements AnonymousClass0QB {
    public static final List<String> sAcceptedActions = ImmutableList.A09("android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED");
    public AnonymousClass0QC _UL_mInjectionContext;

    public final void onReceive(Context context, Intent intent) {
        Uri data;
        final PackagesListener.PackageAction packageAction;
        this._UL_mInjectionContext = new AnonymousClass0QC(2, AnonymousClass0J2.get(context));
        if (intent != null) {
            String action = intent.getAction();
            if (sAcceptedActions.contains(action) && (data = intent.getData()) != null) {
                final String encodedSchemeSpecificPart = data.getEncodedSchemeSpecificPart();
                if (!Strings.isNullOrEmpty(encodedSchemeSpecificPart)) {
                    boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                    if ("android.intent.action.PACKAGE_REPLACED".equals(action)) {
                        packageAction = PackagesListener.PackageAction.UPDATED;
                    } else if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                        packageAction = PackagesListener.PackageAction.ADDED;
                    } else if (!booleanExtra && "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                        packageAction = PackagesListener.PackageAction.REMOVED;
                    } else {
                        return;
                    }
                    if (packageAction != null) {
                        for (final PackagesListener packagesListener : (Set) AnonymousClass0J2.A03(0, 153, this._UL_mInjectionContext)) {
                            ((OculusThreadExecutor) AnonymousClass0J2.A03(1, 333, this._UL_mInjectionContext)).mUiThreadExecutor.execute(new Runnable() {
                                /* class com.oculus.common.packagescache.PackagesCacheReceiver.AnonymousClass1 */

                                public final void run() {
                                    throw null;
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
