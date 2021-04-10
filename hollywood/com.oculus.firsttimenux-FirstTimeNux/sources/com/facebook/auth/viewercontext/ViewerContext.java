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
        public ViewerContext createFromParcel(Parcel in) {
            return new ViewerContext(in);
        }

        @Override // android.os.Parcelable.Creator
        public ViewerContext[] newArray(int size) {
            return new ViewerContext[size];
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
    final boolean mIsFoxContext;
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

    private ViewerContext() {
        this.mUserId = null;
        this.mAuthToken = null;
        this.mSessionCookiesString = null;
        this.mIsPageContext = false;
        this.mIsFoxContext = false;
        this.mIsDittoContext = false;
        this.mIsTimelineViewAsContext = false;
        this.mIsContextualProfileContext = false;
        this.mSessionSecret = null;
        this.mSessionKey = null;
        this.mUsername = null;
        this.mAnalyticsClaim = null;
        this.mIsRoomGuestContext = false;
    }

    private ViewerContext(ViewerContextBuilder builder) {
        this.mUserId = (String) Preconditions.checkNotNull(builder.getUserId());
        this.mAuthToken = (String) Preconditions.checkNotNull(builder.getAuthToken());
        this.mSessionCookiesString = builder.getSessionCookiesString();
        this.mIsPageContext = builder.isPageContext();
        this.mIsFoxContext = builder.isFoxContext();
        this.mIsDittoContext = builder.isDittoContext();
        this.mIsTimelineViewAsContext = builder.isTimelineViewAsContext();
        this.mIsContextualProfileContext = builder.isContextualProfileContext();
        this.mSessionSecret = builder.getSessionSecret();
        this.mSessionKey = builder.getSessionKey();
        this.mUsername = builder.getUsername();
        this.mAnalyticsClaim = builder.getAnalyticsClaim();
        this.mIsRoomGuestContext = builder.getIsRoomGuestContext();
    }

    private ViewerContext(Parcel in) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        this.mUserId = in.readString();
        this.mAuthToken = in.readString();
        this.mSessionCookiesString = in.readString();
        this.mIsPageContext = in.readInt() == 1;
        if (in.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mIsFoxContext = z;
        if (in.readInt() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mIsDittoContext = z2;
        if (in.readInt() == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mIsTimelineViewAsContext = z3;
        if (in.readInt() == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        this.mIsContextualProfileContext = z4;
        this.mSessionSecret = in.readString();
        this.mSessionKey = in.readString();
        this.mUsername = in.readString();
        this.mAnalyticsClaim = in.readString();
        this.mIsRoomGuestContext = in.readInt() != 1 ? false : z5;
    }

    public static ViewerContext decodeViewerContextJson(JSONObject jsonObject) throws JSONException {
        ViewerContextBuilder vcBuilder = newBuilder();
        vcBuilder.setUserId(jsonObject.getString(ServiceContract.EXTRA_USER_ID));
        vcBuilder.setAuthToken(jsonObject.getString(ServiceContract.EXTRA_AUTH_TOKEN));
        vcBuilder.setSessionSecret(jsonObject.getString("session_secret"));
        vcBuilder.setSessionKey(jsonObject.getString("session_key"));
        vcBuilder.setSessionCookiesString(jsonObject.getString("session_cookies_string"));
        vcBuilder.setIsDittoContext(jsonObject.getBoolean("is_ditto_context"));
        vcBuilder.setIsFoxContext(jsonObject.getBoolean("is_fox_context"));
        vcBuilder.setIsPageContext(jsonObject.getBoolean("is_page_context"));
        vcBuilder.setIsTimelineViewAsContext(jsonObject.getBoolean("is_timeline_view_as_context"));
        vcBuilder.setContextualProfileContext(jsonObject.getBoolean("is_contextual_profile_context"));
        vcBuilder.setIsRoomGuestContext(jsonObject.getBoolean("is_room_guest_context"));
        return vcBuilder.build();
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
        if (isPageContext() || isFoxContext() || isDittoContext() || isTimelineViewAsContext() || isContextualProfileContext() || isRoomGuestContext()) {
            return false;
        }
        return true;
    }

    public boolean isPageContext() {
        return this.mIsPageContext;
    }

    public boolean isFoxContext() {
        return this.mIsFoxContext;
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

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewerContext that = (ViewerContext) o;
        if (this.mIsPageContext != that.mIsPageContext || this.mIsFoxContext != that.mIsFoxContext || this.mIsDittoContext != that.mIsDittoContext || this.mIsTimelineViewAsContext != that.mIsTimelineViewAsContext || this.mIsContextualProfileContext != that.mIsContextualProfileContext) {
            return false;
        }
        if (this.mUserId != null) {
            if (!this.mUserId.equals(that.mUserId)) {
                return false;
            }
        } else if (that.mUserId != null) {
            return false;
        }
        if (this.mAuthToken != null) {
            if (!this.mAuthToken.equals(that.mAuthToken)) {
                return false;
            }
        } else if (that.mAuthToken != null) {
            return false;
        }
        if (this.mSessionCookiesString != null) {
            if (!this.mSessionCookiesString.equals(that.mSessionCookiesString)) {
                return false;
            }
        } else if (that.mSessionCookiesString != null) {
            return false;
        }
        if (this.mSessionSecret != null) {
            if (!this.mSessionSecret.equals(that.mSessionSecret)) {
                return false;
            }
        } else if (that.mSessionSecret != null) {
            return false;
        }
        if (this.mSessionKey != null) {
            if (!this.mSessionKey.equals(that.mSessionKey)) {
                return false;
            }
        } else if (that.mSessionKey != null) {
            return false;
        }
        if (this.mAnalyticsClaim != null) {
            if (!this.mAnalyticsClaim.equals(that.mAnalyticsClaim)) {
                return false;
            }
        } else if (that.mAnalyticsClaim != null) {
            return false;
        }
        if (this.mUsername != null) {
            z = this.mUsername.equals(that.mUsername);
        } else if (that.mUsername != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = 1;
        int i11 = 0;
        if (this.mUserId != null) {
            result = this.mUserId.hashCode();
        } else {
            result = 0;
        }
        int i12 = result * 31;
        if (this.mAuthToken != null) {
            i = this.mAuthToken.hashCode();
        } else {
            i = 0;
        }
        int i13 = (i12 + i) * 31;
        if (this.mSessionCookiesString != null) {
            i2 = this.mSessionCookiesString.hashCode();
        } else {
            i2 = 0;
        }
        int i14 = (i13 + i2) * 31;
        if (this.mIsPageContext) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i15 = (i14 + i3) * 31;
        if (this.mIsFoxContext) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i16 = (i15 + i4) * 31;
        if (this.mIsDittoContext) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i17 = (i16 + i5) * 31;
        if (this.mIsTimelineViewAsContext) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i18 = (i17 + i6) * 31;
        if (!this.mIsContextualProfileContext) {
            i10 = 0;
        }
        int i19 = (i18 + i10) * 31;
        if (this.mSessionSecret != null) {
            i7 = this.mSessionSecret.hashCode();
        } else {
            i7 = 0;
        }
        int i20 = (i19 + i7) * 31;
        if (this.mSessionKey != null) {
            i8 = this.mSessionKey.hashCode();
        } else {
            i8 = 0;
        }
        int i21 = (i20 + i8) * 31;
        if (this.mUsername != null) {
            i9 = this.mUsername.hashCode();
        } else {
            i9 = 0;
        }
        int i22 = (i21 + i9) * 31;
        if (this.mAnalyticsClaim != null) {
            i11 = this.mAnalyticsClaim.hashCode();
        }
        return i22 + i11;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        dest.writeString(this.mUserId);
        dest.writeString(this.mAuthToken);
        dest.writeString(this.mSessionCookiesString);
        dest.writeInt(this.mIsPageContext ? 1 : 0);
        if (this.mIsFoxContext) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (this.mIsDittoContext) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (this.mIsTimelineViewAsContext) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        dest.writeInt(i3);
        if (this.mIsContextualProfileContext) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        dest.writeInt(i4);
        dest.writeString(this.mSessionSecret);
        dest.writeString(this.mSessionKey);
        dest.writeString(this.mUsername);
        dest.writeString(this.mAnalyticsClaim);
        if (!this.mIsRoomGuestContext) {
            i5 = 0;
        }
        dest.writeInt(i5);
    }

    @NotThreadSafe
    public static class ViewerContextBuilder {
        @Nullable
        private String mAnalyticsClaim;
        private String mAuthToken;
        private boolean mIsContextualProfileContext;
        private boolean mIsDittoContext;
        private boolean mIsFoxContext;
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
            this.mIsFoxContext = viewerContext.isFoxContext();
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

        public ViewerContextBuilder setUserId(String userId) {
            this.mUserId = userId;
            return this;
        }

        public String getAuthToken() {
            return this.mAuthToken;
        }

        public ViewerContextBuilder setAuthToken(String authToken) {
            this.mAuthToken = authToken;
            return this;
        }

        public String getSessionCookiesString() {
            return this.mSessionCookiesString;
        }

        public ViewerContextBuilder setSessionCookiesString(String sessionCookiesString) {
            this.mSessionCookiesString = sessionCookiesString;
            return this;
        }

        public boolean isPageContext() {
            return this.mIsPageContext;
        }

        public boolean isFoxContext() {
            return this.mIsFoxContext;
        }

        public boolean isDittoContext() {
            return this.mIsDittoContext;
        }

        public ViewerContextBuilder setIsPageContext(boolean isPageContext) {
            this.mIsPageContext = isPageContext;
            return this;
        }

        public ViewerContextBuilder setIsFoxContext(boolean isFoxContext) {
            this.mIsFoxContext = isFoxContext;
            return this;
        }

        public ViewerContextBuilder setIsDittoContext(boolean isDittoContext) {
            this.mIsDittoContext = isDittoContext;
            return this;
        }

        public ViewerContextBuilder setIsRoomGuestContext(boolean isRoomGuestContext) {
            this.mIsRoomGuestContext = isRoomGuestContext;
            return this;
        }

        public boolean isTimelineViewAsContext() {
            return this.mIsTimelineViewAsContext;
        }

        public ViewerContextBuilder setIsTimelineViewAsContext(boolean isTimelineViewAsContext) {
            this.mIsTimelineViewAsContext = isTimelineViewAsContext;
            return this;
        }

        public boolean isContextualProfileContext() {
            return this.mIsContextualProfileContext;
        }

        public boolean getIsRoomGuestContext() {
            return this.mIsRoomGuestContext;
        }

        public ViewerContextBuilder setContextualProfileContext(boolean isContextualProfileContext) {
            this.mIsContextualProfileContext = isContextualProfileContext;
            return this;
        }

        public String getSessionSecret() {
            return this.mSessionSecret;
        }

        public ViewerContextBuilder setSessionSecret(String sessionSecret) {
            this.mSessionSecret = sessionSecret;
            return this;
        }

        public String getSessionKey() {
            return this.mSessionKey;
        }

        public ViewerContextBuilder setSessionKey(String sessionKey) {
            this.mSessionKey = sessionKey;
            return this;
        }

        public ViewerContextBuilder setAnalyticsClaim(@Nullable String analyticsClaim) {
            this.mAnalyticsClaim = analyticsClaim;
            return this;
        }

        @Nullable
        public String getAnalyticsClaim() {
            return this.mAnalyticsClaim;
        }

        public String getUsername() {
            return this.mUsername;
        }

        public ViewerContextBuilder setUsername(String username) {
            this.mUsername = username;
            return this;
        }

        public ViewerContext build() {
            return new ViewerContext(this);
        }
    }
}
