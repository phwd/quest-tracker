package com.oculus.panelapp.androiddialog.dialogs.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.databinding.CustomSystemDialogBinding;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonIcon;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.systemdialog.DialogProgressIndicator;
import com.oculus.systemdialog.DialogVideo;
import java.math.BigDecimal;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomDialog extends ConstraintLayout implements Dialog {
    private static final String ACTION_KEY = "action";
    private static final String ASPECT_RATIO_KEY = "aspectRatio";
    private static final String BACKGROUND_COLOR_KEY = "backgroundColor";
    private static final String BACK_BUTTON_KEY = "backButton";
    private static final String BODY_KEY = "body";
    private static final String DIALOG_ID_KEY = "dialogId";
    private static final String DISABLED_KEY = "disabled";
    private static final String HERO_SPACE_KEY = "heroSpace";
    private static final String ICON_BUTTON_KEY = "iconButton";
    private static final String ICON_KEY = "icon";
    private static final String IMAGE_SOURCE_URL_KEY = "imageSourceUrl";
    private static final String INFORMATION_BOX_KEY = "informationBox";
    private static final String PRIMARY_BUTTON_KEY = "primaryButton";
    private static final String PROGRESS_INDICATOR_KEY = "progressIndicator";
    private static final String PROGRESS_KEY = "progress";
    private static final String SECONDARY_BUTTON_KEY = "secondaryButton";
    private static final String STYLE_KEY = "style";
    private static final String TAG = LoggingUtil.tag(CustomDialog.class);
    private static final String TERTIARY_BUTTON_KEY = "tertiaryButton";
    private static final String TEXT_KEY = "text";
    private static final String TITLE_KEY = "title";
    private static final String TYPE_KEY = "type";
    private static final String VIDEO_SOURCE_URL_KEY = "videoSourceUrl";
    AndroidDialogPanelApp mPanelApp;

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
    }

    public CustomDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "Constructed CustomDialog.");
    }

    private static <T extends DialogButton> void setupButton(OCButton oCButton, @Nullable T t, Consumer<T> consumer, OCEventHandler oCEventHandler, Consumer<String> consumer2) {
        if (t != null) {
            consumer.accept(t);
            oCButton.setEventHandler(oCEventHandler);
            oCButton.setOnClickListener(new View.OnClickListener(consumer2, t) {
                /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$CustomDialog$sak1N5ly0iKnjeGTjpz6mPbgxKA */
                private final /* synthetic */ Consumer f$0;
                private final /* synthetic */ DialogButton f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    this.f$0.accept(this.f$1.getButtonAction());
                }
            });
        }
    }

    @VisibleForTesting
    static void setupText(DialogDefinitionCustom dialogDefinitionCustom, CustomDialogViewModel customDialogViewModel) {
        customDialogViewModel.setTitle(dialogDefinitionCustom.getTitle());
        customDialogViewModel.setBody(dialogDefinitionCustom.getBody());
        DialogInformationBox informationBox = dialogDefinitionCustom.getInformationBox();
        if (informationBox != null) {
            customDialogViewModel.setInformationBox(informationBox);
        }
    }

    @VisibleForTesting
    static void setupProgressIndicator(DialogDefinitionCustom dialogDefinitionCustom, CustomDialogViewModel customDialogViewModel) {
        DialogProgressIndicator progressIndicator = dialogDefinitionCustom.getProgressIndicator();
        if (progressIndicator != null) {
            customDialogViewModel.setProgressIndicator(progressIndicator);
        }
    }

    @VisibleForTesting
    static void setupButtons(DialogDefinitionCustom dialogDefinitionCustom, OCButton oCButton, OCButton oCButton2, OCButton oCButton3, OCBackButton oCBackButton, OCButton oCButton4, OCEventHandler oCEventHandler, CustomDialogViewModel customDialogViewModel, Consumer<String> consumer) {
        DialogButtonText primaryButton = dialogDefinitionCustom.getPrimaryButton();
        customDialogViewModel.getClass();
        setupButton(oCButton, primaryButton, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$t2uwRIbyCl5N13XXaDB9NKuV1bo */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CustomDialogViewModel.this.setPrimaryButton((DialogButtonText) obj);
            }
        }, oCEventHandler, consumer);
        DialogButtonText secondaryButton = dialogDefinitionCustom.getSecondaryButton();
        customDialogViewModel.getClass();
        setupButton(oCButton2, secondaryButton, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$_Yqyn_vdfMMKxJR6R5JP0lHFkk */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CustomDialogViewModel.this.setSecondaryButton((DialogButtonText) obj);
            }
        }, oCEventHandler, consumer);
        DialogButtonText tertiaryButton = dialogDefinitionCustom.getTertiaryButton();
        customDialogViewModel.getClass();
        setupButton(oCButton3, tertiaryButton, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$XOiEyWubn9s7IlZ5XBQUzwvOAVA */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CustomDialogViewModel.this.setTertiaryButton((DialogButtonText) obj);
            }
        }, oCEventHandler, consumer);
        DialogButton backButton = dialogDefinitionCustom.getBackButton();
        customDialogViewModel.getClass();
        setupButton(oCBackButton, backButton, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$X6UDRb7SF17Jfz_yVGfUuzWDllw */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CustomDialogViewModel.this.setBackButton((DialogButton) obj);
            }
        }, oCEventHandler, consumer);
        DialogButtonIcon iconButton = dialogDefinitionCustom.getIconButton();
        customDialogViewModel.getClass();
        setupButton(oCButton4, iconButton, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$fpi2BrvWkvHyWQyZ5mBICUBvYPc */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CustomDialogViewModel.this.setIconButton((DialogButtonIcon) obj);
            }
        }, oCEventHandler, consumer);
    }

    private static void setDialogButtonDisabled(JSONObject jSONObject, DialogButtonText dialogButtonText) throws JSONException {
        if (jSONObject.optBoolean(DISABLED_KEY, false)) {
            dialogButtonText.setDisabled();
        }
    }

    private static DialogButtonText getDialogButtonPrimary(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("style");
        if (TextUtils.isEmpty(optString)) {
            return getDialogButtonText(jSONObject);
        }
        DialogPrimaryButtonStyle dialogPrimaryButtonStyle = null;
        DialogPrimaryButtonStyle[] values = DialogPrimaryButtonStyle.values();
        for (DialogPrimaryButtonStyle dialogPrimaryButtonStyle2 : values) {
            if (dialogPrimaryButtonStyle2.getIPCString().equals(optString)) {
                dialogPrimaryButtonStyle = dialogPrimaryButtonStyle2;
            }
        }
        if (dialogPrimaryButtonStyle != null) {
            DialogButtonPrimary dialogButtonPrimary = new DialogButtonPrimary(jSONObject.getString("action"), jSONObject.getString("text"), dialogPrimaryButtonStyle);
            setDialogButtonDisabled(jSONObject, dialogButtonPrimary);
            return dialogButtonPrimary;
        }
        throw new JSONException(StringFormatUtil.formatStrLocaleSafe("invalid primary button style: %s", optString));
    }

    private static DialogButtonText getDialogButtonText(JSONObject jSONObject) throws JSONException {
        DialogButtonText dialogButtonText = new DialogButtonText(jSONObject.getString("action"), jSONObject.getString("text"));
        setDialogButtonDisabled(jSONObject, dialogButtonText);
        return dialogButtonText;
    }

    private static DialogButtonIcon getDialogButtonIcon(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("icon");
        DialogIcon.IconButton[] values = DialogIcon.IconButton.values();
        DialogIcon.IconButton iconButton = null;
        for (DialogIcon.IconButton iconButton2 : values) {
            if (iconButton2.getIPCString().equals(string)) {
                iconButton = iconButton2;
            }
        }
        if (iconButton != null) {
            return new DialogButtonIcon(jSONObject.getString("action"), iconButton);
        }
        throw new JSONException(StringFormatUtil.formatStrLocaleSafe("invalid icon name for icon button: %s", string));
    }

    private static void setHeroSpace(JSONObject jSONObject, DialogDefinitionCustom dialogDefinitionCustom) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("heroSpace");
        if (optJSONObject != null) {
            float floatValue = BigDecimal.valueOf(optJSONObject.getDouble("aspectRatio")).floatValue();
            String optString = optJSONObject.optString("backgroundColor");
            String optString2 = optJSONObject.optString("imageSourceUrl");
            String optString3 = optJSONObject.optString("videoSourceUrl");
            if (!TextUtils.isEmpty(optString2)) {
                dialogDefinitionCustom.setHeroImage(new DialogHeroImage(optString2, floatValue, optString));
            } else if (!TextUtils.isEmpty(optString3)) {
                dialogDefinitionCustom.setVideo(new DialogVideo(optString3, floatValue, optString));
            } else {
                throw new JSONException(StringFormatUtil.formatStrLocaleSafe("either %s or %s must be set for the hero space", "imageSourceUrl", "videoSourceUrl"));
            }
        }
    }

    private static void setProgressIndicator(JSONObject jSONObject, DialogDefinitionCustom dialogDefinitionCustom) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(PROGRESS_INDICATOR_KEY);
        if (optJSONObject != null) {
            String string = optJSONObject.getString("type");
            DialogProgressIndicator.ProgressIndicatorType progressIndicatorType = null;
            DialogProgressIndicator.ProgressIndicatorType[] values = DialogProgressIndicator.ProgressIndicatorType.values();
            for (DialogProgressIndicator.ProgressIndicatorType progressIndicatorType2 : values) {
                if (progressIndicatorType2.getIPCString().equals(string)) {
                    progressIndicatorType = progressIndicatorType2;
                }
            }
            if (progressIndicatorType != null) {
                int i = AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType[progressIndicatorType.ordinal()];
                if (i == 1) {
                    dialogDefinitionCustom.setProgressSpinner();
                } else if (i != 2) {
                    Log.e(TAG, "invalid progress indicator type");
                } else {
                    dialogDefinitionCustom.setProgressBar(BigDecimal.valueOf(optJSONObject.getDouble("progress")).floatValue());
                }
            } else {
                throw new JSONException(StringFormatUtil.formatStrLocaleSafe("invalid progress indicator type: %s", string));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType = new int[DialogProgressIndicator.ProgressIndicatorType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.systemdialog.DialogProgressIndicator$ProgressIndicatorType[] r0 = com.oculus.systemdialog.DialogProgressIndicator.ProgressIndicatorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.systemdialog.DialogProgressIndicator$ProgressIndicatorType r1 = com.oculus.systemdialog.DialogProgressIndicator.ProgressIndicatorType.SPINNER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.systemdialog.DialogProgressIndicator$ProgressIndicatorType r1 = com.oculus.systemdialog.DialogProgressIndicator.ProgressIndicatorType.BAR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialog.AnonymousClass1.<clinit>():void");
        }
    }

    @VisibleForTesting
    static DialogDefinitionCustom getDialogDefinitionCustom(JSONObject jSONObject) throws JSONException {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(jSONObject.getString("dialogId"), jSONObject.getString("title"), jSONObject.getString("body"));
        JSONObject optJSONObject = jSONObject.optJSONObject("primaryButton");
        if (optJSONObject != null) {
            dialogDefinitionCustom.setPrimaryButton(getDialogButtonPrimary(optJSONObject));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("secondaryButton");
        if (optJSONObject2 != null) {
            dialogDefinitionCustom.setSecondaryButton(getDialogButtonText(optJSONObject2));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject("tertiaryButton");
        if (optJSONObject3 != null) {
            dialogDefinitionCustom.setTertiaryButton(getDialogButtonText(optJSONObject3));
        }
        JSONObject optJSONObject4 = jSONObject.optJSONObject("backButton");
        if (optJSONObject4 != null) {
            dialogDefinitionCustom.setBackButton(new DialogButton(optJSONObject4.getString("action")));
        }
        JSONObject optJSONObject5 = jSONObject.optJSONObject("iconButton");
        if (optJSONObject5 != null) {
            dialogDefinitionCustom.setIconButton(getDialogButtonIcon(optJSONObject5));
        }
        JSONObject optJSONObject6 = jSONObject.optJSONObject("informationBox");
        if (optJSONObject6 != null) {
            dialogDefinitionCustom.setInformationBox(new DialogInformationBox(optJSONObject6.getString("text")));
        }
        setProgressIndicator(jSONObject, dialogDefinitionCustom);
        setHeroSpace(jSONObject, dialogDefinitionCustom);
        return dialogDefinitionCustom;
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, JSONObject jSONObject, CustomSystemDialogBinding customSystemDialogBinding) {
        this.mPanelApp = androidDialogPanelApp;
        CustomDialogViewModel customDialogViewModel = new CustomDialogViewModel();
        customSystemDialogBinding.setViewModel(customDialogViewModel);
        try {
            DialogDefinitionCustom dialogDefinitionCustom = getDialogDefinitionCustom(jSONObject);
            OCButton oCButton = customSystemDialogBinding.primaryButton;
            OCButton oCButton2 = customSystemDialogBinding.secondaryButton;
            OCButton oCButton3 = customSystemDialogBinding.tertiaryButton;
            OCBackButton oCBackButton = customSystemDialogBinding.backButton;
            OCButton oCButton4 = customSystemDialogBinding.iconButton;
            AndroidDialogPanelApp androidDialogPanelApp2 = this.mPanelApp;
            androidDialogPanelApp2.getClass();
            setupButtons(dialogDefinitionCustom, oCButton, oCButton2, oCButton3, oCBackButton, oCButton4, androidDialogPanelApp, customDialogViewModel, new Consumer() {
                /* class com.oculus.panelapp.androiddialog.dialogs.custom.$$Lambda$dD6_e6eGIv8wIlUu8XcSIAsQkPY */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AndroidDialogPanelApp.this.sendResult((String) obj);
                }
            });
            setupText(dialogDefinitionCustom, customDialogViewModel);
            setupProgressIndicator(dialogDefinitionCustom, customDialogViewModel);
        } catch (JSONException e) {
            Log.e(TAG, "dialog definition could not be parsed", e);
        }
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        return true;
    }
}
