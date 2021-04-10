package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.facebook.ultralight.names.UltralightNames;
import com.oculus.modules.codegen.LanguageModule;
import com.oculus.modules.codegen.Resolver;
import java.util.Locale;

public class LanguageModuleImpl extends LanguageModule {
    private static final String TAG = LanguageModule.class.getSimpleName();
    private Context mContext;

    public LanguageModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LanguageModule
    public void getDeviceLocaleImpl(Resolver<String> resolver) {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        resolver.resolve(locale.toString());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LanguageModule
    public void setDeviceLocaleImpl(String language, String country, Resolver<Void> resolver) {
        Log.d(TAG, "Setting locale to " + language + UltralightNames.FQN_SEPARATOR + country);
        Intent intent = new Intent();
        intent.setAction("companion.LOCALE");
        intent.setClassName("com.oculus.companion.server", "com.oculus.companion.server.CompanionService");
        intent.putExtra("LANGUAGE", language);
        intent.putExtra("COUNTRY", country);
        intent.putExtra("USER_HANDLE", Process.myUserHandle());
        this.mContext.startService(intent);
        resolver.resolve(null);
    }
}
