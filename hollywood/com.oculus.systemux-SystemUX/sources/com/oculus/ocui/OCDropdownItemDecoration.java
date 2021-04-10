package com.oculus.ocui;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OCDropdownItemDecoration extends RecyclerView.ItemDecoration {
    int mItemSpacing;

    public OCDropdownItemDecoration(int i) {
        this.mItemSpacing = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (state.getItemCount() != 0 && recyclerView.getChildAdapterPosition(view) != 0) {
            rect.top = this.mItemSpacing;
        }
    }
}
