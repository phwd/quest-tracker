package com.oculus.panelapp.socialandroidbackpanel.views.error;

import X.AnonymousClass2a8;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ErrorViewBinding;

public class ErrorView extends ConstraintLayout {
    public ErrorViewAction mAction;
    public ErrorViewBinding mBinding;
    public ErrorViewModel mErrorViewModel = new ErrorViewModel();

    public static class ErrorViewBuilder {
        public final ErrorViewAction mAction;
        public String mMessage;
        public final ConstraintLayout mTarget;
        public String mTitle;

        public ErrorView show() {
            ErrorViewBinding inflate = ErrorViewBinding.inflate(LayoutInflater.from(this.mTarget.getContext()), null);
            ErrorView errorView = (ErrorView) inflate.mRoot;
            errorView.initialize(inflate, this.mTitle, this.mMessage, this.mAction);
            errorView.attachTo(this.mTarget);
            return errorView;
        }

        public ErrorViewBuilder withError(ErrorType errorType) {
            this.mTitle = errorType.getTitle(this.mTarget.getContext());
            this.mMessage = errorType.getMessage(this.mTarget.getContext());
            return this;
        }

        public ErrorViewBuilder withMessage(String str) {
            this.mMessage = str;
            return this;
        }

        public ErrorViewBuilder withTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public ErrorViewBuilder(ConstraintLayout constraintLayout, ErrorViewAction errorViewAction) {
            this.mTarget = constraintLayout;
            this.mAction = errorViewAction;
        }
    }

    public static class ErrorViewCallback {

        @FunctionalInterface
        public interface CTA {
            void onCTA();
        }

        @FunctionalInterface
        public interface Dismiss {
            void onDismiss();
        }
    }

    public static class OnDismissBuilder {
        public final ConstraintLayout mTarget;

        public ErrorViewBuilder withAction(ErrorViewAction errorViewAction) {
            return new ErrorViewBuilder(this.mTarget, errorViewAction);
        }

        public OnDismissBuilder(ConstraintLayout constraintLayout) {
            this.mTarget = constraintLayout;
        }
    }

    public static AnonymousClass2a8 centeredInParent() {
        AnonymousClass2a8 r1 = new AnonymousClass2a8(-1, -2);
        r1.A0u = 0;
        r1.A0P = 0;
        r1.A0s = 0;
        r1.A0V = 0;
        return r1;
    }

    private void setupView() {
        setVisibility(0);
        setSiblingVisibility(8);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void attachTo(ConstraintLayout constraintLayout) {
        constraintLayout.addView(this.mBinding.mRoot, 0, centeredInParent());
        setupView();
    }

    private void setupBinding() {
        OCButton oCButton;
        Context context;
        this.mBinding.setErrorViewModel(this.mErrorViewModel);
        boolean z = this.mErrorViewModel.hasCta;
        OCButton oCButton2 = this.mBinding.errorViewSecondaryButton;
        if (z) {
            oCButton2.setVisibility(0);
            this.mBinding.errorViewPrimarySecondaryButton.setVisibility(0);
            this.mBinding.errorViewPrimaryButton.setVisibility(8);
            OCButton oCButton3 = this.mBinding.errorViewPrimarySecondaryButton;
            oCButton3.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.error.$$Lambda$ErrorView$stzhsy7rOfg4wmNa6wZ6_zy2pA2 */

                public final void onClick(View view) {
                    ErrorView.this.lambda$setupBinding$0$ErrorView(view);
                }
            });
            context = getContext();
            oCButton3.setText(context.getString(this.mAction.mCtaText.intValue()));
            oCButton = this.mBinding.errorViewSecondaryButton;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.error.$$Lambda$ErrorView$c3vCGxN7eRzUPUPjrRDK3MRWt82 */

                public final void onClick(View view) {
                    ErrorView.this.lambda$setupBinding$1$ErrorView(view);
                }
            });
        } else {
            oCButton2.setVisibility(8);
            this.mBinding.errorViewPrimarySecondaryButton.setVisibility(8);
            this.mBinding.errorViewPrimaryButton.setVisibility(0);
            oCButton = this.mBinding.errorViewPrimaryButton;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.error.$$Lambda$ErrorView$yuI85sd4H55bCHrzaiMS0IdiH7k2 */

                public final void onClick(View view) {
                    ErrorView.this.lambda$setupBinding$2$ErrorView(view);
                }
            });
            context = getContext();
        }
        oCButton.setText(context.getString(this.mAction.mDismissText));
    }

    public static OnDismissBuilder target(ConstraintLayout constraintLayout) {
        return new OnDismissBuilder(constraintLayout);
    }

    public void initialize(ErrorViewBinding errorViewBinding, String str, String str2, ErrorViewAction errorViewAction) {
        this.mBinding = errorViewBinding;
        this.mAction = errorViewAction;
        this.mErrorViewModel.setTitle(str);
        this.mErrorViewModel.setMessage(str2);
        ErrorViewAction errorViewAction2 = this.mAction;
        if (!(errorViewAction2.mOnCTA == null || errorViewAction2.mCtaText == null)) {
            this.mErrorViewModel.setHasCta(true);
        }
        setupBinding();
    }

    public /* synthetic */ void lambda$setupBinding$0$ErrorView(View view) {
        this.mAction.mOnCTA.onCTA();
    }

    public /* synthetic */ void lambda$setupBinding$1$ErrorView(View view) {
        this.mAction.mOnDismiss.onDismiss();
    }

    public /* synthetic */ void lambda$setupBinding$2$ErrorView(View view) {
        this.mAction.mOnDismiss.onDismiss();
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

    public ErrorView(Context context) {
        super(context);
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
