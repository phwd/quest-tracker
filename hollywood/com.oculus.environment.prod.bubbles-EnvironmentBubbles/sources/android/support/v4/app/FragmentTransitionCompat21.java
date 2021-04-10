package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class FragmentTransitionCompat21 {

    public static class EpicenterView {
        public View epicenter;
    }

    public interface ViewRetriever {
        View getView();
    }

    FragmentTransitionCompat21() {
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static Object cloneTransition(Object obj) {
        return obj != null ? ((Transition) obj).clone() : obj;
    }

    public static Object captureExitingViews(Object obj, View view, ArrayList<View> arrayList, Map<String, View> map, View view2) {
        if (obj == null) {
            return obj;
        }
        captureTransitioningViews(arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.add(view2);
        addTargets((Transition) obj, arrayList);
        return obj;
    }

    public static void excludeTarget(Object obj, View view, boolean z) {
        ((Transition) obj).excludeTarget(view, z);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    public static void setEpicenter(Object obj, View view) {
        final Rect boundsOnScreen = getBoundsOnScreen(view);
        ((Transition) obj).setEpicenterCallback(new Transition.EpicenterCallback() {
            /* class android.support.v4.app.FragmentTransitionCompat21.AnonymousClass1 */

            public Rect onGetEpicenter(Transition transition) {
                return boundsOnScreen;
            }
        });
    }

    public static Object wrapSharedElementTransition(Object obj) {
        Transition transition;
        if (obj == null || (transition = (Transition) obj) == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(transition);
        return transitionSet;
    }

    /* access modifiers changed from: private */
    public static void excludeViews(Transition transition, Transition transition2, ArrayList<View> arrayList, boolean z) {
        if (transition != null) {
            int size = transition2 == null ? 0 : arrayList.size();
            for (int i = 0; i < size; i++) {
                transition.excludeTarget(arrayList.get(i), z);
            }
        }
    }

    public static void excludeSharedElementViews(Object obj, Object obj2, Object obj3, ArrayList<View> arrayList, boolean z) {
        Transition transition = (Transition) obj3;
        excludeViews((Transition) obj, transition, arrayList, z);
        excludeViews((Transition) obj2, transition, arrayList, z);
    }

    public static void addTransitionTargets(Object obj, Object obj2, Object obj3, final View view, final ViewRetriever viewRetriever, final View view2, EpicenterView epicenterView, final Map<String, String> map, final ArrayList<View> arrayList, ArrayList<View> arrayList2, Map<String, View> map2, final Map<String, View> map3, ArrayList<View> arrayList3) {
        final Transition transition = (Transition) obj;
        final Transition transition2 = (Transition) obj3;
        Transition transition3 = (Transition) obj2;
        excludeViews(transition, transition2, arrayList2, true);
        if (obj != null || obj2 != null) {
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (obj2 != null) {
                setSharedElementTargets(transition3, view2, map2, arrayList3);
                excludeViews(transition, transition3, arrayList3, true);
                excludeViews(transition2, transition3, arrayList3, true);
            }
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                /* class android.support.v4.app.FragmentTransitionCompat21.AnonymousClass2 */

                public boolean onPreDraw() {
                    View view;
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    Transition transition = transition;
                    if (transition != null) {
                        transition.removeTarget(view2);
                    }
                    ViewRetriever viewRetriever = viewRetriever;
                    if (!(viewRetriever == null || (view = viewRetriever.getView()) == null)) {
                        if (!map.isEmpty()) {
                            FragmentTransitionCompat21.findNamedViews(map3, view);
                            map3.keySet().retainAll(map.values());
                            for (Map.Entry entry : map.entrySet()) {
                                View view2 = (View) map3.get((String) entry.getValue());
                                if (view2 != null) {
                                    view2.setTransitionName((String) entry.getKey());
                                }
                            }
                        }
                        if (transition != null) {
                            FragmentTransitionCompat21.captureTransitioningViews(arrayList, view);
                            arrayList.removeAll(map3.values());
                            arrayList.add(view2);
                            FragmentTransitionCompat21.addTargets(transition, arrayList);
                        }
                    }
                    FragmentTransitionCompat21.excludeViews(transition2, transition, arrayList, true);
                    return true;
                }
            });
            setSharedElementEpicenter(transition, epicenterView);
        }
    }

    public static Object mergeTransitions(Object obj, Object obj2, Object obj3, boolean z) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition == null || transition2 == null) {
            z = true;
        }
        if (z) {
            TransitionSet transitionSet = new TransitionSet();
            if (transition != null) {
                transitionSet.addTransition(transition);
            }
            if (transition2 != null) {
                transitionSet.addTransition(transition2);
            }
            if (transition3 == null) {
                return transitionSet;
            }
            transitionSet.addTransition(transition3);
            return transitionSet;
        }
        TransitionSet transitionSet2 = null;
        if (transition2 != null && transition != null) {
            transitionSet2 = new TransitionSet().addTransition(transition2).addTransition(transition).setOrdering(1);
        } else if (transition2 != null) {
            transitionSet2 = transition2;
        } else if (transition != null) {
            transitionSet2 = transition;
        }
        if (transition3 == null) {
            return transitionSet2;
        }
        TransitionSet transitionSet3 = new TransitionSet();
        if (transitionSet2 != null) {
            transitionSet3.addTransition(transitionSet2);
        }
        transitionSet3.addTransition(transition3);
        return transitionSet3;
    }

    public static void setSharedElementTargets(Object obj, View view, Map<String, View> map, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        arrayList.clear();
        arrayList.addAll(map.values());
        List<View> targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(targets, arrayList.get(i));
        }
        arrayList.add(view);
        addTargets(transitionSet, arrayList);
    }

    private static void bfsAddViewChildren(List<View> list, View view) {
        int size = list.size();
        if (!containedBeforeIndex(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!containedBeforeIndex(list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    private static void setSharedElementEpicenter(Transition transition, final EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback(new Transition.EpicenterCallback() {
                /* class android.support.v4.app.FragmentTransitionCompat21.AnonymousClass3 */
                private Rect mEpicenter;

                public Rect onGetEpicenter(Transition transition) {
                    if (this.mEpicenter == null && epicenterView.epicenter != null) {
                        this.mEpicenter = FragmentTransitionCompat21.getBoundsOnScreen(epicenterView.epicenter);
                    }
                    return this.mEpicenter;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static Rect getBoundsOnScreen(View view) {
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        return rect;
    }

    /* access modifiers changed from: private */
    public static void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                captureTransitioningViews(arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    public static void findNamedViews(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    findNamedViews(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static void cleanupTransitions(final View view, final View view2, Object obj, final ArrayList<View> arrayList, Object obj2, final ArrayList<View> arrayList2, Object obj3, final ArrayList<View> arrayList3, Object obj4, final ArrayList<View> arrayList4, final Map<String, View> map) {
        final Transition transition = (Transition) obj;
        final Transition transition2 = (Transition) obj2;
        final Transition transition3 = (Transition) obj3;
        final Transition transition4 = (Transition) obj4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                /* class android.support.v4.app.FragmentTransitionCompat21.AnonymousClass4 */

                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    Transition transition = transition;
                    if (transition != null) {
                        FragmentTransitionCompat21.removeTargets(transition, arrayList);
                        FragmentTransitionCompat21.excludeViews(transition, transition2, arrayList2, false);
                        FragmentTransitionCompat21.excludeViews(transition, transition3, arrayList3, false);
                    }
                    Transition transition2 = transition2;
                    if (transition2 != null) {
                        FragmentTransitionCompat21.removeTargets(transition2, arrayList2);
                        FragmentTransitionCompat21.excludeViews(transition2, transition, arrayList, false);
                        FragmentTransitionCompat21.excludeViews(transition2, transition3, arrayList3, false);
                    }
                    Transition transition3 = transition3;
                    if (transition3 != null) {
                        FragmentTransitionCompat21.removeTargets(transition3, arrayList3);
                    }
                    for (Map.Entry entry : map.entrySet()) {
                        ((View) entry.getValue()).setTransitionName((String) entry.getKey());
                    }
                    int size = arrayList4.size();
                    for (int i = 0; i < size; i++) {
                        transition4.excludeTarget((View) arrayList4.get(i), false);
                    }
                    transition4.excludeTarget(view2, false);
                    return true;
                }
            });
        }
    }

    public static void removeTargets(Object obj, ArrayList<View> arrayList) {
        List<View> targets;
        Transition transition = (Transition) obj;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            for (int i = 0; i < transitionCount; i++) {
                removeTargets(transitionSet.getTransitionAt(i), arrayList);
            }
        } else if (!hasSimpleTarget(transition) && (targets = transition.getTargets()) != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                transition.removeTarget(arrayList.get(size));
            }
        }
    }

    public static void addTargets(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                addTargets(transitionSet.getTransitionAt(i), arrayList);
                i++;
            }
        } else if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets())) {
            int size = arrayList.size();
            while (i < size) {
                transition.addTarget(arrayList.get(i));
                i++;
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes());
    }

    private static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
