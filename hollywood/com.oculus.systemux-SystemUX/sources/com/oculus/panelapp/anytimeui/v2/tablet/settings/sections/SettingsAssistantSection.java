package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;

public class SettingsAssistantSection extends SettingsSection {
    private static final int DOUBLE_PRESS_ASSISTANT = 2;
    private static final String PRIVACY_URI = "https://support.oculus.com/2106270456509705";
    private static final String VOICE_ACTIVITY_URI = "https://secure.oculus.com/my/voice-activities";
    private final Context mContext;
    private long mEntryTime;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final SettingsLogger mSettingsLogger = new SettingsLogger(this.mContext);
    private final SettingsManager mSettingsManager = new SettingsManager();

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsAssistantSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getResources().getString(R.string.settings_assistant_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        if (anytimeUIAndroidPanelAppV2.isGKEnabled(Gatekeeper.ASSISTANT_NATIVE_VOICE_ACTIVITY)) {
            initializeAssistantContent();
        } else {
            initializeDeprecatedAssistantContent();
        }
    }

    private void initializeAssistantContent() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(R.string.settings_assistant_general_title).withGatekeepers(Gatekeeper.ASSISTANT_IN_APP_VOICE_COMMANDS, Gatekeeper.ASSISTANT_TTS_SELECTION));
        arrayList.add(getInAppVoiceCommandsItem());
        arrayList.add(getAssistantVoiceSelection());
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(R.string.settings_assistant_activation_title).withGatekeepers(Gatekeeper.ASSISTANT_DOUBLE_TAP_SETTING));
        arrayList.add(getDoublePressAssistantItem());
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(R.string.settings_assistant_privacy_title));
        arrayList.add(getVoiceActivitySectionItem());
        arrayList.add(getVoiceInteractionStorageItem());
        addSettingsItems(arrayList);
    }

    private void initializeDeprecatedAssistantContent() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getVoiceActivityWebLinkItem());
        arrayList.add(getAssistantVoiceSelection());
        arrayList.add(getVoiceInteractionStorageItem());
        arrayList.add(getDoublePressAssistantItem());
        arrayList.add(getInAppVoiceCommandsItem());
        addSettingsItems(arrayList);
    }

    private SettingsItem.Builder getVoiceActivityWebLinkItem() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("sys_utils_redesign_menu_device_voice_commands_activity").withTitle(R.string.settings_assistant_voice_activity_title).withSubtitle(R.string.settings_assistant_voice_activity_subtitle).withSubtitleUri(PRIVACY_URI).withGatekeepers(Gatekeeper.ASSISTANT_VIEW_COMMANDS_ON_OCULUS).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(false).withLink(this.mPanelApp, VOICE_ACTIVITY_URI));
    }

    private SettingsItem.Builder getAssistantVoiceSelection() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("assistant_voice_selection").withTitle(R.string.settings_assistant_edit_tts_title).withSubtitle(R.string.settings_assistant_edit_tts_subtitle).withGatekeepers(Gatekeeper.ASSISTANT_TTS_SELECTION).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_assistant_edit_tts_button).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAssistantSection$EZhFAQclVtWVH4S8yvU52f03WM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsAssistantSection.this.lambda$getAssistantVoiceSelection$321$SettingsAssistantSection();
            }
        }));
    }

    public /* synthetic */ void lambda$getAssistantVoiceSelection$321$SettingsAssistantSection() {
        AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
        anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.ASSISTANT.path() + "/client/voice_selection", "");
    }

    private SettingsItem.Builder getInAppVoiceCommandsItem() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("in_app_voice_commands").withTitle(R.string.settings_assistant_in_app_voice_title).withSubtitle(R.string.settings_assistant_in_app_voice_subtitle).withGatekeepers(Gatekeeper.ASSISTANT_IN_APP_VOICE_COMMANDS).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.IN_APP_VOICE_COMMANDS_ENABLED));
    }

    private SettingsItem.Builder getDoublePressAssistantItem() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("doublepress_assistant").withTitle(R.string.settings_assistant_controller_title).withSubtitle(R.string.settings_assistant_controller_subtitle).withGatekeepers(Gatekeeper.ASSISTANT_DOUBLE_TAP_SETTING).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAssistantSection$SZ4flqoxefiXnEu2CcQ58Lxb5XU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsAssistantSection.this.lambda$getDoublePressAssistantItem$322$SettingsAssistantSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAssistantSection$kY6RivlQwuA8dASGSPexFbU34w */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsAssistantSection.this.lambda$getDoublePressAssistantItem$323$SettingsAssistantSection(z, settingsToggleActionType);
            }
        }));
    }

    public /* synthetic */ boolean lambda$getDoublePressAssistantItem$322$SettingsAssistantSection() {
        return this.mSettingsManager.getInt(SettingsManager.OCULUS_BUTTON_DOUBLEPRESS_BEHAVIOR, 0) == 2;
    }

    public /* synthetic */ void lambda$getDoublePressAssistantItem$323$SettingsAssistantSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mSettingsManager.setInt(SettingsManager.OCULUS_BUTTON_DOUBLEPRESS_BEHAVIOR, z ? 2 : 0);
    }

    private SettingsItem.Builder getVoiceInteractionStorageItem() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("voice_interaction_storage").withTitle(R.string.settings_assistant_store_interactions_title).withSubtitle(R.string.settings_assistant_store_interactions_subtitle).withSubtitleUri(PRIVACY_URI).withSettingsActionType(new SettingsToggleActionType.Builder().withObserver(new SettingsToggleActionType.ToggleSetValueObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAssistantSection$c5LbOSFrJmVxLd0XSWD_8yFOJRA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueObserver
            public final void onValueChanged(boolean z) {
                SettingsAssistantSection.this.lambda$getVoiceInteractionStorageItem$324$SettingsAssistantSection(z);
            }
        }).withSettingsManagerKey(SettingsManager.VOICE_INTERACTION_STORAGE_ENABLED));
    }

    public /* synthetic */ void lambda$getVoiceInteractionStorageItem$324$SettingsAssistantSection(boolean z) {
        this.mSettingsLogger.logVoiceActivityEvent(z ? SettingsLogger.VoiceActivityEvent.SubEvent.TOGGLE_VOICE_STORAGE_ENABLE : SettingsLogger.VoiceActivityEvent.SubEvent.TOGGLE_VOICE_STORAGE_DISABLE);
    }

    private SettingsItem.Builder getVoiceActivitySectionItem() {
        return new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("voice_commands_activity").withTitle(R.string.settings_voice_activity_section_title).withSubtitle(R.string.settings_voice_activity_section_subtitle).withSubtitleUri(PRIVACY_URI).withGatekeepers(Gatekeeper.ASSISTANT_VIEW_COMMANDS_ON_OCULUS).withSettingsActionType(new SettingsNavigationActionType.Builder().isInternal(true).withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_VOICE_ACTIVITY));
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void onShow() {
        this.mEntryTime = System.currentTimeMillis();
        this.mSettingsLogger.logVoiceActivityEvent(SettingsLogger.VoiceActivityEvent.SubEvent.SETTINGS_ASSISTANT_SECTION_ENTRY);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void onHide() {
        if (this.mEntryTime > 0) {
            this.mSettingsLogger.logVoiceActivityEventWithLatency(SettingsLogger.VoiceActivityEvent.SubEvent.SETTINGS_ASSISTANT_SECTION_EXIT, System.currentTimeMillis() - this.mEntryTime);
        }
        this.mEntryTime = -1;
    }
}
