package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DialogsModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "DialogsModule";

    public enum CommonDialogID {
        common_system_dialog_app_launch_blocked_controller_required,
        common_system_dialog_app_launch_blocked_hands_required,
        common_system_dialog_guardian_adjust
    }

    public static class DialogButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;

        public static final DialogButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogButton dialogButton = new DialogButton();
            dialogButton.setFromJSONObject(jSONObject);
            return dialogButton;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                Boolean bool = this.autoClose;
                if (bool != null) {
                    jSONObject.put("autoClose", bool);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            this.action = jSONObject.optString("action");
            if (jSONObject.isNull("autoClose")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            }
            this.autoClose = valueOf;
        }
    }

    public static class DialogConfig extends NativeModuleParcel {
        public DialogButton backButton;
        public String body;
        public DialogButton controllerBackButton;
        public String dialogId;
        public DialogHeroSpace heroSpace;
        public DialogIconButton iconButton;
        public DialogInformationBox informationBox;
        public List<DialogInlineImage> inlineImages;
        public DialogList list;
        public DialogPrimaryButton primaryButton;
        public DialogTextButton secondaryButton;
        public DialogTextButton tertiaryButton;
        public String title;

        public static final DialogConfig makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogConfig dialogConfig = new DialogConfig();
            dialogConfig.setFromJSONObject(jSONObject);
            return dialogConfig;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                DialogButton dialogButton = this.backButton;
                if (dialogButton != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_BACK_BUTTON_KEY, dialogButton.convertToJSONObject());
                }
                String str = this.body;
                if (str != null) {
                    jSONObject.put("body", str);
                }
                DialogButton dialogButton2 = this.controllerBackButton;
                if (dialogButton2 != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_CONTROLLER_BACK_BUTTON_KEY, dialogButton2.convertToJSONObject());
                }
                jSONObject.put("dialogId", this.dialogId);
                DialogHeroSpace dialogHeroSpace = this.heroSpace;
                if (dialogHeroSpace != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_HERO_SPACE_KEY, dialogHeroSpace.convertToJSONObject());
                }
                DialogIconButton dialogIconButton = this.iconButton;
                if (dialogIconButton != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_ICON_BUTTON_KEY, dialogIconButton.convertToJSONObject());
                }
                DialogInformationBox dialogInformationBox = this.informationBox;
                if (dialogInformationBox != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_INFORMATION_BOX_KEY, dialogInformationBox.convertToJSONObject());
                }
                List<DialogInlineImage> list2 = this.inlineImages;
                if (list2 != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_INLINE_IMAGES_KEY, NativeModuleParcel.convertParcelListToJSONArray(list2));
                }
                DialogList dialogList = this.list;
                if (dialogList != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_LIST_KEY, dialogList.convertToJSONObject());
                }
                DialogPrimaryButton dialogPrimaryButton = this.primaryButton;
                if (dialogPrimaryButton != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_PRIMARY_BUTTON_KEY, dialogPrimaryButton.convertToJSONObject());
                }
                DialogTextButton dialogTextButton = this.secondaryButton;
                if (dialogTextButton != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_SECONDARY_BUTTON_KEY, dialogTextButton.convertToJSONObject());
                }
                DialogTextButton dialogTextButton2 = this.tertiaryButton;
                if (dialogTextButton2 != null) {
                    jSONObject.put(DialogDefinitionCustom.DIALOG_TERTIARY_BUTTON_KEY, dialogTextButton2.convertToJSONObject());
                }
                String str2 = this.title;
                if (str2 != null) {
                    jSONObject.put("title", str2);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            DialogButton makeFromJSONObject;
            String optString;
            DialogButton makeFromJSONObject2;
            DialogHeroSpace makeFromJSONObject3;
            DialogIconButton makeFromJSONObject4;
            DialogInformationBox makeFromJSONObject5;
            ArrayList convertJSONArrayToParcelList;
            DialogList makeFromJSONObject6;
            DialogPrimaryButton makeFromJSONObject7;
            DialogTextButton makeFromJSONObject8;
            DialogTextButton makeFromJSONObject9;
            String str = null;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_BACK_BUTTON_KEY)) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = DialogButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_BACK_BUTTON_KEY));
            }
            this.backButton = makeFromJSONObject;
            if (jSONObject.isNull("body")) {
                optString = null;
            } else {
                optString = jSONObject.optString("body");
            }
            this.body = optString;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_CONTROLLER_BACK_BUTTON_KEY)) {
                makeFromJSONObject2 = null;
            } else {
                makeFromJSONObject2 = DialogButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_CONTROLLER_BACK_BUTTON_KEY));
            }
            this.controllerBackButton = makeFromJSONObject2;
            this.dialogId = jSONObject.optString("dialogId");
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_HERO_SPACE_KEY)) {
                makeFromJSONObject3 = null;
            } else {
                makeFromJSONObject3 = DialogHeroSpace.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_HERO_SPACE_KEY));
            }
            this.heroSpace = makeFromJSONObject3;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_ICON_BUTTON_KEY)) {
                makeFromJSONObject4 = null;
            } else {
                makeFromJSONObject4 = DialogIconButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_ICON_BUTTON_KEY));
            }
            this.iconButton = makeFromJSONObject4;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_INFORMATION_BOX_KEY)) {
                makeFromJSONObject5 = null;
            } else {
                makeFromJSONObject5 = DialogInformationBox.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_INFORMATION_BOX_KEY));
            }
            this.informationBox = makeFromJSONObject5;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_INLINE_IMAGES_KEY)) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(DialogDefinitionCustom.DIALOG_INLINE_IMAGES_KEY), DialogInlineImage.class);
            }
            this.inlineImages = convertJSONArrayToParcelList;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_LIST_KEY)) {
                makeFromJSONObject6 = null;
            } else {
                makeFromJSONObject6 = DialogList.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_LIST_KEY));
            }
            this.list = makeFromJSONObject6;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_PRIMARY_BUTTON_KEY)) {
                makeFromJSONObject7 = null;
            } else {
                makeFromJSONObject7 = DialogPrimaryButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_PRIMARY_BUTTON_KEY));
            }
            this.primaryButton = makeFromJSONObject7;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_SECONDARY_BUTTON_KEY)) {
                makeFromJSONObject8 = null;
            } else {
                makeFromJSONObject8 = DialogTextButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_SECONDARY_BUTTON_KEY));
            }
            this.secondaryButton = makeFromJSONObject8;
            if (jSONObject.isNull(DialogDefinitionCustom.DIALOG_TERTIARY_BUTTON_KEY)) {
                makeFromJSONObject9 = null;
            } else {
                makeFromJSONObject9 = DialogTextButton.makeFromJSONObject(jSONObject.optJSONObject(DialogDefinitionCustom.DIALOG_TERTIARY_BUTTON_KEY));
            }
            this.tertiaryButton = makeFromJSONObject9;
            if (!jSONObject.isNull("title")) {
                str = jSONObject.optString("title");
            }
            this.title = str;
        }
    }

    public static class DialogHeroSpace extends NativeModuleParcel {
        public double aspectRatio;
        public String backgroundColor;
        public String imageSourceUrl;
        public String videoSourceUrl;

        public static final DialogHeroSpace makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogHeroSpace dialogHeroSpace = new DialogHeroSpace();
            dialogHeroSpace.setFromJSONObject(jSONObject);
            return dialogHeroSpace;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_ASPECT_RATIO_KEY, this.aspectRatio);
                String str = this.backgroundColor;
                if (str != null) {
                    jSONObject.put(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_BACKGROUND_COLOR_KEY, str);
                }
                String str2 = this.imageSourceUrl;
                if (str2 != null) {
                    jSONObject.put(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_IMAGE_SOURCE_URL_KEY, str2);
                }
                String str3 = this.videoSourceUrl;
                if (str3 != null) {
                    jSONObject.put(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_VIDEO_SOURCE_URL_KEY, str3);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String optString2;
            this.aspectRatio = jSONObject.optDouble(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_ASPECT_RATIO_KEY, 0.0d);
            String str = null;
            if (jSONObject.isNull(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_BACKGROUND_COLOR_KEY)) {
                optString = null;
            } else {
                optString = jSONObject.optString(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_BACKGROUND_COLOR_KEY);
            }
            this.backgroundColor = optString;
            if (jSONObject.isNull(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_IMAGE_SOURCE_URL_KEY)) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_IMAGE_SOURCE_URL_KEY);
            }
            this.imageSourceUrl = optString2;
            if (!jSONObject.isNull(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_VIDEO_SOURCE_URL_KEY)) {
                str = jSONObject.optString(com.oculus.systemdialog.DialogHeroSpace.DIALOG_HERO_VIDEO_SOURCE_URL_KEY);
            }
            this.videoSourceUrl = str;
        }
    }

    public static class DialogIconButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public DialogIconButtonIcon icon;

        public static final DialogIconButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogIconButton dialogIconButton = new DialogIconButton();
            dialogIconButton.setFromJSONObject(jSONObject);
            return dialogIconButton;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                Boolean bool = this.autoClose;
                if (bool != null) {
                    jSONObject.put("autoClose", bool);
                }
                jSONObject.put("icon", this.icon.name());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            this.action = jSONObject.optString("action");
            if (jSONObject.isNull("autoClose")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            }
            this.autoClose = valueOf;
            this.icon = DialogIconButtonIcon.valueOf(jSONObject.optString("icon"));
        }
    }

    public enum DialogIconButtonIcon {
        info,
        settings
    }

    public static class DialogInformationBox extends NativeModuleParcel {
        public DialogInformationBoxIcon icon;
        public String text;

        public static final DialogInformationBox makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogInformationBox dialogInformationBox = new DialogInformationBox();
            dialogInformationBox.setFromJSONObject(jSONObject);
            return dialogInformationBox;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                DialogInformationBoxIcon dialogInformationBoxIcon = this.icon;
                if (dialogInformationBoxIcon != null) {
                    jSONObject.put("icon", dialogInformationBoxIcon.name());
                }
                jSONObject.put("text", this.text);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            DialogInformationBoxIcon valueOf;
            if (jSONObject.isNull("icon")) {
                valueOf = null;
            } else {
                valueOf = DialogInformationBoxIcon.valueOf(jSONObject.optString("icon"));
            }
            this.icon = valueOf;
            this.text = jSONObject.optString("text");
        }
    }

    public enum DialogInformationBoxIcon {
        check_alt,
        info,
        spinner
    }

    public static class DialogInlineImage extends NativeModuleParcel {
        public String image;
        public String placeholder;

        public static final DialogInlineImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogInlineImage dialogInlineImage = new DialogInlineImage();
            dialogInlineImage.setFromJSONObject(jSONObject);
            return dialogInlineImage;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("image", this.image);
                jSONObject.put(com.oculus.systemdialog.DialogInlineImage.DIALOG_INLINE_IMAGE_PLACEHOLDER_KEY, this.placeholder);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.image = jSONObject.optString("image");
            this.placeholder = jSONObject.optString(com.oculus.systemdialog.DialogInlineImage.DIALOG_INLINE_IMAGE_PLACEHOLDER_KEY);
        }
    }

    public static class DialogList extends NativeModuleParcel {
        public List<DialogListItem> items;
        public DialogListType type;

        public static final DialogList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogList dialogList = new DialogList();
            dialogList.setFromJSONObject(jSONObject);
            return dialogList;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.oculus.systemdialog.DialogList.DIALOG_LIST_ITEMS_KEY, NativeModuleParcel.convertParcelListToJSONArray(this.items));
                jSONObject.put("type", this.type.name());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.items = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(com.oculus.systemdialog.DialogList.DIALOG_LIST_ITEMS_KEY), DialogListItem.class);
            this.type = DialogListType.valueOf(jSONObject.optString("type"));
        }
    }

    public static class DialogListItem extends NativeModuleParcel {
        public String bodyText;
        public String id;
        public String image;
        public String titleText;

        public static final DialogListItem makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogListItem dialogListItem = new DialogListItem();
            dialogListItem.setFromJSONObject(jSONObject);
            return dialogListItem;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.bodyText;
                if (str != null) {
                    jSONObject.put(com.oculus.systemdialog.DialogListItem.DIALOG_LIST_ITEM_BODY_TEXT_KEY, str);
                }
                jSONObject.put("id", this.id);
                String str2 = this.image;
                if (str2 != null) {
                    jSONObject.put("image", str2);
                }
                jSONObject.put(com.oculus.systemdialog.DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY, this.titleText);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            String str = null;
            if (jSONObject.isNull(com.oculus.systemdialog.DialogListItem.DIALOG_LIST_ITEM_BODY_TEXT_KEY)) {
                optString = null;
            } else {
                optString = jSONObject.optString(com.oculus.systemdialog.DialogListItem.DIALOG_LIST_ITEM_BODY_TEXT_KEY);
            }
            this.bodyText = optString;
            this.id = jSONObject.optString("id");
            if (!jSONObject.isNull("image")) {
                str = jSONObject.optString("image");
            }
            this.image = str;
            this.titleText = jSONObject.optString(com.oculus.systemdialog.DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
        }
    }

    public enum DialogListType {
        multiSelect,
        noSelect,
        singleSelect
    }

    public static class DialogPrimaryButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public Boolean disabled;
        public Boolean disabledUntilBodyScrolledToBottom;
        public DialogPrimaryButtonStyle style;
        public String text;

        public static final DialogPrimaryButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogPrimaryButton dialogPrimaryButton = new DialogPrimaryButton();
            dialogPrimaryButton.setFromJSONObject(jSONObject);
            return dialogPrimaryButton;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                Boolean bool = this.autoClose;
                if (bool != null) {
                    jSONObject.put("autoClose", bool);
                }
                Boolean bool2 = this.disabled;
                if (bool2 != null) {
                    jSONObject.put(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY, bool2);
                }
                Boolean bool3 = this.disabledUntilBodyScrolledToBottom;
                if (bool3 != null) {
                    jSONObject.put(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY, bool3);
                }
                jSONObject.put("text", this.text);
                DialogPrimaryButtonStyle dialogPrimaryButtonStyle = this.style;
                if (dialogPrimaryButtonStyle != null) {
                    jSONObject.put(DialogButtonPrimary.DIALOG_BUTTON_PRIMARY_STYLE_KEY, dialogPrimaryButtonStyle.name());
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            Boolean valueOf2;
            Boolean valueOf3;
            this.action = jSONObject.optString("action");
            DialogPrimaryButtonStyle dialogPrimaryButtonStyle = null;
            if (jSONObject.isNull("autoClose")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            }
            this.autoClose = valueOf;
            if (jSONObject.isNull(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY)) {
                valueOf2 = null;
            } else {
                valueOf2 = Boolean.valueOf(jSONObject.optBoolean(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY));
            }
            this.disabled = valueOf2;
            if (jSONObject.isNull(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY)) {
                valueOf3 = null;
            } else {
                valueOf3 = Boolean.valueOf(jSONObject.optBoolean(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY));
            }
            this.disabledUntilBodyScrolledToBottom = valueOf3;
            this.text = jSONObject.optString("text");
            if (!jSONObject.isNull(DialogButtonPrimary.DIALOG_BUTTON_PRIMARY_STYLE_KEY)) {
                dialogPrimaryButtonStyle = DialogPrimaryButtonStyle.valueOf(jSONObject.optString(DialogButtonPrimary.DIALOG_BUTTON_PRIMARY_STYLE_KEY));
            }
            this.style = dialogPrimaryButtonStyle;
        }
    }

    public enum DialogPrimaryButtonStyle {
        danger,
        primary,
        f4secondary
    }

    public static class DialogResult extends NativeModuleParcel {
        public String action;
        public String dialogId;
        public List<String> listSelection;

        public static final DialogResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogResult dialogResult = new DialogResult();
            dialogResult.setFromJSONObject(jSONObject);
            return dialogResult;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                jSONObject.put("dialogId", this.dialogId);
                jSONObject.put("listSelection", NativeModuleParcel.convertStringListToJSONArray(this.listSelection));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.action = jSONObject.optString("action");
            this.dialogId = jSONObject.optString("dialogId");
            this.listSelection = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("listSelection"));
        }
    }

    public static class DialogTextButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public Boolean disabled;
        public Boolean disabledUntilBodyScrolledToBottom;
        public String text;

        public static final DialogTextButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogTextButton dialogTextButton = new DialogTextButton();
            dialogTextButton.setFromJSONObject(jSONObject);
            return dialogTextButton;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                Boolean bool = this.autoClose;
                if (bool != null) {
                    jSONObject.put("autoClose", bool);
                }
                Boolean bool2 = this.disabled;
                if (bool2 != null) {
                    jSONObject.put(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY, bool2);
                }
                Boolean bool3 = this.disabledUntilBodyScrolledToBottom;
                if (bool3 != null) {
                    jSONObject.put(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY, bool3);
                }
                jSONObject.put("text", this.text);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            Boolean valueOf2;
            this.action = jSONObject.optString("action");
            Boolean bool = null;
            if (jSONObject.isNull("autoClose")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            }
            this.autoClose = valueOf;
            if (jSONObject.isNull(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY)) {
                valueOf2 = null;
            } else {
                valueOf2 = Boolean.valueOf(jSONObject.optBoolean(DialogButtonText.DIALOG_BUTTON_DISABLED_KEY));
            }
            this.disabled = valueOf2;
            if (!jSONObject.isNull(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY)) {
                bool = Boolean.valueOf(jSONObject.optBoolean(DialogButtonText.DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY));
            }
            this.disabledUntilBodyScrolledToBottom = bool;
            this.text = jSONObject.optString("text");
        }
    }

    public abstract void hideDialogImpl(String str, Resolver<Void> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void showCommonDialogImpl(CommonDialogID commonDialogID, JSONObject jSONObject, Resolver<Void> resolver);

    public abstract void showDialogImpl(DialogConfig dialogConfig, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("hideDialog", "+sii"));
        arrayList.add(new Pair("showCommonDialog", "+sjii"));
        arrayList.add(new Pair("showDialog", "+jii"));
        return arrayList;
    }

    public final void emitOnDialogResult(DialogResult dialogResult) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "DialogsModule_onDialogResult", String.valueOf(dialogResult.convertToJSONObject()));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void hideDialog(String str, int i, int i2) {
        hideDialogImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void showCommonDialog(String str, JSONObject jSONObject, int i, int i2) {
        showCommonDialogImpl(CommonDialogID.valueOf(str), jSONObject, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void showDialog(JSONObject jSONObject, int i, int i2) {
        showDialogImpl(DialogConfig.makeFromJSONObject(jSONObject), ResolverFactory.createVoidResolver(this, i, i2));
    }
}
