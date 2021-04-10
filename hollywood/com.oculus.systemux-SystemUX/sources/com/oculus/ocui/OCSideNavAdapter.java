package com.oculus.ocui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcsidenavItemBinding;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class OCSideNavAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = LoggingUtil.tag(OCSideNavAdapter.class);
    private Integer itemBackgroundId;
    private Context mContext;
    private OCEventHandler mEventHandler;
    private DisabledItemHoverCallback mOnDisabledItemHover;
    private Function<OCSideNavItem, ?> mOnItemClick;
    private int mSelectedItemPosition = 0;
    private List<OCSideNavItem> mSideNavItems;

    @FunctionalInterface
    public interface DisabledItemHoverCallback {
        void onDisabledItemHover(OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent);
    }

    public static class SideNavViewHolder extends RecyclerView.ViewHolder {
        OcsidenavItemBinding mBinding;

        public SideNavViewHolder(OcsidenavItemBinding ocsidenavItemBinding) {
            super(ocsidenavItemBinding.getRoot());
            this.mBinding = ocsidenavItemBinding;
        }
    }

    public OCSideNavAdapter(Context context) {
        this.mContext = context;
    }

    public void setItemBackground(int i) {
        this.itemBackgroundId = Integer.valueOf(i);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnItemClick(Function<OCSideNavItem, ?> function) {
        this.mOnItemClick = function;
    }

    public void setOnDisabledItemHover(DisabledItemHoverCallback disabledItemHoverCallback) {
        this.mOnDisabledItemHover = disabledItemHoverCallback;
    }

    public void setItems(List<OCSideNavItem> list) {
        this.mSideNavItems = list;
        notifyDataSetChanged();
    }

    public void setSelectedItem(OCSideNavItem oCSideNavItem) {
        int indexOf = this.mSideNavItems.indexOf(oCSideNavItem);
        if (indexOf < 0) {
            Log.e(TAG, "select called with invalid SideNavItem.");
            return;
        }
        int i = this.mSelectedItemPosition;
        this.mSelectedItemPosition = indexOf;
        notifyItemChanged(i);
        notifyItemChanged(this.mSelectedItemPosition);
    }

    public OCSideNavItem setSelectedItemByID(int i) {
        OCSideNavItem orElse = this.mSideNavItems.stream().filter(new Predicate(i) {
            /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$bMA6X7gPSE7O6pZMbC8kjXK8Is */
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return OCSideNavAdapter.lambda$setSelectedItemByID$13(this.f$0, (OCSideNavItem) obj);
            }
        }).findFirst().orElse(null);
        setSelectedItem(orElse);
        return orElse;
    }

    static /* synthetic */ boolean lambda$setSelectedItemByID$13(int i, OCSideNavItem oCSideNavItem) {
        return oCSideNavItem.getViewId().intValue() == i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mSideNavItems.size();
    }

    public List<OCSideNavItem> getItems() {
        return this.mSideNavItems;
    }

    public OCSideNavItem getItem(String str) {
        for (OCSideNavItem oCSideNavItem : this.mSideNavItems) {
            if (oCSideNavItem.getTitle().equals(str)) {
                return oCSideNavItem;
            }
        }
        return null;
    }

    public OCSideNavItem getItem(int i) {
        for (OCSideNavItem oCSideNavItem : this.mSideNavItems) {
            if (oCSideNavItem.getViewId().intValue() == i) {
                return oCSideNavItem;
            }
        }
        return null;
    }

    public OCSideNavItem getSelectedItem() {
        return this.mSideNavItems.get(this.mSelectedItemPosition);
    }

    public int getPosition(OCSideNavItem oCSideNavItem) {
        for (int i = 0; i < this.mSideNavItems.size(); i++) {
            if (this.mSideNavItems.get(i) == oCSideNavItem) {
                return i;
            }
        }
        return -1;
    }

    /* Return type fixed from 'com.oculus.ocui.OCSideNavAdapter$SideNavViewHolder' to match base method */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        OcsidenavItemBinding inflate = OcsidenavItemBinding.inflate(LayoutInflater.from(this.mContext));
        SideNavViewHolder sideNavViewHolder = new SideNavViewHolder(inflate);
        Integer num = this.itemBackgroundId;
        if (num != null) {
            inflate.setBackground(this.mContext.getDrawable(num.intValue()));
        } else {
            inflate.setBackground(this.mContext.getDrawable(R.drawable.ocsidenav_item_background));
        }
        return sideNavViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OcsidenavItemBinding ocsidenavItemBinding = ((SideNavViewHolder) viewHolder).mBinding;
        OCButton oCButton = ocsidenavItemBinding.navButton;
        OCTextView oCTextView = ocsidenavItemBinding.badge.text;
        OCSideNavItem oCSideNavItem = this.mSideNavItems.get(i);
        boolean z = this.mSelectedItemPosition == i;
        if (oCSideNavItem.getViewId() != null) {
            oCButton.setId(oCSideNavItem.getViewId().intValue());
        }
        oCButton.setText(oCSideNavItem.getTitle());
        oCButton.setSelected(z);
        oCTextView.setSelected(z);
        oCButton.setEventHandler(this.mEventHandler);
        if (z) {
            oCButton.setOnClickListener(null);
            oCButton.setClickable(false);
        } else {
            oCButton.setOnClickListener(new View.OnClickListener(oCSideNavItem) {
                /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$_COpUt3SgGCinzYexBIR5kOIyI */
                private final /* synthetic */ OCSideNavItem f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    OCSideNavAdapter.this.lambda$onBindViewHolder$14$OCSideNavAdapter(this.f$1, view);
                }
            });
            oCButton.setClickable(true);
        }
        ((View) oCButton.getParent()).setOnHoverListener(new View.OnHoverListener(oCButton, oCSideNavItem) {
            /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$PZxzJRsQUINrC065LnN5Teqnrac */
            private final /* synthetic */ OCButton f$1;
            private final /* synthetic */ OCSideNavItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCSideNavAdapter.this.lambda$onBindViewHolder$15$OCSideNavAdapter(this.f$1, this.f$2, view, motionEvent);
            }
        });
        ocsidenavItemBinding.setSideNavItem(oCSideNavItem);
    }

    public /* synthetic */ void lambda$onBindViewHolder$14$OCSideNavAdapter(OCSideNavItem oCSideNavItem, View view) {
        this.mOnItemClick.apply(oCSideNavItem);
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$15$OCSideNavAdapter(OCButton oCButton, OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent) {
        if (oCButton.isEnabled()) {
            return false;
        }
        this.mOnDisabledItemHover.onDisabledItemHover(oCSideNavItem, oCButton, motionEvent);
        return false;
    }
}
