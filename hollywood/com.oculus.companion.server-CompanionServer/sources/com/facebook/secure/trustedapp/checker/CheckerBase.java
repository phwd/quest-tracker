package com.facebook.secure.trustedapp.checker;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.TrustedApp;

public abstract class CheckerBase implements CheckerInternal {
    public static final Checker ALLOW_IF_DEBUG_BUILD_CALLING_DEBUG_BUILD = new CheckerBase() {
        /* class com.facebook.secure.trustedapp.checker.CheckerBase.AnonymousClass3 */

        @Override // com.facebook.secure.trustedapp.checker.Checker
        public boolean shouldAllowIntent(Context context, Object obj, Intent intent, Reporter reporter) {
            AppIdentity appIdentity = TrustedApp.getAppIdentity(context, intent);
            if (appIdentity != null && TrustedApp.isCurrentAppDebugBuild(context) && appIdentity.getPackageName() != null && TrustedApp.checkDebugSignatureFromPackageName(context, appIdentity.getPackageName())) {
                return true;
            }
            return false;
        }
    };
    public static final Checker ALWAYS_ALLOW = new CheckerBase() {
        /* class com.facebook.secure.trustedapp.checker.CheckerBase.AnonymousClass1 */

        @Override // com.facebook.secure.trustedapp.checker.Checker
        public boolean shouldAllowIntent(Context context, Object obj, Intent intent, Reporter reporter) {
            return true;
        }
    };
    public static final Checker ALWAYS_DENY = new CheckerBase() {
        /* class com.facebook.secure.trustedapp.checker.CheckerBase.AnonymousClass2 */

        @Override // com.facebook.secure.trustedapp.checker.Checker
        public boolean shouldAllowIntent(Context context, Object obj, Intent intent, Reporter reporter) {
            return false;
        }
    };

    @Override // com.facebook.secure.trustedapp.checker.Checker
    public Checker and(Checker checker) {
        return new AndChecker(this, checker);
    }
}
