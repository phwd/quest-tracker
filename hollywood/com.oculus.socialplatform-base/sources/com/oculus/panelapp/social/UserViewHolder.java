package com.oculus.panelapp.social;

import X.AnonymousClass006;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.SeekBar;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.SocialUserRecyclerViewHolder;
import com.oculus.panelapp.social.UserViewHolder;
import com.oculus.panelapp.social.actions.AddFriend;
import com.oculus.panelapp.social.actions.AddToParty;
import com.oculus.panelapp.social.actions.Block;
import com.oculus.panelapp.social.actions.CancelPartyInvite;
import com.oculus.panelapp.social.actions.Chat;
import com.oculus.panelapp.social.actions.CreatePartyWith;
import com.oculus.panelapp.social.actions.Goto;
import com.oculus.panelapp.social.actions.KickFromParty;
import com.oculus.panelapp.social.actions.Report;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.actions.SocialUserActionActionHandler;
import com.oculus.panelapp.social.actions.SocialUserActionType;
import com.oculus.panelapp.social.actions.UnFriend;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding;
import com.oculus.socialplatform.R;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.views.ShellButton;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class UserViewHolder extends SocialUserRecyclerViewHolder {
    public static final String TAG = LoggingUtil.tag(UserViewHolder.class);
    public AnytimeTabletSocialListItemV2Binding mBinding;
    public Context mContext;
    public int mMute;
    public SocialPanelApp mPanelApp;
    @Nullable
    public SocialPopupMenu mPopUpmenu;
    public ProfilePictureHelper mProfilePictureHelper = new ProfilePictureHelper(this.mContext);
    public SocialViewModel mSocialViewModel;
    @Nullable
    public SocialUserAdapterItem mUserForRefresh;

    private boolean shouldShowGroupLaunchResponse() {
        return false;
    }

    /* renamed from: com.oculus.panelapp.social.UserViewHolder$7  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType;

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
                com.oculus.horizoncontent.social.SocialUser$UserRowType[] r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType = r2
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_CONTROLS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_MEMBER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.FRIEND     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.INVITED     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.<clinit>():void");
        }
    }

    public UserViewHolder(AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListItemV2Binding.socialListItem);
        this.mBinding = anytimeTabletSocialListItemV2Binding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
        this.mSocialViewModel = socialPanelApp.acquireSocialViewModel();
    }

    private String getGroupLaunchResponseString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        Resources resources;
        int i;
        if (socialGroupLaunchResponse == SocialGroupLaunchResponse.ACCEPT) {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_social_party_group_launch_response_accept_subtitle;
        } else {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_social_party_group_launch_response_pending_subtitle;
        }
        return resources.getString(i);
    }

    private int getPartyLocalMute() {
        return HorizonContentProviderHelper.getPartyLocalMute(this.mContext);
    }

    private void initializeActionButton(SocialUser socialUser, SocialParty.PartyMembership partyMembership) {
        this.mBinding.actionButton.button.clearOnClickListener();
        this.mBinding.callButton.clearOnClickListener();
        this.mBinding.callButton.setVisibility(8);
        this.mBinding.partyVolumeSlider.seekbar.setVisibility(8);
        this.mBinding.partyVolumeSlider.icon.setVisibility(8);
        switch (socialUser.mUserType.ordinal()) {
            case 2:
                if (socialUser.getIsActive(this.mContext.getResources())) {
                    if (showCallButton()) {
                        this.mBinding.actionButton.button.setVisibility(8);
                        this.mBinding.callButton.setVisibility(0);
                        this.mBinding.callButton.setOnClickListener(new View.OnClickListener() {
                            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$OhJ1Dv2fZw561_0wbhtcAUgPtdo2 */

                            public final void onClick(View view) {
                                UserViewHolder.this.lambda$initializeActionButton$6$UserViewHolder(view);
                            }
                        });
                        return;
                    } else if (partyMembership != SocialParty.PartyMembership.MEMBER) {
                        if (partyMembership != SocialParty.PartyMembership.INVITED) {
                            this.mBinding.actionButton.button.setVisibility(0);
                            this.mBinding.setActionButtonText(SocialUserActionType.ADD_TO_PARTY.getActionString(this.mContext.getResources()));
                            this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                                /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$IQlhcbtTn2_bfGbSojhHOL49hqg2 */

                                public final void onClick(View view) {
                                    UserViewHolder.this.lambda$initializeActionButton$8$UserViewHolder(view);
                                }
                            });
                            return;
                        } else if (this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
                            this.mBinding.actionButton.button.setVisibility(0);
                            this.mBinding.setActionButtonText(SocialUserActionType.CANCEL_PARTY_INVITE.getActionString(this.mContext.getResources()));
                            this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                                /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$OyoAb7ykhfFZwgzp3UFZk4fUPHg2 */

                                public final void onClick(View view) {
                                    UserViewHolder.this.lambda$initializeActionButton$7$UserViewHolder(view);
                                }
                            });
                            return;
                        }
                    }
                }
                break;
            case 5:
                if (socialUser.mDeepLink != null) {
                    this.mBinding.actionButton.button.setVisibility(0);
                    this.mBinding.setActionButtonText(SocialUserActionType.GOTO.getActionString(this.mContext.getResources()));
                    this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                        /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$S_H1_ISYquefoMc5jWaLUFVepSA2 */

                        public final void onClick(View view) {
                            UserViewHolder.this.lambda$initializeActionButton$9$UserViewHolder(view);
                        }
                    });
                    return;
                }
                break;
            case 6:
                this.mBinding.partyVolumeSlider.seekbar.setVisibility(0);
                this.mBinding.partyVolumeSlider.icon.setVisibility(0);
                break;
        }
        this.mBinding.actionButton.button.setVisibility(8);
    }

    private void initializeChatButton(SocialUser socialUser) {
        float f;
        if (socialUser.mUserType == SocialUser.UserRowType.FRIEND) {
            this.mBinding.chatButton.setVisibility(0);
            this.mBinding.chatButton.clearOnClickListener();
            boolean z = socialUser.mCanViewerMessage;
            ShellButton shellButton = this.mBinding.chatButton;
            if (!z) {
                f = 0.3f;
            } else {
                f = 1.0f;
            }
            shellButton.setAlpha(f);
            this.mBinding.chatButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$nahRM8aKG9nOOSzapVxHih3K0ts2 */

                public final void onClick(View view) {
                    UserViewHolder.this.lambda$initializeChatButton$4$UserViewHolder(view);
                }
            });
            return;
        }
        this.mBinding.chatButton.setVisibility(8);
    }

    private void initializeMenuButton(SocialUser socialUser, SocialParty.PartyMembership partyMembership) {
        this.mBinding.menuButton.clearOnClickListener();
        this.mBinding.partyMute.setVisibility(8);
        switch (socialUser.mUserType.ordinal()) {
            case 2:
            case 5:
                this.mBinding.menuButton.setBackground(this.mContext.getDrawable(R.drawable.oc_icon_more_vertical_filled_24_d2d2d2));
                this.mBinding.menuButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$UY_BeZHyx8MM7Aqx0_ew52ZaHc2 */

                    public final void onClick(View view) {
                        UserViewHolder.this.lambda$initializeMenuButton$2$UserViewHolder(view);
                    }
                });
                return;
            case 3:
                if (this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
                    this.mBinding.menuButton.setVisibility(0);
                    this.mBinding.menuButton.setBackground(this.mContext.getDrawable(R.drawable.oc_icon_close_filled_24_d2d2d2));
                    this.mBinding.menuButton.setOnClickListener(new View.OnClickListener() {
                        /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$BwgTIgImdCG923tpdMPQdyePcaE2 */

                        public final void onClick(View view) {
                            UserViewHolder.this.lambda$initializeMenuButton$3$UserViewHolder(view);
                        }
                    });
                    return;
                }
                this.mBinding.menuButton.setVisibility(8);
                return;
            case 4:
            default:
                return;
            case 6:
                this.mBinding.partyMute.setVisibility(0);
                this.mBinding.menuButton.setVisibility(8);
                return;
        }
    }

    private void initializeName(SocialUser socialUser) {
        this.mBinding.setNameText(socialUser.mAlias);
    }

    private void initializePartyVOIPControls() {
        int partyLocalMute = HorizonContentProviderHelper.getPartyLocalMute(this.mContext);
        this.mMute = partyLocalMute;
        AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = this.mBinding;
        boolean z = true;
        if (partyLocalMute != 1) {
            z = false;
        }
        anytimeTabletSocialListItemV2Binding.setMuted(z);
        this.mBinding.partyMute.clearOnClickListener();
        this.mBinding.partyMute.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$J4sMnyn01tLeMnj61faa5FwTJMU2 */

            public final void onClick(View view) {
                UserViewHolder.this.lambda$initializePartyVOIPControls$0$UserViewHolder(view);
            }
        });
        final SeekBar seekBar = this.mBinding.partyVolumeSlider.seekbar;
        new Timer().schedule(new TimerTask() {
            /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass1 */

            public /* synthetic */ void lambda$run$0$UserViewHolder$1(SeekBar seekBar) {
                UserViewHolder userViewHolder = UserViewHolder.this;
                userViewHolder.mMute = HorizonContentProviderHelper.getPartyLocalMute(userViewHolder.mContext);
                UserViewHolder userViewHolder2 = UserViewHolder.this;
                AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = userViewHolder2.mBinding;
                int i = userViewHolder2.mMute;
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                anytimeTabletSocialListItemV2Binding.setMuted(z);
                seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(UserViewHolder.this.mContext) * 100.0f));
            }

            public void run() {
                UiThreadExecutor.getInstance().execute(new Runnable(seekBar) {
                    /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$1$pp0Gz_7_FgXF6Gkb2LR4cpae_o2 */
                    public final /* synthetic */ SeekBar f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        UserViewHolder.AnonymousClass1.this.lambda$run$0$UserViewHolder$1(this.f$1);
                    }
                });
            }
        }, 2000);
        HorizonContentProviderHelper.getPartyVolume(this.mContext);
        seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass2 */

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                float f = ((float) i) / 100.0f;
                if (z) {
                    HorizonContentProviderHelper.setPartyVolume(UserViewHolder.this.mContext, f);
                } else {
                    seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(UserViewHolder.this.mContext) * 100.0f));
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initializeProfilePicture(SocialUser socialUser) {
        this.mProfilePictureHelper.setImageViewFromUrl(this.mBinding.icon, socialUser.mProfilePhotoURL, socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
        this.mBinding.icon.setOnClickListener(new View.OnClickListener(socialUser) {
            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$_9lOEGZJ7VNPNJL9yzyjSMrgJ3E2 */
            public final /* synthetic */ SocialUser f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserViewHolder.this.lambda$initializeProfilePicture$1$UserViewHolder(this.f$1, view);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003b, code lost:
        if (r2 != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        if (r4 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r5.mBinding.setSubtitleText(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r4.isEmpty() != false) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeSubtitle(com.oculus.horizoncontent.social.SocialUser r6, com.oculus.horizoncontent.social.SocialGroupLaunchResponse r7) {
        /*
            r5 = this;
            java.lang.String r4 = r6.mPresenceString
            r3 = 0
            if (r4 == 0) goto L_0x000c
            boolean r0 = r4.isEmpty()
            r2 = 1
            if (r0 == 0) goto L_0x000d
        L_0x000c:
            r2 = 0
        L_0x000d:
            com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = r6.mUserType
            int r0 = r0.ordinal()
            r1 = 8
            switch(r0) {
                case 2: goto L_0x003b;
                case 3: goto L_0x0020;
                case 4: goto L_0x0018;
                case 5: goto L_0x003e;
                case 6: goto L_0x0033;
                default: goto L_0x0018;
            }
        L_0x0018:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding r0 = r5.mBinding
            com.oculus.ocui.OCTextView r0 = r0.subtitle
            r0.setVisibility(r1)
            return
        L_0x0020:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding r2 = r5.mBinding
            android.content.Context r0 = r5.mContext
            android.content.res.Resources r1 = r0.getResources()
            r0 = 2131689622(0x7f0f0096, float:1.9008265E38)
            java.lang.String r0 = r1.getString(r0)
            r2.setSubtitleText(r0)
            goto L_0x0047
        L_0x0033:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding r0 = r5.mBinding
            com.oculus.ocui.OCTextView r0 = r0.subtitle
            r0.setVisibility(r1)
            goto L_0x0018
        L_0x003b:
            if (r2 == 0) goto L_0x0018
            goto L_0x0042
        L_0x003e:
            if (r2 == 0) goto L_0x0018
            if (r4 == 0) goto L_0x0018
        L_0x0042:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding r0 = r5.mBinding
            r0.setSubtitleText(r4)
        L_0x0047:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2Binding r0 = r5.mBinding
            com.oculus.ocui.OCTextView r0 = r0.subtitle
            r0.setVisibility(r3)
            return
            switch-data {2->0x003b, 3->0x0020, 4->0x0018, 5->0x003e, 6->0x0033, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.UserViewHolder.initializeSubtitle(com.oculus.horizoncontent.social.SocialUser, com.oculus.horizoncontent.social.SocialGroupLaunchResponse):void");
    }

    public static /* synthetic */ boolean lambda$getPossibleActions$5(SocialUserAction socialUserAction) {
        if (socialUserAction == null || !socialUserAction.isRelevant()) {
            return false;
        }
        return true;
    }

    private boolean showCallButton() {
        SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
        if (socialUserAdapterItem.mUser.mUserType == SocialUser.UserRowType.FRIEND && socialUserAdapterItem.getPartyMembership() == null && this.mUserForRefresh.mUser.getIsActive(this.mContext.getResources())) {
            return true;
        }
        return false;
    }

    private void toggleMuted() {
        ClickEventButtonId clickEventButtonId;
        boolean z = true;
        int i = 1;
        if (this.mMute == 1) {
            i = 2;
        }
        this.mMute = i;
        if (i == 1) {
            clickEventButtonId = ClickEventButtonId.AUIV2_SOCIAL_PARTIES_MUTE_SELF;
        } else {
            clickEventButtonId = ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UNMUTE_SELF;
        }
        this.mPanelApp.logButtonClick(clickEventButtonId);
        AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = this.mBinding;
        if (this.mMute != 1) {
            z = false;
        }
        anytimeTabletSocialListItemV2Binding.setMuted(z);
        HorizonContentProviderHelper.setPartyLocalMuteAsync(this.mContext, this.mMute, new HorizonContentProviderHelper.MuteStateCallback() {
            /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MuteStateCallback
            public void onSuccess(int i) {
            }
        });
    }

    public void bindUser(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUserForRefresh = socialUserAdapterItem;
        hideOverflowMenu();
        setCurrentAsyncAction(null);
        SocialUser socialUser = socialUserAdapterItem.mUser;
        initializeProfilePicture(socialUser);
        initializeSubtitle(socialUser, socialUserAdapterItem.getGroupLaunchResponse());
        initializeMenuButton(socialUser, socialUserAdapterItem.getPartyMembership());
        initializeChatButton(socialUser);
        initializeActionButton(socialUser, socialUserAdapterItem.getPartyMembership());
        initializeName(socialUser);
        AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = this.mBinding;
        boolean z = false;
        if (socialUser.mUserType == SocialUser.UserRowType.INVITED) {
            z = true;
        }
        anytimeTabletSocialListItemV2Binding.setIsInvitedUser(z);
        if (socialUser.mUserType == SocialUser.UserRowType.PARTY_CONTROLS) {
            initializePartyVOIPControls();
        }
    }

    public void hideOverflowMenu() {
        this.mSocialViewModel.removeMenu();
    }

    public /* synthetic */ void lambda$initializeActionButton$6$UserViewHolder(View view) {
        if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON);
            new CreatePartyWith(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass5 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }
            }, SocialUserAction.Source.USER_ROW_MENU);
        }
    }

    public /* synthetic */ void lambda$initializeActionButton$7$UserViewHolder(View view) {
        if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON);
            new CancelPartyInvite(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass6 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }
            }, SocialUserAction.Source.USER_ROW_MENU);
        }
    }

    public /* synthetic */ void lambda$initializeActionButton$8$UserViewHolder(View view) {
        new AddToParty(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    public /* synthetic */ void lambda$initializeActionButton$9$UserViewHolder(View view) {
        new Goto(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    public /* synthetic */ void lambda$initializeChatButton$4$UserViewHolder(View view) {
        SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
        SocialPanelApp socialPanelApp = this.mPanelApp;
        new Chat(socialUserAdapterItem, socialPanelApp).execute(socialPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    public /* synthetic */ void lambda$initializeMenuButton$2$UserViewHolder(View view) {
        SocialPopupMenu socialPopupMenu = new SocialPopupMenu(this.mContext, this.mPanelApp, getPossibleActions(), view, SocialUserAction.Source.USER_ROW_MENU);
        this.mPopUpmenu = socialPopupMenu;
        this.mSocialViewModel.setOpenMenu(socialPopupMenu);
    }

    public /* synthetic */ void lambda$initializeMenuButton$3$UserViewHolder(View view) {
        if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            new CancelPartyInvite(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass4 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserViewHolder.this.setCurrentAsyncAction(null);
                }
            }, SocialUserAction.Source.USER_ROW_MENU);
        }
    }

    public /* synthetic */ void lambda$initializeProfilePicture$1$UserViewHolder(SocialUser socialUser, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_PROFILE_VIEW);
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PROFILE, AnonymousClass006.A07("/vr/", socialUser.mID));
    }

    @Override // com.oculus.panelapp.social.SocialUserRecyclerViewHolder
    public void setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource asyncSource) {
        ShellButton shellButton;
        if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON) {
            this.mBinding.loadingSpinner.setVisibility(0);
            this.mBinding.actionButton.setText("");
        } else {
            if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON) {
                this.mBinding.secondaryLoadingSpinner.setVisibility(0);
                shellButton = this.mBinding.menuButton;
            } else if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON) {
                this.mBinding.startPartyLoadingSpinner.setVisibility(0);
                shellButton = this.mBinding.callButton;
            } else if (asyncSource == null) {
                this.mBinding.startPartyLoadingSpinner.setVisibility(8);
                this.mBinding.loadingSpinner.setVisibility(8);
                this.mBinding.secondaryLoadingSpinner.setVisibility(8);
                this.mBinding.menuButton.setVisibility(0);
                SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
                initializeActionButton(socialUserAdapterItem.mUser, socialUserAdapterItem.getPartyMembership());
                SocialUserAdapterItem socialUserAdapterItem2 = this.mUserForRefresh;
                initializeMenuButton(socialUserAdapterItem2.mUser, socialUserAdapterItem2.getPartyMembership());
            }
            shellButton.setVisibility(4);
        }
        this.mAsyncActionSource = asyncSource;
    }

    private List<SocialUserAction> getPossibleActions() {
        CreatePartyWith createPartyWith;
        showCallButton();
        AddFriend addFriend = new AddFriend(this.mUserForRefresh);
        Chat chat = null;
        if (!showCallButton()) {
            createPartyWith = new CreatePartyWith(this.mUserForRefresh);
        } else {
            createPartyWith = null;
        }
        SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
        if (socialUserAdapterItem.mUser.mUserType != SocialUser.UserRowType.FRIEND) {
            chat = new Chat(socialUserAdapterItem, this.mPanelApp);
        }
        return (List) Arrays.asList(addFriend, createPartyWith, chat, new AddToParty(socialUserAdapterItem), new UnFriend(socialUserAdapterItem), new CancelPartyInvite(socialUserAdapterItem), new Goto(socialUserAdapterItem), new Block(socialUserAdapterItem), new Report(socialUserAdapterItem), new KickFromParty(socialUserAdapterItem, this.mPanelApp)).stream().filter($$Lambda$UserViewHolder$kh_pRDU1i1xx7knPkd4Sc1vp9I2.INSTANCE).collect(Collectors.toList());
    }

    public /* synthetic */ void lambda$initializePartyVOIPControls$0$UserViewHolder(View view) {
        toggleMuted();
    }
}
