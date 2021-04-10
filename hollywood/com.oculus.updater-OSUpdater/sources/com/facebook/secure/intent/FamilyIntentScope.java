package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

public class FamilyIntentScope extends TargetedAppsIntentScope {
    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    @Nullable
    public /* bridge */ /* synthetic */ Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        return super.enforceServiceIntent(intent, context, str);
    }
}
