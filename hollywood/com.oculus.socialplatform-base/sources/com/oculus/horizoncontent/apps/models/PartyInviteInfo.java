package com.oculus.horizoncontent.apps.models;

import X.AnonymousClass006;
import android.database.Cursor;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.GroupLaunchDestination;
import com.oculus.horizoncontent.horizon.CursorHelper;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.LinkedList;
import java.util.List;

public class PartyInviteInfo {
    public static final String TAG = LoggingUtil.tag(PartyInviteInfo.class);
    public List<SocialUser> mBlockedInvitedUsers = new LinkedList();
    public List<SocialUser> mBlockedPartyUsers = new LinkedList();
    public String mCurrentPartyId;
    public GroupLaunchDestination mGroupLaunchDestination;
    public String mId;
    public SocialUser mInvitedBy;
    public List<SocialUser> mInvitedUsers = new LinkedList();
    public SocialUser mPartyLeader;
    public PartyType mPartyType;
    public List<SocialUser> mPartyUsers = new LinkedList();

    public enum PartyType {
        UNKNOWN,
        CLOSED,
        JOINABLE_BY_FRIENDS,
        OPEN
    }

    public static PartyInviteInfo fromCursor(Cursor cursor) {
        List<SocialUser> list;
        SocialUser.UserRowType userRowType;
        cursor.moveToPosition(-1);
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("row_type");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_id");
        GroupLaunchDestination groupLaunchDestination = new GroupLaunchDestination();
        PartyInviteInfo partyInviteInfo = new PartyInviteInfo();
        while (cursor.moveToNext()) {
            switch (cursor.getInt(columnIndexOrThrow)) {
                case 0:
                    partyInviteInfo.mId = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
                    partyInviteInfo.mPartyType = getPartyTypeFromCursor(cursor);
                    continue;
                case 1:
                    partyInviteInfo.mPartyLeader = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
                    continue;
                case 2:
                    partyInviteInfo.mInvitedBy = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
                    continue;
                case 3:
                    list = partyInviteInfo.mInvitedUsers;
                    userRowType = SocialUser.UserRowType.INVITED;
                    list.add(getSocialUserFromCursor(cursor, userRowType));
                case 4:
                    list = partyInviteInfo.mPartyUsers;
                    userRowType = SocialUser.UserRowType.PARTY_MEMBER;
                    list.add(getSocialUserFromCursor(cursor, userRowType));
                case 5:
                    groupLaunchDestination.mApplication = getApplicationFromCursor(cursor);
                    break;
                case 6:
                    setGroupLaunchDestination(groupLaunchDestination, cursor);
                    break;
                case 7:
                    list = partyInviteInfo.mBlockedPartyUsers;
                    userRowType = SocialUser.UserRowType.PARTY_MEMBER;
                    list.add(getSocialUserFromCursor(cursor, userRowType));
                case 8:
                    list = partyInviteInfo.mBlockedInvitedUsers;
                    userRowType = SocialUser.UserRowType.INVITED;
                    list.add(getSocialUserFromCursor(cursor, userRowType));
                case 9:
                    partyInviteInfo.mCurrentPartyId = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
                    continue;
            }
            partyInviteInfo.mGroupLaunchDestination = groupLaunchDestination;
        }
        return partyInviteInfo;
    }

    public static GroupLaunchDestination.Application getApplicationFromCursor(Cursor cursor) {
        return new GroupLaunchDestination.Application(CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("id"), null), CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), null), CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("image_url"), null));
    }

    public static PartyType getPartyTypeFromCursor(Cursor cursor) {
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), null);
        try {
            return PartyType.valueOf(stringFromIndex);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A07("getPartyTypeFromCursor: unknown party_type: ", stringFromIndex), e);
            return PartyType.UNKNOWN;
        }
    }

    public static SocialUser getSocialUserFromCursor(Cursor cursor, SocialUser.UserRowType userRowType) {
        return new SocialUser(CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("id"), ""), null, CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), ""), null, CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("image_url"), ""), false, userRowType);
    }

    public static void setGroupLaunchDestination(GroupLaunchDestination groupLaunchDestination, Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("alias");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("image_url");
        groupLaunchDestination.mId = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, null);
        groupLaunchDestination.mDisplayName = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
        groupLaunchDestination.mImageUrl = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, null);
    }

    public List<SocialUser> getBlockedInvitedUsers() {
        return this.mBlockedInvitedUsers;
    }

    public List<SocialUser> getBlockedPartyUsers() {
        return this.mBlockedPartyUsers;
    }

    public String getCurrentPartyId() {
        return this.mCurrentPartyId;
    }

    public GroupLaunchDestination getGroupLaunchDestination() {
        return this.mGroupLaunchDestination;
    }

    public String getId() {
        return this.mId;
    }

    public SocialUser getInvitedBy() {
        return this.mInvitedBy;
    }

    public List<SocialUser> getInvitedUsers() {
        return this.mInvitedUsers;
    }

    public SocialUser getPartyLeader() {
        return this.mPartyLeader;
    }

    public PartyType getPartyType() {
        return this.mPartyType;
    }

    public List<SocialUser> getPartyUsers() {
        return this.mPartyUsers;
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
}
