package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.common.ocui.databinding.OcdropdownBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import com.oculus.socialplatform.R;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OCDropdown<T> extends PopupWindow {
    public OCDropdownAdapter mAdapter;
    public OcdropdownBinding mBinding;
    public final Context mContext;
    public View mParentView;
    public OCDropdownVisibilityCallback mVisibilityCallback;

    public static /* synthetic */ boolean lambda$new$0(View view, MotionEvent motionEvent) {
        return true;
    }

    public int getCount() {
        return this.mAdapter.getItemCount();
    }

    public Map<T, Drawable> getIconMap() {
        return this.mAdapter.mIconMap;
    }

    public T getItem(int i) {
        return this.mAdapter.mItems.get(i);
    }

    public List<T> getItems() {
        return this.mAdapter.mItems;
    }

    public Map<T, String> getSubtitleMap() {
        return this.mAdapter.mSubtitleMap;
    }

    public Map<T, String> getTitleMap() {
        return this.mAdapter.mTitleMap;
    }

    public /* synthetic */ void lambda$new$1$OCDropdown() {
        OCDropdownVisibilityCallback oCDropdownVisibilityCallback = this.mVisibilityCallback;
        if (oCDropdownVisibilityCallback != null) {
            oCDropdownVisibilityCallback.onHide();
        }
        View view = this.mParentView;
        if (view != null) {
            view.setTag(R.id.ocui_automation_attachedview_key, null);
            this.mParentView = null;
        }
    }

    public void setBadgedItem(T t) {
        this.mAdapter.setBadgedItem(t);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mAdapter.mEventHandler = oCEventHandler;
    }

    public void setIconMap(Map map) {
        this.mAdapter.setIconMap(map);
    }

    public void setIconSizeDp(int i) {
        this.mAdapter.mIconSizeDp = i;
    }

    public void setItems(List<T> list) {
        this.mAdapter.setItems(list);
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mAdapter.mLogger = oCSelectLogger;
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mAdapter.mOnItemClick = function;
    }

    public void setRightIconMap(Map map) {
        this.mAdapter.setRightIconMap(map);
    }

    public void setSelectedItem(T t) {
        this.mAdapter.setSelectedItem(t);
    }

    public void setSubtitleMap(Map map) {
        this.mAdapter.setSubtitleMap(map);
    }

    public void setTitleMap(Map map) {
        this.mAdapter.setTitleMap(map);
    }

    public void setUnbadgedItem(T t) {
        this.mAdapter.setUnbadgedItem(t);
    }

    public OCDropdown(Context context) {
        this.mContext = context;
        OcdropdownBinding inflate = OcdropdownBinding.inflate(LayoutInflater.from(context), null, false);
        this.mBinding = inflate;
        OCRecyclerView oCRecyclerView = inflate.contextMenuList;
        OCDropdownAdapter oCDropdownAdapter = new OCDropdownAdapter(this.mContext, this);
        this.mAdapter = oCDropdownAdapter;
        oCRecyclerView.setAdapter(oCDropdownAdapter);
        oCRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        oCRecyclerView.addItemDecoration(new OCDropdownItemDecoration((int) this.mContext.getResources().getDimension(R.dimen.abc_action_bar_elevation_material)), -1);
        setContentView(this.mBinding.mRoot);
        setAnimationStyle(0);
        setHeight(-2);
        setWidth((int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_width));
        oCRecyclerView.setOnHoverListener($$Lambda$OCDropdown$NjnshX7ubfpKwNocGlS0ZZ1eD3s2.INSTANCE);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.ocui.$$Lambda$OCDropdown$MCQlXsFUmWHq9OHrCJ3vYh7c6jI2 */

            public final void onDismiss() {
                OCDropdown.this.lambda$new$1$OCDropdown();
            }
        });
    }

    public void setVisibilityCallback(OCDropdownVisibilityCallback oCDropdownVisibilityCallback) {
        this.mVisibilityCallback = oCDropdownVisibilityCallback;
    }

    public void setContextMenuTitle(int i) {
        if (i != 0) {
            this.mBinding.contextMenuTitle.setVisibility(0);
            this.mBinding.contextMenuTitle.setText(i);
            return;
        }
        this.mBinding.contextMenuTitle.setVisibility(8);
    }

    public void setContextMenuTitle(String str) {
        if (str == null || str.length() == 0) {
            this.mBinding.contextMenuTitle.setVisibility(8);
            return;
        }
        this.mBinding.contextMenuTitle.setVisibility(0);
        this.mBinding.contextMenuTitle.setText(str);
    }

    public void toggle(View view) {
        int i;
        View rootView = view.getRootView();
        int[] iArr = new int[2];
        rootView.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr2);
        if (iArr2[1] - iArr[1] > rootView.getHeight() - iArr2[1]) {
            Resources resources = this.mContext.getResources();
            int dimension = (int) resources.getDimension(R.dimen.anytime_tablet_common_rectangular_button_height_v2);
            int dimension2 = (int) resources.getDimension(R.dimen.abc_action_bar_elevation_material);
            i = -(view.getHeight() + ((dimension + dimension2) * this.mAdapter.getItemCount()) + ((int) resources.getDimension(R.dimen.abc_floating_window_z)));
        } else {
            i = 0;
        }
        toggle(view, 0, i);
    }

    public void toggle(View view, int i, int i2) {
        showAsDropDown(view, i, i2);
        view.setTag(R.id.ocui_automation_attachedview_key, getContentView());
        this.mParentView = view;
        OCDropdownVisibilityCallback oCDropdownVisibilityCallback = this.mVisibilityCallback;
        if (oCDropdownVisibilityCallback != null) {
            oCDropdownVisibilityCallback.onShow();
        }
    }
}
