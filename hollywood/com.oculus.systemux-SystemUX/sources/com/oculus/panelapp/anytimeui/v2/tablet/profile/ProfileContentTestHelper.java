package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.horizoncontent.profile.FbFriendPrimaryProfile;
import com.oculus.horizoncontent.profile.SelfVRProfileContent;
import com.oculus.horizoncontent.profile.VRProfileContent;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;

public class ProfileContentTestHelper {
    private final String ALIAS = "zuck";
    private final String AVATAR_PHOTO = "avatar_photo.png";
    private final String BIO = "bio";
    private final int FRIEND_COUNT = 111;
    private final boolean IS_ACTIVE = true;
    private final Long LAST_ACTIVE = 0L;
    private final long LAST_ACTIVE_TIME = 1602791539;
    private final String LAST_PRESENCE = HorizonContent.FriendList.LAST_PRESENCE_COLUMN;
    private final String PRESENCE = "Active in Beat Saber";
    private final String PROFILE_PHOTO = "profile_photo.png";
    private final String REAL_NAME = "Mark Zuckerberg";
    private final String USER_ID = "1234";

    public SelfVRProfileContent getSelfVRProfile(boolean z) {
        return new SelfVRProfileContent(111, "bio", true, "Active in Beat Saber", "Mark Zuckerberg", "zuck", z ? this.PROFILE_PHOTO : "", z ? this.AVATAR_PHOTO : "");
    }

    public VRProfileContent getVRProfileContent(SocialUserFriendshipStatus socialUserFriendshipStatus, String str, boolean z, String str2) {
        return new VRProfileContent("zuck", "bio", socialUserFriendshipStatus, Boolean.valueOf(SocialUserFriendshipStatus.BLOCKED.equals(socialUserFriendshipStatus)), "profile_photo.png", "avatar_photo.png", true, this.LAST_ACTIVE.longValue(), HorizonContent.FriendList.LAST_PRESENCE_COLUMN, "Active in Beat Saber", SocialUserPresenceStatus.ONLINE, "Mark Zuckerberg", str, Boolean.valueOf(z), new LinkedAccountsInfo(LinkedAccountsInfo.FbLinkedStatus.LINKED), str2);
    }

    public FbFriendPrimaryProfile getFBProfileContent(SocialUserFriendshipStatus socialUserFriendshipStatus, boolean z, String str, boolean z2, String str2) {
        return new FbFriendPrimaryProfile("Mark Zuckerberg", "profile_photo.png", "bio", true, 1602791539L, z ? this.USER_ID : null, socialUserFriendshipStatus, SocialUserPresenceStatus.ONLINE, true, "Active in Beat Saber", 1602791539L, z2, str2, "1234", str);
    }
}
