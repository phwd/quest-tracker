package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerReactionsSummaryViewModel;
import com.oculus.socialplatform.R;

public class MessageReactionsSummaryPopupBindingImpl extends MessageReactionsSummaryPopupBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(5);
        sIncludes = r3;
        r3.A00(new String[]{"anytime_tablet_messenger_message_reactions_summary_item", "anytime_tablet_messenger_message_reactions_summary_item", "anytime_tablet_messenger_message_reactions_summary_item"}, new int[]{2, 3, 4}, new int[]{R.layout.anytime_tablet_messenger_message_reactions_summary_item, R.layout.anytime_tablet_messenger_message_reactions_summary_item, R.layout.anytime_tablet_messenger_message_reactions_summary_item});
    }

    private boolean onChangeFirstReaction(AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeSecondReaction(AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeThirdReaction(AnytimeTabletMessengerMessageReactionsSummaryItemBinding anytimeTabletMessengerMessageReactionsSummaryItemBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModel(MessengerReactionsSummaryViewModel messengerReactionsSummaryViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 118) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 131) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 162) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 165) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 141) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 163) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 139) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 158) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i == 114) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        } else if (i != 147) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.firstReaction.hasPendingBindings() || this.secondReaction.hasPendingBindings() || this.thirdReaction.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16384;
        }
        this.firstReaction.invalidateAll();
        this.secondReaction.invalidateAll();
        this.thirdReaction.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.MessageReactionsSummaryPopupBinding
    public void setViewModel(@Nullable MessengerReactionsSummaryViewModel messengerReactionsSummaryViewModel) {
        updateRegistration(3, messengerReactionsSummaryViewModel);
        this.mViewModel = messengerReactionsSummaryViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        Drawable drawable;
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        String str4;
        Drawable drawable2;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MessengerReactionsSummaryViewModel messengerReactionsSummaryViewModel = this.mViewModel;
        Drawable drawable3 = null;
        if ((32760 & j) != 0) {
            if ((j & 16424) == 0 || messengerReactionsSummaryViewModel == null) {
                str3 = null;
            } else {
                str3 = messengerReactionsSummaryViewModel.getFirstReactionUsername();
            }
            if ((j & 16408) == 0 || messengerReactionsSummaryViewModel == null) {
                drawable = null;
            } else {
                drawable = messengerReactionsSummaryViewModel.getFirstReactionEmoji();
            }
            if ((j & 24584) == 0 || messengerReactionsSummaryViewModel == null) {
                i = 0;
            } else {
                i = messengerReactionsSummaryViewModel.getReactionRemainingCountVisibility();
            }
            if ((j & 17416) == 0 || messengerReactionsSummaryViewModel == null) {
                drawable2 = null;
            } else {
                drawable2 = messengerReactionsSummaryViewModel.getThirdReactionEmoji();
            }
            if ((j & 16456) == 0 || messengerReactionsSummaryViewModel == null) {
                i2 = 0;
            } else {
                i2 = messengerReactionsSummaryViewModel.getSecondReactionVisibility();
            }
            if ((j & 16648) == 0 || messengerReactionsSummaryViewModel == null) {
                str = null;
            } else {
                str = messengerReactionsSummaryViewModel.getSecondReactionUsername();
            }
            if ((j & 20488) == 0 || messengerReactionsSummaryViewModel == null) {
                str4 = null;
            } else {
                str4 = messengerReactionsSummaryViewModel.getReactionsRemainingCount();
            }
            if ((j & 18440) == 0 || messengerReactionsSummaryViewModel == null) {
                str2 = null;
            } else {
                str2 = messengerReactionsSummaryViewModel.getThirdReactionUsername();
            }
            if (!((j & 16520) == 0 || messengerReactionsSummaryViewModel == null)) {
                drawable3 = messengerReactionsSummaryViewModel.getSecondReactionEmoji();
            }
            if (!((j & 16904) == 0 || messengerReactionsSummaryViewModel == null)) {
                i3 = messengerReactionsSummaryViewModel.getThirdReactionVisibility();
            }
            i3 = 0;
        } else {
            str4 = null;
            str3 = null;
            drawable = null;
            str = null;
            drawable2 = null;
            str2 = null;
            i = 0;
            i2 = 0;
            i3 = 0;
        }
        if ((j & 16408) != 0) {
            this.firstReaction.setReactionEmoji(drawable);
        }
        if ((j & 16424) != 0) {
            this.firstReaction.setReactionUsername(str3);
        }
        if ((j & 20488) != 0) {
            C11051qV.A02(this.moreReactions, str4);
        }
        if ((j & 24584) != 0) {
            this.moreReactions.setVisibility(i);
        }
        if ((j & 16456) != 0) {
            this.secondReaction.mRoot.setVisibility(i2);
        }
        if ((j & 16520) != 0) {
            this.secondReaction.setReactionEmoji(drawable3);
        }
        if ((16648 & j) != 0) {
            this.secondReaction.setReactionUsername(str);
        }
        if ((16904 & j) != 0) {
            this.thirdReaction.mRoot.setVisibility(i3);
        }
        if ((j & 17416) != 0) {
            this.thirdReaction.setReactionEmoji(drawable2);
        }
        if ((j & 18440) != 0) {
            this.thirdReaction.setReactionUsername(str2);
        }
        AnonymousClass1uW.executeBindingsOn(this.firstReaction);
        AnonymousClass1uW.executeBindingsOn(this.secondReaction);
        AnonymousClass1uW.executeBindingsOn(this.thirdReaction);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeThirdReaction((AnytimeTabletMessengerMessageReactionsSummaryItemBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeFirstReaction((AnytimeTabletMessengerMessageReactionsSummaryItemBinding) obj, i2);
        }
        if (i == 2) {
            return onChangeSecondReaction((AnytimeTabletMessengerMessageReactionsSummaryItemBinding) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeViewModel((MessengerReactionsSummaryViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((MessengerReactionsSummaryViewModel) obj);
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.firstReaction.setLifecycleOwner(r2);
        this.secondReaction.setLifecycleOwner(r2);
        this.thirdReaction.setLifecycleOwner(r2);
    }

    public MessageReactionsSummaryPopupBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 5, sIncludes, (SparseIntArray) null));
    }

    public MessageReactionsSummaryPopupBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 4, (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) objArr[2], (OCTextView) objArr[1], (LinearLayout) objArr[0], (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) objArr[3], (AnytimeTabletMessengerMessageReactionsSummaryItemBinding) objArr[4]);
        this.mDirtyFlags = -1;
        this.moreReactions.setTag(null);
        this.reactionsSummaryView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
