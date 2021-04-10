package com.oculus.panelapp.social;

import android.content.Context;
import android.util.Log;
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
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class UserViewHolder extends SocialUserRecyclerViewHolder {
    private static final String TAG = LoggingUtil.tag(UserViewHolder.class);
    private AnytimeTabletSocialListItemV2Binding mBinding;
    private Context mContext;
    private int mMute;
    private SocialPanelApp mPanelApp;
    @Nullable
    private SocialPopupMenu mPopUpmenu;
    private ProfilePictureHelper mProfilePictureHelper = new ProfilePictureHelper(this.mContext);
    private SocialViewModel mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
    @Nullable
    private SocialUserAdapterItem mUserForRefresh;

    private boolean shouldShowGroupLaunchResponse() {
        return false;
    }

    public UserViewHolder(AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialListItemV2Binding.socialListItem);
        this.mBinding = anytimeTabletSocialListItemV2Binding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
    }

    public void hideOverflowMenu() {
        this.mSocialViewModel.removeMenu();
    }

    public void bindUser(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUserForRefresh = socialUserAdapterItem;
        hideOverflowMenu();
        setCurrentAsyncAction(null);
        SocialUser user = socialUserAdapterItem.getUser();
        initializeProfilePicture(user);
        initializeSubtitle(user, socialUserAdapterItem.getGroupLaunchResponse());
        initializeMenuButton(user, socialUserAdapterItem.getPartyMembership());
        initializeChatButton(user);
        initializeActionButton(user, socialUserAdapterItem.getPartyMembership());
        initializeName(user);
        this.mBinding.setIsInvitedUser(user.getUserType() == SocialUser.UserRowType.INVITED);
        if (user.getUserType() == SocialUser.UserRowType.PARTY_CONTROLS) {
            initializePartyVOIPControls();
        }
    }

    private void initializePartyVOIPControls() {
        this.mMute = getPartyLocalMute();
        AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = this.mBinding;
        boolean z = true;
        if (this.mMute != 1) {
            z = false;
        }
        anytimeTabletSocialListItemV2Binding.setMuted(z);
        this.mBinding.partyMute.clearOnClickListener();
        this.mBinding.partyMute.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$9zukJIVp_Lmh7LGAahIkwhTWOnc */

            public final void onClick(View view) {
                UserViewHolder.this.lambda$initializePartyVOIPControls$48$UserViewHolder(view);
            }
        });
        final SeekBar seekBar = this.mBinding.partyVolumeSlider.seekbar;
        new Timer().schedule(new TimerTask() {
            /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass1 */

            public void run() {
                UiThreadExecutor.getInstance().execute(new Runnable(seekBar) {
                    /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$1$LHOJdF38eiA7GR1G63qNq0u_NI */
                    private final /* synthetic */ SeekBar f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        UserViewHolder.AnonymousClass1.this.lambda$run$49$UserViewHolder$1(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$run$49$UserViewHolder$1(SeekBar seekBar) {
                UserViewHolder userViewHolder = UserViewHolder.this;
                userViewHolder.mMute = userViewHolder.getPartyLocalMute();
                AnytimeTabletSocialListItemV2Binding anytimeTabletSocialListItemV2Binding = UserViewHolder.this.mBinding;
                boolean z = true;
                if (UserViewHolder.this.mMute != 1) {
                    z = false;
                }
                anytimeTabletSocialListItemV2Binding.setMuted(z);
                seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(UserViewHolder.this.mContext) * 100.0f));
            }
        }, 2000);
        String str = TAG;
        Log.i(str, "Initializes to be " + (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
        seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.social.UserViewHolder.AnonymousClass2 */

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                float f = ((float) i) / 100.0f;
                String str = UserViewHolder.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("party volume set to: ");
                sb.append(f);
                sb.append(z ? " from user" : "");
                Log.i(str, sb.toString());
                if (!z) {
                    seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(UserViewHolder.this.mContext) * 100.0f));
                } else {
                    HorizonContentProviderHelper.setPartyVolume(UserViewHolder.this.mContext, f);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initializePartyVOIPControls$48$UserViewHolder(View view) {
        toggleMuted();
    }

    private void toggleMuted() {
        boolean z = true;
        this.mMute = this.mMute == 1 ? 2 : 1;
        if (this.mMute == 1) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_MUTE_SELF, this.mPanelApp);
        } else {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UNMUTE_SELF, this.mPanelApp);
        }
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getPartyLocalMute() {
        return HorizonContentProviderHelper.getPartyLocalMute(this.mContext);
    }

    private void initializeProfilePicture(SocialUser socialUser) {
        this.mProfilePictureHelper.setImageViewFromUrl(this.mBinding.icon, socialUser.getProfilePhotoURL(), socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
        this.mBinding.icon.setOnClickListener(new View.OnClickListener(socialUser) {
            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$B93_B0o5YnZ9FzJXJT9FCoYsc9M */
            private final /* synthetic */ SocialUser f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserViewHolder.this.lambda$initializeProfilePicture$50$UserViewHolder(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeProfilePicture$50$UserViewHolder(SocialUser socialUser, View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_ROW_PROFILE_VIEW, this.mPanelApp);
        SocialPanelApp socialPanelApp = this.mPanelApp;
        SystemUXRoute systemUXRoute = SystemUXRoute.AUI_PROFILE;
        socialPanelApp.actionNavigate(systemUXRoute, "/vr/" + socialUser.getID());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.social.UserViewHolder$7  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType = new int[SocialUser.UserRowType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.horizoncontent.social.SocialUser$UserRowType[] r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType = r0
                int[] r0 = com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.SocialUser$UserRowType r1 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_CONTROLS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.SocialUser$UserRowType r1 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_MEMBER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.horizoncontent.social.SocialUser$UserRowType r1 = com.oculus.horizoncontent.social.SocialUser.UserRowType.FRIEND     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.horizoncontent.social.SocialUser$UserRowType r1 = com.oculus.horizoncontent.social.SocialUser.UserRowType.INVITED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.UserViewHolder.AnonymousClass7.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeSubtitle(com.oculus.horizoncontent.social.SocialUser r6, com.oculus.horizoncontent.social.SocialGroupLaunchResponse r7) {
        /*
        // Method dump skipped, instructions count: 256
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.UserViewHolder.initializeSubtitle(com.oculus.horizoncontent.social.SocialUser, com.oculus.horizoncontent.social.SocialGroupLaunchResponse):void");
    }

    private String getGroupLaunchResponseString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        if (socialGroupLaunchResponse == SocialGroupLaunchResponse.ACCEPT) {
            return this.mContext.getResources().getString(R.string.anytime_tablet_social_party_group_launch_response_accept_subtitle);
        }
        return this.mContext.getResources().getString(R.string.anytime_tablet_social_party_group_launch_response_pending_subtitle);
    }

    private void initializeMenuButton(SocialUser socialUser, SocialParty.PartyMembership partyMembership) {
        this.mBinding.menuButton.clearOnClickListener();
        this.mBinding.partyMute.setVisibility(8);
        int i = AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType[socialUser.getUserType().ordinal()];
        if (i == 1) {
            this.mBinding.partyMute.setVisibility(0);
            this.mBinding.menuButton.setVisibility(8);
        } else if (i == 2 || i == 3) {
            this.mBinding.menuButton.setBackground(this.mContext.getDrawable(R.drawable.ic_more_vertical));
            this.mBinding.menuButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$so5KuG_xyQTXAxwT0HQLmJzppDc */

                public final void onClick(View view) {
                    UserViewHolder.this.lambda$initializeMenuButton$51$UserViewHolder(view);
                }
            });
        } else if (i == 4) {
            if (this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
                this.mBinding.menuButton.setVisibility(0);
                this.mBinding.menuButton.setBackground(this.mContext.getDrawable(R.drawable.ic_close));
                this.mBinding.menuButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$D_gDsjY6Cl5VRNJVOJAkGZWzh4 */

                    public final void onClick(View view) {
                        UserViewHolder.this.lambda$initializeMenuButton$52$UserViewHolder(view);
                    }
                });
                return;
            }
            this.mBinding.menuButton.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$initializeMenuButton$51$UserViewHolder(View view) {
        this.mPopUpmenu = new SocialPopupMenu(this.mContext, this.mPanelApp, getPossibleActions(), view, SocialUserAction.Source.USER_ROW_MENU);
        this.mSocialViewModel.setOpenMenu(this.mPopUpmenu);
    }

    public /* synthetic */ void lambda$initializeMenuButton$52$UserViewHolder(View view) {
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

    private void initializeName(SocialUser socialUser) {
        this.mBinding.setNameText(socialUser.getAlias());
    }

    private void initializeChatButton(SocialUser socialUser) {
        if (socialUser.getUserType() == SocialUser.UserRowType.FRIEND) {
            this.mBinding.chatButton.setVisibility(0);
            this.mBinding.chatButton.clearOnClickListener();
            if (!socialUser.getCanViewerMessage()) {
                this.mBinding.chatButton.setAlpha(0.3f);
            } else {
                this.mBinding.chatButton.setAlpha(1.0f);
            }
            this.mBinding.chatButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$NGX2teLslaQsmCpgEQshZiGThVo */

                public final void onClick(View view) {
                    UserViewHolder.this.lambda$initializeChatButton$53$UserViewHolder(view);
                }
            });
            return;
        }
        this.mBinding.chatButton.setVisibility(8);
    }

    public /* synthetic */ void lambda$initializeChatButton$53$UserViewHolder(View view) {
        new Chat(this.mUserForRefresh, this.mPanelApp).execute(this.mPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    private List<SocialUserAction> getPossibleActions() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show call button set to: ");
        sb.append(showCallButton() ? SocialBundleConstants.FB_UPSELL_MUST_INTERACT : "false");
        Log.d("SocialView", sb.toString());
        SocialUserAction[] socialUserActionArr = new SocialUserAction[10];
        socialUserActionArr[0] = new AddFriend(this.mUserForRefresh);
        Chat chat = null;
        socialUserActionArr[1] = !showCallButton() ? new CreatePartyWith(this.mUserForRefresh) : null;
        if (this.mUserForRefresh.getUser().getUserType() != SocialUser.UserRowType.FRIEND) {
            chat = new Chat(this.mUserForRefresh, this.mPanelApp);
        }
        socialUserActionArr[2] = chat;
        socialUserActionArr[3] = new AddToParty(this.mUserForRefresh);
        socialUserActionArr[4] = new UnFriend(this.mUserForRefresh);
        socialUserActionArr[5] = new CancelPartyInvite(this.mUserForRefresh);
        socialUserActionArr[6] = new Goto(this.mUserForRefresh);
        socialUserActionArr[7] = new Block(this.mUserForRefresh);
        socialUserActionArr[8] = new Report(this.mUserForRefresh);
        socialUserActionArr[9] = new KickFromParty(this.mUserForRefresh, this.mPanelApp);
        return (List) Arrays.asList(socialUserActionArr).stream().filter($$Lambda$UserViewHolder$ZOI3gvzlDB7QYYYDS45hls3ytyA.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getPossibleActions$54(SocialUserAction socialUserAction) {
        return socialUserAction != null && socialUserAction.isRelevant();
    }

    private boolean showCallButton() {
        return this.mUserForRefresh.getUser().getUserType() == SocialUser.UserRowType.FRIEND && this.mUserForRefresh.getPartyMembership() == null && this.mUserForRefresh.getUser().getIsActive(this.mContext.getResources());
    }

    private void initializeActionButton(SocialUser socialUser, SocialParty.PartyMembership partyMembership) {
        this.mBinding.actionButton.button.clearOnClickListener();
        this.mBinding.callButton.clearOnClickListener();
        this.mBinding.callButton.setVisibility(8);
        this.mBinding.partyVolumeSlider.seekbar.setVisibility(8);
        this.mBinding.partyVolumeSlider.icon.setVisibility(8);
        int i = AnonymousClass7.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType[socialUser.getUserType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (!socialUser.getIsActive(this.mContext.getResources())) {
                        this.mBinding.actionButton.button.setVisibility(8);
                        return;
                    } else if (showCallButton()) {
                        this.mBinding.actionButton.button.setVisibility(8);
                        this.mBinding.callButton.setVisibility(0);
                        this.mBinding.callButton.setOnClickListener(new View.OnClickListener() {
                            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$lPuceBLrmlCkwzH6rT6aNFIM0M */

                            public final void onClick(View view) {
                                UserViewHolder.this.lambda$initializeActionButton$55$UserViewHolder(view);
                            }
                        });
                        return;
                    } else if (partyMembership == SocialParty.PartyMembership.MEMBER) {
                        this.mBinding.actionButton.button.setVisibility(8);
                        return;
                    } else if (partyMembership != SocialParty.PartyMembership.INVITED) {
                        this.mBinding.actionButton.button.setVisibility(0);
                        this.mBinding.setActionButtonText(SocialUserActionType.ADD_TO_PARTY.getActionString(this.mContext.getResources()));
                        this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$8q6Ue153Ri0L0UVfOOL7zs6JM */

                            public final void onClick(View view) {
                                UserViewHolder.this.lambda$initializeActionButton$57$UserViewHolder(view);
                            }
                        });
                        return;
                    } else if (this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
                        this.mBinding.actionButton.button.setVisibility(0);
                        this.mBinding.setActionButtonText(SocialUserActionType.CANCEL_PARTY_INVITE.getActionString(this.mContext.getResources()));
                        this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                            /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$6WOxTVMna9WyaWjzwYaOgzj7RQ */

                            public final void onClick(View view) {
                                UserViewHolder.this.lambda$initializeActionButton$56$UserViewHolder(view);
                            }
                        });
                        return;
                    } else {
                        this.mBinding.actionButton.button.setVisibility(8);
                        return;
                    }
                }
            } else if (socialUser.getDeepLink() != null) {
                this.mBinding.actionButton.button.setVisibility(0);
                this.mBinding.setActionButtonText(SocialUserActionType.GOTO.getActionString(this.mContext.getResources()));
                this.mBinding.actionButton.button.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.social.$$Lambda$UserViewHolder$gcPbyhwdyQPL5tQLkT2w6J5wBV4 */

                    public final void onClick(View view) {
                        UserViewHolder.this.lambda$initializeActionButton$58$UserViewHolder(view);
                    }
                });
                return;
            }
            this.mBinding.actionButton.button.setVisibility(8);
            return;
        }
        this.mBinding.partyVolumeSlider.seekbar.setVisibility(0);
        this.mBinding.partyVolumeSlider.icon.setVisibility(0);
        this.mBinding.actionButton.button.setVisibility(8);
    }

    public /* synthetic */ void lambda$initializeActionButton$55$UserViewHolder(View view) {
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

    public /* synthetic */ void lambda$initializeActionButton$56$UserViewHolder(View view) {
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

    public /* synthetic */ void lambda$initializeActionButton$57$UserViewHolder(View view) {
        new AddToParty(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    public /* synthetic */ void lambda$initializeActionButton$58$UserViewHolder(View view) {
        new Goto(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, null, SocialUserAction.Source.USER_ROW_MENU);
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.SocialUserRecyclerViewHolder
    public void setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource asyncSource) {
        if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON) {
            this.mBinding.loadingSpinner.setVisibility(0);
            this.mBinding.actionButton.setText("");
        } else if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON) {
            this.mBinding.secondaryLoadingSpinner.setVisibility(0);
            this.mBinding.menuButton.setVisibility(4);
        } else if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON) {
            this.mBinding.startPartyLoadingSpinner.setVisibility(0);
            this.mBinding.callButton.setVisibility(4);
        } else if (asyncSource == null) {
            this.mBinding.startPartyLoadingSpinner.setVisibility(8);
            this.mBinding.loadingSpinner.setVisibility(8);
            this.mBinding.secondaryLoadingSpinner.setVisibility(8);
            this.mBinding.menuButton.setVisibility(0);
            initializeActionButton(this.mUserForRefresh.getUser(), this.mUserForRefresh.getPartyMembership());
            initializeMenuButton(this.mUserForRefresh.getUser(), this.mUserForRefresh.getPartyMembership());
        }
        this.mAsyncActionSource = asyncSource;
    }
}
