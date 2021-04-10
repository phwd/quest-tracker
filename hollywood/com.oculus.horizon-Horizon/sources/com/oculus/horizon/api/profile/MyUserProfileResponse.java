package com.oculus.horizon.api.profile;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

public class MyUserProfileResponse implements ValidatableApiResponse {
    public final Viewer viewer;

    public static class Viewer {
        public final LinkedAccounts linked_accounts_info;
        public final PrivacyEdges privacy_current_activity;
        public final PrivacyEdges privacy_friends;
        public final PrivacyEdges privacy_real_world_identity;
        public final User user;
    }

    @Nullable
    public PrivacyEdges getActivityPrivacySettings() {
        PrivacyEdges privacyEdges;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || (privacyEdges = viewer2.privacy_current_activity) == null) {
            return null;
        }
        return privacyEdges;
    }

    @Nullable
    public PrivacyEdges getFriendsPrivacySettings() {
        PrivacyEdges privacyEdges;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || (privacyEdges = viewer2.privacy_friends) == null) {
            return null;
        }
        return privacyEdges;
    }

    @Nullable
    public LinkedAccounts getLinkedAccounts() {
        LinkedAccounts linkedAccounts;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || (linkedAccounts = viewer2.linked_accounts_info) == null) {
            return null;
        }
        return linkedAccounts;
    }

    @Nullable
    public PrivacyEdges getProfilePrivacySettings() {
        PrivacyEdges privacyEdges;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || (privacyEdges = viewer2.privacy_real_world_identity) == null) {
            return null;
        }
        return privacyEdges;
    }

    public User getUser() {
        return this.viewer.user;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            str = "Received an invalid MyUserProfileResponse. Response didn't have a viewer";
        } else if (viewer2.user == null) {
            str = "Received an invalid MyUserProfileResponse. Viewer didn't have a user";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
