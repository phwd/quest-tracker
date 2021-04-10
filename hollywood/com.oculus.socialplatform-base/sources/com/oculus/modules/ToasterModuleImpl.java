package com.oculus.modules;

import android.content.Context;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.ToasterModule;
import com.oculus.toast.ToastAPI;

public class ToasterModuleImpl extends ToasterModule {
    public final Context mContext;

    @Override // com.oculus.modules.codegen.ToasterModule
    public void createToastImpl(double d, ToasterModule.Bread bread, Resolver<Void> resolver) {
        try {
            ToastAPI.createToast(this.mContext, bread);
            resolver.resolve(null);
        } catch (IllegalArgumentException e) {
            resolver.reject(e);
        }
    }

    public ToasterModuleImpl(Context context) {
        this.mContext = context;
    }
}
