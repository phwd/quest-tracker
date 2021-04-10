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
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantItemBinding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantTextInputBinding;

public class DraftThreadParticipantListAdapter extends AnonymousClass2MI<DraftThreadParticipantListAdapterItem, AnonymousClass1Ah> {
    public static final AnonymousClass2NM<DraftThreadParticipantListAdapterItem> DIFF_CALLBACK = new AnonymousClass2NM<DraftThreadParticipantListAdapterItem>() {
        /* class com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapter.AnonymousClass1 */

        public boolean areContentsTheSame(@NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem, @NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem2) {
            return draftThreadParticipantListAdapterItem.equals(draftThreadParticipantListAdapterItem2);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // X.AnonymousClass2NM
        public /* bridge */ /* synthetic */ boolean areContentsTheSame(@NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem, @NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem2) {
            return draftThreadParticipantListAdapterItem.equals(draftThreadParticipantListAdapterItem2);
        }

        public boolean areItemsTheSame(@NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem, @NonNull DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem2) {
            return draftThreadParticipantListAdapterItem.getID() == draftThreadParticipantListAdapterItem2.getID();
        }
    };
    public static final String TAG = LoggingUtil.tag(DraftThreadParticipantListAdapter.class);
    public MessengerPanelApp mPanelApp;

    public DraftThreadParticipantListAdapter(MessengerPanelApp messengerPanelApp) {
        super(DIFF_CALLBACK);
        this.mPanelApp = messengerPanelApp;
    }

    @Override // X.AnonymousClass1Aj
    @NonNull
    public AnonymousClass1Ah onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == DraftThreadParticipantListAdapterItemType.PARTICIPANT.hashCode()) {
            return new DraftThreadParticipantViewHolder(AnytimeTabletMessengerDraftThreadParticipantItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        }
        if (i == DraftThreadParticipantListAdapterItemType.TEXT_ENTRY.hashCode()) {
            return new DraftThreadParticipantTextInputViewHolder(AnytimeTabletMessengerDraftThreadParticipantTextInputBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false), this.mPanelApp);
        }
        Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
        return null;
    }

    @Override // X.AnonymousClass1Aj
    public long getItemId(int i) {
        return (long) getItem(i).hashCode();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        return ((DraftThreadParticipantListAdapterItem) getItem(i)).getItemViewType().hashCode();
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(AnonymousClass1Ah r4, int i) {
        DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem = (DraftThreadParticipantListAdapterItem) getItem(i);
        if (draftThreadParticipantListAdapterItem.getItemViewType() == DraftThreadParticipantListAdapterItemType.PARTICIPANT) {
            ((DraftThreadParticipantViewHolder) r4).bindParticipant(((DraftThreadParticipantAdapterItem) draftThreadParticipantListAdapterItem).mMessengerContact);
        } else if (draftThreadParticipantListAdapterItem.getItemViewType() == DraftThreadParticipantListAdapterItemType.TEXT_ENTRY) {
            DraftThreadParticipantTextInputViewHolder draftThreadParticipantTextInputViewHolder = (DraftThreadParticipantTextInputViewHolder) r4;
            boolean z = false;
            if (i == 0) {
                z = true;
            }
            draftThreadParticipantTextInputViewHolder.bindIsStartingItem(z);
        }
    }
}
