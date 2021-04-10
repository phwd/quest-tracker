package androidx.appcompat.widget;

import X.AbstractC05360vQ;
import X.AbstractC10751pE;
import X.AbstractC12011tl;
import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass1D0;
import X.AnonymousClass1D4;
import X.AnonymousClass1ER;
import X.AnonymousClass1FF;
import X.AnonymousClass1p4;
import X.AnonymousClass1pD;
import X.AnonymousClass1pF;
import X.C10701p8;
import X.C10711p9;
import X.C10721pA;
import X.C10731pB;
import X.C10901qA;
import X.C10961qL;
import X.C11081qa;
import X.RunnableC10671p5;
import X.RunnableC10741pC;
import X.View$OnClickListenerC10641ov;
import X.View$OnClickListenerC10661p0;
import X.View$OnFocusChangeListenerC10691p7;
import X.View$OnKeyListenerC10651oz;
import X.View$OnLayoutChangeListenerC10681p6;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.customview.view.AbsSavedState;
import com.oculus.socialplatform.R;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements AbstractC12011tl {
    public static final boolean DBG = false;
    public static final String IME_OPTION_NO_MICROPHONE = "nm";
    public static final String LOG_TAG = "SearchView";
    public static final AnonymousClass1ER PRE_API_29_HIDDEN_METHOD_INVOKER;
    public Bundle mAppSearchData;
    public boolean mClearingFocus;
    public final ImageView mCloseButton;
    public final ImageView mCollapsedIcon;
    public int mCollapsedImeOptions;
    public final CharSequence mDefaultQueryHint;
    public final View mDropDownAnchor;
    public boolean mExpandedInActionView;
    public final ImageView mGoButton;
    public boolean mIconified;
    public boolean mIconifiedByDefault;
    public int mMaxWidth;
    public CharSequence mOldQueryText;
    public final View.OnClickListener mOnClickListener;
    public AnonymousClass1pD mOnCloseListener;
    public final TextView.OnEditorActionListener mOnEditorActionListener;
    public final AdapterView.OnItemClickListener mOnItemClickListener;
    public final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    public AbstractC10751pE mOnQueryChangeListener;
    public View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    public View.OnClickListener mOnSearchClickListener;
    public AnonymousClass1pF mOnSuggestionListener;
    public final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    public CharSequence mQueryHint;
    public boolean mQueryRefinement;
    public Runnable mReleaseCursorRunnable;
    public final ImageView mSearchButton;
    public final View mSearchEditFrame;
    public final Drawable mSearchHintIcon;
    public final View mSearchPlate;
    public final SearchAutoComplete mSearchSrcTextView;
    public Rect mSearchSrcTextViewBounds;
    public Rect mSearchSrtTextViewBoundsExpanded;
    public SearchableInfo mSearchable;
    public final View mSubmitArea;
    public boolean mSubmitButtonEnabled;
    public final int mSuggestionCommitIconResId;
    public final int mSuggestionRowLayout;
    public AbstractC05360vQ mSuggestionsAdapter;
    public int[] mTemp;
    public int[] mTemp2;
    public View.OnKeyListener mTextKeyListener;
    public TextWatcher mTextWatcher;
    public AnonymousClass1D4 mTouchDelegate;
    public final Runnable mUpdateDrawableStateRunnable;
    public CharSequence mUserQuery;
    public final Intent mVoiceAppSearchIntent;
    public final ImageView mVoiceButton;
    public boolean mVoiceButtonEnabled;
    public final Intent mVoiceWebSearchIntent;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1D0();
        public boolean A00;

        public final String toString() {
            StringBuilder sb = new StringBuilder("SearchView.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" isIconified=");
            sb.append(this.A00);
            sb.append("}");
            return sb.toString();
        }

        @Override // androidx.customview.view.AbsSavedState
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.A00));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.A00 = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static class SearchAutoComplete extends C10961qL {
        public SearchView A00;
        public boolean A01;
        public int A02;
        public final Runnable A03;

        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.A00.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        public final void performCompletion() {
        }

        public final void replaceText(CharSequence charSequence) {
        }

        public final void A00() {
            if (Build.VERSION.SDK_INT >= 29) {
                setInputMethodMode(1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            AnonymousClass1ER r0 = SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER;
            AnonymousClass1ER.A00();
            Method method = r0.A02;
            if (method != null) {
                try {
                    method.invoke(this, true);
                } catch (Exception unused) {
                }
            }
        }

        public final boolean enoughToFilter() {
            if (this.A02 <= 0 || super.enoughToFilter()) {
                return true;
            }
            return false;
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i >= 600 || (i >= 640 && i2 >= 480)) {
                return 192;
            }
            return 160;
        }

        @Override // X.C10961qL
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.A01) {
                Runnable runnable = this.A03;
                removeCallbacks(runnable);
                post(runnable);
            }
            return onCreateInputConnection;
        }

        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        public final void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.A00.onTextFocusChanged();
        }

        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.A00.hasFocus() && getVisibility() == 0) {
                this.A01 = true;
                if (SearchView.isLandscapeMode(getContext())) {
                    A00();
                }
            }
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.A01 = false;
                removeCallbacks(this.A03);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.A01 = false;
                removeCallbacks(this.A03);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.A01 = true;
            }
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.A02 = i;
        }

        public void setSearchView(SearchView searchView) {
            this.A00 = searchView;
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.A03 = new AnonymousClass1p4(this);
            this.A02 = getThreshold();
        }
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i, String str) {
        int i2;
        Uri uri;
        String A01;
        try {
            String A012 = View$OnClickListenerC10641ov.A01(cursor, cursor.getColumnIndex("suggest_intent_action"));
            if (A012 == null) {
                A012 = this.mSearchable.getSuggestIntentAction();
            }
            if (A012 == null) {
                A012 = "android.intent.action.SEARCH";
            }
            String A013 = View$OnClickListenerC10641ov.A01(cursor, cursor.getColumnIndex("suggest_intent_data"));
            if (A013 == null) {
                A013 = this.mSearchable.getSuggestIntentData();
            }
            if (!(A013 == null || (A01 = View$OnClickListenerC10641ov.A01(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) == null)) {
                A013 = AnonymousClass006.A09(A013, "/", Uri.encode(A01));
            }
            if (A013 == null) {
                uri = null;
            } else {
                uri = Uri.parse(A013);
            }
            return createIntent(A012, uri, View$OnClickListenerC10641ov.A01(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), View$OnClickListenerC10641ov.A01(cursor, cursor.getColumnIndex("suggest_intent_query")), i, str);
        } catch (RuntimeException e) {
            try {
                i2 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i2 = -1;
            }
            Log.w(LOG_TAG, AnonymousClass006.A04("Search suggestions cursor at row ", i2, " returned exception."), e);
            return null;
        }
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    @Override // X.AbstractC12011tl
    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    public boolean onItemClicked(int i, int i2, String str) {
        launchSuggestion(i, 0, null);
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
        return true;
    }

    public void onSearchClicked() {
        updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
        View.OnClickListener onClickListener = this.mOnSearchClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    static {
        AnonymousClass1ER r0;
        if (Build.VERSION.SDK_INT < 29) {
            r0 = new AnonymousClass1ER();
        } else {
            r0 = null;
        }
        PRE_API_29_HIDDEN_METHOD_INVOKER = r0;
    }

    private Intent createIntent(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.mAppSearchData;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.mSearchable.getSearchActivity());
        return intent;
    }

    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        String flattenToShortString;
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        if (searchActivity == null) {
            flattenToShortString = null;
        } else {
            flattenToShortString = searchActivity.flattenToShortString();
        }
        intent2.putExtra("calling_package", flattenToShortString);
        return intent2;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    private void getChildBoundsWithinSearchView(View view, Rect rect) {
        view.getLocationInWindow(this.mTemp);
        getLocationInWindow(this.mTemp2);
        int[] iArr = this.mTemp;
        int i = iArr[1];
        int[] iArr2 = this.mTemp2;
        int i2 = i - iArr2[1];
        int i3 = iArr[0] - iArr2[0];
        rect.set(i3, i2, view.getWidth() + i3, view.getHeight() + i2);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault || this.mSearchHintIcon == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.mSearchSrcTextView.getTextSize()) * 1.25d);
        this.mSearchHintIcon.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private boolean hasVoiceSearch() {
        Intent intent;
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            intent = this.mVoiceWebSearchIntent;
        } else if (!this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            return false;
        } else {
            intent = this.mVoiceAppSearchIntent;
        }
        if (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
            return false;
        }
        return true;
    }

    private boolean isSubmitAreaEnabled() {
        if ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.mIconified) {
            return true;
        }
        return false;
    }

    private void launchIntent(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException e) {
                StringBuilder sb = new StringBuilder("Failed launch activity: ");
                sb.append(intent);
                Log.e(LOG_TAG, sb.toString(), e);
            }
        }
    }

    private boolean launchSuggestion(int i, int i2, String str) {
        Cursor A3f = this.mSuggestionsAdapter.A3f();
        if (A3f == null || !A3f.moveToPosition(i)) {
            return false;
        }
        launchIntent(createIntentFromSuggestion(A3f, i2, str));
        return true;
    }

    private void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i) {
        CharSequence A2I;
        Editable text = this.mSearchSrcTextView.getText();
        Cursor A3f = this.mSuggestionsAdapter.A3f();
        if (A3f == null) {
            return;
        }
        if (!A3f.moveToPosition(i) || (A2I = this.mSuggestionsAdapter.A2I(A3f)) == null) {
            setQuery(text);
        } else {
            setQuery(A2I);
        }
    }

    private void updateCloseButton() {
        int[] iArr;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        int i = 0;
        if (!z2 && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            z = false;
        }
        ImageView imageView = this.mCloseButton;
        if (!z) {
            i = 8;
        }
        imageView.setVisibility(i);
        Drawable drawable = this.mCloseButton.getDrawable();
        if (drawable != null) {
            if (z2) {
                iArr = ENABLED_STATE_SET;
            } else {
                iArr = EMPTY_STATE_SET;
            }
            drawable.setState(iArr);
        }
    }

    private void updateSearchAutoComplete() {
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType = this.mSearchable.getInputType();
        int i = 1;
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.mSearchSrcTextView.setInputType(inputType);
        AbstractC05360vQ r1 = this.mSuggestionsAdapter;
        if (r1 != null) {
            r1.A22(null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            View$OnClickListenerC10641ov r12 = new View$OnClickListenerC10641ov(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSuggestionsAdapter = r12;
            this.mSearchSrcTextView.setAdapter(r12);
            View$OnClickListenerC10641ov r13 = (View$OnClickListenerC10641ov) this.mSuggestionsAdapter;
            if (this.mQueryRefinement) {
                i = 2;
            }
            r13.A02 = i;
        }
    }

    private void updateSubmitButton(boolean z) {
        int i;
        if (!this.mSubmitButtonEnabled || !isSubmitAreaEnabled() || !hasFocus() || (!z && this.mVoiceButtonEnabled)) {
            i = 8;
        } else {
            i = 0;
        }
        this.mGoButton.setVisibility(i);
    }

    private void updateViewsVisibility(boolean z) {
        this.mIconified = z;
        int i = 8;
        boolean z2 = false;
        int i2 = 8;
        if (z) {
            i2 = 0;
        }
        boolean z3 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility(i2);
        updateSubmitButton(z3);
        View view = this.mSearchEditFrame;
        int i3 = 0;
        if (z) {
            i3 = 8;
        }
        view.setVisibility(i3);
        if (this.mCollapsedIcon.getDrawable() != null && !this.mIconifiedByDefault) {
            i = 0;
        }
        this.mCollapsedIcon.setVisibility(i);
        updateCloseButton();
        if (!z3) {
            z2 = true;
        }
        updateVoiceButton(z2);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean z) {
        int i;
        if (!this.mVoiceButtonEnabled || this.mIconified || !z) {
            i = 8;
        } else {
            i = 0;
            this.mGoButton.setVisibility(8);
        }
        this.mVoiceButton.setVisibility(i);
    }

    public void adjustDropDownSizeAndPosition() {
        int i;
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            boolean z = true;
            if (getLayoutDirection() != 1) {
                z = false;
            }
            int i2 = 0;
            if (this.mIconifiedByDefault) {
                i2 = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R.dimen.abc_button_padding_horizontal_material);
            }
            this.mSearchSrcTextView.getDropDownBackground().getPadding(rect);
            int i3 = rect.left;
            if (z) {
                i = -i3;
            } else {
                i = paddingLeft - (i3 + i2);
            }
            this.mSearchSrcTextView.setDropDownHorizontalOffset(i);
            this.mSearchSrcTextView.setDropDownWidth((((this.mDropDownAnchor.getWidth() + rect.left) + rect.right) + i2) - paddingLeft);
        }
    }

    public void forceSuggestionQuery() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.mSearchSrcTextView.refreshAutoCompleteResults();
            return;
        }
        AnonymousClass1ER r3 = PRE_API_29_HIDDEN_METHOD_INVOKER;
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        AnonymousClass1ER.A00();
        Method method = r3.A01;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        SearchAutoComplete searchAutoComplete2 = this.mSearchSrcTextView;
        AnonymousClass1ER.A00();
        Method method2 = r3.A00;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete2, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    @Nullable
    public CharSequence getQueryHint() {
        CharSequence charSequence = this.mQueryHint;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo == null || searchableInfo.getHintId() == 0) {
            return this.mDefaultQueryHint;
        }
        return getContext().getText(this.mSearchable.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    public int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public AbstractC05360vQ getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    public void launchQuerySearch(int i, String str, String str2) {
        getContext().startActivity(createIntent("android.intent.action.SEARCH", null, null, str2, i, str));
    }

    @Override // X.AbstractC12011tl
    public void onActionViewExpanded() {
        if (!this.mExpandedInActionView) {
            this.mExpandedInActionView = true;
            int imeOptions = this.mSearchSrcTextView.getImeOptions();
            this.mCollapsedImeOptions = imeOptions;
            this.mSearchSrcTextView.setImeOptions(imeOptions | 33554432);
            this.mSearchSrcTextView.setText("");
            setIconified(false);
        }
    }

    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            this.mSearchSrcTextView.requestFocus();
            this.mSearchSrcTextView.setImeVisibility(true);
        } else if (this.mIconifiedByDefault) {
            clearFocus();
            updateViewsVisibility(true);
        }
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        if (r0 > 0) goto L_0x001a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            boolean r0 = r5.mIconified
            if (r0 != 0) goto L_0x0036
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r4 = android.view.View.MeasureSpec.getSize(r6)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r3) goto L_0x004c
            if (r0 == 0) goto L_0x0043
            if (r0 != r2) goto L_0x001e
            int r0 = r5.mMaxWidth
            if (r0 <= 0) goto L_0x001e
        L_0x001a:
            int r4 = java.lang.Math.min(r0, r4)
        L_0x001e:
            int r0 = android.view.View.MeasureSpec.getMode(r7)
            int r1 = android.view.View.MeasureSpec.getSize(r7)
            if (r0 == r3) goto L_0x003a
            if (r0 != 0) goto L_0x002e
            int r1 = r5.getPreferredHeight()
        L_0x002e:
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r2)
        L_0x0036:
            super.onMeasure(r6, r7)
            return
        L_0x003a:
            int r0 = r5.getPreferredHeight()
            int r1 = java.lang.Math.min(r0, r1)
            goto L_0x002e
        L_0x0043:
            int r4 = r5.mMaxWidth
            if (r4 > 0) goto L_0x001e
            int r4 = r5.getPreferredWidth()
            goto L_0x001e
        L_0x004c:
            int r0 = r5.mMaxWidth
            if (r0 > 0) goto L_0x001a
            int r0 = r5.getPreferredWidth()
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.onMeasure(int, int):void");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(((AbsSavedState) savedState).A00);
        updateViewsVisibility(savedState.A00);
        requestLayout();
    }

    public void onSubmitQuery() {
        Editable text = this.mSearchSrcTextView.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.mSearchable != null) {
                launchQuerySearch(0, null, text.toString());
            }
            this.mSearchSrcTextView.setImeVisibility(false);
            dismissSuggestions();
        }
    }

    public boolean onSuggestionsKey(View view, int i, KeyEvent keyEvent) {
        int i2;
        if (this.mSearchable != null && this.mSuggestionsAdapter != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i == 66 || i == 84 || i == 61) {
                return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
            }
            if (i == 21) {
                i2 = 0;
            } else if (i == 22) {
                i2 = this.mSearchSrcTextView.length();
            } else if (i == 19) {
                this.mSearchSrcTextView.getListSelection();
            }
            this.mSearchSrcTextView.setSelection(i2);
            this.mSearchSrcTextView.setListSelection(0);
            this.mSearchSrcTextView.clearListSelection();
            this.mSearchSrcTextView.A00();
            return true;
        }
        return false;
    }

    public void onTextChanged(CharSequence charSequence) {
        Editable text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        boolean isEmpty = TextUtils.isEmpty(text);
        boolean z = true;
        boolean z2 = !isEmpty;
        updateSubmitButton(z2);
        if (z2) {
            z = false;
        }
        updateVoiceButton(z);
        updateCloseButton();
        updateSubmitArea();
        this.mOldQueryText = charSequence.toString();
    }

    public void onTextFocusChanged() {
        updateViewsVisibility(this.mIconified);
        postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    public void onVoiceClicked() {
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo != null) {
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    getContext().startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo));
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo));
                }
            } catch (ActivityNotFoundException unused) {
                Log.w(LOG_TAG, "Could not find voice search activity");
            }
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.mClearingFocus || !isFocusable()) {
            return false;
        }
        if (this.mIconified) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.mSearchSrcTextView.requestFocus(i, rect);
        if (requestFocus) {
            updateViewsVisibility(false);
        }
        return requestFocus;
    }

    public void setIconified(boolean z) {
        if (z) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.mIconifiedByDefault != z) {
            this.mIconifiedByDefault = z;
            updateViewsVisibility(z);
            updateQueryHint();
        }
    }

    public void setImeOptions(int i) {
        this.mSearchSrcTextView.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.mSearchSrcTextView.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        requestLayout();
    }

    public void setQueryHint(@Nullable CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.mQueryRefinement = z;
        AbstractC05360vQ r1 = this.mSuggestionsAdapter;
        if (r1 instanceof View$OnClickListenerC10641ov) {
            View$OnClickListenerC10641ov r12 = (View$OnClickListenerC10641ov) r1;
            int i = 1;
            if (z) {
                i = 2;
            }
            r12.A02 = i;
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (searchableInfo != null) {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        boolean hasVoiceSearch = hasVoiceSearch();
        this.mVoiceButtonEnabled = hasVoiceSearch;
        if (hasVoiceSearch) {
            this.mSearchSrcTextView.setPrivateImeOptions(IME_OPTION_NO_MICROPHONE);
        }
        updateViewsVisibility(this.mIconified);
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.mSubmitButtonEnabled = z;
        updateViewsVisibility(this.mIconified);
    }

    public void setSuggestionsAdapter(AbstractC05360vQ r2) {
        this.mSuggestionsAdapter = r2;
        this.mSearchSrcTextView.setAdapter(r2);
    }

    public void updateFocusedState() {
        int[] iArr;
        if (this.mSearchSrcTextView.hasFocus()) {
            iArr = FOCUSED_STATE_SET;
        } else {
            iArr = EMPTY_STATE_SET;
        }
        Drawable background = this.mSearchPlate.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.mSubmitArea.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        String str;
        String str2;
        String str3;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.mAppSearchData;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        int i = 1;
        Resources resources = getResources();
        if (searchableInfo.getVoiceLanguageModeId() != 0) {
            str = resources.getString(searchableInfo.getVoiceLanguageModeId());
        } else {
            str = "free_form";
        }
        String str4 = null;
        if (searchableInfo.getVoicePromptTextId() != 0) {
            str2 = resources.getString(searchableInfo.getVoicePromptTextId());
        } else {
            str2 = null;
        }
        if (searchableInfo.getVoiceLanguageId() != 0) {
            str3 = resources.getString(searchableInfo.getVoiceLanguageId());
        } else {
            str3 = null;
        }
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i = searchableInfo.getVoiceMaxResults();
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str);
        intent3.putExtra("android.speech.extra.PROMPT", str2);
        intent3.putExtra("android.speech.extra.LANGUAGE", str3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i);
        if (searchActivity != null) {
            str4 = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str4);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    public static boolean isLandscapeMode(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private void updateQueryHint() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(getDecoratedHint(queryHint));
    }

    private void updateSubmitArea() {
        int i;
        if (!isSubmitAreaEnabled() || !(this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
            i = 8;
        } else {
            i = 0;
        }
        this.mSubmitArea.setVisibility(i);
    }

    public boolean onItemSelected(int i) {
        rewriteQueryFromSuggestion(i);
        return true;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            Rect rect = this.mSearchSrtTextViewBoundsExpanded;
            Rect rect2 = this.mSearchSrcTextViewBounds;
            rect.set(rect2.left, 0, rect2.right, i4 - i2);
            AnonymousClass1D4 r4 = this.mTouchDelegate;
            Rect rect3 = this.mSearchSrtTextViewBoundsExpanded;
            Rect rect4 = this.mSearchSrcTextViewBounds;
            if (r4 == null) {
                AnonymousClass1D4 r0 = new AnonymousClass1D4(rect3, rect4, this.mSearchSrcTextView);
                this.mTouchDelegate = r0;
                setTouchDelegate(r0);
                return;
            }
            r4.A04.set(rect3);
            Rect rect5 = r4.A03;
            rect5.set(rect3);
            int i5 = -r4.A01;
            rect5.inset(i5, i5);
            r4.A02.set(rect4);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.A00 = this.mIconified;
        return savedState;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        postUpdateFocusedState();
    }

    public void onQueryRefine(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setOnCloseListener(AnonymousClass1pD r1) {
        this.mOnCloseListener = r1;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(AbstractC10751pE r1) {
        this.mOnQueryChangeListener = r1;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setOnSuggestionListener(AnonymousClass1pF r1) {
        this.mOnSuggestionListener = r1;
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSearchSrcTextViewBounds = new Rect();
        this.mSearchSrtTextViewBoundsExpanded = new Rect();
        this.mTemp = new int[2];
        this.mTemp2 = new int[2];
        this.mUpdateDrawableStateRunnable = new RunnableC10741pC(this);
        this.mReleaseCursorRunnable = new RunnableC10671p5(this);
        this.mOutsideDrawablesCache = new WeakHashMap<>();
        this.mOnClickListener = new View$OnClickListenerC10661p0(this);
        this.mTextKeyListener = new View$OnKeyListenerC10651oz(this);
        this.mOnEditorActionListener = new C10731pB(this);
        this.mOnItemClickListener = new C10711p9(this);
        this.mOnItemSelectedListener = new C10701p8(this);
        this.mTextWatcher = new C10721pA(this);
        C10901qA A00 = C10901qA.A00(context, attributeSet, C11081qa.A0K, i, 0);
        LayoutInflater from = LayoutInflater.from(context);
        TypedArray typedArray = A00.A02;
        from.inflate(typedArray.getResourceId(9, R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.mSearchSrcTextView = searchAutoComplete;
        searchAutoComplete.A00 = this;
        this.mSearchEditFrame = findViewById(R.id.search_edit_frame);
        this.mSearchPlate = findViewById(R.id.search_plate);
        this.mSubmitArea = findViewById(R.id.submit_area);
        this.mSearchButton = (ImageView) findViewById(R.id.search_button);
        this.mGoButton = (ImageView) findViewById(R.id.search_go_btn);
        this.mCloseButton = (ImageView) findViewById(R.id.search_close_btn);
        this.mVoiceButton = (ImageView) findViewById(R.id.search_voice_btn);
        this.mCollapsedIcon = (ImageView) findViewById(R.id.search_mag_icon);
        this.mSearchPlate.setBackground(A00.A02(10));
        this.mSubmitArea.setBackground(A00.A02(14));
        this.mSearchButton.setImageDrawable(A00.A02(13));
        this.mGoButton.setImageDrawable(A00.A02(7));
        this.mCloseButton.setImageDrawable(A00.A02(4));
        this.mVoiceButton.setImageDrawable(A00.A02(16));
        this.mCollapsedIcon.setImageDrawable(A00.A02(13));
        this.mSearchHintIcon = A00.A02(12);
        AnonymousClass1FF.A00(this.mSearchButton, getResources().getString(R.string.abc_searchview_description_search));
        this.mSuggestionRowLayout = typedArray.getResourceId(15, R.layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = typedArray.getResourceId(5, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mGoButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
        this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
        this.mSearchSrcTextView.setOnFocusChangeListener(new View$OnFocusChangeListenerC10691p7(this));
        setIconifiedByDefault(typedArray.getBoolean(8, true));
        int dimensionPixelSize = typedArray.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.mDefaultQueryHint = typedArray.getText(6);
        this.mQueryHint = typedArray.getText(11);
        int i2 = typedArray.getInt(3, -1);
        if (i2 != -1) {
            setImeOptions(i2);
        }
        int i3 = typedArray.getInt(2, -1);
        if (i3 != -1) {
            setInputType(i3);
        }
        setFocusable(typedArray.getBoolean(0, true));
        A00.A04();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent = intent;
        intent.addFlags(268435456);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent = intent2;
        intent2.addFlags(268435456);
        View findViewById = findViewById(this.mSearchSrcTextView.getDropDownAnchor());
        this.mDropDownAnchor = findViewById;
        if (findViewById != null) {
            findViewById.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC10681p6(this));
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
    }

    private void setQuery(CharSequence charSequence) {
        this.mSearchSrcTextView.setText(charSequence);
        this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.mUserQuery = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            onSubmitQuery();
        }
    }
}
