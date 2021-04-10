package X;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.databinding.ViewDataBinding$OnStartListener;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Map;

/* renamed from: X.1uW  reason: invalid class name */
public abstract class AnonymousClass1uW extends AnonymousClass1uc implements AnonymousClass1v0 {
    public static final int BINDING_NUMBER_START = 8;
    public static final String BINDING_TAG_PREFIX = "binding_";
    public static final AbstractC12111uw CREATE_LIST_LISTENER = new AnonymousClass1uq();
    public static final AbstractC12111uw CREATE_LIVE_DATA_LISTENER = new AnonymousClass1un();
    public static final AbstractC12111uw CREATE_MAP_LISTENER = new C12061um();
    public static final AbstractC12111uw CREATE_PROPERTY_LISTENER = new C12051ul();
    public static final int HALTED = 2;
    public static final int REBIND = 1;
    public static final AbstractC12071ur<AbstractC12131uy, AnonymousClass1uW, Void> REBIND_NOTIFIER = new C12041uk();
    public static final int REBOUND = 3;
    public static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER = new AnonymousClass1uV();
    public static int SDK_INT = Build.VERSION.SDK_INT;
    public static final boolean USE_CHOREOGRAPHER = true;
    public static final ReferenceQueue<AnonymousClass1uW> sReferenceQueue = new ReferenceQueue<>();
    public final AbstractC003408r mBindingComponent;
    public Choreographer mChoreographer;
    public AnonymousClass1uW mContainingBinding;
    public final Choreographer.FrameCallback mFrameCallback;
    public boolean mInLiveDataRegisterObserver;
    public boolean mIsExecutingPendingBindings;
    public AnonymousClass0AS mLifecycleOwner;
    public AnonymousClass1uY[] mLocalFieldObservers;
    public ViewDataBinding$OnStartListener mOnStartListener;
    public boolean mPendingRebind;
    public AnonymousClass1uX<AbstractC12131uy, AnonymousClass1uW, Void> mRebindCallbacks;
    public boolean mRebindHalted;
    public final Runnable mRebindRunnable;
    public final View mRoot;
    public Handler mUIThreadHandler;

    public abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    public abstract boolean onFieldChange(int i, Object obj, int i2);

    public abstract boolean setVariable(int i, @Nullable Object obj);

    public boolean updateLiveDataRegistration(int i, AnonymousClass0AY<?> r4) {
        this.mInLiveDataRegisterObserver = true;
        try {
            return updateRegistration(i, r4, CREATE_LIVE_DATA_LISTENER);
        } finally {
            this.mInLiveDataRegisterObserver = false;
        }
    }

