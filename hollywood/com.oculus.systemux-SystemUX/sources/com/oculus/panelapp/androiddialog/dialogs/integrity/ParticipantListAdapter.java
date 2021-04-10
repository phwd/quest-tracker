package com.oculus.panelapp.androiddialog.dialogs.integrity;

import android.content.Context;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityParticipantSelectItemBinding;
import com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantViewHolder;
import com.oculus.vrshell.panels.DensityUtils;
import java.util.List;

public class ParticipantListAdapter extends RecyclerView.Adapter<ParticipantViewHolder> {
    private static final String TAG = LoggingUtil.tag(ParticipantListAdapter.class);
    private final ActionType mActionType;
    private final ParticipantViewHolder.BlockSelectorClickCallback mBlockCallback;
    private final Context mContext;
    private final AndroidDialogPanelApp mPanelApp;
    private final ParticipantThumbnailLoader mParticipantThumbnailLoader = new ParticipantThumbnailLoader(new ViewOutlineProvider() {
        /* class com.oculus.panelapp.androiddialog.dialogs.integrity.ParticipantListAdapter.AnonymousClass1 */

        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) DensityUtils.dipToPixelsInt(20.0f, ParticipantListAdapter.this.mPanelApp.getContext().getResources().getDisplayMetrics()));
        }
    });
    private final List<MessengerThreadParticipant> mParticipants;
    private final SocialLogger mSocialLogger;
    private final String mThreadKey;
    private final String mUserFBID;

    ParticipantListAdapter(Context context, AndroidDialogPanelApp androidDialogPanelApp, SocialLogger socialLogger, ActionType actionType, String str, String str2, List<MessengerThreadParticipant> list, ParticipantViewHolder.BlockSelectorClickCallback blockSelectorClickCallback) {
        this.mContext = context;
        this.mPanelApp = androidDialogPanelApp;
        this.mSocialLogger = socialLogger;
        this.mActionType = actionType;
        this.mThreadKey = str;
        this.mUserFBID = str2;
        this.mParticipants = list;
        this.mBlockCallback = blockSelectorClickCallback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ParticipantViewHolder(MessengerIntegrityParticipantSelectItemBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false), this.mPanelApp, this.mSocialLogger, this.mActionType, this.mThreadKey, this.mUserFBID, this.mBlockCallback);
    }

    public void onBindViewHolder(@NonNull ParticipantViewHolder participantViewHolder, int i) {
        participantViewHolder.setParticipant(this.mParticipantThumbnailLoader, this.mParticipants.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mParticipants.size();
    }
}
