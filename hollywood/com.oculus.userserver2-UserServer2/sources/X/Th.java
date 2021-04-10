package X;

import android.transition.Transition;
import androidx.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
public final class Th extends BN {
    public static boolean A00(Transition transition) {
        List<Integer> targetIds = transition.getTargetIds();
        if (targetIds != null && !targetIds.isEmpty()) {
            return true;
        }
        List<String> targetNames = transition.getTargetNames();
        if (targetNames != null && !targetNames.isEmpty()) {
            return true;
        }
        List<Class> targetTypes = transition.getTargetTypes();
        if (targetTypes == null || targetTypes.isEmpty()) {
            return false;
        }
        return true;
    }
}
