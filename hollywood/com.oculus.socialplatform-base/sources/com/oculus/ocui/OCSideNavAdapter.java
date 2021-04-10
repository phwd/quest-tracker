package com.oculus.ocui;

import X.AnonymousClass1Ah;
import X.AnonymousClass1Aj;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.databinding.OcsidenavItemBinding;
import com.oculus.socialplatform.R;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class OCSideNavAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(OCSideNavAdapter.class);
    public Integer itemBackgroundId;
    public Context mContext;
    public OCEventHandler mEventHandler;
    public DisabledItemHoverCallback mOnDisabledItemHover;
    public Function<OCSideNavItem, ?> mOnItemClick;
    public int mSelectedItemPosition = 0;
    public List<OCSideNavItem> mSideNavItems;

    @FunctionalInterface
    public interface DisabledItemHoverCallback {
        void onDisabledItemHover(OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent);
    }

    public static class SideNavViewHolder extends AnonymousClass1Ah {
        public OcsidenavItemBinding mBinding;

        public SideNavViewHolder(OcsidenavItemBinding ocsidenavItemBinding) {
            super(ocsidenavItemBinding.mRoot);
            this.mBinding = ocsidenavItemBinding;
        }
    }

    public int getPosition(OCSideNavItem oCSideNavItem) {
        for (int i = 0; i < this.mSideNavItems.size(); i++) {
            if (this.mSideNavItems.get(i) == oCSideNavItem) {
                return i;
            }
        }
        return -1;
    }

    public static /* synthetic */ boolean lambda$setSelectedItemByID$0(int i, OCSideNavItem oCSideNavItem) {
        if (oCSideNavItem.mViewId.intValue() == i) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mSideNavItems.size();
    }

    public List<OCSideNavItem> getItems() {
        return this.mSideNavItems;
    }

    public OCSideNavItem getSelectedItem() {
        return this.mSideNavItems.get(this.mSelectedItemPosition);
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$OCSideNavAdapter(OCSideNavItem oCSideNavItem, View view) {
        this.mOnItemClick.apply(oCSideNavItem);
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(@NonNull AnonymousClass1Ah r9, int i) {
        OcsidenavItemBinding ocsidenavItemBinding = ((SideNavViewHolder) r9).mBinding;
        OCButton oCButton = ocsidenavItemBinding.navButton;
        OCTextView oCTextView = ocsidenavItemBinding.badge.text;
        OCSideNavItem oCSideNavItem = this.mSideNavItems.get(i);
        boolean z = false;
        if (this.mSelectedItemPosition == i) {
            z = true;
        }
        Integer num = oCSideNavItem.mViewId;
        if (num != null) {
            oCButton.setId(num.intValue());
        }
        oCButton.setText(oCSideNavItem.mTitle);
        oCButton.setSelected(z);
        oCTextView.setSelected(z);
        oCButton.mEventHandler = this.mEventHandler;
        if (z) {
            oCButton.setOnClickListener(null);
            oCButton.setClickable(false);
        } else {
            oCButton.setOnClickListener(new View.OnClickListener(oCSideNavItem) {
                /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$P6ToeEbahM5Ay85lbBKizJBow2 */
                public final /* synthetic */ OCSideNavItem f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    OCSideNavAdapter.this.lambda$onBindViewHolder$1$OCSideNavAdapter(this.f$1, view);
                }
            });
            oCButton.setClickable(true);
        }
        ((View) oCButton.getParent()).setOnHoverListener(new View.OnHoverListener(oCButton, oCSideNavItem) {
            /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$AXTRnk7Mx5FSRDQQuiq1pGHAMrs2 */
            public final /* synthetic */ OCButton f$1;
            public final /* synthetic */ OCSideNavItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCSideNavAdapter.this.lambda$onBindViewHolder$2$OCSideNavAdapter(this.f$1, this.f$2, view, motionEvent);
            }
        });
        ocsidenavItemBinding.setSideNavItem(oCSideNavItem);
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
            /* class com.oculus.ocui.$$Lambda$OCSideNavAdapter$1Di5ZDXXPjCrUYZu4A9VTV8GTBo2 */
            public final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return OCSideNavAdapter.lambda$setSelectedItemByID$0(this.f$0, (OCSideNavItem) obj);
            }
        }).findFirst().orElse(null);
        setSelectedItem(orElse);
        return orElse;
    }

    public OCSideNavAdapter(Context context) {
        this.mContext = context;
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$2$OCSideNavAdapter(OCButton oCButton, OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent) {
        if (oCButton.isEnabled()) {
            return false;
        }
        this.mOnDisabledItemHover.onDisabledItemHover(oCSideNavItem, oCButton, motionEvent);
        return false;
    }

    public void setItemBackground(int i) {
        this.itemBackgroundId = Integer.valueOf(i);
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnDisabledItemHover(DisabledItemHoverCallback disabledItemHoverCallback) {
        this.mOnDisabledItemHover = disabledItemHoverCallback;
    }

    public void setOnItemClick(Function<OCSideNavItem, ?> function) {
        this.mOnItemClick = function;
    }

    public OCSideNavItem getItem(int i) {
        for (OCSideNavItem oCSideNavItem : this.mSideNavItems) {
            if (oCSideNavItem.mViewId.intValue() == i) {
                return oCSideNavItem;
            }
        }
        return null;
    }

    public OCSideNavItem getItem(String str) {
        for (OCSideNavItem oCSideNavItem : this.mSideNavItems) {
            if (oCSideNavItem.mTitle.equals(str)) {
                return oCSideNavItem;
            }
        }
        return null;
    }

    /* Return type fixed from 'com.oculus.ocui.OCSideNavAdapter$SideNavViewHolder' to match base method */
    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context;
        int i2;
        OcsidenavItemBinding inflate = OcsidenavItemBinding.inflate(LayoutInflater.from(this.mContext), null);
        SideNavViewHolder sideNavViewHolder = new SideNavViewHolder(inflate);
        Integer num = this.itemBackgroundId;
        if (num != null) {
            context = this.mContext;
            i2 = num.intValue();
        } else {
            context = this.mContext;
            i2 = R.drawable.ocsidenav_item_background;
        }
        inflate.setBackground(context.getDrawable(i2));
        return sideNavViewHolder;
    }
}
