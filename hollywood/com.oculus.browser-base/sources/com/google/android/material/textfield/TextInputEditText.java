package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.oculus.browser.R;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextInputEditText extends C4011o8 {
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public boolean f9695J;

    public TextInputEditText(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f4110_resource_name_obfuscated_RES_2130968857, 0), attributeSet, R.attr.f4110_resource_name_obfuscated_RES_2130968857);
        int[] iArr = FJ0.J0;
        AbstractC1178Tg1.a(context, attributeSet, R.attr.f4110_resource_name_obfuscated_RES_2130968857, R.style.f75010_resource_name_obfuscated_RES_2132018074);
        AbstractC1178Tg1.b(context, attributeSet, iArr, R.attr.f4110_resource_name_obfuscated_RES_2130968857, R.style.f75010_resource_name_obfuscated_RES_2132018074, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.f4110_resource_name_obfuscated_RES_2130968857, R.style.f75010_resource_name_obfuscated_RES_2132018074);
        this.f9695J = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
    }

    public final TextInputLayout a() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    public void getFocusedRect(Rect rect) {
        super.getFocusedRect(rect);
        TextInputLayout a2 = a();
        if (a2 != null && this.f9695J && rect != null) {
            a2.getFocusedRect(this.I);
            rect.bottom = this.I.bottom;
        }
    }

    public boolean getGlobalVisibleRect(Rect rect, Point point) {
        boolean globalVisibleRect = super.getGlobalVisibleRect(rect, point);
        TextInputLayout a2 = a();
        if (!(a2 == null || !this.f9695J || rect == null)) {
            a2.getGlobalVisibleRect(this.I, point);
            rect.bottom = this.I.bottom;
        }
        return globalVisibleRect;
    }

    public CharSequence getHint() {
        TextInputLayout a2 = a();
        if (a2 == null || !a2.i0) {
            return super.getHint();
        }
        return a2.j();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout a2 = a();
        if (a2 != null && a2.i0 && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint("");
        }
    }

    @Override // defpackage.C4011o8
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && editorInfo.hintText == null) {
            TextInputLayout a2 = a();
            editorInfo.hintText = a2 != null ? a2.j() : null;
        }
        return onCreateInputConnection;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        a();
    }

    public boolean requestRectangleOnScreen(Rect rect) {
        boolean requestRectangleOnScreen = super.requestRectangleOnScreen(rect);
        TextInputLayout a2 = a();
        if (a2 != null && this.f9695J) {
            this.I.set(0, a2.getHeight() - getResources().getDimensionPixelOffset(R.dimen.f21970_resource_name_obfuscated_RES_2131165816), a2.getWidth(), a2.getHeight());
            a2.requestRectangleOnScreen(this.I, true);
        }
        return requestRectangleOnScreen;
    }
}
