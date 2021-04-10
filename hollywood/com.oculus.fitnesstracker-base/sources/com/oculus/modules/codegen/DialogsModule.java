package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DialogsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "DialogsModule";

    public enum CommonDialogID {
        common_system_dialog_app_launch_blocked_controller_required,
        common_system_dialog_app_launch_blocked_hands_required,
        common_system_dialog_guardian_adjust
    }

    public enum DialogIconButtonIcon {
        info,
        settings
    }

    public enum DialogInformationBoxIcon {
        check_alt,
        info,
        spinner
    }

    public enum DialogListType {
        multiSelect,
        noSelect,
        singleSelect
    }

    public enum DialogPrimaryButtonStyle {
        danger,
        primary,
        secondary
    }

    /* access modifiers changed from: protected */
    public abstract void hideDialogImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void showCommonDialogImpl(CommonDialogID commonDialogID, JSONObject jSONObject, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void showDialogImpl(DialogConfig dialogConfig, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("hideDialog", "+sii"));
        arrayList.add(new Pair("showCommonDialog", "+sjii"));
        arrayList.add(new Pair("showDialog", "+jii"));
        return arrayList;
    }

    public final void emitOnDialogResult(DialogResult dialogResult) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DialogsModule_onDialogResult", String.valueOf(dialogResult.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void hideDialog(String str, int i, int i2) {
        hideDialogImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void showCommonDialog(String str, JSONObject jSONObject, int i, int i2) {
        showCommonDialogImpl(CommonDialogID.valueOf(str), jSONObject, ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void showDialog(JSONObject jSONObject, int i, int i2) {
        showDialogImpl(DialogConfig.makeFromJSONObject(jSONObject), ResolverFactory.createVoidResolver(this, i, i2));
    }

    public static class DialogResult extends NativeModuleParcel {
        public String action;
        public String dialogId;
        public List<String> listSelection;

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

        public static final DialogResult makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogResult dialogResult = new DialogResult();
            dialogResult.setFromJSONObject(jSONObject);
            return dialogResult;
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

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.backButton != null) {
                    jSONObject.put("backButton", this.backButton.convertToJSONObject());
                }
                if (this.body != null) {
                    jSONObject.put("body", this.body);
                }
                if (this.controllerBackButton != null) {
                    jSONObject.put("controllerBackButton", this.controllerBackButton.convertToJSONObject());
                }
                jSONObject.put("dialogId", this.dialogId);
                if (this.heroSpace != null) {
                    jSONObject.put("heroSpace", this.heroSpace.convertToJSONObject());
                }
                if (this.iconButton != null) {
                    jSONObject.put("iconButton", this.iconButton.convertToJSONObject());
                }
                if (this.informationBox != null) {
                    jSONObject.put("informationBox", this.informationBox.convertToJSONObject());
                }
                if (this.inlineImages != null) {
                    jSONObject.put("inlineImages", NativeModuleParcel.convertParcelListToJSONArray(this.inlineImages));
                }
                if (this.list != null) {
                    jSONObject.put("list", this.list.convertToJSONObject());
                }
                if (this.primaryButton != null) {
                    jSONObject.put("primaryButton", this.primaryButton.convertToJSONObject());
                }
                if (this.secondaryButton != null) {
                    jSONObject.put("secondaryButton", this.secondaryButton.convertToJSONObject());
                }
                if (this.tertiaryButton != null) {
                    jSONObject.put("tertiaryButton", this.tertiaryButton.convertToJSONObject());
                }
                if (this.title != null) {
                    jSONObject.put("title", this.title);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String str = null;
            this.backButton = jSONObject.isNull("backButton") ? null : DialogButton.makeFromJSONObject(jSONObject.optJSONObject("backButton"));
            this.body = jSONObject.isNull("body") ? null : jSONObject.optString("body");
            this.controllerBackButton = jSONObject.isNull("controllerBackButton") ? null : DialogButton.makeFromJSONObject(jSONObject.optJSONObject("controllerBackButton"));
            this.dialogId = jSONObject.optString("dialogId");
            this.heroSpace = jSONObject.isNull("heroSpace") ? null : DialogHeroSpace.makeFromJSONObject(jSONObject.optJSONObject("heroSpace"));
            this.iconButton = jSONObject.isNull("iconButton") ? null : DialogIconButton.makeFromJSONObject(jSONObject.optJSONObject("iconButton"));
            this.informationBox = jSONObject.isNull("informationBox") ? null : DialogInformationBox.makeFromJSONObject(jSONObject.optJSONObject("informationBox"));
            this.inlineImages = jSONObject.isNull("inlineImages") ? null : NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("inlineImages"), DialogInlineImage.class);
            this.list = jSONObject.isNull("list") ? null : DialogList.makeFromJSONObject(jSONObject.optJSONObject("list"));
            this.primaryButton = jSONObject.isNull("primaryButton") ? null : DialogPrimaryButton.makeFromJSONObject(jSONObject.optJSONObject("primaryButton"));
            this.secondaryButton = jSONObject.isNull("secondaryButton") ? null : DialogTextButton.makeFromJSONObject(jSONObject.optJSONObject("secondaryButton"));
            this.tertiaryButton = jSONObject.isNull("tertiaryButton") ? null : DialogTextButton.makeFromJSONObject(jSONObject.optJSONObject("tertiaryButton"));
            if (!jSONObject.isNull("title")) {
                str = jSONObject.optString("title");
            }
            this.title = str;
        }

        public static final DialogConfig makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogConfig dialogConfig = new DialogConfig();
            dialogConfig.setFromJSONObject(jSONObject);
            return dialogConfig;
        }
    }

    public static class DialogButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                if (this.autoClose != null) {
                    jSONObject.put("autoClose", this.autoClose);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.action = jSONObject.optString("action");
            this.autoClose = jSONObject.isNull("autoClose") ? null : Boolean.valueOf(jSONObject.optBoolean("autoClose"));
        }

        public static final DialogButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogButton dialogButton = new DialogButton();
            dialogButton.setFromJSONObject(jSONObject);
            return dialogButton;
        }
    }

    public static class DialogHeroSpace extends NativeModuleParcel {
        public double aspectRatio;
        public String backgroundColor;
        public String imageSourceUrl;
        public String videoSourceUrl;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("aspectRatio", this.aspectRatio);
                if (this.backgroundColor != null) {
                    jSONObject.put("backgroundColor", this.backgroundColor);
                }
                if (this.imageSourceUrl != null) {
                    jSONObject.put("imageSourceUrl", this.imageSourceUrl);
                }
                if (this.videoSourceUrl != null) {
                    jSONObject.put("videoSourceUrl", this.videoSourceUrl);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.aspectRatio = jSONObject.optDouble("aspectRatio", 0.0d);
            String str = null;
            this.backgroundColor = jSONObject.isNull("backgroundColor") ? null : jSONObject.optString("backgroundColor");
            this.imageSourceUrl = jSONObject.isNull("imageSourceUrl") ? null : jSONObject.optString("imageSourceUrl");
            if (!jSONObject.isNull("videoSourceUrl")) {
                str = jSONObject.optString("videoSourceUrl");
            }
            this.videoSourceUrl = str;
        }

        public static final DialogHeroSpace makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogHeroSpace dialogHeroSpace = new DialogHeroSpace();
            dialogHeroSpace.setFromJSONObject(jSONObject);
            return dialogHeroSpace;
        }
    }

    public static class DialogIconButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public DialogIconButtonIcon icon;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                if (this.autoClose != null) {
                    jSONObject.put("autoClose", this.autoClose);
                }
                jSONObject.put("icon", this.icon.name());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.action = jSONObject.optString("action");
            this.autoClose = jSONObject.isNull("autoClose") ? null : Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            this.icon = DialogIconButtonIcon.valueOf(jSONObject.optString("icon"));
        }

        public static final DialogIconButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogIconButton dialogIconButton = new DialogIconButton();
            dialogIconButton.setFromJSONObject(jSONObject);
            return dialogIconButton;
        }
    }

    public static class DialogInformationBox extends NativeModuleParcel {
        public DialogInformationBoxIcon icon;
        public String text;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.icon != null) {
                    jSONObject.put("icon", this.icon.name());
                }
                jSONObject.put("text", this.text);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.icon = jSONObject.isNull("icon") ? null : DialogInformationBoxIcon.valueOf(jSONObject.optString("icon"));
            this.text = jSONObject.optString("text");
        }

        public static final DialogInformationBox makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogInformationBox dialogInformationBox = new DialogInformationBox();
            dialogInformationBox.setFromJSONObject(jSONObject);
            return dialogInformationBox;
        }
    }

    public static class DialogInlineImage extends NativeModuleParcel {
        public String image;
        public String placeholder;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("image", this.image);
                jSONObject.put("placeholder", this.placeholder);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.image = jSONObject.optString("image");
            this.placeholder = jSONObject.optString("placeholder");
        }

        public static final DialogInlineImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogInlineImage dialogInlineImage = new DialogInlineImage();
            dialogInlineImage.setFromJSONObject(jSONObject);
            return dialogInlineImage;
        }
    }

    public static class DialogList extends NativeModuleParcel {
        public List<DialogListItem> items;
        public DialogListType type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("items", NativeModuleParcel.convertParcelListToJSONArray(this.items));
                jSONObject.put("type", this.type.name());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.items = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("items"), DialogListItem.class);
            this.type = DialogListType.valueOf(jSONObject.optString("type"));
        }

        public static final DialogList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogList dialogList = new DialogList();
            dialogList.setFromJSONObject(jSONObject);
            return dialogList;
        }
    }

    public static class DialogListItem extends NativeModuleParcel {
        public String bodyText;
        public String id;
        public String image;
        public String titleText;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.bodyText != null) {
                    jSONObject.put("bodyText", this.bodyText);
                }
                jSONObject.put("id", this.id);
                if (this.image != null) {
                    jSONObject.put("image", this.image);
                }
                jSONObject.put("titleText", this.titleText);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String str = null;
            this.bodyText = jSONObject.isNull("bodyText") ? null : jSONObject.optString("bodyText");
            this.id = jSONObject.optString("id");
            if (!jSONObject.isNull("image")) {
                str = jSONObject.optString("image");
            }
            this.image = str;
            this.titleText = jSONObject.optString("titleText");
        }

        public static final DialogListItem makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogListItem dialogListItem = new DialogListItem();
            dialogListItem.setFromJSONObject(jSONObject);
            return dialogListItem;
        }
    }

    public static class DialogPrimaryButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public Boolean disabled;
        public Boolean disabledUntilBodyScrolledToBottom;
        public DialogPrimaryButtonStyle style;
        public String text;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                if (this.autoClose != null) {
                    jSONObject.put("autoClose", this.autoClose);
                }
                if (this.disabled != null) {
                    jSONObject.put("disabled", this.disabled);
                }
                if (this.disabledUntilBodyScrolledToBottom != null) {
                    jSONObject.put("disabledUntilBodyScrolledToBottom", this.disabledUntilBodyScrolledToBottom);
                }
                jSONObject.put("text", this.text);
                if (this.style != null) {
                    jSONObject.put("style", this.style.name());
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.action = jSONObject.optString("action");
            DialogPrimaryButtonStyle dialogPrimaryButtonStyle = null;
            this.autoClose = jSONObject.isNull("autoClose") ? null : Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            this.disabled = jSONObject.isNull("disabled") ? null : Boolean.valueOf(jSONObject.optBoolean("disabled"));
            this.disabledUntilBodyScrolledToBottom = jSONObject.isNull("disabledUntilBodyScrolledToBottom") ? null : Boolean.valueOf(jSONObject.optBoolean("disabledUntilBodyScrolledToBottom"));
            this.text = jSONObject.optString("text");
            if (!jSONObject.isNull("style")) {
                dialogPrimaryButtonStyle = DialogPrimaryButtonStyle.valueOf(jSONObject.optString("style"));
            }
            this.style = dialogPrimaryButtonStyle;
        }

        public static final DialogPrimaryButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogPrimaryButton dialogPrimaryButton = new DialogPrimaryButton();
            dialogPrimaryButton.setFromJSONObject(jSONObject);
            return dialogPrimaryButton;
        }
    }

    public static class DialogTextButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public Boolean disabled;
        public Boolean disabledUntilBodyScrolledToBottom;
        public String text;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", this.action);
                if (this.autoClose != null) {
                    jSONObject.put("autoClose", this.autoClose);
                }
                if (this.disabled != null) {
                    jSONObject.put("disabled", this.disabled);
                }
                if (this.disabledUntilBodyScrolledToBottom != null) {
                    jSONObject.put("disabledUntilBodyScrolledToBottom", this.disabledUntilBodyScrolledToBottom);
                }
                jSONObject.put("text", this.text);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.action = jSONObject.optString("action");
            Boolean bool = null;
            this.autoClose = jSONObject.isNull("autoClose") ? null : Boolean.valueOf(jSONObject.optBoolean("autoClose"));
            this.disabled = jSONObject.isNull("disabled") ? null : Boolean.valueOf(jSONObject.optBoolean("disabled"));
            if (!jSONObject.isNull("disabledUntilBodyScrolledToBottom")) {
                bool = Boolean.valueOf(jSONObject.optBoolean("disabledUntilBodyScrolledToBottom"));
            }
            this.disabledUntilBodyScrolledToBottom = bool;
            this.text = jSONObject.optString("text");
        }

        public static final DialogTextButton makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            DialogTextButton dialogTextButton = new DialogTextButton();
            dialogTextButton.setFromJSONObject(jSONObject);
            return dialogTextButton;
        }
    }
}
