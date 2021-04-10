package com.oculus.panelapp.androiddialog.dialogs.social;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialViewerInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class SocialUserViewModel extends SocialViewModel {
    private static String QUERY_HANDLE_KEY = "social-user";
    private String mAlias;
    private boolean mHasSeenVRInviteProfileNux;
    private String mId;
    private Map<SocialViewModelDataObserver, Observable.OnPropertyChangedCallback> mObservers = new HashMap();
    private String mProfilePhotoUrl;
    private final SocialUserRequestFactory mSocialUserRequestFactory;
    private UserType mUserType = UserType.VIEWER;

    public interface SocialViewModelDataObserver {
        void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel);
    }

    public enum UserType {
        USER,
        VIEWER
    }

    public void registerObserver(final SocialViewModelDataObserver socialViewModelDataObserver) {
        AnonymousClass1 r0 = new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel.AnonymousClass1 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                socialViewModelDataObserver.onSocialUserViewModelDataUpdated(SocialUserViewModel.this);
            }
        };
        addOnPropertyChangedCallback(r0);
        this.mObservers.put(socialViewModelDataObserver, r0);
    }

    public void unregisterObserver(SocialViewModelDataObserver socialViewModelDataObserver) {
        Optional.ofNullable(this.mObservers.remove(socialViewModelDataObserver)).ifPresent(new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$pQs238vFCHA_IGdnIFgBZ56tc */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SocialUserViewModel.this.removeOnPropertyChangedCallback((Observable.OnPropertyChangedCallback) obj);
            }
        });
    }

    public SocialUserViewModel(SocialUserRequestFactory socialUserRequestFactory) {
        this.mSocialUserRequestFactory = socialUserRequestFactory;
    }

    public void fetch() {
        if (this.mUserType == UserType.VIEWER) {
            registerQueryHandle(QUERY_HANDLE_KEY, fetchSocialViewer(), true);
        }
    }

    private Function<String, AsyncQueryHandle> fetchSocialViewer() {
        return new Function() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialUserViewModel$Gr1Jby8lvjvJcLCvO2dvw65GZyc */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialUserViewModel.this.lambda$fetchSocialViewer$86$SocialUserViewModel((String) obj);
            }
        };
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchSocialViewer$86$SocialUserViewModel(final String str) {
        return this.mSocialUserRequestFactory.fetchSocialViewer(new HorizonContentProviderHelper.FetchSocialViewerInfoCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialViewerInfoCallback
            public void onSuccess(SocialViewerInfo socialViewerInfo) {
                SocialUserViewModel.this.mId = null;
                SocialUserViewModel.this.mAlias = socialViewerInfo.getAlias();
                SocialUserViewModel.this.mProfilePhotoUrl = socialViewerInfo.getProfilePhotoUrl();
                SocialUserViewModel.this.mHasSeenVRInviteProfileNux = socialViewerInfo.getHasSeenVRInviteProfileNux();
                SocialUserViewModel.this.notifyChange();
                SocialUserViewModel.this.clearHandle(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialUserViewModel.this.clearHandle(str);
            }
        });
    }

    public SocialUserViewModel updateWith(SocialUser socialUser) {
        this.mId = socialUser.getID();
        this.mAlias = socialUser.getAlias();
        this.mProfilePhotoUrl = socialUser.getProfilePhotoURL();
        notifyChange();
        return this;
    }

    @Bindable
    public String getId() {
        return this.mId;
    }

    @Bindable
    public void setId(String str) {
        this.mId = str;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getAlias() {
        return this.mAlias;
    }

    @Bindable
    public void setAlias(String str) {
        this.mAlias = str;
        notifyPropertyChanged(BR.alias);
    }

    @Bindable
    public String getProfilePhotoUrl() {
        return this.mProfilePhotoUrl;
    }

    @Bindable
    public void setProfilePhotoUrl(String str) {
        this.mProfilePhotoUrl = str;
        notifyPropertyChanged(BR.profilePhotoUrl);
    }

    @Bindable
    public boolean getHasSeenVRInviteProfileNux() {
        return this.mHasSeenVRInviteProfileNux;
    }

    @Bindable
    public void setHasSeenVRInviteProfileNux(boolean z) {
        this.mHasSeenVRInviteProfileNux = z;
        notifyPropertyChanged(BR.hasSeenVRInviteProfileNux);
    }

    @Bindable
    public UserType getUserType() {
        return this.mUserType;
    }

    @Bindable
    public void setUserType(@NonNull UserType userType) {
        this.mUserType = userType;
        notifyPropertyChanged(BR.userType);
    }

    public String toString() {
        return "SocialUserViewModel{mId='" + this.mId + '\'' + ", mAlias='" + this.mAlias + '\'' + ", mProfilePhotoUrl='" + this.mProfilePhotoUrl + '\'' + ", mUserType=" + this.mUserType + '}';
    }
}
