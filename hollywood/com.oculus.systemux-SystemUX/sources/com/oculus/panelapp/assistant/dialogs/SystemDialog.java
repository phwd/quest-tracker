package com.oculus.panelapp.assistant.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.config.BroadcastConfig;
import com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.systemdialog.DialogVideo;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemDialog implements DialogResultHandler, IAssistantDialog {
    private AssistantPanelApp mApp;
    private boolean mAutoClose;
    private boolean mAutoClosePrimary;
    private boolean mAutoCloseSecondary;
    private boolean mAutoCloseTertiary;
    private BroadcastConfig mBroadcastConfig = new BroadcastConfig();
    private DialogDefinitionCustom mDialog;
    private final String mId;
    private boolean mRemoveOnHide;
    private long mVisibleDuration;

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public int getHeight() {
        return 0;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void getResultData(Bundle bundle) {
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceType() {
        return AssistantDialogContract.DialogTypes.SYSTEM_DIALOG;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public int getWidth() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCreateDialog(DialogDefinitionCustom dialogDefinitionCustom) {
    }

    public SystemDialog(AssistantPanelApp assistantPanelApp, String str, int i, int i2) {
        this.mApp = assistantPanelApp;
        this.mId = str;
        Resources resources = assistantPanelApp.getContext().getResources();
        this.mDialog = new DialogDefinitionCustom(str, resources.getString(i), resources.getString(i2));
        this.mDialog.setDialogResultHandler(this);
        onCreateDialog(this.mDialog);
    }

    public SystemDialog(AssistantPanelApp assistantPanelApp, String str, String str2, String str3) {
        this.mApp = assistantPanelApp;
        this.mId = str;
        this.mDialog = new DialogDefinitionCustom(str, str2, str3);
        this.mDialog.setDialogResultHandler(this);
        onCreateDialog(this.mDialog);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public long getVisibleDuration() {
        return this.mVisibleDuration;
    }

    public AssistantPanelApp getPanelApp() {
        return this.mApp;
    }

    public Context getContext() {
        return this.mApp.getContext();
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public String getSurfaceId() {
        return this.mId;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public AssistantPanelApp getApp() {
        return this.mApp;
    }

    public DialogDefinitionCustom getDialog() {
        return this.mDialog;
    }

    /* access modifiers changed from: protected */
    public void setHeroImageUri(String str) {
        this.mDialog.setHeroImage(new DialogHeroImage(str, 1.778f, SocialCreatePartyPreviewDialog.HERO_IMAGE_BACKGROUND_COLOR));
    }

    /* access modifiers changed from: protected */
    public void setHeroImage(String str) {
        setHeroImageUri("apk://" + getContext().getPackageName() + "/assets/" + str);
    }

    /* access modifiers changed from: protected */
    public void setVideo(String str) {
        this.mDialog.setVideo(new DialogVideo(str, 1.778f, SocialCreatePartyPreviewDialog.HERO_IMAGE_BACKGROUND_COLOR));
    }

    /* access modifiers changed from: protected */
    public void setInformationBox(String str) {
        this.mDialog.setInformationBox(new DialogInformationBox(str));
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void setBackButtonEnabled(boolean z) {
        if (z) {
            this.mDialog.setBackButton(new DialogButton("back"));
        } else {
            this.mDialog.setBackButton(null);
        }
    }

    private String getString(int i) {
        return getContext().getResources().getString(i);
    }

    private static DialogButtonText createButton(String str, String str2, boolean z) {
        DialogButtonText dialogButtonText = new DialogButtonText(str, str2);
        if (!z) {
            dialogButtonText.setDoesNotAutoClose();
        }
        return dialogButtonText;
    }

    public void setPrimaryButton(DialogButtonText dialogButtonText) {
        this.mDialog.setPrimaryButton(dialogButtonText);
    }

    public void setSecondaryButton(DialogButtonText dialogButtonText) {
        this.mDialog.setSecondaryButton(dialogButtonText);
    }

    public void setTertiaryButton(DialogButtonText dialogButtonText) {
        this.mDialog.setTertiaryButton(dialogButtonText);
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void show() {
        this.mApp.getDialogManager().showDialog(getDialog());
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public void hide(Runnable runnable) {
        this.mApp.getDialogManager().hideDialog();
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.oculus.systemdialog.DialogResultHandler
    public boolean handleDialogResult(DialogResult dialogResult) {
        return onDialogResult(dialogResult.getDialogAction());
    }

    @Override // com.oculus.panelapp.assistant.dialogs.ISurface
    public BroadcastConfig getBroadcastConfig() {
        return this.mBroadcastConfig;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onDialogResult(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1174796206(0xffffffffb9fa0852, float:-4.7689915E-4)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = -817598092(0xffffffffcf447174, float:-3.2957696E9)
            if (r0 == r1) goto L_0x0020
            r1 = -314765822(0xffffffffed3d0e02, float:-3.65685E27)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "primary"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "secondary"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = r3
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "tertiary"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = r2
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            r1 = 0
            if (r0 == 0) goto L_0x0054
            if (r0 == r3) goto L_0x004c
            if (r0 == r2) goto L_0x0044
            boolean r0 = r4.mAutoClose
            if (r0 == 0) goto L_0x005b
            r4.hide(r1)
            goto L_0x005b
        L_0x0044:
            boolean r0 = r4.mAutoCloseTertiary
            if (r0 == 0) goto L_0x005b
            r4.hide(r1)
            goto L_0x005b
        L_0x004c:
            boolean r0 = r4.mAutoCloseSecondary
            if (r0 == 0) goto L_0x005b
            r4.hide(r1)
            goto L_0x005b
        L_0x0054:
            boolean r0 = r4.mAutoClosePrimary
            if (r0 == 0) goto L_0x005b
            r4.hide(r1)
        L_0x005b:
            com.oculus.panelapp.assistant.AssistantPanelApp r0 = r4.mApp
            com.oculus.panelapp.assistant.config.BroadcastConfig r2 = r4.mBroadcastConfig
            boolean r5 = r0.onDialogResult(r4, r2, r5, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.dialogs.SystemDialog.onDialogResult(java.lang.String):boolean");
    }

    public static SystemDialog createDialog(AssistantPanelApp assistantPanelApp, JSONObject jSONObject) throws JSONException {
        SystemDialog systemDialog = new SystemDialog(assistantPanelApp, jSONObject.optString("id", UUID.randomUUID().toString()), jSONObject.optString("title", ""), jSONObject.optString(AssistantDialogContract.Dialog.DESCRIPTION, ""));
        systemDialog.mBroadcastConfig.applyJson(jSONObject);
        systemDialog.mVisibleDuration = jSONObject.optLong(AttentionSystemContract.KEY_DURATION, 0);
        systemDialog.mAutoClose = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE, true);
        systemDialog.mAutoClosePrimary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_PRIMARY, systemDialog.mAutoClose);
        systemDialog.mAutoCloseSecondary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_PRIMARY, systemDialog.mAutoClose);
        systemDialog.mAutoCloseTertiary = jSONObject.optBoolean(AssistantDialogContract.Dialog.Buttons.AUTO_CLOSE_PRIMARY, systemDialog.mAutoClose);
        if (jSONObject.has("primary")) {
            systemDialog.setPrimaryButton(createButton("primary", jSONObject.getString("primary"), systemDialog.mAutoClosePrimary));
        }
        if (jSONObject.has("secondary")) {
            systemDialog.setSecondaryButton(createButton("secondary", jSONObject.getString("secondary"), systemDialog.mAutoCloseSecondary));
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY)) {
            systemDialog.setTertiaryButton(createButton(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY, jSONObject.getString(AssistantDialogContract.Dialog.Buttons.ACTION_TERTIARY), systemDialog.mAutoCloseTertiary));
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.HERO)) {
            systemDialog.setHeroImageUri(jSONObject.getString(AssistantDialogContract.Dialog.HERO));
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.VIDEO)) {
            systemDialog.setVideo(jSONObject.getString(AssistantDialogContract.Dialog.VIDEO));
        }
        if (jSONObject.has(AssistantDialogContract.Dialog.INFORMATION_BOX)) {
            systemDialog.setInformationBox(jSONObject.getString(AssistantDialogContract.Dialog.INFORMATION_BOX));
        }
        systemDialog.setBackButtonEnabled(jSONObject.optBoolean("back", false));
        systemDialog.setRemoveOnHide(jSONObject.optBoolean(AssistantDialogContract.Dialog.REMOVE_ON_HIDE));
        return systemDialog;
    }

    private void setRemoveOnHide(boolean z) {
        this.mRemoveOnHide = z;
    }

    @Override // com.oculus.panelapp.assistant.dialogs.IAssistantDialog
    public boolean shouldRemoveOnHide() {
        return this.mRemoveOnHide;
    }
}
