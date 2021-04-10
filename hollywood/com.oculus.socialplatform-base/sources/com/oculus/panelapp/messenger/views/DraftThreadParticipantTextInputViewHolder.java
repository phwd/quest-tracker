package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantTextInputBinding;
import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import com.oculus.socialplatform.R;
import java.util.function.Function;

public class DraftThreadParticipantTextInputViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(DraftThreadParticipantTextInputViewHolder.class);
    public AnytimeTabletMessengerDraftThreadParticipantTextInputBinding mBinding;
    public Context mContext;
    public Handler mHandler = new Handler(Looper.getMainLooper());
    public MessengerPanelApp mPanelApp;
    public DraftThreadParticipantsDropdown mParticipantEntryDropdown;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DraftThreadParticipantTextInputViewHolder(com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantTextInputBinding r3, com.oculus.panelapp.messenger.MessengerPanelApp r4) {
        /*
            r2 = this;
            android.view.View r0 = r3.mRoot
            r2.<init>(r0)
            r2.mBinding = r3
            r2.mPanelApp = r4
            android.content.Context r0 = r0.getContext()
            r2.mContext = r0
            r2.initializeParticipantEntryDropdown()
            r2.initializeTextInput()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            android.os.Handler r0 = new android.os.Handler
            r0.<init>(r1)
            r2.mHandler = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.DraftThreadParticipantTextInputViewHolder.<init>(com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantTextInputBinding, com.oculus.panelapp.messenger.MessengerPanelApp):void");
    }

    private void initializeParticipantEntryDropdown() {
        DraftThreadParticipantsDropdown draftThreadParticipantsDropdown = new DraftThreadParticipantsDropdown(this.mContext, this.mPanelApp);
        this.mParticipantEntryDropdown = draftThreadParticipantsDropdown;
        draftThreadParticipantsDropdown.mOnMessengerContactItemClickListener = new Function() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantTextInputViewHolder$zLLdTOXfHLf94ENLWWGhs9mLYlY2 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DraftThreadParticipantTextInputViewHolder.this.lambda$initializeParticipantEntryDropdown$1$DraftThreadParticipantTextInputViewHolder((MessengerContact) obj);
            }
        };
    }

    public void bindIsStartingItem(boolean z) {
        ComposeText composeText;
        String str;
        if (z) {
            composeText = this.mBinding.draftThreadPartipantEntryTextInput;
            str = this.mContext.getResources().getString(R.string.anytime_tablet_messenger_thread_compose_view_participant_entry_hint);
        } else {
            composeText = this.mBinding.draftThreadPartipantEntryTextInput;
            str = "";
        }
        composeText.setHint(str);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$DraftThreadParticipantTextInputViewHolder$bRwzSONsjQic7HWGm0m0OYSvwc2 */

            public final void run() {
                DraftThreadParticipantTextInputViewHolder.this.lambda$bindIsStartingItem$0$DraftThreadParticipantTextInputViewHolder();
            }
        });
    }

    public void initializeTextInput() {
        ComposeText composeText = this.mBinding.draftThreadPartipantEntryTextInput;
        composeText.mEventHandler = this.mPanelApp;
        composeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class com.oculus.panelapp.messenger.views.DraftThreadParticipantTextInputViewHolder.AnonymousClass1 */

            public void onFocusChange(View view, boolean z) {
                DraftThreadParticipantTextInputViewHolder draftThreadParticipantTextInputViewHolder = DraftThreadParticipantTextInputViewHolder.this;
                if (!z) {
                    draftThreadParticipantTextInputViewHolder.mParticipantEntryDropdown.hide();
                } else if (draftThreadParticipantTextInputViewHolder.mBinding.draftThreadPartipantEntryTextInput.getText().length() == 0) {
                    DraftThreadParticipantTextInputViewHolder.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_DRAFT_THREAD_PARTICIPANT_TEXT_INPUT, SurfaceType.THREAD_VIEW);
                } else {
                    DraftThreadParticipantTextInputViewHolder draftThreadParticipantTextInputViewHolder2 = DraftThreadParticipantTextInputViewHolder.this;
                    DraftThreadParticipantsDropdown draftThreadParticipantsDropdown = draftThreadParticipantTextInputViewHolder2.mParticipantEntryDropdown;
                    ComposeText composeText = draftThreadParticipantTextInputViewHolder2.mBinding.draftThreadPartipantEntryTextInput;
                    draftThreadParticipantsDropdown.show(composeText, composeText.getText().toString());
                }
            }
        });
        this.mBinding.draftThreadPartipantEntryTextInput.addTextChangedListener(new TextWatcher() {
            /* class com.oculus.panelapp.messenger.views.DraftThreadParticipantTextInputViewHolder.AnonymousClass2 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    DraftThreadParticipantTextInputViewHolder.this.mParticipantEntryDropdown.hide();
                    return;
                }
                DraftThreadParticipantTextInputViewHolder draftThreadParticipantTextInputViewHolder = DraftThreadParticipantTextInputViewHolder.this;
                draftThreadParticipantTextInputViewHolder.mParticipantEntryDropdown.show(draftThreadParticipantTextInputViewHolder.mBinding.draftThreadPartipantEntryTextInput, editable.toString());
            }
        });
    }

    public /* synthetic */ void lambda$bindIsStartingItem$0$DraftThreadParticipantTextInputViewHolder() {
        this.mBinding.draftThreadPartipantEntryTextInput.requestFocus();
    }

    public /* synthetic */ Object lambda$initializeParticipantEntryDropdown$1$DraftThreadParticipantTextInputViewHolder(MessengerContact messengerContact) {
        if (this.mPanelApp.getAPIManager().mDraftThread != null) {
            this.mPanelApp.getAPIManager().mDraftThread.addParticipant(messengerContact);
        }
        this.mBinding.draftThreadPartipantEntryTextInput.setText("");
        this.mParticipantEntryDropdown.clear();
        this.mParticipantEntryDropdown.hide();
        return true;
    }
}
