package com.oculus.panelapp.socialandroidbackpanel.views.social;

import X.AbstractC12091uu;
import X.AbstractC12101uv;
import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialViewerInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.socialandroidbackpanel.graphql.SocialUserRequestFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class SocialUserViewModel extends SocialViewModel {
    public static final String QUERY_HANDLE_KEY = "social-user";
    public String mAlias;
    public boolean mHasSeenVRInviteProfileNux;
    public String mId;
    public Map<SocialViewModelDataObserver, AbstractC12091uu> mObservers = new HashMap();
    public String mProfilePhotoUrl;
    public final SocialUserRequestFactory mSocialUserRequestFactory;
    public UserType mUserType = UserType.VIEWER;

    public interface SocialViewModelDataObserver {
        void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel);
    }

    public enum UserType {
        USER,
        VIEWER
    }

    private Function<String, AsyncQueryHandle> fetchSocialViewer() {
        return new Function() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.social.$$Lambda$SocialUserViewModel$aOLgx3uyiboYdWM_Rey3Quf8MXo2 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialUserViewModel.this.lambda$fetchSocialViewer$0$SocialUserViewModel((String) obj);
            }
        };
    }

    public void fetch() {
        if (this.mUserType == UserType.VIEWER) {
            registerQueryHandle(QUERY_HANDLE_KEY, new Function() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.social.$$Lambda$SocialUserViewModel$aOLgx3uyiboYdWM_Rey3Quf8MXo2 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SocialUserViewModel.this.lambda$fetchSocialViewer$0$SocialUserViewModel((String) obj);
                }
            }, true);
        }
    }

    @Bindable
    public String getAlias() {
        return this.mAlias;
    }

    @Bindable
    public boolean getHasSeenVRInviteProfileNux() {
        return this.mHasSeenVRInviteProfileNux;
    }

    @Bindable
    public String getId() {
        return this.mId;
    }

    @Bindable
    public String getProfilePhotoUrl() {
        return this.mProfilePhotoUrl;
    }

    @Bindable
    public UserType getUserType() {
        return this.mUserType;
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchSocialViewer$0$SocialUserViewModel(final String str) {
        SocialUserRequestFactory socialUserRequestFactory = this.mSocialUserRequestFactory;
        return HorizonContentProviderHelper.fetchSocialViewer(socialUserRequestFactory.mContext, new HorizonContentProviderHelper.FetchSocialViewerInfoCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialUserViewModel.this.clearHandle(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialViewerInfoCallback
            public void onSuccess(SocialViewerInfo socialViewerInfo) {
                SocialUserViewModel socialUserViewModel = SocialUserViewModel.this;
                socialUserViewModel.mId = null;
                socialUserViewModel.mAlias = socialViewerInfo.mAlias;
                socialUserViewModel.mProfilePhotoUrl = socialViewerInfo.mProfilePhotoUrl;
                socialUserViewModel.mHasSeenVRInviteProfileNux = socialViewerInfo.mHasSeenVRInviteProfileNux;
                socialUserViewModel.notifyChange();
                SocialUserViewModel.this.clearHandle(str);
            }
        });
    }

    public void registerObserver(final SocialViewModelDataObserver socialViewModelDataObserver) {
        AnonymousClass1 r1 = new AbstractC12091uu() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel.AnonymousClass1 */

            @Override // X.AbstractC12091uu
            public void onPropertyChanged(AbstractC12101uv r3, int i) {
                socialViewModelDataObserver.onSocialUserViewModelDataUpdated(SocialUserViewModel.this);
            }
        };
        addOnPropertyChangedCallback(r1);
        this.mObservers.put(socialViewModelDataObserver, r1);
    }

    @Bindable
    public void setAlias(String str) {
        this.mAlias = str;
        notifyPropertyChanged(219);
    }

    @Bindable
    public void setHasSeenVRInviteProfileNux(boolean z) {
        this.mHasSeenVRInviteProfileNux = z;
        notifyPropertyChanged(206);
    }

    @Bindable
    public void setId(String str) {
        this.mId = str;
        notifyPropertyChanged(190);
    }

    @Bindable
    public void setProfilePhotoUrl(String str) {
        this.mProfilePhotoUrl = str;
        notifyPropertyChanged(202);
    }

    @Bindable
    public void setUserType(@NonNull UserType userType) {
        this.mUserType = userType;
        notifyPropertyChanged(208);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SocialUserViewModel{mId='");
        sb.append(this.mId);
        sb.append('\'');
        sb.append(", mAlias='");
        sb.append(this.mAlias);
        sb.append('\'');
        sb.append(", mProfilePhotoUrl='");
        sb.append(this.mProfilePhotoUrl);
        sb.append('\'');
        sb.append(", mUserType=");
        sb.append(this.mUserType);
        sb.append('}');
        return sb.toString();
    }

    public void unregisterObserver(SocialViewModelDataObserver socialViewModelDataObserver) {
        Optional.ofNullable(this.mObservers.remove(socialViewModelDataObserver)).ifPresent(new Consumer() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.social.$$Lambda$QbmEgHNvYpXHed6CpCWRTgSY62k2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SocialUserViewModel.this.removeOnPropertyChangedCallback((AbstractC12091uu) obj);
            }
        });
    }

    public SocialUserViewModel updateWith(SocialUser socialUser) {
        this.mId = socialUser.mID;
        this.mAlias = socialUser.mAlias;
        this.mProfilePhotoUrl = socialUser.mProfilePhotoURL;
        notifyChange();
        return this;
    }

    public SocialUserViewModel(SocialUserRequestFactory socialUserRequestFactory) {
        this.mSocialUserRequestFactory = socialUserRequestFactory;
    }
}
