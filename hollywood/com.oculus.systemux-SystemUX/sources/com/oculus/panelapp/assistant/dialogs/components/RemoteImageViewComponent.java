package com.oculus.panelapp.assistant.dialogs.components;

import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.common.util.UriUtil;
import com.oculus.assistant.service.api.AssistantResProviderContract;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.panelapp.assistant.R;
import com.oculus.panelapp.assistant.animation.AnimationSetManager;
import com.oculus.panelapp.assistant.animation.DrawableAnimationQueue;
import com.oculus.panelapp.assistant.animation.listeners.AnimEndedListener;
import com.oculus.panelapp.assistant.animation.listeners.AnimStartListener;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteImageViewComponent extends BaseComponent {
    private static final String TAG = "RemoteImageView";
    private AnimationSetManager mAnimationSetManager = new AnimationSetManager();
    private DrawableAnimationQueue mDrawableQueue;
    private ViewPropertyAnimator mFadeAnim;
    private boolean mHasImage = true;
    private ImageView mImageView;

    public RemoteImageViewComponent(@NonNull Context context) {
        super(context);
    }

    public RemoteImageViewComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public RemoteImageViewComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RemoteImageViewComponent, 0, 0);
        setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.RemoteImageViewComponent_src));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public View onCreateView(Context context) {
        this.mImageView = new ImageView(context);
        this.mDrawableQueue = new DrawableAnimationQueue(this.mImageView);
        return this.mImageView;
    }

    public void setImageUri(String str) {
        new DownloadImageTask(this.mImageView).execute(str);
    }

    public void setImageDrawable(Drawable drawable) {
        this.mImageView.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mImageView.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.assistant.dialogs.components.BaseComponent
    public void onApplyJson(JSONObject jSONObject) throws JSONException {
        Bitmap bitmap;
        super.onApplyJson(jSONObject);
        if (jSONObject.has("id")) {
            makeInteractive(jSONObject.getString("id"), jSONObject.optString(AssistantComponentContract.Components.RemoteImageViewComponent.INTERACTIVE_IMAGE_STYLE));
        } else {
            this.mImageView.setOnHoverListener(null);
        }
        Runnable runnable = $$Lambda$RemoteImageViewComponent$wKy5hfSJY0apCVtz99mLlXTXIfE.INSTANCE;
        if (jSONObject.has("path")) {
            String string = jSONObject.getString("path");
            if (jSONObject.optBoolean(AssistantComponentContract.Components.RemoteImageViewComponent.VIDEO_THUMB, false) || string.endsWith("mp4") || string.endsWith("mkv")) {
                bitmap = ThumbnailUtils.createVideoThumbnail(string, jSONObject.optInt(AssistantComponentContract.Components.RemoteImageViewComponent.THUMB_KIND, 1));
            } else if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.IMAGE_THUMB)) {
                Bitmap decodeFile = BitmapFactory.decodeFile(string);
                bitmap = ThumbnailUtils.extractThumbnail(decodeFile, getRequestedWidth(), getRequestedHeight());
                decodeFile.recycle();
            } else {
                bitmap = BitmapFactory.decodeFile(string);
            }
            runnable = createShowImageRunnable(bitmap);
        } else if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.ANIMATIONS)) {
            JSONArray jSONArray = jSONObject.getJSONArray(AssistantComponentContract.Components.RemoteImageViewComponent.ANIMATIONS);
            String optString = jSONObject.optString("package", "");
            if (!jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.TRANSITION)) {
                this.mDrawableQueue = applyAnimations(jSONArray, optString, this.mDrawableQueue);
                runnable = createShowImageRunnable(this.mDrawableQueue, false);
            } else {
                runnable = createShowImageRunnable(applyAnimations(jSONArray, optString, null), true);
            }
        } else if (jSONObject.has("package")) {
            String string2 = jSONObject.getString("package");
            if (jSONObject.has("res")) {
                runnable = createShowImageRunnable(processDrawable(jSONObject, string2, null), true);
            } else if (jSONObject.has("asset")) {
                runnable = createShowImageRunnable(getBitmapFromAsset(getContext(), string2, jSONObject.getString("asset")));
            } else if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.URI)) {
                runnable = createShowImageRunnable(jSONObject.getString(AssistantComponentContract.Components.RemoteImageViewComponent.URI));
            } else if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.RES_PROVIDER)) {
                runnable = createShowImageRunnable(processDrawable("com.oculus.assistant", getResProviderId(getContext().getContentResolver(), jSONObject.getString(AssistantComponentContract.Components.RemoteImageViewComponent.RES_PROVIDER)), jSONObject.optBoolean(AssistantComponentContract.Components.RemoteImageViewComponent.LOOP, true), null), true);
            }
            if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.BACKGROUND_RES)) {
                Drawable drawableFromPackage = getDrawableFromPackage(getContext(), string2, jSONObject.getInt(AssistantComponentContract.Components.RemoteImageViewComponent.BACKGROUND_RES));
                setBackground(drawableFromPackage);
                if (drawableFromPackage instanceof Animatable) {
                    ((Animatable) drawableFromPackage).start();
                }
            } else if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.BACKGROUND_ASSET)) {
                setBackground(new BitmapDrawable(getResources(), getBitmapFromAsset(getContext(), string2, jSONObject.getString(AssistantComponentContract.Components.RemoteImageViewComponent.BACKGROUND_ASSET))));
            }
        }
        this.mHasImage = !jSONObject.optBoolean("none", false);
        if (!this.mHasImage) {
            fadeImage(false);
        }
        processTransition(jSONObject.optString(AssistantComponentContract.Components.RemoteImageViewComponent.TRANSITION, ""), runnable);
        if (jSONObject.has(AssistantComponentContract.Components.RemoteImageViewComponent.BACKGROUND_NONE)) {
            setBackground(null);
        }
    }

    private static int getResProviderId(ContentResolver contentResolver, String str) {
        Cursor query = contentResolver.query(new Uri.Builder().scheme(UriUtil.LOCAL_CONTENT_SCHEME).authority(AssistantResProviderContract.AUTHORITY).path(str).build(), null, null, null, null);
        int i = 0;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    i = query.getInt(0);
                }
            } catch (Throwable th) {
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        if (query != null) {
            query.close();
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processTransition(@androidx.annotation.NonNull java.lang.String r4, @androidx.annotation.NonNull java.lang.Runnable r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 524180605(0x1f3e5c7d, float:4.031057E-20)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 1397903036(0x53524ebc, float:9.0326408E11)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "crossfade"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "fade-out"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            if (r4 == 0) goto L_0x0036
            if (r4 == r2) goto L_0x0032
            r3.fadeImage(r2)
            if (r5 == 0) goto L_0x0039
            r5.run()
            goto L_0x0039
        L_0x0032:
            r3.crossFadeImage(r2, r5)
            goto L_0x0039
        L_0x0036:
            r3.fadeOutImage(r5)
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.dialogs.components.RemoteImageViewComponent.processTransition(java.lang.String, java.lang.Runnable):void");
    }

    private void cleanupActiveQueue() {
        DrawableAnimationQueue drawableAnimationQueue = this.mDrawableQueue;
        if (drawableAnimationQueue != null) {
            drawableAnimationQueue.cleanup();
            this.mDrawableQueue = null;
        }
    }

    private Runnable createShowImageRunnable(Bitmap bitmap) {
        return new Runnable(bitmap) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$WCxV0jiAx3tGh_lFN0iFPfOwvbo */
            private final /* synthetic */ Bitmap f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                RemoteImageViewComponent.this.lambda$createShowImageRunnable$21$RemoteImageViewComponent(this.f$1);
            }
        };
    }

    public /* synthetic */ void lambda$createShowImageRunnable$21$RemoteImageViewComponent(Bitmap bitmap) {
        cleanupActiveQueue();
        this.mImageView.setImageBitmap(bitmap);
    }

    private Runnable createShowImageRunnable(String str) {
        return new Runnable(str) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$qqHGX7nTkCnfzKCrZZc1a7OV2U */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                RemoteImageViewComponent.this.lambda$createShowImageRunnable$22$RemoteImageViewComponent(this.f$1);
            }
        };
    }

    public /* synthetic */ void lambda$createShowImageRunnable$22$RemoteImageViewComponent(String str) {
        cleanupActiveQueue();
        setImageUri(str);
    }

    private Runnable createShowImageRunnable(Drawable drawable) {
        return new Runnable(drawable) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$lBIf0EcdsqguZueQQKJUIIULyo */
            private final /* synthetic */ Drawable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                RemoteImageViewComponent.this.lambda$createShowImageRunnable$23$RemoteImageViewComponent(this.f$1);
            }
        };
    }

    public /* synthetic */ void lambda$createShowImageRunnable$23$RemoteImageViewComponent(Drawable drawable) {
        cleanupActiveQueue();
        this.mImageView.setImageDrawable(drawable);
    }

    private Runnable createShowImageRunnable(DrawableAnimationQueue drawableAnimationQueue, boolean z) {
        return new Runnable(z, drawableAnimationQueue) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$pjatQmnh_0Axo2n_n4UyjepToiY */
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ DrawableAnimationQueue f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                RemoteImageViewComponent.this.lambda$createShowImageRunnable$24$RemoteImageViewComponent(this.f$1, this.f$2);
            }
        };
    }

    public /* synthetic */ void lambda$createShowImageRunnable$24$RemoteImageViewComponent(boolean z, DrawableAnimationQueue drawableAnimationQueue) {
        if (z) {
            cleanupActiveQueue();
        }
        this.mDrawableQueue = drawableAnimationQueue;
        drawableAnimationQueue.start();
    }

    private DrawableAnimationQueue applyAnimations(JSONArray jSONArray, String str, DrawableAnimationQueue drawableAnimationQueue) throws JSONException {
        if (drawableAnimationQueue == null) {
            drawableAnimationQueue = new DrawableAnimationQueue(this.mImageView);
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            processDrawable(jSONArray.getJSONObject(i), str, drawableAnimationQueue);
        }
        return drawableAnimationQueue;
    }

    private DrawableAnimationQueue processDrawable(JSONObject jSONObject, String str, DrawableAnimationQueue drawableAnimationQueue) throws JSONException {
        String optString = jSONObject.optString("package", str);
        return processDrawable(optString, getResource(jSONObject, optString), jSONObject.optBoolean(AssistantComponentContract.Components.RemoteImageViewComponent.LOOP, true), drawableAnimationQueue);
    }

    private DrawableAnimationQueue processDrawable(String str, int i, boolean z, DrawableAnimationQueue drawableAnimationQueue) {
        if (drawableAnimationQueue == null) {
            drawableAnimationQueue = new DrawableAnimationQueue(this.mImageView);
        }
        Drawable drawableFromPackage = getDrawableFromPackage(getContext(), str, i);
        if (drawableFromPackage == null) {
            Log.e(TAG, "Could not find resource: " + str + "/" + i);
        } else {
            drawableAnimationQueue.addDrawable(drawableFromPackage, z);
        }
        return drawableAnimationQueue;
    }

    public void makeInteractive(String str) {
        makeInteractive(str, AssistantComponentContract.Components.RemoteImageViewComponent.ImageStyle.ROUND);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void makeInteractive(String str, String str2) {
        char c;
        this.mImageView.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$ihiXdIoTPlK78dfDOZIydRQ2YY */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return RemoteImageViewComponent.this.handleHover(view, motionEvent);
            }
        });
        switch (str2.hashCode()) {
            case -817598092:
                if (str2.equals("secondary")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -314765822:
                if (str2.equals("primary")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3387192:
                if (str2.equals("none")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1825644485:
                if (str2.equals("borderless")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            setBackgroundResource(R.drawable.ocbutton_primary);
        } else if (c == 1) {
            setBackgroundResource(R.drawable.ocbutton_secondary);
        } else if (c == 2) {
            setBackgroundResource(R.drawable.ocbutton_borderless);
        } else if (c != 3) {
            setBackgroundResource(R.drawable.ocbutton_borderless_circle);
        } else {
            setBackground(null);
        }
        this.mImageView.setOnClickListener(new View.OnClickListener(str) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$mJvtO5Vbib2eu9hx_7DGxTQKkY */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                RemoteImageViewComponent.this.lambda$makeInteractive$25$RemoteImageViewComponent(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$makeInteractive$25$RemoteImageViewComponent(String str, View view) {
        onInteractableClicked();
        sendActionClick(str);
    }

    /* access modifiers changed from: private */
    public boolean handleHover(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 9) {
            onInteractableHovered();
            setHovered(true);
        }
        if (motionEvent != null && motionEvent.getAction() == 10) {
            setHovered(false);
        }
        return false;
    }

    public boolean hasImage() {
        return this.mHasImage;
    }

    private void fadeImage(boolean z) {
        ObjectAnimator enqueueContinueFade = this.mAnimationSetManager.enqueueContinueFade(this.mImageView, z);
        if (!z) {
            enqueueContinueFade.addListener(new AnimEndedListener(new Runnable() {
                /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$PXOgG78sBfKWMfWM5S0CjgZiVE */

                public final void run() {
                    RemoteImageViewComponent.this.lambda$fadeImage$26$RemoteImageViewComponent();
                }
            }));
        }
        this.mAnimationSetManager.playPendingAnimations("RemoteImageView Fade Image");
    }

    public /* synthetic */ void lambda$fadeImage$26$RemoteImageViewComponent() {
        cleanupActiveQueue();
        this.mImageView.setImageDrawable(null);
    }

    private void crossFadeImage(boolean z, @NonNull Runnable runnable) {
        if (z) {
            this.mAnimationSetManager.enqueueContinueFade(this.mImageView, false);
        }
        this.mAnimationSetManager.enqueueContinueFade(this.mImageView, z).addListener(new AnimStartListener(runnable));
        this.mAnimationSetManager.playPendingAnimations("RemoteImageView Crossfade");
    }

    private void fadeOutImage(@NonNull Runnable runnable) {
        this.mAnimationSetManager.enqueueContinueFade(this.mImageView, false).addListener(new AnimEndedListener(new Runnable(runnable) {
            /* class com.oculus.panelapp.assistant.dialogs.components.$$Lambda$RemoteImageViewComponent$uiXJZFfhOvdd3gpbxOUXebjRCMc */
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                RemoteImageViewComponent.this.lambda$fadeOutImage$27$RemoteImageViewComponent(this.f$1);
            }
        }));
        this.mAnimationSetManager.playPendingAnimations("RemoteImageView Fade Out");
    }

    public /* synthetic */ void lambda$fadeOutImage$27$RemoteImageViewComponent(@NonNull Runnable runnable) {
        runnable.run();
        this.mImageView.setAlpha(1.0f);
    }

    public static Resources getResources(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getResourcesForApplication(str);
    }

    public static int getIconResourceByName(Resources resources, String str, String str2) {
        return resources.getIdentifier(str2, "drawable", str);
    }

    private int getResource(JSONObject jSONObject, String str) throws JSONException {
        String optString = jSONObject.optString("res", null);
        if (TextUtils.isDigitsOnly(optString)) {
            return Integer.valueOf(optString).intValue();
        }
        if (!TextUtils.isEmpty(optString)) {
            try {
                return getIconResourceByName(getResources(getContext(), str), str, optString);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(TAG, "Could not get resource. Package name was not found");
            }
        }
        return 0;
    }

    public static Drawable getDrawableFromPackage(Context context, String str, int i) {
        try {
            return getResources(context, str).getDrawable(i, context.getTheme());
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not load image, package name not found: " + str + " " + e.getMessage());
            return null;
        } catch (Resources.NotFoundException unused) {
            Log.e(TAG, "Could not load image, resource not found: " + str + ".drawable." + i);
            return null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:3:0x0011 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:20:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x0005 */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: android.content.Context */
    /* JADX DEBUG: Multi-variable search result rejected for r6v7, resolved type: android.content.Context */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.InputStream] */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        if (r6 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (r6 != 0) goto L_0x0017;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0021 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055 A[Catch:{ NameNotFoundException -> 0x0056, FileNotFoundException -> 0x003e, IOException -> 0x0020, all -> 0x001d, all -> 0x0082 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0085 A[SYNTHETIC, Splitter:B:31:0x0085] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getBitmapFromAsset(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.dialogs.components.RemoteImageViewComponent.getBitmapFromAsset(android.content.Context, java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    public void setImageBackground(Drawable drawable) {
        this.mImageView.setBackground(drawable);
    }

    public void setImageBackgroundColor(int i) {
        this.mImageView.setBackgroundColor(i);
    }

    public void setImageBackgroundResource(int i) {
        this.mImageView.setBackgroundResource(i);
    }

    public void setImageResource(@DrawableRes int i) {
        this.mImageView.setImageResource(i);
    }

    public void setImageResource(String str, String str2) throws PackageManager.NameNotFoundException {
        int iconResourceByName = getIconResourceByName(getResources(getContext(), str), str, str2);
        if (iconResourceByName != 0) {
            Drawable drawableFromPackage = getDrawableFromPackage(getContext(), str, iconResourceByName);
            DrawableAnimationQueue drawableAnimationQueue = new DrawableAnimationQueue(this.mImageView);
            drawableAnimationQueue.addDrawable(drawableFromPackage, true);
            cleanupActiveQueue();
            drawableAnimationQueue.start();
            this.mDrawableQueue = drawableAnimationQueue;
        }
    }

    public void setImageResource(String str, int i) {
        if (i != 0) {
            Drawable drawableFromPackage = getDrawableFromPackage(getContext(), str, i);
            DrawableAnimationQueue drawableAnimationQueue = new DrawableAnimationQueue(this.mImageView);
            drawableAnimationQueue.addDrawable(drawableFromPackage, true);
            cleanupActiveQueue();
            drawableAnimationQueue.start();
            this.mDrawableQueue = drawableAnimationQueue;
            return;
        }
        setImageResource(i);
    }

    public void setImageResourceFromProvider(String str) {
        setImageResource("com.oculus.assistant", getResProviderId(getContext().getContentResolver(), str));
    }

    /* access modifiers changed from: private */
    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private static final String TAG = "DownloadImageTask";
        private final WeakReference<ImageView> mImageViewReference;

        public DownloadImageTask(ImageView imageView) {
            this.mImageViewReference = new WeakReference<>(imageView);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... strArr) {
            String str = strArr[0];
            try {
                return BitmapFactory.decodeStream(new URL(str).openStream());
            } catch (FileNotFoundException unused) {
                Log.e(TAG, "Could not find requested image: " + str);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            ImageView imageView = this.mImageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
