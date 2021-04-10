package com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.horizoncontent.social.SocialGroupLaunchApp;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.error.DialogErrorCallback;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialogAction;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteListAdapter;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel;
import com.oculus.panelapp.androiddialog.logging.social.SocialLogger;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateVrInviteDialog extends ConstraintLayout implements Dialog, SocialUserViewModel.SocialViewModelDataObserver {
    private static final int PAGE_SIZE = 10;
    private static final String TAG = LoggingUtil.tag(CreateVrInviteDialog.class);
    private CreateVrInviteListAdapter mAdapter;
    private SocialCreateVrInviteDialogBinding mBinding;
    private CreateVrInviteExperiment mCreateVrInviteExperiment;
    private DialogErrorCallback mDialogErrorCallback;
    private boolean mDidInitialRequestLayout = false;
    private RequestBuilder<Bitmap> mGlideFetcher = null;
    private RequestManager mGlideManager = null;
    private CreateVrInviteViewModel.DataObserver mObserver;
    private AndroidDialogPanelApp mPanelApp;
    private SocialLogger mSocialLogger;
    private SocialUserViewModel mSocialViewerModel;
    private CreateVrInviteViewModel mViewModel;

    public CreateVrInviteDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "CreateVrInviteDialog created");
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, SocialCreateVrInviteDialogBinding socialCreateVrInviteDialogBinding, CreateVrInviteRequestFactory createVrInviteRequestFactory, SocialUserRequestFactory socialUserRequestFactory, SocialLogger socialLogger, CreateVrInviteExperiment createVrInviteExperiment, DialogErrorCallback dialogErrorCallback, JSONObject jSONObject) {
        Log.i(TAG, "initializing CreateVrInviteDialog");
        this.mPanelApp = androidDialogPanelApp;
        this.mBinding = socialCreateVrInviteDialogBinding;
        this.mSocialLogger = socialLogger;
        this.mCreateVrInviteExperiment = createVrInviteExperiment;
        this.mDialogErrorCallback = dialogErrorCallback;
        this.mViewModel = new CreateVrInviteViewModel(getContext(), this.mPanelApp, socialLogger, createVrInviteRequestFactory);
        initializeParameters(jSONObject, this.mViewModel);
        this.mViewModel.initializeInviteFlow(this.mCreateVrInviteExperiment);
        this.mBinding.setViewModel(this.mViewModel);
        this.mSocialViewerModel = new SocialUserViewModel(socialUserRequestFactory);
        this.mBinding.setSocialUserViewModel(this.mSocialViewerModel);
        this.mCreateVrInviteExperiment.initialize(this, this.mViewModel, this.mSocialViewerModel);
        this.mGlideManager = Glide.with(getContext());
        this.mGlideFetcher = this.mGlideManager.asBitmap();
        final Resources resources = getContext().getResources();
        this.mBinding.socialCreatePartyReminder.toggle.setEventHandler(this.mPanelApp);
        this.mBinding.socialCreatePartyReminder.toggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$jG8YEpaKbcCz3B7UefIgyAufjPA */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$92$CreateVrInviteDialog(view);
            }
        });
        configurePrimaryButton();
        OCButton oCButton = this.mBinding.createVrInviteSecondaryButton;
        oCButton.setText(resources.getString(R.string.social_create_vr_invite_button_cancel));
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$dg9TbyYGWS5UIhW1nZEBzQPA8A */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$93$CreateVrInviteDialog(view);
            }
        });
        this.mBinding.chooseLaterListItem.setViewModel(new CreateVrInviteListAdapter.ListItem("", resources.getString(R.string.social_create_vr_invite_choose_later), "", ""));
        this.mBinding.chooseLaterListItem.getViewModel().setRightGlyph(getContext().getDrawable(R.drawable.oc_icon_check_circle_filled_24_d2d2d2), false);
        this.mBinding.chooseLaterListItem.leftIcon.setBackground(getContext().getDrawable(R.drawable.social_create_vr_invite_choose_later_button_background));
        OCButton oCButton2 = this.mBinding.chooseLaterListItem.createVrInviteListItemButton;
        oCButton2.setEventHandler(this.mPanelApp);
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$UBiu3h5ssqOvH7XG92BXBI4Ff4 */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$94$CreateVrInviteDialog(view);
            }
        });
        OCButton oCButton3 = this.mBinding.createVrInviteBackButton;
        oCButton3.setEventHandler(this.mPanelApp);
        oCButton3.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$QnCir16OrXsufRJiv_TJi9c0oFg */

            public final void onClick(View view) {
                CreateVrInviteDialog.this.lambda$initialize$95$CreateVrInviteDialog(view);
            }
        });
        this.mAdapter = new CreateVrInviteListAdapter(getContext(), this.mPanelApp, new CreateVrInviteListAdapter.ListCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass1 */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteListAdapter.ListCallback
            public void onItemHovered(MotionEvent motionEvent) {
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteListAdapter.ListCallback
            public void onItemSelected(CreateVrInviteListAdapter.ListItem listItem, CreateVrInviteListAdapter.ListItemViewHolder listItemViewHolder) {
                Log.d(CreateVrInviteDialog.TAG, "List item selected: ");
                int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[CreateVrInviteDialog.this.mViewModel.getInviteStep().getStepName().ordinal()];
                if (i == 1 || i == 2) {
                    Log.d(CreateVrInviteDialog.TAG, "Step found for app selection");
                    CreateVrInviteDialog.this.mAdapter.deselectListItemsFromViewHolder(listItemViewHolder);
                    CreateVrInviteDialog.this.mBinding.chooseLaterListItem.createVrInviteListItemButton.setSelected(false);
                    CreateVrInviteDialog.this.mBinding.chooseLaterListItem.getViewModel().setShowRightGlyph(false);
                    CreateVrInviteDialog.this.mViewModel.setSelectedAppID(listItem.getId());
                    CreateVrInviteDialog.this.mViewModel.clearGroupLaunchDestinations();
                    CreateVrInviteDialog.this.logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_APP_SELECT);
                    CreateVrInviteDialog.this.goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.DESTINATION_SELECTION);
                } else if (i != 3) {
                    Log.e(CreateVrInviteDialog.TAG, "item selected: unknown step");
                } else {
                    Log.d(CreateVrInviteDialog.TAG, "Step found for destination selection");
                    CreateVrInviteDialog.this.mViewModel.setSelectedDestinationID(listItem.getId());
                    CreateVrInviteDialog.this.logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_DESTINATION_SELECT);
                }
            }
        });
        this.mBinding.destinationsView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.destinationsView.setAdapter(this.mAdapter);
        this.mBinding.destinationsView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass2 */

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int i3 = AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[CreateVrInviteDialog.this.mViewModel.getInviteStep().getStepName().ordinal()];
                if (i3 != 1 && i3 != 2) {
                    if (i3 == 3) {
                        CreateVrInviteDialog.this.maybeFetchMoreDestinations();
                    } else if (i3 != 4 && i3 != 5) {
                        Log.e(CreateVrInviteDialog.TAG, "OnScrollListener: unknown step");
                    }
                }
            }
        });
        this.mObserver = new CreateVrInviteViewModel.DataObserver() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass3 */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.DataObserver
            public void updateApplicationList(@Nullable List<SocialGroupLaunchApp> list) {
                Log.d(CreateVrInviteDialog.TAG, "updating application list");
                if (list == null) {
                    Log.d(CreateVrInviteDialog.TAG, "applications list is null");
                    CreateVrInviteDialog.this.mAdapter.setListItems(new ArrayList(0));
                }
                ArrayList arrayList = new ArrayList(list.size());
                for (SocialGroupLaunchApp socialGroupLaunchApp : list) {
                    CreateVrInviteListAdapter.ListItem listItem = new CreateVrInviteListAdapter.ListItem(socialGroupLaunchApp.getID(), socialGroupLaunchApp.getDisplayName(), ApplicationStringsUtil.getApplicationSubtitle(resources, socialGroupLaunchApp.getMaxGroupLaunchCapacity(), socialGroupLaunchApp.getCurrentPartyEntitlementsCount(), socialGroupLaunchApp.getCurrentPartyUsersCount()), socialGroupLaunchApp.getImageSquareUrl());
                    listItem.setRightGlyph(resources.getDrawable(R.drawable.oc_icon_chevron_right_filled_24_d2d2d2), true);
                    arrayList.add(listItem);
                }
                CreateVrInviteDialog.this.mAdapter.setListItems(arrayList);
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.DataObserver
            public void updateDestinationList(@Nullable List<SocialGroupLaunchAppDestination> list) {
                Log.d(CreateVrInviteDialog.TAG, "updating destination list");
                if (list == null) {
                    Log.d(CreateVrInviteDialog.TAG, "destinations list is null");
                    CreateVrInviteDialog.this.mAdapter.setListItems(new ArrayList(0));
                    return;
                }
                CreateVrInviteDialog.this.mAdapter.setListItems(CreateVrInviteDialog.this.createListItemsFromDestinations(list));
                if (CreateVrInviteDialog.this.mViewModel.getSelectedDestinationID() == null) {
                    CreateVrInviteDialog.this.selectDestinationAtPosition(0);
                }
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.DataObserver
            public void updateParty(String str) {
                if (CreateVrInviteDialog.this.mPanelApp.getDeviceConfig(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
                    CreateVrInviteDialog.this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, new Uri.Builder().appendQueryParameter(LoggingConstants.CORRELATION_ID, CreateVrInviteDialog.this.mViewModel.getCorrelationId()).appendQueryParameter("source", SourceConstants.CREATE_PARTY_DIALOG).build().toString());
                    return;
                }
                CreateVrInviteDialog.this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, "");
            }
        };
    }

    public /* synthetic */ void lambda$initialize$92$CreateVrInviteDialog(View view) {
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_PROFILE_REMINDER_TOGGLE);
        this.mBinding.socialCreatePartyReminder.toggle.setSelected(!this.mBinding.socialCreatePartyReminder.toggle.isSelected());
    }

    public /* synthetic */ void lambda$initialize$93$CreateVrInviteDialog(View view) {
        onCancelClicked();
    }

    public /* synthetic */ void lambda$initialize$94$CreateVrInviteDialog(View view) {
        Log.d(TAG, "Choose later clicked");
        this.mViewModel.setSelectedDestinationID(null);
        if (this.mBinding.chooseLaterListItem.createVrInviteListItemButton.isSelected()) {
            logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CHOOSE_LATER_UNSELECT);
            goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTION);
            return;
        }
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CHOOSE_LATER_SELECT);
        goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
    }

    public /* synthetic */ void lambda$initialize$95$CreateVrInviteDialog(View view) {
        Log.d(TAG, "Back clicked");
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_BACK);
        this.mViewModel.clearSelections();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames = new int[CreateVrInviteViewModel.InviteFlow.StepNames.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.DESTINATION_SELECTION     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_ONLY     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel$InviteFlow$StepNames r1 = com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog.AnonymousClass4.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initializeParameters(JSONObject jSONObject, CreateVrInviteViewModel createVrInviteViewModel) {
        String str = TAG;
        Log.d(str, "Initializing parameters: " + jSONObject.toString());
        try {
            if (jSONObject.has("thread_key")) {
                createVrInviteViewModel.setThreadKey(jSONObject.getString("thread_key"));
            }
            if (jSONObject.has("party_id")) {
                createVrInviteViewModel.setCurrentPartyID(jSONObject.getString("party_id"));
            }
            if (jSONObject.has(LoggingConstants.CORRELATION_ID)) {
                createVrInviteViewModel.setCorrelationId(jSONObject.getString(LoggingConstants.CORRELATION_ID));
            }
            if (jSONObject.has("source")) {
                createVrInviteViewModel.setSource(jSONObject.getString("source"));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error initializing parameters", e);
        }
    }

    public Pair<Observable, Integer>[] getObservablesForLoading() {
        int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[this.mViewModel.getInviteStep().getStepName().ordinal()];
        if (i == 4) {
            return new Pair[]{Pair.create(this.mSocialViewerModel, null), Pair.create(this.mCreateVrInviteExperiment, Integer.valueOf(BR.isReady))};
        } else if (i != 5) {
            return new Pair[0];
        } else {
            return new Pair[]{Pair.create(this.mSocialViewerModel, null)};
        }
    }

    private void configurePrimaryButton() {
        OCButton oCButton = this.mBinding.createVrInvitePrimaryButton;
        oCButton.setEventHandler(this.mPanelApp);
        int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[this.mViewModel.getInviteStep().getStepName().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$T6TaeZdf4py_ofRCAUiNGWjhk */

                public final void onClick(View view) {
                    CreateVrInviteDialog.this.lambda$configurePrimaryButton$98$CreateVrInviteDialog(view);
                }
            });
        } else if (i == 4 || i == 5) {
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$y6RMrFFHNB7RNtcJdPquqJ4DgYo */

                public final void onClick(View view) {
                    CreateVrInviteDialog.this.lambda$configurePrimaryButton$96$CreateVrInviteDialog(view);
                }
            });
        } else {
            Log.e(TAG, "configure primary button: unknown step");
        }
    }

    public /* synthetic */ void lambda$configurePrimaryButton$96$CreateVrInviteDialog(View view) {
        logPrimaryButtonClick();
        onConfirmProfileClicked();
    }

    public /* synthetic */ void lambda$configurePrimaryButton$98$CreateVrInviteDialog(View view) {
        logPrimaryButtonClick();
        this.mViewModel.createOrUpdateParty(new CreateVrInviteViewModel.Callback.Error() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$g0SsqRqzpMJb3tt_A3K460nEcD0 */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
            public final void onError(ErrorType errorType) {
                CreateVrInviteDialog.this.lambda$null$97$CreateVrInviteDialog(errorType);
            }
        });
    }

    private void configureChooseLaterButton() {
        boolean z = this.mViewModel.getInviteStep().getStepName() == CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER;
        this.mBinding.chooseLaterListItem.createVrInviteListItemButton.setSelected(z);
        this.mBinding.chooseLaterListItem.getViewModel().setShowRightGlyph(z);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public List<CreateVrInviteListAdapter.ListItem> createListItemsFromDestinations(List<SocialGroupLaunchAppDestination> list) {
        Resources resources = getResources();
        ArrayList arrayList = new ArrayList(list.size());
        for (SocialGroupLaunchAppDestination socialGroupLaunchAppDestination : list) {
            CreateVrInviteListAdapter.ListItem listItem = new CreateVrInviteListAdapter.ListItem(socialGroupLaunchAppDestination.getID(), socialGroupLaunchAppDestination.getDestinationDisplayName(), resources.getString(R.string.social_create_vr_invite_destination_subtitle, String.valueOf(socialGroupLaunchAppDestination.getMaxCapacity())), socialGroupLaunchAppDestination.getImageUrl());
            listItem.setRightGlyph(resources.getDrawable(R.drawable.oc_icon_check_circle_filled_24_d2d2d2), false);
            arrayList.add(listItem);
        }
        return arrayList;
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        Log.d(TAG, "Controller back button pressed");
        if (AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[this.mViewModel.getInviteStep().getStepName().ordinal()] != 3) {
            Log.d(TAG, "close dialog");
            this.mPanelApp.closeDialog();
            return true;
        }
        Log.d(TAG, "return to app selection");
        this.mViewModel.clearSelections();
        return true;
    }

    public void onCancelClicked() {
        Log.d(TAG, "Cancel clicked");
        logButtonClick(ClickEventButtonId.CREATE_VR_INVITE_CANCEL);
        this.mPanelApp.closeDialog();
    }

    private void onConfirmProfileClicked() {
        Log.d(TAG, "Confirm profile clicked");
        setFutureProfileReminderStatus();
        if (this.mCreateVrInviteExperiment.getSkipDestinationStep()) {
            this.mViewModel.createOrUpdateParty(new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$yJ3yXE4z6TrIqzy3ocn0m_0d12Q */

                @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteDialog.this.lambda$onConfirmProfileClicked$99$CreateVrInviteDialog(errorType);
                }
            });
        } else {
            goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void goToStep(CreateVrInviteViewModel.InviteFlow.StepNames stepNames) {
        this.mViewModel.setInviteStep(stepNames);
        configureCurrentStep();
    }

    private void setFutureProfileReminderStatus() {
        Log.d(TAG, "Setting future reminder status");
        if (this.mBinding.socialCreatePartyReminder.toggle.isSelected()) {
            Log.d(TAG, "Don't show reminder again selected");
            this.mViewModel.setProfileNux(new CreateVrInviteViewModel.Callback.Error() {
                /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$BCK_sP306epXHXIOnincDTjtC7A */

                @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                public final void onError(ErrorType errorType) {
                    CreateVrInviteDialog.this.lambda$setFutureProfileReminderStatus$100$CreateVrInviteDialog(errorType);
                }
            });
        }
    }

    private void updateSelectedAppDetails(SocialGroupLaunchApp socialGroupLaunchApp) {
        if (socialGroupLaunchApp.getImageSquareUrl() == null) {
            this.mBinding.selectedAppImage.setImageResource(R.drawable.social_create_vr_invite_image_placeholder);
            return;
        }
        float dimension = (float) ((int) getContext().getResources().getDimension(R.dimen.social_create_vr_invite_border_radius));
        ((RequestBuilder) ((RequestBuilder) this.mGlideFetcher.load(socialGroupLaunchApp.getImageSquareUrl()).transform(new GranularRoundedCorners(dimension, 0.0f, 0.0f, dimension))).placeholder(R.drawable.social_create_vr_invite_image_placeholder)).into(this.mBinding.selectedAppImage);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeFetchMoreDestinations() {
        int size;
        if (((LinearLayoutManager) this.mBinding.destinationsView.getLayoutManager()).findLastCompletelyVisibleItemPosition() >= this.mAdapter.getListItems().size() - 1) {
            Log.d(TAG, "Scrolled to the end of destinations list");
            if (!this.mViewModel.isLoadingGroupLaunchDestinations() && this.mViewModel.getGroupLaunchDestinations().size() - 1 >= 0) {
                SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mViewModel.getGroupLaunchDestinations().get(size);
                if (socialGroupLaunchAppDestination.hasNext()) {
                    this.mViewModel.fetchGroupLaunchDestinationForSelectedApp(socialGroupLaunchAppDestination.getCursor(), 10, new CreateVrInviteViewModel.Callback.Error() {
                        /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$JiO7VwFgZrv5pSJH0f6LsmUfV0c */

                        @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                        public final void onError(ErrorType errorType) {
                            CreateVrInviteDialog.this.lambda$maybeFetchMoreDestinations$101$CreateVrInviteDialog(errorType);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void selectDestinationAtPosition(int i) {
        if (i >= 0 && this.mAdapter.getListItems().size() > i) {
            this.mViewModel.setSelectedDestinationID(this.mAdapter.getListItems().get(i).getId());
            CreateVrInviteListAdapter createVrInviteListAdapter = this.mAdapter;
            createVrInviteListAdapter.setSelectedItemId(Long.valueOf(createVrInviteListAdapter.getItemId(i)));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logButtonClick(ClickEventButtonId clickEventButtonId) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        if (this.mViewModel.getCurrentPartyID() != null) {
            builder.put("party_id", this.mViewModel.getCurrentPartyID());
        }
        if (this.mViewModel.getThreadKey() != null) {
            builder.put("thread_id", this.mViewModel.getThreadKey());
        }
        if (this.mViewModel.getCorrelationId() != null) {
            builder.put(LoggingConstants.CORRELATION_ID, this.mViewModel.getCorrelationId());
        }
        if (this.mViewModel.getSource() != null) {
            builder.put("source", this.mViewModel.getSource());
        }
        this.mSocialLogger.logButtonClick(clickEventButtonId, this.mViewModel.getInviteStep().getLoggingSurface(), builder.build());
    }

    private void logPrimaryButtonClick() {
        ClickEventButtonId primaryActionClickEventButtonId = this.mViewModel.getInviteStep().getPrimaryActionClickEventButtonId();
        if (primaryActionClickEventButtonId == null) {
            String str = TAG;
            Log.e(str, "ClickEventButtonId not set for step: " + this.mViewModel.getInviteStep().getStepName());
            return;
        }
        logButtonClick(primaryActionClickEventButtonId);
    }

    private void configureCurrentStep() {
        Log.d(TAG, "configuring current step");
        if (this.mViewModel == null) {
            Log.d(TAG, "view model is null. Need to initialize first");
        } else if (this.mSocialViewerModel == null) {
            Log.d(TAG, "social user view model is null. Need to initialize first");
        } else {
            configurePrimaryButton();
            int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$androiddialog$dialogs$social$create_vr_invite$CreateVrInviteViewModel$InviteFlow$StepNames[this.mViewModel.getInviteStep().getStepName().ordinal()];
            if (i == 1 || i == 2) {
                if (this.mAdapter.getListItems().size() == 0 && !this.mViewModel.isLoadingGroupLaunchApplications()) {
                    Log.d(TAG, "configure current step: app selection step: no list items yet");
                    this.mViewModel.fetchGroupLaunchApps(new CreateVrInviteViewModel.Callback.Error() {
                        /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$Y6aJnk6ewffJ_Yw9BBLgt1YwpE */

                        @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                        public final void onError(ErrorType errorType) {
                            CreateVrInviteDialog.this.lambda$configureCurrentStep$103$CreateVrInviteDialog(errorType);
                        }
                    });
                }
                configureChooseLaterButton();
            } else if (i == 3) {
                Log.d(TAG, "configure current step: destination selection step");
                SocialGroupLaunchApp selectedApp = this.mViewModel.getSelectedApp();
                if (selectedApp != null) {
                    updateSelectedAppDetails(selectedApp);
                    if (this.mAdapter.getListItems().size() == 0 && !this.mViewModel.isLoadingGroupLaunchDestinations()) {
                        this.mViewModel.fetchGroupLaunchDestinationForSelectedApp(null, 10, new CreateVrInviteViewModel.Callback.Error() {
                            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$cUeUwSa8WJohlFCNT1tgK1rx6g */

                            @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                            public final void onError(ErrorType errorType) {
                                CreateVrInviteDialog.this.lambda$configureCurrentStep$104$CreateVrInviteDialog(errorType);
                            }
                        });
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Need to select an app first");
            } else if (i == 4) {
                Log.d(TAG, "configure current step: profile confirm only");
                this.mSocialViewerModel.fetch();
            } else if (i != 5) {
                Log.e(TAG, "configure current step: unknown step");
            } else {
                Log.d(TAG, "configure current step: profile confirm step");
                this.mSocialViewerModel.fetch();
                if (!this.mViewModel.isLoadingGroupLaunchApplications()) {
                    this.mViewModel.fetchGroupLaunchApps(new CreateVrInviteViewModel.Callback.Error() {
                        /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$EdLm292kCOokxsufd1hbJ3IvjGM */

                        @Override // com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel.Callback.Error
                        public final void onError(ErrorType errorType) {
                            CreateVrInviteDialog.this.lambda$configureCurrentStep$102$CreateVrInviteDialog(errorType);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        Log.d(TAG, "attached to window");
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

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        Log.d(TAG, "detached from window");
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

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        SocialCreateVrInviteDialogBinding socialCreateVrInviteDialogBinding;
        Log.d(TAG, "Destroying dialog");
        CreateVrInviteExperiment createVrInviteExperiment = this.mCreateVrInviteExperiment;
        if (createVrInviteExperiment != null) {
            createVrInviteExperiment.destroy();
        }
        CreateVrInviteViewModel createVrInviteViewModel = this.mViewModel;
        if (createVrInviteViewModel != null) {
            createVrInviteViewModel.destroy();
        }
        RequestManager requestManager = this.mGlideManager;
        if (!(requestManager == null || (socialCreateVrInviteDialogBinding = this.mBinding) == null)) {
            requestManager.clear(socialCreateVrInviteDialogBinding.selectedAppImage);
        }
        SocialUserViewModel socialUserViewModel = this.mSocialViewerModel;
        if (socialUserViewModel != null) {
            socialUserViewModel.destroy();
        }
        SocialLogger socialLogger = this.mSocialLogger;
        if (socialLogger != null) {
            socialLogger.destroy();
            this.mSocialLogger = null;
        }
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel.SocialViewModelDataObserver
    public void onSocialUserViewModelDataUpdated(SocialUserViewModel socialUserViewModel) {
        String str = TAG;
        Log.d(str, "onSocialUserViewModelDataUpdated: " + this.mSocialViewerModel.getHasSeenVRInviteProfileNux());
        if (this.mViewModel.getInviteStep().getStepName() == CreateVrInviteViewModel.InviteFlow.StepNames.PROFILE_CONFIRMATION_STEP && this.mSocialViewerModel.getHasSeenVRInviteProfileNux()) {
            Log.d(TAG, "User has confirmed profile: proceed to app selection step");
            goToStep(CreateVrInviteViewModel.InviteFlow.StepNames.APP_SELECTED_IS_CHOOSE_LATER);
        }
    }

    /* renamed from: showError */
    public void lambda$setFutureProfileReminderStatus$100$CreateVrInviteDialog(ErrorType errorType) {
        this.mDialogErrorCallback.onError(errorType, ErrorDialogAction.onDismiss(new ErrorDialog.ErrorDialogCallback.Dismiss() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.$$Lambda$CreateVrInviteDialog$a3JFY4D2bBsA8YAmAjuvOVRBvaQ */

            @Override // com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog.ErrorDialogCallback.Dismiss
            public final void onDismiss() {
                CreateVrInviteDialog.this.lambda$showError$105$CreateVrInviteDialog();
            }
        }).withDismissText(R.string.party_error_dialog_dismiss_text).build());
    }

    public /* synthetic */ void lambda$showError$105$CreateVrInviteDialog() {
        this.mPanelApp.closeDialog();
    }
}
