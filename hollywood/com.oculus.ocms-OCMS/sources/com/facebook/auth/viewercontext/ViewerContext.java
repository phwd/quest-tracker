package com.facebook.auth.viewercontext;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.infer.annotation.PrivacySource;
import com.google.common.base.Preconditions;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

@Immutable
public class ViewerContext implements Parcelable {
    public static final Parcelable.Creator<ViewerContext> CREATOR = new Parcelable.Creator<ViewerContext>() {
        /* class com.facebook.auth.viewercontext.ViewerContext.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ViewerContext createFromParcel(Parcel parcel) {
            return new ViewerContext(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ViewerContext[] newArray(int i) {
            return new ViewerContext[i];
        }
    };
    public static final String OVERRIDDEN_VIEWER_CONTEXT_PARAM = "overridden_viewer_context";
    @PrivacySource
    @Nullable
    final String mAnalyticsClaim;
    @PrivacySource
    final String mAuthToken;
    final boolean mIsContextualProfileContext;
    final boolean mIsDittoContext;
    final boolean mIsPageContext;
    final boolean mIsRoomGuestContext;
    final boolean mIsTimelineViewAsContext;
    @PrivacySource
    final String mSessionCookiesString;
    @PrivacySource
    final String mSessionKey;
    @PrivacySource
    final String mSessionSecret;
    @PrivacySource
    final String mUserId;
    @PrivacySource
    final String mUsername;

    public int describeContents() {
        return 0;
    }

    private ViewerContext() {
        this.mUserId = null;
        this.mAuthToken = null;
        this.mSessionCookiesString = null;
        this.mIsPageContext = false;
        this.mIsDittoContext = false;
        this.mIsTimelineViewAsContext = false;
        this.mIsContextualProfileContext = false;
        this.mSessionSecret = null;
        this.mSessionKey = null;
        this.mUsername = null;
        this.mAnalyticsClaim = null;
        this.mIsRoomGuestContext = false;
    }

    private ViewerContext(ViewerContextBuilder viewerContextBuilder) {
        this.mUserId = (String) Preconditions.checkNotNull(viewerContextBuilder.getUserId());
        this.mAuthToken = (String) Preconditions.checkNotNull(viewerContextBuilder.getAuthToken());
        this.mSessionCookiesString = viewerContextBuilder.getSessionCookiesString();
        this.mIsPageContext = viewerContextBuilder.isPageContext();
        this.mIsDittoContext = viewerContextBuilder.isDittoContext();
        this.mIsTimelineViewAsContext = viewerContextBuilder.isTimelineViewAsContext();
        this.mIsContextualProfileContext = viewerContextBuilder.isContextualProfileContext();
        this.mSessionSecret = viewerContextBuilder.getSessionSecret();
        this.mSessionKey = viewerContextBuilder.getSessionKey();
        this.mUsername = viewerContextBuilder.getUsername();
        this.mAnalyticsClaim = viewerContextBuilder.getAnalyticsClaim();
        this.mIsRoomGuestContext = viewerContextBuilder.getIsRoomGuestContext();
    }

    private ViewerContext(Parcel parcel) {
        this.mUserId = parcel.readString();
        this.mAuthToken = parcel.readString();
        this.mSessionCookiesString = parcel.readString();
        boolean z = false;
        this.mIsPageContext = parcel.readInt() == 1;
        this.mIsDittoContext = parcel.readInt() == 1;
        this.mIsTimelineViewAsContext = parcel.readInt() == 1;
        this.mIsContextualProfileContext = parcel.readInt() == 1;
        this.mSessionSecret = parcel.readString();
        this.mSessionKey = parcel.readString();
        this.mUsername = parcel.readString();
        this.mAnalyticsClaim = parcel.readString();
        this.mIsRoomGuestContext = parcel.readInt() == 1 ? true : z;
    }

    public static ViewerContext decodeViewerContextJson(JSONObject jSONObject) throws JSONException {
        ViewerContextBuilder newBuilder = newBuilder();
        newBuilder.setUserId(jSONObject.getString("user_id"));
        newBuilder.setAuthToken(jSONObject.getString(ServiceContract.EXTRA_AUTH_TOKEN));
        newBuilder.setSessionSecret(jSONObject.getString("session_secret"));
        newBuilder.setSessionKey(jSONObject.getString("session_key"));
        newBuilder.setSessionCookiesString(jSONObject.getString("session_cookies_string"));
        newBuilder.setIsDittoContext(jSONObject.getBoolean("is_ditto_context"));
        newBuilder.setIsPageContext(jSONObject.getBoolean("is_page_context"));
        newBuilder.setIsTimelineViewAsContext(jSONObject.getBoolean("is_timeline_view_as_context"));
        newBuilder.setContextualProfileContext(jSONObject.getBoolean("is_contextual_profile_context"));
        newBuilder.setIsRoomGuestContext(jSONObject.getBoolean("is_room_guest_context"));
        return newBuilder.build();
    }

    public static ViewerContextBuilder newBuilder() {
        return new ViewerContextBuilder();
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }

    public String getSessionCookiesString() {
        return this.mSessionCookiesString;
    }

    public boolean isUserContext() {
        return !isPageContext() && !isDittoContext() && !isTimelineViewAsContext() && !isContextualProfileContext() && !isRoomGuestContext();
    }

    public boolean isPageContext() {
        return this.mIsPageContext;
    }

    public boolean isDittoContext() {
        return this.mIsDittoContext;
    }

    public boolean getIsRoomGuestContext() {
        return this.mIsRoomGuestContext;
    }

    public boolean isTimelineViewAsContext() {
        return this.mIsTimelineViewAsContext;
    }

    public boolean isContextualProfileContext() {
        return this.mIsContextualProfileContext;
    }

    public boolean isRoomGuestContext() {
        return this.mIsRoomGuestContext;
    }

    public String getSessionSecret() {
        return this.mSessionSecret;
    }

    public String getSessionKey() {
        return this.mSessionKey;
    }

    @Nullable
    public String getAnalyticsClaim() {
        return this.mAnalyticsClaim;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ViewerContext viewerContext = (ViewerContext) obj;
        if (this.mIsPageContext != viewerContext.mIsPageContext || this.mIsDittoContext != viewerContext.mIsDittoContext || this.mIsTimelineViewAsContext != viewerContext.mIsTimelineViewAsContext || this.mIsContextualProfileContext != viewerContext.mIsContextualProfileContext) {
            return false;
        }
        String str = this.mUserId;
        if (str == null ? viewerContext.mUserId != null : !str.equals(viewerContext.mUserId)) {
            return false;
        }
        String str2 = this.mAuthToken;
        if (str2 == null ? viewerContext.mAuthToken != null : !str2.equals(viewerContext.mAuthToken)) {
            return false;
        }
        String str3 = this.mSessionCookiesString;
        if (str3 == null ? viewerContext.mSessionCookiesString != null : !str3.equals(viewerContext.mSessionCookiesString)) {
            return false;
        }
        String str4 = this.mSessionSecret;
        if (str4 == null ? viewerContext.mSessionSecret != null : !str4.equals(viewerContext.mSessionSecret)) {
            return false;
        }
        String str5 = this.mSessionKey;
        if (str5 == null ? viewerContext.mSessionKey != null : !str5.equals(viewerContext.mSessionKey)) {
            return false;
        }
        String str6 = this.mAnalyticsClaim;
        if (str6 == null ? viewerContext.mAnalyticsClaim != null : !str6.equals(viewerContext.mAnalyticsClaim)) {
            return false;
        }
        String str7 = this.mUsername;
        String str8 = viewerContext.mUsername;
        if (str7 != null) {
            return str7.equals(str8);
        }
        if (str8 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.mUserId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mAuthToken;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mSessionCookiesString;
        int hashCode3 = (((((((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.mIsPageContext ? 1 : 0)) * 31) + (this.mIsDittoContext ? 1 : 0)) * 31) + (this.mIsTimelineViewAsContext ? 1 : 0)) * 31) + (this.mIsContextualProfileContext ? 1 : 0)) * 31;
        String str4 = this.mSessionSecret;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.mSessionKey;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.mUsername;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.mAnalyticsClaim;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mAuthToken);
        parcel.writeString(this.mSessionCookiesString);
        parcel.writeInt(this.mIsPageContext ? 1 : 0);
        parcel.writeInt(this.mIsDittoContext ? 1 : 0);
        parcel.writeInt(this.mIsTimelineViewAsContext ? 1 : 0);
        parcel.writeInt(this.mIsContextualProfileContext ? 1 : 0);
        parcel.writeString(this.mSessionSecret);
        parcel.writeString(this.mSessionKey);
        parcel.writeString(this.mUsername);
        parcel.writeString(this.mAnalyticsClaim);
        parcel.writeInt(this.mIsRoomGuestContext ? 1 : 0);
    }

    @NotThreadSafe
    public static class ViewerContextBuilder {
        @Nullable
        private String mAnalyticsClaim;
        private String mAuthToken;
        private boolean mIsContextualProfileContext;
        private boolean mIsDittoContext;
        private boolean mIsPageContext;
        private boolean mIsRoomGuestContext;
        private boolean mIsTimelineViewAsContext;
        private String mSessionCookiesString;
        private String mSessionKey;
        private String mSessionSecret;
        private String mUserId;
        private String mUsername;

        ViewerContextBuilder() {
        }

        public ViewerContextBuilder setFrom(ViewerContext viewerContext) {
            this.mUserId = viewerContext.getUserId();
            this.mAuthToken = viewerContext.getAuthToken();
            this.mSessionCookiesString = viewerContext.getSessionCookiesString();
            this.mIsPageContext = viewerContext.isPageContext();
            this.mIsDittoContext = viewerContext.isDittoContext();
            this.mIsTimelineViewAsContext = viewerContext.isTimelineViewAsContext();
            this.mIsContextualProfileContext = viewerContext.isContextualProfileContext();
            this.mSessionSecret = viewerContext.getSessionSecret();
            this.mSessionKey = viewerContext.getSessionKey();
            this.mUsername = viewerContext.getUsername();
            this.mAnalyticsClaim = viewerContext.getAnalyticsClaim();
            this.mIsRoomGuestContext = viewerContext.getIsRoomGuestContext();
            return this;
        }

        public String getUserId() {
            return this.mUserId;
        }

        public ViewerContextBuilder setUserId(String str) {
            this.mUserId = str;
            return this;
        }

        public String getAuthToken() {
            return this.mAuthToken;
        }

        public ViewerContextBuilder setAuthToken(String str) {
            this.mAuthToken = str;
            return this;
        }

        public String getSessionCookiesString() {
            return this.mSessionCookiesString;
        }

        public ViewerContextBuilder setSessionCookiesString(String str) {
            this.mSessionCookiesString = str;
            return this;
        }

        public boolean isPageContext() {
            return this.mIsPageContext;
        }

        public boolean isDittoContext() {
            return this.mIsDittoContext;
        }

        public ViewerContextBuilder setIsPageContext(boolean z) {
            this.mIsPageContext = z;
            return this;
        }

        public ViewerContextBuilder setIsDittoContext(boolean z) {
            this.mIsDittoContext = z;
            return this;
        }

        public ViewerContextBuilder setIsRoomGuestContext(boolean z) {
            this.mIsRoomGuestContext = z;
            return this;
        }

        public boolean isTimelineViewAsContext() {
            return this.mIsTimelineViewAsContext;
        }

        public ViewerContextBuilder setIsTimelineViewAsContext(boolean z) {
            this.mIsTimelineViewAsContext = z;
            return this;
        }

        public boolean isContextualProfileContext() {
            return this.mIsContextualProfileContext;
        }

        public boolean getIsRoomGuestContext() {
            return this.mIsRoomGuestContext;
        }

        public ViewerContextBuilder setContextualProfileContext(boolean z) {
            this.mIsContextualProfileContext = z;
            return this;
        }

        public String getSessionSecret() {
            return this.mSessionSecret;
        }

        public ViewerContextBuilder setSessionSecret(String str) {
            this.mSessionSecret = str;
            return this;
        }

        public String getSessionKey() {
            return this.mSessionKey;
        }

        public ViewerContextBuilder setSessionKey(String str) {
            this.mSessionKey = str;
            return this;
        }

        public ViewerContextBuilder setAnalyticsClaim(@Nullable String str) {
            this.mAnalyticsClaim = str;
            return this;
        }

        @Nullable
        public String getAnalyticsClaim() {
            return this.mAnalyticsClaim;
        }

        public String getUsername() {
            return this.mUsername;
        }

        public ViewerContextBuilder setUsername(String str) {
            this.mUsername = str;
            return this;
        }

        public ViewerContext build() {
            return new ViewerContext(this);
        }
    }
}
