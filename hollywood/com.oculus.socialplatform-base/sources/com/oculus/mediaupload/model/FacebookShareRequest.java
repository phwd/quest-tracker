package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class FacebookShareRequest implements Parcelable {
    public static final Parcelable.Creator<FacebookShareRequest> CREATOR = new Parcelable.Creator<FacebookShareRequest>() {
        /* class com.oculus.mediaupload.model.FacebookShareRequest.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FacebookShareRequest createFromParcel(Parcel parcel) {
            return new FacebookShareRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FacebookShareRequest[] newArray(int i) {
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

    public int describeContents() {
        return 0;
    }

    public static FacebookShareRequest forShareToMessengerThread(String str, boolean z) {
        return new FacebookShareRequest(FacebookShareType.MESSENGER_THREAD, null, null, null, str, z, ME);
    }

    public static FacebookShareRequest forSync() {
        return new FacebookShareRequest(FacebookShareType.SYNC, null, null, null, null, false, ME);
    }

    public String toString() {
        String str;
        Locale locale = Locale.US;
        String name = this.type.name();
        FacebookSharePrivacy facebookSharePrivacy = this.privacy;
        if (facebookSharePrivacy != null) {
            str = facebookSharePrivacy.name();
        } else {
            str = null;
        }
        return String.format(locale, "%s[%s, privacy = %s, groupId = %s, gameId = %s, threadId = %s, isGroupThread = %s, userId= %s]", "FacebookShareRequest", name, str, this.groupId, this.gameId, this.threadId, Boolean.valueOf(this.isGroupThread), this.userId);
    }

    public void writeToParcel(Parcel parcel, int i) {
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
        this.userId = readString2 == null ? ME : readString2;
    }

    public FacebookShareRequest(FacebookShareType facebookShareType, @Nullable FacebookSharePrivacy facebookSharePrivacy, @Nullable String str, @Nullable String str2, @Nullable String str3, boolean z, String str4) {
        this.type = facebookShareType;
        this.privacy = facebookSharePrivacy;
        this.groupId = str;
        this.gameId = str2;
        this.threadId = str3;
        this.isGroupThread = z;
        this.userId = str4;
    }

    public static FacebookShareRequest forShareToGroup(String str) {
        return new FacebookShareRequest(FacebookShareType.GROUP, null, str, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToGroup(String str, String str2) {
        return new FacebookShareRequest(FacebookShareType.GROUP, null, str, str2, null, false, ME);
    }

    public static FacebookShareRequest forShareToStories() {
        return new FacebookShareRequest(FacebookShareType.STORY, null, null, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToStories(String str) {
        return new FacebookShareRequest(FacebookShareType.STORY, null, null, str, null, false, ME);
    }

    public static FacebookShareRequest forShareToTimeline(FacebookSharePrivacy facebookSharePrivacy) {
        return new FacebookShareRequest(FacebookShareType.TIMELINE, facebookSharePrivacy, null, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToTimeline(FacebookSharePrivacy facebookSharePrivacy, String str) {
        return new FacebookShareRequest(FacebookShareType.TIMELINE, facebookSharePrivacy, null, str, null, false, ME);
    }
}
