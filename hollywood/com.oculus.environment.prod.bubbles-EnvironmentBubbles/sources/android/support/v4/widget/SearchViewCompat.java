package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SearchViewCompatHoneycomb;
import android.view.View;

public final class SearchViewCompat {
    private static final SearchViewCompatImpl IMPL;

    public interface OnCloseListener {
        boolean onClose();
    }

    @Deprecated
    public static abstract class OnCloseListenerCompat implements OnCloseListener {
        @Override // android.support.v4.widget.SearchViewCompat.OnCloseListener
        public boolean onClose() {
            return false;
        }
    }

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    @Deprecated
    public static abstract class OnQueryTextListenerCompat implements OnQueryTextListener {
        @Override // android.support.v4.widget.SearchViewCompat.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            return false;
        }

        @Override // android.support.v4.widget.SearchViewCompat.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            return false;
        }
    }

    interface SearchViewCompatImpl {
        CharSequence getQuery(View view);

        boolean isIconified(View view);

        boolean isQueryRefinementEnabled(View view);

        boolean isSubmitButtonEnabled(View view);

        Object newOnCloseListener(OnCloseListener onCloseListener);

        Object newOnQueryTextListener(OnQueryTextListener onQueryTextListener);

        View newSearchView(Context context);

        void setIconified(View view, boolean z);

        void setImeOptions(View view, int i);

        void setInputType(View view, int i);

        void setMaxWidth(View view, int i);

        void setOnCloseListener(View view, OnCloseListener onCloseListener);

        void setOnQueryTextListener(View view, OnQueryTextListener onQueryTextListener);

        void setQuery(View view, CharSequence charSequence, boolean z);

        void setQueryHint(View view, CharSequence charSequence);

        void setQueryRefinementEnabled(View view, boolean z);

        void setSearchableInfo(View view, ComponentName componentName);

        void setSubmitButtonEnabled(View view, boolean z);
    }

    static class SearchViewCompatStubImpl implements SearchViewCompatImpl {
        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public CharSequence getQuery(View view) {
            return null;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public boolean isIconified(View view) {
            return true;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public boolean isQueryRefinementEnabled(View view) {
            return false;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public boolean isSubmitButtonEnabled(View view) {
            return false;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public Object newOnCloseListener(OnCloseListener onCloseListener) {
            return null;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public Object newOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
            return null;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public View newSearchView(Context context) {
            return null;
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setIconified(View view, boolean z) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setImeOptions(View view, int i) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setInputType(View view, int i) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setMaxWidth(View view, int i) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setOnCloseListener(View view, OnCloseListener onCloseListener) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setOnQueryTextListener(View view, OnQueryTextListener onQueryTextListener) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setQuery(View view, CharSequence charSequence, boolean z) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setQueryHint(View view, CharSequence charSequence) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setQueryRefinementEnabled(View view, boolean z) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setSearchableInfo(View view, ComponentName componentName) {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl
        public void setSubmitButtonEnabled(View view, boolean z) {
        }

        SearchViewCompatStubImpl() {
        }
    }

    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl {
        SearchViewCompatHoneycombImpl() {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public View newSearchView(Context context) {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setSearchableInfo(View view, ComponentName componentName) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSearchableInfo(view, componentName);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public Object newOnQueryTextListener(final OnQueryTextListener onQueryTextListener) {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(new SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge() {
                /* class android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl.AnonymousClass1 */

                @Override // android.support.v4.widget.SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge
                public boolean onQueryTextSubmit(String str) {
                    return onQueryTextListener.onQueryTextSubmit(str);
                }

                @Override // android.support.v4.widget.SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge
                public boolean onQueryTextChange(String str) {
                    return onQueryTextListener.onQueryTextChange(str);
                }
            });
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setOnQueryTextListener(View view, OnQueryTextListener onQueryTextListener) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnQueryTextListener(view, newOnQueryTextListener(onQueryTextListener));
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public Object newOnCloseListener(final OnCloseListener onCloseListener) {
            return SearchViewCompatHoneycomb.newOnCloseListener(new SearchViewCompatHoneycomb.OnCloseListenerCompatBridge() {
                /* class android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl.AnonymousClass2 */

                @Override // android.support.v4.widget.SearchViewCompatHoneycomb.OnCloseListenerCompatBridge
                public boolean onClose() {
                    return onCloseListener.onClose();
                }
            });
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setOnCloseListener(View view, OnCloseListener onCloseListener) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnCloseListener(view, newOnCloseListener(onCloseListener));
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public CharSequence getQuery(View view) {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.getQuery(view);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setQuery(View view, CharSequence charSequence, boolean z) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQuery(view, charSequence, z);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setQueryHint(View view, CharSequence charSequence) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryHint(view, charSequence);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setIconified(View view, boolean z) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setIconified(view, z);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public boolean isIconified(View view) {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isIconified(view);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setSubmitButtonEnabled(View view, boolean z) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, z);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public boolean isSubmitButtonEnabled(View view) {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setQueryRefinementEnabled(View view, boolean z) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, z);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public boolean isQueryRefinementEnabled(View view) {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setMaxWidth(View view, int i) {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setMaxWidth(view, i);
        }

        /* access modifiers changed from: protected */
        public void checkIfLegalArg(View view) {
            SearchViewCompatHoneycomb.checkIfLegalArg(view);
        }
    }

    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl {
        SearchViewCompatIcsImpl() {
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl
        public View newSearchView(Context context) {
            return SearchViewCompatIcs.newSearchView(context);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setImeOptions(View view, int i) {
            checkIfLegalArg(view);
            SearchViewCompatIcs.setImeOptions(view, i);
        }

        @Override // android.support.v4.widget.SearchViewCompat.SearchViewCompatImpl, android.support.v4.widget.SearchViewCompat.SearchViewCompatStubImpl
        public void setInputType(View view, int i) {
            checkIfLegalArg(view);
            SearchViewCompatIcs.setInputType(view, i);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            IMPL = new SearchViewCompatIcsImpl();
        } else if (Build.VERSION.SDK_INT >= 11) {
            IMPL = new SearchViewCompatHoneycombImpl();
        } else {
            IMPL = new SearchViewCompatStubImpl();
        }
    }

    private SearchViewCompat(Context context) {
    }

    public static View newSearchView(Context context) {
        return IMPL.newSearchView(context);
    }

    public static void setSearchableInfo(View view, ComponentName componentName) {
        IMPL.setSearchableInfo(view, componentName);
    }

    public static void setImeOptions(View view, int i) {
        IMPL.setImeOptions(view, i);
    }

    public static void setInputType(View view, int i) {
        IMPL.setInputType(view, i);
    }

    public static void setOnQueryTextListener(View view, OnQueryTextListener onQueryTextListener) {
        IMPL.setOnQueryTextListener(view, onQueryTextListener);
    }

    public static void setOnCloseListener(View view, OnCloseListener onCloseListener) {
        IMPL.setOnCloseListener(view, onCloseListener);
    }

    public static CharSequence getQuery(View view) {
        return IMPL.getQuery(view);
    }

    public static void setQuery(View view, CharSequence charSequence, boolean z) {
        IMPL.setQuery(view, charSequence, z);
    }

    public static void setQueryHint(View view, CharSequence charSequence) {
        IMPL.setQueryHint(view, charSequence);
    }

    public static void setIconified(View view, boolean z) {
        IMPL.setIconified(view, z);
    }

    public static boolean isIconified(View view) {
        return IMPL.isIconified(view);
    }

    public static void setSubmitButtonEnabled(View view, boolean z) {
        IMPL.setSubmitButtonEnabled(view, z);
    }

    public static boolean isSubmitButtonEnabled(View view) {
        return IMPL.isSubmitButtonEnabled(view);
    }

    public static void setQueryRefinementEnabled(View view, boolean z) {
        IMPL.setQueryRefinementEnabled(view, z);
    }

    public static boolean isQueryRefinementEnabled(View view) {
        return IMPL.isQueryRefinementEnabled(view);
    }

    public static void setMaxWidth(View view, int i) {
        IMPL.setMaxWidth(view, i);
    }
}