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
    public static final String TAG = LoggingUtil.tag(OCSideNav.class);
    public OCSideNavAdapter mAdapter;
    public OcsidenavBinding mBinding;
    public Context mContext;

    private void initializeRecyclerView() {
        this.mBinding.recyclerView.mHasFixedSize = true;
    }

    public void destroy() {
        this.mBinding.recyclerView.setAdapter(null);
    }

    public OCSideNavItem getItem(String str) {
        return this.mAdapter.getItem(str);
    }

    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }

    public List<OCSideNavItem> getItems() {
        return this.mAdapter.mSideNavItems;
    }

    public int getPosition(OCSideNavItem oCSideNavItem) {
        return this.mAdapter.getPosition(oCSideNavItem);
    }

    public OCSideNavItem getSelectedItem() {
        return this.mAdapter.getSelectedItem();
    }

    public void setBackground(int i) {
        this.mBinding.setBackground(this.mContext.getDrawable(i));
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

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mAdapter.mEventHandler = oCEventHandler;
    }

    public void setItemBackground(int i) {
        this.mAdapter.setItemBackground(i);
    }

    public void setItems(List<OCSideNavItem> list) {
        this.mAdapter.setItems(list);
    }

    public void setOnDisabledItemHover(OCSideNavAdapter.DisabledItemHoverCallback disabledItemHoverCallback) {
        this.mAdapter.mOnDisabledItemHover = disabledItemHoverCallback;
    }

    public void setOnItemClick(Function<OCSideNavItem, ?> function) {
        this.mAdapter.mOnItemClick = function;
    }

    public void setSelectedItem(OCSideNavItem oCSideNavItem) {
        this.mAdapter.setSelectedItem(oCSideNavItem);
    }

    public OCSideNavItem setSelectedItemByID(int i) {
        return this.mAdapter.setSelectedItemByID(i);
    }

    public void setTitle(String str) {
        this.mBinding.setTitle(str);
    }

    public OCSideNav(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mBinding = OcsidenavBinding.inflate(LayoutInflater.from(context), this, true);
        initializeRecyclerView();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OCSideNav, 0, 0);
        try {
            this.mBinding.setTitle(obtainStyledAttributes.getString(1));
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            if (drawable != null) {
                this.mBinding.setBackground(drawable);
            } else {
                this.mBinding.setBackground(context.getDrawable(com.oculus.socialplatform.R.drawable.ocsidenav_background));
            }
            OCSideNavAdapter oCSideNavAdapter = new OCSideNavAdapter(context);
            this.mAdapter = oCSideNavAdapter;
            this.mBinding.recyclerView.setAdapter(oCSideNavAdapter);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
