package androidx.core.view;

import android.os.Build;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.R;

public final class ViewGroupCompat {
    private ViewGroupCompat() {
    }

    public static boolean isTransitionGroup(@NonNull ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(R.id.tag_transition_group);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && ViewCompat.getTransitionName(viewGroup) == null) ? false : true;
    }
}
