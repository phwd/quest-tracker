package com.oculus.panelapp.androiddialog.dialogs.error;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.panelapp.androiddialog.databinding.ErrorDialogBinding;

public class ErrorDialog extends ConstraintLayout {
    private ErrorDialogAction mAction;
    private ErrorDialogBinding mBinding;
    private ErrorViewModel mErrorViewModel = new ErrorViewModel();

    public static class ErrorDialogCallback {

        @FunctionalInterface
        public interface CTA {
            void onCTA();
        }

        @FunctionalInterface
        public interface Dismiss {
            void onDismiss();
        }
    }

    public ErrorDialog(Context context) {
        super(context);
    }

    public ErrorDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(ErrorDialogBinding errorDialogBinding, String str, String str2, ErrorDialogAction errorDialogAction) {
        this.mBinding = errorDialogBinding;
        this.mAction = errorDialogAction;
        this.mErrorViewModel.setTitle(str);
        this.mErrorViewModel.setMessage(str2);
        if (!(this.mAction.getOnCTA() == null || this.mAction.getCtaText() == null)) {
            this.mErrorViewModel.setHasCta(true);
        }
        setupBinding();
    }

    private void setupBinding() {
        this.mBinding.setErrorViewModel(this.mErrorViewModel);
        if (this.mErrorViewModel.getHasCta()) {
            this.mBinding.errorDialogSecondaryButton.setVisibility(0);
            this.mBinding.errorDialogPrimarySecondaryButton.setVisibility(0);
            this.mBinding.errorDialogPrimaryButton.setVisibility(8);
            this.mBinding.errorDialogPrimarySecondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.error.$$Lambda$ErrorDialog$WoC9yJlbnGyWiFgYgYsVfAypN0 */

                public final void onClick(View view) {
                    ErrorDialog.this.lambda$setupBinding$4$ErrorDialog(view);
                }
            });
            this.mBinding.errorDialogPrimarySecondaryButton.setText(getContext().getString(this.mAction.getCtaText().intValue()));
            this.mBinding.errorDialogSecondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.androiddialog.dialogs.error.$$Lambda$ErrorDialog$EHjuuZrb1IJM3jLpWKNQaBd9iUw */

                public final void onClick(View view) {
                    ErrorDialog.this.lambda$setupBinding$5$ErrorDialog(view);
                }
            });
            this.mBinding.errorDialogSecondaryButton.setText(getContext().getString(this.mAction.getDismissText()));
            return;
        }
        this.mBinding.errorDialogSecondaryButton.setVisibility(8);
        this.mBinding.errorDialogPrimarySecondaryButton.setVisibility(8);
        this.mBinding.errorDialogPrimaryButton.setVisibility(0);
        this.mBinding.errorDialogPrimaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.error.$$Lambda$ErrorDialog$Cq2AOwGCDZrnv5g91ShnoFkO7A */

            public final void onClick(View view) {
                ErrorDialog.this.lambda$setupBinding$6$ErrorDialog(view);
            }
        });
        this.mBinding.errorDialogPrimaryButton.setText(getContext().getString(this.mAction.getDismissText()));
    }

    public /* synthetic */ void lambda$setupBinding$4$ErrorDialog(View view) {
        this.mAction.getOnCTA().onCTA();
    }

    public /* synthetic */ void lambda$setupBinding$5$ErrorDialog(View view) {
        this.mAction.getOnDismiss().onDismiss();
    }

    public /* synthetic */ void lambda$setupBinding$6$ErrorDialog(View view) {
        this.mAction.getOnDismiss().onDismiss();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void attachTo(ConstraintLayout constraintLayout) {
        constraintLayout.addView(this.mBinding.getRoot(), 0, centeredInParent());
        setupDialog();
    }

    private void setupDialog() {
        setVisibility(0);
        setSiblingVisibility(8);
    }

    private void setSiblingVisibility(int i) {
        ViewGroup viewGroup = (ViewGroup) getParent();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getId() != getId()) {
                childAt.setVisibility(i);
            }
        }
    }

    private static ConstraintLayout.LayoutParams centeredInParent() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-1, -2);
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        layoutParams.startToStart = 0;
        layoutParams.endToEnd = 0;
        return layoutParams;
    }

    public static OnDismissBuilder target(ConstraintLayout constraintLayout) {
        return new OnDismissBuilder(constraintLayout);
    }

    public static class OnDismissBuilder {
        private final ConstraintLayout mTarget;

        private OnDismissBuilder(ConstraintLayout constraintLayout) {
            this.mTarget = constraintLayout;
        }

        public ErrorDialogBuilder withAction(ErrorDialogAction errorDialogAction) {
            return new ErrorDialogBuilder(this.mTarget, errorDialogAction);
        }
    }

    public static class ErrorDialogBuilder {
        private final ErrorDialogAction mAction;
        private String mMessage;
        private final ConstraintLayout mTarget;
        private String mTitle;

        private ErrorDialogBuilder(ConstraintLayout constraintLayout, ErrorDialogAction errorDialogAction) {
            this.mTarget = constraintLayout;
            this.mAction = errorDialogAction;
        }

        public ErrorDialogBuilder withError(ErrorType errorType) {
            this.mTitle = errorType.getTitle(this.mTarget.getContext());
            this.mMessage = errorType.getMessage(this.mTarget.getContext());
            return this;
        }

        public ErrorDialogBuilder withTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public ErrorDialogBuilder withMessage(String str) {
            this.mMessage = str;
            return this;
        }

        public ErrorDialog show() {
            ErrorDialogBinding inflate = ErrorDialogBinding.inflate(LayoutInflater.from(this.mTarget.getContext()));
            ErrorDialog errorDialog = (ErrorDialog) inflate.getRoot();
            errorDialog.initialize(inflate, this.mTitle, this.mMessage, this.mAction);
            errorDialog.attachTo(this.mTarget);
            return errorDialog;
        }
    }
}
