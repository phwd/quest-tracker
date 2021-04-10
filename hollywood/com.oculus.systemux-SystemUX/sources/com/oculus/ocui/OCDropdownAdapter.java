package com.oculus.ocui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OccontextMenuItemBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OCDropdownAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> mBadgedItems;
    private Context mContext;
    private OCDropdown mDropdown;
    private OCEventHandler mEventHandler;
    private Map<T, Drawable> mIconMap;
    private int mIconSizeDp;
    private List<T> mItems;
    private OCSelectLogger mLogger;
    private Function<T, ?> mOnItemClick;
    private Map<T, Drawable> mRightIconMap;
    private T mSelectedItem;
    private Map<T, String> mSubtitleMap;
    private Map<T, String> mTitleMap;

    public OCDropdownAdapter(Context context, OCDropdown oCDropdown) {
        this.mContext = context;
        this.mDropdown = oCDropdown;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.mItems;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class ContextMenuItemViewHolder extends RecyclerView.ViewHolder {
        OccontextMenuItemBinding mBinding;

        public ContextMenuItemViewHolder(OccontextMenuItemBinding occontextMenuItemBinding) {
            super(occontextMenuItemBinding.getRoot());
            this.mBinding = occontextMenuItemBinding;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        OccontextMenuItemBinding inflate = OccontextMenuItemBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        ConstraintLayout constraintLayout = inflate.contextMenuItem;
        constraintLayout.setOnHoverListener(new View.OnHoverListener(constraintLayout, inflate.itemTitle) {
            /* class com.oculus.ocui.$$Lambda$OCDropdownAdapter$_z4oQYF0QNBJ4EtQLePSCWsWaGo */
            private final /* synthetic */ ConstraintLayout f$1;
            private final /* synthetic */ TextView f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCDropdownAdapter.this.lambda$onCreateViewHolder$6$OCDropdownAdapter(this.f$1, this.f$2, view, motionEvent);
            }
        });
        return new ContextMenuItemViewHolder(inflate);
    }

    public /* synthetic */ boolean lambda$onCreateViewHolder$6$OCDropdownAdapter(ConstraintLayout constraintLayout, TextView textView, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            constraintLayout.setHovered(true);
            constraintLayout.setSelected(true);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        } else if (actionMasked == 9) {
            OCEventHandler oCEventHandler = this.mEventHandler;
            if (oCEventHandler != null) {
                oCEventHandler.onButtonEnter();
            }
        } else if (actionMasked == 10) {
            constraintLayout.setHovered(false);
            constraintLayout.setSelected(false);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        int i3;
        T t = this.mItems.get(i);
        ContextMenuItemViewHolder contextMenuItemViewHolder = (ContextMenuItemViewHolder) viewHolder;
        OccontextMenuItemBinding occontextMenuItemBinding = contextMenuItemViewHolder.mBinding;
        occontextMenuItemBinding.itemTitle.setText(this.mTitleMap.get(t));
        FrameLayout frameLayout = occontextMenuItemBinding.itemIconContainer;
        ConstraintLayout constraintLayout = occontextMenuItemBinding.itemTextContainer;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        Map<T, Drawable> map = this.mIconMap;
        if (map == null || map.get(t) == null) {
            frameLayout.setVisibility(8);
            layoutParams.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_text_spacing_horizontal_large);
        } else {
            frameLayout.setVisibility(0);
            if (this.mIconSizeDp > 0) {
                ViewGroup.LayoutParams layoutParams2 = occontextMenuItemBinding.itemIcon.getLayoutParams();
                int i4 = this.mIconSizeDp;
                layoutParams2.height = i4;
                layoutParams2.width = i4;
                occontextMenuItemBinding.itemIcon.setLayoutParams(layoutParams2);
            }
            contextMenuItemViewHolder.mBinding.itemIcon.setBackground(this.mIconMap.get(t));
            layoutParams.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_text_spacing_horizontal_regular);
        }
        constraintLayout.setLayoutParams(layoutParams);
        ConstraintLayout constraintLayout2 = occontextMenuItemBinding.contextMenuItem;
        OCTextView oCTextView = occontextMenuItemBinding.itemTitle;
        OCTextView oCTextView2 = occontextMenuItemBinding.itemSubtitle;
        ViewGroup.LayoutParams layoutParams3 = constraintLayout2.getLayoutParams();
        Map<T, String> map2 = this.mSubtitleMap;
        if (map2 == null || map2.get(t) == null) {
            layoutParams3.height = (int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_item_height_regular);
            oCTextView2.setVisibility(8);
        } else {
            layoutParams3.height = (int) this.mContext.getResources().getDimension(R.dimen.occontext_menu_item_height_large);
            oCTextView2.setVisibility(0);
            oCTextView2.setText(this.mSubtitleMap.get(t));
        }
        constraintLayout2.setLayoutParams(layoutParams3);
        if (t == this.mSelectedItem) {
            i3 = R.attr.ocPrimaryButtonText;
            i2 = R.drawable.ocbutton_primary_init;
        } else {
            i3 = R.attr.ocSecondaryButtonText;
            i2 = R.drawable.ocbutton_borderless;
        }
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i3, typedValue, true);
        oCTextView.setTextColor(typedValue.data);
        oCTextView2.setTextColor(typedValue.data);
        oCTextView2.setAlpha(0.6f);
        constraintLayout2.setBackground(this.mContext.getDrawable(i2));
        constraintLayout2.setOnClickListener(new View.OnClickListener(t) {
            /* class com.oculus.ocui.$$Lambda$OCDropdownAdapter$noUK4tDhEv8LYc8SORwkgc7ibUE */
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                OCDropdownAdapter.this.lambda$onBindViewHolder$7$OCDropdownAdapter(this.f$1, view);
            }
        });
        List<T> list = this.mBadgedItems;
        if (list == null || !list.contains(t)) {
            occontextMenuItemBinding.itemIconContainer.setActivated(false);
        } else {
            occontextMenuItemBinding.itemIconContainer.setActivated(true);
        }
        Map<T, Drawable> map3 = this.mRightIconMap;
        if (map3 == null || map3.get(t) == null) {
            occontextMenuItemBinding.itemRightIcon.setVisibility(8);
            return;
        }
        occontextMenuItemBinding.itemRightIcon.setVisibility(0);
        occontextMenuItemBinding.itemRightIcon.setImageDrawable(this.mRightIconMap.get(t));
    }

    public /* synthetic */ void lambda$onBindViewHolder$7$OCDropdownAdapter(Object obj, View view) {
        OCSelectLogger oCSelectLogger = this.mLogger;
        if (oCSelectLogger != null) {
            oCSelectLogger.onLogOptionSelected(this.mSelectedItem, obj);
        }
        this.mOnItemClick.apply(obj);
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        this.mDropdown.dismiss();
    }

    public T getItem(int i) {
        return this.mItems.get(i);
    }

    public List<T> getItems() {
        return this.mItems;
    }

    public Map<T, String> getTitleMap() {
        return this.mTitleMap;
    }

    public Map<T, String> getSubtitleMap() {
        return this.mSubtitleMap;
    }

    public Map<T, Drawable> getIconMap() {
        return this.mIconMap;
    }

    public void setItems(List<T> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<T, java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    public void setTitleMap(Map map) {
        if (map == null || map.isEmpty()) {
            Map<T, String> map2 = this.mTitleMap;
            if (map2 != null) {
                map2.clear();
                return;
            }
            return;
        }
        Object next = map.values().iterator().next();
        if (next instanceof Integer) {
            if (this.mTitleMap == null) {
                this.mTitleMap = new HashMap();
            }
            this.mTitleMap.clear();
            for (Object obj : map.keySet()) {
                this.mTitleMap.put(obj, this.mContext.getResources().getString(((Integer) map.get(obj)).intValue()));
            }
        } else if (next instanceof String) {
            this.mTitleMap = map;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<T, java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    public void setSubtitleMap(Map map) {
        if (map == null || map.isEmpty()) {
            Map<T, String> map2 = this.mSubtitleMap;
            if (map2 != null) {
                map2.clear();
                return;
            }
            return;
        }
        Object next = map.values().iterator().next();
        if (next instanceof Integer) {
            if (this.mSubtitleMap == null) {
                this.mSubtitleMap = new HashMap();
            }
            this.mSubtitleMap.clear();
            for (Object obj : map.keySet()) {
                this.mSubtitleMap.put(obj, this.mContext.getResources().getString(((Integer) map.get(obj)).intValue()));
            }
        } else if (next instanceof String) {
            this.mSubtitleMap = map;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<T, android.graphics.drawable.Drawable> */
    /* JADX WARN: Multi-variable type inference failed */
    public void setIconMap(Map map) {
        if (map == null || map.isEmpty()) {
            Map<T, Drawable> map2 = this.mIconMap;
            if (map2 != null) {
                map2.clear();
                return;
            }
            return;
        }
        Object next = map.values().iterator().next();
        if (next instanceof Integer) {
            if (this.mIconMap == null) {
                this.mIconMap = new HashMap();
            }
            this.mIconMap.clear();
            for (Object obj : map.keySet()) {
                this.mIconMap.put(obj, this.mContext.getResources().getDrawable(((Integer) map.get(obj)).intValue()));
            }
        } else if (next instanceof Drawable) {
            this.mIconMap = map;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<T, android.graphics.drawable.Drawable> */
    /* JADX WARN: Multi-variable type inference failed */
    public void setRightIconMap(Map map) {
        if (map == null || map.isEmpty()) {
            Map<T, Drawable> map2 = this.mRightIconMap;
            if (map2 != null) {
                map2.clear();
            }
        } else if (map.values().iterator().next() instanceof Integer) {
            if (this.mRightIconMap == null) {
                this.mRightIconMap = new HashMap();
            }
            this.mRightIconMap.clear();
            for (Object obj : map.keySet()) {
                this.mRightIconMap.put(obj, this.mContext.getResources().getDrawable(((Integer) map.get(obj)).intValue()));
            }
        }
    }

    public void setIconSizeDp(int i) {
        this.mIconSizeDp = i;
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mOnItemClick = function;
    }

    public void setSelectedItem(T t) {
        this.mSelectedItem = t;
        notifyDataSetChanged();
    }

    public void setBadgedItem(T t) {
        if (this.mBadgedItems == null) {
            this.mBadgedItems = new ArrayList();
        }
        if (!this.mBadgedItems.contains(t)) {
            this.mBadgedItems.add(t);
            notifyDataSetChanged();
        }
    }

    public void setUnbadgedItem(T t) {
        List<T> list = this.mBadgedItems;
        if (list != null && list.contains(t)) {
            this.mBadgedItems.remove(t);
            notifyDataSetChanged();
        }
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mLogger = oCSelectLogger;
    }
}
