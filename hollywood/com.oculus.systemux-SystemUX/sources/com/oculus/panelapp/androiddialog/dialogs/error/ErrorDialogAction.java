package com.oculus.panelapp.androiddialog.dialogs.error;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;

public class ErrorDialogAction {
    @Nullable
    @StringRes
    private Integer mCtaText;
    @StringRes
    private int mDismissText;
    @Nullable
    private ErrorDialog.ErrorDialogCallback.CTA mOnCTA;
    private ErrorDialog.ErrorDialogCallback.Dismiss mOnDismiss;

    private ErrorDialogAction(@Nullable Integer num, @Nullable ErrorDialog.ErrorDialogCallback.CTA cta, int i, ErrorDialog.ErrorDialogCallback.Dismiss dismiss) {
        this.mCtaText = num;
        this.mOnCTA = cta;
        this.mDismissText = i;
        this.mOnDismiss = dismiss;
    }

    @Nullable
    public Integer getCtaText() {
        return this.mCtaText;
    }

    public ErrorDialog.ErrorDialogCallback.CTA getOnCTA() {
        return this.mOnCTA;
    }

    public int getDismissText() {
        return this.mDismissText;
    }

    public ErrorDialog.ErrorDialogCallback.Dismiss getOnDismiss() {
        return this.mOnDismiss;
    }

    public static ErrorActionBuilder onDismiss(ErrorDialog.ErrorDialogCallback.Dismiss dismiss) {
        return new ErrorActionBuilder(dismiss);
    }

    public static class ErrorActionBuilder {
        @Nullable
        @StringRes
        private Integer mCtaText = null;
        @StringRes
        private int mDismissText = R.string.error_dialog_dismiss_text;
        @Nullable
        private ErrorDialog.ErrorDialogCallback.CTA mOnCTA = null;
        private final ErrorDialog.ErrorDialogCallback.Dismiss mOnDismiss;

        public ErrorActionBuilder(ErrorDialog.ErrorDialogCallback.Dismiss dismiss) {
            this.mOnDismiss = dismiss;
        }

        public ErrorActionBuilder withCTAText(@StringRes int i) {
            this.mCtaText = Integer.valueOf(i);
            return this;
        }

        public ErrorActionBuilder withCTA(ErrorDialog.ErrorDialogCallback.CTA cta) {
            this.mOnCTA = cta;
            return this;
        }

        public ErrorActionBuilder withDismissText(@StringRes int i) {
            this.mDismissText = i;
            return this;
        }

        public ErrorDialogAction build() {
            return new ErrorDialogAction(this.mCtaText, this.mOnCTA, this.mDismissText, this.mOnDismiss);
        }
    }
}
