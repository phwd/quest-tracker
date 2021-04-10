package com.oculus.horizoncontent.social;

import android.database.Cursor;
import com.oculus.horizoncontent.horizon.CursorHelper;

public class SocialPartyPrivacyInfo {
    public boolean mHasLinkInvite;
    public String mId;
    public String mPartyType;
    public String mPartyUrl;

    public static SocialPartyPrivacyInfo fromCursor(Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("party_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_type");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("party_has_link_invite");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("party_url");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        SocialPartyPrivacyInfo socialPartyPrivacyInfo = new SocialPartyPrivacyInfo();
        socialPartyPrivacyInfo.mId = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, null);
        socialPartyPrivacyInfo.mPartyType = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
        socialPartyPrivacyInfo.mHasLinkInvite = CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow3);
        socialPartyPrivacyInfo.mPartyUrl = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, null);
        return socialPartyPrivacyInfo;
    }

    public boolean getHasLinkInvite() {
        return this.mHasLinkInvite;
    }

    public String getId() {
        return this.mId;
    }

    public String getPartyType() {
        return this.mPartyType;
    }

    public String getPartyUrl() {
        return this.mPartyUrl;
    }
}
