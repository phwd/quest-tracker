package com.facebook.secure.content;

import X.AnonymousClass0kC;
import android.content.Context;
import android.os.Binder;

public abstract class SecureContentProvider extends AbstractContentProvider {
    @Override // X.AnonymousClass0jF
    public final boolean onCheckPermissions() {
        Context context = getContext();
        try {
            return AnonymousClass0kC.A06(context, context.getApplicationInfo().uid, Binder.getCallingUid());
        } catch (SecurityException unused) {
            return false;
        }
    }
}
