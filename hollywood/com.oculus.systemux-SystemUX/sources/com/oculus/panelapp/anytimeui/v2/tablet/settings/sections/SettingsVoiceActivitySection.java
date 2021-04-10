package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.graphql.model.GraphQLPageInfo;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNullState;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsVoiceActivityLogActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.AssistantActivityLogItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import okhttp3.OkHttpClient;

public final class SettingsVoiceActivitySection extends SettingsSection {
    private static final String DELETE_ALL_VOICE_ACTIVITY_DIALOG_ID = "delete_all_voice_activity_dialog";
    private static final String TAG = LoggingUtil.tag(SettingsVoiceActivitySection.class);
    private List<AssistantActivityLogItem> mActivityHistory = new ArrayList();
    private SimpleDateFormat mActivityLogDateFormat;
    private SimpleDateFormat mActivityLogMonthHeaderFormat;
    private final Context mContext;
    private String mCurrentActivityLogMonth;
    private GraphQLPageInfo mCurrentPageInfo;
    private SettingsVoiceActivityLogActionType mCurrentVoiceActivityAction;
    private long mEntryTime;
    private MediaPlayer mMediaPlayer;
    private OkHttpClient mOkHttpClient;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private boolean mPendingQuery;
    private final Runnable mRefreshView;
    private final SettingsLogger mSettingsLogger;
    private final SettingsManager mSettingsManager = new SettingsManager();

