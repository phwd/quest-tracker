package com.oculus.horizoncontent.apps.models;

import android.database.Cursor;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.GroupLaunchDestination;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.LinkedList;
import java.util.List;

public class PartyInviteInfo {
    private static final String TAG = LoggingUtil.tag(PartyInviteInfo.class);
    private List<SocialUser> mBlockedInvitedUsers = new LinkedList();
    private List<SocialUser> mBlockedPartyUsers = new LinkedList();
    private String mCurrentPartyId;
    private GroupLaunchDestination mGroupLaunchDestination;
    private String mId;
    private SocialUser mInvitedBy;
    private List<SocialUser> mInvitedUsers = new LinkedList();
    private SocialUser mPartyLeader;
    private PartyType mPartyType;
    private List<SocialUser> mPartyUsers = new LinkedList();

    public enum PartyType {
        UNKNOWN,
        CLOSED,
        JOINABLE_BY_FRIENDS,
        OPEN
    }

    public String getId() {
        return this.mId;
    }

    public String getCurrentPartyId() {
        return this.mCurrentPartyId;
    }

    public PartyType getPartyType() {
        return this.mPartyType;
    }

    public SocialUser getPartyLeader() {
        return this.mPartyLeader;
    }

    public SocialUser getInvitedBy() {
        return this.mInvitedBy;
    }

    public List<SocialUser> getInvitedUsers() {
        return this.mInvitedUsers;
    }

    public List<SocialUser> getPartyUsers() {
        return this.mPartyUsers;
    }

    public List<SocialUser> getBlockedPartyUsers() {
        return this.mBlockedPartyUsers;
    }

    public List<SocialUser> getBlockedInvitedUsers() {
        return this.mBlockedInvitedUsers;
    }

    public GroupLaunchDestination getGroupLaunchDestination() {
        return this.mGroupLaunchDestination;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("PartyInviteInfo{");
        stringBuffer.append("mId='");
        stringBuffer.append(this.mId);
        stringBuffer.append('\'');
        stringBuffer.append(", mCurrentPartyId='");
        stringBuffer.append(this.mCurrentPartyId);
        stringBuffer.append('\'');
        stringBuffer.append(", mPartyType=");
        stringBuffer.append(this.mPartyType);
        stringBuffer.append(", mPartyLeader=");
        stringBuffer.append(this.mPartyLeader);
        stringBuffer.append(", mInvitedBy=");
        stringBuffer.append(this.mInvitedBy);
        stringBuffer.append(", mInvitedUsers=");
        stringBuffer.append(this.mInvitedUsers);
        stringBuffer.append(", mPartyUsers=");
        stringBuffer.append(this.mPartyUsers);
        stringBuffer.append(", mBlockedPartyUsers=");
        stringBuffer.append(this.mBlockedPartyUsers);
        stringBuffer.append(", mBlockedInvitedUsers=");
        stringBuffer.append(this.mBlockedInvitedUsers);
        stringBuffer.append(", mGroupLaunchDestination=");
        stringBuffer.append(this.mGroupLaunchDestination);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public static PartyInviteInfo fromCursor(Cursor cursor) {
        cursor.moveToPosition(-1);
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("row_type");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_id");
        GroupLaunchDestination groupLaunchDestination = new GroupLaunchDestination();
        PartyInviteInfo partyInviteInfo = new PartyInviteInfo();
        while (cursor.moveToNext()) {
            switch (cursor.getInt(columnIndexOrThrow)) {
                case 0:
                    partyInviteInfo.mId = HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
                    partyInviteInfo.mPartyType = getPartyTypeFromCursor(cursor);
                    break;
                case 1:
                    partyInviteInfo.mPartyLeader = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
                    break;
                case 2:
                    partyInviteInfo.mInvitedBy = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
                    break;
                case 3:
                    partyInviteInfo.mInvitedUsers.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
                    break;
                case 4:
                    partyInviteInfo.mPartyUsers.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER));
                    break;
                case 5:
                    groupLaunchDestination.setApplication(getApplicationFromCursor(cursor));
                    partyInviteInfo.mGroupLaunchDestination = groupLaunchDestination;
                    break;
                case 6:
                    setGroupLaunchDestination(groupLaunchDestination, cursor);
                    partyInviteInfo.mGroupLaunchDestination = groupLaunchDestination;
                    break;
                case 7:
                    partyInviteInfo.mBlockedPartyUsers.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER));
                    break;
                case 8:
                    partyInviteInfo.mBlockedInvitedUsers.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
                    break;
                case 9:
                    partyInviteInfo.mCurrentPartyId = HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
                    break;
            }
        }
        return partyInviteInfo;
    }

    private static PartyType getPartyTypeFromCursor(Cursor cursor) {
        String stringFromIndex = HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), null);
        try {
            return PartyType.valueOf(stringFromIndex);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "getPartyTypeFromCursor: unknown party_type: " + stringFromIndex, e);
            return PartyType.UNKNOWN;
        }
    }

    private static void setGroupLaunchDestination(GroupLaunchDestination groupLaunchDestination, Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("alias");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("image_url");
        groupLaunchDestination.setId(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow, null));
        groupLaunchDestination.setDisplayName(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null));
        groupLaunchDestination.setImageUrl(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow3, null));
    }

    private static GroupLaunchDestination.Application getApplicationFromCursor(Cursor cursor) {
        return new GroupLaunchDestination.Application(HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("id"), null), HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), null), HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("image_url"), null));
    }

    private static SocialUser getSocialUserFromCursor(Cursor cursor, SocialUser.UserRowType userRowType) {
        return new SocialUser(HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("id"), ""), null, HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), ""), null, HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("image_url"), ""), false, userRowType);
    }
}
