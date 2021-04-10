package com.oculus.horizon.api.profile;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

public class MyUserProfileResponse implements ValidatableApiResponse {
    public Viewer viewer;

    public static class Viewer {
        public LinkedAccounts linked_accounts_info;
        public PrivacyEdges privacy_current_activity;
        public PrivacyEdges privacy_friends;
        public PrivacyEdges privacy_real_world_identity;
        public User user;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            throw new NullPointerException("Received an invalid MyUserProfileResponse. Response didn't have a viewer");
        } else if (viewer2.user == null) {
            throw new NullPointerException("Received an invalid MyUserProfileResponse. Viewer didn't have a user");
        }
    }

    public User getUser() {
        return this.viewer.user;
    }

    @Nullable
    public PrivacyEdges getProfilePrivacySettings() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.privacy_real_world_identity == null) {
            return null;
        }
        return this.viewer.privacy_real_world_identity;
    }

    @Nullable
    public PrivacyEdges getActivityPrivacySettings() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.privacy_current_activity == null) {
            return null;
        }
        return this.viewer.privacy_current_activity;
    }

    @Nullable
    public PrivacyEdges getFriendsPrivacySettings() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.privacy_friends == null) {
            return null;
        }
        return this.viewer.privacy_friends;
    }

    @Nullable
    public LinkedAccounts getLinkedAccounts() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.linked_accounts_info == null) {
            return null;
        }
        return this.viewer.linked_accounts_info;
    }
}