    public SettingsVoiceActivitySection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.settings_voice_activity_section_title), TabletDeepLinkingUriUtil.SETTINGS_ASSISTANT_URI);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        this.mCurrentPageInfo = new GraphQLPageInfo.Builder().build();
        this.mSettingsLogger = anytimeUIAndroidPanelAppV2.acquireAndroidSettingsViewModel().getSettingsLogger();
        initializeOkHttpClient();
        initializeDateFormatter();
        this.mPendingQuery = true;
        VoiceActivityGraphQLUtil.queryVoiceActivity(context, this.mOkHttpClient, anytimeUIAndroidPanelAppV2.getAccessToken(), this.mCurrentPageInfo, new VoiceActivityGraphQLUtil.OnVoiceActivityResult() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$a1GHKJk4xMTcUqYhF5OgqPc_Jo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.OnVoiceActivityResult
            public final void onResult(List list, GraphQLPageInfo graphQLPageInfo) {
                SettingsVoiceActivitySection.m5lambda$a1GHKJk4xMTcUqYhF5OgqPc_Jo(SettingsVoiceActivitySection.this, list, graphQLPageInfo);
            }
        }, this.mSettingsLogger);
        initializeOnScrollToBottom();
        initializeMediaLoader();
    }

    private void initializeMediaLoader() {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).setUsage(1).build());
    }

    /* access modifiers changed from: private */
    public void initializeActivityLogItems(List<AssistantActivityLogItem> list, GraphQLPageInfo graphQLPageInfo) {
        UiThreadExecutor.getInstance().execute(new Runnable(graphQLPageInfo, list) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$gAajKPxamzfbpWCNDfre6Ypp1Rg */
            private final /* synthetic */ GraphQLPageInfo f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SettingsVoiceActivitySection.this.lambda$initializeActivityLogItems$471$SettingsVoiceActivitySection(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$initializeActivityLogItems$471$SettingsVoiceActivitySection(GraphQLPageInfo graphQLPageInfo, List list) {
        this.mCurrentPageInfo = graphQLPageInfo;
        if (list.isEmpty()) {
            addEmptyState();
        } else {
            initializeDeleteAllButton();
            addActivityLogItems(list, graphQLPageInfo);
        }
        this.mPendingQuery = false;
    }

    private void initializeOnScrollToBottom() {
        super.setOnScrollToBottom(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$4g0Qydthhwb_cBjN2OSxovERNc */

            public final void run() {
                SettingsVoiceActivitySection.this.lambda$initializeOnScrollToBottom$474$SettingsVoiceActivitySection();
            }
        });
    }

    public /* synthetic */ void lambda$initializeOnScrollToBottom$474$SettingsVoiceActivitySection() {
        if (this.mCurrentPageInfo.hasPreviousPage() && !this.mPendingQuery) {
            this.mPendingQuery = true;
            VoiceActivityGraphQLUtil.queryVoiceActivity(this.mContext, this.mOkHttpClient, this.mPanelApp.getAccessToken(), this.mCurrentPageInfo, new VoiceActivityGraphQLUtil.OnVoiceActivityResult() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$ak__DzdWvVOWAOUQnmRFq0srdk4 */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.OnVoiceActivityResult
                public final void onResult(List list, GraphQLPageInfo graphQLPageInfo) {
                    SettingsVoiceActivitySection.this.lambda$null$473$SettingsVoiceActivitySection(list, graphQLPageInfo);
                }
            }, this.mSettingsLogger);
        }
    }

    public /* synthetic */ void lambda$null$473$SettingsVoiceActivitySection(List list, GraphQLPageInfo graphQLPageInfo) {
        UiThreadExecutor.getInstance().execute(new Runnable(list, graphQLPageInfo) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$JR590P04mK3R4O5UN96yLsvNi8c */
            private final /* synthetic */ List f$1;
            private final /* synthetic */ GraphQLPageInfo f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SettingsVoiceActivitySection.this.lambda$null$472$SettingsVoiceActivitySection(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$null$472$SettingsVoiceActivitySection(List list, GraphQLPageInfo graphQLPageInfo) {
        addActivityLogItems(list, graphQLPageInfo);
        this.mPendingQuery = false;
    }

    private void initializeOkHttpClient() {
        this.mOkHttpClient = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS).certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    }

    private void initializeDateFormatter() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        this.mActivityLogDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, DateFormat.is24HourFormat(this.mContext) ? "M/d/yyyy, Hms" : "M/d/yyyy, hms"), locale);
        this.mActivityLogMonthHeaderFormat = new SimpleDateFormat("MMM yyyy", locale);
    }

    private void initializeDeleteAllButton() {
        setToolbarButton(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_voice_activity_delete_all_button).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$h0n9ze34JwZnTUKRpl8DJy0MGmk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsVoiceActivitySection.this.lambda$initializeDeleteAllButton$477$SettingsVoiceActivitySection();
            }
        }));
    }

    public /* synthetic */ void lambda$initializeDeleteAllButton$477$SettingsVoiceActivitySection() {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(DELETE_ALL_VOICE_ACTIVITY_DIALOG_ID, resources.getString(R.string.settings_voice_activity_delete_all_dialog_title), resources.getString(R.string.settings_voice_activity_delete_all_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_voice_activity_delete_all_dialog_cancel)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_voice_activity_delete_all_dialog_confirm)));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$4V5Nf4ytODuFimnmL3qx5VHRMM0 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsVoiceActivitySection.this.lambda$null$476$SettingsVoiceActivitySection(dialogResult);
            }
        });
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_ATTEMPTED);
        this.mPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public /* synthetic */ boolean lambda$null$476$SettingsVoiceActivitySection(DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_CONFIRMED);
            VoiceActivityGraphQLUtil.deleteAllVoiceActivity(this.mContext, this.mOkHttpClient, this.mPanelApp.getAccessToken(), new VoiceActivityGraphQLUtil.OnDeleteAllResult() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$ahuc5thzSVGqKISUC0pLz5tRJH0 */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.OnDeleteAllResult
                public final void onDeleted() {
                    SettingsVoiceActivitySection.this.lambda$null$475$SettingsVoiceActivitySection();
                }
            }, this.mSettingsLogger);
            return true;
        }
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.DELETE_ALL_CANCELLED);
        return true;
    }

    public /* synthetic */ void lambda$null$475$SettingsVoiceActivitySection() {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$xYk3SD8DjZm5_x6QbJlCUWfSzAo */

            public final void run() {
                SettingsVoiceActivitySection.lambda$xYk3SD8DjZm5_x6QbJlCUWfSzAo(SettingsVoiceActivitySection.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onDeletedAllVoiceActivity() {
        clearSettingsItems();
        this.mCurrentPageInfo = new GraphQLPageInfo.Builder().build();
        this.mCurrentActivityLogMonth = null;
        addEmptyState();
        this.mRefreshView.run();
        super.setToolbarButton(null);
        if (this.mCurrentVoiceActivityAction != null) {
            this.mMediaPlayer.reset();
            this.mCurrentVoiceActivityAction = null;
        }
    }

    private void addEmptyState() {
        SettingsNullState.Builder withTitle = new SettingsNullState.Builder(this.mContext, this.mPanelApp).withTitle(R.string.settings_voice_acitvity_empty_title);
        if (this.mSettingsManager.getBoolean(SettingsManager.VOICE_INTERACTION_STORAGE_ENABLED, false)) {
            withTitle.withSubtitle(R.string.settings_voice_acitvity_empty_subtitle);
        }
        addSettingsItem(withTitle);
        this.mRefreshView.run();
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.EMPTY_VIEW);
    }

    private void addActivityLogItems(List<AssistantActivityLogItem> list, GraphQLPageInfo graphQLPageInfo) {
        this.mCurrentPageInfo = graphQLPageInfo;
        this.mActivityHistory.addAll(list);
        addActivityLogsToSettings(list);
    }

    private void addActivityLogsToSettings(List<AssistantActivityLogItem> list) {
        List<BaseSettingsItem.Builder> arrayList = new ArrayList<>();
        for (AssistantActivityLogItem assistantActivityLogItem : list) {
            BaseSettingsItem.Builder monthGroupingHeader = getMonthGroupingHeader(assistantActivityLogItem);
            if (monthGroupingHeader != null) {
                arrayList.add(monthGroupingHeader);
            }
            String utterance = assistantActivityLogItem.getUtterance();
            SettingsItem.Builder builder = new SettingsItem.Builder(this.mContext, this.mPanelApp);
            if (assistantActivityLogItem.isFakeWake()) {
                builder.withTitle(R.string.settings_voice_activity_unintended_audio);
            } else if (TextUtils.isEmpty(utterance)) {
                builder.withTitle(R.string.settings_voice_activity_text_unavailable);
            } else {
                builder.withTitle(String.format("\"%s\"", utterance));
            }
            String logID = assistantActivityLogItem.getLogID();
            builder.withSubtitle(this.mActivityLogDateFormat.format(Double.valueOf(Double.parseDouble(assistantActivityLogItem.getTimestamp())))).withSettingsActionType(new SettingsVoiceActivityLogActionType.Builder(this.mContext).withLogId(logID).withPlayButtonOnClick(new SettingsVoiceActivityLogActionType.OnButtonClickHandler(assistantActivityLogItem) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$FAHPH6rgyICcnl1APgo4JulwxQ4 */
                private final /* synthetic */ AssistantActivityLogItem f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsVoiceActivityLogActionType.OnButtonClickHandler
                public final void onClick(SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType) {
                    SettingsVoiceActivitySection.this.lambda$addActivityLogsToSettings$478$SettingsVoiceActivitySection(this.f$1, settingsVoiceActivityLogActionType);
                }
            }).withDeleteButtonOnClick(new SettingsVoiceActivityLogActionType.OnButtonClickHandler(logID) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$483z8tcQ_ZbS6AASvrWLiBESEYU */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsVoiceActivityLogActionType.OnButtonClickHandler
                public final void onClick(SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType) {
                    SettingsVoiceActivitySection.this.lambda$addActivityLogsToSettings$479$SettingsVoiceActivitySection(this.f$1, settingsVoiceActivityLogActionType);
                }
            }));
            arrayList.add(builder);
        }
        addSettingsItems(arrayList);
        this.mRefreshView.run();
    }

    public /* synthetic */ void lambda$addActivityLogsToSettings$479$SettingsVoiceActivitySection(String str, SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType) {
        VoiceActivityGraphQLUtil.deleteVoiceActivityById(this.mContext, this.mOkHttpClient, this.mPanelApp.getAccessToken(), str, new VoiceActivityGraphQLUtil.OnDeleteByIdResult() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$RFlE_soPOuIX60T_9LtQkdUYupc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant.VoiceActivityGraphQLUtil.OnDeleteByIdResult
            public final void onDeleted(String str) {
                SettingsVoiceActivitySection.lambda$RFlE_soPOuIX60T_9LtQkdUYupc(SettingsVoiceActivitySection.this, str);
            }
        }, this.mSettingsLogger);
    }

    private SettingsDescriptiveText.Builder getMonthGroupingHeader(AssistantActivityLogItem assistantActivityLogItem) {
        String format = this.mActivityLogMonthHeaderFormat.format(Double.valueOf(Double.parseDouble(assistantActivityLogItem.getTimestamp())));
        if (format.equals(this.mCurrentActivityLogMonth)) {
            return null;
        }
        this.mCurrentActivityLogMonth = format;
        return new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(this.mCurrentActivityLogMonth);
    }

    /* access modifiers changed from: private */
    /* renamed from: onPlayButtonClick */
    public void lambda$addActivityLogsToSettings$478$SettingsVoiceActivitySection(SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType, AssistantActivityLogItem assistantActivityLogItem) {
        try {
            if (settingsVoiceActivityLogActionType != this.mCurrentVoiceActivityAction) {
                if (this.mCurrentVoiceActivityAction != null) {
                    this.mCurrentVoiceActivityAction.setPlaying(false);
                }
                settingsVoiceActivityLogActionType.setPlaying(true);
                this.mCurrentVoiceActivityAction = settingsVoiceActivityLogActionType;
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$3vMbP4bcFjNOl5oLlS2rEJpfz7Y */

                    public final void onCompletion(MediaPlayer mediaPlayer) {
                        SettingsVoiceActivitySection.this.lambda$onPlayButtonClick$481$SettingsVoiceActivitySection(mediaPlayer);
                    }
                });
                ThreadExecutor.getInstance().execute(new Callable(assistantActivityLogItem) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$xYVv5BTda2kF2FFk6tF6TXPTUss */
                    private final /* synthetic */ AssistantActivityLogItem f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return SettingsVoiceActivitySection.this.lambda$onPlayButtonClick$482$SettingsVoiceActivitySection(this.f$1);
                    }
                });
            } else if (settingsVoiceActivityLogActionType.isPlaying()) {
                settingsVoiceActivityLogActionType.setPlaying(false);
                this.mMediaPlayer.pause();
            } else {
                settingsVoiceActivityLogActionType.setPlaying(true);
                this.mMediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            Log.e(TAG, "Error illegal state for media player", e);
            this.mMediaPlayer.reset();
            SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType2 = this.mCurrentVoiceActivityAction;
            if (settingsVoiceActivityLogActionType2 != null) {
                settingsVoiceActivityLogActionType2.setPlaying(false);
                settingsVoiceActivityLogActionType.setPlaying(false);
            }
        }
    }

    public /* synthetic */ void lambda$onPlayButtonClick$481$SettingsVoiceActivitySection(MediaPlayer mediaPlayer) {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$9rUuY65l3SN6tdg8kPTQHsPSDQU */

            public final void run() {
                SettingsVoiceActivitySection.this.lambda$null$480$SettingsVoiceActivitySection();
            }
        });
    }

    public /* synthetic */ void lambda$null$480$SettingsVoiceActivitySection() {
        this.mCurrentVoiceActivityAction.setPlaying(false);
        this.mCurrentVoiceActivityAction = null;
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.AUDIO_END);
    }

    public /* synthetic */ Object lambda$onPlayButtonClick$482$SettingsVoiceActivitySection(AssistantActivityLogItem assistantActivityLogItem) throws Exception {
        this.mMediaPlayer.setDataSource(assistantActivityLogItem.getUtteranceUrl());
        this.mMediaPlayer.prepare();
        this.mMediaPlayer.start();
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.AUDIO_PLAYED);
        return null;
    }

    /* access modifiers changed from: private */
    public void removeLog(String str) {
        UiThreadExecutor.getInstance().execute(new Runnable(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$6tpxdkS0od8qZvJSX5M4c4JO9H8 */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SettingsVoiceActivitySection.this.lambda$removeLog$484$SettingsVoiceActivitySection(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$removeLog$484$SettingsVoiceActivitySection(String str) {
        if (this.mActivityHistory.removeIf(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsVoiceActivitySection$e3bfi0XJISC9gU_GPhMK2131ktk */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SettingsVoiceActivitySection.lambda$null$483(this.f$0, (AssistantActivityLogItem) obj);
            }
        })) {
            SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType = this.mCurrentVoiceActivityAction;
            if (settingsVoiceActivityLogActionType != null && settingsVoiceActivityLogActionType.getLogID().equals(str)) {
                this.mMediaPlayer.reset();
                this.mCurrentVoiceActivityAction = null;
            }
            clearSettingsItems();
            this.mCurrentActivityLogMonth = null;
            if (!this.mActivityHistory.isEmpty() || this.mCurrentPageInfo.hasPreviousPage()) {
                addActivityLogsToSettings(this.mActivityHistory);
            } else {
                onDeletedAllVoiceActivity();
            }
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void onShow() {
        this.mEntryTime = System.currentTimeMillis();
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.SETTINGS_VOICE_ACTIVITY_SECTION_ENTRY);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void onHide() {
        if (this.mEntryTime > 0) {
            this.mSettingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.SETTINGS_VOICE_ACTIVITY_SECTION_EXIT, System.currentTimeMillis() - this.mEntryTime);
        }
        this.mEntryTime = -1;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
