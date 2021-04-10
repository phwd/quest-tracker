package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class FacebookShareRequest implements Parcelable {
    public static final Parcelable.Creator<FacebookShareRequest> CREATOR = new Parcelable.Creator<FacebookShareRequest>() {
        /* class com.oculus.mediaupload.model.FacebookShareRequest.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final FacebookShareRequest createFromParcel(Parcel parcel) {
            return new FacebookShareRequest(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final FacebookShareRequest[] newArray(int i) {
            return new FacebookShareRequest[i];
        }
    };
    public static final String ME = "me";
    @Nullable
    public final String gameId;
    @Nullable
    public final String groupId;
    public final boolean isGroupThread;
    @Nullable
    public final FacebookSharePrivacy privacy;
    @Nullable
    public final String threadId;
    public final FacebookShareType type;
    public final String userId;

    public enum FacebookSharePrivacy {
        SELF,
        ALL_FRIENDS,
        FRIENDS_OF_FRIENDS,
        EVERYONE
    }

    public enum FacebookShareType {
        GROUP,
        TIMELINE,
        STORY,
        MESSENGER_THREAD,
        SYNC
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        Object[] objArr = new Object[8];
        objArr[0] = "FacebookShareRequest";
        objArr[1] = this.type.name();
        FacebookSharePrivacy facebookSharePrivacy = this.privacy;
        if (facebookSharePrivacy != null) {
            str = facebookSharePrivacy.name();
        } else {
            str = null;
        }
        objArr[2] = str;
        objArr[3] = this.groupId;
        objArr[4] = this.gameId;
        objArr[5] = this.threadId;
        objArr[6] = Boolean.valueOf(this.isGroupThread);
        objArr[7] = this.userId;
        return String.format(locale, "%s[%s, privacy = %s, groupId = %s, gameId = %s, threadId = %s, isGroupThread = %s, userId= %s]", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String name;
        parcel.writeString(this.type.name());
        FacebookSharePrivacy facebookSharePrivacy = this.privacy;
        if (facebookSharePrivacy == null) {
            name = null;
        } else {
            name = facebookSharePrivacy.name();
        }
        parcel.writeString(name);
        parcel.writeString(this.groupId);
        parcel.writeString(this.gameId);
        parcel.writeString(this.threadId);
        parcel.writeInt(this.isGroupThread ? 1 : 0);
        parcel.writeString(this.userId);
    }

    public FacebookShareRequest(Parcel parcel) {
        FacebookSharePrivacy facebookSharePrivacy;
        this.type = FacebookShareType.valueOf(parcel.readString());
        String readString = parcel.readString();
        if (readString != null) {
            facebookSharePrivacy = FacebookSharePrivacy.valueOf(readString);
        } else {
            facebookSharePrivacy = null;
        }
        this.privacy = facebookSharePrivacy;
        this.groupId = parcel.readString();
        this.gameId = parcel.readString();
        this.threadId = parcel.readString();
        this.isGroupThread = parcel.readInt() != 1 ? false : true;
        String readString2 = parcel.readString();
        this.userId = readString2 == null ? "me" : readString2;
    }

    public FacebookShareRequest(FacebookShareType facebookShareType) {
        this.type = facebookShareType;
        this.privacy = null;
        this.groupId = null;
        this.gameId = null;
        this.threadId = null;
        this.isGroupThread = false;
        this.userId = "me";
    }
}
