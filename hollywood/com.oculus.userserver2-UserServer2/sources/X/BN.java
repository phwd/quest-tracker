package X;

import android.annotation.SuppressLint;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"UnknownNullness"})
@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public abstract class BN {
    public final void A01(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    A01(transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
            } else if (!Th.A00(transition)) {
                List<View> targets = transition.getTargets();
                if (targets == null || targets.isEmpty()) {
                    int size = arrayList.size();
                    while (i < size) {
                        transition.addTarget(arrayList.get(i));
                        i++;
                    }
                }
            }
        }
    }

    public final void A02(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                A02(transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
        } else if (!Th.A00(transition) && (targets = transition.getTargets()) != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            if (arrayList2 != null) {
                int size = arrayList2.size();
                while (i < size) {
                    transition.addTarget(arrayList2.get(i));
                    i++;
                }
            }
            int size2 = arrayList.size();
            while (true) {
                size2--;
                if (size2 >= 0) {
                    transition.removeTarget(arrayList.get(size2));
                } else {
                    return;
                }
            }
        }
    }
}
