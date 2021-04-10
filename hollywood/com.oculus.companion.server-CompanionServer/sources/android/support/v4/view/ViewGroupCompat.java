package android.support.v4.view;

import android.os.Build;
import android.support.compat.R$id;
import android.view.ViewGroup;

public final class ViewGroupCompat {
    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(R$id.tag_transition_group);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && ViewCompat.getTransitionName(viewGroup) == null) ? false : true;
    }
}
