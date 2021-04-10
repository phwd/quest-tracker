package com.oculus.panelapp.messenger.views;

import X.AnonymousClass006;
import X.AnonymousClass1CG;
import X.AnonymousClass2a4;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCDropdown;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.IMessengerAPI;
import com.oculus.panelapp.messenger.api.MessengerAPIManager;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.api.MessengerActionCallback;
import com.oculus.panelapp.messenger.api.ThreadListener;
import com.oculus.panelapp.messenger.api.models.DraftThread;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import com.oculus.panelapp.messenger.fetchers.PartiesFetcher;
import com.oculus.panelapp.messenger.util.PartyMutator;
import com.oculus.panelapp.messenger.util.ThreadHelper;
import com.oculus.panelapp.messenger.views.GroupThreadBlockedView;
import com.oculus.panelapp.messenger.views.LeaveChatConfirmationView;
import com.oculus.panelapp.messenger.views.OneOnOneThreadBlockedView;
import com.oculus.panelapp.messenger.views.ThreadView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.KeyboardHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreadView {
    public static final String DICTATION_MODE = " EnableDictationModeAutoTrigger ON";
    public static final String KEYBOARD_OPEN = "keyboard open ";
    public static final int MESSAGE_BATCH_SIZE = 10;
    public static final String NO_OCULUS_USER_ID = "0";
    public static final String TAG = LoggingUtil.tag(ThreadView.class);
    public MessageListAdapter mAdapter;
    public AnytimeTabletMessengerViewV2Binding mBinding;
    public Context mContext;
    public OCDropdown<ThreadViewContextMenuItem> mContextMenu;
    public MessengerAPIManager.DraftThreadListener mDraftThreadListener;
    public DraftThreadParticipantListAdapter mDraftThreadParticipantListAdapter;
    public GroupThreadBlockedView mGroupThreadBlockedView;
    public boolean mLastScrollAtEndOfList;
    public LeaveChatConfirmationView mLeaveChatConfirmationView;
    public MessengerViewModel mMessengerViewModel;
    public boolean mMostRecentMessageVisible;
    public AnonymousClass2a4 mOneOnOneBlockedHiddenConstraintSet;
    public AnonymousClass2a4 mOneOnOneBlockedShownConstraintSet;
    public OneOnOneThreadBlockedView mOneOnOneThreadBlockedView;
    public Long mOptedInThreadKey;
    public MessengerPanelApp mPanelApp;
    public PartyButtonView mPartyButtonView;
    public boolean mPassesPartyButtonGK;
    public boolean mPassesQuickRepliesGK;
    public String mPreviousMessageId = "";
    public QuickReplyHelper mQuickReplyHelper;
    public ReactionPillCallback mReactionPillCallback;
    public ThreadListener mThreadListener;

    private boolean isSentByViewer(MessengerMessage messengerMessage) {
        return (messengerMessage == null || this.mPanelApp.getAPIManager().mCurrentAPI.getUserID() == null || Long.parseLong(this.mPanelApp.getAPIManager().mCurrentAPI.getUserID()) != messengerMessage.getSenderId()) ? false : true;
    }

    private void onContextMenuBlockClick() {
        List<MessengerParticipant> threadParticipants = this.mPanelApp.getAPIManager().mCurrentAPI.getThreadParticipants();
        String userID = this.mPanelApp.getAPIManager().mCurrentAPI.getUserID();
        if (ThreadHelper.isOneOnOneThread(threadParticipants)) {
            MessengerParticipant otherThreadParticipant = ThreadHelper.getOtherThreadParticipant(userID, threadParticipants);
            if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.FB_MSYS) {
                lambda$initializeBlockedViews$9$ThreadView(userID, otherThreadParticipant.getParticipantId(), otherThreadParticipant.getName());
            } else {
                launchOcBlockFlow(otherThreadParticipant.getParticipantId());
            }
        } else {
            launchParticipantSelector(userID, this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread().getThreadKey(), IntegrityActionType.Block, false);
        }
    }

    private void onContextMenuReportClick() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_CONTEXT_MENU_REPORT, SurfaceType.THREAD_VIEW);
        List<MessengerParticipant> threadParticipants = this.mPanelApp.getAPIManager().mCurrentAPI.getThreadParticipants();
        String userID = this.mPanelApp.getAPIManager().mCurrentAPI.getUserID();
        MessengerThread currentThread = this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread();
        if (currentThread == null) {
            return;
        }
        if (ThreadHelper.isOneOnOneThread(threadParticipants)) {
            MessengerParticipant otherThreadParticipant = ThreadHelper.getOtherThreadParticipant(userID, threadParticipants);
            long threadKey = currentThread.getThreadKey();
            if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.FB_MSYS) {
                lambda$initializeBlockedViews$8$ThreadView(userID, otherThreadParticipant.getParticipantId(), otherThreadParticipant.getName(), threadKey);
            } else {
                launchOcReportFlow(otherThreadParticipant.getParticipantId(), threadKey);
            }
        } else {
            launchParticipantSelector(userID, currentThread.getThreadKey(), IntegrityActionType.Report, false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean shouldScrollToMostRecent(MessengerMessage messengerMessage) {
        if (messengerMessage == null || this.mPreviousMessageId.equals(messengerMessage.getMessageId())) {
            return false;
        }
        return isSentByViewer(messengerMessage) || this.mMostRecentMessageVisible;
    }

    public /* synthetic */ void lambda$initializeBlockedViews$13$ThreadView(long j) {
        String userID = this.mPanelApp.getAPIManager().mCurrentAPI.getUserID();
        if (userID == null) {
            Log.e(TAG, "viewer user id is null when trying to manage blocked members in group thread");
        } else {
            launchParticipantSelector(userID, j, IntegrityActionType.Block, true);
        }
    }

    /* renamed from: com.oculus.panelapp.messenger.views.ThreadView$10  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass10 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$messenger$views$ThreadViewContextMenuItem;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem[] r0 = com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass10.$SwitchMap$com$oculus$panelapp$messenger$views$ThreadViewContextMenuItem = r2
                com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem r0 = com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem.BLOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem r0 = com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem.UNBLOCK     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem r0 = com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem.REPORT     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem r0 = com.oculus.panelapp.messenger.views.ThreadViewContextMenuItem.LEAVE_CHAT     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass10.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void bindContextMenu() {
        ThreadViewContextMenuItem threadViewContextMenuItem;
        OCDropdown<ThreadViewContextMenuItem> oCDropdown;
        boolean isOneOnOneThread;
        if (this.mContextMenu != null) {
            List<MessengerParticipant> threadParticipants = this.mPanelApp.getAPIManager().mCurrentAPI.getThreadParticipants();
            threadParticipants.toString();
            ArrayList arrayList = new ArrayList();
            if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() != MessengerAPIType.OC_CHATS) {
                if (ThreadHelper.isOneOnOneBlockedThread(threadParticipants, ThreadHelper.getBlockedParticipants(threadParticipants))) {
                    threadViewContextMenuItem = ThreadViewContextMenuItem.UNBLOCK;
                } else {
                    threadViewContextMenuItem = ThreadViewContextMenuItem.BLOCK;
                }
                arrayList.add(threadViewContextMenuItem);
                if (this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_ENABLE_FB_REPORT_FLOW)) {
                    arrayList.add(ThreadViewContextMenuItem.REPORT);
                }
                this.mContextMenu.setItems(arrayList);
                oCDropdown = this.mContextMenu;
                isOneOnOneThread = ThreadHelper.isOneOnOneThread(threadParticipants);
            } else if (ThreadHelper.isOneOnOneThread(threadParticipants)) {
                if (this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_ENABLE_OC_REPORT_FLOW)) {
                    arrayList.add(ThreadViewContextMenuItem.REPORT);
                }
                arrayList.add(ThreadViewContextMenuItem.BLOCK);
                this.mContextMenu.setItems(arrayList);
                oCDropdown = this.mContextMenu;
                isOneOnOneThread = true;
            } else {
                arrayList.add(ThreadViewContextMenuItem.LEAVE_CHAT);
                this.mContextMenu.setItems(arrayList);
                oCDropdown = this.mContextMenu;
                isOneOnOneThread = false;
            }
            oCDropdown.setTitleMap(ThreadViewContextMenuItem.getTitleMap(isOneOnOneThread));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void blockComposerIfViewerCannotMessage(long j, List<MessengerParticipant> list) {
        long participantId;
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        if (iMessengerAPI.getType() != MessengerAPIType.TEST && this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_DISABLE_COMPOSER)) {
            if (list.size() == 0 || ThreadHelper.isOneOnOneThread(list)) {
                String userID = iMessengerAPI.getUserID();
                if (list.size() == 0) {
                    participantId = j;
                } else {
                    participantId = ThreadHelper.getOtherThreadParticipant(userID, list).getParticipantId();
                }
                this.mPanelApp.getCanViewerMessageFetcher().query(String.valueOf(participantId), new ICanViewerMessageFetcher.CanViewerMessageSuccessCallback(iMessengerAPI, j) {
                    /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$RnwPLn_nDNBqdkP2FN2jqhYd0OQ2 */
                    public final /* synthetic */ IMessengerAPI f$1;
                    public final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher.CanViewerMessageSuccessCallback
                    public final void onSuccess(Boolean bool) {
                        ThreadView.this.lambda$blockComposerIfViewerCannotMessage$15$ThreadView(this.f$1, this.f$2, bool);
                    }
                }, $$Lambda$ThreadView$1FmryAvF_UaOLA5jWyown6w1r82.INSTANCE);
                return;
            }
            this.mMessengerViewModel.setIsOneOnOneThreadViewerCannotMessage(false, iMessengerAPI.getType());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: cancelLeaveGroupChat */
    public void lambda$triggerLeaveChatFromGroupWithoutBlocked$7$ThreadView() {
        this.mLeaveChatConfirmationView.hide();
        this.mMessengerViewModel.setIsLeaveThreadConfirmationShowing(false);
    }

    /* access modifiers changed from: private */
    /* renamed from: cancelLeaveGroupChatContainingBlocked */
    public void lambda$triggerLeaveChatFromGroupContainingBlocked$6$ThreadView() {
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        lambda$triggerLeaveChatFromGroupWithoutBlocked$7$ThreadView();
        this.mGroupThreadBlockedView.show(iMessengerAPI.getCurrentThread(), ThreadHelper.getBlockedParticipants(iMessengerAPI.getThreadParticipants()));
        this.mMessengerViewModel.setIsGroupThreadContainingBlockedViewShowing(true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleOcOneOnOneBlockedThread(List<MessengerParticipant> list) {
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.OC_CHATS && ThreadHelper.isOneOnOneBlockedThread(list, ThreadHelper.getBlockedParticipants(list))) {
            selectFirstThread();
        }
    }

    private void initializeAudioInputButton() {
        this.mMessengerViewModel.setIsPassingAudioInputGK(this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_COMPOSER_DICTATION));
        OCButton oCButton = this.mBinding.audioInputButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$H9fWAcbI7vy5PIDcdDCdy9Qug42 */

            public final void onClick(View view) {
                ThreadView.this.lambda$initializeAudioInputButton$2$ThreadView(view);
            }
        });
    }

    private void initializeBlockedViews() {
        AnonymousClass2a4 r1 = new AnonymousClass2a4();
        this.mOneOnOneBlockedShownConstraintSet = r1;
        r1.A0A(this.mBinding.messengerView);
        this.mOneOnOneBlockedShownConstraintSet.A06(R.id.message_list, 4, R.id.one_on_one_blocked_thread_inflated, 3);
        AnonymousClass2a4 r12 = new AnonymousClass2a4();
        this.mOneOnOneBlockedHiddenConstraintSet = r12;
        r12.A0A(this.mBinding.messengerView);
        this.mOneOnOneBlockedHiddenConstraintSet.A06(R.id.message_list, 4, R.id.compose_bar_guideline, 3);
        OneOnOneThreadBlockedView oneOnOneThreadBlockedView = this.mOneOnOneThreadBlockedView;
        oneOnOneThreadBlockedView.mOnReportClickListener = new OneOnOneThreadBlockedView.OnReportClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$VmmvyUEbloKIXAQukWI4XnZKplc2 */

            @Override // com.oculus.panelapp.messenger.views.OneOnOneThreadBlockedView.OnReportClickListener
            public final void onReportClick(String str, long j, String str2, long j2) {
                ThreadView.this.lambda$initializeBlockedViews$8$ThreadView(str, j, str2, j2);
            }
        };
        oneOnOneThreadBlockedView.mOnUnblockClickListener = new OneOnOneThreadBlockedView.OnUnblockClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$HFLSdUK0oWEgLU030m7Lu0tIX842 */

            @Override // com.oculus.panelapp.messenger.views.OneOnOneThreadBlockedView.OnUnblockClickListener
            public final void onUnblockClick(String str, long j, String str2) {
                ThreadView.this.lambda$initializeBlockedViews$9$ThreadView(str, j, str2);
            }
        };
        GroupThreadBlockedView groupThreadBlockedView = this.mGroupThreadBlockedView;
        groupThreadBlockedView.mOnContinueClickListener = new GroupThreadBlockedView.OnContinueClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$ETJNEba4ccaehTdC7Nh6ur_Bro2 */

            @Override // com.oculus.panelapp.messenger.views.GroupThreadBlockedView.OnContinueClickListener
            public final void onContinueClick() {
                ThreadView.this.lambda$initializeBlockedViews$10$ThreadView();
            }
        };
        groupThreadBlockedView.mOnLeaveGroupClickListener = new GroupThreadBlockedView.OnLeaveGroupClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$jGubZRhnHD6QVwlCudu28wTjVrQ2 */

            @Override // com.oculus.panelapp.messenger.views.GroupThreadBlockedView.OnLeaveGroupClickListener
            public final void onLeaveGroupClick() {
                ThreadView.this.lambda$initializeBlockedViews$11$ThreadView();
            }
        };
        groupThreadBlockedView.mOnManageSingleBlockClickListener = new GroupThreadBlockedView.OnManageSingleBlockClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$29kdpPYqjXbmAhwc0uQhBUtFzEU2 */

            @Override // com.oculus.panelapp.messenger.views.GroupThreadBlockedView.OnManageSingleBlockClickListener
            public final void onManageSingleBlockClick(long j, String str) {
                ThreadView.this.lambda$initializeBlockedViews$12$ThreadView(j, str);
            }
        };
        groupThreadBlockedView.mOnManageBlockedMembersClickListener = new GroupThreadBlockedView.OnManageBlockedMembersClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$Pt2Z_0WTZGOk8kiSwj1YVIW5pg2 */

            @Override // com.oculus.panelapp.messenger.views.GroupThreadBlockedView.OnManageBlockedMembersClickListener
            public final void onManageBlockedMembersClick(long j) {
                ThreadView.this.lambda$initializeBlockedViews$13$ThreadView(j);
            }
        };
    }

    private void initializeComposeScroll() {
        try {
            Field declaredField = HorizontalScrollView.class.getDeclaredField("mTouchSlop");
            declaredField.setAccessible(true);
            declaredField.set(this.mBinding.composeBarScrollView, 40);
        } catch (Exception e) {
            Log.e(TAG, "Unable to set HorizontalScrollView touch slop.", e);
        }
    }

    private void initializeComposeText() {
        setComposeBarType(ComposeBarType.COLLAPSED);
        ComposeText composeText = this.mBinding.composeTextInput;
        composeText.mEventHandler = this.mPanelApp;
        composeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass3 */

            public void onFocusChange(View view, boolean z) {
                ComposeBarType composeBarType;
                ThreadView threadView = ThreadView.this;
                if (z) {
                    threadView.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_TEXT_INPUT, SurfaceType.THREAD_VIEW);
                    threadView = ThreadView.this;
                    composeBarType = ComposeBarType.TEXT_INPUT;
                } else {
                    composeBarType = ComposeBarType.COLLAPSED;
                }
                threadView.setComposeBarType(composeBarType);
            }
        });
        this.mBinding.composeTextInput.addTextChangedListener(new TextWatcher() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass4 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                editable.toString();
            }
        });
    }

    private void initializeContextMenuButton() {
        OCButton oCButton = this.mBinding.threadContextMenuButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$bceJb3PFSgvhvQFMd9R7RnYDomw2 */

            public final void onClick(View view) {
                ThreadView.this.lambda$initializeContextMenuButton$5$ThreadView(view);
            }
        });
    }

    private void initializeDraftThreadParticipantRecyclerView() {
        DraftThreadParticipantListAdapter draftThreadParticipantListAdapter = new DraftThreadParticipantListAdapter(this.mPanelApp);
        this.mDraftThreadParticipantListAdapter = draftThreadParticipantListAdapter;
        draftThreadParticipantListAdapter.setHasStableIds(true);
        this.mBinding.draftThreadParticipantList.setAdapter(draftThreadParticipantListAdapter);
        this.mBinding.draftThreadParticipantList.setItemAnimator(null);
    }

    private void initializeLeaveChatConfirmationView() {
        this.mLeaveChatConfirmationView.mOnLeaveClickListener = new LeaveChatConfirmationView.OnLeaveClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$sAowPaMYCMw5uFkRTk1qG0ZTLyE2 */

            @Override // com.oculus.panelapp.messenger.views.LeaveChatConfirmationView.OnLeaveClickListener
            public final void onLeaveClick() {
                ThreadView.this.lambda$initializeLeaveChatConfirmationView$14$ThreadView();
            }
        };
    }

    private void initializeQuickReplyButtons() {
        int size = this.mQuickReplyHelper.mQuickReplies.size();
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding = this.mBinding;
        List asList = Arrays.asList(anytimeTabletMessengerViewV2Binding.quickReply0, anytimeTabletMessengerViewV2Binding.quickReply1, anytimeTabletMessengerViewV2Binding.quickReply2, anytimeTabletMessengerViewV2Binding.quickReply3, anytimeTabletMessengerViewV2Binding.quickReply4);
        for (int i = 0; i < asList.size(); i++) {
            OCButton oCButton = (OCButton) asList.get(i);
            if (i >= size) {
                oCButton.setVisibility(8);
            } else {
                String str = this.mQuickReplyHelper.mQuickReplies.get(i);
                oCButton.mEventHandler = this.mPanelApp;
                oCButton.setText(str);
                oCButton.setOnClickListener(new View.OnClickListener(str) {
                    /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$25AHuxkIUrdCfbzKtUUFGkbOjcI2 */
                    public final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ThreadView.this.lambda$initializeQuickReplyButtons$3$ThreadView(this.f$1, view);
                    }
                });
            }
        }
    }

    private void initializeRecyclerView() {
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding = this.mBinding;
        anytimeTabletMessengerViewV2Binding.messageList.mHasFixedSize = true;
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        Context context = this.mContext;
        ReactionPillCallback reactionPillCallback = new ReactionPillCallback(anytimeTabletMessengerViewV2Binding, messengerPanelApp, context, new MessageReactionsPopup(context, messengerPanelApp));
        this.mReactionPillCallback = reactionPillCallback;
        MessageListAdapter messageListAdapter = new MessageListAdapter(this.mPanelApp, reactionPillCallback);
        this.mAdapter = messageListAdapter;
        messageListAdapter.setHasStableIds(true);
        this.mBinding.messageList.setAdapter(messageListAdapter);
        ((LinearLayoutManager) this.mBinding.messageList.mLayout).setStackFromEnd(true);
        this.mBinding.messageList.addOnScrollListener(new AnonymousClass1CG() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass8 */

            @Override // X.AnonymousClass1CG
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.mLayout;
                boolean z = true;
                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    ThreadView threadView = ThreadView.this;
                    if (!threadView.mLastScrollAtEndOfList) {
                        threadView.mLastScrollAtEndOfList = true;
                        threadView.mPanelApp.getAPIManager().mCurrentAPI.updateMessageCount(ThreadView.this.mAdapter.getItemCount() + 10);
                        return;
                    }
                }
                ThreadView threadView2 = ThreadView.this;
                threadView2.mLastScrollAtEndOfList = false;
                if (threadView2.mAdapter.getItemCount() <= 0 || linearLayoutManager.findLastCompletelyVisibleItemPosition() != ThreadView.this.mAdapter.getItemCount() - 1) {
                    z = false;
                }
                threadView2.mMostRecentMessageVisible = z;
            }
        });
    }

    private void initializeSendButton() {
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding = this.mBinding;
        OCButton oCButton = anytimeTabletMessengerViewV2Binding.sendButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$RezYM_FUdYSRjThOGnYY0nAD86Q2 */

            public final void onClick(View view) {
                ThreadView.this.lambda$initializeSendButton$0$ThreadView(view);
            }
        });
        anytimeTabletMessengerViewV2Binding.composeTextInput.mCallback = new KeyboardHandler.KeyboardListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$83E8k46fLIxsbsKU7A2XCSWSM42 */

            @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
            public final void onKeyboardActionKey() {
                ThreadView.this.lambda$initializeSendButton$1$ThreadView();
            }
        };
    }

    private void initializeStartPartyButton() {
        boolean deviceConfig = this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_START_PARTY_ENTRYPOINT);
        this.mPassesPartyButtonGK = deviceConfig;
        this.mMessengerViewModel.setIsPassingPartyButtonGK(deviceConfig);
        if (this.mPassesPartyButtonGK) {
            this.mPartyButtonView.initialize();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: launchFbBlockFlow */
    public void lambda$initializeBlockedViews$9$ThreadView(String str, long j, String str2) {
        this.mPanelApp.actionNavigate(Uri.parse(SystemUXRoute.MESSENGER_INTEGRITY.path()).buildUpon().appendQueryParameter("user_fbid", str).appendQueryParameter("target_fbid", Long.toString(j)).appendQueryParameter("target_name", str2).build().toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: launchFbReportFlow */
    public void lambda$initializeBlockedViews$8$ThreadView(String str, long j, String str2, long j2) {
        this.mPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(this.mBinding.mRoot.getContext().getPackageName()).appendPath("0").appendPath("messenger").appendQueryParameter("reportee_fbid", Long.toString(j)).appendQueryParameter("reportee_name", str2).appendQueryParameter("reporter_fbid", str).appendQueryParameter("thread_key", Long.toString(j2)).build().toString());
    }

    private void launchOcBlockFlow(long j) {
        this.mPanelApp.actionNavigate(SystemUXRoute.USER_BLOCK, new Uri.Builder().encodedPath("/system_utilities/user_block/").appendPath(this.mBinding.mRoot.getContext().getPackageName()).appendPath(Long.toString(j)).appendPath("messenger").build().toString());
    }

    private void launchOcReportFlow(long j, long j2) {
        this.mPanelApp.actionNavigate(SystemUXRoute.USER_REPORT, new Uri.Builder().encodedPath("/system_utilities/user_report/").appendPath(this.mBinding.mRoot.getContext().getPackageName()).appendPath(Long.toString(j)).appendPath("oculus_chat").appendQueryParameter("thread_id", Long.toString(j2)).build().toString());
    }

    private void launchOcUnblockFlow(long j) {
        this.mPanelApp.actionNavigate(SystemUXRoute.USER_UNBLOCK, new Uri.Builder().encodedPath("/system_utilities/user_unblock/").appendPath(this.mBinding.mRoot.getContext().getPackageName()).appendPath(Long.toString(j)).appendPath("messenger").build().toString());
    }

    private void launchParticipantSelector(String str, long j, IntegrityActionType integrityActionType, boolean z) {
        this.mPanelApp.actionNavigate(Uri.parse(SystemUXRoute.MESSENGER_INTEGRITY.path()).buildUpon().appendQueryParameter("action_type", integrityActionType.getParamString()).appendQueryParameter("start_with_selector", "true").appendQueryParameter("only_show_blocked", String.valueOf(z)).appendQueryParameter("user_fbid", str).appendQueryParameter("thread_key", Long.toString(j)).build().toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: leaveThread */
    public void lambda$initializeLeaveChatConfirmationView$14$ThreadView() {
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        iMessengerAPI.leaveGroupThread(iMessengerAPI.getCurrentThread());
        selectFirstThread();
    }

    private void onContextMenuLeaveChatClick() {
        if (this.mGroupThreadBlockedView.mIsShowing) {
            lambda$initializeBlockedViews$11$ThreadView();
        } else {
            triggerLeaveChatFromGroupWithoutBlocked();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void scrollToMostRecent() {
        MessageListAdapter messageListAdapter = this.mAdapter;
        if (messageListAdapter != null) {
            this.mBinding.messageList.smoothScrollToPosition(messageListAdapter.getItemCount() - 1);
        }
    }

    private void selectFirstThread() {
        IMessengerAPI iMessengerAPI = this.mPanelApp.getAPIManager().mCurrentAPI;
        if (!iMessengerAPI.getThreads().isEmpty()) {
            final long currentTimeMillis = System.currentTimeMillis();
            iMessengerAPI.updateCurrentThread(iMessengerAPI.getThreads().get(0).getThreadKey(), new MessengerActionCallback() {
                /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass9 */

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onError(String str, String str2) {
                    Log.e(ThreadView.TAG, "Update current thread failed");
                    ThreadView.this.mPanelApp.getLogger().logActionFailure(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.THREAD_VIEW, str2, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                }

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onSuccess(String str) {
                    ThreadView.this.mPanelApp.getLogger().logActionSuccess(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.THREAD_VIEW, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        }
    }

    private void setComposeBarText(String str) {
        this.mBinding.composeTextInput.setText(str);
    }

    private void submitMessage(final ClickEventButtonId clickEventButtonId) {
        if (!this.mBinding.composeTextInput.getText().toString().trim().isEmpty()) {
            this.mBinding.composeTextInput.getText();
            final long currentTimeMillis = System.currentTimeMillis();
            this.mPanelApp.getAPIManager().sendMessage(this.mBinding.composeTextInput.getText().toString(), new MessengerActionCallback() {
                /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass5 */

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onError(String str, String str2) {
                    Log.e(ThreadView.TAG, "Send message failed");
                    ThreadView.this.mPanelApp.getLogger().logActionFailure(ActionId.MESSAGE_SEND, clickEventButtonId, SurfaceType.THREAD_VIEW, str2, "thread_id", str);
                }

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onSuccess(String str) {
                    ThreadView.this.mPanelApp.getLogger().logActionSuccess(ActionId.MESSAGE_SEND, clickEventButtonId, SurfaceType.THREAD_VIEW, "thread_id", str);
                }
            }, new MessengerActionCallback() {
                /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass6 */

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onError(String str, String str2) {
                    Log.e(ThreadView.TAG, "Update current thread failed (from message send)");
                    ThreadView.this.mPanelApp.getLogger().logActionFailure(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.MESSAGE_COMPOSE, str2, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                }

                @Override // com.oculus.panelapp.messenger.api.MessengerActionCallback
                public void onSuccess(String str) {
                    ThreadView.this.mPanelApp.getLogger().logActionSuccess(ActionId.MESSAGE_THREAD_INITIAL_LOAD, ClickEventButtonId.NO_BUTTON, SurfaceType.MESSAGE_COMPOSE, "thread_id", str, LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: triggerLeaveChatFromGroupContainingBlocked */
    public void lambda$initializeBlockedViews$11$ThreadView() {
        this.mGroupThreadBlockedView.hide();
        this.mMessengerViewModel.setIsGroupThreadContainingBlockedViewShowing(false);
        this.mMessengerViewModel.setIsLeaveThreadConfirmationShowing(true);
        this.mLeaveChatConfirmationView.show();
        this.mLeaveChatConfirmationView.mOnCancelClickListener = new LeaveChatConfirmationView.OnCancelClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$UGmeM9z8_v71p57g9zJV7oBYcQg2 */

            @Override // com.oculus.panelapp.messenger.views.LeaveChatConfirmationView.OnCancelClickListener
            public final void onCancelClick() {
                ThreadView.this.lambda$triggerLeaveChatFromGroupContainingBlocked$6$ThreadView();
            }
        };
    }

    private void triggerLeaveChatFromGroupWithoutBlocked() {
        this.mMessengerViewModel.setIsLeaveThreadConfirmationShowing(true);
        this.mLeaveChatConfirmationView.show();
        this.mLeaveChatConfirmationView.mOnCancelClickListener = new LeaveChatConfirmationView.OnCancelClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$braughUBHpdcr2pP66uaR31Ox82 */

            @Override // com.oculus.panelapp.messenger.views.LeaveChatConfirmationView.OnCancelClickListener
            public final void onCancelClick() {
                ThreadView.this.lambda$triggerLeaveChatFromGroupWithoutBlocked$7$ThreadView();
            }
        };
    }

    public void destroy() {
        this.mPanelApp.getAPIManager().removeCurrentThreadListener(this.mThreadListener);
        this.mThreadListener = null;
        this.mPanelApp.getAPIManager().removeDraftThreadListener(this.mDraftThreadListener);
        this.mDraftThreadListener = null;
        this.mAdapter = null;
        this.mBinding.messageList.setAdapter(null);
        this.mDraftThreadParticipantListAdapter = null;
        this.mBinding.draftThreadParticipantList.setAdapter(null);
        this.mBinding.messageList.addOnScrollListener(null);
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding = this.mBinding;
        anytimeTabletMessengerViewV2Binding.composeTextInput.destroy();
        OCButton oCButton = anytimeTabletMessengerViewV2Binding.audioInputButton;
        oCButton.mEventHandler = null;
        OCButton oCButton2 = anytimeTabletMessengerViewV2Binding.sendButton;
        oCButton2.mEventHandler = null;
        OCButton oCButton3 = anytimeTabletMessengerViewV2Binding.quickReply0;
        oCButton3.mEventHandler = null;
        OCButton oCButton4 = anytimeTabletMessengerViewV2Binding.quickReply1;
        oCButton4.mEventHandler = null;
        OCButton oCButton5 = anytimeTabletMessengerViewV2Binding.quickReply2;
        oCButton5.mEventHandler = null;
        OCButton oCButton6 = anytimeTabletMessengerViewV2Binding.threadContextMenuButton;
        oCButton6.mEventHandler = null;
        oCButton.setOnClickListener(null);
        oCButton2.setOnClickListener(null);
        oCButton3.setOnClickListener(null);
        oCButton4.setOnClickListener(null);
        oCButton5.setOnClickListener(null);
        oCButton6.setOnClickListener(null);
        OCDropdown<ThreadViewContextMenuItem> oCDropdown = this.mContextMenu;
        if (oCDropdown != null) {
            oCDropdown.setOnItemClick(null);
            oCDropdown.setEventHandler(null);
            this.mContextMenu = null;
        }
        this.mLeaveChatConfirmationView.destroy();
        this.mLeaveChatConfirmationView = null;
        this.mGroupThreadBlockedView.destroy();
        this.mGroupThreadBlockedView = null;
        this.mOneOnOneThreadBlockedView.destroy();
        this.mOneOnOneThreadBlockedView = null;
        if (this.mPassesPartyButtonGK) {
            this.mPartyButtonView.destroy();
            this.mPartyButtonView = null;
        }
        ReactionPillCallback reactionPillCallback = this.mReactionPillCallback;
        if (reactionPillCallback != null) {
            reactionPillCallback.destroy();
        }
    }

    public /* synthetic */ void lambda$initializeAudioInputButton$2$ThreadView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_AUDIO_INPUT_BUTTON, SurfaceType.THREAD_VIEW);
        setComposeBarType(ComposeBarType.AUDIO_INPUT);
        this.mPanelApp.getAndroidPanelApp().mFrameCommandChannel.sendCommand(AnonymousClass006.A09(KEYBOARD_OPEN, this.mPanelApp.getAndroidPanelApp().getClass().getCanonicalName(), DICTATION_MODE));
    }

    public /* synthetic */ void lambda$initializeBlockedViews$10$ThreadView() {
        this.mGroupThreadBlockedView.hide();
        this.mMessengerViewModel.setIsGroupThreadContainingBlockedViewShowing(false);
        this.mOptedInThreadKey = Long.valueOf(this.mPanelApp.getAPIManager().mCurrentAPI.getCurrentThread().getThreadKey());
    }

    public /* synthetic */ void lambda$initializeBlockedViews$12$ThreadView(long j, String str) {
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.FB_MSYS) {
            String userID = this.mPanelApp.getAPIManager().mCurrentAPI.getUserID();
            if (userID == null) {
                Log.e(TAG, "viewer user id is null when trying to block other user in group thread");
            } else {
                lambda$initializeBlockedViews$9$ThreadView(userID, j, str);
            }
        } else {
            launchOcUnblockFlow(j);
        }
    }

    public /* synthetic */ void lambda$initializeContextMenuButton$5$ThreadView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_CONTEXT_MENU, SurfaceType.THREAD_VIEW);
        OCDropdown<ThreadViewContextMenuItem> oCDropdown = this.mContextMenu;
        if (oCDropdown == null) {
            this.mContextMenu = new OCDropdown<>(this.mContext);
            bindContextMenu();
            oCDropdown = this.mContextMenu;
            oCDropdown.setOnItemClick(new Function() {
                /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$dWn2e9JuW2PQJoJmDYrc5no8ea02 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThreadView.this.lambda$null$4$ThreadView((ThreadViewContextMenuItem) obj);
                }
            });
            oCDropdown.setEventHandler(this.mPanelApp);
        }
        oCDropdown.toggle(view);
    }

    public /* synthetic */ void lambda$initializeQuickReplyButtons$3$ThreadView(String str, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_QUICK_REPLY_BUTTON, SurfaceType.THREAD_VIEW);
        setComposeBarType(ComposeBarType.TEXT_INPUT);
        setComposeBarText(str);
    }

    public /* synthetic */ void lambda$initializeSendButton$0$ThreadView(View view) {
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_SEND_BUTTON;
        messengerPanelApp.logButtonClick(clickEventButtonId, SurfaceType.THREAD_VIEW);
        submitMessage(clickEventButtonId);
        setComposeBarType(ComposeBarType.COLLAPSED);
        setComposeBarText(null);
    }

    public /* synthetic */ void lambda$initializeSendButton$1$ThreadView() {
        MessengerPanelApp messengerPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_COMPOSE_SEND_KEYBOARD_GO;
        messengerPanelApp.logButtonClick(clickEventButtonId, SurfaceType.THREAD_VIEW);
        submitMessage(clickEventButtonId);
        setComposeBarType(ComposeBarType.COLLAPSED);
        setComposeBarText(null);
    }

    public ThreadView(MessengerPanelApp messengerPanelApp, AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding, final MessengerViewModel messengerViewModel) {
        this.mBinding = anytimeTabletMessengerViewV2Binding;
        this.mPanelApp = messengerPanelApp;
        this.mContext = anytimeTabletMessengerViewV2Binding.mRoot.getContext();
        this.mPassesQuickRepliesGK = messengerPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_QUICK_REPLIES);
        this.mQuickReplyHelper = new QuickReplyHelper(this.mContext);
        this.mMessengerViewModel = messengerViewModel;
        MessengerPanelApp messengerPanelApp2 = this.mPanelApp;
        AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding2 = this.mBinding;
        this.mLeaveChatConfirmationView = new LeaveChatConfirmationView(messengerPanelApp2, anytimeTabletMessengerViewV2Binding2.leaveChatConfirmationStub);
        this.mGroupThreadBlockedView = new GroupThreadBlockedView(messengerPanelApp2, anytimeTabletMessengerViewV2Binding2.groupThreadContainingBlockedStub, this.mContext.getResources());
        this.mOneOnOneThreadBlockedView = new OneOnOneThreadBlockedView(this.mPanelApp, this.mBinding.oneOnOneBlockedThreadStub, this.mContext.getResources());
        initializeBlockedViews();
        initializeLeaveChatConfirmationView();
        this.mMostRecentMessageVisible = true;
        OCButton oCButton = this.mBinding.startParty;
        Context context = this.mContext;
        this.mPartyButtonView = new PartyButtonView(oCButton, context, this.mPanelApp, this.mMessengerViewModel, new PartiesFetcher(context), new PartyMutator(context));
        initializeRecyclerView();
        initializeDraftThreadParticipantRecyclerView();
        this.mDraftThreadListener = new MessengerAPIManager.DraftThreadListener() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass1 */

            public static /* synthetic */ DraftThreadParticipantAdapterItem lambda$onDraftThreadUpdate$0(MessengerContact messengerContact) {
                return new DraftThreadParticipantAdapterItem(messengerContact);
            }

            public /* synthetic */ void lambda$onDraftThreadUpdate$1$ThreadView$1() {
                if (ThreadView.this.mDraftThreadParticipantListAdapter.getItemCount() > 0) {
                    ThreadView threadView = ThreadView.this;
                    threadView.mBinding.draftThreadParticipantList.smoothScrollToPosition(threadView.mDraftThreadParticipantListAdapter.getItemCount() - 1);
                }
            }

            @Override // com.oculus.panelapp.messenger.api.MessengerAPIManager.DraftThreadListener
            public void onDraftThreadUpdate(DraftThread draftThread) {
                ThreadView.this.mMessengerViewModel.setDraftThread(draftThread);
                if (draftThread != null) {
                    messengerViewModel.setIsGroupThreadContainingBlockedViewShowing(false);
                    messengerViewModel.setIsOneOnOneBlockedThreadShowing(false);
                    messengerViewModel.setIsLeaveThreadConfirmationShowing(false);
                    messengerViewModel.setIsOneOnOneThreadViewerCannotMessage(false, ThreadView.this.mPanelApp.getAPIManager().mCurrentAPI.getType());
                    ThreadView.this.mGroupThreadBlockedView.hide();
                    ThreadView.this.mOneOnOneThreadBlockedView.hide();
                    ThreadView.this.mLeaveChatConfirmationView.hide();
                }
                ArrayList arrayList = new ArrayList();
                if (draftThread != null) {
                    arrayList = (ArrayList) draftThread.mParticipants.stream().map($$Lambda$ThreadView$1$9rIHQTjkzWNzGL0m9X_Bp4zYiYM2.INSTANCE).collect(Collectors.toCollection($$Lambda$OGSS2qx6njxlnp0dnKb4lA3jnw82.INSTANCE));
                    arrayList.add(new DraftThreadParticipantTextInputAdapterItem());
                }
                ThreadView.this.mDraftThreadParticipantListAdapter.submitList(arrayList, new Runnable() {
                    /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$1$duFy4vm9aWC0zhbdRS6iwcVVGQ2 */

                    public final void run() {
                        ThreadView.AnonymousClass1.this.lambda$onDraftThreadUpdate$1$ThreadView$1();
                    }
                });
            }
        };
        this.mPanelApp.getAPIManager().registerDraftThreadListener(this.mDraftThreadListener);
        this.mThreadListener = new ThreadListener() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass2 */

            public /* synthetic */ void lambda$onThreadUpdate$0$ThreadView$2(MessengerMessage messengerMessage) {
                if (ThreadView.this.shouldScrollToMostRecent(messengerMessage)) {
                    ThreadView.this.scrollToMostRecent();
                }
                if (messengerMessage != null) {
                    ThreadView.this.mPreviousMessageId = messengerMessage.getMessageId();
                }
            }

            @Override // com.oculus.panelapp.messenger.api.ThreadListener
            public void onThreadUpdate(MessengerThread messengerThread, List<MessengerMessage> list) {
                if (ThreadView.this.mPanelApp.getAPIManager().mDraftThread == null) {
                    List<MessengerParticipant> threadParticipants = ThreadView.this.mPanelApp.getAPIManager().mCurrentAPI.getThreadParticipants();
                    ThreadView.this.handleOcOneOnOneBlockedThread(threadParticipants);
                    MessengerViewModel messengerViewModel = ThreadView.this.mMessengerViewModel;
                    boolean z = false;
                    if (threadParticipants.size() == 0) {
                        z = true;
                    }
                    messengerViewModel.setIsThreadParticipantListEmpty(z);
                    MessengerMessage mostRecentMessageIn = ThreadView.mostRecentMessageIn(list);
                    ThreadView.this.mAdapter.submitList(list, new Runnable(mostRecentMessageIn) {
                        /* class com.oculus.panelapp.messenger.views.$$Lambda$ThreadView$2$K5AaXnmTDh1oiTeCgak1xsjHduU2 */
                        public final /* synthetic */ MessengerMessage f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ThreadView.AnonymousClass2.this.lambda$onThreadUpdate$0$ThreadView$2(this.f$1);
                        }
                    });
                    if (mostRecentMessageIn != null) {
                        mostRecentMessageIn.getText();
                    }
                    ThreadView.this.mMessengerViewModel.setCurrentThreadTitle(messengerThread.getThreadName());
                    ThreadView threadView = ThreadView.this;
                    threadView.mBinding.facepileThreadIcon.setIcon(threadView.mPanelApp, messengerThread);
                    ThreadView threadView2 = ThreadView.this;
                    threadView2.mBinding.facepileThreadIcon.setFacepileBorderBackground(threadView2.mContext.getResources().getDrawable(R.drawable.anytime_tablet_messenger_header_facepile_icon_border, ThreadView.this.mContext.getTheme()));
                    ThreadView.this.mLeaveChatConfirmationView.hide();
                    ThreadView.this.mMessengerViewModel.setIsLeaveThreadConfirmationShowing(false);
                    ThreadView.this.mPartyButtonView.refresh();
                    threadParticipants.toString();
                    ThreadView.this.bindBlockedViews(messengerThread, threadParticipants);
                    ThreadView threadView3 = ThreadView.this;
                    threadView3.mMessengerViewModel.setIsSelfThread(threadView3.isSelfThread(messengerThread, threadParticipants));
                    ThreadView.this.bindContextMenu();
                    ThreadView.this.blockComposerIfViewerCannotMessage(messengerThread.getThreadKey(), threadParticipants);
                }
            }
        };
        this.mPanelApp.getAPIManager().registerCurrentThreadListener(this.mThreadListener);
        initializeAudioInputButton();
        initializeComposeText();
        initializeComposeScroll();
        initializeSendButton();
        initializeQuickReplyButtons();
        initializeStartPartyButton();
        initializeContextMenuButton();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void bindBlockedViews(MessengerThread messengerThread, List<MessengerParticipant> list) {
        List<MessengerParticipant> blockedParticipants = ThreadHelper.getBlockedParticipants(list);
        if (this.mOptedInThreadKey == null || messengerThread.getThreadKey() != this.mOptedInThreadKey.longValue()) {
            this.mOptedInThreadKey = null;
            if (ThreadHelper.isOneOnOneBlockedThread(list, blockedParticipants)) {
                this.mOneOnOneThreadBlockedView.show(list, messengerThread.getThreadKey());
                this.mOneOnOneBlockedShownConstraintSet.A09(this.mBinding.messengerView);
                this.mMessengerViewModel.setIsOneOnOneBlockedThreadShowing(true);
            } else {
                this.mOneOnOneThreadBlockedView.hide();
                this.mOneOnOneBlockedHiddenConstraintSet.A09(this.mBinding.messengerView);
                this.mMessengerViewModel.setIsOneOnOneBlockedThreadShowing(false);
            }
            if (ThreadHelper.isGroupContainingBlockedThread(list, blockedParticipants)) {
                this.mGroupThreadBlockedView.show(messengerThread, blockedParticipants);
                this.mMessengerViewModel.setIsGroupThreadContainingBlockedViewShowing(true);
                return;
            }
            this.mGroupThreadBlockedView.hide();
            this.mMessengerViewModel.setIsGroupThreadContainingBlockedViewShowing(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isSelfThread(MessengerThread messengerThread, List<MessengerParticipant> list) {
        if (list.size() == 1 && list.get(0).getParticipantId() == messengerThread.getThreadKey()) {
            return true;
        }
        return false;
    }

    @Nullable
    public static MessengerMessage mostRecentMessageIn(List<MessengerMessage> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComposeBarType(ComposeBarType composeBarType) {
        Resources resources;
        int i;
        composeBarType.toString();
        if (!this.mPassesQuickRepliesGK) {
            if (composeBarType == ComposeBarType.AUDIO_INPUT) {
                this.mBinding.composeTextInput.requestFocus();
            }
            ComposeBarType composeBarType2 = ComposeBarType.TEXT_INPUT;
            composeBarType2.toString();
            this.mMessengerViewModel.setComposeBarType(composeBarType2);
            ViewGroup.LayoutParams layoutParams = this.mBinding.composeTextInput.getLayoutParams();
            layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_compose_text_input_width_expanded);
            this.mBinding.composeTextInput.setLayoutParams(layoutParams);
            return;
        }
        ComposeBarType composeBarType3 = ComposeBarType.COLLAPSED;
        if (composeBarType == composeBarType3) {
            this.mMessengerViewModel.setComposeBarType(composeBarType3);
            this.mBinding.composeTextInput.clearFocus();
        } else {
            ComposeBarType composeBarType4 = ComposeBarType.TEXT_INPUT;
            if (composeBarType == composeBarType4) {
                this.mMessengerViewModel.setComposeBarType(composeBarType4);
                this.mBinding.composeTextInput.getLayoutParams().width = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_compose_text_input_width_expanded);
            } else {
                ComposeBarType composeBarType5 = ComposeBarType.AUDIO_INPUT;
                if (composeBarType == composeBarType5) {
                    this.mMessengerViewModel.setComposeBarType(composeBarType5);
                }
            }
            this.mBinding.composeTextInput.requestFocus();
        }
        int measuredWidth = this.mBinding.composeTextInput.getMeasuredWidth();
        if (composeBarType == composeBarType3) {
            resources = this.mContext.getResources();
            i = R.dimen.messenger_tablet_compose_text_input_width_collapsed;
        } else {
            resources = this.mContext.getResources();
            i = R.dimen.messenger_tablet_compose_text_input_width_expanded;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(measuredWidth, resources.getDimensionPixelSize(i));
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.oculus.panelapp.messenger.views.ThreadView.AnonymousClass7 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Number) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = ThreadView.this.mBinding.composeTextInput.getLayoutParams();
                layoutParams.width = intValue;
                ThreadView.this.mBinding.composeTextInput.setLayoutParams(layoutParams);
            }
        });
        ofInt.setDuration(300L);
        ofInt.start();
    }

    public /* synthetic */ void lambda$blockComposerIfViewerCannotMessage$15$ThreadView(IMessengerAPI iMessengerAPI, long j, Boolean bool) {
        if (iMessengerAPI.getCurrentThread() != null && j == iMessengerAPI.getCurrentThread().getThreadKey()) {
            this.mMessengerViewModel.setIsOneOnOneThreadViewerCannotMessage(!bool.booleanValue(), iMessengerAPI.getType());
        }
    }

    public /* synthetic */ Object lambda$null$4$ThreadView(ThreadViewContextMenuItem threadViewContextMenuItem) {
        MessengerPanelApp messengerPanelApp;
        ClickEventButtonId clickEventButtonId;
        switch (threadViewContextMenuItem.ordinal()) {
            case 0:
                messengerPanelApp = this.mPanelApp;
                clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_CONTEXT_MENU_BLOCK;
                break;
            case 1:
                messengerPanelApp = this.mPanelApp;
                clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_THREAD_CONTEXT_MENU_UNBLOCK;
                break;
            case 2:
                onContextMenuReportClick();
                return null;
            case 3:
                onContextMenuLeaveChatClick();
                return null;
            default:
                Log.e(TAG, "Received unexpected context menu item");
                return null;
        }
        messengerPanelApp.logButtonClick(clickEventButtonId, SurfaceType.THREAD_VIEW);
        onContextMenuBlockClick();
        return null;
    }
}
