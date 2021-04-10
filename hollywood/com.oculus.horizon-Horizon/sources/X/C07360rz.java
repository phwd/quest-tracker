package X;

import android.graphics.Rect;
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
/* renamed from: X.0rz  reason: invalid class name and case insensitive filesystem */
public final class C07360rz extends AnonymousClass0A6 {
    @Override // X.AnonymousClass0A6
    public final Object A04(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // X.AnonymousClass0A6
    public final Object A05(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }

    @Override // X.AnonymousClass0A6
    public final Object A06(Object obj, Object obj2, Object obj3) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition == null) {
            transition = null;
            if (transition2 != null) {
                transition = transition2;
            }
        } else if (transition2 != null) {
            transition = new TransitionSet().addTransition(transition).addTransition(transition2).setOrdering(1);
        }
        if (transition3 == null) {
            return transition;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (transition != null) {
            transitionSet.addTransition(transition);
        }
        transitionSet.addTransition(transition3);
        return transitionSet;
    }

    @Override // X.AnonymousClass0A6
    public final Object A07(Object obj, Object obj2, Object obj3) {
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

    @Override // X.AnonymousClass0A6
    public final void A08(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    @Override // X.AnonymousClass0A6
    public final void A09(@NonNull Fragment fragment, @NonNull Object obj, @NonNull AnonymousClass05d r4, @NonNull Runnable runnable) {
        ((Transition) obj).addListener(new AnonymousClass0A1(this, runnable));
    }

    @Override // X.AnonymousClass0A6
    public final void A0A(Object obj, Rect rect) {
        if (obj != null) {
            ((Transition) obj).setEpicenterCallback(new AnonymousClass0A2(this, rect));
        }
    }

    @Override // X.AnonymousClass0A6
    public final void A0B(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    @Override // X.AnonymousClass0A6
    public final void A0C(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).removeTarget(view);
        }
    }

    @Override // X.AnonymousClass0A6
    public final void A0D(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            AnonymousClass0A6.A00(view, rect);
            ((Transition) obj).setEpicenterCallback(new AnonymousClass09y(this, rect));
        }
    }

    @Override // X.AnonymousClass0A6
    public final void A0E(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).addListener(new C004909z(this, view, arrayList));
    }

    @Override // X.AnonymousClass0A6
    public final void A0F(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List<View> targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            int size2 = targets.size();
            int i2 = 0;
            while (true) {
                if (i2 < size2) {
                    if (targets.get(i2) == view2) {
                        break;
                    }
                    i2++;
                } else {
                    targets.add(view2);
                    for (int i3 = size2; i3 < targets.size(); i3++) {
                        View view3 = targets.get(i3);
                        if (view3 instanceof ViewGroup) {
                            ViewGroup viewGroup = (ViewGroup) view3;
                            int childCount = viewGroup.getChildCount();
                            for (int i4 = 0; i4 < childCount; i4++) {
                                View childAt = viewGroup.getChildAt(i4);
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= size2) {
                                        targets.add(childAt);
                                        break;
                                    }
                                    if (targets.get(i5) == childAt) {
                                        break;
                                    }
                                    i5++;
                                }
                            }
                        }
                    }
                }
            }
        }
        targets.add(view);
        arrayList.add(view);
        A0H(transitionSet, arrayList);
    }

    @Override // X.AnonymousClass0A6
    public final void A0G(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((Transition) obj).addListener(new AnonymousClass0A0(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // X.AnonymousClass0A6
    public final void A0H(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    A0H(transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
            } else if (!A01(transition)) {
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

    @Override // X.AnonymousClass0A6
    public final void A0I(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                A0I(transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
        } else if (!A01(transition) && (targets = transition.getTargets()) != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
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

    @Override // X.AnonymousClass0A6
    public final void A0J(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            A0I(transitionSet, arrayList, arrayList2);
        }
    }

    public static boolean A01(Transition transition) {
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

    @Override // X.AnonymousClass0A6
    public final boolean A0K(Object obj) {
        return obj instanceof Transition;
    }
}
