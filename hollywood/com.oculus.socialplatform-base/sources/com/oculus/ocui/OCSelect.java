package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.common.ocui.databinding.OcselectBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class OCSelect<T> extends FrameLayout {
    public Map<T, Boolean> mAlertMap;
    public OCButton mButton;
    public final Context mContext;
    public OCDropdown mDropdown;
    public boolean mIsCompact;
    public OCSelectLogger mLogger;
    public T mSelectedItem;

    public void dismissDropdown() {
        this.mDropdown.dismiss();
    }

    public OCDropdown getDropdown() {
        return this.mDropdown;
    }

    public T getItem(int i) {
        return (T) this.mDropdown.getItem(i);
    }

    public int getItemCount() {
        return this.mDropdown.mAdapter.getItemCount();
    }

    public List<T> getItems() {
        return this.mDropdown.mAdapter.mItems;
    }

    public T getSelectedItem() {
        return this.mSelectedItem;
    }

    public /* synthetic */ void lambda$new$0$OCSelect(View view) {
        this.mDropdown.toggle(this.mButton);
        OCSelectLogger oCSelectLogger = this.mLogger;
        if (oCSelectLogger != null) {
            oCSelectLogger.onLogClick();
        }
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mButton.mEventHandler = oCEventHandler;
        this.mDropdown.setEventHandler(oCEventHandler);
    }

    public void setIconMap(Map map) {
        this.mDropdown.setIconMap(map);
    }

    public void setItems(List<T> list) {
        this.mDropdown.setItems(list);
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mLogger = oCSelectLogger;
        this.mDropdown.setLogger(oCSelectLogger);
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mDropdown.setOnItemClick(function);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
        if (r0 == false) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectedItem(T r5) {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocui.OCSelect.setSelectedItem(java.lang.Object):void");
    }

    public void setSubtitleMap(Map map) {
        this.mDropdown.setSubtitleMap(map);
    }

    public void setTitleMap(Map map) {
        this.mDropdown.setTitleMap(map);
    }

    public void setVisibilityCallback(OCDropdownVisibilityCallback oCDropdownVisibilityCallback) {
        this.mDropdown.mVisibilityCallback = oCDropdownVisibilityCallback;
    }

    public OCSelect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mDropdown = new OCDropdown(context);
        LayoutInflater.from(this.mContext).inflate(R.layout.ocselect, this);
        OCButton oCButton = OcselectBinding.inflate(LayoutInflater.from(this.mContext), this, true).dropdownButton;
        this.mButton = oCButton;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCSelect$OydhGbGyNuMA5DNL9Npv9Qi_ed02 */

            public final void onClick(View view) {
                OCSelect.this.lambda$new$0$OCSelect(view);
            }
        });
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, com.oculus.common.ocui.R.styleable.OCSelect);
        float dimension = obtainStyledAttributes.getDimension(0, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        if (dimension != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            this.mDropdown.setWidth((int) dimension);
        }
        obtainStyledAttributes.recycle();
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        this.mButton.setClickable(z);
    }

    public void setAlertMap(Map map) {
        this.mAlertMap = map;
    }

    public void setIsCompact(boolean z) {
        this.mIsCompact = z;
    }
}
