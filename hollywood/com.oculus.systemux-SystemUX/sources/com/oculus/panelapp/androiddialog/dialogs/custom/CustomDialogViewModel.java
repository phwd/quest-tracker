package com.oculus.panelapp.androiddialog.dialogs.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonIcon;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.systemdialog.DialogProgressIndicator;

public class CustomDialogViewModel extends BaseObservable {
    private static final String TAG = LoggingUtil.tag(CustomDialogViewModel.class);
    private DialogButton mBackButton = null;
    private String mBody = null;
    private DialogButtonIcon mIconButton = null;
    private DialogInformationBox mInformationBox = null;
    private DialogButtonText mPrimaryButton = null;
    private DialogProgressIndicator mProgressIndicator = null;
    private DialogButtonText mSecondaryButton = null;
    private DialogButtonText mTertiaryButton = null;
    private String mTitle = null;

    public void setTitle(String str) {
        this.mTitle = str;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getTitle() {
        return this.mTitle;
    }

    public void setBody(String str) {
        this.mBody = str;
        notifyPropertyChanged(BR.body);
    }

    @Bindable
    public String getBody() {
        return this.mBody;
    }

    public void setInformationBox(DialogInformationBox dialogInformationBox) {
        this.mInformationBox = dialogInformationBox;
        notifyPropertyChanged(BR.informationBoxText);
    }

    @Bindable
    public String getInformationBoxText() {
        DialogInformationBox dialogInformationBox = this.mInformationBox;
        if (dialogInformationBox != null) {
            return dialogInformationBox.getText();
        }
        return null;
    }

    public void setProgressIndicator(DialogProgressIndicator dialogProgressIndicator) {
        this.mProgressIndicator = dialogProgressIndicator;
        notifyPropertyChanged(BR.progressSpinnerVisible);
        notifyPropertyChanged(BR.progressBarVisible);
        notifyPropertyChanged(BR.progressBarProgress);
    }

    @Bindable
    public boolean getProgressSpinnerVisible() {
        DialogProgressIndicator dialogProgressIndicator = this.mProgressIndicator;
        if (dialogProgressIndicator == null || dialogProgressIndicator.getType() != DialogProgressIndicator.ProgressIndicatorType.SPINNER) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getProgressBarVisible() {
        DialogProgressIndicator dialogProgressIndicator = this.mProgressIndicator;
        if (dialogProgressIndicator == null || dialogProgressIndicator.getType() != DialogProgressIndicator.ProgressIndicatorType.BAR) {
            return false;
        }
        return true;
    }

    @Bindable
    public int getProgressBarProgress() {
        DialogProgressIndicator dialogProgressIndicator = this.mProgressIndicator;
        if (dialogProgressIndicator != null) {
            return Math.round(dialogProgressIndicator.getProgress() * 100.0f);
        }
        return 0;
    }

    public void setIconButton(DialogButtonIcon dialogButtonIcon) {
        this.mIconButton = dialogButtonIcon;
        notifyPropertyChanged(BR.iconButtonVisible);
        notifyPropertyChanged(BR.iconButtonIcon);
    }

    @Bindable
    public boolean getIconButtonVisible() {
        return this.mIconButton != null;
    }

    @Bindable
    public DialogIcon.IconButton getIconButtonIcon() {
        DialogButtonIcon dialogButtonIcon = this.mIconButton;
        return dialogButtonIcon == null ? DialogIcon.IconButton.INFO : dialogButtonIcon.getButtonIcon();
    }

    @BindingAdapter({"icon_button_drawable_start"})
    public static void setIconButtonDrawableStart(OCButton oCButton, DialogIcon.IconButton iconButton) {
        Context context = oCButton.getContext();
        int i = AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogIcon$IconButton[iconButton.ordinal()];
        if (i == 1) {
            oCButton.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.oc_icon_info_filled_24_d2d2d2), (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i != 2) {
            Log.e(TAG, "invalid icon type");
        } else {
            oCButton.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.oc_icon_settings_filled_24_d2d2d2), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    public void setBackButton(DialogButton dialogButton) {
        this.mBackButton = dialogButton;
        notifyPropertyChanged(BR.backButtonVisible);
    }

    @Bindable
    public boolean getBackButtonVisible() {
        return this.mBackButton != null;
    }

    @Nullable
    private static String getButtonText(@Nullable DialogButtonText dialogButtonText) {
        if (dialogButtonText != null) {
            return dialogButtonText.getText();
        }
        return null;
    }

    private static boolean getButtonDisabled(@Nullable DialogButtonText dialogButtonText) {
        if (dialogButtonText != null) {
            return dialogButtonText.getDisabled();
        }
        return false;
    }

    public void setPrimaryButton(DialogButtonText dialogButtonText) {
        this.mPrimaryButton = dialogButtonText;
        notifyPropertyChanged(BR.primaryButtonText);
        notifyPropertyChanged(BR.primaryButtonDisabled);
        notifyPropertyChanged(BR.primaryButtonStyle);
    }

    @Bindable
    public DialogPrimaryButtonStyle getPrimaryButtonStyle() {
        DialogButtonText dialogButtonText = this.mPrimaryButton;
        if (dialogButtonText == null) {
            return DialogPrimaryButtonStyle.PRIMARY;
        }
        if (dialogButtonText instanceof DialogButtonPrimary) {
            return ((DialogButtonPrimary) dialogButtonText).getStyle();
        }
        return DialogPrimaryButtonStyle.PRIMARY;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$systemdialog$DialogIcon$IconButton = new int[DialogIcon.IconButton.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle = new int[DialogPrimaryButtonStyle.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        static {
            /*
                com.oculus.systemdialog.DialogPrimaryButtonStyle[] r0 = com.oculus.systemdialog.DialogPrimaryButtonStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle = r0
                r0 = 1
                int[] r1 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.systemdialog.DialogPrimaryButtonStyle r2 = com.oculus.systemdialog.DialogPrimaryButtonStyle.PRIMARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.systemdialog.DialogPrimaryButtonStyle r3 = com.oculus.systemdialog.DialogPrimaryButtonStyle.SECONDARY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r2 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.systemdialog.DialogPrimaryButtonStyle r3 = com.oculus.systemdialog.DialogPrimaryButtonStyle.DANGER     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.oculus.systemdialog.DialogIcon$IconButton[] r2 = com.oculus.systemdialog.DialogIcon.IconButton.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogIcon$IconButton = r2
                int[] r2 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogIcon$IconButton     // Catch:{ NoSuchFieldError -> 0x003d }
                com.oculus.systemdialog.DialogIcon$IconButton r3 = com.oculus.systemdialog.DialogIcon.IconButton.INFO     // Catch:{ NoSuchFieldError -> 0x003d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogIcon$IconButton     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.oculus.systemdialog.DialogIcon$IconButton r2 = com.oculus.systemdialog.DialogIcon.IconButton.SETTINGS     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.custom.CustomDialogViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    @BindingAdapter({"primary_button_background"})
    public static void setPrimaryButtonBackground(View view, DialogPrimaryButtonStyle dialogPrimaryButtonStyle) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogPrimaryButtonStyle[dialogPrimaryButtonStyle.ordinal()];
        if (i == 1) {
            view.setBackgroundResource(R.drawable.ocbutton_primary);
        } else if (i == 2) {
            view.setBackgroundResource(R.drawable.ocbutton_secondary);
        } else if (i == 3) {
            view.setBackgroundResource(R.drawable.ocbutton_danger);
        }
    }

    @Bindable
    public String getPrimaryButtonText() {
        return getButtonText(this.mPrimaryButton);
    }

    @Bindable
    public boolean getPrimaryButtonDisabled() {
        return getButtonDisabled(this.mPrimaryButton);
    }

    public void setSecondaryButton(DialogButtonText dialogButtonText) {
        this.mSecondaryButton = dialogButtonText;
        notifyPropertyChanged(BR.secondaryButtonText);
        notifyPropertyChanged(BR.secondaryButtonDisabled);
    }

    @Bindable
    public String getSecondaryButtonText() {
        return getButtonText(this.mSecondaryButton);
    }

    @Bindable
    public boolean getSecondaryButtonDisabled() {
        return getButtonDisabled(this.mSecondaryButton);
    }

    public void setTertiaryButton(DialogButtonText dialogButtonText) {
        this.mTertiaryButton = dialogButtonText;
        notifyPropertyChanged(BR.tertiaryButtonText);
        notifyPropertyChanged(BR.tertiaryButtonDisabled);
    }

    @Bindable
    public String getTertiaryButtonText() {
        return getButtonText(this.mTertiaryButton);
    }

    @Bindable
    public boolean getTertiaryButtonDisabled() {
        return getButtonDisabled(this.mTertiaryButton);
    }
}
