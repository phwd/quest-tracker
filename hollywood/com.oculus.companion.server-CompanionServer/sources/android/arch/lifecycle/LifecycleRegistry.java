package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.core.internal.SafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import android.support.coordinatorlayout.R$styleable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private final WeakReference<LifecycleOwner> mLifecycleOwner;
    private boolean mNewEventOccurred = false;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap<>();
    private ArrayList<Lifecycle.State> mParentStates = new ArrayList<>();
    private Lifecycle.State mState;

    public LifecycleRegistry(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = new WeakReference<>(lifecycleOwner);
        this.mState = Lifecycle.State.INITIALIZED;
    }

    public void markState(Lifecycle.State state) {
        moveToState(state);
    }

    public void handleLifecycleEvent(Lifecycle.Event event) {
        moveToState(getStateAfter(event));
    }

    private void moveToState(Lifecycle.State state) {
        if (this.mState != state) {
            this.mState = state;
            if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
                this.mNewEventOccurred = true;
                return;
            }
            this.mHandlingEvent = true;
            sync();
            this.mHandlingEvent = false;
        }
    }

    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.mObserverMap.eldest().getValue().mState;
        Lifecycle.State state2 = this.mObserverMap.newest().getValue().mState;
        if (state == state2 && this.mState == state2) {
            return true;
        }
        return false;
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, ObserverWithState> ceil = this.mObserverMap.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = ceil != null ? ceil.getValue().mState : null;
        if (!this.mParentStates.isEmpty()) {
            ArrayList<Lifecycle.State> arrayList = this.mParentStates;
            state = arrayList.get(arrayList.size() - 1);
        }
        return min(min(this.mState, state2), state);
    }

    @Override // android.arch.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        Lifecycle.State state = this.mState;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state2);
        if (this.mObserverMap.putIfAbsent(lifecycleObserver, observerWithState) == null && (lifecycleOwner = this.mLifecycleOwner.get()) != null) {
            boolean z = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.mAddingObserverCounter++;
            while (observerWithState.mState.compareTo((Enum) calculateTargetState) < 0 && this.mObserverMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.mState);
                observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
                popParentState();
                calculateTargetState = calculateTargetState(lifecycleObserver);
            }
            if (!z) {
                sync();
            }
            this.mAddingObserverCounter--;
        }
    }

    private void popParentState() {
        ArrayList<Lifecycle.State> arrayList = this.mParentStates;
        arrayList.remove(arrayList.size() - 1);
    }

    private void pushParentState(Lifecycle.State state) {
        this.mParentStates.add(state);
    }

    @Override // android.arch.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver lifecycleObserver) {
        this.mObserverMap.remove(lifecycleObserver);
    }

    @Override // android.arch.lifecycle.Lifecycle
    public Lifecycle.State getCurrentState() {
        return this.mState;
    }

    static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        switch (AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()]) {
            case 1:
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity /*{ENCODED_INT: 2}*/:
                return Lifecycle.State.CREATED;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior /*{ENCODED_INT: 3}*/:
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges /*{ENCODED_INT: 4}*/:
                return Lifecycle.State.STARTED;
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge /*{ENCODED_INT: 5}*/:
                return Lifecycle.State.RESUMED;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline /*{ENCODED_INT: 6}*/:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.arch.lifecycle.LifecycleRegistry$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event = new int[Lifecycle.Event.values().length];
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State = new int[Lifecycle.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|(2:17|18)|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|(2:17|18)|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0053 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0086 */
        static {
            /*
            // Method dump skipped, instructions count: 146
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.LifecycleRegistry.AnonymousClass1.<clinit>():void");
        }
    }

    private static Lifecycle.Event downEvent(Lifecycle.State state) {
        int i = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State[state.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        } else if (i == 2) {
            return Lifecycle.Event.ON_DESTROY;
        } else {
            if (i == 3) {
                return Lifecycle.Event.ON_STOP;
            }
            if (i == 4) {
                return Lifecycle.Event.ON_PAUSE;
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
            throw new IllegalArgumentException();
        }
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state) {
        int i = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State[state.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            } else if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> */
    /* JADX WARN: Multi-variable type inference failed */
    private void forwardPass(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = this.mObserverMap.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.mNewEventOccurred) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.mState.compareTo((Enum) this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                pushParentState(observerWithState.mState);
                observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
                popParentState();
            }
        }
    }

    private void backwardPass(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> next = descendingIterator.next();
            ObserverWithState value = next.getValue();
            while (value.mState.compareTo((Enum) this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(next.getKey())) {
                Lifecycle.Event downEvent = downEvent(value.mState);
                pushParentState(getStateAfter(downEvent));
                value.dispatchEvent(lifecycleOwner, downEvent);
                popParentState();
            }
        }
    }

    private void sync() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo((Enum) this.mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> newest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && newest != null && this.mState.compareTo((Enum) newest.getValue().mState) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
        this.mNewEventOccurred = false;
    }

    static Lifecycle.State min(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    /* access modifiers changed from: package-private */
    public static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.mLifecycleObserver = Lifecycling.getCallback(lifecycleObserver);
            this.mState = state;
        }

        /* access modifiers changed from: package-private */
        public void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State stateAfter = LifecycleRegistry.getStateAfter(event);
            this.mState = LifecycleRegistry.min(this.mState, stateAfter);
            this.mLifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.mState = stateAfter;
        }
    }
}