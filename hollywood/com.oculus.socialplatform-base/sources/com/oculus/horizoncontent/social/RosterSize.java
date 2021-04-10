package com.oculus.horizoncontent.social;

import android.database.Cursor;

public class RosterSize {
    public int mInviteCount;
    public int mRosterCount;

    public static RosterSize fromCursor(Cursor cursor) {
        cursor.moveToPosition(-1);
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("row_type");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("value");
        RosterSize rosterSize = new RosterSize();
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndexOrThrow);
            if (i == 0) {
                rosterSize.mRosterCount = cursor.getInt(columnIndexOrThrow2);
            } else if (i == 1) {
                rosterSize.mInviteCount = cursor.getInt(columnIndexOrThrow2);
            }
        }
        return rosterSize;
    }

    public int getInviteCount() {
        return this.mInviteCount;
    }

    public int getRosterCount() {
        return this.mRosterCount;
    }

    public int getTotalCount() {
        return this.mRosterCount + this.mInviteCount;
    }
}
