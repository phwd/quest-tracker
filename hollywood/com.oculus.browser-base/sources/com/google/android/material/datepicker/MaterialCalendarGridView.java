package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.oculus.browser.R;
import java.util.Calendar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MaterialCalendarGridView extends GridView {
    public final Calendar F = AbstractC2255ds1.e();

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        if (C1531Zc0.m1(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        AbstractC1920bu1.n(this, new C1165Tc0(this));
    }

    /* renamed from: a */
    public C0032Al0 getAdapter() {
        return (C0032Al0) super.getAdapter();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    public final void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        C0032Al0 a2 = getAdapter();
        DateSelector dateSelector = a2.H;
        C0702Ll ll = a2.I;
        Long b = a2.getItem(a2.a());
        Long b2 = a2.getItem(a2.c());
        for (C1754aw0 aw0 : dateSelector.n()) {
            Object obj = aw0.f9500a;
            if (obj != null) {
                if (aw0.b == null) {
                    continue;
                } else {
                    long longValue = ((Long) obj).longValue();
                    long longValue2 = ((Long) aw0.b).longValue();
                    Long valueOf = Long.valueOf(longValue);
                    Long valueOf2 = Long.valueOf(longValue2);
                    int i5 = 1;
                    if (!(b == null || b2 == null || valueOf == null || valueOf2 == null || valueOf.longValue() > b2.longValue() || valueOf2.longValue() < b.longValue())) {
                        if (longValue < b.longValue()) {
                            i2 = a2.a();
                            if (i2 % a2.G.f9692J == 0) {
                                i = 0;
                            } else {
                                i = materialCalendarGridView.getChildAt(i2 - 1).getRight();
                            }
                        } else {
                            materialCalendarGridView.F.setTimeInMillis(longValue);
                            i2 = (materialCalendarGridView.F.get(5) - 1) + a2.a();
                            View childAt = materialCalendarGridView.getChildAt(i2);
                            i = (childAt.getWidth() / 2) + childAt.getLeft();
                        }
                        if (longValue2 > b2.longValue()) {
                            i4 = a2.c();
                            if ((i4 + 1) % a2.G.f9692J == 0) {
                                i3 = getWidth();
                            } else {
                                i3 = materialCalendarGridView.getChildAt(i4).getRight();
                            }
                        } else {
                            materialCalendarGridView.F.setTimeInMillis(longValue2);
                            i4 = (materialCalendarGridView.F.get(5) - 1) + a2.a();
                            View childAt2 = materialCalendarGridView.getChildAt(i4);
                            i3 = (childAt2.getWidth() / 2) + childAt2.getLeft();
                        }
                        int itemId = (int) a2.getItemId(i2);
                        int itemId2 = (int) a2.getItemId(i4);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            int numColumns2 = (getNumColumns() + numColumns) - i5;
                            View childAt3 = materialCalendarGridView.getChildAt(numColumns);
                            canvas.drawRect((float) (numColumns > i2 ? 0 : i), (float) (childAt3.getTop() + ll.f8435a.f8384a.top), (float) (i4 > numColumns2 ? getWidth() : i3), (float) (childAt3.getBottom() - ll.f8435a.f8384a.bottom), ll.h);
                            itemId++;
                            i5 = 1;
                            materialCalendarGridView = this;
                        }
                    } else {
                        return;
                    }
                }
            }
            materialCalendarGridView = this;
        }
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
        } else if (i == 33) {
            setSelection(getAdapter().c());
        } else if (i == 130) {
            setSelection(getAdapter().a());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().a()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(getAdapter().a());
        return true;
    }

    public void setSelection(int i) {
        if (i < getAdapter().a()) {
            super.setSelection(getAdapter().a());
        } else {
            super.setSelection(i);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView
    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof C0032Al0) {
            super.setAdapter(listAdapter);
        } else {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), C0032Al0.class.getCanonicalName()));
        }
    }
}
