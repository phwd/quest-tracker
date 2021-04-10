package com.oculus.ocui;

import X.AnonymousClass1Ah;
import X.AnonymousClass1Aj;
import X.AnonymousClass2a8;
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
import com.oculus.common.ocui.databinding.OccontextMenuItemBinding;
import com.oculus.ocui.logging.OCSelectLogger;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OCDropdownAdapter<T> extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public List<T> mBadgedItems;
    public Context mContext;
    public OCDropdown mDropdown;
    public OCEventHandler mEventHandler;
    public Map<T, Drawable> mIconMap;
    public int mIconSizeDp;
    public List<T> mItems;
    public OCSelectLogger mLogger;
    public Function<T, ?> mOnItemClick;
    public Map<T, Drawable> mRightIconMap;
    public T mSelectedItem;
    public Map<T, String> mSubtitleMap;
    public Map<T, String> mTitleMap;

    public static class ContextMenuItemViewHolder extends AnonymousClass1Ah {
        public OccontextMenuItemBinding mBinding;

        public ContextMenuItemViewHolder(OccontextMenuItemBinding occontextMenuItemBinding) {
            super(occontextMenuItemBinding.mRoot);
            this.mBinding = occontextMenuItemBinding;
        }
    }

    public Map<T, Drawable> getIconMap() {
        return this.mIconMap;
    }

    public T getItem(int i) {
        return this.mItems.get(i);
    }

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        List<T> list = this.mItems;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<T> getItems() {
        return this.mItems;
    }

    public Map<T, String> getSubtitleMap() {
        return this.mSubtitleMap;
    }

    public Map<T, String> getTitleMap() {
        return this.mTitleMap;
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$OCDropdownAdapter(Object obj, View view) {
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

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(@NonNull AnonymousClass1Ah r13, int i) {
        T t = this.mItems.get(i);
        ContextMenuItemViewHolder contextMenuItemViewHolder = (ContextMenuItemViewHolder) r13;
        OccontextMenuItemBinding occontextMenuItemBinding = contextMenuItemViewHolder.mBinding;
        occontextMenuItemBinding.itemTitle.setText(this.mTitleMap.get(t));
        FrameLayout frameLayout = occontextMenuItemBinding.itemIconContainer;
        ConstraintLayout constraintLayout = occontextMenuItemBinding.itemTextContainer;
        AnonymousClass2a8 r4 = (AnonymousClass2a8) constraintLayout.getLayoutParams();
        Map<T, Drawable> map = this.mIconMap;
        if (map == null || map.get(t) == null) {
            frameLayout.setVisibility(8);
            r4.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.abc_dialog_padding_material);
        } else {
            frameLayout.setVisibility(0);
            if (this.mIconSizeDp > 0) {
                ViewGroup.LayoutParams layoutParams = occontextMenuItemBinding.itemIcon.getLayoutParams();
                int i2 = this.mIconSizeDp;
                layoutParams.height = i2;
                layoutParams.width = i2;
                occontextMenuItemBinding.itemIcon.setLayoutParams(layoutParams);
            }
            contextMenuItemViewHolder.mBinding.itemIcon.setBackground(this.mIconMap.get(t));
            r4.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.abc_button_padding_horizontal_material);
        }
        constraintLayout.setLayoutParams(r4);
        ConstraintLayout constraintLayout2 = occontextMenuItemBinding.contextMenuItem;
        OCTextView oCTextView = occontextMenuItemBinding.itemTitle;
        OCTextView oCTextView2 = occontextMenuItemBinding.itemSubtitle;
        ViewGroup.LayoutParams layoutParams2 = constraintLayout2.getLayoutParams();
        Map<T, String> map2 = this.mSubtitleMap;
        if (map2 == null || map2.get(t) == null) {
            layoutParams2.height = (int) this.mContext.getResources().getDimension(R.dimen.anytime_tablet_common_rectangular_button_height_v2);
            oCTextView2.setVisibility(8);
        } else {
            layoutParams2.height = (int) this.mContext.getResources().getDimension(R.dimen.abc_action_bar_stacked_max_height);
            oCTextView2.setVisibility(0);
            oCTextView2.setText(this.mSubtitleMap.get(t));
        }
        constraintLayout2.setLayoutParams(layoutParams2);
        T t2 = this.mSelectedItem;
        int i3 = R.attr.ocSecondaryButtonText;
        int i4 = R.drawable.ocbutton_borderless;
        if (t == t2) {
            i3 = R.attr.ocPrimaryButtonText;
            i4 = R.drawable.ocbutton_primary_init;
        }
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i3, typedValue, true);
        oCTextView.setTextColor(typedValue.data);
        oCTextView2.setTextColor(typedValue.data);
        oCTextView2.setAlpha(0.6f);
        constraintLayout2.setBackground(this.mContext.getDrawable(i4));
        constraintLayout2.setOnClickListener(new View.OnClickListener(t) {
            /* class com.oculus.ocui.$$Lambda$OCDropdownAdapter$Ysty15T8SmkDfyNWyFHBHIqtdOk2 */
            public final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                OCDropdownAdapter.this.lambda$onBindViewHolder$1$OCDropdownAdapter(this.f$1, view);
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

    @Override // X.AnonymousClass1Aj
    @NonNull
    public AnonymousClass1Ah onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        OccontextMenuItemBinding inflate = OccontextMenuItemBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        ConstraintLayout constraintLayout = inflate.contextMenuItem;
        constraintLayout.setOnHoverListener(new View.OnHoverListener(constraintLayout, inflate.itemTitle) {
            /* class com.oculus.ocui.$$Lambda$OCDropdownAdapter$MEX6C_L_pkXTSJTJkLDDiATDTA2 */
            public final /* synthetic */ ConstraintLayout f$1;
            public final /* synthetic */ TextView f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCDropdownAdapter.this.lambda$onCreateViewHolder$0$OCDropdownAdapter(this.f$1, this.f$2, view, motionEvent);
            }
        });
        return new ContextMenuItemViewHolder(inflate);
    }

    public void setBadgedItem(T t) {
        List list = this.mBadgedItems;
        if (list == null) {
            list = new ArrayList();
            this.mBadgedItems = list;
        }
        if (!list.contains(t)) {
            this.mBadgedItems.add(t);
            notifyDataSetChanged();
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
            Map map3 = this.mIconMap;
            if (map3 == null) {
                map3 = new HashMap();
                this.mIconMap = map3;
            }
            map3.clear();
            for (Object obj : map.keySet()) {
                this.mIconMap.put(obj, this.mContext.getResources().getDrawable(((Number) map.get(obj)).intValue()));
            }
        } else if (next instanceof Drawable) {
            this.mIconMap = map;
        }
    }

    public void setItems(List<T> list) {
        this.mItems = list;
        notifyDataSetChanged();
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
            Map map3 = this.mRightIconMap;
            if (map3 == null) {
                map3 = new HashMap();
                this.mRightIconMap = map3;
            }
            map3.clear();
            for (Object obj : map.keySet()) {
                this.mRightIconMap.put(obj, this.mContext.getResources().getDrawable(((Number) map.get(obj)).intValue()));
            }
        }
    }

    public void setSelectedItem(T t) {
        this.mSelectedItem = t;
        notifyDataSetChanged();
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
            Map map3 = this.mSubtitleMap;
            if (map3 == null) {
                map3 = new HashMap();
                this.mSubtitleMap = map3;
            }
            map3.clear();
            for (Object obj : map.keySet()) {
                this.mSubtitleMap.put(obj, this.mContext.getResources().getString(((Number) map.get(obj)).intValue()));
            }
        } else if (next instanceof String) {
            this.mSubtitleMap = map;
        }
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
            Map map3 = this.mTitleMap;
            if (map3 == null) {
                map3 = new HashMap();
                this.mTitleMap = map3;
            }
            map3.clear();
            for (Object obj : map.keySet()) {
                this.mTitleMap.put(obj, this.mContext.getResources().getString(((Number) map.get(obj)).intValue()));
            }
        } else if (next instanceof String) {
            this.mTitleMap = map;
        }
    }

    public void setUnbadgedItem(T t) {
        List<T> list = this.mBadgedItems;
        if (list != null && list.contains(t)) {
            this.mBadgedItems.remove(t);
            notifyDataSetChanged();
        }
    }

    public OCDropdownAdapter(Context context, OCDropdown oCDropdown) {
        this.mContext = context;
        this.mDropdown = oCDropdown;
    }

    public /* synthetic */ boolean lambda$onCreateViewHolder$0$OCDropdownAdapter(ConstraintLayout constraintLayout, TextView textView, View view, MotionEvent motionEvent) {
        TextUtils.TruncateAt truncateAt;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 9) {
                OCEventHandler oCEventHandler = this.mEventHandler;
                if (oCEventHandler != null) {
                    oCEventHandler.onButtonEnter();
                    return true;
                }
            } else if (actionMasked == 10) {
                constraintLayout.setHovered(false);
                constraintLayout.setSelected(false);
                truncateAt = TextUtils.TruncateAt.END;
            }
            return true;
        }
        constraintLayout.setHovered(true);
        constraintLayout.setSelected(true);
        truncateAt = TextUtils.TruncateAt.MARQUEE;
        textView.setEllipsize(truncateAt);
        return true;
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setIconSizeDp(int i) {
        this.mIconSizeDp = i;
    }

    public void setLogger(OCSelectLogger oCSelectLogger) {
        this.mLogger = oCSelectLogger;
    }

    public void setOnItemClick(Function<T, ?> function) {
        this.mOnItemClick = function;
    }
}
