package com.oculus.horizoncontent.social;

import X.AnonymousClass006;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SocialParty {
    public boolean mAreAllPartyMembersUsingQuest;
    @Nullable
    public List<SocialUser> mBlockedInvitedUsers;
    @Nullable
    public List<SocialUser> mBlockedMembers;
    public boolean mDoesSocialActivitySupportGroupLaunch;
    public boolean mHasLinkSharing;
    public String mID;
    public List<SocialUser> mInvitedUsers;
    @Nullable
    public SocialUser mInviter;
    public SocialPartyType mJoinType;
    public int mMaxPartySize;
    @Nullable
    public SocialUser mPartyLeader;
    public List<SocialUser> mPartyMembers;
    public String mSocialActivityApplicationDisplayName;
    public String mSocialActivityApplicationId;
    public String mSocialActivityApplicationPackageName;
    public String mSocialActivityDeeplink;
    public String mSocialActivityIconUrl;
    public String mSocialActivityId;
    public String mSocialActivityTitle;
    @Nullable
    public SocialGroupLaunchAppDestination mSocialProposedGroupLaunchAppDestination;
    public HashSet<String> mUsersMutedByViewer;

    public enum PartyMembership {
        BLOCKED_INVITED,
        BLOCKED_MEMBER,
        INVITED,
        MEMBER,
        NONE
    }

    public boolean equals(SocialParty socialParty) {
        Map<String, SocialGroupLaunchResponse> map;
        SocialGroupLaunchState socialGroupLaunchState;
        SocialGroupLaunchState socialGroupLaunchState2;
        if (this == socialParty) {
            return true;
        }
        if (socialParty == null) {
            return false;
        }
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mSocialProposedGroupLaunchAppDestination;
        Map<String, SocialGroupLaunchResponse> map2 = null;
        if (socialGroupLaunchAppDestination != null) {
            map = socialGroupLaunchAppDestination.mUserResponses;
            socialGroupLaunchState = socialGroupLaunchAppDestination.mGroupLaunchState;
        } else {
            map = null;
            socialGroupLaunchState = null;
        }
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination2 = socialParty.mSocialProposedGroupLaunchAppDestination;
        if (socialGroupLaunchAppDestination2 != null) {
            map2 = socialGroupLaunchAppDestination2.mUserResponses;
            socialGroupLaunchState2 = socialGroupLaunchAppDestination2.mGroupLaunchState;
        } else {
            socialGroupLaunchState2 = null;
        }
        if (this.mPartyMembers.equals(socialParty.mPartyMembers) && this.mID.equals(socialParty.mID) && Objects.equals(this.mJoinType, socialParty.mJoinType) && Objects.equals(Boolean.valueOf(this.mHasLinkSharing), Boolean.valueOf(socialParty.mHasLinkSharing)) && Objects.equals(this.mInvitedUsers, socialParty.mInvitedUsers) && Objects.equals(this.mBlockedMembers, socialParty.mBlockedMembers) && Objects.equals(this.mBlockedInvitedUsers, socialParty.mBlockedInvitedUsers) && Objects.equals(this.mUsersMutedByViewer, socialParty.mUsersMutedByViewer) && Objects.equals(this.mPartyLeader, socialParty.mPartyLeader)) {
            SocialDeeplinkPresence socialDeeplinkPresence = this.mPartyLeader.mDeepLink;
            return Objects.equals(socialDeeplinkPresence, socialDeeplinkPresence) && Objects.equals(this.mInviter, socialParty.mInviter) && Objects.equals(this.mSocialProposedGroupLaunchAppDestination, socialParty.mSocialProposedGroupLaunchAppDestination) && Objects.equals(map, map2) && Objects.equals(socialGroupLaunchState, socialGroupLaunchState2) && Objects.equals(this.mSocialActivityId, socialParty.mSocialActivityId);
        }
    }

    public void addInvitedUser(SocialUser socialUser) {
        this.mInvitedUsers.add(socialUser);
    }

    public boolean getAreAllPartyMembersUsingQuest() {
        return this.mAreAllPartyMembersUsingQuest;
    }

    public List<SocialUser> getBlockedInvitedUsers() {
        List<SocialUser> list = this.mBlockedInvitedUsers;
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }

    public List<SocialUser> getBlockedMembers() {
        List<SocialUser> list = this.mBlockedMembers;
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }

    public boolean getDoesSocialActivitySupportGroupLaunch() {
        return this.mDoesSocialActivitySupportGroupLaunch;
    }

    public boolean getHasLinkSharing() {
        return this.mHasLinkSharing;
    }

    public String getID() {
        return this.mID;
    }

    public List<SocialUser> getInvitedUsers() {
        List<SocialUser> list = this.mInvitedUsers;
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }

    public boolean getIsUserInvitedByLocalUserToParty(String str, SocialUser socialUser) {
        String str2;
        String str3 = socialUser.mID;
        for (SocialUser socialUser2 : this.mInvitedUsers) {
            if (str3.equals(socialUser2.mID) && (str2 = socialUser2.mInvitedToLocalPartyBySenderID) != null && str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean getIsUserMutedByViewer(String str) {
        return this.mUsersMutedByViewer.contains(str);
    }

    public SocialPartyType getJoinType() {
        return this.mJoinType;
    }

    public int getMaxPartySize() {
        return this.mMaxPartySize;
    }

    public SocialUser getPartyLeader() {
        return this.mPartyLeader;
    }

    public String getPartyMemberNamesString() {
        ArrayList arrayList = new ArrayList();
        for (SocialUser socialUser : this.mPartyMembers) {
            arrayList.add(socialUser.mAlias);
        }
        return (String) arrayList.stream().collect(Collectors.joining(", "));
    }

    public List<SocialUser> getPartyMembers() {
        return this.mPartyMembers;
    }

    public SocialGroupLaunchAppDestination getProposedGroupLaunchAppDestination() {
        return this.mSocialProposedGroupLaunchAppDestination;
    }

    public int getSize() {
        int size;
        List<SocialUser> list = this.mPartyMembers;
        int i = 0;
        if (list == null) {
            size = 0;
        } else {
            size = list.size();
        }
        List<SocialUser> list2 = this.mBlockedMembers;
        if (list2 != null) {
            i = list2.size();
        }
        return size + i;
    }

    public String getSocialActivityApplicationDisplayName() {
        return this.mSocialActivityApplicationDisplayName;
    }

    public String getSocialActivityApplicationId() {
        return this.mSocialActivityApplicationId;
    }

    public String getSocialActivityApplicationPackageName() {
        return this.mSocialActivityApplicationPackageName;
    }

    public String getSocialActivityDeeplink() {
        return this.mSocialActivityDeeplink;
    }

    public String getSocialActivityIconUrl() {
        return this.mSocialActivityIconUrl;
    }

    public String getSocialActivityId() {
        return this.mSocialActivityId;
    }

    public String getSocialActivityTitle() {
        return this.mSocialActivityTitle;
    }

    public PartyMembership getUserPartyMembership(SocialUser socialUser) {
        String str = socialUser.mID;
        for (SocialUser socialUser2 : this.mPartyMembers) {
            if (str.equals(socialUser2.mID)) {
                return PartyMembership.MEMBER;
            }
        }
        for (SocialUser socialUser3 : this.mInvitedUsers) {
            if (str.equals(socialUser3.mID)) {
                return PartyMembership.INVITED;
            }
        }
        List<SocialUser> list = this.mBlockedMembers;
        if (list != null) {
            for (SocialUser socialUser4 : list) {
                if (str.equals(socialUser4.mID)) {
                    return PartyMembership.BLOCKED_MEMBER;
                }
            }
        }
        List<SocialUser> list2 = this.mBlockedInvitedUsers;
        if (list2 != null) {
            for (SocialUser socialUser5 : list2) {
                if (str.equals(socialUser5.mID)) {
                    return PartyMembership.BLOCKED_INVITED;
                }
            }
        }
        return PartyMembership.NONE;
    }

    public int hashCode() {
        return Objects.hash(this.mPartyMembers, this.mID, this.mJoinType, Boolean.valueOf(this.mHasLinkSharing), this.mInvitedUsers, this.mBlockedMembers, this.mBlockedInvitedUsers, this.mPartyLeader, this.mInviter, Integer.valueOf(this.mMaxPartySize));
    }

    public boolean isFull() {
        if (this.mPartyMembers.size() >= this.mMaxPartySize) {
            return true;
        }
        return false;
    }

    public boolean isUserPartyLeader(String str) {
        SocialUser socialUser = this.mPartyLeader;
        if (socialUser == null) {
            return false;
        }
        return socialUser.mID.equals(str);
    }

    public void removeInvitedUser(String str) {
        this.mInvitedUsers.removeIf(new Predicate(str) {
            /* class com.oculus.horizoncontent.social.$$Lambda$SocialParty$pJQh5ZdJJUUofPSDeo1YrcF_ZqE2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialUser) obj).mID.equals(this.f$0);
            }
        });
    }

    public void removePartyMember(String str) {
        this.mPartyMembers.removeIf(new Predicate(str) {
            /* class com.oculus.horizoncontent.social.$$Lambda$SocialParty$BmTN1ZWgvu_HR1q6cHiilhnG3mI2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialUser) obj).mID.equals(this.f$0);
            }
        });
    }

    public String toString() {
        int i;
        String str;
        int i2;
        int i3;
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.ROOT;
        int i4 = 0;
        String str2 = this.mID;
        Integer valueOf = Integer.valueOf(this.mMaxPartySize);
        List<SocialUser> list = this.mPartyMembers;
        if (list != null) {
            i = list.size();
        } else {
            i = 0;
        }
        Integer valueOf2 = Integer.valueOf(i);
        SocialPartyType socialPartyType = this.mJoinType;
        SocialUser socialUser = this.mPartyLeader;
        String str3 = "";
        if (socialUser != null) {
            str = socialUser.mID;
        } else {
            str = str3;
        }
        SocialUser socialUser2 = this.mInviter;
        if (socialUser2 != null) {
            str3 = socialUser2.mID;
        }
        List<SocialUser> list2 = this.mInvitedUsers;
        if (list2 != null) {
            i2 = list2.size();
        } else {
            i2 = 0;
        }
        Integer valueOf3 = Integer.valueOf(i2);
        List<SocialUser> list3 = this.mBlockedMembers;
        if (list3 != null) {
            i3 = list3.size();
        } else {
            i3 = 0;
        }
        Integer valueOf4 = Integer.valueOf(i3);
        List<SocialUser> list4 = this.mBlockedInvitedUsers;
        if (list4 != null) {
            i4 = list4.size();
        }
        sb.append(String.format(locale, "%s: id: %s max capacity: %d, member_size: %d, join_status: %s, leader_id: %s, inviter: %s invited_size: %d, blocked_member_size: %d, blocked_invited_size: %d\nmembers:", "SocialParty", str2, valueOf, valueOf2, socialPartyType, str, str3, valueOf3, valueOf4, Integer.valueOf(i4)));
        for (SocialUser socialUser3 : this.mPartyMembers) {
            sb.append(AnonymousClass006.A07("\n", socialUser3.toString()));
        }
        List<SocialUser> list5 = this.mBlockedMembers;
        if (list5 != null) {
            sb.append("\nblocked_members:");
            for (SocialUser socialUser4 : list5) {
                sb.append(AnonymousClass006.A07("\n", socialUser4.toString()));
            }
        }
        return sb.toString();
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setInviter(SocialUser socialUser) {
        this.mInviter = socialUser;
    }

    public void setJoinType(SocialPartyType socialPartyType) {
        this.mJoinType = socialPartyType;
    }

    public void setPartyMembers(List<SocialUser> list) {
        this.mPartyMembers = list;
    }

    public SocialParty(String str, List<SocialUser> list, SocialPartyType socialPartyType, boolean z, SocialUser socialUser, List<SocialUser> list2, List<SocialUser> list3, List<SocialUser> list4, HashSet<String> hashSet, SocialUser socialUser2, int i, SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, boolean z2, String str2, String str3, boolean z3, String str4, String str5, String str6, String str7, String str8) {
        String str9 = str2;
        String str10 = str4;
        String str11 = str3;
        String str12 = str7;
        String str13 = str5;
        String str14 = str6;
        this.mID = str;
        this.mPartyMembers = list;
        this.mJoinType = socialPartyType;
        this.mHasLinkSharing = z;
        this.mPartyLeader = socialUser;
        this.mInvitedUsers = list2;
        this.mBlockedMembers = list3;
        this.mBlockedInvitedUsers = list4;
        this.mUsersMutedByViewer = hashSet;
        this.mInviter = socialUser2;
        this.mMaxPartySize = i;
        this.mSocialProposedGroupLaunchAppDestination = socialGroupLaunchAppDestination;
        this.mAreAllPartyMembersUsingQuest = z2;
        String str15 = "";
        this.mSocialActivityIconUrl = str2 == null ? str15 : str9;
        this.mSocialActivityApplicationId = str3 == null ? str15 : str11;
        this.mDoesSocialActivitySupportGroupLaunch = z3;
        this.mSocialActivityApplicationPackageName = str4 == null ? str15 : str10;
        this.mSocialActivityApplicationDisplayName = str5 == null ? str15 : str13;
        this.mSocialActivityDeeplink = str6 == null ? str15 : str14;
        this.mSocialActivityTitle = str7 == null ? str15 : str12;
        this.mSocialActivityId = str8 != null ? str8 : str15;
    }
}
