package com.facebook.secure.content;

import X.AbstractC02680jd;
import X.AnonymousClass0kC;
import android.content.Context;
import android.os.Binder;
import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class SecureContentDelegate extends DeferredInitAbstractContentProviderDelegate {
    @Override // com.facebook.secure.content.DeferredInitAbstractContentProviderNoDIDelegate
    public final boolean A0N() {
        Context context = ((AbstractC02680jd) this).A00.getContext();
        try {
            return AnonymousClass0kC.A06(context, context.getApplicationInfo().uid, Binder.getCallingUid());
        } catch (SecurityException unused) {
            return false;
        }
    }

    public SecureContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
