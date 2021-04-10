package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherAppTileV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.AdapterUpdatesCoalescer;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import java.util.ArrayList;
import java.util.List;

public class ManagedLauncherContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = LoggingUtil.tag(ManagedLauncherContentAdapter.class);
    public static final int VIEW_TYPE_APP = 0;
    public static final int VIEW_TYPE_INGESTED_APP = 1;
    public static final int VIEW_TYPE_SYSTEM_UX_ROUTE_APP = 2;
    private final AdapterUpdatesCoalescer mAdapterUpdatesCoalescer;
    private final Context mContext;
    private boolean mIsOCShellAutomationEnabled;
    private List<ManagedLauncherItem> mItems = new ArrayList();
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final ScrollCallback mScrollCallback;

    /* access modifiers changed from: private */
    public enum AppTileType {
        HOSTED_APP,
        INGESTED_APP,
        SYSTEM_UX_ROUTE_APP
    }

    /* access modifiers changed from: private */
    public interface IAppTileClickListener {
        void onClick(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding);
    }

    private static boolean isSameRatio(int i, int i2, int i3, int i4) {
        return i * i4 == i2 * i3;
    }

    public void destroy() {
    }

    public ManagedLauncherContentAdapter(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ScrollCallback scrollCallback) {
        Log.d(TAG, "Creating the ManagedLauncherContentAdapter instance");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mScrollCallback = scrollCallback;
        this.mAdapterUpdatesCoalescer = new AdapterUpdatesCoalescer(this);
        this.mIsOCShellAutomationEnabled = this.mPanelApp.isOCShellAutomationEnabled();
    }

    public void setItems(List<ManagedLauncherItem> list) {
        this.mAdapterUpdatesCoalescer.setUpdate(new LibraryUpdate(this, list));
    }

    /* access modifiers changed from: private */
    public class LibraryUpdate implements ILibraryUpdate {
        private final List<ManagedLauncherItem> mUpdateItems;

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
        public void onStart() {
        }

        LibraryUpdate(ManagedLauncherContentAdapter managedLauncherContentAdapter, List<ManagedLauncherItem> list) {
            this.mUpdateItems = list;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
        public DiffUtil.Callback getDiffUtilCallback() {
            return new ManagedLauncherContentAdapterDiffUtilCallback(this.mUpdateItems, ManagedLauncherContentAdapter.this.mItems);
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
        public void beforeDispatchUpdates() {
            ManagedLauncherContentAdapter.this.mItems.clear();
            ManagedLauncherContentAdapter.this.mItems.addAll(this.mUpdateItems);
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
        public void afterDispatchUpdates() {
            ManagedLauncherContentAdapter.this.mScrollCallback.onUpdate();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType = new int[ManagedLauncherItem.ItemType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.APP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.INGESTED_APP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.SYSTEM_UX_ROUTE_APP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType[this.mItems.get(i).getItemType().ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 != 2) {
            return i2 != 3 ? -1 : 2;
        }
        return 1;
    }

    public /* synthetic */ void lambda$onCreateViewHolder$141$ManagedLauncherContentAdapter(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
        this.mPanelApp.actionNavigate(anytimeTabletManagedLauncherAppTileV2Binding.getItem().getPackageName(), "");
    }

    public /* synthetic */ void lambda$onCreateViewHolder$142$ManagedLauncherContentAdapter(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
        this.mPanelApp.actionNavigate(anytimeTabletManagedLauncherAppTileV2Binding.getItem().getIngestedApp().packageName, "");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            return createViewHolder(viewGroup, AppTileType.HOSTED_APP, new IAppTileClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$4flYIcC0k423d4USh2mlV1fwsIY */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.IAppTileClickListener
                public final void onClick(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
                    ManagedLauncherContentAdapter.this.lambda$onCreateViewHolder$141$ManagedLauncherContentAdapter(anytimeTabletManagedLauncherAppTileV2Binding);
                }
            });
        }
        if (i == 1) {
            return createViewHolder(viewGroup, AppTileType.INGESTED_APP, new IAppTileClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$7v8FNS9i2CjlG67bxjRLqzE7eY */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.IAppTileClickListener
                public final void onClick(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
                    ManagedLauncherContentAdapter.this.lambda$onCreateViewHolder$142$ManagedLauncherContentAdapter(anytimeTabletManagedLauncherAppTileV2Binding);
                }
            });
        }
        if (i != 2) {
            return null;
        }
        return createViewHolder(viewGroup, AppTileType.SYSTEM_UX_ROUTE_APP, new IAppTileClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$uAK5sQoAKpxqQ1M9d0fDNqqYdfM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter.IAppTileClickListener
            public final void onClick(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
                ManagedLauncherContentAdapter.this.lambda$onCreateViewHolder$143$ManagedLauncherContentAdapter(anytimeTabletManagedLauncherAppTileV2Binding);
            }
        });
    }

    public /* synthetic */ void lambda$onCreateViewHolder$143$ManagedLauncherContentAdapter(AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
        this.mPanelApp.actionNavigate(anytimeTabletManagedLauncherAppTileV2Binding.getItem().getRoute(), "");
    }

    private RecyclerView.ViewHolder createViewHolder(ViewGroup viewGroup, AppTileType appTileType, IAppTileClickListener iAppTileClickListener) {
        AnytimeTabletManagedLauncherAppTileV2Binding inflate = AnytimeTabletManagedLauncherAppTileV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.appTileButton.setOnHoverListener(new View.OnHoverListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$TMV4efbiz4u9K5T8NGYxDfImbzM */
            private final /* synthetic */ AnytimeTabletManagedLauncherAppTileV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return ManagedLauncherContentAdapter.this.lambda$createViewHolder$144$ManagedLauncherContentAdapter(this.f$1, view, motionEvent);
            }
        });
        inflate.appTileButton.setOnClickListener(new View.OnClickListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$rXWdd7__LbT5sw01fUotdOkSZi4 */
            private final /* synthetic */ AnytimeTabletManagedLauncherAppTileV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ManagedLauncherContentAdapter.IAppTileClickListener.this.onClick(this.f$1);
            }
        });
        return new AppTileHolder(appTileType, inflate);
    }

    /* access modifiers changed from: private */
    /* renamed from: onHoverAppTile */
    public boolean lambda$createViewHolder$144$ManagedLauncherContentAdapter(View view, MotionEvent motionEvent, AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.ocimagecard_inner_zoom);
        loadAnimation.reset();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletManagedLauncherAppTileV2Binding.setIsHovered(true);
            anytimeTabletManagedLauncherAppTileV2Binding.title.setSelected(true);
            if (anytimeTabletManagedLauncherAppTileV2Binding.getItem().getIngestedApp() != null) {
                anytimeTabletManagedLauncherAppTileV2Binding.imageView.startAnimation(loadAnimation);
            } else {
                anytimeTabletManagedLauncherAppTileV2Binding.icon.startAnimation(loadAnimation);
            }
        } else if (actionMasked == 10) {
            anytimeTabletManagedLauncherAppTileV2Binding.setIsHovered(false);
            anytimeTabletManagedLauncherAppTileV2Binding.title.setSelected(false);
            if (anytimeTabletManagedLauncherAppTileV2Binding.getItem().getIngestedApp() != null) {
                anytimeTabletManagedLauncherAppTileV2Binding.imageView.clearAnimation();
            } else {
                anytimeTabletManagedLauncherAppTileV2Binding.icon.clearAnimation();
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ManagedLauncherItem managedLauncherItem = this.mItems.get(i);
        int i2 = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType[managedLauncherItem.getItemType().ordinal()];
        if (i2 == 1) {
            onBindAppHolder((AppTileHolder) viewHolder, managedLauncherItem);
        } else if (i2 == 2) {
            onBindIngestedAppHolder((AppTileHolder) viewHolder, managedLauncherItem);
        } else if (i2 == 3) {
            onBindSystemUxRouteAppHolder((AppTileHolder) viewHolder, managedLauncherItem);
        }
    }

    private void onBindAppHolder(AppTileHolder appTileHolder, ManagedLauncherItem managedLauncherItem) {
        appTileHolder.mBinding.setItem(managedLauncherItem);
        appTileHolder.mBinding.setDisplayName(managedLauncherItem.getDisplayName());
        appTileHolder.mBinding.icon.setImageDrawable(generateAppTileImage(managedLauncherItem.getPackageName()));
        appTileHolder.mBinding.setImageAvailable(true);
        if (this.mIsOCShellAutomationEnabled) {
            appTileHolder.mBinding.appTileButton.setTag(managedLauncherItem.getPackageName());
        }
    }

    private void onBindIngestedAppHolder(AppTileHolder appTileHolder, ManagedLauncherItem managedLauncherItem) {
        appTileHolder.mBinding.setItem(managedLauncherItem);
        App ingestedApp = managedLauncherItem.getIngestedApp();
        appTileHolder.mBinding.setDisplayName(ingestedApp.displayName);
        appTileHolder.mBinding.imageView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(Uri.parse(LibraryAppUtils.getPreferredAppTileImageUri(ingestedApp))))).setControllerListener(new FrescoControllerListener(new FrescoControllerListener.FrescoImageReadyCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.$$Lambda$ManagedLauncherContentAdapter$nzrBIXXaZEHDBP6DAZ6exRU29BE */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener.FrescoImageReadyCallback
            public final void onImageReady() {
                ManagedLauncherContentAdapter.AppTileHolder.this.mBinding.setImageAvailable(true);
            }
        }))).build());
        if (this.mIsOCShellAutomationEnabled) {
            appTileHolder.mBinding.appTileButton.setTag(ingestedApp.packageName);
        }
    }

    private void onBindSystemUxRouteAppHolder(AppTileHolder appTileHolder, ManagedLauncherItem managedLauncherItem) {
        appTileHolder.mBinding.setItem(managedLauncherItem);
        appTileHolder.mBinding.setDisplayName(managedLauncherItem.getDisplayName());
        appTileHolder.mBinding.icon.setImageDrawable(this.mContext.getResources().getDrawable(managedLauncherItem.getDrawableId(), null));
        appTileHolder.mBinding.setImageAvailable(true);
        if (this.mIsOCShellAutomationEnabled) {
            appTileHolder.mBinding.appTileButton.setTag(managedLauncherItem.getPackageName());
        }
    }

    /* access modifiers changed from: package-private */
    public class AppTileHolder extends RecyclerView.ViewHolder {
        final AppTileType mAppTileType;
        final AnytimeTabletManagedLauncherAppTileV2Binding mBinding;

        AppTileHolder(AppTileType appTileType, AnytimeTabletManagedLauncherAppTileV2Binding anytimeTabletManagedLauncherAppTileV2Binding) {
            super(anytimeTabletManagedLauncherAppTileV2Binding.getRoot());
            this.mAppTileType = appTileType;
            this.mBinding = anytimeTabletManagedLauncherAppTileV2Binding;
        }
    }

    private Drawable generateAppTileImage(String str) {
        Drawable drawable = null;
        try {
            Context createPackageContext = this.mContext.createPackageContext(str, 2);
            ApplicationInfo applicationInfo = createPackageContext.getPackageManager().getApplicationInfo(str, 128);
            drawable = createPackageContext.getPackageManager().getResourcesForApplication(applicationInfo).getDrawableForDensity(applicationInfo.icon, 640, null);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            String str2 = TAG;
            Log.w(str2, "Failed to retrieve application icon, using fallback: " + str);
        }
        if (drawable == null) {
            return generateDefaultAppTileImage();
        }
        return coerceToAppTileDimensions(drawable);
    }

    private Drawable generateDefaultAppTileImage() {
        return coerceToAppTileDimensions(this.mContext.getResources().getDrawable(R.drawable.managed_launcher_default_app_tile_icon, null));
    }

    private Drawable coerceToAppTileDimensions(Drawable drawable) {
        Bitmap drawableToBitmap = drawableToBitmap(drawable);
        int dimensionPixelSize = this.mPanelApp.getContext().getResources().getDimensionPixelSize(R.dimen.anytime_tablet_enterprise_launcher_app_tile_width);
        int dimensionPixelSize2 = this.mPanelApp.getContext().getResources().getDimensionPixelSize(R.dimen.anytime_tablet_enterprise_launcher_app_tile_height);
        return !isSameRatio(drawableToBitmap.getWidth(), drawableToBitmap.getHeight(), dimensionPixelSize, dimensionPixelSize2) ? scaleAndCenterAgainstBlurredBackground(drawableToBitmap, dimensionPixelSize, dimensionPixelSize2) : drawable;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap getBlurredBackgroundBitmap(Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RenderScript create = RenderScript.create(this.mPanelApp.getContext());
        Allocation createFromBitmap = Allocation.createFromBitmap(create, copy, Allocation.MipmapControl.MIPMAP_FULL, 128);
        Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        create2.setInput(createFromBitmap);
        create2.setRadius(24.0f);
        create2.forEach(createTyped);
        createTyped.copyTo(copy);
        int i5 = width * i2;
        int i6 = height * i;
        if (i5 < i6) {
            i3 = i5 / i;
            i4 = width;
        } else {
            i4 = i6 / i2;
            i3 = height;
        }
        int i7 = (width / 2) - (i4 / 2);
        int i8 = (height / 2) - (i3 / 2);
        Paint paint = new Paint();
        paint.setAlpha(179);
        canvas.drawBitmap(copy, new Rect(i7, i8, i4 + i7, i3 + i8), new Rect(0, 0, i, i2), paint);
        return createBitmap;
    }

    private BitmapDrawable scaleAndCenterAgainstBlurredBackground(Bitmap bitmap, int i, int i2) {
        Bitmap blurredBackgroundBitmap = getBlurredBackgroundBitmap(bitmap, i, i2);
        Canvas canvas = new Canvas(blurredBackgroundBitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height > i2) {
            float f = (float) height;
            float f2 = ((float) i2) / f;
            bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(f2 * ((float) width)), Math.round(f * f2), false);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
        if (width <= i) {
            int i3 = (i - width) / 2;
            canvas.drawBitmap(bitmap, (Rect) null, new Rect(i3, 0, width + i3, i2), (Paint) null);
        } else {
            int i4 = (width - i) / 2;
            canvas.drawBitmap(bitmap, new Rect(i4, 0, width - i4, height), new Rect(0, 0, i, i2), (Paint) null);
        }
        return new BitmapDrawable(this.mContext.getResources(), blurredBackgroundBitmap);
    }
}
