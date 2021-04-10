package com.oculus.panelapp.messenger.views;

import android.content.Context;
import com.oculus.socialplatform.R;
import java.util.Arrays;
import java.util.List;

public class QuickReplyHelper {
    public Context mContext;
    public List<String> mQuickReplies;

    public void setLastMessageReceived(String str) {
    }

    public List<String> getQuickReplies() {
        return this.mQuickReplies;
    }

    public QuickReplyHelper(Context context) {
        this.mContext = context;
        this.mQuickReplies = Arrays.asList(context.getResources().getString(R.string.messenger_quick_reply_up_for_a_game), this.mContext.getResources().getString(R.string.messenger_quick_reply_join_me_in_vr), this.mContext.getResources().getString(R.string.messenger_quick_reply_start_a_party), this.mContext.getResources().getString(R.string.messenger_quick_reply_are_you_around), this.mContext.getResources().getString(R.string.messenger_quick_reply_lets_go));
    }
}
