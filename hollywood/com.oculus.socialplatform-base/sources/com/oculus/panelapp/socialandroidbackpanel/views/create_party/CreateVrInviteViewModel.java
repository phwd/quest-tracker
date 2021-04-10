package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import X.AnonymousClass006;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialGroupLaunchApp;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.social.SocialViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class CreateVrInviteViewModel extends SocialViewModel implements ViewModelLifecycle {
    public static final String CREATE_PARTY_KEY = "createParty";
    public static final String FETCH_APPS_KEY = "fetchApps";
    public static final String FETCH_DESTINATIONS_KEY = "fetchDestinations";
    public static final String SET_NUX_KEY = "setNux";
    public static final String TAG = LoggingUtil.tag(CreateVrInviteViewModel.class);
    public static final String UPDATE_PARTY_KEY = "updateParty";
    public static List<WeakReference<DataObserver>> mObservers;
    public List<SocialGroupLaunchApp> mApps;
    public Context mContext;
    @Nullable
    public String mCorrelationId = null;
    public final CreateVrInviteRequestFactory mCreateVrInviteRequestFactory;
    @Nullable
    public String mCurrentPartyID = null;
    public List<SocialGroupLaunchAppDestination> mDestinations;
    public InviteFlow mInviteFlow;
    public final SocialAndroidBackPanelApp mPanelApp;
    @Nullable
    public SocialGroupLaunchApp mSelectedApp = null;
    @Nullable
    public String mSelectedDestinationID = null;
    @Nullable
    public String mSource = null;
    @Nullable
    public String mThreadKey = null;

    public static class Callback {

        @FunctionalInterface
        public interface Error {
            void onError(ErrorType errorType);
        }
    }

    public static abstract class DataObserver {
        public void updateApplicationList(@Nullable List<SocialGroupLaunchApp> list) {
        }

        public void updateDestinationList(@Nullable List<SocialGroupLaunchAppDestination> list) {
        }

        public void updateParty(String str) {
        }

        public void updateSocialViewer() {
        }
    }

    public static class InviteFlow {
        public InviteStep mStep;
        @Nullable
        public Map<StepNames, InviteStep> mStepMapping;

        public static class InviteStep {
            public SurfaceType mLoggingSurface;
            public Boolean mPrimaryActionButtonEnabled;
            public int mPrimaryActionButtonText;
            @Nullable
            public ClickEventButtonId mPrimaryActionClickEventButtonId;
            public StepNames mStepName;
            public int mTitleText;

            public SurfaceType getLoggingSurface() {
                return this.mLoggingSurface;
            }

            public Boolean getPrimaryActionButtonEnabled() {
                return this.mPrimaryActionButtonEnabled;
            }

            public int getPrimaryActionButtonText() {
                return this.mPrimaryActionButtonText;
            }

            public ClickEventButtonId getPrimaryActionClickEventButtonId() {
                return this.mPrimaryActionClickEventButtonId;
            }

            public StepNames getStepName() {
                return this.mStepName;
            }

            public int getTitleText() {
                return this.mTitleText;
            }

            public InviteStep(StepNames stepNames, int i, int i2, @Nullable ClickEventButtonId clickEventButtonId, Boolean bool, SurfaceType surfaceType) {
                this.mStepName = stepNames;
                this.mTitleText = i;
                this.mPrimaryActionButtonText = i2;
                this.mPrimaryActionClickEventButtonId = clickEventButtonId;
                this.mPrimaryActionButtonEnabled = bool;
                this.mLoggingSurface = surfaceType;
            }
        }

        public enum StepNames {
            PROFILE_CONFIRMATION_ONLY,
            PROFILE_CONFIRMATION_STEP,
            APP_SELECTION,
            APP_SELECTED_IS_CHOOSE_LATER,
            DESTINATION_SELECTION
        }

        public Map<StepNames, InviteStep> createStepMapping(boolean z) {
            ClickEventButtonId clickEventButtonId;
            ClickEventButtonId clickEventButtonId2;
            ClickEventButtonId clickEventButtonId3;
            HashMap hashMap = new HashMap();
            this.mStepMapping = hashMap;
            StepNames stepNames = StepNames.PROFILE_CONFIRMATION_ONLY;
            ClickEventButtonId clickEventButtonId4 = ClickEventButtonId.CREATE_VR_INVITE_PROFILE_CONFIRM;
            SurfaceType surfaceType = SurfaceType.PROFILE_CONFIRM;
            hashMap.put(stepNames, new InviteStep(stepNames, R.string.social_create_vr_invite_header_confirm_profile, R.string.social_create_vr_invite_button_confirm_profile, clickEventButtonId4, true, surfaceType));
            Map<StepNames, InviteStep> map = this.mStepMapping;
            StepNames stepNames2 = StepNames.PROFILE_CONFIRMATION_STEP;
            map.put(stepNames2, new InviteStep(stepNames2, R.string.social_create_vr_invite_header_confirm_profile, R.string.social_create_vr_invite_button_confirm_profile, clickEventButtonId4, true, surfaceType));
            Map<StepNames, InviteStep> map2 = this.mStepMapping;
            StepNames stepNames3 = StepNames.APP_SELECTION;
            int i = R.string.social_create_vr_invite_send_invites;
            if (z) {
                i = R.string.social_create_vr_invite_select;
            }
            if (z) {
                clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE;
            } else {
                clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES;
            }
            SurfaceType surfaceType2 = SurfaceType.APP_SELECT;
            map2.put(stepNames3, new InviteStep(stepNames3, R.string.social_create_vr_invite_header_app_selection, i, clickEventButtonId, false, surfaceType2));
            Map<StepNames, InviteStep> map3 = this.mStepMapping;
            StepNames stepNames4 = StepNames.APP_SELECTED_IS_CHOOSE_LATER;
            int i2 = R.string.social_create_vr_invite_send_invites;
            if (z) {
                i2 = R.string.social_create_vr_invite_select;
            }
            if (z) {
                clickEventButtonId2 = ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE;
            } else {
                clickEventButtonId2 = ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES;
            }
            map3.put(stepNames4, new InviteStep(stepNames4, R.string.social_create_vr_invite_header_app_selection, i2, clickEventButtonId2, true, surfaceType2));
            Map<StepNames, InviteStep> map4 = this.mStepMapping;
            StepNames stepNames5 = StepNames.DESTINATION_SELECTION;
            int i3 = R.string.social_create_vr_invite_send_invites;
            if (z) {
                i3 = R.string.social_create_vr_invite_select;
            }
            if (z) {
                clickEventButtonId3 = ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE;
            } else {
                clickEventButtonId3 = ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES;
            }
            map4.put(stepNames5, new InviteStep(stepNames5, R.string.social_create_vr_invite_header_destination_selection, i3, clickEventButtonId3, true, SurfaceType.DESTINATION_SELECT));
            return this.mStepMapping;
        }

        public InviteStep getCurrentStep() {
            return this.mStep;
        }

        public void setCurrentStep(StepNames stepNames) {
            this.mStep = this.mStepMapping.get(stepNames);
        }

        public InviteFlow(Context context, boolean z) {
            this.mStepMapping = createStepMapping(z);
        }
    }

    public enum NuxFlags {
        HAS_SEEN_VR_INVITE_PROFILE_NUX
    }

    public void clearSelections() {
        this.mSelectedApp = null;
        this.mSelectedDestinationID = null;
        updateApplicationsForObservers();
        notifyPropertyChanged(194);
        setInviteStep(InviteFlow.StepNames.APP_SELECTION);
    }

    @VisibleForTesting
    public void createPartyAndSendInvites(boolean z, Callback.Error error) {
        InviteFlow.InviteStep inviteStep = this.mInviteFlow.mStep;
        HashMap hashMap = new HashMap();
        SocialPartyType socialPartyType = SocialPartyType.JOINABLE_BY_FRIENDS;
        String str = this.mSelectedDestinationID;
        if (str != null) {
            hashMap.put("destination_id", str);
        }
        hashMap.put("party_type", socialPartyType.toString());
        String str2 = this.mThreadKey;
        if (str2 != null) {
            hashMap.put("message_thread_key", str2);
        }
        hashMap.toString();
        registerQueryHandle(CREATE_PARTY_KEY, new Function(hashMap, Stopwatch.createStarted(), z, inviteStep, error) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteViewModel$AObrUsquOoADuz1ar8jyBjugLYU2 */
            public final /* synthetic */ Map f$1;
            public final /* synthetic */ Stopwatch f$2;
            public final /* synthetic */ boolean f$3;
            public final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$4;
            public final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$createPartyAndSendInvites$3$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (String) obj);
            }
        });
        notifyPropertyChanged(227);
    }

    public void createPartyNoButtonClickLogging(Callback.Error error) {
        createPartyAndSendInvites(true, error);
    }

    public void fetchGroupLaunchDestinationForSelectedApp(@Nullable String str, @Nullable Integer num, Callback.Error error) {
        if (this.mSelectedApp != null) {
            registerQueryHandle(FETCH_DESTINATIONS_KEY, new Function(str, num, Stopwatch.createStarted(), this.mInviteFlow.mStep, error) {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteViewModel$edxExZMiEyqXbRYBTDuVvdnxyxA2 */
                public final /* synthetic */ String f$1;
                public final /* synthetic */ Integer f$2;
                public final /* synthetic */ Stopwatch f$3;
                public final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$4;
                public final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$5;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CreateVrInviteViewModel.this.lambda$fetchGroupLaunchDestinationForSelectedApp$2$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (String) obj);
                }
            });
            notifyPropertyChanged(209);
            return;
        }
        throw new IllegalArgumentException("App has not been selected yet");
    }

    public /* synthetic */ AsyncQueryHandle lambda$createPartyAndSendInvites$3$CreateVrInviteViewModel(final Map map, final Stopwatch stopwatch, final boolean z, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        CreateVrInviteRequestFactory createVrInviteRequestFactory = this.mCreateVrInviteRequestFactory;
        return HorizonContentProviderHelper.createOrUpdateCustomParty(createVrInviteRequestFactory.mContext, map, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "An error occured when attempting to create a party.");
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                SocialLogger logger = CreateVrInviteViewModel.this.mPanelApp.getLogger();
                ActionId actionId = ActionId.PARTY_CREATE;
                InviteFlow.InviteStep inviteStep = inviteStep;
                logger.logActionFailure(actionId, inviteStep.mPrimaryActionClickEventButtonId, inviteStep.mLoggingSurface, String.format("Error creating party with params: %s", map.toString()), CreateVrInviteViewModel.this.buildExtraLogParams(null, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                ClickEventButtonId clickEventButtonId;
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel.this.updatePartyObservers(str);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                SocialLogger logger = createVrInviteViewModel.mPanelApp.getLogger();
                ActionId actionId = ActionId.PARTY_CREATE;
                if (z) {
                    clickEventButtonId = ClickEventButtonId.NO_BUTTON;
                } else {
                    clickEventButtonId = inviteStep.mPrimaryActionClickEventButtonId;
                }
                logger.logActionSuccess(actionId, clickEventButtonId, inviteStep.mLoggingSurface, createVrInviteViewModel.buildExtraLogParams(str, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_CREATED));
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchGroupLaunchApps$0$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        CreateVrInviteRequestFactory createVrInviteRequestFactory = this.mCreateVrInviteRequestFactory;
        return HorizonContentProviderHelper.fetchGroupLaunchSupportedApps(createVrInviteRequestFactory.mContext, new HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "Error fetching apps");
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionFailure(ActionId.PARTY_FETCH_GROUP_LAUNCH_APPS, ClickEventButtonId.NO_BUTTON, inviteStep.mLoggingSurface, "Error fetching group launch apps", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback
            public void onSuccess(List<SocialGroupLaunchApp> list) {
                Stopwatch stopwatch = stopwatch;
                if (stopwatch.isRunning) {
                    stopwatch.stop();
                }
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str);
                CreateVrInviteViewModel.this.setGroupLaunchApps(list);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionSuccess(ActionId.PARTY_FETCH_GROUP_LAUNCH_APPS, ClickEventButtonId.NO_BUTTON, inviteStep.mLoggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchGroupLaunchDestinationForSelectedApp$2$CreateVrInviteViewModel(String str, Integer num, final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str2) {
        CreateVrInviteRequestFactory createVrInviteRequestFactory = this.mCreateVrInviteRequestFactory;
        return HorizonContentProviderHelper.fetchGroupLaunchAppDestinations(createVrInviteRequestFactory.mContext, this.mSelectedApp.mId, str, num, new HorizonContentProviderHelper.FetchGroupLaunchAppDestinationsCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, AnonymousClass006.A07("Error fetching destinations for ID: ", CreateVrInviteViewModel.this.mSelectedApp.mId));
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str2);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionFailure(ActionId.PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS, ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT, inviteStep.mLoggingSurface, "Error fetching group launch apps", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchAppDestinationsCallback
            public void onSuccess(List<SocialGroupLaunchAppDestination> list) {
                list.toString();
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str2);
                CreateVrInviteViewModel.this.mDestinations.addAll(list);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionSuccess(ActionId.PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS, ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT, inviteStep.mLoggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.updateDestinationsForObservers();
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$setProfileNux$1$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.setNuxFlagForUser(NuxFlags.HAS_SEEN_VR_INVITE_PROFILE_NUX.toString(), true, new HorizonContentProviderHelper.SetNuxFlagForUserCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "Error setting profile nux");
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearHandle(str);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionFailure(ActionId.PARTY_CREATE_TOGGLE_DISMISS_REMINDER, ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE, inviteStep.mLoggingSurface, "Error setting profile nux for user", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SetNuxFlagForUserCallback
            public void onSuccess() {
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearHandle(str);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                createVrInviteViewModel.mPanelApp.getLogger().logActionSuccess(ActionId.PARTY_CREATE_TOGGLE_DISMISS_REMINDER, ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE, inviteStep.mLoggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$updatePartyGroupLaunchDestination$4$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.setPartyGroupLaunchDestination(this.mCurrentPartyID, this.mSelectedDestinationID, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                String format = String.format("Error setting destination to %s for party: %s", createVrInviteViewModel.mSelectedDestinationID, createVrInviteViewModel.mCurrentPartyID);
                Log.e(CreateVrInviteViewModel.TAG, format);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel createVrInviteViewModel2 = CreateVrInviteViewModel.this;
                SocialLogger logger = createVrInviteViewModel2.mPanelApp.getLogger();
                ActionId actionId = ActionId.PARTY_UPDATE_DESTINATION;
                InviteFlow.InviteStep inviteStep = inviteStep;
                logger.logActionFailure(actionId, inviteStep.mPrimaryActionClickEventButtonId, inviteStep.mLoggingSurface, format, createVrInviteViewModel2.buildExtraLogParams(createVrInviteViewModel2.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                Stopwatch stopwatch = stopwatch;
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                SocialLogger logger = createVrInviteViewModel.mPanelApp.getLogger();
                ActionId actionId = ActionId.PARTY_UPDATE_DESTINATION;
                InviteFlow.InviteStep inviteStep = inviteStep;
                logger.logActionSuccess(actionId, inviteStep.mPrimaryActionClickEventButtonId, inviteStep.mLoggingSurface, createVrInviteViewModel.buildExtraLogParams(str, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.updatePartyObservers(str);
            }
        });
    }

    public void setSelectedAppID(@Nullable String str) {
        this.mSelectedApp = null;
        Iterator<SocialGroupLaunchApp> it = this.mApps.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SocialGroupLaunchApp next = it.next();
            if (next.mId.equals(str)) {
                this.mSelectedApp = next;
                break;
            }
        }
        notifyPropertyChanged(194);
    }

    /* renamed from: com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$6  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$create_party$CreateVrInviteViewModel$InviteFlow$StepNames;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames[] r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass6.$SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$create_party$CreateVrInviteViewModel$InviteFlow$StepNames = r2
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.AnonymousClass6.<clinit>():void");
        }
    }

    private void updateApplicationsForObservers() {
        for (WeakReference<DataObserver> weakReference : mObservers) {
            DataObserver dataObserver = weakReference.get();
            if (dataObserver != null) {
                dataObserver.updateApplicationList(this.mApps);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateDestinationsForObservers() {
        for (WeakReference<DataObserver> weakReference : mObservers) {
            DataObserver dataObserver = weakReference.get();
            if (dataObserver != null) {
                dataObserver.updateDestinationList(this.mDestinations);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePartyObservers(String str) {
        for (WeakReference<DataObserver> weakReference : mObservers) {
            DataObserver dataObserver = weakReference.get();
            if (dataObserver != null) {
                dataObserver.updateParty(str);
            }
        }
    }

    private void updateSocialViewerForObservers() {
        for (WeakReference<DataObserver> weakReference : mObservers) {
            weakReference.get();
        }
    }

    public void clearGroupLaunchDestinations() {
        this.mDestinations.clear();
        updateDestinationsForObservers();
    }

    public void createOrUpdateParty(Callback.Error error) {
        if (this.mCurrentPartyID == null) {
            createPartyAndSendInvites(false, error);
        } else {
            updatePartyGroupLaunchDestination(error);
        }
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.social.SocialViewModel, com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        mObservers.clear();
        super.destroy();
    }

    public void fetchGroupLaunchApps(Callback.Error error) {
        this.mApps.clear();
        updateApplicationsForObservers();
        registerQueryHandle(FETCH_APPS_KEY, new Function(Stopwatch.createStarted(), this.mInviteFlow.mStep, error) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteViewModel$R9YKwOVKmdX99te3sBOI0HFTFY2 */
            public final /* synthetic */ Stopwatch f$1;
            public final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
            public final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$fetchGroupLaunchApps$0$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        });
        notifyPropertyChanged(209);
    }

    @Bindable({"inviteStep"})
    public boolean getChooseLaterVisible() {
        if (this.mCurrentPartyID != null) {
            return false;
        }
        InviteFlow.StepNames stepNames = this.mInviteFlow.mStep.mStepName;
        if (stepNames == InviteFlow.StepNames.APP_SELECTION || stepNames == InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER) {
            return true;
        }
        return false;
    }

    @Nullable
    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    @Nullable
    public String getCurrentPartyID() {
        return this.mCurrentPartyID;
    }

    @Bindable
    public List<SocialGroupLaunchApp> getGroupLaunchApps() {
        return this.mApps;
    }

    public List<SocialGroupLaunchAppDestination> getGroupLaunchDestinations() {
        return this.mDestinations;
    }

    @Bindable
    public InviteFlow.InviteStep getInviteStep() {
        return this.mInviteFlow.mStep;
    }

    @Bindable
    public boolean getIsCreatingOrUpdating() {
        if (this.mQueryHandleMap.containsKey(CREATE_PARTY_KEY) || this.mQueryHandleMap.containsKey(UPDATE_PARTY_KEY)) {
            return true;
        }
        return false;
    }

    @Bindable({"inviteStep", "isCreatingOrUpdating"})
    public boolean getPrimaryActionButtonEnabled() {
        if (!this.mInviteFlow.mStep.mPrimaryActionButtonEnabled.booleanValue() || getIsCreatingOrUpdating()) {
            return false;
        }
        return true;
    }

    @Bindable({"inviteStep"})
    public boolean getProfileVisible() {
        switch (this.mInviteFlow.mStep.mStepName.ordinal()) {
            case 0:
            case 1:
                return true;
            default:
                return false;
        }
    }

    @Nullable
    @Bindable
    public SocialGroupLaunchApp getSelectedApp() {
        return this.mSelectedApp;
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppDisplayName() {
        SocialGroupLaunchApp socialGroupLaunchApp = this.mSelectedApp;
        if (socialGroupLaunchApp == null) {
            return "";
        }
        return socialGroupLaunchApp.mDisplayName;
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppDisplayShortDescription() {
        SocialGroupLaunchApp socialGroupLaunchApp = this.mSelectedApp;
        if (socialGroupLaunchApp == null) {
            return "";
        }
        return socialGroupLaunchApp.mDisplayShortDescription;
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppGroupJoinDetails() {
        if (this.mSelectedApp == null) {
            return "";
        }
        Resources resources = this.mContext.getResources();
        SocialGroupLaunchApp socialGroupLaunchApp = this.mSelectedApp;
        return ApplicationStringsUtil.getApplicationSubtitle(resources, socialGroupLaunchApp.mMaxGroupLaunchCapacity, socialGroupLaunchApp.mCurrentPartyEntitlementsCount, socialGroupLaunchApp.mCurrentPartyUsersCount);
    }

    public String getSelectedDestinationID() {
        return this.mSelectedDestinationID;
    }

    @Nullable
    public String getSource() {
        return this.mSource;
    }

    @Nullable
    public String getThreadKey() {
        return this.mThreadKey;
    }

    @Bindable({"inviteStep"})
    public String getTitleText() {
        return this.mContext.getResources().getString(this.mInviteFlow.mStep.mTitleText);
    }

    public boolean hasNextPageGroupLaunchDestinations() {
        if (this.mDestinations.size() == 0) {
            return false;
        }
        List<SocialGroupLaunchAppDestination> list = this.mDestinations;
        return list.get(list.size() - 1).mHasNext;
    }

    public void initializeInviteFlow(CreateVrInviteExperiment createVrInviteExperiment) {
        InviteFlow.StepNames stepNames;
        if (this.mCurrentPartyID == null) {
            this.mInviteFlow = new InviteFlow(this.mContext, false);
            if (createVrInviteExperiment.getSkipDestinationStep()) {
                stepNames = InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY;
            } else {
                stepNames = InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP;
            }
        } else {
            this.mInviteFlow = new InviteFlow(this.mContext, true);
            stepNames = InviteFlow.StepNames.APP_SELECTION;
        }
        setInviteStep(stepNames);
    }

    public boolean isLoadingGroupLaunchApplications() {
        return this.mQueryHandleMap.containsKey(FETCH_APPS_KEY);
    }

    public boolean isLoadingGroupLaunchDestinations() {
        return this.mQueryHandleMap.containsKey(FETCH_DESTINATIONS_KEY);
    }

    public void registerObserver(DataObserver dataObserver) {
        if (!mObservers.contains(dataObserver)) {
            mObservers.add(new WeakReference<>(dataObserver));
        }
    }

    public void setGroupLaunchApps(List<SocialGroupLaunchApp> list) {
        this.mApps = list;
        updateApplicationsForObservers();
        notifyPropertyChanged(218);
    }

    public void setInviteStep(InviteFlow.StepNames stepNames) {
        this.mInviteFlow.setCurrentStep(stepNames);
        notifyPropertyChanged(192);
    }

    public void setProfileNux(Callback.Error error) {
        registerQueryHandle(SET_NUX_KEY, new Function(Stopwatch.createStarted(), this.mInviteFlow.mStep, error) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteViewModel$UNiKLZnpnWNQUlzUQgftm25JV1A2 */
            public final /* synthetic */ Stopwatch f$1;
            public final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
            public final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$setProfileNux$1$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        });
    }

    public void unregisterObserver(DataObserver dataObserver) {
        int i = 0;
        for (WeakReference<DataObserver> weakReference : mObservers) {
            if (weakReference.get() == dataObserver) {
                mObservers.remove(i);
                return;
            }
            i++;
        }
    }

    @VisibleForTesting
    public void updatePartyGroupLaunchDestination(Callback.Error error) {
        if (this.mCurrentPartyID == null) {
            throw new IllegalStateException("Party ID has not been set yet");
        } else if (this.mSelectedDestinationID != null) {
            registerQueryHandle(UPDATE_PARTY_KEY, new Function(Stopwatch.createStarted(), this.mInviteFlow.mStep, error) {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteViewModel$Opidp1MDvaWMx0XndIUEAR9SG5o2 */
                public final /* synthetic */ Stopwatch f$1;
                public final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
                public final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CreateVrInviteViewModel.this.lambda$updatePartyGroupLaunchDestination$4$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
                }
            });
            notifyPropertyChanged(227);
        } else {
            throw new IllegalStateException("Destination ID has not been set yet");
        }
    }

    public CreateVrInviteViewModel(Context context, SocialAndroidBackPanelApp socialAndroidBackPanelApp, CreateVrInviteRequestFactory createVrInviteRequestFactory) {
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mCreateVrInviteRequestFactory = createVrInviteRequestFactory;
        this.mApps = new ArrayList();
        this.mDestinations = new ArrayList();
        mObservers = new ArrayList();
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<String, String> buildExtraLogParams(String str, Long l) {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        if (str != null) {
            A04.put("party_id", str);
        }
        String str2 = this.mThreadKey;
        if (str2 != null) {
            A04.put("thread_id", str2);
        }
        String str3 = this.mCorrelationId;
        if (str3 != null) {
            A04.put("correlation_id", str3);
        }
        String str4 = this.mSource;
        if (str4 != null) {
            A04.put("source", str4);
        }
        if (l != null) {
            A04.put(LoggingConstants.TIME_TO_COMPLETE_MS, l.toString());
        }
        return A04.build();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchAppOrDestinationsHandle(String str) {
        clearHandle(str);
        notifyPropertyChanged(209);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPartyMutationHandle(String str) {
        clearHandle(str);
        notifyPropertyChanged(227);
    }

    @Bindable({"inviteStep"})
    public boolean getIsDestinationsSpinnerVisible() {
        if (getProfileVisible()) {
            return false;
        }
        if (this.mQueryHandleMap.containsKey(FETCH_APPS_KEY) || this.mQueryHandleMap.containsKey(FETCH_DESTINATIONS_KEY)) {
            return true;
        }
        return false;
    }

    @Bindable({"inviteStep", "isCreatingOrUpdating"})
    public String getPrimaryActionButtonText() {
        if (getIsCreatingOrUpdating()) {
            return null;
        }
        return this.mContext.getResources().getString(this.mInviteFlow.mStep.mPrimaryActionButtonText);
    }

    public void setCorrelationId(@Nullable String str) {
        this.mCorrelationId = str;
    }

    public void setCurrentPartyID(@Nullable String str) {
        this.mCurrentPartyID = str;
    }

    public void setSelectedDestinationID(@Nullable String str) {
        this.mSelectedDestinationID = str;
    }

    public void setSource(@Nullable String str) {
        this.mSource = str;
    }

    public void setThreadKey(@Nullable String str) {
        this.mThreadKey = str;
    }
}
