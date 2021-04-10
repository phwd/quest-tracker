package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcselectBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class OCSelect<T> extends FrameLayout {
    private Map<T, Boolean> mAlertMap;
    private OCButton mButton;
    private final Context mContext;
    private OCDropdown mDropdown = new OCDropdown(this.mContext);
    private boolean mIsCompact;
    private OCSelectLogger mLogger;
    private T mSelectedItem;

    public OCSelect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        LayoutInflater.from(this.mContext).inflate(R.layout.ocselect, this);
        this.mButton = OcselectBinding.inflate(LayoutInflater.from(this.mContext), this, true).dropdownButton;
        this.mButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCSelect$j5uRZ7lLVQehuuHqH6CvXvWElAo */

            public final void onClick(View view) {
                OCSelect.this.lambda$new$12$OCSelect(view);
            }
        });
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.OCSelect);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.OCSelect_dropdownWidth, 0.0f);
        if (dimension != 0.0f) {
            this.mDropdown.setWidth((int) dimension);
        }
        obtainStyledAttributes.recycle();
    }

    public /* synthetic */ void lambda$new$12$OCSelect(View view) {
        this.mDropdown.toggle(this.mButton);
        OCSelectLogger oCSelectLogger = this.mLogger;
        if (oCSelectLogger != null) {
            oCSelectLogger.onLogClick();
        }
    }

    public void dismissDropdown() {
        this.mDropdown.dismiss();
    }

    public List<T> getItems() {
        return this.mDropdown.getItems();
    }

    public T getItem(int i) {
        return (T) this.mDropdown.getItem(i);
    }

    public T getSelectedItem() {
        return this.mSelectedItem;
    }

    public int getItemCount() {
        return this.mDropdown.getCount();
    }

    public void setItems(List<T> list) {
        this.mDropdown.setItems(list);
    }

    public void setSelectedItem(T t) {
        int i;
        this.mSelectedItem = t;
        if (!this.mIsCompact || this.mDropdown.getIconMap() == null) {
            this.mButton.setText(this.mDropdown.getTitleMap().get(t));
        } else {
            this.mButton.setText("");
        }
        if (this.mDropdown.getIconMap() != null) {
            this.mButton.setCompoundDrawablesRelativeWithIntrinsicBounds(this.mDropdown.getIconMap().get(t), (Drawable) null, this.mContext.getDrawable(R.drawable.oc_icon_chevron_down_filled_24_d2d2d2), (Drawable) null);
        }
        Map<T, Boolean> map = this.mAlertMap;
        if (map == null || !map.get(t).booleanValue()) {
            i = R.attr.ocPrimaryText;
        } else {
            i = R.attr.ocWarning;
        }
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i, typedValue, true);
        this.mButton.setTextColor(typedValue.data);
        this.mDropdown.setSelectedItem(t);
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mDropdown.setOnItemClick(function);
    }

    public void setTitleMap(Map map) {
        this.mDropdown.setTitleMap(map);
    }

    public void setSubtitleMap(Map map) {
        this.mDropdown.setSubtitleMap(map);
    }

    public void setIconMap(Map map) {
        this.mDropdown.setIconMap(map);
    }

    public void setAlertMap(Map map) {
        this.mAlertMap = map;
    }

    public void setVisibilityCallback(OCDropdownVisibilityCallback oCDropdownVisibilityCallback) {
        this.mDropdown.setVisibilityCallback(oCDropdownVisibilityCallback);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mButton.setEventHandler(oCEventHandler);
        this.mDropdown.setEventHandler(oCEventHandler);
    }

    public void setIsCompact(boolean z) {
        this.mIsCompact = z;
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        this.mButton.setClickable(z);
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mLogger = oCSelectLogger;
        this.mDropdown.setLogger(oCSelectLogger);
    }

    public View getDropdownContentView() {
        return this.mDropdown.getContentView();
    }
}
