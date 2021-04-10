package com.oculus.panelapp.socialandroidbackpanel.views.party_privacy;

import X.AnonymousClass006;
import X.AnonymousClass1uc;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;
import com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper;
import com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel;
import com.oculus.tablet.view.ViewModelLifecycle;
import org.json.JSONException;
import org.json.JSONObject;

public class PartyPrivacyViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(PartyPrivacyViewModel.class);
    public String mCorrelationId;
    public boolean mHasLinkInvite;
    public String mPartyId;
    public PartyPrivacyType mPartyType;
    public String mPartyUrl;
    public String mSource;
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    @FunctionalInterface
    public interface Callback {
        void execute();
    }

    public void toggleLinkInvite(GraphQLService graphQLService, Callback callback, Callback callback2) {
        if (!TextUtils.isEmpty(this.mPartyId)) {
            boolean z = this.mHasLinkInvite;
            setHasLinkInvite(!z);
            setHasLinkInviteMutation(graphQLService, this.mPartyId, !z, callback, callback2);
            return;
        }
        Log.e(TAG, "attempted to set has_active_link_sharing with null party id");
        callback2.execute();
    }

    public void togglePartyType(GraphQLService graphQLService, Callback callback, Callback callback2) {
        if (!TextUtils.isEmpty(this.mPartyId)) {
            PartyPrivacyType partyPrivacyType = this.mPartyType;
            PartyPrivacyType partyPrivacyType2 = PartyPrivacyType.CLOSED;
            if (partyPrivacyType == partyPrivacyType2) {
                partyPrivacyType2 = PartyPrivacyType.JOINABLE_BY_FRIENDS;
            }
            setPartyType(partyPrivacyType2);
            setPartyPrivacyTypeMutation(graphQLService, this.mPartyId, partyPrivacyType2, callback, callback2);
            return;
        }
        Log.e(TAG, "attempted to set party type with null party id");
        callback2.execute();
    }

    /* renamed from: com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialandroidbackpanel$graphql$PartyPrivacyType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType[] r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.AnonymousClass4.$SwitchMap$com$oculus$panelapp$socialandroidbackpanel$graphql$PartyPrivacyType = r2
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.OPEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.AnonymousClass4.<clinit>():void");
        }
    }

    private PartyPrivacyQueryHelper.PartyPrivacyQueryCallback partyPrivacyLinkInviteMutationCallback(final Callback callback, final Callback callback2) {
        return new PartyPrivacyQueryHelper.PartyPrivacyQueryCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.AnonymousClass1 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onFailure(String str) {
                callback2.execute();
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onSuccess(JSONObject jSONObject) {
                jSONObject.toString();
                callback.execute();
            }
        };
    }

    private PartyPrivacyQueryHelper.PartyPrivacyQueryCallback partyPrivacyMutationCallback(final Callback callback, final Callback callback2) {
        return new PartyPrivacyQueryHelper.PartyPrivacyQueryCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.AnonymousClass2 */

            public /* synthetic */ void lambda$onSuccess$0$PartyPrivacyViewModel$2(JSONObject jSONObject) {
                PartyPrivacyViewModel.this.setPartyType(PartyPrivacyType.fromString(jSONObject.optString("party_type")));
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onFailure(String str) {
                Log.e(PartyPrivacyViewModel.TAG, AnonymousClass006.A07("onError: An error occurred while setting party-privacy type: ", str));
                PartyPrivacyViewModel.this.mainHandler.post(new Runnable() {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyViewModel$2$rI_AFKcZrAEyD9K27oupLZaMoY2 */

                    public final void run() {
                        PartyPrivacyViewModel.Callback.this.execute();
                    }
                });
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onSuccess(JSONObject jSONObject) {
                jSONObject.toString();
                PartyPrivacyViewModel.this.mainHandler.post(new Runnable(jSONObject) {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyViewModel$2$77_GKhrGOGUNtcGG_1rq92oB4A2 */
                    public final /* synthetic */ JSONObject f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        PartyPrivacyViewModel.AnonymousClass2.this.lambda$onSuccess$0$PartyPrivacyViewModel$2(this.f$1);
                    }
                });
                callback.execute();
            }
        };
    }

    private PartyPrivacyQueryHelper.PartyPrivacyQueryCallback partyPrivacyQueryCallback(final Callback callback, final Callback callback2) {
        return new PartyPrivacyQueryHelper.PartyPrivacyQueryCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.AnonymousClass3 */

            public /* synthetic */ void lambda$onSuccess$0$PartyPrivacyViewModel$3(String str) {
                PartyPrivacyViewModel.this.setPartyUrl(str);
            }

            public /* synthetic */ void lambda$onSuccess$1$PartyPrivacyViewModel$3(PartyPrivacyType partyPrivacyType) {
                PartyPrivacyViewModel.this.setPartyType(partyPrivacyType);
            }

            public /* synthetic */ void lambda$onSuccess$2$PartyPrivacyViewModel$3(boolean z) {
                PartyPrivacyViewModel.this.setHasLinkInvite(z);
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onFailure(String str) {
                Log.e(PartyPrivacyViewModel.TAG, AnonymousClass006.A07("onError: An error occurred while fetching party-privacy info: ", str));
                callback2.execute();
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyQueryHelper.PartyPrivacyQueryCallback
            public void onSuccess(JSONObject jSONObject) {
                String str;
                String str2;
                try {
                    PartyPrivacyViewModel.this.mainHandler.post(new Runnable(jSONObject.getString(PartyPrivacyQueryHelper.PARTY_URL_PARAM)) {
                        /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyViewModel$3$0Obp8021v3tVluclin7kjzcluVM2 */
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            PartyPrivacyViewModel.AnonymousClass3.this.lambda$onSuccess$0$PartyPrivacyViewModel$3(this.f$1);
                        }
                    });
                    PartyPrivacyViewModel.this.mainHandler.post(new Runnable(PartyPrivacyType.fromString(jSONObject.getString("party_type"))) {
                        /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyViewModel$3$JFzroIMgCATfqt9wpmSmcY3xX4g2 */
                        public final /* synthetic */ PartyPrivacyType f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            PartyPrivacyViewModel.AnonymousClass3.this.lambda$onSuccess$1$PartyPrivacyViewModel$3(this.f$1);
                        }
                    });
                    PartyPrivacyViewModel.this.mainHandler.post(new Runnable(jSONObject.getBoolean(PartyPrivacyQueryHelper.PARTY_HAS_ACTIVE_LINK_SHARING_PARAM)) {
                        /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyViewModel$3$yOQoHD03woWxDFSn5ZSCUoVHVNs2 */
                        public final /* synthetic */ boolean f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            PartyPrivacyViewModel.AnonymousClass3.this.lambda$onSuccess$2$PartyPrivacyViewModel$3(this.f$1);
                        }
                    });
                    callback.execute();
                } catch (JSONException e) {
                    str = PartyPrivacyViewModel.TAG;
                    str2 = AnonymousClass006.A07("Error parsing response: ", e.toString());
                    Log.e(str, str2);
                    callback2.execute();
                } catch (Exception e2) {
                    str = PartyPrivacyViewModel.TAG;
                    str2 = e2.toString();
                    Log.e(str, str2);
                    callback2.execute();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasLinkInvite(boolean z) {
        this.mHasLinkInvite = z;
        notifyPropertyChanged(196);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPartyType(PartyPrivacyType partyPrivacyType) {
        this.mPartyType = partyPrivacyType;
        notifyPropertyChanged(215);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPartyUrl(String str) {
        this.mPartyUrl = str;
        notifyPropertyChanged(214);
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    public void fetch(GraphQLService graphQLService, Callback callback, Callback callback2) {
        if (!TextUtils.isEmpty(this.mPartyId)) {
            PartyPrivacyQueryHelper.fetchPartyPrivacyInfo(graphQLService, partyPrivacyQueryCallback(callback, callback2), this.mPartyId);
            return;
        }
        Log.e(TAG, "attempted to fetch data without setting partyId");
        callback2.execute();
    }

    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    @Bindable
    public boolean getHasLinkInvite() {
        return this.mHasLinkInvite;
    }

    @Bindable({"partyType"})
    public boolean getIsInviteOnly() {
        PartyPrivacyType partyPrivacyType = this.mPartyType;
        if (partyPrivacyType != null) {
            switch (partyPrivacyType.ordinal()) {
                case 1:
                case 2:
                    return false;
            }
        }
        return true;
    }

    @Bindable
    public String getPartyId() {
        return this.mPartyId;
    }

    @Bindable
    public PartyPrivacyType getPartyType() {
        return this.mPartyType;
    }

    @Bindable
    public String getPartyUrl() {
        return this.mPartyUrl;
    }

    public String getSource() {
        return this.mSource;
    }

    private void fetchPartyPrivacyInfo(GraphQLService graphQLService, String str, Callback callback, Callback callback2) {
        PartyPrivacyQueryHelper.fetchPartyPrivacyInfo(graphQLService, partyPrivacyQueryCallback(callback, callback2), str);
    }

    private void setHasLinkInviteMutation(GraphQLService graphQLService, String str, boolean z, Callback callback, Callback callback2) {
        PartyPrivacyQueryHelper.setPartyHasActiveLinkSharing(graphQLService, partyPrivacyLinkInviteMutationCallback(callback, callback2), str, z);
    }

    private void setPartyPrivacyTypeMutation(GraphQLService graphQLService, String str, PartyPrivacyType partyPrivacyType, Callback callback, Callback callback2) {
        PartyPrivacyQueryHelper.setPartyPrivacyType(graphQLService, partyPrivacyMutationCallback(callback, callback2), str, partyPrivacyType);
    }

    public void setCorrelationId(String str) {
        this.mCorrelationId = str;
    }

    public void setPartyId(String str) {
        this.mPartyId = str;
    }

    public void setSource(String str) {
        this.mSource = str;
    }
}
