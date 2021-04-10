package com.oculus.panelapp.messenger.views;

import X.AnonymousClass006;
import X.AnonymousClass1Ah;
import X.AnonymousClass2MI;
import X.AnonymousClass2NM;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadItemBinding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadItemV2Binding;

public class ThreadListAdapter extends AnonymousClass2MI<ThreadListAdapterItem, AnonymousClass1Ah> {
    public static final AnonymousClass2NM<ThreadListAdapterItem> DIFF_CALLBACK = new AnonymousClass2NM<ThreadListAdapterItem>() {
        /* class com.oculus.panelapp.messenger.views.ThreadListAdapter.AnonymousClass1 */

        public boolean areContentsTheSame(@NonNull ThreadListAdapterItem threadListAdapterItem, @NonNull ThreadListAdapterItem threadListAdapterItem2) {
            return threadListAdapterItem.equals(threadListAdapterItem2);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // X.AnonymousClass2NM
        public /* bridge */ /* synthetic */ boolean areContentsTheSame(@NonNull ThreadListAdapterItem threadListAdapterItem, @NonNull ThreadListAdapterItem threadListAdapterItem2) {
            return threadListAdapterItem.equals(threadListAdapterItem2);
        }

        public boolean areItemsTheSame(@NonNull ThreadListAdapterItem threadListAdapterItem, @NonNull ThreadListAdapterItem threadListAdapterItem2) {
            return threadListAdapterItem.getID() == threadListAdapterItem2.getID();
        }
    };
    public static final String TAG = LoggingUtil.tag(ThreadListAdapter.class);
    public MessengerPanelApp mPanelApp;

    public ThreadListAdapter(MessengerPanelApp messengerPanelApp) {
        super(DIFF_CALLBACK);
        this.mPanelApp = messengerPanelApp;
    }

    @Override // X.AnonymousClass1Aj
    @NonNull
    public AnonymousClass1Ah onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ThreadListAdapterItemType.EXISTING.hashCode()) {
            return new ThreadViewHolder(AnytimeTabletMessengerThreadItemV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        }
        if (i == ThreadListAdapterItemType.DRAFT.hashCode()) {
            return new DraftThreadViewHolder(AnytimeTabletMessengerDraftThreadItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        }
        Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
        return null;
    }

    @Override // X.AnonymousClass1Aj
    public long getItemId(int i) {
        return ((ThreadListAdapterItem) getItem(i)).getID();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        return ((ThreadListAdapterItem) getItem(i)).getItemViewType().hashCode();
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(AnonymousClass1Ah r5, int i) {
        if (((ThreadListAdapterItem) getItem(i)).getItemViewType() == ThreadListAdapterItemType.EXISTING) {
            ((ThreadViewHolder) r5).bindThread(((ExistingThreadAdapterItem) getItem(i)).mThread);
        }
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.TEST) {
            r5.itemView.setTag(AnonymousClass006.A00(((ThreadListAdapterItem) getItem(i)).getID(), "thread"));
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewRecycled(AnonymousClass1Ah r2) {
        super.onViewRecycled(r2);
        if (r2 instanceof ThreadViewHolder) {
            ((ThreadViewHolder) r2).onViewRecycled();
        }
    }
}
