package com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters;

import android.os.Handler;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterUpdatesCoalescer {
    private final RecyclerView.Adapter mAdapter;
    private boolean mIsDiffing = false;
    private ILibraryUpdate mPendingUpdate = null;

    public AdapterUpdatesCoalescer(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    public void setUpdate(ILibraryUpdate iLibraryUpdate) {
        if (this.mIsDiffing) {
            this.mPendingUpdate = iLibraryUpdate;
            return;
        }
        this.mIsDiffing = true;
        startDiffingInBackground(iLibraryUpdate);
    }

    private void startDiffingInBackground(ILibraryUpdate iLibraryUpdate) {
        iLibraryUpdate.onStart();
        new Thread(new Runnable(iLibraryUpdate, new Handler()) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$AdapterUpdatesCoalescer$n0p1zrtg981DxZtjubilzI77iHs */
            private final /* synthetic */ ILibraryUpdate f$1;
            private final /* synthetic */ Handler f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                AdapterUpdatesCoalescer.this.lambda$startDiffingInBackground$140$AdapterUpdatesCoalescer(this.f$1, this.f$2);
            }
        }).start();
    }

    public /* synthetic */ void lambda$startDiffingInBackground$140$AdapterUpdatesCoalescer(ILibraryUpdate iLibraryUpdate, Handler handler) {
        handler.post(new Runnable(iLibraryUpdate, DiffUtil.calculateDiff(iLibraryUpdate.getDiffUtilCallback())) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$AdapterUpdatesCoalescer$On8upHzjMDRRai_F9tIsFVPHsU */
            private final /* synthetic */ ILibraryUpdate f$1;
            private final /* synthetic */ DiffUtil.DiffResult f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                AdapterUpdatesCoalescer.this.lambda$null$139$AdapterUpdatesCoalescer(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$null$139$AdapterUpdatesCoalescer(ILibraryUpdate iLibraryUpdate, DiffUtil.DiffResult diffResult) {
        iLibraryUpdate.beforeDispatchUpdates();
        diffResult.dispatchUpdatesTo(this.mAdapter);
        iLibraryUpdate.afterDispatchUpdates();
        ILibraryUpdate iLibraryUpdate2 = this.mPendingUpdate;
        if (iLibraryUpdate2 != null) {
            this.mPendingUpdate = null;
            startDiffingInBackground(iLibraryUpdate2);
            return;
        }
        this.mIsDiffing = false;
    }
}
