package com.oculus.panelapp.androiddialog;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.androiddialog.databinding.CustomSystemDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.FacebookBlockDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.LocalStreamPrivacyCheckBinding;
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.SocialJoinPartyDialogBinding;
import com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseCastToBrowserPinDialog;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerDialog;
import com.oculus.panelapp.androiddialog.dialogs.filepreviewer.FilePreviewerDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerIntegrityDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.block.FacebookBlockDialog;
import com.oculus.panelapp.androiddialog.dialogs.load.LoadingContainer;
import com.oculus.panelapp.androiddialog.dialogs.localstream.LocalStreamPrivacyCheckDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteExperiment;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.SocialJoinPartyDialog;
import com.oculus.panelapp.androiddialog.logging.social.SocialLogger;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogDefinitionCommon;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidDialogPanelApp extends AndroidPanelApp implements OCEventHandler {
    private static final String CUSTOM_DIALOG_TYPE = "custom";
    private static final String IPC_COMMAND_AUTOMATION_COMMAND_DIALOG_ACTION = "dialogAction";
    private static final String IPC_COMMAND_AUTOMATION_COMMAND_DIALOG_QUERY = "dialogQuery";
    private static final String IPC_COMMAND_DIALOG_SHOW = "dialogShow";
    private static final String TAG = LoggingUtil.tag(AndroidDialogPanelApp.class);
    private final Application mApplication;
    @VisibleForTesting
    Dialog mCurrentDialog;
    @VisibleForTesting
    String mCurrentDialogHost;
    @VisibleForTesting
    String mCurrentDialogHostPackageName;
    @VisibleForTesting
    String mCurrentDialogHostServiceName;
    @VisibleForTesting
    String mCurrentDialogId;
    private long mCurrentDialogStartTime;
    private final DialogAnalytics mDialogAnalytics = new DialogAnalytics(this.mLoggingApi, $$Lambda$ZeIJeXXFMphBL5e8yybGR_cbRY.INSTANCE);
    private LoggingApi mLoggingApi = new LoggingApi(this);

    private native long nativeCreateInstance(long j, long j2);

    public AndroidDialogPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        this.mApplication = application;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unsupported layer " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        String str2 = TAG;
        Log.i(str2, "Inflating view for viewIdentifier = " + i);
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", new Object[0]));
    }

    public LoggingApi getLogger() {
        return this.mLoggingApi;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog == null || !dialog.onControllerBackButton()) {
            return true;
        }
        getCommandChannel().playAudio(SoundType.CLOSE);
        return true;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.destroy();
            this.mCurrentDialog = null;
        }
        super.destroy();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public String[] getLayersToAutoEnable() {
        return new String[0];
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonClick() {
        getCommandChannel().playAudio(SoundType.SELECT);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonEnter() {
        getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onBackButtonClick() {
        getCommandChannel().playAudio(SoundType.CLOSE);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class ParsedDialog {
        @Nullable
        CommonSystemDialog commonSystemDialog;
        final JSONObject dialogDefinition;
        final String dialogHost;
        final String dialogHostPackageName;
        final String dialogHostServiceName;
        final String dialogId;
        final boolean isCustomDialog;
        @Nullable
        JSONObject parameters;

        ParsedDialog(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, CommonSystemDialog commonSystemDialog2, boolean z) {
            this.dialogHost = str;
            this.dialogHostPackageName = str2;
            this.dialogHostServiceName = str3;
            this.dialogId = str4;
            this.dialogDefinition = jSONObject;
            this.parameters = jSONObject2;
            this.commonSystemDialog = commonSystemDialog2;
            this.isCustomDialog = z;
        }
    }

    @VisibleForTesting
    static ParsedDialog parseDialog(Scanner scanner) throws IllegalArgumentException {
        String next = scanner.next();
        String next2 = scanner.next();
        String next3 = scanner.next();
        String nextLine = scanner.nextLine();
        try {
            JSONObject jSONObject = new JSONObject(nextLine);
            String string = jSONObject.getString("dialogId");
            String string2 = jSONObject.getString("type");
            JSONObject optJSONObject = jSONObject.optJSONObject(DialogDefinitionCommon.DIALOG_PARAMETERS_KEY);
            CommonSystemDialog fromId = CommonSystemDialog.fromId(string);
            boolean equals = string2.equals("custom");
            if (equals || fromId != null) {
                return new ParsedDialog(next, next2, next3, string, jSONObject, optJSONObject, fromId, equals);
            }
            throw new IllegalArgumentException("Invalid custom dialog ID: " + string);
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Unable to parse JSON dialog definition: " + nextLine, e);
            throw new IllegalArgumentException("Invalid dialog definition: " + nextLine, e);
        }
    }

    private boolean handleShowDialog(Scanner scanner, String str) {
        AndroidPanelLayer.Spec spec;
        String str2 = TAG;
        Log.i(str2, "handleShowDialog command received: " + str);
        final ParsedDialog parseDialog = parseDialog(scanner);
        AnonymousClass1 r5 = new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.androiddialog.AndroidDialogPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                View view;
                if (parseDialog.isCustomDialog) {
                    view = AndroidDialogPanelApp.this.createMainLayerView(context, parseDialog.dialogDefinition);
                } else {
                    view = AndroidDialogPanelApp.this.createMainLayerView(context, parseDialog.commonSystemDialog, parseDialog.parameters);
                }
                if (view instanceof Dialog) {
                    AndroidDialogPanelApp.this.setCurrentDialog((Dialog) view, parseDialog.dialogHost, parseDialog.dialogId, parseDialog.dialogHostPackageName, parseDialog.dialogHostServiceName);
                    return view;
                }
                throw new RuntimeException(String.format("View for %s must be an instance of Dialog.", parseDialog.dialogId));
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return parseDialog.dialogId;
            }
        };
        if (parseDialog.isCustomDialog) {
            spec = getDefaultLayerSpec();
        } else {
            spec = getMainLayerSpec(parseDialog.commonSystemDialog);
        }
        attachResizeLayoutListener(ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, spec, r5), spec.resizeBehavior);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context, JSONObject jSONObject) {
        CustomSystemDialogBinding inflate = CustomSystemDialogBinding.inflate(LayoutInflater.from(context));
        CustomDialog customDialog = (CustomDialog) inflate.getRoot();
        customDialog.initialize(this, jSONObject, inflate);
        return customDialog;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context, CommonSystemDialog commonSystemDialog, JSONObject jSONObject) {
        switch (commonSystemDialog) {
            case ENTERPRISE_CAST_TO_BROWSER_PIN:
                EnterpriseCastToBrowserPinDialogBinding inflate = EnterpriseCastToBrowserPinDialogBinding.inflate(LayoutInflater.from(context));
                EnterpriseCastToBrowserPinDialog enterpriseCastToBrowserPinDialog = (EnterpriseCastToBrowserPinDialog) inflate.getRoot();
                enterpriseCastToBrowserPinDialog.initialize(this, inflate, jSONObject);
                return enterpriseCastToBrowserPinDialog;
            case ENTERPRISE_KIOSK_NOT_INSTALLED:
                EnterpriseKioskNotInstalledDialogBinding inflate2 = EnterpriseKioskNotInstalledDialogBinding.inflate(LayoutInflater.from(context));
                EnterpriseKioskNotInstalledDialog enterpriseKioskNotInstalledDialog = (EnterpriseKioskNotInstalledDialog) inflate2.getRoot();
                enterpriseKioskNotInstalledDialog.initialize(this, inflate2);
                return enterpriseKioskNotInstalledDialog;
            case FACEBOOK_BLOCK:
                FacebookBlockDialogBinding inflate3 = FacebookBlockDialogBinding.inflate(LayoutInflater.from(context));
                FacebookBlockDialog facebookBlockDialog = (FacebookBlockDialog) inflate3.getRoot();
                facebookBlockDialog.initialize(this.mApplication, this, inflate3, new SocialLogger(this), jSONObject);
                return facebookBlockDialog;
            case FILE_PICKER:
                FilePickerDialogBinding inflate4 = FilePickerDialogBinding.inflate(LayoutInflater.from(context));
                FilePickerDialog filePickerDialog = (FilePickerDialog) inflate4.getRoot();
                filePickerDialog.initialize(this, inflate4);
                return filePickerDialog;
            case FILE_PREVIEWER:
                FilePreviewerDialogBinding inflate5 = FilePreviewerDialogBinding.inflate(LayoutInflater.from(context));
                FilePreviewerDialog filePreviewerDialog = inflate5.filePreviewerDialog;
                filePreviewerDialog.initialize(this, inflate5);
                return filePreviewerDialog;
            case LOCAL_STREAM_PRIVACY_CHECK:
                LocalStreamPrivacyCheckBinding inflate6 = LocalStreamPrivacyCheckBinding.inflate(LayoutInflater.from(context));
                LocalStreamPrivacyCheckDialog localStreamPrivacyCheckDialog = (LocalStreamPrivacyCheckDialog) inflate6.getRoot();
                localStreamPrivacyCheckDialog.initialize(this, inflate6);
                return localStreamPrivacyCheckDialog;
            case MESSENGER_INTEGRITY:
                MessengerIntegrityDialogBinding inflate7 = MessengerIntegrityDialogBinding.inflate(LayoutInflater.from(context));
                MessengerIntegrityDialog messengerIntegrityDialog = (MessengerIntegrityDialog) inflate7.getRoot();
                messengerIntegrityDialog.initialize(this.mApplication, this, inflate7, new SocialLogger(this), jSONObject);
                return messengerIntegrityDialog;
            case SOCIAL_CREATE_VR_INVITE:
                SocialCreateVrInviteDialogBinding inflate8 = SocialCreateVrInviteDialogBinding.inflate(LayoutInflater.from(context));
                CreateVrInviteDialog createVrInviteDialog = (CreateVrInviteDialog) inflate8.getRoot();
                LoadingContainer wrap = LoadingContainer.wrap(createVrInviteDialog);
                createVrInviteDialog.initialize(this, inflate8, new CreateVrInviteRequestFactory(context), new SocialUserRequestFactory(context), new SocialLogger(this), new CreateVrInviteExperiment(this), wrap, jSONObject);
                wrap.waitFor(createVrInviteDialog.getObservablesForLoading());
                return wrap;
            case SOCIAL_CONFIRM_JOIN_PARTY:
                SocialJoinPartyDialogBinding inflate9 = SocialJoinPartyDialogBinding.inflate(LayoutInflater.from(context));
                SocialJoinPartyDialog socialJoinPartyDialog = (SocialJoinPartyDialog) inflate9.getRoot();
                LoadingContainer wrap2 = LoadingContainer.wrap(socialJoinPartyDialog);
                socialJoinPartyDialog.initialize(this, inflate9, new PartyInviteRequestFactory(context), new SocialUserRequestFactory(context), wrap2, new SocialLogger(this), jSONObject);
                wrap2.waitFor(inflate9.getPartyViewModel(), inflate9.getViewerViewModel());
                return wrap2;
            case SOCIAL_PARTY_PRIVACY:
                SocialPartyPrivacyDialogBinding inflate10 = SocialPartyPrivacyDialogBinding.inflate(LayoutInflater.from(context));
                SocialPartyPrivacyDialog socialPartyPrivacyDialog = (SocialPartyPrivacyDialog) inflate10.getRoot();
                LoadingContainer wrap3 = LoadingContainer.wrap(socialPartyPrivacyDialog);
                socialPartyPrivacyDialog.initialize(this, inflate10, new SocialLogger(this), wrap3, jSONObject.optString("party_id"), new SocialPartyPrivacyRequestFactory(context));
                wrap3.waitFor(inflate10.getPrivacyViewModel());
                return wrap3;
            default:
                throw new RuntimeException(String.format("Dialog %s is not handled by Android dialog panel app.", commonSystemDialog.getDialogId()));
        }
    }

    private static AndroidPanelLayer.Spec getMainLayerSpec(CommonSystemDialog commonSystemDialog) {
        int i = AnonymousClass2.$SwitchMap$com$oculus$systemdialog$CommonSystemDialog[commonSystemDialog.ordinal()];
        if (i == 4 || i == 5) {
            return new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 780, 480, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme);
        }
        return getDefaultLayerSpec();
    }

    private static AndroidPanelLayer.Spec getDefaultLayerSpec() {
        return new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 512, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentDialog(Dialog dialog, String str, String str2, String str3, String str4) {
        Dialog dialog2 = this.mCurrentDialog;
        if (dialog2 != null) {
            dialog2.destroy();
            this.mCurrentDialog = null;
        }
        this.mCurrentDialog = dialog;
        this.mCurrentDialogHost = str;
        this.mCurrentDialogId = str2;
        this.mCurrentDialogHostPackageName = str3;
        this.mCurrentDialogHostServiceName = str4;
        this.mCurrentDialogStartTime = SystemClock.elapsedRealtime();
        this.mDialogAnalytics.logDialogShown(str2, str, this.mCurrentDialogHostPackageName, this.mCurrentDialogHostServiceName);
    }

    public void sendResult(JSONObject jSONObject) {
        String str = this.mCurrentDialogHost;
        if (str == null) {
            Log.e(TAG, "Unable to close dialog, no dialog host specified.");
        } else if (this.mCurrentDialogId == null) {
            Log.e(TAG, "Unable to close dialog, no dialog ID specified.");
        } else {
            try {
                jSONObject.put("dialogHost", str);
                jSONObject.put("dialogId", this.mCurrentDialogId);
                getCommandChannel().sendCommand(String.format("systemDialogResult %s", jSONObject.toString()));
                this.mDialogAnalytics.logDialogAction(this.mCurrentDialogId, this.mCurrentDialogHost, jSONObject.getString("action"), this.mCurrentDialogHostPackageName, this.mCurrentDialogHostServiceName, this.mCurrentDialogStartTime);
            } catch (JSONException e) {
                Log.e(TAG, "Unable to send dialog result, cannot format dialog result JSON.", e);
            }
        }
    }

    public void sendResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", str);
            sendResult(jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, "Unable to send dialog action, cannot format dialog result JSON.", e);
        }
    }

    public void closeDialog() {
        sendResult("close");
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        String path = systemUXRoute.path();
        String str2 = TAG;
        Log.i(str2, "actionNavigate - App:  " + path + ", Uri:  " + str);
        getCommandChannel().launch(path, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public boolean handleGenericMessage(String str) {
        boolean handleShowDialog;
        Scanner scanner = new Scanner(str);
        try {
            String next = scanner.next();
            char c = 65535;
            if (next.hashCode() == -1385076635) {
                if (next.equals(IPC_COMMAND_DIALOG_SHOW)) {
                    c = 0;
                }
            }
            if (c != 0) {
                handleShowDialog = super.handleGenericMessage(str);
            } else {
                handleShowDialog = handleShowDialog(scanner, str);
            }
            return handleShowDialog;
        } catch (InputMismatchException e) {
            Log.e(TAG, "Received Invalid response: " + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String handleAutomationMessageCommand(org.json.JSONObject r5) throws org.json.JSONException {
        /*
            r4 = this;
            java.lang.String r0 = "commandName"
            java.lang.String r0 = r5.getString(r0)
            int r1 = r0.hashCode()
            r2 = -138587746(0xfffffffff7bd519e, float:-7.6796836E33)
            r3 = 1
            if (r1 == r2) goto L_0x0020
            r2 = 10827872(0xa53860, float:1.517308E-38)
            if (r1 == r2) goto L_0x0016
            goto L_0x002a
        L_0x0016:
            java.lang.String r1 = "dialogQuery"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002a
            r0 = r3
            goto L_0x002b
        L_0x0020:
            java.lang.String r1 = "dialogAction"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002a
            r0 = 0
            goto L_0x002b
        L_0x002a:
            r0 = -1
        L_0x002b:
            if (r0 == 0) goto L_0x0039
            if (r0 == r3) goto L_0x0034
            java.lang.String r5 = super.handleAutomationMessageCommand(r5)
            return r5
        L_0x0034:
            java.lang.String r5 = r4.handleAutomationDialogQueryCommand(r5)
            return r5
        L_0x0039:
            java.lang.String r5 = r4.handleAutomationDialogActionCommand(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.AndroidDialogPanelApp.handleAutomationMessageCommand(org.json.JSONObject):java.lang.String");
    }

    public boolean getDeviceConfig(Gatekeeper gatekeeper) {
        return DeviceConfigHelper.getBoolean(getContext(), gatekeeper);
    }

    private String handleAutomationDialogActionCommand(JSONObject jSONObject) throws JSONException {
        return DialogAutomation.getDialogActionResponse(this.mCurrentDialog, jSONObject);
    }

    private String handleAutomationDialogQueryCommand(JSONObject jSONObject) {
        return DialogAutomation.getDialogQueryResponse(this.mCurrentDialog);
    }
}
