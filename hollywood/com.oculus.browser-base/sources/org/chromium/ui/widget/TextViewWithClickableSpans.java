package org.chromium.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupMenu;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextViewWithClickableSpans extends TextViewWithLeading implements View.OnLongClickListener {
    public AccessibilityManager F;
    public PopupMenu G;

    public TextViewWithClickableSpans(Context context) {
        super(context);
        d();
    }

    public final void a() {
        AccessibilityManager accessibilityManager = this.F;
        if (accessibilityManager != null) {
            setOnLongClickListener(accessibilityManager.isTouchExplorationEnabled() ? this : null);
        }
    }

    public ClickableSpan[] b() {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return null;
        }
        SpannableString spannableString = (SpannableString) text;
        return (ClickableSpan[]) spannableString.getSpans(0, spannableString.length(), ClickableSpan.class);
    }

    public final void c() {
        ClickableSpan[] b = b();
        if (b != null && b.length != 0) {
            if (b.length == 1) {
                b[0].onClick(this);
            } else {
                e();
            }
        }
    }

    public final void d() {
        setSaveEnabled(false);
        this.F = (AccessibilityManager) getContext().getSystemService("accessibility");
        a();
    }

    public final void e() {
        ClickableSpan[] b = b();
        if (!(b == null || b.length == 0 || this.G != null)) {
            SpannableString spannableString = (SpannableString) getText();
            PopupMenu popupMenu = new PopupMenu(getContext(), this);
            this.G = popupMenu;
            Menu menu = popupMenu.getMenu();
            for (ClickableSpan clickableSpan : b) {
                menu.add(spannableString.subSequence(spannableString.getSpanStart(clickableSpan), spannableString.getSpanEnd(clickableSpan))).setOnMenuItemClickListener(new MenuItem$OnMenuItemClickListenerC0812Ng1(this, clickableSpan));
            }
            this.G.setOnDismissListener(new C0873Og1(this));
            this.G.show();
        }
    }

    public boolean f(MotionEvent motionEvent) {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return false;
        }
        int x = ((int) motionEvent.getX()) - getTotalPaddingLeft();
        int y = ((int) motionEvent.getY()) - getTotalPaddingTop();
        int scrollX = getScrollX() + x;
        int scrollY = getScrollY() + y;
        Layout layout = getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), (float) scrollX);
        if (((ClickableSpan[]) ((SpannableString) text).getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class)).length > 0) {
            return true;
        }
        return false;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    public boolean onLongClick(View view) {
        if (!this.F.isTouchExplorationEnabled()) {
            return false;
        }
        e();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 || !this.F.isTouchExplorationEnabled() || f(motionEvent)) {
            return onTouchEvent;
        }
        c();
        return true;
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i != 8) {
            a();
        }
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(i, bundle);
        }
        c();
        return true;
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }
}
