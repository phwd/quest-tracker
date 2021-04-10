package X;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1B2  reason: invalid class name */
public abstract class AnonymousClass1B2 {
    @Deprecated
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
    }

    @Deprecated
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
    }

    @Deprecated
    public void getItemOffsets(@NonNull Rect rect, int i, @NonNull RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }

    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull AnonymousClass1As r5) {
        getItemOffsets(rect, ((C05831Bi) view.getLayoutParams()).A01.getLayoutPosition(), recyclerView);
    }

    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull AnonymousClass1As r3) {
    }

    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull AnonymousClass1As r3) {
    }
}
