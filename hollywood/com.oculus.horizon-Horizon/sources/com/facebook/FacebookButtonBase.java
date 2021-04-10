package com.facebook;

import X.AnonymousClass04J;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FragmentWrapper;
import com.oculus.horizon.R;

public abstract class FacebookButtonBase extends Button {
    public String analyticsButtonCreatedEventName;
    public String analyticsButtonTappedEventName;
    public View.OnClickListener externalOnClickListener;
    public View.OnClickListener internalOnClickListener;
    public boolean overrideCompoundPadding;
    public int overrideCompoundPaddingLeft;
    public int overrideCompoundPaddingRight;
    public FragmentWrapper parentFragment;

    public FacebookButtonBase(Context context, AttributeSet attributeSet, int i, int i2, String str, String str2) {
        super(context, attributeSet, 0);
        configureButton(context, attributeSet, i, i2 == 0 ? R.style.com_facebook_button : i2);
        this.analyticsButtonCreatedEventName = str;
        this.analyticsButtonTappedEventName = str2;
    }

    private void logButtonCreated(Context context) {
        AppEventsLogger.A02(new AppEventsLogger(context, null), this.analyticsButtonCreatedEventName, null, true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logButtonTapped(Context context) {
        AppEventsLogger.A02(new AppEventsLogger(context, null), this.analyticsButtonTappedEventName, null, true);
    }

    @SuppressLint({"ResourceType"})
    private void parseCompoundDrawableAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16843119, 16843117, 16843120, 16843118, 16843121}, i, i2);
        try {
            setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getResourceId(0, 0), obtainStyledAttributes.getResourceId(1, 0), obtainStyledAttributes.getResourceId(2, 0), obtainStyledAttributes.getResourceId(3, 0));
            setCompoundDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(4, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void parseContentAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842966, 16842967, 16842968, 16842969}, i, i2);
        try {
            setPadding(obtainStyledAttributes.getDimensionPixelSize(0, 0), obtainStyledAttributes.getDimensionPixelSize(1, 0), obtainStyledAttributes.getDimensionPixelSize(2, 0), obtainStyledAttributes.getDimensionPixelSize(3, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void parseTextAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842904}, i, i2);
        try {
            setTextColor(obtainStyledAttributes.getColor(0, -1));
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842927}, i, i2);
            setGravity(obtainStyledAttributes2.getInt(0, 17));
            obtainStyledAttributes2.recycle();
            obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842901, 16842903, 16843087}, i, i2);
            setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(0, 0));
            setTypeface(Typeface.defaultFromStyle(obtainStyledAttributes.getInt(1, 1)));
            setText(obtainStyledAttributes.getString(2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public abstract int getDefaultRequestCode();

    public int getDefaultStyleResource() {
        return 0;
    }

    private void setupOnClickListener() {
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.facebook.FacebookButtonBase.AnonymousClass1 */

            public void onClick(View view) {
                FacebookButtonBase facebookButtonBase = FacebookButtonBase.this;
                facebookButtonBase.logButtonTapped(facebookButtonBase.getContext());
                FacebookButtonBase facebookButtonBase2 = FacebookButtonBase.this;
                View.OnClickListener onClickListener = facebookButtonBase2.internalOnClickListener;
                if (onClickListener != null || (onClickListener = facebookButtonBase2.externalOnClickListener) != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }

    public void callExternalOnClickListener(View view) {
        View.OnClickListener onClickListener = this.externalOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public int getCompoundPaddingLeft() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingLeft;
        }
        return super.getCompoundPaddingLeft();
    }

    public int getCompoundPaddingRight() {
        if (this.overrideCompoundPadding) {
            return this.overrideCompoundPaddingRight;
        }
        return super.getCompoundPaddingRight();
    }

    public Fragment getFragment() {
        FragmentWrapper fragmentWrapper = this.parentFragment;
        if (fragmentWrapper != null) {
            return fragmentWrapper.supportFragment;
        }
        return null;
    }

    public android.app.Fragment getNativeFragment() {
        FragmentWrapper fragmentWrapper = this.parentFragment;
        if (fragmentWrapper != null) {
            return fragmentWrapper.nativeFragment;
        }
        return null;
    }

    private void parseBackgroundAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842964}, i, i2);
            try {
                if (obtainStyledAttributes.hasValue(0)) {
                    int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        setBackgroundResource(resourceId);
                    } else {
                        setBackgroundColor(obtainStyledAttributes.getColor(0, 0));
                    }
                } else {
                    setBackgroundColor(AnonymousClass04J.A00(context, R.color.com_facebook_blue));
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        parseBackgroundAttributes(context, attributeSet, i, i2);
        parseCompoundDrawableAttributes(context, attributeSet, i, i2);
        parseContentAttributes(context, attributeSet, i, i2);
        parseTextAttributes(context, attributeSet, i, i2);
        setupOnClickListener();
    }

    public Activity getActivity() {
        Context context = getContext();
        while (!(context instanceof Activity)) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                throw new FacebookException("Unable to get Activity.");
            }
        }
        return (Activity) context;
    }

    public int getRequestCode() {
        throw null;
    }

    public int measureTextWidth(String str) {
        return (int) Math.ceil((double) getPaint().measureText(str));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            logButtonCreated(getContext());
        }
    }

    public void onDraw(Canvas canvas) {
        if ((getGravity() & 1) != 0) {
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingRight = getCompoundPaddingRight();
            int min = Math.min((((getWidth() - (getCompoundDrawablePadding() + compoundPaddingLeft)) - compoundPaddingRight) - measureTextWidth(getText().toString())) >> 1, (compoundPaddingLeft - getPaddingLeft()) >> 1);
            this.overrideCompoundPaddingLeft = compoundPaddingLeft - min;
            this.overrideCompoundPaddingRight = compoundPaddingRight + min;
            this.overrideCompoundPadding = true;
        }
        super.onDraw(canvas);
        this.overrideCompoundPadding = false;
    }

    public void setInternalOnClickListener(View.OnClickListener onClickListener) {
        this.internalOnClickListener = onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.externalOnClickListener = onClickListener;
    }

    public void setFragment(android.app.Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }
}
