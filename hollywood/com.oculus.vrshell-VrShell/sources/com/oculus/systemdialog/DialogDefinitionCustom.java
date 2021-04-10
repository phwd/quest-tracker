package com.oculus.systemdialog;

import android.util.Log;
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

    public DialogDefinitionCustom setTitle(String str) {
        this.mDialogTitle = str;
        return this;
    }

    public DialogDefinitionCustom setBody(String str) {
        this.mDialogBody = str;
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

    public DialogDefinitionCustom setProgressBar(float f) {
        this.mDialogProgressIndicator = DialogProgressIndicator.Bar(f);
        return this;
    }

    public DialogDefinitionCustom setProgressSpinner() {
        this.mDialogProgressIndicator = DialogProgressIndicator.Spinner();
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

    public DialogDefinitionCustom setIconButton(DialogButtonIcon dialogButtonIcon) {
        this.mDialogIconButton = dialogButtonIcon;
        return this;
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

    @Override // com.oculus.systemdialog.DialogDefinitionBase
    public String getDialogConfigurationIPCParcel() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DialogDefinitionBase.DIALOG_TYPE_KEY, DialogDefinitionBase.DIALOG_TYPE_CUSTOM_VALUE);
            jSONObject.put("dialogId", this.mDialogId);
            jSONObject.put("title", this.mDialogTitle);
            jSONObject.put("body", this.mDialogBody);
            if (this.mDialogInformationBox != null) {
                jSONObject.put(DIALOG_INFORMATION_BOX_KEY, this.mDialogInformationBox.getDialogInformationBoxConfigurationIPCParcel());
            }
            if (this.mDialogList != null) {
                jSONObject.put(DIALOG_LIST_KEY, this.mDialogList.getDialogListConfigurationIPCParcel());
            }
            if (this.mDialogHeroSpace != null) {
                jSONObject.put(DIALOG_HERO_SPACE_KEY, this.mDialogHeroSpace.getDialogHeroSpaceConfigurationIPCParcel());
            }
            if (this.mDialogPrimaryButton != null) {
                jSONObject.put(DIALOG_PRIMARY_BUTTON_KEY, this.mDialogPrimaryButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogSecondaryButton != null) {
                jSONObject.put(DIALOG_SECONDARY_BUTTON_KEY, this.mDialogSecondaryButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogTertiaryButton != null) {
                jSONObject.put(DIALOG_TERTIARY_BUTTON_KEY, this.mDialogTertiaryButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogIconButton != null) {
                jSONObject.put(DIALOG_ICON_BUTTON_KEY, this.mDialogIconButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogBackButton != null) {
                jSONObject.put(DIALOG_BACK_BUTTON_KEY, this.mDialogBackButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogControllerBackButton != null) {
                jSONObject.put(DIALOG_CONTROLLER_BACK_BUTTON_KEY, this.mDialogControllerBackButton.getDialogButtonConfigurationIPCParcel());
            }
            if (this.mDialogInlineImages != null) {
                JSONArray jSONArray = new JSONArray();
                for (DialogInlineImage dialogInlineImage : this.mDialogInlineImages) {
                    jSONArray.put(dialogInlineImage.getDialogInlineImageConfigurationIPCParcel());
                }
                jSONObject.put(DIALOG_INLINE_IMAGES_KEY, jSONArray);
            }
            if (this.mDialogProgressIndicator != null) {
                jSONObject.put(DIALOG_PROGRESS_INDICATOR_KEY, this.mDialogProgressIndicator.getDialogProgressIndicatorConfigurationIPCParcel());
            }
            if (this.mAutoAction != null) {
                jSONObject.put(DIALOG_AUTO_ACTION_KEY, this.mAutoAction.getAutoActionConfigurationIPCParcel());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog configuration JSON.", e);
            return "";
        }
    }
}
