package com.oculus.horizoncontent.social;

import android.database.Cursor;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;

public class SocialPartyPrivacyInfo {
    private boolean mHasLinkInvite;
    private String mId;
    private String mPartyType;
    private String mPartyUrl;

    public String getId() {
        return this.mId;
    }

    public String getPartyType() {
        return this.mPartyType;
    }

    public boolean getHasLinkInvite() {
        return this.mHasLinkInvite;
    }

    public String getPartyUrl() {
        return this.mPartyUrl;
    }

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
        socialPartyPrivacyInfo.mId = HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow, null);
        socialPartyPrivacyInfo.mPartyType = HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, null);
        socialPartyPrivacyInfo.mHasLinkInvite = HorizonContentProviderHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow3);
        socialPartyPrivacyInfo.mPartyUrl = HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow4, null);
        return socialPartyPrivacyInfo;
    }
}
