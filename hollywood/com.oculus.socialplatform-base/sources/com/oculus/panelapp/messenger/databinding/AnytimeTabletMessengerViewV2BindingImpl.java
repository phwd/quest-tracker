package com.oculus.panelapp.messenger.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.AnonymousClass2NC;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.HorizontalScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import com.facebook.acra.ErrorReporter;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.ComposeText;
import com.oculus.panelapp.messenger.views.MessengerFacepile;
import com.oculus.panelapp.messenger.views.MessengerView;
import com.oculus.panelapp.messenger.views.MessengerViewModel;
import com.oculus.socialplatform.R;
import okio.SegmentPool;

public class AnytimeTabletMessengerViewV2BindingImpl extends AnytimeTabletMessengerViewV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeMessengerViewModel(MessengerViewModel messengerViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 157) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 154) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 135) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 144) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 138) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 129) {
            synchronized (this) {
                this.mDirtyFlags |= 576;
            }
            return true;
        } else if (i == 116) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 161) {
            synchronized (this) {
                this.mDirtyFlags |= 32832;
            }
            return true;
        } else if (i == 130) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 164) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 150) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 125) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i == 111) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        } else if (i == 142) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == 127) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == 152) {
            synchronized (this) {
                this.mDirtyFlags |= SegmentPool.MAX_SIZE;
            }
            return true;
        } else if (i == 134) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (i != 133) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = ErrorReporter.SIGQUIT_MAX_REPORT_SIZE;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding
    public void setMessengerViewModel(@Nullable MessengerViewModel messengerViewModel) {
        updateRegistration(0, messengerViewModel);
        this.mMessengerViewModel = messengerViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(132);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.leftnav, 15);
        sparseIntArray.put(R.id.leftnav_guideline, 16);
        sparseIntArray.put(R.id.messenger_tablet_offline_view_stub, 17);
        sparseIntArray.put(R.id.leftbar_guideline, 18);
        sparseIntArray.put(R.id.leftbar_background, 19);
        sparseIntArray.put(R.id.thread_create, 20);
        sparseIntArray.put(R.id.thread_list, 21);
        sparseIntArray.put(R.id.thread_list_null_state_view_stub, 22);
        sparseIntArray.put(R.id.facepile_thread_icon, 23);
        sparseIntArray.put(R.id.draft_thread_partipant_entry_prompt, 24);
        sparseIntArray.put(R.id.draft_thread_participant_list, 25);
        sparseIntArray.put(R.id.header_guideline, 26);
        sparseIntArray.put(R.id.compose_bar_guideline, 27);
        sparseIntArray.put(R.id.compose_bar_scroll_view, 28);
        sparseIntArray.put(R.id.send_button, 29);
        sparseIntArray.put(R.id.quick_reply_0, 30);
        sparseIntArray.put(R.id.quick_reply_1, 31);
        sparseIntArray.put(R.id.quick_reply_2, 32);
        sparseIntArray.put(R.id.quick_reply_3, 33);
        sparseIntArray.put(R.id.quick_reply_4, 34);
        sparseIntArray.put(R.id.leave_chat_confirmation_stub, 35);
        sparseIntArray.put(R.id.one_on_one_blocked_thread_stub, 36);
        sparseIntArray.put(R.id.group_thread_containing_blocked_stub, 37);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b3, code lost:
        if (r0 != false) goto L_0x00b5;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 673
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2BindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeMessengerViewModel((MessengerViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (132 != i) {
            return false;
        }
        setMessengerViewModel((MessengerViewModel) obj);
        return true;
    }

    public AnytimeTabletMessengerViewV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 38, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public AnytimeTabletMessengerViewV2BindingImpl(AbstractC003408r r82, View view, Object[] objArr) {
        super(r82, view, 1, (OCButton) objArr[13], (Guideline) objArr[27], (HorizontalScrollView) objArr[28], (ComposeText) objArr[12], (OCTextView) objArr[14], (Group) objArr[10], (Group) objArr[11], (Group) objArr[3], (Group) objArr[8], (OCRecyclerView) objArr[25], (OCTextView) objArr[24], (MessengerFacepile) objArr[23], new AnonymousClass2NC((ViewStub) objArr[37]), (Guideline) objArr[26], new AnonymousClass2NC((ViewStub) objArr[35]), (View) objArr[19], (Guideline) objArr[18], (Group) objArr[1], (SocialTabletSideNav) objArr[15], (Guideline) objArr[16], (OCRecyclerView) objArr[9], new AnonymousClass2NC((ViewStub) objArr[17]), (OCTextView) objArr[2], (MessengerView) objArr[0], new AnonymousClass2NC((ViewStub) objArr[36]), (OCButton) objArr[30], (OCButton) objArr[31], (OCButton) objArr[32], (OCButton) objArr[33], (OCButton) objArr[34], (OCButton) objArr[29], (OCButton) objArr[5], (OCSpinner) objArr[6], (OCButton) objArr[7], (OCButton) objArr[20], (OCRecyclerView) objArr[21], new AnonymousClass2NC((ViewStub) objArr[22]), (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.audioInputButton.setTag(null);
        this.composeTextInput.setTag(null);
        this.composerBlockedView.setTag(null);
        this.composerCollapsedInputGroup.setTag(null);
        this.composerExpandedInputGroup.setTag(null);
        this.currentThreadHeader.setTag(null);
        this.draftThreadHeader.setTag(null);
        this.groupThreadContainingBlockedStub.A03 = this;
        this.leaveChatConfirmationStub.A03 = this;
        this.leftbarView.setTag(null);
        this.messageList.setTag(null);
        this.messengerTabletOfflineViewStub.A03 = this;
        this.messengerTabletTitle.setTag(null);
        this.messengerView.setTag(null);
        this.oneOnOneBlockedThreadStub.A03 = this;
        this.startParty.setTag(null);
        this.threadAddToPartySpinner.setTag(null);
        this.threadContextMenuButton.setTag(null);
        this.threadListNullStateViewStub.A03 = this;
        this.threadTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
