package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import dalvik.system.VMDebug;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    static {
        try {
            sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            Log.i(TAG, "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException e2) {
            Log.i(TAG, "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException e3) {
            Log.i(TAG, "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ListPopupWindow, defStyleAttr, defStyleRes);
        this.mDropDownHorizontalOffset = a.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = a.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        a.recycle();
        this.mPopup = new AppCompatPopupWindow(context, attrs, defStyleAttr, defStyleRes);
        this.mPopup.setInputMethodMode(1);
    }

    public void setAdapter(@Nullable ListAdapter adapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setPromptPosition(int position) {
        this.mPromptPosition = position;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public void setModal(boolean modal) {
        this.mModal = modal;
        this.mPopup.setFocusable(modal);
    }

    public boolean isModal() {
        return this.mModal;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setForceIgnoreOutsideTouch(boolean forceIgnoreOutsideTouch) {
        this.mForceIgnoreOutsideTouch = forceIgnoreOutsideTouch;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDropDownAlwaysVisible(boolean dropDownAlwaysVisible) {
        this.mDropDownAlwaysVisible = dropDownAlwaysVisible;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public void setSoftInputMode(int mode) {
        this.mPopup.setSoftInputMode(mode);
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public void setListSelector(Drawable selector) {
        this.mDropDownListHighlight = selector;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public void setBackgroundDrawable(@Nullable Drawable d) {
        this.mPopup.setBackgroundDrawable(d);
    }

    public void setAnimationStyle(@StyleRes int animationStyle) {
        this.mPopup.setAnimationStyle(animationStyle);
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public void setAnchorView(@Nullable View anchor) {
        this.mDropDownAnchorView = anchor;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public void setHorizontalOffset(int offset) {
        this.mDropDownHorizontalOffset = offset;
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public void setVerticalOffset(int offset) {
        this.mDropDownVerticalOffset = offset;
        this.mDropDownVerticalOffsetSet = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEpicenterBounds(Rect bounds) {
        this.mEpicenterBounds = bounds;
    }

    public void setDropDownGravity(int gravity) {
        this.mDropDownGravity = gravity;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public void setWidth(int width) {
        this.mDropDownWidth = width;
    }

    public void setContentWidth(int width) {
        Drawable popupBackground = this.mPopup.getBackground();
        if (popupBackground != null) {
            popupBackground.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + width;
            return;
        }
        setWidth(width);
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public void setHeight(int height) {
        if (height >= 0 || -2 == height || -1 == height) {
            this.mDropDownHeight = height;
            return;
        }
        throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
    }

    public void setWindowLayoutType(int layoutType) {
        this.mDropDownWindowLayoutType = layoutType;
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener clickListener) {
        this.mItemClickListener = clickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener selectedListener) {
        this.mItemSelectedListener = selectedListener;
    }

    public void setPromptView(@Nullable View prompt) {
        boolean showing = isShowing();
        if (showing) {
            removePromptView();
        }
        this.mPromptView = prompt;
        if (showing) {
            show();
        }
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public void show() {
        int widthSpec;
        int heightSpec;
        int widthSpec2;
        int heightSpec2;
        int height = buildDropDown();
        boolean noInputMethod = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        boolean z = true;
        if (!this.mPopup.isShowing()) {
            int widthSpec3 = this.mDropDownWidth;
            if (widthSpec3 == -1) {
                widthSpec = -1;
            } else if (widthSpec3 == -2) {
                widthSpec = getAnchorView().getWidth();
            } else {
                widthSpec = this.mDropDownWidth;
            }
            int i = this.mDropDownHeight;
            if (i == -1) {
                heightSpec = -1;
            } else if (i == -2) {
                heightSpec = height;
            } else {
                heightSpec = this.mDropDownHeight;
            }
            this.mPopup.setWidth(widthSpec);
            this.mPopup.setHeight(heightSpec);
            setPopupClipToScreenEnabled(true);
            this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
            this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
            if (this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
            }
            Method method = sSetEpicenterBoundsMethod;
            if (method != null) {
                try {
                    method.invoke(this.mPopup, this.mEpicenterBounds);
                } catch (Exception e) {
                    Log.e(TAG, "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
            PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.mModal) {
                this.mHandler.post(this.mHideSelector);
            }
        } else if (ViewCompat.isAttachedToWindow(getAnchorView())) {
            int widthSpec4 = this.mDropDownWidth;
            if (widthSpec4 == -1) {
                widthSpec2 = -1;
            } else if (widthSpec4 == -2) {
                widthSpec2 = getAnchorView().getWidth();
            } else {
                widthSpec2 = this.mDropDownWidth;
            }
            int i2 = this.mDropDownHeight;
            if (i2 == -1) {
                heightSpec2 = noInputMethod ? height : -1;
                if (noInputMethod) {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(0);
                } else {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(-1);
                }
            } else {
                heightSpec2 = i2 == -2 ? height : this.mDropDownHeight;
            }
            PopupWindow popupWindow = this.mPopup;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                z = false;
            }
            popupWindow.setOutsideTouchable(z);
            this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, widthSpec2 < 0 ? -1 : widthSpec2, heightSpec2 < 0 ? -1 : heightSpec2);
        }
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener listener) {
        this.mPopup.setOnDismissListener(listener);
    }

    private void removePromptView() {
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    public void setInputMethodMode(int mode) {
        this.mPopup.setInputMethodMode(mode);
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public void setSelection(int position) {
        DropDownListView list = this.mDropDownList;
        if (isShowing() && list != null) {
            list.setListSelectionHidden(false);
            list.setSelection(position);
            if (list.getChoiceMode() != 0) {
                list.setItemChecked(position, true);
            }
        }
    }

    public void clearListSelection() {
        DropDownListView list = this.mDropDownList;
        if (list != null) {
            list.setListSelectionHidden(true);
            list.requestLayout();
        }
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean performItemClick(int position) {
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener == null) {
            return true;
        }
        DropDownListView list = this.mDropDownList;
        this.mItemClickListener.onItemClick(list, list.getChildAt(position - list.getFirstVisiblePosition()), position, list.getAdapter().getItemId(position));
        return true;
    }

    @Nullable
    public Object getSelectedItem() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }

    public int getSelectedItemPosition() {
        if (!isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }

    public long getSelectedItemId() {
        if (!isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }

    @Nullable
    public View getSelectedView() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    @Nullable
    public ListView getListView() {
        return this.mDropDownList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DropDownListView createDropDownListView(Context context, boolean hijackFocus) {
        return new DropDownListView(context, hijackFocus);
    }

    /* access modifiers changed from: package-private */
    public void setListItemExpandMax(int max) {
        this.mListItemExpandMaximum = max;
    }

    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        int i;
        int i2;
        if (isShowing() && keyCode != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(keyCode))) {
            int curIndex = this.mDropDownList.getSelectedItemPosition();
            boolean below = !this.mPopup.isAboveAnchor();
            ListAdapter adapter = this.mAdapter;
            int firstItem = Integer.MAX_VALUE;
            int lastItem = Integer.MIN_VALUE;
            if (adapter != null) {
                boolean allEnabled = adapter.areAllItemsEnabled();
                if (allEnabled) {
                    i = 0;
                } else {
                    i = this.mDropDownList.lookForSelectablePosition(0, true);
                }
                firstItem = i;
                if (allEnabled) {
                    i2 = adapter.getCount() - 1;
                } else {
                    i2 = this.mDropDownList.lookForSelectablePosition(adapter.getCount() - 1, false);
                }
                lastItem = i2;
            }
            if ((!below || keyCode != 19 || curIndex > firstItem) && (below || keyCode != 20 || curIndex < lastItem)) {
                this.mDropDownList.setListSelectionHidden(false);
                if (this.mDropDownList.onKeyDown(keyCode, event)) {
                    this.mPopup.setInputMethodMode(2);
                    this.mDropDownList.requestFocusFromTouch();
                    show();
                    if (keyCode == 19 || keyCode == 20 || keyCode == 23 || keyCode == 66) {
                        return true;
                    }
                } else if (!below || keyCode != 20) {
                    if (!below && keyCode == 19 && curIndex == firstItem) {
                        return true;
                    }
                    return false;
                } else if (curIndex == lastItem) {
                    return true;
                }
            } else {
                clearListSelection();
                this.mPopup.setInputMethodMode(1);
                show();
                return true;
            }
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean consumed = this.mDropDownList.onKeyUp(keyCode, event);
        if (consumed && isConfirmKey(keyCode)) {
            dismiss();
        }
        return consumed;
    }

    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (keyCode != 4 || !isShowing()) {
            return false;
        }
        View anchorView = this.mDropDownAnchorView;
        if (event.getAction() == 0 && event.getRepeatCount() == 0) {
            KeyEvent.DispatcherState state = anchorView.getKeyDispatcherState();
            if (state != null) {
                state.startTracking(event, this);
            }
            return true;
        } else if (event.getAction() != 1) {
            return false;
        } else {
            KeyEvent.DispatcherState state2 = anchorView.getKeyDispatcherState();
            if (state2 != null) {
                state2.handleUpEvent(event);
            }
            if (!event.isTracking() || event.isCanceled()) {
                return false;
            }
            dismiss();
            return true;
        }
    }

    public View.OnTouchListener createDragToOpenListener(View src) {
        return new ForwardingListener(src) {
            /* class android.support.v7.widget.ListPopupWindow.AnonymousClass1 */

            @Override // android.support.v7.widget.ForwardingListener
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v24, resolved type: android.widget.LinearLayout */
    /* JADX WARN: Multi-variable type inference failed */
    private int buildDropDown() {
        int padding;
        int childWidthSpec;
        int widthSize;
        int widthMode;
        int otherHeights = 0;
        boolean ignoreBottomDecorations = false;
        if (this.mDropDownList == null) {
            Context context = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                /* class android.support.v7.widget.ListPopupWindow.AnonymousClass2 */

                @Override // java.lang.Runnable
                public void run() {
                    View view = ListPopupWindow.this.getAnchorView();
                    if (view != null && view.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            this.mDropDownList = createDropDownListView(context, !this.mModal);
            Drawable drawable = this.mDropDownListHighlight;
            if (drawable != null) {
                this.mDropDownList.setSelector(drawable);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                /* class android.support.v7.widget.ListPopupWindow.AnonymousClass3 */

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    DropDownListView dropDownList;
                    if (position != -1 && (dropDownList = ListPopupWindow.this.mDropDownList) != null) {
                        dropDownList.setListSelectionHidden(false);
                    }
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener);
            }
            ViewGroup dropDownView = this.mDropDownList;
            View hintView = this.mPromptView;
            if (hintView != null) {
                LinearLayout hintContainer = new LinearLayout(context);
                hintContainer.setOrientation(1);
                LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i = this.mPromptPosition;
                if (i == 0) {
                    hintContainer.addView(hintView);
                    hintContainer.addView(dropDownView, hintParams);
                } else if (i != 1) {
                    Log.e(TAG, "Invalid hint position " + this.mPromptPosition);
                } else {
                    hintContainer.addView(dropDownView, hintParams);
                    hintContainer.addView(hintView);
                }
                if (this.mDropDownWidth >= 0) {
                    widthMode = Integer.MIN_VALUE;
                    widthSize = this.mDropDownWidth;
                } else {
                    widthMode = 0;
                    widthSize = 0;
                }
                hintView.measure(View.MeasureSpec.makeMeasureSpec(widthSize, widthMode), 0);
                LinearLayout.LayoutParams hintParams2 = (LinearLayout.LayoutParams) hintView.getLayoutParams();
                dropDownView = hintContainer;
                otherHeights = hintView.getMeasuredHeight() + hintParams2.topMargin + hintParams2.bottomMargin;
            }
            this.mPopup.setContentView(dropDownView);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mPopup.getContentView();
            View view = this.mPromptView;
            if (view != null) {
                LinearLayout.LayoutParams hintParams3 = (LinearLayout.LayoutParams) view.getLayoutParams();
                otherHeights = view.getMeasuredHeight() + hintParams3.topMargin + hintParams3.bottomMargin;
            }
        }
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            padding = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        } else {
            this.mTempRect.setEmpty();
            padding = 0;
        }
        if (this.mPopup.getInputMethodMode() == 2) {
            ignoreBottomDecorations = true;
        }
        int maxHeight = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset, ignoreBottomDecorations);
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            return maxHeight + padding;
        }
        int i2 = this.mDropDownWidth;
        if (i2 == -2) {
            childWidthSpec = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
        } else if (i2 != -1) {
            childWidthSpec = View.MeasureSpec.makeMeasureSpec(i2, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS);
        } else {
            childWidthSpec = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), VMDebug.KIND_THREAD_EXT_FREED_OBJECTS);
        }
        int listContent = this.mDropDownList.measureHeightOfChildrenCompat(childWidthSpec, 0, -1, maxHeight - otherHeights, -1);
        if (listContent > 0) {
            otherHeights += padding + this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom();
        }
        return listContent + otherHeights;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setOverlapAnchor(boolean overlapAnchor) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = overlapAnchor;
    }

    /* access modifiers changed from: private */
    public class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    /* access modifiers changed from: private */
    public class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.mDropDownList) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    /* access modifiers changed from: private */
    public class PopupTouchInterceptor implements View.OnTouchListener {
        PopupTouchInterceptor() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public class PopupScrollListener implements AbsListView.OnScrollListener {
        PopupScrollListener() {
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }

    private static boolean isConfirmKey(int keyCode) {
        return keyCode == 66 || keyCode == 23;
    }

    private void setPopupClipToScreenEnabled(boolean clip) {
        Method method = sClipToWindowEnabledMethod;
        if (method != null) {
            try {
                method.invoke(this.mPopup, Boolean.valueOf(clip));
            } catch (Exception e) {
                Log.i(TAG, "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int getMaxAvailableHeight(View anchor, int yOffset, boolean ignoreBottomDecorations) {
        Method method = sGetMaxAvailableHeightMethod;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.mPopup, anchor, Integer.valueOf(yOffset), Boolean.valueOf(ignoreBottomDecorations))).intValue();
            } catch (Exception e) {
                Log.i(TAG, "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.mPopup.getMaxAvailableHeight(anchor, yOffset);
    }
}
