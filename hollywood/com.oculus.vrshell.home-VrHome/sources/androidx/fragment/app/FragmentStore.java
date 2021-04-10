package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* access modifiers changed from: package-private */
public class FragmentStore {
    private final HashMap<String, FragmentStateManager> mActive = new HashMap<>();
    private final ArrayList<Fragment> mAdded = new ArrayList<>();

    FragmentStore() {
    }

    /* access modifiers changed from: package-private */
    public void resetActiveFragments() {
        this.mActive.clear();
    }

    /* access modifiers changed from: package-private */
    public void restoreAddedFragments(List<String> added) {
        this.mAdded.clear();
        if (added != null) {
            for (String who : added) {
                Fragment f = findActiveFragment(who);
                if (f == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + who + ")");
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + who + "): " + f);
                }
                addFragment(f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeActive(FragmentStateManager newlyActive) {
        this.mActive.put(newlyActive.getFragment().mWho, newlyActive);
    }

    /* access modifiers changed from: package-private */
    public void addFragment(Fragment fragment) {
        if (this.mAdded.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.mAdded) {
            this.mAdded.add(fragment);
        }
        fragment.mAdded = true;
    }

    /* access modifiers changed from: package-private */
    public void dispatchStateChange(int state) {
        Iterator<Fragment> it = this.mAdded.iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = this.mActive.get(it.next().mWho);
            if (fragmentStateManager != null) {
                fragmentStateManager.setFragmentManagerState(state);
            }
        }
        for (FragmentStateManager fragmentStateManager2 : this.mActive.values()) {
            if (fragmentStateManager2 != null) {
                fragmentStateManager2.setFragmentManagerState(state);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeFragment(Fragment fragment) {
        synchronized (this.mAdded) {
            this.mAdded.remove(fragment);
        }
        fragment.mAdded = false;
    }

    /* access modifiers changed from: package-private */
    public void makeInactive(FragmentStateManager newlyInactive) {
        Fragment f = newlyInactive.getFragment();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.getFragment();
                if (f.mWho.equals(fragment.mTargetWho)) {
                    fragment.mTarget = f;
                    fragment.mTargetWho = null;
                }
            }
        }
        this.mActive.put(f.mWho, null);
        if (f.mTargetWho != null) {
            f.mTarget = findActiveFragment(f.mTargetWho);
        }
    }

    /* access modifiers changed from: package-private */
    public void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    /* access modifiers changed from: package-private */
    public ArrayList<FragmentState> saveActiveFragments() {
        ArrayList<FragmentState> active = new ArrayList<>(this.mActive.size());
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment f = fragmentStateManager.getFragment();
                FragmentState fs = fragmentStateManager.saveState();
                active.add(fs);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Saved state of " + f + ": " + fs.mSavedFragmentState);
                }
            }
        }
        return active;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> saveAddedFragments() {
        ArrayList<String> added;
        synchronized (this.mAdded) {
            if (this.mAdded.isEmpty()) {
                added = null;
            } else {
                added = new ArrayList<>(this.mAdded.size());
                Iterator<Fragment> it = this.mAdded.iterator();
                while (it.hasNext()) {
                    Fragment f = it.next();
                    added.add(f.mWho);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + f.mWho + "): " + f);
                    }
                }
            }
        }
        return added;
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> getFragments() {
        ArrayList arrayList;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            arrayList = new ArrayList(this.mAdded);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> getActiveFragments() {
        ArrayList<Fragment> activeFragments = new ArrayList<>();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                activeFragments.add(fragmentStateManager.getFragment());
            } else {
                activeFragments.add(null);
            }
        }
        return activeFragments;
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentById(int id) {
        for (int i = this.mAdded.size() - 1; i >= 0; i--) {
            Fragment f = this.mAdded.get(i);
            if (f != null && f.mFragmentId == id) {
                return f;
            }
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment f2 = fragmentStateManager.getFragment();
                if (f2.mFragmentId == id) {
                    return f2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentByTag(String tag) {
        if (tag != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (tag != null) {
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                if (fragmentStateManager != null) {
                    Fragment f2 = fragmentStateManager.getFragment();
                    if (tag.equals(f2.mTag)) {
                        return f2;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean containsActiveFragment(String who) {
        return this.mActive.containsKey(who);
    }

    /* access modifiers changed from: package-private */
    public FragmentStateManager getFragmentStateManager(String who) {
        return this.mActive.get(who);
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentByWho(String who) {
        Fragment f;
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (!(fragmentStateManager == null || (f = fragmentStateManager.getFragment().findFragmentByWho(who)) == null)) {
                return f;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Fragment findActiveFragment(String who) {
        FragmentStateManager fragmentStateManager = this.mActive.get(who);
        if (fragmentStateManager != null) {
            return fragmentStateManager.getFragment();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentUnder(Fragment f) {
        ViewGroup container = f.mContainer;
        View view = f.mView;
        if (container == null || view == null) {
            return null;
        }
        for (int i = this.mAdded.indexOf(f) - 1; i >= 0; i--) {
            Fragment underFragment = this.mAdded.get(i);
            if (underFragment.mContainer == container && underFragment.mView != null) {
                return underFragment;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        String innerPrefix = prefix + "    ";
        if (!this.mActive.isEmpty()) {
            writer.print(prefix);
            writer.print("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                writer.print(prefix);
                if (fragmentStateManager != null) {
                    Fragment f = fragmentStateManager.getFragment();
                    writer.println(f);
                    f.dump(innerPrefix, fd, writer, args);
                } else {
                    writer.println("null");
                }
            }
        }
        int count = this.mAdded.size();
        if (count > 0) {
            writer.print(prefix);
            writer.println("Added Fragments:");
            for (int i = 0; i < count; i++) {
                writer.print(prefix);
                writer.print("  #");
                writer.print(i);
                writer.print(": ");
                writer.println(this.mAdded.get(i).toString());
            }
        }
    }
}
