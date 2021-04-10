package com.facebook;

import X.AnonymousClass0B0;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.internal.Validate;

public abstract class ProfileTracker {
    public final AnonymousClass0B0 broadcastManager;
    public boolean isTracking = false;
    public final BroadcastReceiver receiver;

    public abstract void onCurrentProfileChanged(Profile profile, Profile profile2);

    public class ProfileBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (ProfileManager.ACTION_CURRENT_PROFILE_CHANGED.equals(intent.getAction())) {
                ProfileTracker.this.onCurrentProfileChanged((Profile) intent.getParcelableExtra(ProfileManager.EXTRA_OLD_PROFILE), (Profile) intent.getParcelableExtra(ProfileManager.EXTRA_NEW_PROFILE));
            }
        }

        public ProfileBroadcastReceiver() {
        }
    }

    private void addBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ProfileManager.ACTION_CURRENT_PROFILE_CHANGED);
        this.broadcastManager.A02(this.receiver, intentFilter);
    }

    public void startTracking() {
        if (!this.isTracking) {
            addBroadcastReceiver();
            this.isTracking = true;
        }
    }

    public void stopTracking() {
        if (this.isTracking) {
            this.broadcastManager.A01(this.receiver);
            this.isTracking = false;
        }
    }

    public ProfileTracker() {
        Validate.sdkInitialized();
        this.receiver = new ProfileBroadcastReceiver();
        Validate.sdkInitialized();
        this.broadcastManager = AnonymousClass0B0.A00(FacebookSdk.applicationContext);
        startTracking();
    }

    public boolean isTracking() {
        return this.isTracking;
    }
}
