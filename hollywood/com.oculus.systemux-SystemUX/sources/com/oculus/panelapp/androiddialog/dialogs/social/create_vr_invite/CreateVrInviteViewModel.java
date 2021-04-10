package com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialGroupLaunchApp;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel;
import com.oculus.panelapp.androiddialog.logging.social.SocialLogger;
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
    private static final String CREATE_PARTY_KEY = "createParty";
    private static final String FETCH_APPS_KEY = "fetchApps";
    private static final String FETCH_DESTINATIONS_KEY = "fetchDestinations";
    private static final String SET_NUX_KEY = "setNux";
    private static final String TAG = LoggingUtil.tag(CreateVrInviteViewModel.class);
    private static final String UPDATE_PARTY_KEY = "updateParty";
    private static List<WeakReference<DataObserver>> mObservers;
    private List<SocialGroupLaunchApp> mApps;
    private Context mContext;
    @Nullable
    private String mCorrelationId = null;
    private final CreateVrInviteRequestFactory mCreateVrInviteRequestFactory;
    @Nullable
    private String mCurrentPartyID = null;
    private List<SocialGroupLaunchAppDestination> mDestinations;
    private InviteFlow mInviteFlow;
    private final AndroidDialogPanelApp mPanelApp;
    @Nullable
    private SocialGroupLaunchApp mSelectedApp = null;
    @Nullable
    private String mSelectedDestinationID = null;
    private SocialLogger mSocialLogger;
    @Nullable
    private String mSource = null;
    @Nullable
    private String mThreadKey = null;

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

    public enum NuxFlags {
        HAS_SEEN_VR_INVITE_PROFILE_NUX
    }

    public static class InviteFlow {
        private InviteStep mStep;
        @Nullable
        Map<StepNames, InviteStep> mStepMapping;

        public enum StepNames {
            PROFILE_CONFIRMATION_ONLY,
            PROFILE_CONFIRMATION_STEP,
            APP_SELECTION,
            APP_SELECTED_IS_CHOOSE_LATER,
            DESTINATION_SELECTION
        }

        public InviteFlow(Context context, boolean z) {
            this.mStepMapping = createStepMapping(z);
        }

        public static class InviteStep {
            private SurfaceType mLoggingSurface;
            private Boolean mPrimaryActionButtonEnabled;
            private int mPrimaryActionButtonText;
            @Nullable
            private ClickEventButtonId mPrimaryActionClickEventButtonId;
            private StepNames mStepName;
            private int mTitleText;

            public InviteStep(StepNames stepNames, int i, int i2, @Nullable ClickEventButtonId clickEventButtonId, Boolean bool, SurfaceType surfaceType) {
                this.mStepName = stepNames;
                this.mTitleText = i;
                this.mPrimaryActionButtonText = i2;
                this.mPrimaryActionClickEventButtonId = clickEventButtonId;
                this.mPrimaryActionButtonEnabled = bool;
                this.mLoggingSurface = surfaceType;
            }

            public StepNames getStepName() {
                return this.mStepName;
            }

            public int getTitleText() {
                return this.mTitleText;
            }

            public ClickEventButtonId getPrimaryActionClickEventButtonId() {
                return this.mPrimaryActionClickEventButtonId;
            }

            public int getPrimaryActionButtonText() {
                return this.mPrimaryActionButtonText;
            }

            public Boolean getPrimaryActionButtonEnabled() {
                return this.mPrimaryActionButtonEnabled;
            }

            public SurfaceType getLoggingSurface() {
                return this.mLoggingSurface;
            }
        }

        public Map<StepNames, InviteStep> createStepMapping(boolean z) {
            this.mStepMapping = new HashMap();
            this.mStepMapping.put(StepNames.PROFILE_CONFIRMATION_ONLY, new InviteStep(StepNames.PROFILE_CONFIRMATION_ONLY, R.string.social_create_vr_invite_header_confirm_profile, R.string.social_create_vr_invite_button_confirm_profile, ClickEventButtonId.CREATE_VR_INVITE_PROFILE_CONFIRM, true, SurfaceType.PROFILE_CONFIRM));
            this.mStepMapping.put(StepNames.PROFILE_CONFIRMATION_STEP, new InviteStep(StepNames.PROFILE_CONFIRMATION_STEP, R.string.social_create_vr_invite_header_confirm_profile, R.string.social_create_vr_invite_button_confirm_profile, ClickEventButtonId.CREATE_VR_INVITE_PROFILE_CONFIRM, true, SurfaceType.PROFILE_CONFIRM));
            this.mStepMapping.put(StepNames.APP_SELECTION, new InviteStep(StepNames.APP_SELECTION, R.string.social_create_vr_invite_header_app_selection, z ? R.string.social_create_vr_invite_select : R.string.social_create_vr_invite_send_invites, z ? ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE : ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES, false, SurfaceType.APP_SELECT));
            this.mStepMapping.put(StepNames.APP_SELECTED_IS_CHOOSE_LATER, new InviteStep(StepNames.APP_SELECTED_IS_CHOOSE_LATER, R.string.social_create_vr_invite_header_app_selection, z ? R.string.social_create_vr_invite_select : R.string.social_create_vr_invite_send_invites, z ? ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE : ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES, true, SurfaceType.APP_SELECT));
            this.mStepMapping.put(StepNames.DESTINATION_SELECTION, new InviteStep(StepNames.DESTINATION_SELECTION, R.string.social_create_vr_invite_header_destination_selection, z ? R.string.social_create_vr_invite_select : R.string.social_create_vr_invite_send_invites, z ? ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_UPDATE : ClickEventButtonId.CREATE_VR_INVITE_SEND_INVITES, true, SurfaceType.DESTINATION_SELECT));
            return this.mStepMapping;
        }

        public InviteStep getCurrentStep() {
            return this.mStep;
        }

        public void setCurrentStep(StepNames stepNames) {
            this.mStep = this.mStepMapping.get(stepNames);
        }
    }

    public CreateVrInviteViewModel(Context context, AndroidDialogPanelApp androidDialogPanelApp, SocialLogger socialLogger, CreateVrInviteRequestFactory createVrInviteRequestFactory) {
        this.mPanelApp = androidDialogPanelApp;
        this.mCreateVrInviteRequestFactory = createVrInviteRequestFactory;
        this.mApps = new ArrayList();
        this.mDestinations = new ArrayList();
        mObservers = new ArrayList();
        this.mContext = context;
        this.mSocialLogger = socialLogger;
    }

    @Bindable
    public InviteFlow.InviteStep getInviteStep() {
        return this.mInviteFlow.getCurrentStep();
    }

    @Bindable
    public List<SocialGroupLaunchApp> getGroupLaunchApps() {
        return this.mApps;
    }

    public void setGroupLaunchApps(List<SocialGroupLaunchApp> list) {
        this.mApps = list;
        updateApplicationsForObservers();
        notifyPropertyChanged(BR.groupLaunchApps);
    }

    public void initializeInviteFlow(CreateVrInviteExperiment createVrInviteExperiment) {
        if (this.mCurrentPartyID == null) {
            this.mInviteFlow = new InviteFlow(this.mContext, false);
            setInviteStep(createVrInviteExperiment.getSkipDestinationStep() ? InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY : InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP);
            return;
        }
        this.mInviteFlow = new InviteFlow(this.mContext, true);
        setInviteStep(InviteFlow.StepNames.APP_SELECTION);
    }

    public void setInviteStep(InviteFlow.StepNames stepNames) {
        String str = TAG;
        Log.d(str, "setting step: " + stepNames);
        this.mInviteFlow.setCurrentStep(stepNames);
        notifyPropertyChanged(BR.inviteStep);
    }

    @Bindable({"inviteStep"})
    public String getTitleText() {
        return this.mContext.getResources().getString(getInviteStep().getTitleText());
    }

    public void clearSelections() {
        Log.d(TAG, "clearing destination selections");
        this.mSelectedApp = null;
        this.mSelectedDestinationID = null;
        updateApplicationsForObservers();
        notifyPropertyChanged(BR.selectedApp);
        setInviteStep(InviteFlow.StepNames.APP_SELECTION);
    }

    public void setCurrentPartyID(@Nullable String str) {
        this.mCurrentPartyID = str;
    }

    @Nullable
    public String getCurrentPartyID() {
        return this.mCurrentPartyID;
    }

    public void setSelectedAppID(@Nullable String str) {
        this.mSelectedApp = null;
        Iterator<SocialGroupLaunchApp> it = this.mApps.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SocialGroupLaunchApp next = it.next();
            if (next.getID().equals(str)) {
                this.mSelectedApp = next;
                break;
            }
        }
        notifyPropertyChanged(BR.selectedApp);
    }

    @Nullable
    @Bindable
    public SocialGroupLaunchApp getSelectedApp() {
        return this.mSelectedApp;
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppDisplayName() {
        SocialGroupLaunchApp socialGroupLaunchApp = this.mSelectedApp;
        return socialGroupLaunchApp == null ? "" : socialGroupLaunchApp.getDisplayName();
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppGroupJoinDetails() {
        if (this.mSelectedApp == null) {
            return "";
        }
        return ApplicationStringsUtil.getApplicationSubtitle(this.mContext.getResources(), this.mSelectedApp.getMaxGroupLaunchCapacity(), this.mSelectedApp.getCurrentPartyEntitlementsCount(), this.mSelectedApp.getCurrentPartyUsersCount());
    }

    @Bindable({"selectedApp"})
    public String getSelectedAppDisplayShortDescription() {
        SocialGroupLaunchApp socialGroupLaunchApp = this.mSelectedApp;
        return socialGroupLaunchApp == null ? "" : socialGroupLaunchApp.getDisplayShortDescription();
    }

    @Bindable({"inviteStep"})
    public boolean getChooseLaterVisible() {
        return this.mCurrentPartyID == null && (getInviteStep().getStepName() == InviteFlow.StepNames.APP_SELECTION || getInviteStep().getStepName() == InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$6  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames = new int[InviteFlow.StepNames.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass6.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass6.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass6.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass6.<clinit>():void");
        }
    }

    @Bindable({"inviteStep"})
    public boolean getProfileVisible() {
        int i = AnonymousClass6.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[getInviteStep().getStepName().ordinal()];
        return i == 1 || i == 2;
    }

    @Nullable
    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    public void setCorrelationId(@Nullable String str) {
        this.mCorrelationId = str;
    }

    @Nullable
    public String getSource() {
        return this.mSource;
    }

    public void setSource(@Nullable String str) {
        this.mSource = str;
    }

    public void setSelectedDestinationID(@Nullable String str) {
        this.mSelectedDestinationID = str;
    }

    public String getSelectedDestinationID() {
        return this.mSelectedDestinationID;
    }

    public void setThreadKey(@Nullable String str) {
        this.mThreadKey = str;
    }

    @Nullable
    public String getThreadKey() {
        return this.mThreadKey;
    }

    public void registerObserver(DataObserver dataObserver) {
        String str = TAG;
        Log.d(str, "registerObserver: " + dataObserver.getClass().getSimpleName());
        if (!mObservers.contains(dataObserver)) {
            mObservers.add(new WeakReference<>(dataObserver));
        }
    }

    public void unregisterObserver(DataObserver dataObserver) {
        String str = TAG;
        Log.d(str, "unregisterObserver: " + dataObserver.getClass().getSimpleName());
        int i = 0;
        for (WeakReference<DataObserver> weakReference : mObservers) {
            if (weakReference.get() == dataObserver) {
                mObservers.remove(i);
                return;
            }
            i++;
        }
    }

    public void fetchGroupLaunchApps(Callback.Error error) {
        Log.d(TAG, "Fetching group launch apps: ");
        this.mApps.clear();
        updateApplicationsForObservers();
        registerQueryHandle(FETCH_APPS_KEY, new Function(Stopwatch.createStarted(), getInviteStep(), error) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteViewModel$7WGur7omxfzjrkCa3iJN0dMrmOg */
            private final /* synthetic */ Stopwatch f$1;
            private final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
            private final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$fetchGroupLaunchApps$87$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        });
        notifyPropertyChanged(BR.isDestinationsSpinnerVisible);
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchGroupLaunchApps$87$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.fetchGroupLaunchSupportedApps(new HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback
            public void onSuccess(List<SocialGroupLaunchApp> list) {
                String str = CreateVrInviteViewModel.TAG;
                Log.d(str, "List of apps: " + list);
                if (stopwatch.isRunning()) {
                    stopwatch.stop();
                }
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str);
                CreateVrInviteViewModel.this.setGroupLaunchApps(list);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_FETCH_GROUP_LAUNCH_APPS;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.NO_BUTTON;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionSuccess(actionId, clickEventButtonId, loggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "Error fetching apps");
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_FETCH_GROUP_LAUNCH_APPS;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.NO_BUTTON;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionFailure(actionId, clickEventButtonId, loggingSurface, "Error fetching group launch apps", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<String, String> buildExtraLogParams(String str, Long l) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (str != null) {
            builder.put("party_id", str);
        }
        String str2 = this.mThreadKey;
        if (str2 != null) {
            builder.put("thread_id", str2);
        }
        String str3 = this.mCorrelationId;
        if (str3 != null) {
            builder.put(LoggingConstants.CORRELATION_ID, str3);
        }
        String str4 = this.mSource;
        if (str4 != null) {
            builder.put("source", str4);
        }
        if (l != null) {
            builder.put(LoggingConstants.TIME_TO_COMPLETE_MS, l.toString());
        }
        return builder.build();
    }

    public void setProfileNux(Callback.Error error) {
        registerQueryHandle(SET_NUX_KEY, new Function(Stopwatch.createStarted(), getInviteStep(), error) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteViewModel$2Sz_R085FjcpdiIozKpRPORGEag */
            private final /* synthetic */ Stopwatch f$1;
            private final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
            private final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$setProfileNux$88$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
            }
        });
    }

    public /* synthetic */ AsyncQueryHandle lambda$setProfileNux$88$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.setNuxFlagForUser(NuxFlags.HAS_SEEN_VR_INVITE_PROFILE_NUX.toString(), true, new HorizonContentProviderHelper.SetNuxFlagForUserCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SetNuxFlagForUserCallback
            public void onSuccess() {
                Log.d(CreateVrInviteViewModel.TAG, "Success setting profile nux");
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearHandle(str);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_CREATE_TOGGLE_DISMISS_REMINDER;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionSuccess(actionId, clickEventButtonId, loggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "Error setting profile nux");
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearHandle(str);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_CREATE_TOGGLE_DISMISS_REMINDER;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionFailure(actionId, clickEventButtonId, loggingSurface, "Error setting profile nux for user", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }
        });
    }

    public boolean isLoadingGroupLaunchApplications() {
        return doesHandleExist(FETCH_APPS_KEY);
    }

    public void clearGroupLaunchDestinations() {
        this.mDestinations.clear();
        updateDestinationsForObservers();
    }

    public void fetchGroupLaunchDestinationForSelectedApp(@Nullable String str, @Nullable Integer num, Callback.Error error) {
        if (this.mSelectedApp != null) {
            String str2 = TAG;
            Log.d(str2, "Fetching destinations for app ID: " + this.mSelectedApp.getID());
            registerQueryHandle(FETCH_DESTINATIONS_KEY, new Function(str, num, Stopwatch.createStarted(), getInviteStep(), error) {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteViewModel$UXo6WWjFpIbThxK4_bmddsKJOwU */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Integer f$2;
                private final /* synthetic */ Stopwatch f$3;
                private final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$4;
                private final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$5;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CreateVrInviteViewModel.this.lambda$fetchGroupLaunchDestinationForSelectedApp$89$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (String) obj);
                }
            });
            notifyPropertyChanged(BR.isDestinationsSpinnerVisible);
            return;
        }
        throw new IllegalArgumentException("App has not been selected yet");
    }

    public /* synthetic */ AsyncQueryHandle lambda$fetchGroupLaunchDestinationForSelectedApp$89$CreateVrInviteViewModel(@Nullable String str, @Nullable Integer num, final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str2) {
        return this.mCreateVrInviteRequestFactory.fetchGroupLaunchAppDestinations(this.mSelectedApp.getID(), str, num, new HorizonContentProviderHelper.FetchGroupLaunchAppDestinationsCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchAppDestinationsCallback
            public void onSuccess(List<SocialGroupLaunchAppDestination> list) {
                String str = CreateVrInviteViewModel.TAG;
                Log.d(str, "List of destinations: " + list.toString());
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str2);
                CreateVrInviteViewModel.this.mDestinations.addAll(list);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionSuccess(actionId, clickEventButtonId, loggingSurface, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.updateDestinationsForObservers();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                String str = CreateVrInviteViewModel.TAG;
                Log.e(str, "Error fetching destinations for ID: " + CreateVrInviteViewModel.this.mSelectedApp.getID());
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearFetchAppOrDestinationsHandle(str2);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS;
                ClickEventButtonId clickEventButtonId = ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT;
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionFailure(actionId, clickEventButtonId, loggingSurface, "Error fetching group launch apps", createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }
        });
    }

    public boolean isLoadingGroupLaunchDestinations() {
        return doesHandleExist(FETCH_DESTINATIONS_KEY);
    }

    public boolean hasNextPageGroupLaunchDestinations() {
        if (this.mDestinations.size() == 0) {
            return false;
        }
        List<SocialGroupLaunchAppDestination> list = this.mDestinations;
        return list.get(list.size() - 1).hasNext();
    }

    public List<SocialGroupLaunchAppDestination> getGroupLaunchDestinations() {
        return this.mDestinations;
    }

    @Bindable({"inviteStep", "isCreatingOrUpdating"})
    public String getPrimaryActionButtonText() {
        if (getIsCreatingOrUpdating()) {
            return null;
        }
        return this.mContext.getResources().getString(getInviteStep().getPrimaryActionButtonText());
    }

    @Bindable({"inviteStep", "isCreatingOrUpdating"})
    public boolean getPrimaryActionButtonEnabled() {
        return getInviteStep().getPrimaryActionButtonEnabled().booleanValue() && !getIsCreatingOrUpdating();
    }

    public void createOrUpdateParty(Callback.Error error) {
        if (this.mCurrentPartyID == null) {
            createPartyAndSendInvites(false, error);
        } else {
            updatePartyGroupLaunchDestination(error);
        }
    }

    public void createPartyNoButtonClickLogging(Callback.Error error) {
        createPartyAndSendInvites(true, error);
    }

    @Bindable
    public boolean getIsCreatingOrUpdating() {
        return doesHandleExist("createParty") || doesHandleExist(UPDATE_PARTY_KEY);
    }

    @Bindable({"inviteStep"})
    public boolean getIsDestinationsSpinnerVisible() {
        if (getProfileVisible()) {
            return false;
        }
        if (isLoadingGroupLaunchApplications() || isLoadingGroupLaunchDestinations()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void createPartyAndSendInvites(boolean z, Callback.Error error) {
        Log.d(TAG, "Creating party and sending invites");
        InviteFlow.InviteStep inviteStep = getInviteStep();
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
        String str3 = TAG;
        Log.d(str3, "Party params: " + hashMap.toString());
        registerQueryHandle("createParty", new Function(hashMap, Stopwatch.createStarted(), z, inviteStep, error) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteViewModel$cr5tflY0vlT862Li_VbNQ4nPXk */
            private final /* synthetic */ Map f$1;
            private final /* synthetic */ Stopwatch f$2;
            private final /* synthetic */ boolean f$3;
            private final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$4;
            private final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CreateVrInviteViewModel.this.lambda$createPartyAndSendInvites$90$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (String) obj);
            }
        });
        notifyPropertyChanged(BR.isCreatingOrUpdating);
    }

    public /* synthetic */ AsyncQueryHandle lambda$createPartyAndSendInvites$90$CreateVrInviteViewModel(@Nullable final Map map, final Stopwatch stopwatch, final boolean z, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.createOrUpdateCustomParty(map, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                ClickEventButtonId clickEventButtonId;
                String str2 = CreateVrInviteViewModel.TAG;
                Log.i(str2, "Created a party with partyID: " + str + " redirecting to AUI");
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel.this.updatePartyObservers(str);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_CREATE;
                if (z) {
                    clickEventButtonId = ClickEventButtonId.NO_BUTTON;
                } else {
                    clickEventButtonId = inviteStep.getPrimaryActionClickEventButtonId();
                }
                socialLogger.logActionSuccess(actionId, clickEventButtonId, inviteStep.getLoggingSurface(), CreateVrInviteViewModel.this.buildExtraLogParams(str, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.mContext.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_CREATED));
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(CreateVrInviteViewModel.TAG, "An error occured when attempting to create a party.");
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel.this.mSocialLogger.logActionFailure(ActionId.PARTY_CREATE, inviteStep.getPrimaryActionClickEventButtonId(), inviteStep.getLoggingSurface(), String.format("Error creating party with params: %s", map.toString()), CreateVrInviteViewModel.this.buildExtraLogParams(null, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void updatePartyGroupLaunchDestination(Callback.Error error) {
        Log.d(TAG, "Update party group launch destination");
        String str = this.mCurrentPartyID;
        if (str != null) {
            String str2 = this.mSelectedDestinationID;
            if (str2 != null) {
                Log.d(TAG, String.format("Update party id: %s destination id: %s ", str, str2));
                registerQueryHandle(UPDATE_PARTY_KEY, new Function(Stopwatch.createStarted(), getInviteStep(), error) {
                    /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteViewModel$BHTsgsZSxxxWcgAiRFHlyRj66ao */
                    private final /* synthetic */ Stopwatch f$1;
                    private final /* synthetic */ CreateVrInviteViewModel.InviteFlow.InviteStep f$2;
                    private final /* synthetic */ CreateVrInviteViewModel.Callback.Error f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return CreateVrInviteViewModel.this.lambda$updatePartyGroupLaunchDestination$91$CreateVrInviteViewModel(this.f$1, this.f$2, this.f$3, (String) obj);
                    }
                });
                notifyPropertyChanged(BR.isCreatingOrUpdating);
                return;
            }
            throw new IllegalStateException("Destination ID has not been set yet");
        }
        throw new IllegalStateException("Party ID has not been set yet");
    }

    public /* synthetic */ AsyncQueryHandle lambda$updatePartyGroupLaunchDestination$91$CreateVrInviteViewModel(final Stopwatch stopwatch, final InviteFlow.InviteStep inviteStep, final Callback.Error error, final String str) {
        return this.mCreateVrInviteRequestFactory.setPartyGroupLaunchDestination(this.mCurrentPartyID, this.mSelectedDestinationID, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                String str2 = CreateVrInviteViewModel.TAG;
                Log.i(str2, "Successfully updated party: " + CreateVrInviteViewModel.this.mCurrentPartyID);
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                CreateVrInviteViewModel.this.mSocialLogger.logActionSuccess(ActionId.PARTY_UPDATE_DESTINATION, inviteStep.getPrimaryActionClickEventButtonId(), inviteStep.getLoggingSurface(), CreateVrInviteViewModel.this.buildExtraLogParams(str, Long.valueOf(elapsed)));
                CreateVrInviteViewModel.this.updatePartyObservers(str);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                String format = String.format("Error setting destination to %s for party: %s", CreateVrInviteViewModel.this.mSelectedDestinationID, CreateVrInviteViewModel.this.mCurrentPartyID);
                Log.e(CreateVrInviteViewModel.TAG, format);
                CreateVrInviteViewModel.this.clearPartyMutationHandle(str);
                SocialLogger socialLogger = CreateVrInviteViewModel.this.mSocialLogger;
                ActionId actionId = ActionId.PARTY_UPDATE_DESTINATION;
                ClickEventButtonId primaryActionClickEventButtonId = inviteStep.getPrimaryActionClickEventButtonId();
                SurfaceType loggingSurface = inviteStep.getLoggingSurface();
                CreateVrInviteViewModel createVrInviteViewModel = CreateVrInviteViewModel.this;
                socialLogger.logActionFailure(actionId, primaryActionClickEventButtonId, loggingSurface, format, createVrInviteViewModel.buildExtraLogParams(createVrInviteViewModel.mCurrentPartyID, Long.valueOf(elapsed)));
                error.onError(ErrorType.GENERAL);
            }
        });
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

    private void updateSocialViewerForObservers() {
        for (WeakReference<DataObserver> weakReference : mObservers) {
            DataObserver dataObserver = weakReference.get();
            if (dataObserver != null) {
                dataObserver.updateSocialViewer();
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

    @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialViewModel, com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        mObservers.clear();
        super.destroy();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPartyMutationHandle(String str) {
        clearHandle(str);
        notifyPropertyChanged(BR.isCreatingOrUpdating);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchAppOrDestinationsHandle(String str) {
        clearHandle(str);
        notifyPropertyChanged(BR.isDestinationsSpinnerVisible);
    }
}
