package com.oculus.panelapp.social;

import X.AnonymousClass006;
import X.AnonymousClass1Ah;
import X.AnonymousClass1Aj;
import X.AnonymousClass1BN;
import X.AnonymousClass2OT;
import X.AnonymousClass2OV;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding;
import java.util.ArrayList;
import java.util.List;

public class SocialActionAdapter extends AnonymousClass1Aj<AnonymousClass1Ah> {
    public static final String TAG = LoggingUtil.tag(SocialActionAdapter.class);
    public List<SocialAdapterItem> mItems = new ArrayList();
    public SocialPanelApp mPanelApp;

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        return this.mItems.get(i).getItemViewType().ordinal();
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(AnonymousClass1Ah r3, int i) {
        if (r3 instanceof UserCardViewHolder) {
            ((UserCardViewHolder) r3).bindUser((SocialUserAdapterItem) this.mItems.get(i));
        } else if (r3 instanceof SocialGuidedActionViewHolder) {
            ((SocialGuidedActionViewHolder) r3).bindData((SocialGuidedActionAdapterItem) this.mItems.get(i));
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == SocialAdapterItemType.USER.ordinal()) {
            Context context = viewGroup.getContext();
            return new UserCardViewHolder(AnytimeTabletSocialUserCardBinding.inflate(LayoutInflater.from(context), viewGroup, false), context, this.mPanelApp);
        } else if (i == SocialAdapterItemType.GUIDED_ACTION.ordinal()) {
            Context context2 = viewGroup.getContext();
            return new SocialGuidedActionViewHolder(AnytimeTabletSocialGuidedActionCardBinding.inflate(LayoutInflater.from(context2), viewGroup, false), context2, this.mPanelApp);
        } else {
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewDetachedFromWindow(AnonymousClass1Ah r2) {
        if (r2 instanceof UserViewHolder) {
            ((UserViewHolder) r2).hideOverflowMenu();
        }
    }

    public void setData(List<SocialAdapterItem> list) {
        AnonymousClass2OT A00 = AnonymousClass2OV.A00(new SocialAdapterDiffCallback(list, this.mItems));
        this.mItems.clear();
        this.mItems.addAll(list);
        A00.A01(new AnonymousClass1BN(this));
    }

    public SocialActionAdapter(SocialPanelApp socialPanelApp) {
        this.mPanelApp = socialPanelApp;
    }
}
