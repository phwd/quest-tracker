package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DialogsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = DialogsModule.class.getSimpleName();

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
    public abstract void showCommonDialogImpl(CommonDialogID commonDialogID, JSONObject jSONObject, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void showDialogImpl(DialogConfig dialogConfig, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("hideDialog", "+sii"));
        list.add(new Pair<>("showCommonDialog", "+sjii"));
        list.add(new Pair<>("showDialog", "+jii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnDialogResult(DialogResult data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DialogsModule_onDialogResult", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void hideDialog(String dialogId, int resolveID, int rejectID) {
        hideDialogImpl(dialogId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void showCommonDialog(String commonDialogId, JSONObject config, int resolveID, int rejectID) {
        showCommonDialogImpl(CommonDialogID.valueOf(commonDialogId), config, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void showDialog(JSONObject config, int resolveID, int rejectID) {
        showDialogImpl(DialogConfig.makeFromJSONObject(config), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class DialogResult extends NativeModuleParcel {
        public String action;
        public String dialogId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("action", this.action);
                parcel.put("dialogId", this.dialogId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.action = json.optString("action");
            this.dialogId = json.optString("dialogId");
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
            JSONObject parcel = new JSONObject();
            try {
                if (this.backButton != null) {
                    parcel.put("backButton", this.backButton.convertToJSONObject());
                }
                if (this.body != null) {
                    parcel.put("body", this.body);
                }
                if (this.controllerBackButton != null) {
                    parcel.put("controllerBackButton", this.controllerBackButton.convertToJSONObject());
                }
                parcel.put("dialogId", this.dialogId);
                if (this.heroSpace != null) {
                    parcel.put("heroSpace", this.heroSpace.convertToJSONObject());
                }
                if (this.iconButton != null) {
                    parcel.put("iconButton", this.iconButton.convertToJSONObject());
                }
                if (this.informationBox != null) {
                    parcel.put("informationBox", this.informationBox.convertToJSONObject());
                }
                if (this.inlineImages != null) {
                    parcel.put("inlineImages", NativeModuleParcel.convertParcelListToJSONArray(this.inlineImages));
                }
                if (this.list != null) {
                    parcel.put("list", this.list.convertToJSONObject());
                }
                if (this.primaryButton != null) {
                    parcel.put("primaryButton", this.primaryButton.convertToJSONObject());
                }
                if (this.secondaryButton != null) {
                    parcel.put("secondaryButton", this.secondaryButton.convertToJSONObject());
                }
                if (this.tertiaryButton != null) {
                    parcel.put("tertiaryButton", this.tertiaryButton.convertToJSONObject());
                }
                if (this.title != null) {
                    parcel.put("title", this.title);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.backButton = json.isNull("backButton") ? null : DialogButton.makeFromJSONObject(json.optJSONObject("backButton"));
            this.body = json.isNull("body") ? null : json.optString("body");
            this.controllerBackButton = json.isNull("controllerBackButton") ? null : DialogButton.makeFromJSONObject(json.optJSONObject("controllerBackButton"));
            this.dialogId = json.optString("dialogId");
            this.heroSpace = json.isNull("heroSpace") ? null : DialogHeroSpace.makeFromJSONObject(json.optJSONObject("heroSpace"));
            this.iconButton = json.isNull("iconButton") ? null : DialogIconButton.makeFromJSONObject(json.optJSONObject("iconButton"));
            this.informationBox = json.isNull("informationBox") ? null : DialogInformationBox.makeFromJSONObject(json.optJSONObject("informationBox"));
            this.inlineImages = json.isNull("inlineImages") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("inlineImages"), DialogInlineImage.class);
            this.list = json.isNull("list") ? null : DialogList.makeFromJSONObject(json.optJSONObject("list"));
            this.primaryButton = json.isNull("primaryButton") ? null : DialogPrimaryButton.makeFromJSONObject(json.optJSONObject("primaryButton"));
            this.secondaryButton = json.isNull("secondaryButton") ? null : DialogTextButton.makeFromJSONObject(json.optJSONObject("secondaryButton"));
            this.tertiaryButton = json.isNull("tertiaryButton") ? null : DialogTextButton.makeFromJSONObject(json.optJSONObject("tertiaryButton"));
            if (!json.isNull("title")) {
                str = json.optString("title");
            }
            this.title = str;
        }

        public static final DialogConfig makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogConfig result = new DialogConfig();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("action", this.action);
                if (this.autoClose != null) {
                    parcel.put("autoClose", this.autoClose);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.action = json.optString("action");
            this.autoClose = json.isNull("autoClose") ? null : Boolean.valueOf(json.optBoolean("autoClose"));
        }

        public static final DialogButton makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogButton result = new DialogButton();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogHeroSpace extends NativeModuleParcel {
        public double aspectRatio;
        public String backgroundColor;
        public String imageSourceUrl;
        public String videoSourceUrl;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("aspectRatio", this.aspectRatio);
                if (this.backgroundColor != null) {
                    parcel.put("backgroundColor", this.backgroundColor);
                }
                if (this.imageSourceUrl != null) {
                    parcel.put("imageSourceUrl", this.imageSourceUrl);
                }
                if (this.videoSourceUrl != null) {
                    parcel.put("videoSourceUrl", this.videoSourceUrl);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.aspectRatio = json.optDouble("aspectRatio", 0.0d);
            this.backgroundColor = json.isNull("backgroundColor") ? null : json.optString("backgroundColor");
            this.imageSourceUrl = json.isNull("imageSourceUrl") ? null : json.optString("imageSourceUrl");
            if (!json.isNull("videoSourceUrl")) {
                str = json.optString("videoSourceUrl");
            }
            this.videoSourceUrl = str;
        }

        public static final DialogHeroSpace makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogHeroSpace result = new DialogHeroSpace();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogIconButton extends NativeModuleParcel {
        public String action;
        public Boolean autoClose;
        public DialogIconButtonIcon icon;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("action", this.action);
                if (this.autoClose != null) {
                    parcel.put("autoClose", this.autoClose);
                }
                parcel.put("icon", this.icon.name());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.action = json.optString("action");
            this.autoClose = json.isNull("autoClose") ? null : Boolean.valueOf(json.optBoolean("autoClose"));
            this.icon = DialogIconButtonIcon.valueOf(json.optString("icon"));
        }

        public static final DialogIconButton makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogIconButton result = new DialogIconButton();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogInformationBox extends NativeModuleParcel {
        public DialogInformationBoxIcon icon;
        public String text;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.icon != null) {
                    parcel.put("icon", this.icon.name());
                }
                parcel.put("text", this.text);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.icon = json.isNull("icon") ? null : DialogInformationBoxIcon.valueOf(json.optString("icon"));
            this.text = json.optString("text");
        }

        public static final DialogInformationBox makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogInformationBox result = new DialogInformationBox();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogInlineImage extends NativeModuleParcel {
        public String image;
        public String placeholder;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("image", this.image);
                parcel.put("placeholder", this.placeholder);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.image = json.optString("image");
            this.placeholder = json.optString("placeholder");
        }
    }

    public static class DialogList extends NativeModuleParcel {
        public List<DialogListItem> items;
        public DialogListType type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("items", NativeModuleParcel.convertParcelListToJSONArray(this.items));
                parcel.put("type", this.type.name());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.items = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("items"), DialogListItem.class);
            this.type = DialogListType.valueOf(json.optString("type"));
        }

        public static final DialogList makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogList result = new DialogList();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class DialogListItem extends NativeModuleParcel {
        public String bodyText;
        public String id;
        public String image;
        public String titleText;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.bodyText != null) {
                    parcel.put("bodyText", this.bodyText);
                }
                parcel.put("id", this.id);
                if (this.image != null) {
                    parcel.put("image", this.image);
                }
                parcel.put("titleText", this.titleText);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.bodyText = json.isNull("bodyText") ? null : json.optString("bodyText");
            this.id = json.optString("id");
            if (!json.isNull("image")) {
                str = json.optString("image");
            }
            this.image = str;
            this.titleText = json.optString("titleText");
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
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("action", this.action);
                if (this.autoClose != null) {
                    parcel.put("autoClose", this.autoClose);
                }
                if (this.disabled != null) {
                    parcel.put("disabled", this.disabled);
                }
                if (this.disabledUntilBodyScrolledToBottom != null) {
                    parcel.put("disabledUntilBodyScrolledToBottom", this.disabledUntilBodyScrolledToBottom);
                }
                parcel.put("text", this.text);
                if (this.style != null) {
                    parcel.put("style", this.style.name());
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            DialogPrimaryButtonStyle dialogPrimaryButtonStyle = null;
            this.action = json.optString("action");
            this.autoClose = json.isNull("autoClose") ? null : Boolean.valueOf(json.optBoolean("autoClose"));
            this.disabled = json.isNull("disabled") ? null : Boolean.valueOf(json.optBoolean("disabled"));
            this.disabledUntilBodyScrolledToBottom = json.isNull("disabledUntilBodyScrolledToBottom") ? null : Boolean.valueOf(json.optBoolean("disabledUntilBodyScrolledToBottom"));
            this.text = json.optString("text");
            if (!json.isNull("style")) {
                dialogPrimaryButtonStyle = DialogPrimaryButtonStyle.valueOf(json.optString("style"));
            }
            this.style = dialogPrimaryButtonStyle;
        }

        public static final DialogPrimaryButton makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogPrimaryButton result = new DialogPrimaryButton();
            result.setFromJSONObject(json);
            return result;
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
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("action", this.action);
                if (this.autoClose != null) {
                    parcel.put("autoClose", this.autoClose);
                }
                if (this.disabled != null) {
                    parcel.put("disabled", this.disabled);
                }
                if (this.disabledUntilBodyScrolledToBottom != null) {
                    parcel.put("disabledUntilBodyScrolledToBottom", this.disabledUntilBodyScrolledToBottom);
                }
                parcel.put("text", this.text);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            Boolean bool = null;
            this.action = json.optString("action");
            this.autoClose = json.isNull("autoClose") ? null : Boolean.valueOf(json.optBoolean("autoClose"));
            this.disabled = json.isNull("disabled") ? null : Boolean.valueOf(json.optBoolean("disabled"));
            if (!json.isNull("disabledUntilBodyScrolledToBottom")) {
                bool = Boolean.valueOf(json.optBoolean("disabledUntilBodyScrolledToBottom"));
            }
            this.disabledUntilBodyScrolledToBottom = bool;
            this.text = json.optString("text");
        }

        public static final DialogTextButton makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            DialogTextButton result = new DialogTextButton();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
