package com.oculus.panelapp.androiddialog.dialogs.load;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Observable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.dialogs.error.DialogErrorCallback;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialogAction;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoadingContainer extends ConstraintLayout implements Dialog, DialogErrorCallback {
    private static final int SPINNER_SIZE = 48;
    private static final String TAG = LoggingUtil.tag(LoadingContainer.class);
    private final Map<Observable, Observable.OnPropertyChangedCallback> mCallbacks;
    private List<View> mChildViews;
    private Dialog mDialog;
    private boolean mHasError;
    private final Map<Observable, Boolean> mObservableStates;
    private OCSpinner spinner;

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

    public void waitFor(Observable... observableArr) {
        AnonymousClass1 r0 = new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.load.LoadingContainer.AnonymousClass1 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                LoadingContainer.this.recordObserverChangeAndSetSpinner(observable);
            }
        };
        for (Observable observable : observableArr) {
            addObservable(observable, r0);
        }
    }

    public void waitFor(Observable observable, final int i) {
        addObservable(observable, new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.load.LoadingContainer.AnonymousClass2 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (i == i) {
                    LoadingContainer.this.recordObserverChangeAndSetSpinner(observable);
                }
            }
        });
    }

    public void waitFor(Pair<Observable, Integer>... pairArr) {
        for (Pair<Observable, Integer> pair : pairArr) {
            final Integer num = (Integer) pair.second;
            addObservable((Observable) pair.first, new Observable.OnPropertyChangedCallback() {
                /* class com.oculus.panelapp.androiddialog.dialogs.load.LoadingContainer.AnonymousClass3 */

                @Override // androidx.databinding.Observable.OnPropertyChangedCallback
                public void onPropertyChanged(Observable observable, int i) {
                    Integer num = num;
                    if (num == null || num.intValue() == i) {
                        LoadingContainer.this.recordObserverChangeAndSetSpinner(observable);
                    }
                }
            });
        }
    }

    private void addObservable(Observable observable, Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        this.mObservableStates.put(observable, false);
        this.mCallbacks.put(observable, onPropertyChangedCallback);
        observable.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void recordObserverChangeAndSetSpinner(Observable observable) {
        if (this.mObservableStates.containsKey(observable)) {
            this.mObservableStates.put(observable, true);
            if (validateLoadingState()) {
                hideSpinner();
            } else {
                showSpinner();
            }
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

    @Override // com.oculus.panelapp.androiddialog.dialogs.error.DialogErrorCallback
    public void onError(ErrorType errorType, ErrorDialogAction errorDialogAction) {
        removeCallbacks();
        hideSpinner();
        ErrorDialog.target(this).withAction(errorDialogAction).withError(errorType).show();
        this.mHasError = true;
    }

    private void removeCallbacks() {
        this.mObservableStates.clear();
        for (Observable observable : this.mCallbacks.keySet()) {
            observable.removeOnPropertyChangedCallback(this.mCallbacks.get(observable));
        }
        this.mCallbacks.clear();
    }

    private void addChildView(View view) {
        this.mChildViews.add(view);
        if (view instanceof Dialog) {
            this.mDialog = (Dialog) view;
        }
        addView(view, 0, centeredInParent());
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!validateLoadingState()) {
            if (this.spinner.getParent() == null) {
                super.addView(this.spinner, 0, centeredSpinner());
            }
            showSpinner();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void showSpinner() {
        this.spinner.setVisibility(0);
        for (View view : this.mChildViews) {
            view.setVisibility(4);
        }
    }

    private void hideSpinner() {
        this.spinner.setVisibility(4);
        for (View view : this.mChildViews) {
            view.setVisibility(0);
        }
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        removeCallbacks();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.destroy();
        }
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog.onControllerBackButton();
        }
        return false;
    }

    public boolean hasError() {
        return this.mHasError;
    }

    private static ConstraintLayout.LayoutParams centeredInParent() {
        return centerInParent(new ConstraintLayout.LayoutParams(-1, -2));
    }

    private static ConstraintLayout.LayoutParams centeredSpinner() {
        return centerInParent(new ConstraintLayout.LayoutParams(48, 48));
    }

    private static ConstraintLayout.LayoutParams centerInParent(ConstraintLayout.LayoutParams layoutParams) {
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        layoutParams.startToStart = 0;
        layoutParams.endToEnd = 0;
        return layoutParams;
    }

    public static LoadingContainer wrap(ViewGroup viewGroup) {
        LoadingContainer loadingContainer = new LoadingContainer(viewGroup.getContext());
        loadingContainer.addChildView(viewGroup);
        return loadingContainer;
    }
}
