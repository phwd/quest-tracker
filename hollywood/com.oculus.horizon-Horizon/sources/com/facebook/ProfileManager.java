package com.facebook;

import X.AnonymousClass0B0;
import android.content.Intent;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

public final class ProfileManager {
    public static final String ACTION_CURRENT_PROFILE_CHANGED = "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
    public static final String EXTRA_NEW_PROFILE = "com.facebook.sdk.EXTRA_NEW_PROFILE";
    public static final String EXTRA_OLD_PROFILE = "com.facebook.sdk.EXTRA_OLD_PROFILE";
    public static volatile ProfileManager instance;
    public Profile currentProfile;
    public final AnonymousClass0B0 localBroadcastManager;
    public final ProfileCache profileCache;

    public static ProfileManager getInstance() {
        if (instance == null) {
            synchronized (ProfileManager.class) {
                if (instance == null) {
                    Validate.sdkInitialized();
                    instance = new ProfileManager(AnonymousClass0B0.A00(FacebookSdk.applicationContext), new ProfileCache());
                }
            }
        }
        return instance;
    }

    private void sendCurrentProfileChangedBroadcast(Profile profile, Profile profile2) {
        Intent intent = new Intent(ACTION_CURRENT_PROFILE_CHANGED);
        intent.putExtra(EXTRA_OLD_PROFILE, profile);
        intent.putExtra(EXTRA_NEW_PROFILE, profile2);
        this.localBroadcastManager.A03(intent);
    }

    public boolean loadCurrentProfile() {
        Profile load = this.profileCache.load();
        if (load == null) {
            return false;
        }
        setCurrentProfile(load, false);
        return true;
    }

    public ProfileManager(AnonymousClass0B0 r2, ProfileCache profileCache2) {
        Validate.notNull(r2, "localBroadcastManager");
        Validate.notNull(profileCache2, "profileCache");
        this.localBroadcastManager = r2;
        this.profileCache = profileCache2;
    }

    public Profile getCurrentProfile() {
        return this.currentProfile;
    }

    private void setCurrentProfile(Profile profile, boolean z) {
        Profile profile2 = this.currentProfile;
        this.currentProfile = profile;
        if (z) {
            ProfileCache profileCache2 = this.profileCache;
            if (profile != null) {
                profileCache2.save(profile);
            } else {
                profileCache2.clear();
            }
        }
        if (!Utility.areObjectsEqual(profile2, profile)) {
            sendCurrentProfileChangedBroadcast(profile2, profile);
        }
    }

    public void setCurrentProfile(Profile profile) {
        setCurrentProfile(profile, true);
    }
}
