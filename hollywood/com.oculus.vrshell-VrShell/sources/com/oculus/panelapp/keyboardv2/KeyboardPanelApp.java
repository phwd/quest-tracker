package com.oculus.panelapp.keyboardv2;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.EnvironmentCompat;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.keyboardv2.IKeyboardInputMethodInterface;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardInputMethodService;
import com.oculus.panelapp.keyboardv2.KeyboardView;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardAssistant;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardTranscription;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardTypeahead;
import com.oculus.panelapp.keyboardv2.japanese.KeyboardActionListenerJP;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.panels.KeyboardHandler;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.automation.AutomationHelpers;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class KeyboardPanelApp extends AndroidPanelApp {
    private static final String POPUP_LAYER = "popup";
    private static String TAG = LoggingUtil.tag(KeyboardPanelApp.class);
    private static final HashSet<Integer> VALID_TYPEAHEAD_LAYOUTS = new HashSet<>(Arrays.asList(Integer.valueOf(R.xml.en_us_text_default_32), Integer.valueOf(R.xml.en_us_text_default_transcription_32), Integer.valueOf(R.xml.en_us_text_default_48), Integer.valueOf(R.xml.en_us_text_default_transcription_48)));
    private final String BACKSPACE_KEY_SOUND_FILE_PATH;
    private final String HOVER_SOUND_FILE_PATH;
    private final String LAYOUT_KEY_SOUND_FILE_PATH;
    private final String PRESS_SOUND_FILE_PATH;
    private final String TRANSCRIPTION_KEY_SOUND_FILE_PATH;
    private boolean mAllowEmoji;
    private boolean mAllowImeComposition;
    private ActionType mCurrentActionType;
    private InputType mCurrentInputType;
    private KeyboardSize mCurrentKeyboardSize;
    private KeyboardLocale mCurrentLanguage;
    private StringBuilder mCurrentTextBuilder;
    private KeyboardActionListenerDefault mDefaultKeyboardActionListener;
    private KeyboardLocale mDefaultLanguage;
    private DictationModeAutoTriggerState mEnableDictationModeAutoTrigger;
    private boolean mIsIMEConnected;
    private KeyboardAssistant mKeyboardAssistant;
    private IKeyboardInputMethodInterface mKeyboardIMECallback;
    private KeyboardTranscription mKeyboardTranscription;
    private boolean mKeyboardTranscriptionDisabled;
    private KeyboardTypeahead mKeyboardTypeahead;
    private Keyboard.Key mLongPressedKey;
    private AndroidPanelLayer.Spec mMainLayerSpec;
    private KeyboardPanelView mMainView;
    private boolean mPhysicalKeyboardMode;
    private PhysicalKeyboardSettingsHelper mPhysicalKeyboardSettingsHelper;
    private AndroidPanelLayer.Spec mPopupLayerSpec;
    private int mPopupResId;
    private KeyboardPopupView mPopupView;
    private boolean mRemoteKeyboardDisabled;
    private final String mRequestingPanel;
    private boolean mTypeAheadControlFlagExists;
    private boolean mTypeAheadDisabled;
    private String mUiLanguage;
    private boolean tooltipShownOnHover;

    private native long nativeCreateInstance(long j, long j2);

    private native void nativeSetEnvironment(long j, boolean z, String str);

    public native void nativeOnAction(long j, String str, String str2, String str3, String str4, boolean z);

    public void showPopup() {
    }

    public class KeyboardActionListenerDefault implements KeyboardView.OnKeyboardActionListener {
        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public void onKey(int i, int[] iArr) {
        }

        public KeyboardActionListenerDefault() {
        }

        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public void onPress(String str, boolean z, Keyboard.Key key) {
            if (key != null && !key.disabled) {
                if (KeyboardPanelApp.this.mKeyboardTranscription != null && KeyboardPanelApp.this.mKeyboardTranscription.isActive() && KeyboardPanelApp.this.mEnableDictationModeAutoTrigger.equals(DictationModeAutoTriggerState.ON_WITH_AUTO_SUBMIT)) {
                    KeyboardPanelApp.this.mEnableDictationModeAutoTrigger = DictationModeAutoTriggerState.OFF;
                }
                switch (KeyCode.get(key.codes.get(0).intValue())) {
                    case LAYOUT:
                    case DISMISS:
                    case ACTION_KEY:
                        return;
                    case RETURN_TO_CURRENT_LANGUAGE:
                        KeyboardPanelApp.this.mPhysicalKeyboardMode = false;
                        KeyboardPanelApp.this.handleCommand(str, KeyCode.get(key.codes.get(0).intValue()), key);
                        return;
                    case BACKSPACE:
                    case ARROW_UP:
                    case ARROW_DOWN:
                    case ARROW_LEFT:
                    case ARROW_RIGHT:
                    case TRANSCRIBE:
                        KeyboardPanelApp.this.handleCommand(str, KeyCode.get(key.codes.get(0).intValue()), key);
                        return;
                    case PHONE_INPUT:
                        KeyboardPanelApp.this.handleCommand(str, KeyCode.get(key.codes.get(0).intValue()), key);
                        KeyboardPanelApp.this.getCommandChannel().sendCommand("telemetry oculus_keyboard_command 1 0 remote_input 1");
                        return;
                    case WORD_PREDICTION:
                        if (key.text != null && KeyboardPanelApp.this.mCurrentTextBuilder != null) {
                            String additionalText = KeyboardTypeahead.getAdditionalText(KeyboardPanelApp.this.mCurrentTextBuilder.toString(), key.text.toString());
                            KeyboardPanelApp.this.mKeyboardTypeahead.typeaheadUsed(additionalText);
                            KeyboardPanelApp.this.getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
                            KeyboardPanelApp keyboardPanelApp = KeyboardPanelApp.this;
                            keyboardPanelApp.nativeOnAction(keyboardPanelApp.getNativePointer(), "simple", additionalText, KeyCode.NONE.name(), KeyAction.TEXT.name(), false);
                            if (KeyboardPanelApp.this.mCurrentTextBuilder != null) {
                                KeyboardPanelApp.this.mCurrentTextBuilder.append(additionalText);
                                KeyboardPanelApp.this.getTypeahead();
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        CharSequence charSequence = z ? key.shiftText : key.text;
                        if (charSequence == null) {
                            charSequence = key.text;
                        }
                        if (charSequence != null) {
                            KeyboardPanelApp.this.playPressSound();
                            KeyboardPanelApp.this.commitTextInput(str, charSequence.toString(), KeyCode.NONE, KeyAction.TEXT, false);
                            return;
                        }
                        return;
                }
            }
        }

        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public void onRelease(String str, boolean z, Keyboard.Key key) {
            InputType inputType;
            if (!key.disabled) {
                KeyboardPanelApp.this.hidePopupLayer();
                if (key.codes.get(0).intValue() == KeyCode.LAYOUT.value) {
                    KeyboardPanelApp.this.stopTypeahead();
                    KeyboardPanelApp.this.getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/layout.ogg"));
                    if (key.keyboardLocale != KeyboardLocale.UNKNOWN) {
                        if (KeyboardPanelApp.this.mCurrentInputType == InputType.EMAIL_DEFAULT) {
                            inputType = InputType.TEXT_DEFAULT;
                        } else {
                            inputType = KeyboardPanelApp.this.mCurrentInputType;
                        }
                        KeyboardPanelApp.this.setUILanguage(key.keyboardLocale.toString());
                        KeyboardPanelApp.this.setUserLocale(key.keyboardLocale.toString());
                        KeyboardPanelApp.this.setEnvironment(key.keyboardLocale.toString(), KeyboardPanelApp.this.mCurrentActionType.name(), inputType.name(), KeyboardPanelApp.this.mRemoteKeyboardDisabled, KeyboardPanelApp.this.mKeyboardTranscriptionDisabled, KeyboardPanelApp.this.mPhysicalKeyboardMode, KeyboardPanelApp.this.mTypeAheadDisabled, DictationModeAutoTriggerState.OFF.getStateName(), true);
                    } else {
                        int i = -1;
                        int fixupTranscriptionKeyboardId = KeyboardPanelApp.this.fixupTranscriptionKeyboardId(key.keyboardId);
                        if (key.keyboardId == R.xml.language_selector_32 || key.keyboardId == R.xml.language_selector_48 || key.keyboardId == R.xml.language_selector_jp_32 || key.keyboardId == R.xml.language_selector_jp_48) {
                            KeyboardPanelApp keyboardPanelApp = KeyboardPanelApp.this;
                            i = keyboardPanelApp.fixupTranscriptionKeyboardId(keyboardPanelApp.getKeyboardLayoutResourceId());
                            if (key.keyboardId == R.xml.language_selector_32) {
                                fixupTranscriptionKeyboardId = R.xml.language_selector_jp_32;
                            } else if (key.keyboardId == R.xml.language_selector_48) {
                                fixupTranscriptionKeyboardId = R.xml.language_selector_jp_48;
                            }
                        }
                        KeyboardPanelView keyboardPanelView = KeyboardPanelApp.this.mMainView;
                        Context presentationContext = KeyboardPanelApp.this.getPanelLayer(AndroidPanelApp.MAIN_LAYER).getPresentationContext();
                        ActionType actionType = KeyboardPanelApp.this.mCurrentActionType;
                        KeyboardLocale keyboardLocale = KeyboardPanelApp.this.mCurrentLanguage;
                        KeyboardSize keyboardSize = KeyboardPanelApp.this.mCurrentKeyboardSize;
                        KeyboardPanelApp keyboardPanelApp2 = KeyboardPanelApp.this;
                        keyboardPanelView.setKeyboard(presentationContext, fixupTranscriptionKeyboardId, actionType, keyboardLocale, keyboardSize, keyboardPanelApp2, i, keyboardPanelApp2.isTranscriptionEnabled(), KeyboardPanelApp.this.isNumeric());
                        KeyboardPanelApp.this.getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_layout 1 0 layout %s", KeyboardPanelApp.this.getContext().getResources().getResourceEntryName(key.keyboardId)));
                        KeyboardPanelApp.this.startTypeahead();
                    }
                } else if (key.codes.contains(Integer.valueOf(KeyCode.RETURN_TO_CURRENT_LANGUAGE.value))) {
                    KeyboardPanelApp.this.getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/layout.ogg"));
                    if (key.keyboardSize != KeyboardSize.UNKNOWN) {
                        KeyboardPanelApp.this.setUserKeyboardSize(key.keyboardSize);
                    }
                    KeyboardPanelApp.this.stopTypeahead();
                    KeyboardPanelApp.this.returnToCurrentLanguage();
                } else if (key.codes.get(0).intValue() == KeyCode.DISMISS.value || key.codes.get(0).intValue() == KeyCode.ACTION_KEY.value) {
                    KeyboardPanelApp.this.handleCommand(str, KeyCode.get(key.codes.get(0).intValue()), key);
                }
                if (key.codes.get(0).intValue() == KeyCode.SHIFT.value) {
                    KeyboardPanelApp.this.getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
                    KeyboardPanelApp.this.mMainView.toggleShiftState();
                } else if (KeyboardPanelApp.this.mMainView.getMainKeyboard().getShiftState() != ShiftState.CAPS_LOCK) {
                    KeyboardPanelApp.this.mMainView.setShiftState(ShiftState.OFF);
                }
                if (key.codes.get(0).intValue() != KeyCode.TRANSCRIBE.value) {
                    KeyboardPanelApp.this.mMainView.getTranscriptionPanelView().resetTranscription();
                    KeyboardPanelApp.this.stopTranscription();
                }
            }
        }

        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public void onHoverEnter(Keyboard.Key key) {
            KeyboardPanelApp.this.playHoverSound();
            if (key.codes.get(0).intValue() == KeyCode.PHONE_INPUT.value) {
                KeyboardPanelApp.this.tooltipShownOnHover = true;
                KeyboardPanelApp keyboardPanelApp = KeyboardPanelApp.this;
                keyboardPanelApp.showTooltip(key, keyboardPanelApp.getContext().getResources().getString(R.string.keyboard_remote_input_key_hover_tooltip));
            } else if (key.codes.get(0).intValue() != KeyCode.TRANSCRIBE.value) {
                KeyboardPanelApp.this.hideTooltip();
            } else if (!KeyboardPanelApp.this.mMainView.isTranscriptionFeedbackVisible()) {
                KeyboardPanelApp.this.tooltipShownOnHover = true;
                KeyboardPanelApp keyboardPanelApp2 = KeyboardPanelApp.this;
                keyboardPanelApp2.showTooltip(key, keyboardPanelApp2.getContext().getResources().getString(R.string.transcribe_key_hover_tooltip_installed));
            }
        }

        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public void onHoverExit() {
            KeyboardPanelApp.this.hideTooltip();
        }

        @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener
        public boolean onLongPress(Keyboard.Key key, boolean z) {
            KeyboardPanelApp.this.mPopupResId = z ? key.shiftPopupResId : key.popupResId;
            if (KeyboardPanelApp.this.mPopupResId == 0) {
                return false;
            }
            KeyboardPanelApp.this.mLongPressedKey = key;
            KeyboardPanelApp.this.showPopupLayer();
            return true;
        }
    }

    public KeyboardPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        String str;
        AndroidPanelLayer.Shape shape;
        this.PRESS_SOUND_FILE_PATH = "apk:///assets/press.ogg";
        this.HOVER_SOUND_FILE_PATH = "apk:///assets/hover.ogg";
        this.BACKSPACE_KEY_SOUND_FILE_PATH = "apk:///assets/backspace.ogg";
        this.TRANSCRIPTION_KEY_SOUND_FILE_PATH = "apk:///assets/transcribe_ding.ogg";
        this.LAYOUT_KEY_SOUND_FILE_PATH = "apk:///assets/layout.ogg";
        this.mAllowEmoji = true;
        this.mDefaultLanguage = KeyboardLocale.UNKNOWN;
        this.mCurrentLanguage = KeyboardLocale.UNKNOWN;
        this.mCurrentInputType = InputType.UNKNOWN;
        this.mCurrentActionType = ActionType.UNKNOWN;
        this.mRemoteKeyboardDisabled = false;
        this.mKeyboardTranscriptionDisabled = false;
        this.mTypeAheadDisabled = true;
        this.mTypeAheadControlFlagExists = false;
        this.mEnableDictationModeAutoTrigger = DictationModeAutoTriggerState.OFF;
        this.mCurrentKeyboardSize = KeyboardSize.LARGE;
        this.tooltipShownOnHover = false;
        this.mPhysicalKeyboardMode = false;
        this.mDefaultKeyboardActionListener = new KeyboardActionListenerDefault();
        this.mCurrentKeyboardSize = getUserKeyboardSize();
        this.mAllowImeComposition = getEnvironmentArg("AllowImeComposition") != null ? getEnvironmentArg("AllowImeComposition").equalsIgnoreCase("true") : false;
        if (isInOverlay()) {
            if (isGKEnabled("oculus_vrshell_input_overlay_composition")) {
                this.mAllowImeComposition = true;
            } else {
                this.mAllowEmoji = false;
            }
        }
        this.mCurrentTextBuilder = new StringBuilder();
        String str2 = "";
        this.mCurrentLanguage = parseLocale(getEnvironmentArg("Language") != null ? getEnvironmentArg("Language") : str2);
        this.mCurrentInputType = InputType.getInputType(getEnvironmentArg("InputType") != null ? getEnvironmentArg("InputType") : str2);
        this.mCurrentActionType = ActionType.getActionType(getEnvironmentArg("ActionType") != null ? getEnvironmentArg("ActionType") : str2);
        this.mRemoteKeyboardDisabled = getEnvironmentArg("RemoteKeyboardDisabled") != null && getEnvironmentArg("RemoteKeyboardDisabled").equals("true");
        this.mKeyboardTranscriptionDisabled = getEnvironmentArg("KeyboardTranscriptionDisabled") != null && getEnvironmentArg("KeyboardTranscriptionDisabled").equals("true");
        this.mTypeAheadControlFlagExists = getEnvironmentArg("KeyboardTypeAheadDisabled") != null;
        this.mTypeAheadDisabled = getEnvironmentArg("KeyboardTypeAheadDisabled") == null || getEnvironmentArg("KeyboardTypeAheadDisabled").equals("true");
        this.mEnableDictationModeAutoTrigger = DictationModeAutoTriggerState.getStateFromString(getEnvironmentArg("EnableDictationModeAutoTrigger"));
        setUILanguage(getUserLocale());
        nativeSetEnvironment(getNativePointer(), this.mAllowImeComposition, getEnvironmentArg("Cookie") != null ? getEnvironmentArg("Cookie") : str2);
        this.mIsIMEConnected = getEnvironmentArg("ConnectionType") != null && ConnectionType.KEYBOARD_IME.getName().equals(getEnvironmentArg("ConnectionType"));
        if (this.mIsIMEConnected) {
            bindIMECallbackService();
        }
        this.mRequestingPanel = getEnvironmentArg("RequestingPanel") != null ? getEnvironmentArg("RequestingPanel") : EnvironmentCompat.MEDIA_UNKNOWN;
        this.mPhysicalKeyboardMode = getEnvironmentArg("PhysicalKeyboardMode") != null && getEnvironmentArg("PhysicalKeyboardMode").equals("true");
        this.mPhysicalKeyboardSettingsHelper = new PhysicalKeyboardSettingsHelper(isFeatureEnabled("keyboard_tracking_opt_in"));
        String inputType = this.mCurrentInputType.toString();
        if (this.mCurrentLanguage.equals(KeyboardLocale.UNKNOWN)) {
            str = getUserLocale();
        } else {
            str = this.mCurrentLanguage.toString();
        }
        if (shouldUseDefaultForPassword(inputType, str)) {
            this.mCurrentLanguage = KeyboardLocale.EN_US;
        }
        if ("Flat".equals(getEnvironmentArg("Shape"))) {
            shape = AndroidPanelLayer.Shape.Flat;
        } else {
            shape = AndroidPanelLayer.Shape.LandscapeCylinder;
        }
        setLayerSpecs(shape);
        showMainLayer();
        getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_language 1 0 ui_language %s default_language %s keyboard_language %s", this.mUiLanguage, this.mDefaultLanguage.toString(), this.mCurrentLanguage.toString()));
    }

    private void showMainLayer() {
        attachResizeLayoutListener(ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, this.mMainLayerSpec, new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.keyboardv2.KeyboardPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AndroidPanelApp.MAIN_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return KeyboardPanelApp.this.createMainLayerView(context);
            }
        }), AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mMainView == null) {
            this.mMainView = (KeyboardPanelView) LayoutInflater.from(context).inflate(R.layout.keyboard_panel_view, (ViewGroup) null);
            this.mMainView.initialize(getDefaultKeyboardActionListener(), isInOverlay());
            setEnvironment(this.mCurrentLanguage.toString(), this.mCurrentActionType.name(), this.mCurrentInputType.name(), this.mRemoteKeyboardDisabled, this.mKeyboardTranscriptionDisabled, this.mPhysicalKeyboardMode, this.mTypeAheadDisabled, this.mEnableDictationModeAutoTrigger.getStateName(), true);
            return this.mMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPopupLayer() {
        ensurePanelLayer(POPUP_LAYER, this.mPopupLayerSpec, new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.keyboardv2.KeyboardPanelApp.AnonymousClass2 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return KeyboardPanelApp.POPUP_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return KeyboardPanelApp.this.createPopupLayerView(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createPopupLayerView(Context context) {
        this.mPopupView = (KeyboardPopupView) LayoutInflater.from(context).inflate(R.layout.keyboard_popup_view, (ViewGroup) null);
        this.mPopupView.initialize(this);
        this.mPopupView.setKeyboard(new Keyboard(context, this.mPopupResId, this.mCurrentActionType, this.mCurrentLanguage, this.mCurrentKeyboardSize, this, false));
        this.mPopupView.setShiftState(this.mMainView.getMainKeyboard().getShiftState());
        return this.mPopupView;
    }

    private void setLayerSpecs(AndroidPanelLayer.Shape shape) {
        this.mMainLayerSpec = new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 0, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, shape, R.style.KeyboardPanelTheme);
        this.mPopupLayerSpec = new AndroidPanelLayer.Spec(POPUP_LAYER, 0, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, shape, R.style.KeyboardPanelTheme);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new IllegalStateException("Unexpected call to getLayerSpec for layer " + str);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        throw new IllegalStateException("Unexpected call to createViewForLayer for layer " + str);
    }

    public void playPressSound() {
        getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
    }

    public void playHoverSound() {
        getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/hover.ogg"));
    }

    public void commitTextInput(String str, String str2, KeyCode keyCode, KeyAction keyAction, boolean z) {
        if (isTranscriptionPanelVisible()) {
            this.mMainView.getTranscriptionPanelView().resetTranscription();
            stopTranscription();
        }
        nativeOnAction(getNativePointer(), str, str2, keyCode.name(), keyAction.name(), z);
        if (z) {
            this.mCurrentTextBuilder = new StringBuilder();
        }
        if (this.mCurrentTextBuilder != null) {
            if (keyCode == KeyCode.BACKSPACE) {
                if (this.mCurrentTextBuilder.length() > 0) {
                    StringBuilder sb = this.mCurrentTextBuilder;
                    sb.setLength(sb.length() - KeyboardHandler.getCharCountToDeleteOnBackSpace(this.mCurrentTextBuilder.toString(), this.mCurrentTextBuilder.length()));
                }
            } else if (keyAction == KeyAction.TEXT) {
                this.mCurrentTextBuilder.append(str2);
            }
            if (isTypeaheadEnabled()) {
                getTypeahead();
            }
        }
    }

    public void getTypeahead() {
        KeyboardTypeahead keyboardTypeahead = this.mKeyboardTypeahead;
        if (keyboardTypeahead != null) {
            keyboardTypeahead.getTypeahead(this.mCurrentTextBuilder.toString());
        }
    }

    public KeyboardActionListenerDefault getDefaultKeyboardActionListener() {
        return this.mDefaultKeyboardActionListener;
    }

    public boolean getAllowImeComposition() {
        return this.mAllowImeComposition;
    }

    public boolean getAllowEmoji() {
        return this.mAllowEmoji;
    }

    /* access modifiers changed from: protected */
    public boolean isRemoteInputEnabled() {
        return !this.mRemoteKeyboardDisabled && isFeatureEnabled("remote_input");
    }

    public boolean isTranscriptionEnabled() {
        return !this.mKeyboardTranscriptionDisabled && this.mCurrentInputType != InputType.PASSWORD && isFeatureEnabled("transcription");
    }

    public boolean isTranscriptionFeedbackEnabled() {
        return isFeatureEnabled("transcription_feedback");
    }

    private boolean isTranscriptionPanelVisible() {
        return isTranscriptionEnabled() && this.mMainView.getMainKeyboard() != null && this.mMainView.getTranscriptionPanelView().getVisibility() == 0;
    }

    private boolean isTypeAheadPanelVisible() {
        return isTypeaheadEnabled() && this.mMainView.getMainKeyboard() != null && this.mMainView.getTypeaheadPanelView().getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean isTypeaheadEnabled() {
        return (this.mCurrentInputType == InputType.TEXT_DEFAULT && this.mCurrentLanguage.toString().equalsIgnoreCase("en_us") && !this.mPhysicalKeyboardMode && VALID_TYPEAHEAD_LAYOUTS.contains(Integer.valueOf(this.mMainView.getMainKeyboard().getLayoutResId()))) && (isFeatureEnabled("typeahead") || isGKEnabled("oculus_vrshell_keyboard_typeahead")) && !this.mTypeAheadDisabled;
    }

    private void bindIMECallbackService() {
        AnonymousClass3 r0 = new ServiceConnection() {
            /* class com.oculus.panelapp.keyboardv2.KeyboardPanelApp.AnonymousClass3 */

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                KeyboardPanelApp.this.mKeyboardIMECallback = IKeyboardInputMethodInterface.Stub.asInterface(iBinder);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                KeyboardPanelApp.this.mKeyboardIMECallback = null;
            }
        };
        Intent intent = new Intent(getContext(), KeyboardInputMethodService.KeyboardInputMethodCallbackService.class);
        intent.setAction(IKeyboardInputMethodInterface.class.getName());
        getContext().bindService(intent, r0, 1);
    }

    private void submitInputToIME(String str, String str2) {
        try {
            if (this.mIsIMEConnected && this.mKeyboardIMECallback != null) {
                this.mKeyboardIMECallback.submitInput(str, str2);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to submit input text to IME", e);
        }
    }

    private void closeIMEConnection() {
        if (this.mIsIMEConnected) {
            try {
                if (this.mKeyboardIMECallback != null) {
                    this.mKeyboardIMECallback.closeConnection();
                }
            } catch (RemoteException unused) {
                Log.e(TAG, "Unable to close IME connection");
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleCommand(String str, KeyCode keyCode, Keyboard.Key key) {
        switch (keyCode) {
            case DISMISS:
            case RETURN_TO_CURRENT_LANGUAGE:
            case ARROW_UP:
            case ARROW_DOWN:
            case ARROW_LEFT:
            case ARROW_RIGHT:
                getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
                nativeOnAction(getNativePointer(), str, keyCode.name(), keyCode.name(), KeyAction.COMMAND.name(), false);
                return;
            case ACTION_KEY:
                getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
                if (isTranscriptionPanelVisible()) {
                    nativeOnAction(getNativePointer(), str, this.mMainView.getTranscriptionPanelView().getTranscriptionText(), KeyCode.NONE.name(), KeyAction.TEXT.name(), false);
                    this.mMainView.getTranscriptionPanelView().clear();
                }
                this.mMainView.getTranscriptionPanelView().setVisibility(8);
                nativeOnAction(getNativePointer(), str, keyCode.name(), keyCode.name(), KeyAction.COMMAND.name(), false);
                if (this.mCurrentTextBuilder != null) {
                    stopTypeahead();
                    this.mCurrentTextBuilder.setLength(0);
                }
                startTypeahead();
                return;
            case BACKSPACE:
                getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/backspace.ogg"));
                commitTextInput(str, keyCode.name(), keyCode, KeyAction.COMMAND, false);
                return;
            case TRANSCRIBE:
                if (isTranscriptionEnabled()) {
                    getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/transcribe_ding.ogg"));
                    KeyboardTranscription keyboardTranscription = this.mKeyboardTranscription;
                    if (keyboardTranscription == null || !keyboardTranscription.isActive()) {
                        startAssistant();
                        this.mKeyboardTranscription.startTranscription(this.mCurrentInputType, this.mCurrentActionType, this.mRequestingPanel);
                        showTranscription();
                        if (!this.mMainView.isTranscriptionFeedbackVisible() && this.mEnableDictationModeAutoTrigger == DictationModeAutoTriggerState.OFF) {
                            showTooltip(key, getContext().getResources().getString(R.string.transcribe_key_click_tooltip));
                        }
                        getCommandChannel().sendCommand("telemetry oculus_keyboard_command 1 0 start_transcription 1");
                        return;
                    }
                    stopTranscription();
                    return;
                }
                return;
            case PHONE_INPUT:
                getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
                nativeOnAction(getNativePointer(), str, keyCode.name(), keyCode.name(), KeyAction.COMMAND.name(), false);
                showTooltip(key, getContext().getResources().getString(R.string.keyboard_remote_input_key_click_tooltip));
                return;
            default:
                return;
        }
    }

    public void sendTranscription() {
        int i = 0;
        getCommandChannel().sendCommand(String.format("audioPlay %s", "apk:///assets/press.ogg"));
        String transcriptionText = this.mMainView.getTranscriptionPanelView().getTranscriptionText();
        if (!TextUtils.isEmpty(transcriptionText)) {
            nativeOnAction(getNativePointer(), "simple", transcriptionText, KeyCode.NONE.name(), KeyAction.TEXT.name(), false);
            i = transcriptionText.trim().length();
            this.mMainView.getTranscriptionPanelView().clear();
        }
        this.mCurrentTextBuilder.append(transcriptionText);
        FrameCommandChannel commandChannel = getCommandChannel();
        commandChannel.sendCommand("telemetry oculus_keyboard_command 1 0 finish_transcription " + i);
    }

    public void stopTranscription() {
        KeyboardTranscription keyboardTranscription = this.mKeyboardTranscription;
        if (keyboardTranscription != null) {
            keyboardTranscription.stopTranscription();
        }
    }

    public void showTooltip(Keyboard.Key key, String str) {
        int i = key.y;
        if (isTranscriptionPanelVisible()) {
            i += this.mMainView.getTranscriptionPanelView().getHeight();
        }
        if (isTypeAheadPanelVisible()) {
            i += this.mMainView.getTypeaheadPanelView().getHeight();
        }
        this.mTooltipManager.showTooltip(new TooltipDefinition(AndroidPanelApp.MAIN_LAYER, str, TooltipPosition.Bottom, TooltipColor.Black, "Keyboard_input_tooltip"), TooltipUVCoordinates.getTooltipUVCoordinates(key.x, i, key.width, key.height, getPanelLayer(AndroidPanelApp.MAIN_LAYER).getWidthInPixels(), getPanelLayer(AndroidPanelApp.MAIN_LAYER).getHeightInPixels(), TooltipPosition.Bottom));
    }

    /* access modifiers changed from: package-private */
    public int getKeyboardLayoutResourceIdForName(String str) {
        return getContext().getResources().getIdentifier(str, "xml", getContext().getPackageName());
    }

    /* access modifiers changed from: package-private */
    public int getKeyboardLayoutResourceIdForName(StringBuilder sb) {
        return getKeyboardLayoutResourceIdForName(sb.toString());
    }

    /* access modifiers changed from: package-private */
    public int getKeyboardLayoutResourceId() {
        int keyboardLayoutResourceId = getKeyboardLayoutResourceId(this.mCurrentInputType);
        return keyboardLayoutResourceId == 0 ? getKeyboardLayoutResourceId(InputType.TEXT_DEFAULT) : keyboardLayoutResourceId;
    }

    /* access modifiers changed from: package-private */
    public int getKeyboardLayoutResourceId(InputType inputType) {
        KeyboardLocale keyboardLocale;
        InputType inputType2;
        StringBuilder sb = new StringBuilder();
        KeyboardLocale keyboardLocale2 = this.mCurrentLanguage;
        if (this.mPhysicalKeyboardMode) {
            inputType2 = InputType.TEXT_PHYSICAL;
            keyboardLocale = KeyboardLocale.EN_US;
        } else {
            keyboardLocale = keyboardLocale2;
            inputType2 = inputType;
        }
        if (inputType == InputType.PASSWORD) {
            inputType2 = InputType.TEXT_DEFAULT;
        }
        switch (inputType2) {
            case TEXT_SIMPLE:
            case TEXT_DEFAULT:
            case TEXT_URI:
            case TEXT_PHYSICAL:
                sb.append(keyboardLocale.toString().toLowerCase());
                sb.append("_");
                sb.append(InputType.toString(inputType2).toLowerCase());
                sb.append("_");
                sb.append(KeyboardSize.getSizeSuffix(this.mCurrentKeyboardSize));
                return fixupTranscriptionKeyboardId(getKeyboardLayoutResourceIdForName(sb));
            case EMAIL_DEFAULT:
            case NUMBER_DEFAULT:
            case NUMBER_COMPACT:
            case PHONE_DEFAULT:
                sb.append(InputType.toString(inputType2).toLowerCase());
                sb.append("_");
                sb.append(KeyboardSize.getSizeSuffix(this.mCurrentKeyboardSize));
                return getKeyboardLayoutResourceIdForName(sb);
            default:
                Log.e(TAG, String.format("Unknown type: %s (effective type %s)", inputType.name(), inputType2.name()));
                return 0;
        }
    }

    public int fixupTranscriptionKeyboardId(int i) {
        if (!isTranscriptionEnabled()) {
            return i;
        }
        if (i == R.xml.en_us_text_default_32) {
            return R.xml.en_us_text_default_transcription_32;
        }
        return i == R.xml.en_us_text_default_48 ? R.xml.en_us_text_default_transcription_48 : i;
    }

    public void setEnvironment(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, String str4, boolean z5) {
        KeyboardLocale parseLocale = parseLocale(str);
        ActionType actionType = ActionType.getActionType(str2);
        InputType inputType = InputType.getInputType(str3);
        DictationModeAutoTriggerState stateFromString = DictationModeAutoTriggerState.getStateFromString(str4);
        if (!(!z5 && parseLocale == this.mCurrentLanguage && inputType == this.mCurrentInputType && actionType == this.mCurrentActionType && z == this.mRemoteKeyboardDisabled && z2 == this.mKeyboardTranscriptionDisabled && z3 == this.mPhysicalKeyboardMode && z4 == this.mTypeAheadDisabled && stateFromString == this.mEnableDictationModeAutoTrigger)) {
            if (parseLocale != this.mCurrentLanguage) {
                getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_language 1 0 ui_language %s default_language %s keyboard_language %s", this.mUiLanguage, this.mDefaultLanguage.toString(), parseLocale.toString()));
            }
            if (isAutomaticDictationRequested(stateFromString)) {
                this.mCurrentLanguage = KeyboardLocale.EN_US;
                this.mKeyboardTranscriptionDisabled = false;
            } else {
                this.mCurrentLanguage = parseLocale;
                this.mKeyboardTranscriptionDisabled = z2;
            }
            this.mCurrentInputType = inputType;
            this.mCurrentActionType = actionType;
            this.mTypeAheadDisabled = z4;
            this.mPhysicalKeyboardMode = z3;
            this.mRemoteKeyboardDisabled = z;
            this.mEnableDictationModeAutoTrigger = stateFromString;
            int keyboardLayoutResourceId = getKeyboardLayoutResourceId();
            this.mMainView.setKeyboard(getPanelLayer(AndroidPanelApp.MAIN_LAYER).getPresentationContext(), keyboardLayoutResourceId, this.mCurrentActionType, this.mCurrentLanguage, this.mCurrentKeyboardSize, this, -1, isTranscriptionEnabled(), isNumeric());
            startTypeahead();
            getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_layout 1 0 layout %s", getContext().getResources().getResourceEntryName(keyboardLayoutResourceId)));
            Keyboard.Key transcribeKey = this.mMainView.getMainKeyboard().getTranscribeKey();
            if (isAutomaticDictationRequested(this.mEnableDictationModeAutoTrigger) && transcribeKey != null) {
                this.mDefaultKeyboardActionListener.onPress("simple", false, transcribeKey);
            }
        }
        hidePopupLayer();
    }

    private boolean isAutomaticDictationRequested(DictationModeAutoTriggerState dictationModeAutoTriggerState) {
        return dictationModeAutoTriggerState == DictationModeAutoTriggerState.ON || dictationModeAutoTriggerState == DictationModeAutoTriggerState.ON_WITH_AUTO_SUBMIT;
    }

    public void invalidateKeys(List<Keyboard.Key> list) {
        for (Keyboard.Key key : list) {
            this.mMainView.invalidateKey(key);
        }
    }

    public void invalidateKey(Keyboard.Key key) {
        this.mMainView.invalidateKey(key);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUILanguage(String str) {
        this.mUiLanguage = str;
        this.mDefaultLanguage = KeyboardLocale.getKnownLanguages(this.mUiLanguage);
        if (this.mDefaultLanguage == KeyboardLocale.KO_KR && !this.mAllowImeComposition) {
            Log.d(TAG, "Disallowing Korean Language as default language - Selecting EN_US");
            this.mDefaultLanguage = KeyboardLocale.EN_US;
        } else if (this.mDefaultLanguage == KeyboardLocale.JA_JP && !this.mAllowImeComposition) {
            Log.d(TAG, "Disallowing Japanese Language as default language - Selecting EN_US");
            this.mDefaultLanguage = KeyboardLocale.EN_US;
        } else if (this.mDefaultLanguage == KeyboardLocale.UNKNOWN) {
            Log.d(TAG, String.format("Unknown UI Language %s - Selecting EN_US", this.mUiLanguage));
            this.mDefaultLanguage = KeyboardLocale.EN_US;
        }
    }

    private KeyboardLocale parseLocale(String str) {
        KeyboardLocale knownLanguages = KeyboardLocale.getKnownLanguages(str);
        if (knownLanguages != KeyboardLocale.UNKNOWN) {
            return knownLanguages;
        }
        String str2 = TAG;
        Log.d(str2, "Unknown Language: " + str);
        return this.mDefaultLanguage;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseDefaultForPassword(String str, String str2) {
        if (InputType.getInputType(str) != InputType.PASSWORD) {
            return false;
        }
        if (str2 == null || str2.isEmpty()) {
            return isNonRomanCharset(getUserLocale());
        }
        return isNonRomanCharset(str2);
    }

    private boolean isNonRomanCharset(String str) {
        KeyboardLocale knownLanguages = KeyboardLocale.getKnownLanguages(str);
        return knownLanguages.equals(KeyboardLocale.JA_JP) || knownLanguages.equals(KeyboardLocale.JA_JP_ROMAJI) || knownLanguages.equals(KeyboardLocale.KO_KR) || knownLanguages.equals(KeyboardLocale.RU_RU) || knownLanguages.equals(KeyboardLocale.UNKNOWN);
    }

    private String getUserLocale() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("keyboard", 0);
        String string = sharedPreferences.getString("default_locale", null);
        String string2 = sharedPreferences.getString("user_locale", null);
        String upperCase = Locale.getDefault().toString().toUpperCase();
        if (string != null && string2 != null && upperCase.equals(string)) {
            return string2;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_locale", upperCase);
        edit.putString("default_locale", upperCase);
        edit.commit();
        return upperCase;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserLocale(String str) {
        SharedPreferences.Editor edit = getContext().getSharedPreferences("keyboard", 0).edit();
        edit.putString("user_locale", str);
        edit.commit();
    }

    private KeyboardSize getUserKeyboardSize() {
        String string = getContext().getSharedPreferences("keyboard", 0).getString("keyboard_size", null);
        if (string == null) {
            setUserKeyboardSize(KeyboardSize.LARGE);
            return KeyboardSize.LARGE;
        }
        try {
            return KeyboardSize.valueOf(string);
        } catch (Exception unused) {
            setUserKeyboardSize(KeyboardSize.LARGE);
            return KeyboardSize.LARGE;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserKeyboardSize(KeyboardSize keyboardSize) {
        this.mCurrentKeyboardSize = keyboardSize;
        SharedPreferences.Editor edit = getContext().getSharedPreferences("keyboard", 0).edit();
        edit.putString("keyboard_size", keyboardSize.name());
        edit.commit();
    }

    private void startAssistant() {
        if (this.mKeyboardAssistant == null) {
            this.mKeyboardAssistant = new KeyboardAssistant(this);
            this.mKeyboardTranscription = this.mKeyboardAssistant.getKeyboardTranscription();
            this.mKeyboardTypeahead = this.mKeyboardAssistant.getKeyboardTypeahead();
        }
    }

    private void showTranscription() {
        stopTypeahead();
        this.mMainView.getTranscriptionPanelView().setVisibility(0);
    }

    private void hideTranscription() {
        this.mMainView.getTranscriptionPanelView().setVisibility(8);
        startTypeahead();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startTypeahead() {
        if (isTypeaheadEnabled()) {
            this.mMainView.getTypeaheadPanelView().setVisibility(0);
            startAssistant();
            this.mKeyboardTypeahead.startTypeahead(this.mRequestingPanel);
            getTypeahead();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void stopTypeahead() {
        if (isTypeaheadEnabled()) {
            this.mMainView.getTypeaheadPanelView().setVisibility(8);
            KeyboardTypeahead keyboardTypeahead = this.mKeyboardTypeahead;
            if (keyboardTypeahead != null) {
                keyboardTypeahead.stopTypeahead(this.mCurrentTextBuilder.toString());
            }
        }
    }

    public void hidePopupLayer() {
        if (this.mPopupResId != 0) {
            destroyLayer(POPUP_LAYER);
            this.mPopupResId = 0;
            this.mLongPressedKey.onReleased();
            this.mMainView.invalidateKey(this.mLongPressedKey);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void hideTooltip() {
        if (this.tooltipShownOnHover) {
            this.mTooltipManager.hideTooltip();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f8  */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void nextFrameAction(java.lang.String r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.KeyboardPanelApp.nextFrameAction(java.lang.String, java.lang.String):void");
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        if (this.mMainView.getMainKeyboard().getLayoutResId() == R.xml.language_selector_32 || this.mMainView.getMainKeyboard().getLayoutResId() == R.xml.language_selector_48) {
            returnToCurrentLanguage();
        } else {
            nativeOnAction(getNativePointer(), "simple", KeyCode.DISMISS.name(), KeyCode.DISMISS.name(), KeyAction.COMMAND.name(), false);
        }
        getCommandChannel().playAudio(SoundType.CLOSE);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void returnToCurrentLanguage() {
        int keyboardLayoutResourceId = getKeyboardLayoutResourceId();
        this.mMainView.setKeyboard(getPanelLayer(AndroidPanelApp.MAIN_LAYER).getPresentationContext(), keyboardLayoutResourceId, this.mCurrentActionType, this.mCurrentLanguage, this.mCurrentKeyboardSize, this, -1, isTranscriptionEnabled(), isNumeric());
        startTypeahead();
        getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_layout 1 0 layout %s", getContext().getResources().getResourceEntryName(keyboardLayoutResourceId)));
    }

    public void onPhysicalKeyboardInput() {
        this.mPhysicalKeyboardSettingsHelper.turnOnHandTracking();
        if (!this.mPhysicalKeyboardMode) {
            this.mMainView.setKeyboard(getPanelLayer(AndroidPanelApp.MAIN_LAYER).getPresentationContext(), R.xml.en_us_text_physical_48, this.mCurrentActionType, this.mCurrentLanguage, this.mCurrentKeyboardSize, this, -1, isTranscriptionEnabled(), isNumeric());
        }
        this.mPhysicalKeyboardMode = true;
        getCommandChannel().sendCommand(String.format("telemetry oculus_keyboard_layout 1 0 layout %s", getContext().getResources().getResourceEntryName(R.xml.en_us_text_physical_48)));
        hidePopupLayer();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        stopTypeahead();
        KeyboardTranscription keyboardTranscription = this.mKeyboardTranscription;
        if (keyboardTranscription != null) {
            keyboardTranscription.destroy();
            submitInputToIME(KeyAction.COMMAND.name(), KeyCode.ACTION_KEY.name());
        }
        if (this.mPhysicalKeyboardMode) {
            this.mPhysicalKeyboardSettingsHelper.turnOffHandTracking();
        }
        closeIMEConnection();
        super.destroy();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String automationQueryLayer(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        AndroidPanelLayer panelLayer = getPanelLayer(str);
        if (panelLayer != null) {
            jSONObject.put("result", "success");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("layerId", str);
            jSONObject2.put("visible", panelLayer.isVisible());
            jSONObject2.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH, panelLayer.getWidthInPixels());
            jSONObject2.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_HEIGHT, panelLayer.getHeightInPixels());
            jSONObject.put("layer", jSONObject2);
            jSONObject2.put("view", convertKeyboardViewToObject(true));
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, jSONObject.toString(2));
        return jSONObject.toString();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String automationQueryView(String str, String str2, String str3, boolean z) throws JSONException {
        Keyboard.Key key;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("layerId", str);
        KeyboardView keyboardView = (KeyboardView) this.mMainView.findViewById(R.id.keyboard_input_view);
        if (str3.equals("id")) {
            key = findKeyByName(str2);
        } else {
            key = str3.equals("automationId") ? findKeyByAutomationId(str2) : null;
        }
        if (!str.equals(AndroidPanelApp.MAIN_LAYER) || keyboardView == null) {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        } else if (str2.equals(getContext().getResources().getResourceEntryName(R.id.keyboard_input_view))) {
            jSONObject.put("view", convertKeyboardViewToObject(z));
            jSONObject.put("result", "success");
        } else if (key != null) {
            jSONObject.put("view", convertKeyToObject(key, keyboardView.getVisibility()));
            jSONObject.put("result", "success");
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, jSONObject.toString(2));
        return jSONObject.toString();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String automationActivateView(String str, String str2, String str3) throws JSONException {
        Keyboard.Key key;
        JSONObject jSONObject = new JSONObject();
        getContext().getResources();
        KeyboardView keyboardView = (KeyboardView) this.mMainView.findViewById(R.id.keyboard_input_view);
        if (str3.equals("id")) {
            key = findKeyByName(str2);
        } else {
            key = str3.equals("automationId") ? findKeyByAutomationId(str2) : null;
        }
        if (!str.equals(AndroidPanelApp.MAIN_LAYER) || keyboardView == null) {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        } else if (str2.equals(getContext().getResources().getResourceEntryName(R.id.keyboard_input_view))) {
            performClick(keyboardView);
            jSONObject.put("view", convertKeyboardViewToObject(false));
        } else if (key != null) {
            performClick(keyboardView, (float) (key.x + keyboardView.getPaddingLeft() + (key.width / 2)), (float) (key.y + keyboardView.getPaddingTop() + (key.height / 2)));
            jSONObject.put("view", convertKeyToObject(key, keyboardView.getVisibility()));
            jSONObject.put("result", "success");
        } else {
            jSONObject.put("result", AutomationHelpers.AUTOMATION_RESULT_VALUE_NOTFOUND);
        }
        Log.d(TAG, jSONObject.toString(2));
        return jSONObject.toString();
    }

    private JSONObject convertKeyboardViewToObject(boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        KeyboardView keyboardView = (KeyboardView) this.mMainView.findViewById(R.id.keyboard_input_view);
        if (keyboardView == null) {
            return null;
        }
        jSONObject.put("id", R.id.keyboard_input_view);
        jSONObject.put(ServiceContract.EXTRA_NAME, getContext().getResources().getResourceEntryName(R.id.keyboard_input_view));
        jSONObject.put(DialogDefinitionBase.DIALOG_TYPE_KEY, keyboardView.getClass().getCanonicalName());
        jSONObject.put("visibility", keyboardView.getVisibility());
        if (z) {
            JSONArray jSONArray = new JSONArray();
            List<Keyboard.Key> keys = this.mMainView.getMainKeyboard().getKeys();
            jSONObject.put("childCount", keys.size());
            for (Keyboard.Key key : keys) {
                jSONArray.put(convertKeyToObject(key, keyboardView.getVisibility()));
            }
            jSONObject.put("children", jSONArray);
        }
        return jSONObject;
    }

    private JSONObject convertKeyToObject(Keyboard.Key key, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String keyNameFromKey = keyNameFromKey(key);
        if (keyNameFromKey != null) {
            jSONObject.put(ServiceContract.EXTRA_NAME, keyNameFromKey);
        }
        if (key.automationId <= 0) {
            key.automationId = getUniqueAutomationId();
        }
        jSONObject.put("automation_id", key.automationId);
        jSONObject.put(DialogDefinitionBase.DIALOG_TYPE_KEY, key.getClass().getCanonicalName());
        jSONObject.put("visibility", i);
        jSONObject.put("text", key.label);
        jSONObject.put("keyCode", key.codes.get(0));
        return jSONObject;
    }

    private Keyboard.Key findKeyByName(String str) {
        if (str == null) {
            return null;
        }
        for (Keyboard.Key key : this.mMainView.getMainKeyboard().getKeys()) {
            if (str.equals(keyNameFromKey(key))) {
                return key;
            }
        }
        return null;
    }

    private Keyboard.Key findKeyByAutomationId(String str) {
        if (str == null) {
            return null;
        }
        try {
            List<Keyboard.Key> keys = this.mMainView.getMainKeyboard().getKeys();
            int parseInt = Integer.parseInt(str);
            for (Keyboard.Key key : keys) {
                if (parseInt == key.automationId) {
                    return key;
                }
            }
            return null;
        } catch (NumberFormatException unused) {
            Log.w(TAG, String.format("findKeyByAutomationId %s is not a number. Did you mean to use idType \"id\"?", str));
            return null;
        }
    }

    private String keyNameFromKey(Keyboard.Key key) {
        if (key.text == null) {
            KeyCode keyCode = KeyCode.get(key.codes.get(0).intValue());
            if (keyCode == KeyCode.NONE) {
                return null;
            }
            String keyCode2 = keyCode.toString();
            int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode[keyCode.ordinal()];
            if (i != 1) {
                if (i != 4 || key.keyboardSize == KeyboardSize.UNKNOWN) {
                    return keyCode2;
                }
                return keyCode2 + String.format("_%s", key.keyboardSize.name());
            } else if (key.keyboardLocale != KeyboardLocale.UNKNOWN) {
                return keyCode2 + String.format("_%s", key.keyboardLocale);
            } else {
                String resourceEntryName = getContext().getResources().getResourceEntryName(key.keyboardId);
                return keyCode2 + String.format("_%s", resourceEntryName);
            }
        } else if (key.text.equals(" ")) {
            return "space";
        } else {
            if (!key.text.toString().matches("\\d+")) {
                return key.text.toString();
            }
            return "num" + ((Object) key.text);
        }
    }

    public void updateTranscription(String str, boolean z) {
        if (!z) {
            str = str + " ";
        }
        this.mMainView.getTranscriptionPanelView().updateTranscriptionText(str, KeyCode.NONE, KeyAction.TEXT, false, z);
    }

    public void onTranscriptionStopped(boolean z) {
        if (this.mMainView.getTranscriptionPanelView().getTranscriptionText().isEmpty()) {
            hideTranscription();
        }
        updateTranscriptionVolumeLevel(0.0f);
        if (this.mMainView.getMainKeyboard().getTranscribeKey() != null) {
            this.mMainView.getMainKeyboard().getTranscribeKey().icon.setState(new int[0]);
            KeyboardPanelView keyboardPanelView = this.mMainView;
            keyboardPanelView.invalidateKey(keyboardPanelView.getMainKeyboard().getTranscribeKey());
        }
        if (isTranscriptionFeedbackEnabled() && z) {
            this.mMainView.showTranscriptionFeedback(this.mKeyboardTranscription);
        }
        Keyboard.Key actionKey = this.mMainView.getMainKeyboard().getActionKey();
        if (this.mEnableDictationModeAutoTrigger == DictationModeAutoTriggerState.ON_WITH_AUTO_SUBMIT && z && actionKey != null) {
            this.mDefaultKeyboardActionListener.onRelease("simple", false, actionKey);
        }
        this.mEnableDictationModeAutoTrigger = DictationModeAutoTriggerState.OFF;
    }

    public void setTranscriptionListening(boolean z) {
        Keyboard.Key transcribeKey = this.mMainView.getMainKeyboard().getTranscribeKey();
        if (transcribeKey != null) {
            if (z) {
                transcribeKey.icon.setState(new int[]{16842913});
            } else {
                transcribeKey.icon.setState(new int[0]);
            }
            this.mMainView.invalidateKey(transcribeKey);
        }
        this.mMainView.getTranscriptionPanelView().updateTranscriptionVolumeLevel(0.0f);
    }

    public void updateTranscriptionVolumeLevel(float f) {
        this.mMainView.getTranscriptionPanelView().updateTranscriptionVolumeLevel(f);
    }

    public void updateTranscriptionInteractionId(String str) {
        FrameCommandChannel commandChannel = getCommandChannel();
        commandChannel.sendCommand("telemetry oculus_keyboard_command 1 0 transcription_id " + str);
    }

    public void clearTypeaheadOutput() {
        this.mMainView.getTypeaheadPanelView().clear();
    }

    public void setTypeaheadSuggestions(String str, String[] strArr) {
        this.mMainView.getTypeaheadPanelView().setTypeaheadSuggestions(str, strArr);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onBackground() {
        super.onBackground();
        stopTypeahead();
        KeyboardTranscription keyboardTranscription = this.mKeyboardTranscription;
        if (keyboardTranscription != null) {
            keyboardTranscription.stopTranscription();
        }
    }

    private boolean isFeatureEnabled(String str) {
        return "true".equals(getEnvironmentArg("_oc_ff:" + str));
    }

    private boolean isGKEnabled(String str) {
        return "true".equals(getEnvironmentArg("_oc_gk:" + str));
    }

    private boolean isInOverlay() {
        return "minimal_overlay".equals(getEnvironmentArg("_oc_shell_host"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isNumeric() {
        return InputType.NUMBER_COMPACT.equals(this.mCurrentInputType) || InputType.NUMBER_DEFAULT.equals(this.mCurrentInputType);
    }

    /* access modifiers changed from: protected */
    public void resetJapaneseIMEState() {
        if (this.mMainView.getMainKeyboard().getListener().equalsIgnoreCase(KeyboardActionListenerJP.TAG)) {
            ((KeyboardActionListenerJP) this.mMainView.getMainKeyboardView().getOnKeyboardActionListener()).resetIMEState();
        }
    }
}
