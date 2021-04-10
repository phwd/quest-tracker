package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessengerReactionsSummaryViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final int MAX_NUM_OF_REACTIONS = 3;
    public static final String TAG = LoggingUtil.tag(MessengerReactionsSummaryViewModel.class);
    public Context mContext;
    public MessengerEmojiHandler mEmojiHandler;
    public MessengerPanelApp mPanelApp;
    public List<MessengerReaction> mReactions;
    public int mReactionsTotalCount;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mEmojiHandler = null;
    }

    @Nullable
    private Drawable getDrawableForEmoji(String str) {
        MessengerEmojiHandler messengerEmojiHandler = this.mEmojiHandler;
        if (messengerEmojiHandler != null) {
            return messengerEmojiHandler.getEmojiDrawableFromString(str);
        }
        Log.w(TAG, "getDrawableForEmoji: emoji handler is NULL");
        return null;
    }

    private MessengerReaction getSecondReaction() {
        if (this.mReactionsTotalCount > 1) {
            return this.mReactions.get(1);
        }
        return null;
    }

    private MessengerReaction getThirdReaction() {
        if (this.mReactionsTotalCount > 2) {
            return this.mReactions.get(2);
        }
        return null;
    }

    public static /* synthetic */ int lambda$reorderReactions$0(long j, MessengerReaction messengerReaction, MessengerReaction messengerReaction2) {
        if (messengerReaction.mActorId == j) {
            return -1;
        }
        if (messengerReaction2.mActorId == j) {
            return 1;
        }
        return ((String) Optional.ofNullable(messengerReaction.mActorName).get()).compareTo((String) Optional.ofNullable(messengerReaction2.mActorName).get());
    }

    private List<MessengerReaction> reorderReactions(List<MessengerReaction> list) {
        return (List) list.stream().sorted(new Comparator(Long.parseLong(this.mPanelApp.getAPIManager().mCurrentAPI.getUserID())) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$MessengerReactionsSummaryViewModel$l0Z9aVsuFVatxXp0pnw9QkBBXzc2 */
            public final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MessengerReactionsSummaryViewModel.lambda$reorderReactions$0(this.f$0, (MessengerReaction) obj, (MessengerReaction) obj2);
            }
        }).collect(Collectors.toList());
    }

    private void updateReactions(List<MessengerReaction> list) {
        if (list != null && list.size() != 0) {
            this.mReactions = reorderReactions(list);
            this.mReactionsTotalCount = list.size();
        }
    }

    @Nullable
    @Bindable
    public Drawable getFirstReactionEmoji() {
        return getDrawableForEmoji(this.mReactions.get(0).mReaction);
    }

    @Nullable
    @Bindable
    public String getFirstReactionUsername() {
        return (String) Optional.ofNullable(this.mReactions.get(0).mActorName).get();
    }

    @Bindable
    public int getReactionRemainingCountVisibility() {
        if (this.mReactionsTotalCount > 3) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public String getReactionsRemainingCount() {
        return this.mContext.getResources().getString(R.string.anytime_tablet_messenger_message_reactions_remaining_count, String.valueOf(this.mReactionsTotalCount - 3));
    }

    @Bindable
    public int getSecondReactionVisibility() {
        if (this.mReactionsTotalCount > 1) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public int getThirdReactionVisibility() {
        if (this.mReactionsTotalCount > 2) {
            return 0;
        }
        return 8;
    }

    public MessengerReactionsSummaryViewModel(Context context, MessengerPanelApp messengerPanelApp) {
        this.mContext = context;
        this.mPanelApp = messengerPanelApp;
        this.mEmojiHandler = messengerPanelApp.getEmojiHandler();
    }

    @Nullable
    @Bindable
    public Drawable getSecondReactionEmoji() {
        if (getSecondReaction() != null) {
            return getDrawableForEmoji(getSecondReaction().mReaction);
        }
        return null;
    }

    @Nullable
    @Bindable
    public String getSecondReactionUsername() {
        if (getSecondReaction() != null) {
            return (String) Optional.ofNullable(getSecondReaction().mActorName).get();
        }
        return null;
    }

    @Nullable
    @Bindable
    public Drawable getThirdReactionEmoji() {
        if (getThirdReaction() != null) {
            return getDrawableForEmoji(getThirdReaction().mReaction);
        }
        return null;
    }

    @Nullable
    @Bindable
    public String getThirdReactionUsername() {
        if (getThirdReaction() != null) {
            return (String) Optional.ofNullable(getThirdReaction().mActorName).get();
        }
        return null;
    }

    public void setReactions(List<MessengerReaction> list) {
        updateReactions(list);
        notifyChange();
    }
}
