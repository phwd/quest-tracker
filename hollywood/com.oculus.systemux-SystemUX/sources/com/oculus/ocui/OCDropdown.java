package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcdropdownBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OCDropdown<T> extends PopupWindow {
    private OCDropdownAdapter mAdapter = new OCDropdownAdapter(this.mContext, this);
    private OcdropdownBinding mBinding;
    private final Context mContext;
    private View mParentView;
    private OCDropdownVisibilityCallback mVisibilityCallback;

    static /* synthetic */ boolean lambda$new$4(View view, MotionEvent motionEvent) {
        return true;
    }

    public OCDropdown(Context context) {
        this.mContext = context;
        this.mBinding = OcdropdownBinding.inflate(LayoutInflater.from(context), null, false);
        OCRecyclerView oCRecyclerView = this.mBinding.contextMenuList;
        oCRecyclerView.setAdapter(this.mAdapter);
        oCRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        oCRecyclerView.addItemDecoration(new OCDropdownItemDecoration((int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_item_spacing)));
        setContentView(this.mBinding.getRoot());
        setAnimationStyle(0);
        setHeight(-2);
        setWidth((int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_width));
        oCRecyclerView.setOnHoverListener($$Lambda$OCDropdown$0L1iO9TO174fjlrw3WJqEK9f8fE.INSTANCE);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.ocui.$$Lambda$OCDropdown$IYUhp4Myj1j2hMXD_hhLF5ARS3g */

            public final void onDismiss() {
                OCDropdown.this.lambda$new$5$OCDropdown();
            }
        });
    }

    public /* synthetic */ void lambda$new$5$OCDropdown() {
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

    public void toggle(View view) {
        int i;
        View rootView = view.getRootView();
        int[] iArr = new int[2];
        rootView.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr2);
        if (iArr2[1] - iArr[1] > rootView.getHeight() - iArr2[1]) {
            Resources resources = this.mContext.getResources();
            i = -(view.getHeight() + ((((int) resources.getDimension(R.dimen.ocbutton_height)) + ((int) resources.getDimension(R.dimen.occontext_menu_item_spacing))) * getCount()) + ((int) resources.getDimension(R.dimen.ocdropdown_padding)));
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

    public List<T> getItems() {
        return this.mAdapter.getItems();
    }

    public T getItem(int i) {
        return (T) this.mAdapter.getItem(i);
    }

    public int getCount() {
        return this.mAdapter.getItemCount();
    }

    public Map<T, String> getTitleMap() {
        return this.mAdapter.getTitleMap();
    }

    public Map<T, String> getSubtitleMap() {
        return this.mAdapter.getSubtitleMap();
    }

    public Map<T, Drawable> getIconMap() {
        return this.mAdapter.getIconMap();
    }

    public void setItems(List<T> list) {
        this.mAdapter.setItems(list);
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mAdapter.setOnItemClick(function);
    }

    public void setTitleMap(Map map) {
        this.mAdapter.setTitleMap(map);
    }

    public void setSubtitleMap(Map map) {
        this.mAdapter.setSubtitleMap(map);
    }

    public void setIconMap(Map map) {
        this.mAdapter.setIconMap(map);
    }

    public void setRightIconMap(Map map) {
        this.mAdapter.setRightIconMap(map);
    }

    public void setIconSizeDp(int i) {
        this.mAdapter.setIconSizeDp(i);
    }

    public void setSelectedItem(T t) {
        this.mAdapter.setSelectedItem(t);
    }

    public void setBadgedItem(T t) {
        this.mAdapter.setBadgedItem(t);
    }

    public void setUnbadgedItem(T t) {
        this.mAdapter.setUnbadgedItem(t);
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

    public void setVisibilityCallback(OCDropdownVisibilityCallback oCDropdownVisibilityCallback) {
        this.mVisibilityCallback = oCDropdownVisibilityCallback;
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mAdapter.setEventHandler(oCEventHandler);
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mAdapter.setLogger(oCSelectLogger);
    }
}