    public static AbstractC003408r checkAndCastToBindingComponent(Object obj) {
        if (obj == null) {
            return null;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            AnonymousClass1uX<AbstractC12131uy, AnonymousClass1uW, Void> r0 = this.mRebindCallbacks;
            if (r0 != null) {
                r0.A05(this, 1);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.A05(this, 2);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                AnonymousClass1uX<AbstractC12131uy, AnonymousClass1uW, Void> r1 = this.mRebindCallbacks;
                if (r1 != null) {
                    r1.A05(this, 3);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    public static int findIncludeIndex(String str, int i, AnonymousClass1ui r5, int i2) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = r5.A02[i2];
        int length = strArr.length;
        while (i < length) {
            if (TextUtils.equals(subSequence, strArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static AnonymousClass1uW getBinding(View view) {
        if (view != null) {
            return (AnonymousClass1uW) view.getTag(R.id.dataBinding);
        }
        return null;
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    public static <K, T> T getFrom(Map<K, T> map, K k) {
        if (map == null) {
            return null;
        }
        return map.get(k);
    }

    /* access modifiers changed from: private */
    public void handleFieldChange(int i, Object obj, int i2) {
        if (!this.mInLiveDataRegisterObserver && onFieldChange(i, obj, i2)) {
            requestRebind();
        }
    }

    public static void processReferenceQueue() {
        while (true) {
            Reference<? extends AnonymousClass1uW> poll = sReferenceQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof AnonymousClass1uY) {
                ((AnonymousClass1uY) poll).A00();
            }
        }
    }

    public static void setBindingInverseListener(AnonymousClass1uW r1, AbstractC12141uz r2, AbstractC12121ux r3) {
        if (r2 != r3) {
            if (r2 != null) {
                r1.removeOnPropertyChangedCallback(null);
            }
            if (r3 != null) {
                r1.addOnPropertyChangedCallback(r3);
            }
        }
    }

    public void addOnRebindCallback(@NonNull AbstractC12131uy r3) {
        AnonymousClass1uX<AbstractC12131uy, AnonymousClass1uW, Void> r1 = this.mRebindCallbacks;
        if (r1 == null) {
            r1 = new AnonymousClass1uX<>(REBIND_NOTIFIER);
            this.mRebindCallbacks = r1;
        }
        r1.A03(r3);
    }

    public void ensureBindingComponentIsNotNull(Class<?> cls) {
        throw new IllegalStateException(AnonymousClass006.A0C("Required DataBindingComponent is null in class ", getClass().getSimpleName(), ". A BindingAdapter in ", cls.getCanonicalName(), " is not static and requires an object to use, retrieved from the DataBindingComponent. If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static."));
    }

    public void executePendingBindings() {
        AnonymousClass1uW r0 = this.mContainingBinding;
        if (r0 == null) {
            executeBindingsInternal();
        } else {
            r0.executePendingBindings();
        }
    }

    @Nullable
    public AnonymousClass0AS getLifecycleOwner() {
        return this.mLifecycleOwner;
    }

    public Object getObservedField(int i) {
        AnonymousClass1uY r0 = this.mLocalFieldObservers[i];
        if (r0 == null) {
            return null;
        }
        return r0.A00;
    }

    @NonNull
    public View getRoot() {
        return this.mRoot;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public void registerTo(int i, Object obj, AbstractC12111uw r6) {
        if (obj != 0) {
            AnonymousClass1uY r2 = this.mLocalFieldObservers[i];
            if (r2 == null) {
                r2 = r6.A2K(this, i);
                this.mLocalFieldObservers[i] = r2;
                AnonymousClass0AS r1 = this.mLifecycleOwner;
                if (r1 != null) {
                    r2.A01.A9y(r1);
                }
            }
            r2.A00();
            r2.A00 = obj;
            r2.A01.A1I(obj);
        }
    }

    public void removeOnRebindCallback(@NonNull AbstractC12131uy r2) {
        AnonymousClass1uX<AbstractC12131uy, AnonymousClass1uW, Void> r0 = this.mRebindCallbacks;
        if (r0 != null) {
            r0.A04(r2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (X.AnonymousClass1uW.USE_CHOREOGRAPHER == false) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        r2.mChoreographer.postFrameCallback(r2.mFrameCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
        r2.mUIThreadHandler.post(r2.mRebindRunnable);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestRebind() {
        /*
            r2 = this;
            X.1uW r0 = r2.mContainingBinding
            if (r0 == 0) goto L_0x0008
            r0.requestRebind()
            return
        L_0x0008:
            X.0AS r0 = r2.mLifecycleOwner
            if (r0 == 0) goto L_0x001d
            X.0AQ r0 = r0.getLifecycle()
            X.0AP r1 = r0.A05()
            X.0AP r0 = X.AnonymousClass0AP.STARTED
            boolean r0 = r1.isAtLeast(r0)
            if (r0 != 0) goto L_0x001d
            return
        L_0x001d:
            monitor-enter(r2)
            boolean r0 = r2.mPendingRebind     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0024
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            return
        L_0x0024:
            r0 = 1
            r2.mPendingRebind = r0     // Catch:{ all -> 0x003c }
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            boolean r0 = X.AnonymousClass1uW.USE_CHOREOGRAPHER
            if (r0 == 0) goto L_0x0034
            android.view.Choreographer r1 = r2.mChoreographer
            android.view.Choreographer$FrameCallback r0 = r2.mFrameCallback
            r1.postFrameCallback(r0)
            return
        L_0x0034:
            android.os.Handler r1 = r2.mUIThreadHandler
            java.lang.Runnable r0 = r2.mRebindRunnable
            r1.post(r0)
            return
        L_0x003c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1uW.requestRebind():void");
    }

    public void setContainedBinding(AnonymousClass1uW r1) {
        if (r1 != null) {
            r1.mContainingBinding = this;
        }
    }

    @MainThread
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r5) {
        AnonymousClass0AS r0 = this.mLifecycleOwner;
        if (r0 != r5) {
            if (r0 != null) {
                r0.getLifecycle().A07(this.mOnStartListener);
            }
            this.mLifecycleOwner = r5;
            if (r5 != null) {
                if (this.mOnStartListener == null) {
                    this.mOnStartListener = new ViewDataBinding$OnStartListener(this);
                }
                r5.getLifecycle().A06(this.mOnStartListener);
            }
            AnonymousClass1uY[] r3 = this.mLocalFieldObservers;
            for (AnonymousClass1uY r02 : r3) {
                if (r02 != null) {
                    r02.A01.A9y(r5);
                }
            }
        }
    }

    public void unbind() {
        AnonymousClass1uY[] r3 = this.mLocalFieldObservers;
        for (AnonymousClass1uY r0 : r3) {
            if (r0 != null) {
                r0.A00();
            }
        }
    }

    public boolean unregisterFrom(int i) {
        AnonymousClass1uY r0 = this.mLocalFieldObservers[i];
        if (r0 != null) {
            return r0.A00();
        }
        return false;
    }

    public static AnonymousClass1uW bind(Object obj, View view, int i) {
        return AnonymousClass1uU.A00.getDataBinder(checkAndCastToBindingComponent(obj), view, i);
    }

    public static int findLastMatching(ViewGroup viewGroup, int i) {
        String str;
        String str2 = (String) viewGroup.getChildAt(i).getTag();
        int length = str2.length();
        String substring = str2.substring(0, length - 1);
        int length2 = substring.length();
        int childCount = viewGroup.getChildCount();
        for (int i2 = i + 1; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt.getTag() instanceof String) && (str = (String) childAt.getTag()) != null && str.startsWith(substring)) {
                int length3 = str.length();
                if (length3 == length && str.charAt(length3 - 1) == '0') {
                    break;
                } else if (isNumeric(str, length2)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    public static int getColorFromResource(View view, int i) {
        return view.getContext().getColor(i);
    }

    public static ColorStateList getColorStateListFromResource(View view, int i) {
        return view.getContext().getColorStateList(i);
    }

    public static Drawable getDrawableFromResource(View view, int i) {
        return view.getContext().getDrawable(i);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP})
    public static <T extends AnonymousClass1uW> T inflateInternal(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        checkAndCastToBindingComponent(obj);
        return (T) AnonymousClass1uU.A00(layoutInflater, i, viewGroup, z);
    }

    public static boolean isNumeric(String str, int i) {
        int length = str.length();
        if (length != i) {
            while (i < length) {
                if (Character.isDigit(str.charAt(i))) {
                    i++;
                }
            }
            return true;
        }
        return false;
    }

    public static int parseTagInt(String str, int i) {
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            i2 = (i2 * 10) + (str.charAt(i) - '0');
            i++;
        }
        return i2;
    }

    public void forceExecuteBindings() {
        executeBindings();
    }

    public static void executeBindingsOn(AnonymousClass1uW r0) {
        r0.executeBindingsInternal();
    }

    public AnonymousClass1uW(AbstractC003408r r3, View view, int i) {
        this.mRebindRunnable = new AnonymousClass1ub(this);
        this.mPendingRebind = false;
        this.mRebindHalted = false;
        this.mBindingComponent = r3;
        this.mLocalFieldObservers = new AnonymousClass1uY[i];
        this.mRoot = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new AnonymousClass1ue(this);
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    public AnonymousClass1uW(Object obj, View view, int i) {
        this(checkAndCastToBindingComponent(obj), view, i);
    }

    public static byte getFromArray(byte[] bArr, int i) {
        if (bArr == null || i < 0 || i >= bArr.length) {
            return 0;
        }
        return bArr[i];
    }

    public static char getFromArray(char[] cArr, int i) {
        if (cArr == null || i < 0 || i >= cArr.length) {
            return 0;
        }
        return cArr[i];
    }

    public static double getFromArray(double[] dArr, int i) {
        if (dArr == null || i < 0 || i >= dArr.length) {
            return 0.0d;
        }
        return dArr[i];
    }

    public static float getFromArray(float[] fArr, int i) {
        return (fArr == null || i < 0 || i >= fArr.length) ? AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z : fArr[i];
    }

    public static int getFromArray(int[] iArr, int i) {
        if (iArr == null || i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    public static long getFromArray(long[] jArr, int i) {
        if (jArr == null || i < 0 || i >= jArr.length) {
            return 0;
        }
        return jArr[i];
    }

    public static <T> T getFromArray(T[] tArr, int i) {
        if (tArr == null || i < 0 || i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    public static short getFromArray(short[] sArr, int i) {
        if (sArr == null || i < 0 || i >= sArr.length) {
            return 0;
        }
        return sArr[i];
    }

    public static boolean getFromArray(boolean[] zArr, int i) {
        if (zArr == null || i < 0 || i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    public static int getFromList(SparseIntArray sparseIntArray, int i) {
        if (sparseIntArray == null || i < 0) {
            return 0;
        }
        return sparseIntArray.get(i);
    }

    @TargetApi(18)
    public static long getFromList(SparseLongArray sparseLongArray, int i) {
        if (sparseLongArray == null || i < 0) {
            return 0;
        }
        return sparseLongArray.get(i);
    }

    @TargetApi(16)
    public static <T> T getFromList(LongSparseArray<T> longSparseArray, int i) {
        if (longSparseArray == null || i < 0) {
            return null;
        }
        return longSparseArray.get((long) i);
    }

    public static <T> T getFromList(SparseArray<T> sparseArray, int i) {
        if (sparseArray == null || i < 0) {
            return null;
        }
        return sparseArray.get(i);
    }

    public static <T> T getFromList(AnonymousClass02n<T> r3, int i) {
        if (r3 == null || i < 0) {
            return null;
        }
        return r3.A01((long) i, null);
    }

    public static <T> T getFromList(List<T> list, int i) {
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static boolean getFromList(SparseBooleanArray sparseBooleanArray, int i) {
        if (sparseBooleanArray == null || i < 0) {
            return false;
        }
        return sparseBooleanArray.get(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        if (r0 != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00dc, code lost:
        if (r7 != null) goto L_0x00de;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void mapBindings(X.AbstractC003408r r14, android.view.View r15, java.lang.Object[] r16, X.AnonymousClass1ui r17, android.util.SparseIntArray r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 254
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1uW.mapBindings(X.08r, android.view.View, java.lang.Object[], X.1ui, android.util.SparseIntArray, boolean):void");
    }

    public static Object[] mapBindings(AbstractC003408r r3, View view, int i, AnonymousClass1ui r6, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        mapBindings(r3, view, objArr, r6, sparseIntArray, true);
        return objArr;
    }

    public static Object[] mapBindings(AbstractC003408r r8, View[] viewArr, int i, AnonymousClass1ui r11, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        for (View view : viewArr) {
            mapBindings(r8, view, objArr, r11, sparseIntArray, true);
        }
        return objArr;
    }

    public static byte parse(String str, byte b) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static char parse(String str, char c) {
        return (str == null || str.isEmpty()) ? c : str.charAt(0);
    }

    public static double parse(String str, double d) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static float parse(String str, float f) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static int parse(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long parse(String str, long j) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static short parse(String str, short s) {
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    public static boolean parse(String str, boolean z) {
        return str == null ? z : Boolean.parseBoolean(str);
    }

    public static byte safeUnbox(Byte b) {
        if (b == null) {
            return 0;
        }
        return b.byteValue();
    }

    public static char safeUnbox(Character ch) {
        if (ch == null) {
            return 0;
        }
        return ch.charValue();
    }

    public static double safeUnbox(Double d) {
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    public static float safeUnbox(Float f) {
        return f == null ? AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z : f.floatValue();
    }

    public static int safeUnbox(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static long safeUnbox(Long l) {
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public static short safeUnbox(Short sh) {
        if (sh == null) {
            return 0;
        }
        return sh.shortValue();
    }

    public static boolean safeUnbox(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @TargetApi(16)
    public static <T> void setTo(LongSparseArray<T> longSparseArray, int i, T t) {
        if (longSparseArray != null && i >= 0 && i < longSparseArray.size()) {
            longSparseArray.put((long) i, t);
        }
    }

    public static <T> void setTo(SparseArray<T> sparseArray, int i, T t) {
        if (sparseArray != null && i >= 0 && i < sparseArray.size()) {
            sparseArray.put(i, t);
        }
    }

    public static void setTo(SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        if (sparseBooleanArray != null && i >= 0 && i < sparseBooleanArray.size()) {
            sparseBooleanArray.put(i, z);
        }
    }

    public static void setTo(SparseIntArray sparseIntArray, int i, int i2) {
        if (sparseIntArray != null && i >= 0 && i < sparseIntArray.size()) {
            sparseIntArray.put(i, i2);
        }
    }

    @TargetApi(18)
    public static void setTo(SparseLongArray sparseLongArray, int i, long j) {
        if (sparseLongArray != null && i >= 0 && i < sparseLongArray.size()) {
            sparseLongArray.put(i, j);
        }
    }

    public static <T> void setTo(AnonymousClass02n<T> r2, int i, T t) {
        if (r2 != null && i >= 0) {
            if (r2.A01) {
                AnonymousClass02n.A00(r2);
            }
            if (i < r2.A00) {
                r2.A02((long) i, t);
            }
        }
    }

    public static <T> void setTo(List<T> list, int i, T t) {
        if (list != null && i >= 0 && i < list.size()) {
            list.set(i, t);
        }
    }

    public static <K, T> void setTo(Map<K, T> map, K k, T t) {
        if (map != null) {
            map.put(k, t);
        }
    }

    public static void setTo(byte[] bArr, int i, byte b) {
        if (bArr != null && i >= 0 && i < bArr.length) {
            bArr[i] = b;
        }
    }

    public static void setTo(char[] cArr, int i, char c) {
        if (cArr != null && i >= 0 && i < cArr.length) {
            cArr[i] = c;
        }
    }

    public static void setTo(double[] dArr, int i, double d) {
        if (dArr != null && i >= 0 && i < dArr.length) {
            dArr[i] = d;
        }
    }

    public static void setTo(float[] fArr, int i, float f) {
        if (fArr != null && i >= 0 && i < fArr.length) {
            fArr[i] = f;
        }
    }

    public static void setTo(int[] iArr, int i, int i2) {
        if (iArr != null && i >= 0 && i < iArr.length) {
            iArr[i] = i2;
        }
    }

    public static void setTo(long[] jArr, int i, long j) {
        if (jArr != null && i >= 0 && i < jArr.length) {
            jArr[i] = j;
        }
    }

    public static <T> void setTo(T[] tArr, int i, T t) {
        if (tArr != null && i >= 0 && i < tArr.length) {
            tArr[i] = t;
        }
    }

    public static void setTo(short[] sArr, int i, short s) {
        if (sArr != null && i >= 0 && i < sArr.length) {
            sArr[i] = s;
        }
    }

    public static void setTo(boolean[] zArr, int i, boolean z) {
        if (zArr != null && i >= 0 && i < zArr.length) {
            zArr[i] = z;
        }
    }

    private boolean updateRegistration(int i, Object obj, AbstractC12111uw r5) {
        if (obj == null) {
            return unregisterFrom(i);
        }
        AnonymousClass1uY r0 = this.mLocalFieldObservers[i];
        if (r0 != null) {
            if (r0.A00 == obj) {
                return false;
            }
            unregisterFrom(i);
        }
        registerTo(i, obj, r5);
        return true;
    }

    public void setRootTag(View view) {
        view.setTag(R.id.dataBinding, this);
    }

    public void setRootTag(View[] viewArr) {
        for (View view : viewArr) {
            view.setTag(R.id.dataBinding, this);
        }
    }

    public boolean updateRegistration(int i, AbstractC12101uv r3) {
        return updateRegistration(i, r3, CREATE_PROPERTY_LISTENER);
    }

    public boolean updateRegistration(int i, AnonymousClass1up r3) {
        return updateRegistration(i, r3, CREATE_LIST_LISTENER);
    }

    public boolean updateRegistration(int i, AnonymousClass1nR r3) {
        return updateRegistration(i, r3, CREATE_MAP_LISTENER);
    }
}
