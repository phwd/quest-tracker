package X;

import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
/* renamed from: X.0vA  reason: invalid class name */
public final class AnonymousClass0vA extends AnonymousClass0A7 {
    @Override // X.AnonymousClass0A7
    public final Object A01(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // X.AnonymousClass0A7
    public final Object A02(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }

    @Override // X.AnonymousClass0A7
    public final Object A03(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.addTransition((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.addTransition((Transition) obj3);
        }
        return transitionSet;
    }

    @Override // X.AnonymousClass0A7
    public final void A04(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    @Override // X.AnonymousClass0A7
    public final void A05(@NonNull Fragment fragment, @NonNull Object obj, @NonNull AnonymousClass05j r4, @NonNull Runnable runnable) {
        ((Transition) obj).addListener(new AnonymousClass0A2(this, runnable));
    }

    @Override // X.AnonymousClass0A7
    public final void A06(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    @Override // X.AnonymousClass0A7
    public final void A07(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).removeTarget(view);
        }
    }

    @Override // X.AnonymousClass0A7
    public final void A08(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).addListener(new AnonymousClass0A0(this, view, arrayList));
    }

    @Override // X.AnonymousClass0A7
    public final void A09(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((Transition) obj).addListener(new AnonymousClass0A1(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // X.AnonymousClass0A7
    public final void A0A(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    A0A(transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
            } else if (!A00(transition)) {
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

    @Override // X.AnonymousClass0A7
    public final void A0B(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                A0B(transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
        } else if (!A00(transition) && (targets = transition.getTargets()) != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
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

    @Override // X.AnonymousClass0A7
    public final void A0C(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            A0B(transitionSet, arrayList, arrayList2);
        }
    }

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

    @Override // X.AnonymousClass0A7
    public final boolean A0D(Object obj) {
        return obj instanceof Transition;
    }
}
