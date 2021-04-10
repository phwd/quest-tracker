package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.HashMap;
import java.util.List;

public class OCFriendsLoader {
    public final Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchSocialFriendsAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle;
    @Nullable
    public SocialLogger mSocialLogger;

    public interface FriendLoadingCallback {
        void onError();

        void onSuccess(List<SocialUser> list);
    }

    public void destroy() {
        this.mSocialLogger = null;
        clearFetchSocialFriendsAsyncQueryHandle();
        clearFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle();
    }

    public void loadFriends(final String str, @Nullable String[] strArr, @Nullable final Integer num, final FriendLoadingCallback friendLoadingCallback) {
        clearFetchSocialFriendsAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchSocialFriendsAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialFriends(this.mContext, strArr, num, new HorizonContentProviderHelper.FetchSocialFriendsCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onError(Exception exc) {
                try {
                    OCFriendsLoader.this.logFetchFailure(Long.toString(System.currentTimeMillis() - currentTimeMillis), num, true, exc);
                    friendLoadingCallback.onError();
                } finally {
                    OCFriendsLoader.this.clearFetchSocialFriendsAsyncQueryHandle();
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onSuccess(List<SocialUser> list) {
                try {
                    String l = Long.toString(System.currentTimeMillis() - currentTimeMillis);
                    list.size();
                    OCFriendsLoader.this.logFetchSuccess(l, num, true);
                    friendLoadingCallback.onSuccess(list);
                } finally {
                    OCFriendsLoader.this.clearFetchSocialFriendsAsyncQueryHandle();
                }
            }
        });
    }

    public void loadFriendsWithoutRichPresence(final String str, @Nullable String[] strArr, @Nullable final Integer num, final FriendLoadingCallback friendLoadingCallback) {
        clearFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialFriendsWithoutRichPresence(this.mContext, strArr, num, new HorizonContentProviderHelper.FetchSocialFriendsCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onError(Exception exc) {
                try {
                    OCFriendsLoader.this.logFetchFailure(Long.toString(System.currentTimeMillis() - currentTimeMillis), num, false, exc);
                    friendLoadingCallback.onError();
                } finally {
                    OCFriendsLoader.this.clearFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle();
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialFriendsCallback
            public void onSuccess(List<SocialUser> list) {
                try {
                    String l = Long.toString(System.currentTimeMillis() - currentTimeMillis);
                    list.size();
                    OCFriendsLoader.this.logFetchSuccess(l, num, false);
                    friendLoadingCallback.onSuccess(list);
                } finally {
                    OCFriendsLoader.this.clearFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialFriendsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSocialFriendsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSocialFriendsAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSocialFriendsWithoutRichPresenceAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logFetchFailure(String str, @Nullable Integer num, boolean z, Exception exc) {
        String str2;
        if (this.mSocialLogger != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(LoggingConstants.TIME_TO_COMPLETE_MS, str);
            if (num != null) {
                str2 = num.toString();
            } else {
                str2 = "null";
            }
            hashMap.put("first", str2);
            hashMap.put(LoggingConstants.INCLUDES_RICH_PRESENCE, Boolean.toString(z));
            this.mSocialLogger.logActionFailure(ActionId.FETCH_OCULUS_FRIENDS, ClickEventButtonId.NO_BUTTON, SurfaceType.BACKGROUND_FETCHER, exc, hashMap);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logFetchSuccess(String str, @Nullable Integer num, boolean z) {
        String str2;
        if (this.mSocialLogger != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(LoggingConstants.TIME_TO_COMPLETE_MS, str);
            if (num != null) {
                str2 = num.toString();
            } else {
                str2 = "null";
            }
            hashMap.put("first", str2);
            hashMap.put(LoggingConstants.INCLUDES_RICH_PRESENCE, Boolean.toString(z));
            this.mSocialLogger.logActionSuccess(ActionId.FETCH_OCULUS_FRIENDS, ClickEventButtonId.NO_BUTTON, SurfaceType.BACKGROUND_FETCHER, hashMap);
        }
    }

    public OCFriendsLoader(Context context, SocialLogger socialLogger) {
        this.mContext = context;
        this.mSocialLogger = socialLogger;
    }
}
