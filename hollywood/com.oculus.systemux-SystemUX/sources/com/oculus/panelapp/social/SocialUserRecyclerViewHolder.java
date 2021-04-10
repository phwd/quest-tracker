package com.oculus.panelapp.social;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* access modifiers changed from: package-private */
public abstract class SocialUserRecyclerViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    AsyncSource mAsyncActionSource = null;

    public enum AsyncSource {
        MAIN_BUTTON,
        SECONDARY_BUTTON,
        START_PARTY_BUTTON
    }

    /* access modifiers changed from: package-private */
    public abstract void setCurrentAsyncAction(AsyncSource asyncSource);

    public SocialUserRecyclerViewHolder(View view) {
        super(view);
    }
}
