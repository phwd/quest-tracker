package androidx.coordinatorlayout.widget;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface CoordinatorLayout$DefaultBehavior {
    Class<? extends CoordinatorLayout.Behavior> value();
}
