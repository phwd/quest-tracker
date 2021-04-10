package com.oculus.panelapp.messenger.views;

import X.AnonymousClass2MI;
import X.AnonymousClass2NM;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsItemBinding;

public class MessageReactionsListAdapter extends AnonymousClass2MI<MessageReactionsListAdapterItem, MessageReactionsListItemViewHolder> {
    public static final AnonymousClass2NM<MessageReactionsListAdapterItem> DIFF_CALLBACK = new AnonymousClass2NM<MessageReactionsListAdapterItem>() {
        /* class com.oculus.panelapp.messenger.views.MessageReactionsListAdapter.AnonymousClass1 */

        public boolean areContentsTheSame(@NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem, @NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem2) {
            return messageReactionsListAdapterItem.equals(messageReactionsListAdapterItem2);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // X.AnonymousClass2NM
        public /* bridge */ /* synthetic */ boolean areContentsTheSame(@NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem, @NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem2) {
            return messageReactionsListAdapterItem.equals(messageReactionsListAdapterItem2);
        }

        public boolean areItemsTheSame(@NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem, @NonNull MessageReactionsListAdapterItem messageReactionsListAdapterItem2) {
            return messageReactionsListAdapterItem.getID() == messageReactionsListAdapterItem2.getID();
        }
    };
    public static final String TAG = LoggingUtil.tag(MessageReactionsListAdapter.class);
    public final Context mContext;

    public MessageReactionsListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    @Override // X.AnonymousClass1Aj
    public long getItemId(int i) {
        return (long) getItem(i).hashCode();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        return ((MessageReactionsListAdapterItem) getItem(i)).getItemViewType().hashCode();
    }

    public void onBindViewHolder(@NonNull MessageReactionsListItemViewHolder messageReactionsListItemViewHolder, int i) {
        MessageReactionsListAdapterItem messageReactionsListAdapterItem = (MessageReactionsListAdapterItem) getItem(i);
        if (messageReactionsListAdapterItem.getItemViewType() == MessageReactionsListAdapterItemType.USER) {
            messageReactionsListItemViewHolder.setReaction(((MessageReactionAdapterItem) messageReactionsListAdapterItem).mReaction);
        }
    }

    @Override // X.AnonymousClass1Aj
    public MessageReactionsListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MessageReactionsListItemViewHolder(AnytimeTabletMessengerMessageReactionsItemBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false));
    }
}
