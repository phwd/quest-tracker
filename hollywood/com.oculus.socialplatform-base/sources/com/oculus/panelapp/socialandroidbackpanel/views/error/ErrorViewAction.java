package com.oculus.panelapp.socialandroidbackpanel.views.error;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.socialplatform.R;

public class ErrorViewAction {
    @Nullable
    @StringRes
    public Integer mCtaText;
    @StringRes
    public int mDismissText;
    @Nullable
    public ErrorView.ErrorViewCallback.CTA mOnCTA;
    public ErrorView.ErrorViewCallback.Dismiss mOnDismiss;

    public static class ErrorActionBuilder {
        @Nullable
        @StringRes
        public Integer mCtaText = null;
        @StringRes
        public int mDismissText = R.string.error_dialog_dismiss_text;
        @Nullable
        public ErrorView.ErrorViewCallback.CTA mOnCTA = null;
        public final ErrorView.ErrorViewCallback.Dismiss mOnDismiss;

        public ErrorViewAction build() {
            return new ErrorViewAction(this.mCtaText, this.mOnCTA, this.mDismissText, this.mOnDismiss);
        }

        public ErrorActionBuilder(ErrorView.ErrorViewCallback.Dismiss dismiss) {
            this.mOnDismiss = dismiss;
        }

        public ErrorActionBuilder withCTAText(@StringRes int i) {
            this.mCtaText = Integer.valueOf(i);
            return this;
        }

        public ErrorActionBuilder withCTA(ErrorView.ErrorViewCallback.CTA cta) {
            this.mOnCTA = cta;
            return this;
        }

        public ErrorActionBuilder withDismissText(@StringRes int i) {
            this.mDismissText = i;
            return this;
        }
    }

    public static ErrorActionBuilder onDismiss(ErrorView.ErrorViewCallback.Dismiss dismiss) {
        return new ErrorActionBuilder(dismiss);
    }

    @Nullable
    public Integer getCtaText() {
        return this.mCtaText;
    }

    public int getDismissText() {
        return this.mDismissText;
    }

    public ErrorView.ErrorViewCallback.CTA getOnCTA() {
        return this.mOnCTA;
    }

    public ErrorView.ErrorViewCallback.Dismiss getOnDismiss() {
        return this.mOnDismiss;
    }

    public ErrorViewAction(@Nullable Integer num, @Nullable ErrorView.ErrorViewCallback.CTA cta, int i, ErrorView.ErrorViewCallback.Dismiss dismiss) {
        this.mCtaText = num;
        this.mOnCTA = cta;
        this.mDismissText = i;
        this.mOnDismiss = dismiss;
    }
}
