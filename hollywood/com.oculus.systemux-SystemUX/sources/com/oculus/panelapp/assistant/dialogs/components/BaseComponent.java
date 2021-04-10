package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.assistant.dialogs.ISurface;
import com.oculus.vrshell.panels.DensityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseComponent extends FrameLayout implements ActionClickListener {
    private static final String TAG = "AssistantBaseComponent";
    private ActionClickListener mActionClickListener;
    private OCEventHandler mEventHandler;
    private int mHeight;
    private String mId;
    private View.OnClickListener mOnClickListener;
    private View.OnHoverListener mOnHoverListener;
    private ISurface mSurface;
    private View mView;
    private int mWidth;

    /* access modifiers changed from: protected */
    public void onAddResultData(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract View onCreateView(Context context);

    public BaseComponent(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public BaseComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setView(onCreateView(context));
    }

    /* access modifiers changed from: protected */
    public void setView(View view) {
        View view2 = this.mView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mView = view;
        addView(view);
    }

    public void initialize(ISurface iSurface) {
        this.mSurface = iSurface;
        this.mEventHandler = iSurface.getApp();
    }

    public String getComponentId() {
        return this.mId;
    }

    public void setActionClickListener(ActionClickListener actionClickListener) {
        this.mActionClickListener = actionClickListener;
    }

    public void sendActionClick(String str) {
        sendActionClick(str, null);
    }

    public void sendActionClick(String str, Bundle bundle) {
        if (!onActionClick(str, bundle)) {
            getSurface().getApp().sendAction(this.mSurface, str, bundle);
        }
    }

    public final void addResultData(Bundle bundle) {
        onAddResultData(bundle);
    }

    public final void applyJson(ISurface iSurface, JSONObject jSONObject) throws JSONException {
        initialize(iSurface);
        if (jSONObject.has("data")) {
            onApplyJson(jSONObject.getJSONObject("data"));
        } else {
            onApplyJson(jSONObject);
        }
    }

    public ISurface getSurface() {
        return this.mSurface;
    }

    public void setSize(Integer num, Integer num2) {
        if (num != null || num2 != null) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-2, -2);
            }
            if (num != null) {
                layoutParams.width = num.intValue();
                layoutParams2.width = num.intValue();
                this.mWidth = num.intValue();
            }
            if (num2 != null) {
                layoutParams.height = num2.intValue();
                layoutParams2.height = num2.intValue();
                this.mHeight = num2.intValue();
            }
            this.mView.setLayoutParams(layoutParams2);
            setLayoutParams(layoutParams);
            postInvalidate();
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setWidth(int i) {
        setSize(Integer.valueOf(i), null);
    }

    public void setHeight(int i) {
        setSize(null, Integer.valueOf(i));
    }

    public int getRequestedWidth() {
        return this.mWidth;
    }

    public int getRequestedHeight() {
        return this.mHeight;
    }

    public static int getDimension(Context context, JSONObject jSONObject, String str) throws JSONException {
        return DensityUtils.dipToPixelsInt((float) jSONObject.getInt(str), context.getResources().getDisplayMetrics());
    }

    public static int optDimension(Context context, JSONObject jSONObject, String str, int i) throws JSONException {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (jSONObject.has(str)) {
            i = jSONObject.getInt(str);
        }
        return i > 0 ? DensityUtils.dipToPixelsInt((float) i, displayMetrics) : i;
    }

    /* access modifiers changed from: protected */
    public int getDimension(JSONObject jSONObject, String str) throws JSONException {
        return getDimension(getContext(), jSONObject, str);
    }

    /* access modifiers changed from: protected */
    public int optDimension(JSONObject jSONObject, String str, int i) throws JSONException {
        return optDimension(getContext(), jSONObject, str, i);
    }

    public void setComponentId(String str) {
        this.mId = str;
    }

    /* access modifiers changed from: protected */
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        Integer num = null;
        Integer valueOf = jSONObject.has("width") ? Integer.valueOf(getDimension(jSONObject, "width")) : null;
        if (jSONObject.has("height")) {
            num = Integer.valueOf(getDimension(jSONObject, "height"));
        }
        this.mId = jSONObject.optString("id", "");
        setSize(valueOf, num);
        if (jSONObject.has(AssistantComponentContract.Padding.PADDING)) {
            Object obj = jSONObject.get(AssistantComponentContract.Padding.PADDING);
            if (obj instanceof JSONObject) {
                JSONObject jSONObject2 = (JSONObject) obj;
                this.mView.setPadding(optDimension(jSONObject2, "left", getPaddingLeft()), optDimension(jSONObject2, "top", getPaddingTop()), optDimension(jSONObject2, "right", getPaddingRight()), optDimension(jSONObject2, "bottom", getPaddingBottom()));
            } else {
                int dimension = getDimension(jSONObject, AssistantComponentContract.Padding.PADDING);
                this.mView.setPadding(dimension, dimension, dimension, dimension);
            }
        }
        parseBackgroundColor(jSONObject, this.mView);
    }

    protected static void parseBackgroundColor(JSONObject jSONObject, View view) throws JSONException {
        if (jSONObject.has(AssistantComponentContract.Components.BaseComponent.BACKGROUND_COLOR)) {
            try {
                view.setBackgroundColor(jSONObject.getInt(AssistantComponentContract.Components.BaseComponent.BACKGROUND_COLOR));
            } catch (JSONException unused) {
                String string = jSONObject.getString(AssistantComponentContract.Components.BaseComponent.BACKGROUND_COLOR);
                try {
                    view.setBackgroundColor(Color.parseColor(string));
                } catch (IllegalArgumentException unused2) {
                    Log.w(TAG, "Invalid color provided: " + string);
                }
            }
        }
    }

    @Override // com.oculus.panelapp.assistant.dialogs.components.ActionClickListener
    public boolean onActionClick(String str, Bundle bundle) {
        ActionClickListener actionClickListener = this.mActionClickListener;
        return actionClickListener != null && actionClickListener.onActionClick(str, bundle);
    }

    public static int parseGravity(JSONObject jSONObject) throws JSONException {
        return parseGravity(jSONObject, "gravity");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int parseGravity(JSONObject jSONObject, String str) throws JSONException {
        char c;
        if (!jSONObject.has(str)) {
            return 0;
        }
        String string = jSONObject.getString(str);
        if (TextUtils.isDigitsOnly(string)) {
            return Integer.parseInt(string);
        }
        String[] split = string.split("\\|");
        int i = 0;
        for (String str2 : split) {
            switch (str2.hashCode()) {
                case -1383228885:
                    if (str2.equals("bottom")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1364013995:
                    if (str2.equals(AssistantComponentContract.Gravity.CENTER)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -170316306:
                    if (str2.equals(AssistantComponentContract.Gravity.CENTER_VERTICAL)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 115029:
                    if (str2.equals("top")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 3317767:
                    if (str2.equals("left")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 108511772:
                    if (str2.equals("right")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 716870812:
                    if (str2.equals(AssistantComponentContract.Gravity.CENTER_HORIZONTAL)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    i |= 17;
                    break;
                case 1:
                    i |= 1;
                    break;
                case 2:
                    i |= 16;
                    break;
                case 3:
                    i |= 3;
                    break;
                case 4:
                    i |= 5;
                    break;
                case 5:
                    i |= 48;
                    break;
                case 6:
                    i |= 80;
                    break;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void onInteractableHovered() {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonEnter();
        }
    }

    /* access modifiers changed from: protected */
    public void onInteractableClicked() {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
    }

    /* access modifiers changed from: protected */
    public void addOculusInteractions(View view, View.OnClickListener onClickListener) {
        this.mEventHandler = getSurface().getApp();
        view.setOnHoverListener(new View.OnHoverListener(view) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$BaseComponent$ub7nVnf0c4zTO8UouWqNll5Nbw */
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return BaseComponent.this.lambda$addOculusInteractions$18$BaseComponent(this.f$1, view, motionEvent);
            }
        });
        view.setOnClickListener(new View.OnClickListener(onClickListener) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$BaseComponent$480u5wJ9Eaymp86YmjakSUJa5w */
            private final /* synthetic */ View.OnClickListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BaseComponent.this.lambda$addOculusInteractions$19$BaseComponent(this.f$1, view);
            }
        });
    }

    public /* synthetic */ boolean lambda$addOculusInteractions$18$BaseComponent(View view, View view2, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            onInteractableHovered();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public /* synthetic */ void lambda$addOculusInteractions$19$BaseComponent(View.OnClickListener onClickListener, View view) {
        onInteractableClicked();
        View.OnClickListener onClickListener2 = this.mOnClickListener;
        if (onClickListener2 != null) {
            onClickListener2.onClick(view);
        }
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
