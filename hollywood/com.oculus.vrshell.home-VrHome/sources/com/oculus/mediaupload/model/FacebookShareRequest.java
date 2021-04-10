package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class FacebookShareRequest implements Parcelable {
    public static final Parcelable.Creator<FacebookShareRequest> CREATOR = new Parcelable.Creator<FacebookShareRequest>() {
        /* class com.oculus.mediaupload.model.FacebookShareRequest.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FacebookShareRequest createFromParcel(Parcel in) {
            return new FacebookShareRequest(in);
        }

        @Override // android.os.Parcelable.Creator
        public FacebookShareRequest[] newArray(int size) {
            return new FacebookShareRequest[size];
        }
    };
    private static final String ME = "me";
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

    public static FacebookShareRequest forShareToStories() {
        return new FacebookShareRequest(FacebookShareType.STORY, null, null, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToStories(String gameId2) {
        return new FacebookShareRequest(FacebookShareType.STORY, null, null, gameId2, null, false, ME);
    }

    public static FacebookShareRequest forShareToTimeline(FacebookSharePrivacy privacy2) {
        return new FacebookShareRequest(FacebookShareType.TIMELINE, privacy2, null, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToTimeline(FacebookSharePrivacy privacy2, String gameId2) {
        return new FacebookShareRequest(FacebookShareType.TIMELINE, privacy2, null, gameId2, null, false, ME);
    }

    public static FacebookShareRequest forShareToGroup(String groupId2) {
        return new FacebookShareRequest(FacebookShareType.GROUP, null, groupId2, null, null, false, ME);
    }

    public static FacebookShareRequest forShareToGroup(String groupId2, String gameId2) {
        return new FacebookShareRequest(FacebookShareType.GROUP, null, groupId2, gameId2, null, false, ME);
    }

    public static FacebookShareRequest forShareToMessengerThread(String threadId2, boolean isGroupThread2) {
        return new FacebookShareRequest(FacebookShareType.MESSENGER_THREAD, null, null, null, threadId2, isGroupThread2, ME);
    }

    public static FacebookShareRequest forSync() {
        return new FacebookShareRequest(FacebookShareType.SYNC, null, null, null, null, false, ME);
    }

    private FacebookShareRequest(FacebookShareType type2, @Nullable FacebookSharePrivacy privacy2, @Nullable String groupId2, @Nullable String gameId2, @Nullable String threadId2, boolean isGroupThread2, String userId2) {
        this.type = type2;
        this.privacy = privacy2;
        this.groupId = groupId2;
        this.gameId = gameId2;
        this.threadId = threadId2;
        this.isGroupThread = isGroupThread2;
        this.userId = userId2;
    }

    public FacebookShareRequest(Parcel in) {
        boolean z = true;
        this.type = FacebookShareType.valueOf(in.readString());
        String privacyString = in.readString();
        this.privacy = privacyString != null ? FacebookSharePrivacy.valueOf(privacyString) : null;
        this.groupId = in.readString();
        this.gameId = in.readString();
        this.threadId = in.readString();
        this.isGroupThread = in.readInt() != 1 ? false : z;
        String maybeUserId = in.readString();
        if (maybeUserId != null) {
            this.userId = maybeUserId;
        } else {
            this.userId = ME;
        }
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type.name());
        if (this.privacy == null) {
            out.writeString(null);
        } else {
            out.writeString(this.privacy.name());
        }
        out.writeString(this.groupId);
        out.writeString(this.gameId);
        out.writeString(this.threadId);
        out.writeInt(this.isGroupThread ? 1 : 0);
        out.writeString(this.userId);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[8];
        objArr[0] = FacebookShareRequest.class.getSimpleName();
        objArr[1] = this.type.name();
        objArr[2] = this.privacy != null ? this.privacy.name() : null;
        objArr[3] = this.groupId;
        objArr[4] = this.gameId;
        objArr[5] = this.threadId;
        objArr[6] = Boolean.valueOf(this.isGroupThread);
        objArr[7] = this.userId;
        return String.format(locale, "%s[%s, privacy = %s, groupId = %s, gameId = %s, threadId = %s, isGroupThread = %s, userId= %s]", objArr);
    }
}
