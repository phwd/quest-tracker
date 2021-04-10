package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import X.AnonymousClass1Ah;
import X.AnonymousClass1Aj;
import X.AnonymousClass1uc;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteListItemBinding;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.List;

public class CreateVrInviteListAdapter extends AnonymousClass1Aj<ListItemViewHolder> {
    public static final String TAG = LoggingUtil.tag(CreateVrInviteListAdapter.class);
    public final ListCallback mCallback;
    public final Context mContext;
    public List<ListItem> mList;
    public final SocialAndroidBackPanelApp mPanelApp;
    public Long mSelectedItemId;

    public static abstract class ListCallback {
        public void onItemHovered(MotionEvent motionEvent) {
        }

        public void onItemSelected(ListItem listItem, ListItemViewHolder listItemViewHolder) {
        }
    }

    public static class ListItem extends AnonymousClass1uc {
        public boolean mAlwaysShowRightGlyph = false;
        public final String mIconUri;
        public final String mId;
        public Drawable mRightGlyph = null;
        public boolean mShowRightGlyph = false;
        public final String mSubtitle;
        public final String mTitle;

        public String getId() {
            return this.mId;
        }

        @Bindable
        public Drawable getRightGlyph() {
            if (this.mAlwaysShowRightGlyph || this.mShowRightGlyph) {
                return this.mRightGlyph;
            }
            return null;
        }

        @Bindable
        public String getSubtitle() {
            return this.mSubtitle;
        }

        @Bindable
        public int getSubtitleVisibility() {
            String str = this.mSubtitle;
            if (str == null || str.length() == 0) {
                return 8;
            }
            return 0;
        }

        @Bindable
        public String getTitle() {
            return this.mTitle;
        }

        public void setRightGlyph(Drawable drawable, boolean z) {
            this.mRightGlyph = drawable;
            this.mAlwaysShowRightGlyph = z;
            notifyPropertyChanged(182);
        }

        public void setShowRightGlyph(boolean z) {
            this.mShowRightGlyph = z;
            notifyPropertyChanged(182);
        }

        public ListItem(String str, String str2, String str3, String str4) {
            this.mId = str;
            this.mTitle = str2;
            this.mSubtitle = str3;
            this.mIconUri = str4;
        }
    }

    public class ListItemViewHolder extends AnonymousClass1Ah implements View.OnClickListener, View.OnHoverListener {
        public final SocialCreateVrInviteListItemBinding mBinding;
        public ListItem mListItem = null;

        public void unsetIcon() {
        }

        public ListItemViewHolder(SocialCreateVrInviteListItemBinding socialCreateVrInviteListItemBinding) {
            super(socialCreateVrInviteListItemBinding.mRoot);
            this.mBinding = socialCreateVrInviteListItemBinding;
            OCButton oCButton = socialCreateVrInviteListItemBinding.createVrInviteListItemButton;
            oCButton.mEventHandler = CreateVrInviteListAdapter.this.mPanelApp;
            oCButton.setOnClickListener(this);
            oCButton.setOnHoverListener(this);
        }

        public boolean isSelected() {
            return this.mBinding.createVrInviteListItemButton.isSelected();
        }

        public void onClick(View view) {
            CreateVrInviteListAdapter.this.onItemClicked(this);
        }

        public boolean onHover(View view, MotionEvent motionEvent) {
            return false;
        }

        public void setIconUri(String str) {
            if (str == null) {
                this.mBinding.leftIcon.setImageResource(R.drawable.social_create_vr_invite_image_placeholder);
            }
        }

        public void setListItem(ListItem listItem) {
            this.mListItem = listItem;
            this.mBinding.setViewModel(listItem);
            setIconUri(listItem.mIconUri);
        }

        public void setSelected(boolean z) {
            this.mBinding.createVrInviteListItemButton.setSelected(z);
            this.mListItem.setShowRightGlyph(z);
        }
    }

    public void deselectListItemsFromViewHolder(ListItemViewHolder listItemViewHolder) {
        this.mSelectedItemId = null;
        listItemViewHolder.setSelected(false);
    }

    public void initialize(Context context) {
        setHasStableIds(true);
    }

    public static /* synthetic */ void access$400(CreateVrInviteListAdapter createVrInviteListAdapter, MotionEvent motionEvent) {
    }

    public ListCallback getCallback() {
        return this.mCallback;
    }

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // X.AnonymousClass1Aj
    public long getItemId(int i) {
        return (long) this.mList.get(i).mId.hashCode();
    }

    public List<ListItem> getListItems() {
        return this.mList;
    }

    @VisibleForTesting
    public Long getSelectedItemId() {
        return this.mSelectedItemId;
    }

    public void setListItems(List<ListItem> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setSelectedItemId(Long l) {
        this.mSelectedItemId = l;
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onItemClicked(ListItemViewHolder listItemViewHolder) {
        int bindingAdapterPosition = listItemViewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition >= 0 && bindingAdapterPosition < getItemCount()) {
            setSelectedItemId(Long.valueOf(listItemViewHolder.mItemId));
            this.mCallback.onItemSelected(this.mList.get(bindingAdapterPosition), listItemViewHolder);
        }
    }

    public void refreshData() {
        notifyDataSetChanged();
    }

    private void onItemHovered(MotionEvent motionEvent) {
    }

    @VisibleForTesting
    public CreateVrInviteListAdapter() {
        this.mList = new ArrayList();
        this.mSelectedItemId = null;
        this.mContext = null;
        this.mPanelApp = null;
        this.mCallback = null;
    }

    public CreateVrInviteListAdapter(Context context, SocialAndroidBackPanelApp socialAndroidBackPanelApp, ListCallback listCallback) {
        this.mList = new ArrayList();
        this.mSelectedItemId = null;
        this.mContext = context;
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mCallback = listCallback;
        setHasStableIds(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r1 != 0) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter.ListItemViewHolder r8, int r9) {
        /*
            r7 = this;
            java.util.List<com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter$ListItem> r0 = r7.mList
            java.lang.Object r6 = r0.get(r9)
            com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter$ListItem r6 = (com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter.ListItem) r6
            java.lang.Long r0 = r7.mSelectedItemId
            if (r0 == 0) goto L_0x0019
            long r4 = r7.getItemId(r9)
            long r2 = r0.longValue()
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r0 = 1
            if (r1 == 0) goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            r8.setListItem(r6)
            r8.setSelected(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter.onBindViewHolder(com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter$ListItemViewHolder, int):void");
    }

    @Override // X.AnonymousClass1Aj
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ListItemViewHolder(SocialCreateVrInviteListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.1Ah] */
    @Override // X.AnonymousClass1Aj
    public /* bridge */ /* synthetic */ void onViewRecycled(ListItemViewHolder listItemViewHolder) {
    }

    public void onViewRecycled(ListItemViewHolder listItemViewHolder) {
    }
}
