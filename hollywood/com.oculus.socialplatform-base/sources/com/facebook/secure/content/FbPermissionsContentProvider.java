package com.facebook.secure.content;

import X.AbstractC02660jW;
import X.AnonymousClass0jF;
import X.AnonymousClass0k6;
import X.AnonymousClass0k7;
import X.AnonymousClass0kC;
import X.AnonymousClass0kD;
import X.AnonymousClass0kK;
import X.AnonymousClass0kR;
import android.content.Context;
import javax.annotation.Nullable;

public abstract class FbPermissionsContentProvider extends AnonymousClass0jF {
    public abstract String getFbPermission();

    @Nullable
    public AbstractC02660jW getReporter() {
        return null;
    }

    private boolean onCheckPermissionsHelper(String str) {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        AnonymousClass0k7 A00 = AnonymousClass0kD.A00(context, null, null, false);
        if (A00 == null) {
            throw new SecurityException("Invalid Caller Identity (null)");
        }
        AnonymousClass0kK.A00();
        String A002 = A00.A00();
        if (!AnonymousClass0k6.A10.contains(AnonymousClass0kC.A03(context, context.getPackageName())) || !AnonymousClass0kK.A01(context, A002).contains(str)) {
            return AnonymousClass0kR.A00(str).A01(context, null, null);
        }
        return true;
    }

    public String getReadOnlyFbPermission() {
        return getFbPermission();
    }

    @Override // X.AnonymousClass0jF
    public boolean onCheckPermissions() {
        return onCheckPermissionsHelper(getFbPermission());
    }

    @Override // X.AnonymousClass0jF
    public boolean onCheckReadOnlyPermissions() {
        return onCheckPermissionsHelper(getReadOnlyFbPermission());
    }
}
