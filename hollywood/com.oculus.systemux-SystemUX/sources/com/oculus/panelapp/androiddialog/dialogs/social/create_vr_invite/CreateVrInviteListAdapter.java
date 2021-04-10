package com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteListItemBinding;
import java.util.ArrayList;
import java.util.List;

public class CreateVrInviteListAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private static final String TAG = LoggingUtil.tag(CreateVrInviteListAdapter.class);
    private final ListCallback mCallback;
    private final Context mContext;
    private RequestBuilder<Bitmap> mGlideFetcher;
    private RequestManager mGlideManager;
    private List<ListItem> mList;
    private final AndroidDialogPanelApp mPanelApp;
    private Long mSelectedItemId;

    public static abstract class ListCallback {
        /* access modifiers changed from: package-private */
        public void onItemHovered(MotionEvent motionEvent) {
        }

        /* access modifiers changed from: package-private */
        public void onItemSelected(ListItem listItem, ListItemViewHolder listItemViewHolder) {
        }
    }

    @VisibleForTesting
    protected CreateVrInviteListAdapter() {
        this.mList = new ArrayList();
        this.mSelectedItemId = null;
        this.mGlideManager = null;
        this.mGlideFetcher = null;
        this.mContext = null;
        this.mPanelApp = null;
        this.mCallback = null;
    }

    public CreateVrInviteListAdapter(Context context, AndroidDialogPanelApp androidDialogPanelApp, ListCallback listCallback) {
        this.mList = new ArrayList();
        this.mSelectedItemId = null;
        this.mGlideManager = null;
        this.mGlideFetcher = null;
        this.mContext = context;
        this.mPanelApp = androidDialogPanelApp;
        this.mCallback = listCallback;
        initialize(context);
    }

    /* access modifiers changed from: protected */
    public void initialize(Context context) {
        this.mGlideManager = Glide.with(context);
        this.mGlideFetcher = this.mGlideManager.asBitmap();
        setHasStableIds(true);
    }

    /* access modifiers changed from: protected */
    public void refreshData() {
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public ListCallback getCallback() {
        return this.mCallback;
    }

    public List<ListItem> getListItems() {
        return this.mList;
    }

    public void setListItems(List<ListItem> list) {
        this.mList.clear();
        this.mList.addAll(list);
        refreshData();
    }

    public void deselectListItemsFromViewHolder(ListItemViewHolder listItemViewHolder) {
        Log.d(TAG, "deselecting list items from viewholder");
        this.mSelectedItemId = null;
        listItemViewHolder.setSelected(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");
        return new ListItemViewHolder(SocialCreateVrInviteListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ListItemViewHolder listItemViewHolder, int i) {
        String str = TAG;
        Log.d(str, "onBindViewHolder at position " + String.valueOf(i));
        ListItem listItem = this.mList.get(i);
        Long selectedItemId = getSelectedItemId();
        boolean z = selectedItemId != null && getItemId(i) == selectedItemId.longValue();
        listItemViewHolder.setListItem(listItem);
        listItemViewHolder.setSelected(z);
    }

    public void onViewRecycled(ListItemViewHolder listItemViewHolder) {
        listItemViewHolder.unsetIcon();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return (long) this.mList.get(i).mId.hashCode();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Long getSelectedItemId() {
        return this.mSelectedItemId;
    }

    public void setSelectedItemId(Long l) {
        this.mSelectedItemId = l;
        refreshData();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onItemClicked(ListItemViewHolder listItemViewHolder) {
        int adapterPosition = listItemViewHolder.getAdapterPosition();
        if (adapterPosition >= 0 && adapterPosition < getItemCount()) {
            setSelectedItemId(Long.valueOf(listItemViewHolder.getItemId()));
            getCallback().onItemSelected(this.mList.get(adapterPosition), listItemViewHolder);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onItemHovered(MotionEvent motionEvent) {
        getCallback().onItemHovered(motionEvent);
    }

    public static class ListItem extends BaseObservable {
        private boolean mAlwaysShowRightGlyph = false;
        private final String mIconUri;
        private final String mId;
        private Drawable mRightGlyph = null;
        private boolean mShowRightGlyph = false;
        private final String mSubtitle;
        private final String mTitle;

        public ListItem(String str, String str2, String str3, String str4) {
            this.mId = str;
            this.mTitle = str2;
            this.mSubtitle = str3;
            this.mIconUri = str4;
        }

        public String getId() {
            return this.mId;
        }

        @Bindable
        public String getSubtitle() {
            return this.mSubtitle;
        }

        @Bindable
        public int getSubtitleVisibility() {
            String str = this.mSubtitle;
            return (str == null || str.length() == 0) ? 8 : 0;
        }

        @Bindable
        public String getTitle() {
            return this.mTitle;
        }

        public void setRightGlyph(Drawable drawable, boolean z) {
            this.mRightGlyph = drawable;
            this.mAlwaysShowRightGlyph = z;
            notifyPropertyChanged(BR.rightGlyph);
        }

        @Bindable
        public Drawable getRightGlyph() {
            if (this.mAlwaysShowRightGlyph || this.mShowRightGlyph) {
                return this.mRightGlyph;
            }
            return null;
        }

        public void setShowRightGlyph(boolean z) {
            this.mShowRightGlyph = z;
            notifyPropertyChanged(BR.rightGlyph);
        }
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnHoverListener {
        private final SocialCreateVrInviteListItemBinding mBinding;
        private ListItem mListItem = null;

        public ListItemViewHolder(SocialCreateVrInviteListItemBinding socialCreateVrInviteListItemBinding) {
            super(socialCreateVrInviteListItemBinding.getRoot());
            this.mBinding = socialCreateVrInviteListItemBinding;
            this.mBinding.createVrInviteListItemButton.setEventHandler(CreateVrInviteListAdapter.this.mPanelApp);
            this.mBinding.createVrInviteListItemButton.setOnClickListener(this);
            this.mBinding.createVrInviteListItemButton.setOnHoverListener(this);
        }

        public void setIconUri(String str) {
            if (str == null) {
                this.mBinding.leftIcon.setImageResource(R.drawable.social_create_vr_invite_image_placeholder);
                return;
            }
            ((RequestBuilder) ((RequestBuilder) CreateVrInviteListAdapter.this.mGlideFetcher.load(str).transform(new CenterCrop(), new RoundedCorners((int) CreateVrInviteListAdapter.this.mContext.getResources().getDimension(R.dimen.social_create_vr_invite_border_radius)))).placeholder(R.drawable.social_create_vr_invite_image_placeholder)).into(this.mBinding.leftIcon);
        }

        public void unsetIcon() {
            CreateVrInviteListAdapter.this.mGlideManager.clear(this.mBinding.leftIcon);
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

        public boolean isSelected() {
            return this.mBinding.createVrInviteListItemButton.isSelected();
        }

        public void onClick(View view) {
            CreateVrInviteListAdapter.this.onItemClicked(this);
        }

        public boolean onHover(View view, MotionEvent motionEvent) {
            CreateVrInviteListAdapter.this.onItemHovered(motionEvent);
            return false;
        }
    }
}
