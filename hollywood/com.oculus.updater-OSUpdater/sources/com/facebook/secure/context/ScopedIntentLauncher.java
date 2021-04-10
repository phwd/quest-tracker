package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.secure.intent.BaseIntentScope;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher", "ImprovedNewApi", "BadMethodUse-androidx.fragment.app.Fragment.startActivityForResult"})
public class ScopedIntentLauncher extends BaseIntentLauncher {
    @Nullable
    private String mCallerDomain = null;
    private final List<Object> mIntentLaunchingPlugins;
    protected final BaseIntentScope mScope;

    public ScopedIntentLauncher(BaseIntentScope baseIntentScope, List<Object> list) {
        this.mScope = baseIntentScope;
        this.mIntentLaunchingPlugins = list;
    }

    public ScopedIntentLauncher withDomain(@Nullable String str) {
        this.mCallerDomain = str;
        return this;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        Intent enforceServiceIntent = this.mScope.enforceServiceIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceServiceIntent == null) {
            return null;
        }
        return context.startService(enforceServiceIntent);
    }
}
