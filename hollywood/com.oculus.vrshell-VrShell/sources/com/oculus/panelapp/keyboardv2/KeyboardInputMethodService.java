package com.oculus.panelapp.keyboardv2;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.keyboardv2.IKeyboardInputMethodInterface;

public class KeyboardInputMethodService extends InputMethodService {
    private static final String ACTION_SHOW_VRSHELL_KEYBOARD = "com.oculus.show_keyboard";
    private static final String IME_OPTION_VRDESKTOP = "VrDesktopMode";
    private static final String KEYBOARD_OVERLAY_FEATURE = "oculus.software.overlay_keyboard";
    private static final String SHELLENV_PACKAGE_NAME = "com.oculus.shellenv";
    private static final String SHELL_KEYBOARD_CLOSE_CMD = "keyboard_close";
    private static final String SHELL_OVERLAY_COMMAND = "com.oculus.vrshell.OVERLAY_COMMAND";
    private static final String TAG = LoggingUtil.tag(KeyboardInputMethodService.class);
    private static final String VRSHELL_PACKAGE_NAME = "com.oculus.vrshell";
    private static KeyboardInputMethodService sInstance;
    private EditorInfo mEditorInfo;
    private long mShowRequestTimestamp;
    private boolean mShowing = false;
    private UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public static class KeyboardInputMethodCallbackService extends Service {
        private final IKeyboardInputMethodInterface.Stub mBinder = new IKeyboardInputMethodInterface.Stub() {
            /* class com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.KeyboardInputMethodCallbackService.AnonymousClass1 */

            @Override // com.oculus.panelapp.keyboardv2.IKeyboardInputMethodInterface
            public void submitInput(String str, String str2) {
                InputMethodEditorAction inputMethodEditorAction = InputMethodEditorAction.get(str);
                if (KeyboardInputMethodCallbackService.this.mIME != null) {
                    InputConnection currentInputConnection = KeyboardInputMethodCallbackService.this.mIME.getCurrentInputConnection();
                    if (currentInputConnection == null) {
                        Log.e(KeyboardInputMethodService.TAG, "No active input connection, dropping");
                    } else {
                        submitInput(currentInputConnection, inputMethodEditorAction, str2);
                    }
                }
            }

            private void submitInput(InputConnection inputConnection, InputMethodEditorAction inputMethodEditorAction, String str) {
                Log.d(KeyboardInputMethodService.TAG, String.format("imeAction: %s", inputMethodEditorAction.name()));
                int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction[inputMethodEditorAction.ordinal()];
                if (i == 1) {
                    inputConnection.commitText(str, str.length());
                } else if (i == 2) {
                    inputConnection.setComposingText(str, str.length());
                } else if (i == 3) {
                    submitCommand(inputConnection, str);
                }
            }

            private void submitCommand(InputConnection inputConnection, String str) {
                int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode[KeyCode.get(str).ordinal()];
                if (i == 1) {
                    CharSequence selectedText = inputConnection.getSelectedText(0);
                    if (selectedText == null || selectedText.length() <= 0) {
                        inputConnection.deleteSurroundingTextInCodePoints(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }
                } else if (i == 2) {
                    inputConnection.performEditorAction(KeyboardInputMethodService.getAndroidActionFromEditorInfo(KeyboardInputMethodService.getInstance().mEditorInfo));
                }
            }

            @Override // com.oculus.panelapp.keyboardv2.IKeyboardInputMethodInterface
            public void closeConnection() {
                if (KeyboardInputMethodCallbackService.this.mIME != null) {
                    KeyboardInputMethodCallbackService.this.mIME.closeIme();
                }
            }
        };
        private KeyboardInputMethodService mIME;

        public void onCreate() {
            super.onCreate();
            this.mIME = KeyboardInputMethodService.getInstance();
        }

        public IBinder onBind(Intent intent) {
            return this.mBinder;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.keyboardv2.KeyboardInputMethodService$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction = new int[InputMethodEditorAction.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode = new int[KeyCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|5|6|7|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        static {
            /*
                com.oculus.panelapp.keyboardv2.KeyCode[] r0 = com.oculus.panelapp.keyboardv2.KeyCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode = r0
                r0 = 1
                int[] r1 = com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.keyboardv2.KeyCode r2 = com.oculus.panelapp.keyboardv2.KeyCode.BACKSPACE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.keyboardv2.KeyCode r3 = com.oculus.panelapp.keyboardv2.KeyCode.ACTION_KEY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                com.oculus.panelapp.keyboardv2.InputMethodEditorAction[] r2 = com.oculus.panelapp.keyboardv2.InputMethodEditorAction.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction = r2
                int[] r2 = com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.oculus.panelapp.keyboardv2.InputMethodEditorAction r3 = com.oculus.panelapp.keyboardv2.InputMethodEditorAction.TEXT     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                int[] r0 = com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction     // Catch:{ NoSuchFieldError -> 0x003c }
                com.oculus.panelapp.keyboardv2.InputMethodEditorAction r2 = com.oculus.panelapp.keyboardv2.InputMethodEditorAction.COMPOSE     // Catch:{ NoSuchFieldError -> 0x003c }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x003c }
            L_0x003c:
                int[] r0 = com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$InputMethodEditorAction     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.oculus.panelapp.keyboardv2.InputMethodEditorAction r1 = com.oculus.panelapp.keyboardv2.InputMethodEditorAction.COMMAND     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.KeyboardInputMethodService.AnonymousClass1.<clinit>():void");
        }
    }

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(getApplicationContext());
        Log.d(TAG, "onCreate");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public void onStartInput(EditorInfo editorInfo, boolean z) {
        this.mEditorInfo = editorInfo;
    }

    public boolean onShowInputRequested(int i, boolean z) {
        Log.d(TAG, "ShowInputRequested");
        if (isVrDesktopCaller()) {
            sendBroadcast(new Intent(ACTION_SHOW_VRSHELL_KEYBOARD));
            return false;
        } else if (!hasCallerRequestedOverlayKeyboard()) {
            Log.d(TAG, "Calling app did not request Keyboard Overlay feature");
            return false;
        } else if (this.mShowing) {
            return false;
        } else {
            Intent intent = new Intent("com.oculus.vrshell.intent.action.OVERLAY");
            intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver"));
            intent.setData(Uri.parse("systemux://keyboard"));
            intent.putExtra("Mode", "Overlay");
            intent.putExtra("RotationPitch", "0.0");
            intent.putExtra("PanelTranslationMY", "0.0");
            intent.putExtra("ConnectionType", "KeyboardIME");
            intent.putExtra("Cookie", getCookie());
            intent.putExtra("InputType", getInputTypeFromEditorInfo(this.mEditorInfo).name());
            intent.putExtra("KeyboardTypeAheadDisabled", "false");
            intent.putExtra("ActionType", getActionTypeFromEditorInfo(this.mEditorInfo).name());
            sendBroadcast(intent);
            this.mShowing = true;
            reportImeConnectionOpened();
            return false;
        }
    }

    public void hideWindow() {
        if (!isVrDesktopCaller()) {
            if (!hasCallerRequestedOverlayKeyboard()) {
                Log.d(TAG, "Calling app did not request Keyboard Overlay feature");
                return;
            }
            Intent intent = new Intent(SHELL_OVERLAY_COMMAND);
            intent.putExtra("command", SHELL_KEYBOARD_CLOSE_CMD);
            sendBroadcast(intent);
            this.mShowing = false;
            closeIme();
        }
    }

    private String getCookie() {
        InputBinding currentInputBinding = getCurrentInputBinding();
        int pid = currentInputBinding.getPid();
        getPackageManager().getNameForUid(currentInputBinding.getUid());
        return String.format("%s:%d", this.mEditorInfo.packageName, Integer.valueOf(pid));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void closeIme() {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.performEditorAction(6);
        }
        reportImeConnectionClosed();
    }

    private boolean hasCallerRequestedOverlayKeyboard() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        if (!packageManager.hasSystemFeature(KEYBOARD_OVERLAY_FEATURE)) {
            return false;
        }
        String str = this.mEditorInfo.packageName;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 16384);
            if (packageInfo.reqFeatures != null) {
                for (FeatureInfo featureInfo : packageInfo.reqFeatures) {
                    if (KEYBOARD_OVERLAY_FEATURE.equals(featureInfo.name)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot find the package " + str, e);
        }
        return false;
    }

    public void onFinishInput() {
        super.onFinishInput();
    }

    public static KeyboardInputMethodService getInstance() {
        return sInstance;
    }

    public static InputType getInputTypeFromEditorInfo(EditorInfo editorInfo) {
        if (editorInfo == null) {
            return InputType.TEXT_DEFAULT;
        }
        int i = editorInfo.inputType & 15;
        int i2 = editorInfo.inputType & 4080;
        if (i == 1) {
            return getInputTypeFromTextVariation(i2);
        }
        if (i == 2) {
            return InputType.NUMBER_DEFAULT;
        }
        if (i != 3) {
            return InputType.TEXT_DEFAULT;
        }
        return InputType.PHONE_DEFAULT;
    }

    private static InputType getInputTypeFromTextVariation(int i) {
        if (i == 32) {
            return InputType.EMAIL_DEFAULT;
        }
        if (i == 128 || i == 144) {
            return InputType.PASSWORD;
        }
        return InputType.TEXT_DEFAULT;
    }

    public static ActionType getActionTypeFromEditorInfo(EditorInfo editorInfo) {
        int androidActionFromEditorInfo = getAndroidActionFromEditorInfo(editorInfo);
        int i = editorInfo.inputType & 16773120;
        if (androidActionFromEditorInfo == 2) {
            return ActionType.GO;
        }
        if (androidActionFromEditorInfo == 3) {
            return ActionType.SEARCH;
        }
        if (androidActionFromEditorInfo == 4) {
            return ActionType.SEND;
        }
        if (androidActionFromEditorInfo == 5) {
            return ActionType.NEXT;
        }
        if (androidActionFromEditorInfo == 6) {
            return ActionType.DONE;
        }
        if ((i & 393216) != 0) {
            return ActionType.NEWLINE;
        }
        return ActionType.DONE;
    }

    public static int getAndroidActionFromEditorInfo(EditorInfo editorInfo) {
        return editorInfo.imeOptions & 255;
    }

    private void reportImeConnectionOpened() {
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_keyboard_open_ime_connection"), false);
    }

    private void reportImeConnectionClosed() {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_keyboard_close_ime_connection");
        analyticsEvent.setExtra("duration_in_sec", Long.valueOf((System.currentTimeMillis() - this.mShowRequestTimestamp) / 1000));
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    private boolean isVrDesktopCaller() {
        EditorInfo editorInfo = this.mEditorInfo;
        return editorInfo != null && ("com.oculus.vrshell".equals(editorInfo.packageName) || "com.oculus.shellenv".equals(this.mEditorInfo.packageName) || IME_OPTION_VRDESKTOP.equals(this.mEditorInfo.privateImeOptions));
    }
}
