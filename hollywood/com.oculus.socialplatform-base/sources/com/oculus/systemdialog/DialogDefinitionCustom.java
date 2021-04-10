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
    public static final String DIALOG_AUTO_ACTION_KEY = "autoAction";
    public static final String DIALOG_BACK_BUTTON_KEY = "backButton";
    public static final String DIALOG_BODY_KEY = "body";
    public static final String DIALOG_CONTROLLER_BACK_BUTTON_KEY = "controllerBackButton";
    public static final String DIALOG_HERO_SPACE_KEY = "heroSpace";
    public static final String DIALOG_ICON_BUTTON_KEY = "iconButton";
    public static final String DIALOG_INFORMATION_BOX_KEY = "informationBox";
    public static final String DIALOG_INLINE_IMAGES_KEY = "inlineImages";
    public static final String DIALOG_LIST_KEY = "list";
    public static final String DIALOG_PRIMARY_BUTTON_KEY = "primaryButton";
    public static final String DIALOG_PROGRESS_INDICATOR_KEY = "progressIndicator";
    public static final String DIALOG_SECONDARY_BUTTON_KEY = "secondaryButton";
    public static final String DIALOG_TERTIARY_BUTTON_KEY = "tertiaryButton";
    public static final String DIALOG_TITLE_KEY = "title";
    public static final String TAG = LoggingUtil.tag(DialogDefinitionCustom.class);
    public AutoAction mAutoAction;
    public DialogButton mDialogBackButton;
    public String mDialogBody;
    public DialogButton mDialogControllerBackButton;
    public DialogHeroSpace mDialogHeroSpace;
    public DialogButtonIcon mDialogIconButton;
    public DialogInformationBox mDialogInformationBox;
    public List<DialogInlineImage> mDialogInlineImages;
    public DialogList mDialogList;
    public DialogButtonText mDialogPrimaryButton;
    public DialogProgressIndicator mDialogProgressIndicator;
    public DialogButtonText mDialogSecondaryButton;
    public DialogButtonText mDialogTertiaryButton;
    public String mDialogTitle;

    public DialogDefinitionCustom addInlineImage(DialogInlineImage dialogInlineImage) {
        if (dialogInlineImage == null) {
            Log.w(TAG, "Dialog inline image must be non-null.");
            return this;
        }
        List list = this.mDialogInlineImages;
        if (list == null) {
            list = new ArrayList();
            this.mDialogInlineImages = list;
        }
        list.add(dialogInlineImage);
        return this;
    }

    @Nullable
    public AutoAction getAutoAction() {
        return this.mAutoAction;
    }

    @Nullable
    public DialogButton getBackButton() {
        return this.mDialogBackButton;
    }

    public String getBody() {
        return this.mDialogBody;
    }

    public JSONObject getDialogConfigurationJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "custom");
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

    @Nullable
    public DialogHeroSpace getHeroSpace() {
        return this.mDialogHeroSpace;
    }

    @Nullable
    public DialogButtonIcon getIconButton() {
        return this.mDialogIconButton;
    }

    @Nullable
    public DialogInformationBox getInformationBox() {
        return this.mDialogInformationBox;
    }

    @Nullable
    public DialogList getList() {
        return this.mDialogList;
    }

    @Nullable
    public String getPrimaryAction() {
        DialogButtonText dialogButtonText = this.mDialogPrimaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.mButtonAction;
        }
        return null;
    }

    @Nullable
    public DialogButtonText getPrimaryButton() {
        return this.mDialogPrimaryButton;
    }

    @Nullable
    public DialogProgressIndicator getProgressIndicator() {
        return this.mDialogProgressIndicator;
    }

    @Nullable
    public String getSecondaryAction() {
        DialogButtonText dialogButtonText = this.mDialogSecondaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.mButtonAction;
        }
        return null;
    }

    @Nullable
    public DialogButtonText getSecondaryButton() {
        return this.mDialogSecondaryButton;
    }

    @Nullable
    public String getTertiaryAction() {
        DialogButtonText dialogButtonText = this.mDialogTertiaryButton;
        if (dialogButtonText != null) {
            return dialogButtonText.mButtonAction;
        }
        return null;
    }

    @Nullable
    public DialogButtonText getTertiaryButton() {
        return this.mDialogTertiaryButton;
    }

    public String getTitle() {
        return this.mDialogTitle;
    }

    public DialogDefinitionCustom setAutoAction(String str, int i) {
        this.mAutoAction = new AutoAction(str, i);
        return this;
    }

    public DialogDefinitionCustom setHeroImage(DialogHeroImage dialogHeroImage) {
        this.mDialogHeroSpace = dialogHeroImage.mDialogHeroSpace;
        return this;
    }

    public DialogDefinitionCustom setVideo(DialogVideo dialogVideo) {
        this.mDialogHeroSpace = dialogVideo.mDialogHeroSpace;
        return this;
    }

    public DialogDefinitionCustom(String str, String str2, String str3) {
        super(str);
        this.mDialogTitle = str2;
        this.mDialogBody = str3;
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

    public DialogDefinitionCustom setProgressBar(float f) {
        this.mDialogProgressIndicator = DialogProgressIndicator.Bar(f);
        return this;
    }

    public DialogDefinitionCustom setProgressSpinner() {
        this.mDialogProgressIndicator = DialogProgressIndicator.Spinner();
        return this;
    }

    public DialogDefinitionCustom setBackButton(DialogButton dialogButton) {
        this.mDialogBackButton = dialogButton;
        return this;
    }

    public DialogDefinitionCustom setBody(String str) {
        this.mDialogBody = str;
        return this;
    }

    public DialogDefinitionCustom setControllerBackButton(DialogButton dialogButton) {
        this.mDialogControllerBackButton = dialogButton;
        return this;
    }

    public DialogDefinitionCustom setHeroSpace(DialogHeroSpace dialogHeroSpace) {
        this.mDialogHeroSpace = dialogHeroSpace;
        return this;
    }

    public DialogDefinitionCustom setIconButton(DialogButtonIcon dialogButtonIcon) {
        this.mDialogIconButton = dialogButtonIcon;
        return this;
    }

    public DialogDefinitionCustom setInformationBox(DialogInformationBox dialogInformationBox) {
        this.mDialogInformationBox = dialogInformationBox;
        return this;
    }

    public DialogDefinitionCustom setList(DialogList dialogList) {
        this.mDialogList = dialogList;
        return this;
    }

    public DialogDefinitionCustom setPrimaryButton(DialogButtonText dialogButtonText) {
        this.mDialogPrimaryButton = dialogButtonText;
        return this;
    }

    public DialogDefinitionCustom setSecondaryButton(DialogButtonText dialogButtonText) {
        this.mDialogSecondaryButton = dialogButtonText;
        return this;
    }

    public DialogDefinitionCustom setTertiaryButton(DialogButtonText dialogButtonText) {
        this.mDialogTertiaryButton = dialogButtonText;
        return this;
    }

    public DialogDefinitionCustom setTitle(String str) {
        this.mDialogTitle = str;
        return this;
    }
}
