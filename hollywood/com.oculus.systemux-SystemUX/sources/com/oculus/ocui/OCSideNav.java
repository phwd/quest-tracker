package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcsidenavBinding;
import com.oculus.ocui.OCSideNavAdapter;
import java.util.List;
import java.util.function.Function;

public class OCSideNav extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(OCSideNav.class);
    private OCSideNavAdapter mAdapter;
    private OcsidenavBinding mBinding;
    private Context mContext;

    public OCSideNav(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mBinding = OcsidenavBinding.inflate(LayoutInflater.from(context), this, true);
        initializeRecyclerView();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OCSideNav, 0, 0);
        try {
            this.mBinding.setTitle(obtainStyledAttributes.getString(R.styleable.OCSideNav_title));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.OCSideNav_background);
            if (drawable != null) {
                this.mBinding.setBackground(drawable);
            } else {
                this.mBinding.setBackground(context.getDrawable(R.drawable.ocsidenav_background));
            }
            this.mAdapter = new OCSideNavAdapter(context);
            this.mBinding.recyclerView.setAdapter(this.mAdapter);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void destroy() {
        this.mBinding.recyclerView.setAdapter(null);
    }

    private void initializeRecyclerView() {
        this.mBinding.recyclerView.setHasFixedSize(true);
    }

    public void setTitle(String str) {
        this.mBinding.setTitle(str);
    }

    public void setItems(List<OCSideNavItem> list) {
        this.mAdapter.setItems(list);
    }

    public void setBackground(int i) {
        this.mBinding.setBackground(this.mContext.getDrawable(i));
    }

    public void setItemBackground(int i) {
        this.mAdapter.setItemBackground(i);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mAdapter.setEventHandler(oCEventHandler);
    }

    public void setOnItemClick(Function<OCSideNavItem, ?> function) {
        this.mAdapter.setOnItemClick(function);
    }

    public void setOnDisabledItemHover(OCSideNavAdapter.DisabledItemHoverCallback disabledItemHoverCallback) {
        this.mAdapter.setOnDisabledItemHover(disabledItemHoverCallback);
    }

    public void setBadgeCount(String str, int i) {
        OCSideNavItem item = this.mAdapter.getItem(str);
        if (item == null) {
            Log.e(TAG, "Unable to find SideNavItem");
            return;
        }
        item.setBadgeCount(i);
        OCSideNavAdapter oCSideNavAdapter = this.mAdapter;
        oCSideNavAdapter.notifyItemChanged(oCSideNavAdapter.getPosition(item));
    }

    public void setSelectedItem(OCSideNavItem oCSideNavItem) {
        this.mAdapter.setSelectedItem(oCSideNavItem);
    }

    public OCSideNavItem setSelectedItemByID(int i) {
        return this.mAdapter.setSelectedItemByID(i);
    }

    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }

    public List<OCSideNavItem> getItems() {
        return this.mAdapter.getItems();
    }

    public OCSideNavItem getItem(String str) {
        return this.mAdapter.getItem(str);
    }

    public OCSideNavItem getSelectedItem() {
        return this.mAdapter.getSelectedItem();
    }

    public int getPosition(OCSideNavItem oCSideNavItem) {
        return this.mAdapter.getPosition(oCSideNavItem);
    }
}
