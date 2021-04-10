package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessengerReactionsPillViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final int MAX_NUM_OF_REACTIONS_DISPLAYED = 3;
    public static final String TAG = LoggingUtil.tag(MessengerReactionsPillViewModel.class);
    public MessengerEmojiHandler mEmojiHandler;
    public int mReactionsTotalCount;
    @Nullable
    public List<String> mTopReactions;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mTopReactions = null;
        this.mEmojiHandler = null;
    }

    @Nullable
    private Drawable getDrawableForEmoji(int i) {
        MessengerEmojiHandler messengerEmojiHandler = this.mEmojiHandler;
        if (messengerEmojiHandler != null) {
            return messengerEmojiHandler.getEmojiDrawableFromString(this.mTopReactions.get(i));
        }
        Log.w(TAG, "getDrawableForEmoji: emoji handler is NULL");
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.util.List<java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    private void updateTopReactions(List<MessengerReaction> list) {
        if (!(list == null || list.size() == 0)) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(list.get(i).mReaction, Integer.valueOf(((Number) hashMap.getOrDefault(list.get(i).mReaction, 0)).intValue() + 1));
            }
            ArrayList arrayList = new ArrayList(hashMap.entrySet());
            Collections.sort(arrayList, $$Lambda$MessengerReactionsPillViewModel$2CimoQm7v9OmTwz8JGyCdswkeA02.INSTANCE);
            if (this.mTopReactions == null) {
                this.mTopReactions = new ArrayList();
            }
            for (int i2 = 0; i2 < Math.min(arrayList.size(), 3); i2++) {
                this.mTopReactions.add(i2, ((Map.Entry) arrayList.get(i2)).getKey());
            }
        }
    }

    @Nullable
    @Bindable
    public Drawable getFirstReaction() {
        List<String> list = this.mTopReactions;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return getDrawableForEmoji(0);
    }

    @Bindable
    public int getReactionTotalCountVisibility() {
        List<String> list = this.mTopReactions;
        if (list == null || this.mReactionsTotalCount <= list.size()) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public String getReactionsTotalCount() {
        return String.valueOf(this.mReactionsTotalCount);
    }

    @Nullable
    @Bindable
    public Drawable getSecondReaction() {
        List<String> list = this.mTopReactions;
        if (list == null || list.size() <= 1) {
            return null;
        }
        return getDrawableForEmoji(1);
    }

    @Bindable
    public int getSecondReactionVisibility() {
        List<String> list = this.mTopReactions;
        if (list == null || list.size() <= 1 || this.mTopReactions.get(1) == null || this.mTopReactions.get(1).isEmpty()) {
            return 8;
        }
        return 0;
    }

    @Nullable
    @Bindable
    public Drawable getThirdReaction() {
        List<String> list = this.mTopReactions;
        if (list == null || list.size() <= 2) {
            return null;
        }
        return getDrawableForEmoji(2);
    }

    @Bindable
    public int getThirdReactionVisibility() {
        List<String> list = this.mTopReactions;
        if (list == null || list.size() <= 2 || this.mTopReactions.get(2) == null || this.mTopReactions.get(2).isEmpty()) {
            return 8;
        }
        return 0;
    }

    public static /* synthetic */ int lambda$updateTopReactions$0(Map.Entry entry, Map.Entry entry2) {
        return ((Number) entry2.getValue()).intValue() - ((Number) entry.getValue()).intValue();
    }

    public void setReactions(List<MessengerReaction> list) {
        this.mReactionsTotalCount = list.size();
        updateTopReactions(list);
        notifyPropertyChanged(159);
        notifyPropertyChanged(124);
        notifyPropertyChanged(162);
        notifyPropertyChanged(149);
        notifyPropertyChanged(163);
        notifyPropertyChanged(112);
        notifyPropertyChanged(128);
    }

    public void setEmojiHandler(MessengerEmojiHandler messengerEmojiHandler) {
        this.mEmojiHandler = messengerEmojiHandler;
    }
}
