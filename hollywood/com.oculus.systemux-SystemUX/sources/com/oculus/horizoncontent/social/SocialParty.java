package com.oculus.horizoncontent.social;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SocialParty {
    private boolean mAreAllPartyMembersUsingQuest;
    @Nullable
    private List<SocialUser> mBlockedInvitedUsers;
    @Nullable
    private List<SocialUser> mBlockedMembers;
    private boolean mDoesSocialActivitySupportGroupLaunch;
    private boolean mHasLinkSharing;
    private String mID;
    private List<SocialUser> mInvitedUsers;
    @Nullable
    private SocialUser mInviter;
    private SocialPartyType mJoinType;
    private int mMaxPartySize;
    @Nullable
    private SocialUser mPartyLeader;
    private List<SocialUser> mPartyMembers;
    private String mSocialActivityApplicationDisplayName;
    private String mSocialActivityApplicationId;
    private String mSocialActivityApplicationPackageName;
    private String mSocialActivityDeeplink;
    private String mSocialActivityIconUrl;
    private String mSocialActivityId;
    private String mSocialActivityTitle;
    @Nullable
    private SocialGroupLaunchAppDestination mSocialProposedGroupLaunchAppDestination;
    private HashSet<String> mUsersMutedByViewer;

    public enum PartyMembership {
        BLOCKED_INVITED,
        BLOCKED_MEMBER,
        INVITED,
        MEMBER,
        NONE
    }

    public SocialParty(String str, List<SocialUser> list, SocialPartyType socialPartyType, boolean z, SocialUser socialUser, List<SocialUser> list2, List<SocialUser> list3, List<SocialUser> list4, HashSet<String> hashSet, SocialUser socialUser2, int i, SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, boolean z2, String str2, String str3, boolean z3, String str4, String str5, String str6, String str7, String str8) {
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
        String str9 = "";
        this.mSocialActivityIconUrl = str2 == null ? str9 : str2;
        this.mSocialActivityApplicationId = str3 == null ? str9 : str3;
        this.mDoesSocialActivitySupportGroupLaunch = z3;
        this.mSocialActivityApplicationPackageName = str4 == null ? str9 : str4;
        this.mSocialActivityApplicationDisplayName = str5 == null ? str9 : str5;
        this.mSocialActivityDeeplink = str6 == null ? str9 : str6;
        this.mSocialActivityTitle = str7 == null ? str9 : str7;
        this.mSocialActivityId = str8 != null ? str8 : str9;
    }

    public List<SocialUser> getPartyMembers() {
        return this.mPartyMembers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[10];
        int i = 0;
        objArr[0] = SocialParty.class.getSimpleName();
        objArr[1] = this.mID;
        objArr[2] = Integer.valueOf(this.mMaxPartySize);
        List<SocialUser> list = this.mPartyMembers;
        objArr[3] = Integer.valueOf(list != null ? list.size() : 0);
        objArr[4] = this.mJoinType;
        SocialUser socialUser = this.mPartyLeader;
        String str = "";
        objArr[5] = socialUser != null ? socialUser.getID() : str;
        SocialUser socialUser2 = this.mInviter;
        if (socialUser2 != null) {
            str = socialUser2.getID();
        }
        objArr[6] = str;
        List<SocialUser> list2 = this.mInvitedUsers;
        objArr[7] = Integer.valueOf(list2 != null ? list2.size() : 0);
        List<SocialUser> list3 = this.mBlockedMembers;
        objArr[8] = Integer.valueOf(list3 != null ? list3.size() : 0);
        List<SocialUser> list4 = this.mBlockedInvitedUsers;
        if (list4 != null) {
            i = list4.size();
        }
        objArr[9] = Integer.valueOf(i);
        sb.append(String.format(locale, "%s: id: %s max capacity: %d, member_size: %d, join_status: %s, leader_id: %s, inviter: %s invited_size: %d, blocked_member_size: %d, blocked_invited_size: %d\nmembers:", objArr));
        Iterator<SocialUser> it = this.mPartyMembers.iterator();
        while (it.hasNext()) {
            sb.append("\n" + it.next().toString());
        }
        if (this.mBlockedMembers != null) {
            sb.append("\nblocked_members:");
            Iterator<SocialUser> it2 = this.mBlockedMembers.iterator();
            while (it2.hasNext()) {
                sb.append("\n" + it2.next().toString());
            }
        }
        return sb.toString();
    }

    public String getID() {
        return this.mID;
    }

    public boolean equals(SocialParty socialParty) {
        SocialGroupLaunchState socialGroupLaunchState;
        Map<String, SocialGroupLaunchResponse> map;
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
            map = socialGroupLaunchAppDestination.getAllUserResponses();
            socialGroupLaunchState = this.mSocialProposedGroupLaunchAppDestination.getGroupLaunchState();
        } else {
            map = null;
            socialGroupLaunchState = null;
        }
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination2 = socialParty.mSocialProposedGroupLaunchAppDestination;
        if (socialGroupLaunchAppDestination2 != null) {
            map2 = socialGroupLaunchAppDestination2.getAllUserResponses();
            socialGroupLaunchState2 = socialParty.mSocialProposedGroupLaunchAppDestination.getGroupLaunchState();
        } else {
            socialGroupLaunchState2 = null;
        }
        return this.mPartyMembers.equals(socialParty.mPartyMembers) && this.mID.equals(socialParty.mID) && Objects.equals(this.mJoinType, socialParty.mJoinType) && Objects.equals(Boolean.valueOf(this.mHasLinkSharing), Boolean.valueOf(socialParty.mHasLinkSharing)) && Objects.equals(this.mInvitedUsers, socialParty.mInvitedUsers) && Objects.equals(this.mBlockedMembers, socialParty.mBlockedMembers) && Objects.equals(this.mBlockedInvitedUsers, socialParty.mBlockedInvitedUsers) && Objects.equals(this.mUsersMutedByViewer, socialParty.mUsersMutedByViewer) && Objects.equals(this.mPartyLeader, socialParty.mPartyLeader) && Objects.equals(this.mPartyLeader.getDeepLink(), this.mPartyLeader.getDeepLink()) && Objects.equals(this.mInviter, socialParty.mInviter) && Objects.equals(this.mSocialProposedGroupLaunchAppDestination, socialParty.mSocialProposedGroupLaunchAppDestination) && Objects.equals(map, map2) && Objects.equals(socialGroupLaunchState, socialGroupLaunchState2) && Objects.equals(this.mSocialActivityId, socialParty.mSocialActivityId);
    }

    public int hashCode() {
        return Objects.hash(this.mPartyMembers, this.mID, this.mJoinType, Boolean.valueOf(this.mHasLinkSharing), this.mInvitedUsers, this.mBlockedMembers, this.mBlockedInvitedUsers, this.mPartyLeader, this.mInviter, Integer.valueOf(this.mMaxPartySize));
    }

    public SocialPartyType getJoinType() {
        return this.mJoinType;
    }

    public boolean getHasLinkSharing() {
        return this.mHasLinkSharing;
    }

    public int getMaxPartySize() {
        return this.mMaxPartySize;
    }

    public SocialUser getPartyLeader() {
        return this.mPartyLeader;
    }

    public List<SocialUser> getInvitedUsers() {
        List<SocialUser> list = this.mInvitedUsers;
        return list == null ? new ArrayList() : list;
    }

    public List<SocialUser> getBlockedMembers() {
        List<SocialUser> list = this.mBlockedMembers;
        return list == null ? new ArrayList() : list;
    }

    public List<SocialUser> getBlockedInvitedUsers() {
        List<SocialUser> list = this.mBlockedInvitedUsers;
        return list == null ? new ArrayList() : list;
    }

    public PartyMembership getUserPartyMembership(SocialUser socialUser) {
        String id = socialUser.getID();
        for (SocialUser socialUser2 : this.mPartyMembers) {
            if (id.equals(socialUser2.getID())) {
                return PartyMembership.MEMBER;
            }
        }
        for (SocialUser socialUser3 : this.mInvitedUsers) {
            if (id.equals(socialUser3.getID())) {
                return PartyMembership.INVITED;
            }
        }
        List<SocialUser> list = this.mBlockedMembers;
        if (list != null) {
            for (SocialUser socialUser4 : list) {
                if (id.equals(socialUser4.getID())) {
                    return PartyMembership.BLOCKED_MEMBER;
                }
            }
        }
        List<SocialUser> list2 = this.mBlockedInvitedUsers;
        if (list2 != null) {
            for (SocialUser socialUser5 : list2) {
                if (id.equals(socialUser5.getID())) {
                    return PartyMembership.BLOCKED_INVITED;
                }
            }
        }
        return PartyMembership.NONE;
    }

    public boolean getIsUserInvitedByLocalUserToParty(String str, SocialUser socialUser) {
        String invitedToLocalPartyBySenderID;
        String id = socialUser.getID();
        for (SocialUser socialUser2 : this.mInvitedUsers) {
            if (id.equals(socialUser2.getID()) && (invitedToLocalPartyBySenderID = socialUser2.getInvitedToLocalPartyBySenderID()) != null && invitedToLocalPartyBySenderID.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean getIsUserMutedByViewer(String str) {
        return this.mUsersMutedByViewer.contains(str);
    }

    public boolean isFull() {
        return this.mPartyMembers.size() >= this.mMaxPartySize;
    }

    public String getPartyMemberNamesString() {
        ArrayList arrayList = new ArrayList();
        for (SocialUser socialUser : this.mPartyMembers) {
            arrayList.add(socialUser.getAlias());
        }
        return (String) arrayList.stream().collect(Collectors.joining(", "));
    }

    public int getSize() {
        List<SocialUser> list = this.mPartyMembers;
        int i = 0;
        int size = list == null ? 0 : list.size();
        List<SocialUser> list2 = this.mBlockedMembers;
        if (list2 != null) {
            i = list2.size();
        }
        return size + i;
    }

    public SocialGroupLaunchAppDestination getProposedGroupLaunchAppDestination() {
        return this.mSocialProposedGroupLaunchAppDestination;
    }

    public String getSocialActivityApplicationId() {
        return this.mSocialActivityApplicationId;
    }

    public String getSocialActivityIconUrl() {
        return this.mSocialActivityIconUrl;
    }

    public boolean getDoesSocialActivitySupportGroupLaunch() {
        return this.mDoesSocialActivitySupportGroupLaunch;
    }

    public String getSocialActivityApplicationPackageName() {
        return this.mSocialActivityApplicationPackageName;
    }

    public String getSocialActivityApplicationDisplayName() {
        return this.mSocialActivityApplicationDisplayName;
    }

    public String getSocialActivityDeeplink() {
        return this.mSocialActivityDeeplink;
    }

    public String getSocialActivityTitle() {
        return this.mSocialActivityTitle;
    }

    public String getSocialActivityId() {
        return this.mSocialActivityId;
    }

    public boolean getAreAllPartyMembersUsingQuest() {
        return this.mAreAllPartyMembersUsingQuest;
    }

    public void setPartyMembers(List<SocialUser> list) {
        this.mPartyMembers = list;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setJoinType(SocialPartyType socialPartyType) {
        this.mJoinType = socialPartyType;
    }

    public void setInviter(SocialUser socialUser) {
        this.mInviter = socialUser;
    }

    public void removeInvitedUser(String str) {
        this.mInvitedUsers.removeIf(new Predicate(str) {
            /* class com.oculus.horizoncontent.social.$$Lambda$SocialParty$liIz5BVkFIXFqmgTDwkrreQgSsU */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialUser) obj).getID().equals(this.f$0);
            }
        });
    }

    public void addInvitedUser(SocialUser socialUser) {
        this.mInvitedUsers.add(socialUser);
    }

    public void removePartyMember(String str) {
        this.mPartyMembers.removeIf(new Predicate(str) {
            /* class com.oculus.horizoncontent.social.$$Lambda$SocialParty$9aEWRPjHAY_qlQOwgD2mayYjyu0 */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SocialUser) obj).getID().equals(this.f$0);
            }
        });
    }

    public boolean isUserPartyLeader(String str) {
        SocialUser socialUser = this.mPartyLeader;
        if (socialUser == null) {
            return false;
        }
        return socialUser.getID().equals(str);
    }
}
