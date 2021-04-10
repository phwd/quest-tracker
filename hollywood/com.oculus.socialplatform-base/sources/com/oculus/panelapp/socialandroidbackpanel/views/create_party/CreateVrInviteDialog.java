package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import X.AbstractC12101uv;
import X.AnonymousClass1CG;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.social.SocialGroupLaunchApp;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteDialogBinding;
import com.oculus.panelapp.socialandroidbackpanel.graphql.SocialUserRequestFactory;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewAction;
import com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateVrInviteDialog extends BaseView implements SocialAndroidBackPanelView, SocialUserViewModel.SocialViewModelDataObserver {
    public static final int PAGE_SIZE = 10;
    public static final String TAG = LoggingUtil.tag(CreateVrInviteDialog.class);
    public CreateVrInviteListAdapter mAdapter;
    public SocialCreateVrInviteDialogBinding mBinding;
    public CreateVrInviteExperiment mCreateVrInviteExperiment;
    public ErrorCallback mDialogErrorCallback;
    public boolean mDidInitialRequestLayout = false;
    public CreateVrInviteViewModel.DataObserver mObserver;
    public SocialAndroidBackPanelApp mPanelApp;
    public SocialUserViewModel mSocialViewerModel;
    public CreateVrInviteViewModel mViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$create_party$CreateVrInviteViewModel$InviteFlow$StepNames;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames[] r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$socialandroidbackpanel$views$create_party$CreateVrInviteViewModel$InviteFlow$StepNames = r2
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.DESTINATION_SELECTION     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel$InviteFlow$StepNames r0 = com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog.AnonymousClass4.<clinit>():void");
        }
    }

    private void configureChooseLaterButton() {
        boolean z = false;
        if (this.mViewModel.mInviteFlow.mStep.mStepName == CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER) {
            z = true;
        }
        this.mBinding.chooseLaterListItem.createVrInviteListItemButton.setSelected(z);
        this.mBinding.chooseLaterListItem.mViewModel.setShowRightGlyph(z);
    }

    private void configureCurrentStep() {
        if (this.mViewModel != null && this.mSocialViewerModel != null) {
            configurePrimaryButton();
            CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
            switch (createVrInviteViewModel.mInviteFlow.mStep.mStepName.ordinal()) {
                case 0:
                    this.mSocialViewerModel.fetch();
                    return;
                case 1:
                    this.mSocialViewerModel.fetch();
                    if (!this.mViewModel.isLoadingGroupLaunchApplications()) {
                        this.mViewModel.fetchGroupLaunchApps(new CreateVrInviteViewModel.Callback.Error() {
                            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$Y7n_mAtWbamRSKqCsG41d26H_N02 */

                            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                            public final void onError(ErrorType errorType) {
                                CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                            }
                        });
                        return;
                    }
                    return;
                case 2:
                case 3:
                    if (this.mAdapter.mList.size() == 0 && !this.mViewModel.isLoadingGroupLaunchApplications()) {
                        this.mViewModel.fetchGroupLaunchApps(new CreateVrInviteViewModel.Callback.Error() {
                            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$wQvIFEJYcW1PFFzzEoGccNcqOP02 */

                            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                            public final void onError(ErrorType errorType) {
                                CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                            }
                        });
                    }
                    configureChooseLaterButton();
                    return;
                case 4:
                    SocialGroupLaunchApp socialGroupLaunchApp = createVrInviteViewModel.mSelectedApp;
                    if (socialGroupLaunchApp != null) {
                        updateSelectedAppDetails(socialGroupLaunchApp);
                        if (this.mAdapter.mList.size() == 0 && !this.mViewModel.isLoadingGroupLaunchDestinations()) {
                            this.mViewModel.fetchGroupLaunchDestinationForSelectedApp(null, 10, new CreateVrInviteViewModel.Callback.Error() {
                                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$rV2S5aBD7afbY4Y1VozQf_XOVl02 */

                                @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                                public final void onError(ErrorType errorType) {
                                    CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("Need to select an app first");
                default:
                    Log.e(TAG, "configure current step: unknown step");
                    return;
            }
        }
    }

    private void configurePrimaryButton() {
        OCButton oCButton = this.mBinding.createVrInvitePrimaryButton;
        oCButton.mEventHandler = this.mPanelApp;
        switch (this.mViewModel.mInviteFlow.mStep.mStepName.ordinal()) {
            case 0:
            case 1:
                oCButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$IeQ3JtPWKd24ryjZYRHKtlmNJ4k2 */

                    public final void onClick(View view) {
                        CreateVrInviteDialog.this.lambda$configurePrimaryButton$4$CreateVrInviteDialog(view);
                    }
                });
                return;
            case 2:
            case 3:
            case 4:
                oCButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$vTHLGe0hxm5ystJD5u2dGRyGHkk2 */

                    public final void onClick(View view) {
                        CreateVrInviteDialog.this.lambda$configurePrimaryButton$6$CreateVrInviteDialog(view);
                    }
                });
                return;
            default:
                Log.e(TAG, "configure primary button: unknown step");
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logButtonClick(ClickEventButtonId clickEventButtonId) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        String str = this.mViewModel.mCurrentPartyID;
        if (str != null) {
            builder.put("party_id", str);
        }
        String str2 = this.mViewModel.mThreadKey;
        if (str2 != null) {
            builder.put("thread_id", str2);
        }
        String str3 = this.mViewModel.mCorrelationId;
        if (str3 != null) {
            builder.put("correlation_id", str3);
        }
        String str4 = this.mViewModel.mSource;
        if (str4 != null) {
            builder.put("source", str4);
        }
        this.mPanelApp.getLogger().logButtonClick(clickEventButtonId, this.mViewModel.mInviteFlow.mStep.mLoggingSurface, builder.build());
    }

    private void logPrimaryButtonClick() {
        CreateVrInviteViewModel.InviteFlow.InviteStep inviteStep = this.mViewModel.mInviteFlow.mStep;
        ClickEventButtonId clickEventButtonId = inviteStep.mPrimaryActionClickEventButtonId;
        if (clickEventButtonId == null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder("ClickEventButtonId not set for step: ");
            sb.append(inviteStep.mStepName);
            Log.e(str, sb.toString());
            return;
        }
        logButtonClick(clickEventButtonId);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeFetchMoreDestinations() {
        int size;
        if (((LinearLayoutManager) this.mBinding.destinationsView.mLayout).findLastCompletelyVisibleItemPosition() >= this.mAdapter.mList.size() - 1 && !this.mViewModel.isLoadingGroupLaunchDestinations() && this.mViewModel.mDestinations.size() - 1 >= 0) {
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mViewModel.mDestinations.get(size);
            if (socialGroupLaunchAppDestination.mHasNext) {
                this.mViewModel.fetchGroupLaunchDestinationForSelectedApp(socialGroupLaunchAppDestination.mCursor, 10, new CreateVrInviteViewModel.Callback.Error() {
                    /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$VhaLOMS1h1ZgcEfCHzF9OBaaBc2 */

                    @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                    public final void onError(ErrorType errorType) {
                        CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void selectDestinationAtPosition(int i) {
        if (i >= 0 && this.mAdapter.mList.size() > i) {
            this.mViewModel.mSelectedDestinationID = this.mAdapter.mList.get(i).mId;
            CreateVrInviteListAdapter createVrInviteListAdapter = this.mAdapter;
            createVrInviteListAdapter.setSelectedItemId(Long.valueOf(createVrInviteListAdapter.getItemId(i)));
        }
    }

    private void setFutureProfileReminderStatus() {
        if (this.mBinding.socialCreatePartyReminder.toggle.isSelected()) {
            this.mViewModel.setProfileNux(new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$gKUVunhBRaVVjOEEO448XmHx1H02 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                }
            });
        }
    }

    private void updateSelectedAppDetails(SocialGroupLaunchApp socialGroupLaunchApp) {
        if (socialGroupLaunchApp.mImageSquareUrl == null) {
            this.mBinding.selectedAppImage.setImageResource(R.drawable.social_create_vr_invite_image_placeholder);
        }
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void destroy() {
        CreateVrInviteExperiment createVrInviteExperiment = this.mCreateVrInviteExperiment;
        if (createVrInviteExperiment != null) {
            createVrInviteExperiment.destroy();
        }
        CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
        if (createVrInviteViewModel != null) {
            createVrInviteViewModel.destroy();
        }
        SocialUserViewModel socialUserViewModel = this.mSocialViewerModel;
        if (socialUserViewModel != null) {
            socialUserViewModel.destroy();
        }
    }

    public Pair<AbstractC12101uv, Integer>[] getObservablesForLoading() {
        switch (this.mViewModel.mInviteFlow.mStep.mStepName.ordinal()) {
            case 0:
                return new Pair[]{Pair.create(this.mSocialViewerModel, null), Pair.create(this.mCreateVrInviteExperiment, 33)};
            case 1:
                return new Pair[]{Pair.create(this.mSocialViewerModel, null)};
            default:
                return new Pair[0];
        }
    }

    @VisibleForTesting
    public void goToStep(CreateVrInviteViewModel.InviteFlow.StepNames stepNames) {
        this.mViewModel.setInviteStep(stepNames);
        configureCurrentStep();
    }

    public void initialize(SocialAndroidBackPanelApp socialAndroidBackPanelApp, SocialCreateVrInviteDialogBinding socialCreateVrInviteDialogBinding, CreateVrInviteRequestFactory createVrInviteRequestFactory, CreateVrInviteExperiment createVrInviteExperiment, ErrorCallback errorCallback, JSONObject jSONObject) {
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mBinding = socialCreateVrInviteDialogBinding;
        this.mCreateVrInviteExperiment = createVrInviteExperiment;
        this.mDialogErrorCallback = errorCallback;
        Context context = getContext();
        CreateVrInviteViewModel createVrInviteViewModel = new CreateVrInviteViewModel(context, socialAndroidBackPanelApp, createVrInviteRequestFactory);
        this.mViewModel = createVrInviteViewModel;
        initializeParameters(jSONObject, createVrInviteViewModel);
        this.mViewModel.initializeInviteFlow(this.mCreateVrInviteExperiment);
        this.mBinding.setViewModel(this.mViewModel);
        SocialUserViewModel socialUserViewModel = new SocialUserViewModel(new SocialUserRequestFactory(context));
        this.mSocialViewerModel = socialUserViewModel;
        this.mBinding.setSocialUserViewModel(socialUserViewModel);
        this.mCreateVrInviteExperiment.initialize(this, this.mViewModel, this.mSocialViewerModel);
        final Resources resources = context.getResources();
        OCToggle oCToggle = this.mBinding.socialCreatePartyReminder.toggle;
        oCToggle.mEventHandler = this.mPanelApp;
        oCToggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$blkbFCpwfYUPVdRB5thxlCQ2 */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$0$CreateVrInviteDialog(view);
            }
        });
        configurePrimaryButton();
        OCButton oCButton = this.mBinding.createVrInviteSecondaryButton;
        oCButton.setText(resources.getString(R.string.social_create_vr_invite_button_cancel));
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$OICc5Xd0eT0U0YF4MfToUuxTm5U2 */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.onCancelClicked();
            }
        });
        this.mBinding.chooseLaterListItem.setViewModel(new CreateVrInviteListAdapter.ListItem("", resources.getString(R.string.social_create_vr_invite_choose_later), "", ""));
        this.mBinding.chooseLaterListItem.mViewModel.setRightGlyph(context.getDrawable(R.drawable.oc_icon_check_circle_filled_24_d2d2d2), false);
        this.mBinding.chooseLaterListItem.leftIcon.setBackground(context.getDrawable(R.drawable.social_create_vr_invite_choose_later_button_background));
        SocialCreateVrInviteDialogBinding socialCreateVrInviteDialogBinding2 = this.mBinding;
        OCButton oCButton2 = socialCreateVrInviteDialogBinding2.chooseLaterListItem.createVrInviteListItemButton;
        SocialAndroidBackPanelApp socialAndroidBackPanelApp2 = this.mPanelApp;
        oCButton2.mEventHandler = socialAndroidBackPanelApp2;
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$mSowQlco4NcYidXr4iLS6J4HF2w2 */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$2$CreateVrInviteDialog(view);
            }
        });
        OCButton oCButton3 = socialCreateVrInviteDialogBinding2.createVrInviteBackButton;
        oCButton3.mEventHandler = socialAndroidBackPanelApp2;
        oCButton3.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$r87pFUsLmznBNFzDoSmDY0xU3Ok2 */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$3$CreateVrInviteDialog(view);
            }
        });
        this.mAdapter = new CreateVrInviteListAdapter(context, socialAndroidBackPanelApp2, new CreateVrInviteListAdapter.ListCallback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog.AnonymousClass1 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter.ListCallback
            public void onItemHovered(MotionEvent motionEvent) {
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter.ListCallback
            public void onItemSelected(CreateVrInviteListAdapter.ListItem listItem, CreateVrInviteListAdapter.ListItemViewHolder listItemViewHolder) {
                CreateVrInviteDialog createVrInviteDialog = CreateVrInviteDialog.this;
                CreateVrInviteViewModel createVrInviteViewModel = createVrInviteDialog.mViewModel;
                switch (createVrInviteViewModel.mInviteFlow.mStep.mStepName.ordinal()) {
                    case 2:
                    case 3:
                        createVrInviteDialog.mAdapter.deselectListItemsFromViewHolder(listItemViewHolder);
                        CreateVrInviteDialog.this.mBinding.chooseLaterListItem.createVrInviteListItemButton.setSelected(false);
                        CreateVrInviteDialog.this.mBinding.chooseLaterListItem.mViewModel.setShowRightGlyph(false);
                        CreateVrInviteDialog.this.mViewModel.setSelectedAppID(listItem.mId);
                        CreateVrInviteDialog.this.mViewModel.clearGroupLaunchDestinations();
                        CreateVrInviteDialog.this.logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_APP_SELECT);
                        CreateVrInviteDialog.this.goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.DESTINATION_SELECTION);
                        return;
                    case 4:
                        createVrInviteViewModel.mSelectedDestinationID = listItem.mId;
                        createVrInviteDialog.logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT);
                        return;
                    default:
                        Log.e(CreateVrInviteDialog.TAG, "item selected: unknown step");
                        return;
                }
            }
        });
        this.mBinding.destinationsView.setLayoutManager(new LinearLayoutManager(context));
        this.mBinding.destinationsView.setAdapter(this.mAdapter);
        this.mBinding.destinationsView.addOnScrollListener(new AnonymousClass1CG() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog.AnonymousClass2 */

            @Override // X.AnonymousClass1CG
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                CreateVrInviteDialog createVrInviteDialog = CreateVrInviteDialog.this;
                switch (createVrInviteDialog.mViewModel.mInviteFlow.mStep.mStepName.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return;
                    case 4:
                        createVrInviteDialog.maybeFetchMoreDestinations();
                        return;
                    default:
                        Log.e(CreateVrInviteDialog.TAG, "OnScrollListener: unknown step");
                        return;
                }
            }
        });
        this.mObserver = new CreateVrInviteViewModel.DataObserver() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog.AnonymousClass3 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.DataObserver
            public void updateDestinationList(@Nullable List<SocialGroupLaunchAppDestination> list) {
                if (list == null) {
                    CreateVrInviteDialog.this.mAdapter.setListItems(new ArrayList(0));
                    return;
                }
                CreateVrInviteDialog.this.mAdapter.setListItems(CreateVrInviteDialog.this.createListItemsFromDestinations(list));
                CreateVrInviteDialog createVrInviteDialog = CreateVrInviteDialog.this;
                if (createVrInviteDialog.mViewModel.mSelectedDestinationID == null) {
                    createVrInviteDialog.selectDestinationAtPosition(0);
                }
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.DataObserver
            public void updateApplicationList(@Nullable List<SocialGroupLaunchApp> list) {
                if (list == null) {
                    CreateVrInviteDialog.this.mAdapter.setListItems(new ArrayList(0));
                }
                ArrayList arrayList = new ArrayList(list.size());
                for (SocialGroupLaunchApp socialGroupLaunchApp : list) {
                    CreateVrInviteListAdapter.ListItem listItem = new CreateVrInviteListAdapter.ListItem(socialGroupLaunchApp.mId, socialGroupLaunchApp.mDisplayName, ApplicationStringsUtil.getApplicationSubtitle(resources, socialGroupLaunchApp.mMaxGroupLaunchCapacity, socialGroupLaunchApp.mCurrentPartyEntitlementsCount, socialGroupLaunchApp.mCurrentPartyUsersCount), socialGroupLaunchApp.mImageSquareUrl);
                    listItem.setRightGlyph(resources.getDrawable(R.drawable.oc_icon_chevron_right_filled_24_d2d2d2), true);
                    arrayList.add(listItem);
                }
                CreateVrInviteDialog.this.mAdapter.setListItems(arrayList);
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.DataObserver
            public void updateParty(String str) {
                SocialAndroidBackPanelApp socialAndroidBackPanelApp;
                SystemUXRoute systemUXRoute;
                String str2;
                if (CreateVrInviteDialog.this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
                    Uri build = new Uri.Builder().appendQueryParameter("correlation_id", CreateVrInviteDialog.this.mViewModel.mCorrelationId).appendQueryParameter("source", SourceConstants.CREATE_PARTY_DIALOG).build();
                    socialAndroidBackPanelApp = CreateVrInviteDialog.this.mPanelApp;
                    systemUXRoute = SystemUXRoute.AUI_PARTIES;
                    str2 = build.toString();
                } else {
                    socialAndroidBackPanelApp = CreateVrInviteDialog.this.mPanelApp;
                    systemUXRoute = SystemUXRoute.AUI_SOCIAL;
                    str2 = "";
                }
                socialAndroidBackPanelApp.actionNavigate(systemUXRoute, str2);
            }
        };
    }

    @VisibleForTesting
    public void initializeParameters(JSONObject jSONObject, CreateVrInviteViewModel createVrInviteViewModel) {
        jSONObject.toString();
        try {
            if (jSONObject.has("thread_key")) {
                createVrInviteViewModel.mThreadKey = jSONObject.getString("thread_key");
            }
            if (jSONObject.has("party_id")) {
                createVrInviteViewModel.mCurrentPartyID = jSONObject.getString("party_id");
            }
            if (jSONObject.has("correlation_id")) {
                createVrInviteViewModel.mCorrelationId = jSONObject.getString("correlation_id");
            }
            if (jSONObject.has("source")) {
                createVrInviteViewModel.mSource = jSONObject.getString("source");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error initializing parameters", e);
        }
    }

    public /* synthetic */ void lambda$initialize$0$CreateVrInviteDialog(View view) {
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE);
        OCToggle oCToggle = this.mBinding.socialCreatePartyReminder.toggle;
        oCToggle.setSelected(!oCToggle.isSelected());
    }

    public /* synthetic */ void lambda$initialize$2$CreateVrInviteDialog(View view) {
        CreateVrInviteViewModel.InviteFlow.StepNames stepNames;
        this.mViewModel.mSelectedDestinationID = null;
        if (this.mBinding.chooseLaterListItem.createVrInviteListItemButton.isSelected()) {
            logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CHOOSE_LATER_UNSELECT);
            stepNames = CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTION;
        } else {
            logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CHOOSE_LATER_SELECT);
            stepNames = CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER;
        }
        goToStep(stepNames);
    }

    public /* synthetic */ void lambda$initialize$3$CreateVrInviteDialog(View view) {
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_BACK);
        this.mViewModel.clearSelections();
    }

    public /* synthetic */ void lambda$showError$13$CreateVrInviteDialog() {
        this.mPanelApp.quitApp();
    }

    public void onCancelClicked() {
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CANCEL);
        this.mPanelApp.quitApp();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public boolean onControllerBackButton() {
        CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
        if (createVrInviteViewModel.mInviteFlow.mStep.mStepName.ordinal() != 4) {
            this.mPanelApp.quitApp();
            return true;
        }
        createVrInviteViewModel.clearSelections();
        return true;
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel.SocialViewModelDataObserver
    public void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel) {
        boolean z = this.mSocialViewerModel.mHasSeenVRInviteProfileNux;
        if (this.mViewModel.mInviteFlow.mStep.mStepName == CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP && z) {
            goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
        }
    }

    /* renamed from: showError */
    public void lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(ErrorType errorType) {
        ErrorViewAction.ErrorActionBuilder errorActionBuilder = new ErrorViewAction.ErrorActionBuilder(new ErrorView.ErrorViewCallback.Dismiss() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$vM9WzDgjQlwYiy5zwDjGBhBJbc2 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView.ErrorViewCallback.Dismiss
            public final void onDismiss() {
                CreateVrInviteDialog.this.lambda$showError$13$CreateVrInviteDialog();
            }
        });
        errorActionBuilder.mDismissText = R.string.party_error_dialog_dismiss_text;
        this.mDialogErrorCallback.onError(errorType, errorActionBuilder.build());
    }

    public CreateVrInviteDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void onConfirmProfileClicked() {
        setFutureProfileReminderStatus();
        if (this.mCreateVrInviteExperiment.getSkipDestinationStep()) {
            this.mViewModel.createOrUpdateParty(new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$I7ROa7CErDBBEb6H30uGGx1kqCg2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
                }
            });
        } else {
            goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
        }
    }

    @VisibleForTesting
    public List<CreateVrInviteListAdapter.ListItem> createListItemsFromDestinations(List<SocialGroupLaunchAppDestination> list) {
        Resources resources = getResources();
        ArrayList arrayList = new ArrayList(list.size());
        for (SocialGroupLaunchAppDestination socialGroupLaunchAppDestination : list) {
            CreateVrInviteListAdapter.ListItem listItem = new CreateVrInviteListAdapter.ListItem(socialGroupLaunchAppDestination.mId, socialGroupLaunchAppDestination.mDestinationDisplayName, resources.getString(R.string.social_create_vr_invite_destination_subtitle, String.valueOf(socialGroupLaunchAppDestination.mMaxCapacity)), socialGroupLaunchAppDestination.mImageUrl);
            listItem.setRightGlyph(resources.getDrawable(R.drawable.oc_icon_check_circle_filled_24_d2d2d2), false);
            arrayList.add(listItem);
        }
        return arrayList;
    }

    public /* synthetic */ void lambda$configurePrimaryButton$4$CreateVrInviteDialog(View view) {
        logPrimaryButtonClick();
        onConfirmProfileClicked();
    }

    public /* synthetic */ void lambda$configurePrimaryButton$6$CreateVrInviteDialog(View view) {
        logPrimaryButtonClick();
        this.mViewModel.createOrUpdateParty(new CreateVrInviteViewModel.Callback.Error() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.create_party.$$Lambda$CreateVrInviteDialog$PgZ24AdqT3mpjZR250CmtFP0G602 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel.Callback.Error
            public final void onError(ErrorType errorType) {
                CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$8$CreateVrInviteDialog(errorType);
            }
        });
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
        if (createVrInviteViewModel != null) {
            createVrInviteViewModel.registerObserver(this.mObserver);
        }
        SocialUserViewModel socialUserViewModel = this.mSocialViewerModel;
        if (socialUserViewModel != null) {
            socialUserViewModel.registerObserver(this);
        }
        configureCurrentStep();
        if (!this.mDidInitialRequestLayout) {
            this.mDidInitialRequestLayout = true;
            requestLayout();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
        if (createVrInviteViewModel != null) {
            createVrInviteViewModel.unregisterObserver(this.mObserver);
        }
        SocialUserViewModel socialUserViewModel = this.mSocialViewerModel;
        if (socialUserViewModel != null) {
            socialUserViewModel.unregisterObserver(this);
        }
    }

    public /* synthetic */ void lambda$initialize$1$CreateVrInviteDialog(View view) {
        onCancelClicked();
    }
}
