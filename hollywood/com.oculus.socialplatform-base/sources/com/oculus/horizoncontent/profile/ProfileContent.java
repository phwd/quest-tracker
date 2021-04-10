package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;

public interface ProfileContent {
    String getAlias();

    String getAvatarUrl();

    String getBiography();

    int getFriendCount();

    SocialUserFriendshipStatus getFriendshipStatusInVr();

    Boolean getIsUserActive();

    ProfilePresence getPresence(Resources resources);

    String getProfilePictureUri();

    String getRealName();
}
