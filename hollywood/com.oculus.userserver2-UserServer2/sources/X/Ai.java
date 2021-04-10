package X;

import android.view.View;
import android.view.Window;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public abstract class Ai {
    @Nullable
    public final View A00(@IdRes int i) {
        if (!(this instanceof AbstractC0144Tp)) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" does not have a view");
            throw new IllegalStateException(sb.toString());
        }
        AbstractC0144Tp tp = (AbstractC0144Tp) this;
        if (!(tp instanceof C0047Cb)) {
            return null;
        }
        return ((C0047Cb) tp).A00.findViewById(i);
    }

    public final boolean A01() {
        if (!(this instanceof AbstractC0144Tp)) {
            return false;
        }
        AbstractC0144Tp tp = (AbstractC0144Tp) this;
        if (!(tp instanceof C0047Cb)) {
            return true;
        }
        Window window = ((C0047Cb) tp).A00.getWindow();
        if (window == null || window.peekDecorView() == null) {
            return false;
        }
        return true;
    }
}
