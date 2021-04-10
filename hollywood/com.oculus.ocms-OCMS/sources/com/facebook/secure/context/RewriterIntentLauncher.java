package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher"})
public class RewriterIntentLauncher extends BaseIntentLauncher {
    private final IntentLauncher mDelegate;
    private final IntentRewriter mRewriter;

    public RewriterIntentLauncher(IntentRewriter intentRewriter, IntentLauncher intentLauncher) {
        this.mRewriter = intentRewriter;
        this.mDelegate = intentLauncher;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Context context) {
        return this.mDelegate.launchActivity(this.mRewriter.rewriteIntent(intent, context), context);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Activity activity) {
        return this.mDelegate.launchActivityForResult(this.mRewriter.rewriteIntent(intent, activity), i, activity);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @TargetApi(16)
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        return this.mDelegate.launchActivityForResult(this.mRewriter.rewriteIntent(intent, activity), i, bundle, activity);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Fragment fragment) {
        return this.mDelegate.launchActivityForResult(this.mRewriter.rewriteIntent(intent, fragment.getContext()), i, fragment);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        return this.mDelegate.launchService(this.mRewriter.rewriteIntent(intent, context), context);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean bindToService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return this.mDelegate.bindToService(this.mRewriter.rewriteIntent(intent, context), serviceConnection, i, context);
    }
}
