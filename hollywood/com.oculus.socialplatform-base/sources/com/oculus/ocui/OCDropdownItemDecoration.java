package com.oculus.ocui;

import X.AnonymousClass1As;
import X.AnonymousClass1B2;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OCDropdownItemDecoration extends AnonymousClass1B2 {
    public int mItemSpacing;

    public OCDropdownItemDecoration(int i) {
        this.mItemSpacing = i;
    }

    @Override // X.AnonymousClass1B2
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull AnonymousClass1As r5) {
        super.getItemOffsets(rect, view, recyclerView, r5);
        if (r5.A00() != 0 && recyclerView.getChildAdapterPosition(view) != 0) {
            rect.top = this.mItemSpacing;
        }
    }
}
