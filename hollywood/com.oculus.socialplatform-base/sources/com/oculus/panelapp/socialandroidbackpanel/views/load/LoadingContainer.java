package com.oculus.panelapp.socialandroidbackpanel.views.load;

import X.AbstractC12091uu;
import X.AbstractC12101uv;
import X.AnonymousClass2a8;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewAction;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoadingContainer extends ConstraintLayout implements ErrorCallback, SocialAndroidBackPanelView {
    public static final int SPINNER_SIZE = 48;
    public static final String TAG = LoggingUtil.tag(LoadingContainer.class);
    public final Map<AbstractC12101uv, AbstractC12091uu> mCallbacks;
    public List<View> mChildViews;
    public boolean mHasError;
    public final Map<AbstractC12101uv, Boolean> mObservableStates;
    public SocialAndroidBackPanelView mView;
    public OCSpinner spinner;

    public static AnonymousClass2a8 centerInParent(AnonymousClass2a8 r1) {
        r1.A0u = 0;
        r1.A0P = 0;
        r1.A0s = 0;
        r1.A0V = 0;
        return r1;
    }

    public static AnonymousClass2a8 centeredInParent() {
        AnonymousClass2a8 r0 = new AnonymousClass2a8(-1, -2);
        centerInParent(r0);
        return r0;
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public void onShow(String str) {
    }

    private void addChildView(View view) {
        this.mChildViews.add(view);
        if (view instanceof SocialAndroidBackPanelView) {
            this.mView = (SocialAndroidBackPanelView) view;
        }
        addView(view, 0, centeredInParent());
    }

    private void addObservable(AbstractC12101uv r3, AbstractC12091uu r4) {
        this.mObservableStates.put(r3, false);
        this.mCallbacks.put(r3, r4);
        r3.addOnPropertyChangedCallback(r4);
    }

    public static AnonymousClass2a8 centeredSpinner() {
        AnonymousClass2a8 r0 = new AnonymousClass2a8(48, 48);
        centerInParent(r0);
        return r0;
    }

    private void hideSpinner() {
        this.spinner.setVisibility(4);
        for (View view : this.mChildViews) {
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void recordObserverChangeAndSetSpinner(AbstractC12101uv r3) {
        if (this.mObservableStates.containsKey(r3)) {
            this.mObservableStates.put(r3, true);
            if (validateLoadingState()) {
                hideSpinner();
            } else {
                showSpinner();
            }
        }
    }

    private void removeCallbacks() {
        this.mObservableStates.clear();
        for (AbstractC12101uv r1 : this.mCallbacks.keySet()) {
            r1.removeOnPropertyChangedCallback(this.mCallbacks.get(r1));
        }
        this.mCallbacks.clear();
    }

    private void showSpinner() {
        this.spinner.setVisibility(0);
        for (View view : this.mChildViews) {
            view.setVisibility(4);
        }
    }

    private boolean validateLoadingState() {
        if (this.mObservableStates.isEmpty()) {
            Log.w(TAG, "validateLoadingState: No observables provided, will remain in loading state");
        }
        for (Boolean bool : this.mObservableStates.values()) {
            if (!bool.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @VisibleForTesting
    public List<View> getChildViews() {
        return this.mChildViews;
    }

    public boolean hasError() {
        return this.mHasError;
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public boolean onControllerBackButton() {
        SocialAndroidBackPanelView socialAndroidBackPanelView = this.mView;
        if (socialAndroidBackPanelView != null) {
            return socialAndroidBackPanelView.onControllerBackButton();
        }
        return false;
    }

    public static LoadingContainer wrap(ViewGroup viewGroup) {
        LoadingContainer loadingContainer = new LoadingContainer(viewGroup.getContext());
        loadingContainer.addChildView(viewGroup);
        return loadingContainer;
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public void destroy() {
        removeCallbacks();
        SocialAndroidBackPanelView socialAndroidBackPanelView = this.mView;
        if (socialAndroidBackPanelView != null) {
            socialAndroidBackPanelView.destroy();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!validateLoadingState()) {
            if (this.spinner.getParent() == null) {
                super.addView(this.spinner, 0, centeredSpinner());
            }
            showSpinner();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback
    public void onError(ErrorType errorType, ErrorViewAction errorViewAction) {
        removeCallbacks();
        hideSpinner();
        ErrorView.ErrorViewBuilder withAction = new ErrorView.OnDismissBuilder(this).withAction(errorViewAction);
        withAction.withError(errorType);
        withAction.show();
        this.mHasError = true;
    }

    public LoadingContainer(Context context) {
        super(context);
        this.mObservableStates = new HashMap();
        this.mCallbacks = new HashMap();
        this.mChildViews = new LinkedList();
        this.mHasError = false;
        this.spinner = new OCSpinner(getContext(), null);
    }

    public LoadingContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mObservableStates = new HashMap();
        this.mCallbacks = new HashMap();
        this.mChildViews = new LinkedList();
        this.mHasError = false;
        this.spinner = new OCSpinner(getContext(), attributeSet);
    }

    public void waitFor(AbstractC12101uv r2, final int i) {
        addObservable(r2, new AbstractC12091uu() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.load.LoadingContainer.AnonymousClass2 */

            @Override // X.AbstractC12091uu
            public void onPropertyChanged(AbstractC12101uv r2, int i) {
                if (i == i) {
                    LoadingContainer.this.recordObserverChangeAndSetSpinner(r2);
                }
            }
        });
    }

    public void waitFor(Pair<AbstractC12101uv, Integer>... pairArr) {
        for (Pair<AbstractC12101uv, Integer> pair : pairArr) {
            final Integer num = (Integer) pair.second;
            addObservable((AbstractC12101uv) pair.first, new AbstractC12091uu() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.load.LoadingContainer.AnonymousClass3 */

                @Override // X.AbstractC12091uu
                public void onPropertyChanged(AbstractC12101uv r2, int i) {
                    Integer num = num;
                    if (num == null || num.intValue() == i) {
                        LoadingContainer.this.recordObserverChangeAndSetSpinner(r2);
                    }
                }
            });
        }
    }

    public void waitFor(AbstractC12101uv... r5) {
        AnonymousClass1 r3 = new AbstractC12091uu() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.load.LoadingContainer.AnonymousClass1 */

            @Override // X.AbstractC12091uu
            public void onPropertyChanged(AbstractC12101uv r2, int i) {
                LoadingContainer.this.recordObserverChangeAndSetSpinner(r2);
            }
        };
        for (AbstractC12101uv r0 : r5) {
            addObservable(r0, r3);
        }
    }
}
