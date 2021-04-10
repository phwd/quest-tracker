package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.oculus.modules.codegen.LanguageModule;
import com.oculus.modules.codegen.Resolver;
import java.util.Locale;

public class LanguageModuleImpl extends LanguageModule {
    public static final String TAG = "LanguageModule";
    public Context mContext;

    @Override // com.oculus.modules.codegen.LanguageModule
    public void getDeviceLocaleImpl(Resolver<String> resolver) {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        resolver.resolve(locale.toString());
    }

    @Override // com.oculus.modules.codegen.LanguageModule
    public void setDeviceLocaleImpl(String str, String str2, Resolver<Void> resolver) {
        Intent intent = new Intent();
        intent.setAction("companion.LOCALE");
        intent.setClassName("com.oculus.companion.server", "com.oculus.companion.server.CompanionService");
        intent.putExtra("LANGUAGE", str);
        intent.putExtra("COUNTRY", str2);
        intent.putExtra("USER_HANDLE", Process.myUserHandle());
        this.mContext.startService(intent);
        resolver.resolve(null);
    }

    public LanguageModuleImpl(Context context) {
        this.mContext = context;
    }
}
