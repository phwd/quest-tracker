package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher"})
public class CompositeIntentLauncher extends BaseIntentLauncher {
    private final List<? extends IntentLauncher> mLaunchers;

    public CompositeIntentLauncher(List<? extends IntentLauncher> list) {
        this.mLaunchers = list;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Context context) {
        for (IntentLauncher intentLauncher : this.mLaunchers) {
            if (intentLauncher.launchActivity(intent, context)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Activity activity) {
        for (IntentLauncher intentLauncher : this.mLaunchers) {
            if (intentLauncher.launchActivityForResult(intent, i, activity)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @TargetApi(16)
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        for (IntentLauncher intentLauncher : this.mLaunchers) {
            if (intentLauncher.launchActivityForResult(intent, i, bundle, activity)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Fragment fragment) {
        for (IntentLauncher intentLauncher : this.mLaunchers) {
            if (intentLauncher.launchActivityForResult(intent, i, fragment)) {
                return true;
            }
        }
        return false;
    }
}
