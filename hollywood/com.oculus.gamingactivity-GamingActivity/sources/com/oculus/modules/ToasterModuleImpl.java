package com.oculus.modules;

import android.content.Context;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.ToasterModule;
import com.oculus.toast.ToastAPI;

public class ToasterModuleImpl extends ToasterModule {
    private final Context mContext;

    public ToasterModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ToasterModule
    public void createToastImpl(double toastID, ToasterModule.Bread bread, Resolver<Void> resolver) {
        try {
            ToastAPI.createToast(this.mContext, bread);
            resolver.resolve(null);
        } catch (IllegalArgumentException e) {
            resolver.reject(e);
        }
    }
}
