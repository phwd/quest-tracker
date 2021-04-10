package com.oculus.modules;

import android.content.Context;
import android.os.UserManager;
import com.oculus.modules.codegen.UserModule;

public class UserModuleImpl extends UserModule {
    private final Context mContext;

    public UserModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.UserModule
    public boolean marshallJSConstantIsPrimaryUser() {
        return ((UserManager) this.mContext.getSystemService("user")).isSystemUser();
    }
}
