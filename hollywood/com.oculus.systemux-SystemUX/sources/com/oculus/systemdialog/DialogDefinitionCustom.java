package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogDefinitionCustom extends DialogDefinitionBase {
    private static final String DIALOG_AUTO_ACTION_KEY = "autoAction";
    public static final String DIALOG_BACK_BUTTON_KEY = "backButton";
    public static final String DIALOG_BODY_KEY = "body";
    public static final String DIALOG_CONTROLLER_BACK_BUTTON_KEY = "controllerBackButton";
    public static final String DIALOG_HERO_SPACE_KEY = "heroSpace";
    public static final String DIALOG_ICON_BUTTON_KEY = "iconButton";
    public static final String DIALOG_INFORMATION_BOX_KEY = "informationBox";
    public static final String DIALOG_INLINE_IMAGES_KEY = "inlineImages";
    public static final String DIALOG_LIST_KEY = "list";
    public static final String DIALOG_PRIMARY_BUTTON_KEY = "primaryButton";
    private static final String DIALOG_PROGRESS_INDICATOR_KEY = "progressIndicator";
    public static final String DIALOG_SECONDARY_BUTTON_KEY = "secondaryButton";
    public static final String DIALOG_TERTIARY_BUTTON_KEY = "tertiaryButton";
    public static final String DIALOG_TITLE_KEY = "title";
    private static final String TAG = LoggingUtil.tag(DialogDefinitionCustom.class);
    private AutoAction mAutoAction;
    private DialogButton mDialogBackButton;
    private String mDialogBody;
    private DialogButton mDialogControllerBackButton;
    private DialogHeroSpace mDialogHeroSpace;
    private DialogButtonIcon mDialogIconButton;
    private DialogInformationBox mDialogInformationBox;
    private List<DialogInlineImage> mDialogInlineImages;
    private DialogList mDialogList;
    private DialogButtonText mDialogPrimaryButton;
    private DialogProgressIndicator mDialogProgressIndicator;
    private DialogButtonText mDialogSecondaryButton;
    private DialogButtonText mDialogTertiaryButton;
    private String mDialogTitle;

    public DialogDefinitionCustom(String str, String str2, String str3) {
        super(str);
        this.mDialogTitle = str2;
        this.mDialogBody = str3;
    }

    public String getTitle() {
        return this.mDialogTitle;
    }

    public DialogDefinitionCustom setTitle(String str) {
        this.mDialogTitle = str;
        return this;
    }

    public String getBody() {
        return this.mDialogBody;
    }

    public DialogDefinitionCustom setBody(String str) {
        this.mDialogBody = str;
        return this;
    }

    @Nullable
    public DialogInformationBox getInformationBox() {
        return this.mDialogInformationBox;
    }

    public DialogDefinitionCustom setInformationBox(DialogInformationBox dialogInformationBox) {
        this.mDialogInformationBox = dialogInformationBox;
        return this;
    }

    public DialogDefinitionCustom setList(DialogList dialogList) {
        this.mDialogList = dialogList;
        return this;
    }

    @Nullable
    public DialogHeroSpace getHeroSpace() {
        return this.mDialogHeroSpace;
    }

    public DialogDefinitionCustom setHeroSpace(DialogHeroSpace dialogHeroSpace) {
        this.mDialogHeroSpace = dialogHeroSpace;
        return this;
    }

    public DialogDefinitionCustom setVideo(DialogVideo dialogVideo) {
        this.mDialogHeroSpace = dialogVideo.getDialogHeroSpace();
        return this;
    }

    public DialogDefinitionCustom setHeroImage(DialogHeroImage dialogHeroImage) {
        this.mDialogHeroSpace = dialogHeroImage.getDialogHeroSpace();
        return this;
    }

    @Nullable
    public DialogProgressIndicator getProgressIndicator() {
        return this.mDialogProgressIndicator;
    }

    public DialogDefinitionCustom setProgressBar(float f) {
        this.mDialogProgressIndicator = DialogProgressIndicator.Bar(f);
        return this;
    }

    public DialogDefinitionCustom setProgressSpinner() {
        this.mDialogProgressIndicator = DialogProgressIndicator.Spinner();
        return this;
    }

    @Nullable
    public DialogButtonText getPrimaryButton() {
        return this.mDialogPrimaryButton;
    }

    public DialogDefinitionCustom setPrimaryButton(DialogButtonText dialogButtonText) {
        this.mDialogPrimaryButton = dialogButtonText;
        return this;
    }

    @Nullable
    public String getPrimaryAction() {
        DialogButtonText dialogButtonText = this.mDialogPrimaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.getButtonAction();
        }
        return null;
    }

    @Nullable
    public DialogButtonText getSecondaryButton() {
        return this.mDialogSecondaryButton;
    }

    public DialogDefinitionCustom setSecondaryButton(DialogButtonText dialogButtonText) {
        this.mDialogSecondaryButton = dialogButtonText;
        return this;
    }

    @Nullable
    public String getSecondaryAction() {
        DialogButtonText dialogButtonText = this.mDialogSecondaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.getButtonAction();
        }
        return null;
    }

    @Nullable
    public DialogButtonText getTertiaryButton() {
        return this.mDialogTertiaryButton;
    }

    public DialogDefinitionCustom setTertiaryButton(DialogButtonText dialogButtonText) {
        this.mDialogTertiaryButton = dialogButtonText;
        return this;
    }

    @Nullable
    public String getTertiaryAction() {
        DialogButtonText dialogButtonText = this.mDialogTertiaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.getButtonAction();
        }
        return null;
    }

    @Nullable
    public DialogButtonIcon getIconButton() {
        return this.mDialogIconButton;
    }

    public DialogDefinitionCustom setIconButton(DialogButtonIcon dialogButtonIcon) {
        this.mDialogIconButton = dialogButtonIcon;
        return this;
    }

    @Nullable
    public DialogButton getBackButton() {
        return this.mDialogBackButton;
    }

    public DialogDefinitionCustom setBackButton(DialogButton dialogButton) {
        this.mDialogBackButton = dialogButton;
        return this;
    }

    public DialogDefinitionCustom setControllerBackButton(DialogButton dialogButton) {
        this.mDialogControllerBackButton = dialogButton;
        return this;
    }

    public DialogDefinitionCustom addInlineImage(DialogInlineImage dialogInlineImage) {
        if (dialogInlineImage == null) {
            Log.w(TAG, "Dialog inline image must be non-null.");
            return this;
        }
        if (this.mDialogInlineImages == null) {
            this.mDialogInlineImages = new ArrayList();
        }
        this.mDialogInlineImages.add(dialogInlineImage);
        return this;
    }

    public DialogDefinitionCustom setAutoAction(String str, int i) {
        this.mAutoAction = new AutoAction(str, i);
        return this;
    }

    public JSONObject getDialogConfigurationJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", DialogDefinitionBase.DIALOG_TYPE_CUSTOM_VALUE);
        jSONObject.put("dialogId", this.mDialogId);
        jSONObject.put("title", this.mDialogTitle);
        jSONObject.put("body", this.mDialogBody);
        DialogInformationBox dialogInformationBox = this.mDialogInformationBox;
        if (dialogInformationBox != null) {
            jSONObject.put(DIALOG_INFORMATION_BOX_KEY, dialogInformationBox.getDialogInformationBoxConfigurationIPCParcel());
        }
        DialogList dialogList = this.mDialogList;
        if (dialogList != null) {
            jSONObject.put(DIALOG_LIST_KEY, dialogList.getDialogListConfigurationIPCParcel());
        }
        DialogHeroSpace dialogHeroSpace = this.mDialogHeroSpace;
        if (dialogHeroSpace != null) {
            jSONObject.put(DIALOG_HERO_SPACE_KEY, dialogHeroSpace.getDialogHeroSpaceConfigurationIPCParcel());
        }
        DialogButtonText dialogButtonText = this.mDialogPrimaryButton;
        if (dialogButtonText != null) {
            jSONObject.put(DIALOG_PRIMARY_BUTTON_KEY, dialogButtonText.getDialogButtonConfigurationIPCParcel());
        }
        DialogButtonText dialogButtonText2 = this.mDialogSecondaryButton;
        if (dialogButtonText2 != null) {
            jSONObject.put(DIALOG_SECONDARY_BUTTON_KEY, dialogButtonText2.getDialogButtonConfigurationIPCParcel());
        }
        DialogButtonText dialogButtonText3 = this.mDialogTertiaryButton;
        if (dialogButtonText3 != null) {
            jSONObject.put(DIALOG_TERTIARY_BUTTON_KEY, dialogButtonText3.getDialogButtonConfigurationIPCParcel());
        }
        DialogButtonIcon dialogButtonIcon = this.mDialogIconButton;
        if (dialogButtonIcon != null) {
            jSONObject.put(DIALOG_ICON_BUTTON_KEY, dialogButtonIcon.getDialogButtonConfigurationIPCParcel());
        }
        DialogButton dialogButton = this.mDialogBackButton;
        if (dialogButton != null) {
            jSONObject.put(DIALOG_BACK_BUTTON_KEY, dialogButton.getDialogButtonConfigurationIPCParcel());
        }
        DialogButton dialogButton2 = this.mDialogControllerBackButton;
        if (dialogButton2 != null) {
            jSONObject.put(DIALOG_CONTROLLER_BACK_BUTTON_KEY, dialogButton2.getDialogButtonConfigurationIPCParcel());
        }
        if (this.mDialogInlineImages != null) {
            JSONArray jSONArray = new JSONArray();
            for (DialogInlineImage dialogInlineImage : this.mDialogInlineImages) {
                jSONArray.put(dialogInlineImage.getDialogInlineImageConfigurationIPCParcel());
            }
            jSONObject.put(DIALOG_INLINE_IMAGES_KEY, jSONArray);
        }
        DialogProgressIndicator dialogProgressIndicator = this.mDialogProgressIndicator;
        if (dialogProgressIndicator != null) {
            jSONObject.put(DIALOG_PROGRESS_INDICATOR_KEY, dialogProgressIndicator.getDialogProgressIndicatorConfigurationIPCParcel());
        }
        AutoAction autoAction = this.mAutoAction;
        if (autoAction != null) {
            jSONObject.put(DIALOG_AUTO_ACTION_KEY, autoAction.getAutoActionConfigurationIPCParcel());
        }
        return jSONObject;
    }

    @Override // com.oculus.systemdialog.DialogDefinitionBase
    public String getDialogConfigurationIPCParcel() {
        try {
            return getDialogConfigurationJson().toString();
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog configuration JSON.", e);
            return "";
        }
    }
}
