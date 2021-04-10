package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.FriendListRowData;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.tablet.utils.ProfilePictureHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class FriendListAdapter extends ArrayAdapter<FriendListRowData> {
    private static final int LAYOUT_RESOURCE_ID = R.layout.social_friend_list_item_layout;
    private static final int ROW_DEFAULT_DRAWABLE_ID = R.drawable.friend_list_row_clickable_background;
    private static final int ROW_SELECTED_COLOR_ID = R.color.button_background_secondary_disabled;
    private static final String TAG = LoggingUtil.tag(FriendListAdapter.class);
    private InviteClickListener mInviteClickListener;
    private final ProfilePictureHelper mProfilePictureHelper;
    private HashSet<String> mSelectedUIDs = new HashSet<>();
    private HashMap<String, Integer> mUIDToFriendPosition = new HashMap<>();

    public FriendListAdapter(Context context, List<FriendListRowData> list) {
        super(context, LAYOUT_RESOURCE_ID, list);
        this.mProfilePictureHelper = new ProfilePictureHelper(context);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mUIDToFriendPosition.clear();
        for (int i = 0; i < getCount(); i++) {
            this.mUIDToFriendPosition.put(((FriendListRowData) getItem(i)).id, Integer.valueOf(i));
        }
    }

    public void setInviteClickListener(InviteClickListener inviteClickListener) {
        this.mInviteClickListener = inviteClickListener;
    }

    public FriendListRowData getFriendByID(String str) {
        HashMap<String, Integer> hashMap = this.mUIDToFriendPosition;
        if (hashMap == null) {
            Log.e(TAG, "getFriendByID - mUIDToFriendPosition = null.  How did this happen?");
            return null;
        }
        Integer num = hashMap.get(str);
        if (num == null) {
            return null;
        }
        return (FriendListRowData) getItem(num.intValue());
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(LAYOUT_RESOURCE_ID, viewGroup, false);
        final FriendListRowData friendListRowData = (FriendListRowData) getItem(i);
        if (friendListRowData == null) {
            return inflate;
        }
        inflate.setTag(String.format("social_friend_list_item_layout:%s", friendListRowData.id));
        this.mProfilePictureHelper.setImageViewFromUrl((ImageView) inflate.findViewById(R.id.profile_picture), friendListRowData.profilePhotoURL, !friendListRowData.presenceStatus.contains("Offline"));
        ((TextView) inflate.findViewById(R.id.display_name)).setText(friendListRowData.displayName);
        ((TextView) inflate.findViewById(R.id.presence_status)).setText(friendListRowData.presenceStatus);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.action_icon);
        int i2 = AnonymousClass2.$SwitchMap$com$oculus$horizoncontent$horizon$HorizonContent$FriendList$UserInviteState[friendListRowData.inviteState.ordinal()];
        if (i2 == 1) {
            imageButton.setBackgroundResource(R.drawable.ic_party_invite_sent);
        } else if (i2 != 2) {
            imageButton.setBackgroundResource(R.drawable.oc_icon_party_add_filled_24_d2d2d2);
        } else {
            imageButton.setBackgroundResource(R.drawable.ic_in_party);
        }
        boolean isInvitable = isInvitable(friendListRowData.inviteState);
        inflate.setEnabled(isInvitable);
        updateSelectionUI(inflate, this.mSelectedUIDs.contains(friendListRowData.id), isInvitable);
        inflate.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter.AnonymousClass1 */

            public void onClick(View view) {
                FriendListAdapter.this.mInviteClickListener.onInviteClick(friendListRowData.id);
                if (FriendListAdapter.this.mSelectedUIDs.contains(friendListRowData.id)) {
                    FriendListAdapter.this.mSelectedUIDs.remove(friendListRowData.id);
                } else {
                    FriendListAdapter.this.mSelectedUIDs.add(friendListRowData.id);
                }
                FriendListAdapter friendListAdapter = FriendListAdapter.this;
                friendListAdapter.updateSelectionUI(inflate, friendListAdapter.mSelectedUIDs.contains(friendListRowData.id), FriendListAdapter.this.isInvitable(friendListRowData.inviteState));
            }
        });
        return inflate;
    }

    /* renamed from: com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$horizon$HorizonContent$FriendList$UserInviteState = new int[HorizonContent.FriendList.UserInviteState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.horizoncontent.horizon.HorizonContent$FriendList$UserInviteState[] r0 = com.oculus.horizoncontent.horizon.HorizonContent.FriendList.UserInviteState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter.AnonymousClass2.$SwitchMap$com$oculus$horizoncontent$horizon$HorizonContent$FriendList$UserInviteState = r0
                int[] r0 = com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter.AnonymousClass2.$SwitchMap$com$oculus$horizoncontent$horizon$HorizonContent$FriendList$UserInviteState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.horizon.HorizonContent$FriendList$UserInviteState r1 = com.oculus.horizoncontent.horizon.HorizonContent.FriendList.UserInviteState.INVITED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter.AnonymousClass2.$SwitchMap$com$oculus$horizoncontent$horizon$HorizonContent$FriendList$UserInviteState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.horizon.HorizonContent$FriendList$UserInviteState r1 = com.oculus.horizoncontent.horizon.HorizonContent.FriendList.UserInviteState.JOINED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.dialogs.FriendListAdapter.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isInvitable(HorizonContent.FriendList.UserInviteState userInviteState) {
        return (userInviteState == HorizonContent.FriendList.UserInviteState.INVITED || userInviteState == HorizonContent.FriendList.UserInviteState.JOINED) ? false : true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateSelectionUI(View view, boolean z, boolean z2) {
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_icon);
        if (!z2) {
            view.setBackgroundResource(ROW_DEFAULT_DRAWABLE_ID);
        } else if (z) {
            view.setBackgroundColor(getContext().getResources().getColor(ROW_SELECTED_COLOR_ID));
            imageButton.setBackgroundResource(R.drawable.ic_add_to_party_hover);
        } else {
            view.setBackgroundResource(ROW_DEFAULT_DRAWABLE_ID);
            imageButton.setBackgroundResource(R.drawable.oc_icon_party_add_filled_24_d2d2d2);
        }
    }
}
