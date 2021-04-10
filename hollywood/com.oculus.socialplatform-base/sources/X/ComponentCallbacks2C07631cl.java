package X;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.GeneratedAppGlideModule;
import com.bumptech.glide.GeneratedAppGlideModuleImpl;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1cl  reason: invalid class name and case insensitive filesystem */
public final class ComponentCallbacks2C07631cl implements ComponentCallbacks2 {
    public static volatile ComponentCallbacks2C07631cl A0A;
    public static volatile boolean A0B;
    public final C08731fB A00;
    public final C07641cm A01;
    public final AbstractC07941di A02;
    public final AnonymousClass1hX A03;
    public final C07821dS A04;
    public final AnonymousClass1S9 A05;
    public final C08411eb A06;
    public final List<AnonymousClass1g0> A07 = new ArrayList();
    public final C08101dy A08;
    public final AbstractC08671f5 A09;

    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Nullable
    public static GeneratedAppGlideModule A00(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (!Log.isLoggable("Glide", 5)) {
                return null;
            }
            Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            return null;
        } catch (InstantiationException e) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e2);
        } catch (NoSuchMethodException e3) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e3);
        } catch (InvocationTargetException e4) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e4);
        }
    }

    @NonNull
    public static ComponentCallbacks2C07631cl A01(@NonNull Context context) {
        if (A0A == null) {
            GeneratedAppGlideModule A002 = A00(context.getApplicationContext());
            synchronized (ComponentCallbacks2C07631cl.class) {
                if (A0A == null) {
                    if (!A0B) {
                        A0B = true;
                        A05(context, new C07651cn(), A002);
                        A0B = false;
                    } else {
                        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
                    }
                }
            }
        }
        return A0A;
    }

    @NonNull
    public static C08411eb A02(@Nullable Context context) {
        if (context != null) {
            return A01(context).A06;
        }
        throw new NullPointerException("You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
    }

    @VisibleForTesting
    public static synchronized void A04() {
        synchronized (ComponentCallbacks2C07631cl.class) {
            if (A0A != null) {
                A0A.A00.getBaseContext().getApplicationContext().unregisterComponentCallbacks(A0A);
                AbstractC08671f5 r2 = A0A.A09;
                AnonymousClass1eX r1 = r2.A02;
                C07681cq.A00(r1.A04);
                C07681cq.A00(r1.A05);
                C07681cq.A00(r1.A06);
                C07681cq.A00(r1.A03);
                C08051dt r12 = r2.A03;
                synchronized (r12) {
                    if (r12.A01 != null) {
                        r12.A01.clear();
                    }
                }
                C08691f7 r13 = r2.A00;
                r13.A04 = true;
                Executor executor = r13.A03;
                if (executor instanceof ExecutorService) {
                    C07681cq.A00((ExecutorService) executor);
                }
            }
            A0A = null;
        }
    }

    @Nullable
    public static File A03(@NonNull Context context, @NonNull String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
        } else if (Log.isLoggable("Glide", 6)) {
            Log.e("Glide", "default disk cache dir is null");
            return null;
        }
        return null;
    }

    @GuardedBy("Glide.class")
    public static void A05(@NonNull Context context, @NonNull C07651cn r19, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        C08401ea r0;
        List<AnonymousClass1fS<Object>> unmodifiableList;
        Context applicationContext = context.getApplicationContext();
        List emptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.isManifestParsingEnabled()) {
            AnonymousClass1Fp r02 = new AnonymousClass1Fp(applicationContext);
            emptyList = new ArrayList();
            try {
                Context context2 = r02.A00;
                ApplicationInfo applicationInfo = context2.getPackageManager().getApplicationInfo(context2.getPackageName(), 128);
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    for (String str : bundle.keySet()) {
                        if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                            try {
                                Class<?> cls = Class.forName(str);
                                try {
                                    Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                                    StringBuilder sb = new StringBuilder("Expected instanceof GlideModule, but found: ");
                                    sb.append(newInstance);
                                    throw new RuntimeException(sb.toString());
                                } catch (InstantiationException e) {
                                    AnonymousClass1Fp.A00(cls, e);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } catch (IllegalAccessException e2) {
                                    AnonymousClass1Fp.A00(cls, e2);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } catch (NoSuchMethodException e3) {
                                    AnonymousClass1Fp.A00(cls, e3);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } catch (InvocationTargetException e4) {
                                    AnonymousClass1Fp.A00(cls, e4);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            } catch (ClassNotFoundException e5) {
                                throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
                            }
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e6) {
                throw new RuntimeException("Unable to find metadata to parse GlideModules", e6);
            }
        }
        if (generatedAppGlideModule != null && !Collections.emptySet().isEmpty()) {
            Collections.emptySet();
            Iterator it = emptyList.iterator();
            if (it.hasNext()) {
                it.next();
                throw new NullPointerException("getClass");
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator it2 = emptyList.iterator();
            if (it2.hasNext()) {
                it2.next();
                throw new NullPointerException("getClass");
            }
        }
        if (generatedAppGlideModule == null || !(generatedAppGlideModule instanceof GeneratedAppGlideModuleImpl)) {
            r0 = null;
        } else {
            r0 = new C08401ea();
        }
        r19.A0B = r0;
        Iterator it3 = emptyList.iterator();
        if (it3.hasNext()) {
            it3.next();
            throw new NullPointerException("applyOptions");
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.applyOptions(applicationContext, r19);
        }
        if (r19.A09 == null) {
            C07661co r3 = new C07661co(false);
            if (ExecutorServiceC07671cp.A02 == 0) {
                ExecutorServiceC07671cp.A02 = Math.min(4, Runtime.getRuntime().availableProcessors());
            }
            int i = ExecutorServiceC07671cp.A02;
            r3.A00 = i;
            r3.A01 = i;
            r3.A02 = "source";
            r19.A09 = r3.A00();
        }
        if (r19.A08 == null) {
            C07661co r1 = new C07661co(true);
            r1.A00 = 1;
            r1.A01 = 1;
            r1.A02 = "disk-cache";
            r19.A08 = r1.A00();
        }
        if (r19.A07 == null) {
            if (ExecutorServiceC07671cp.A02 == 0) {
                ExecutorServiceC07671cp.A02 = Math.min(4, Runtime.getRuntime().availableProcessors());
            }
            int i2 = 1;
            if (ExecutorServiceC07671cp.A02 >= 4) {
                i2 = 2;
            }
            C07661co r12 = new C07661co(true);
            r12.A00 = i2;
            r12.A01 = i2;
            r12.A02 = "animation";
            r19.A07 = r12.A00();
        }
        C07691cr r13 = r19.A06;
        if (r13 == null) {
            r13 = new C07691cr(new C07701cs(applicationContext));
            r19.A06 = r13;
        }
        if (r19.A0A == null) {
            r19.A0A = new AnonymousClass1S8();
        }
        if (r19.A02 == null) {
            int i3 = r13.A01;
            if (i3 > 0) {
                r19.A02 = new AnonymousClass1hE((long) i3);
            } else {
                r19.A02 = new C08021dq();
            }
        }
        if (r19.A03 == null) {
            r19.A03 = new AnonymousClass1hX(r19.A06.A00);
        }
        C07821dS r6 = r19.A05;
        if (r6 == null) {
            r6 = new C07821dS((long) r19.A06.A02);
            r19.A05 = r6;
        }
        AbstractC08061du r5 = r19.A04;
        if (r5 == null) {
            r5 = new C08031dr(applicationContext);
            r19.A04 = r5;
        }
        if (r19.A01 == null) {
            r19.A01 = new AbstractC08671f5(r6, r5, r19.A08, r19.A09, new ExecutorServiceC07671cp(new ThreadPoolExecutor(0, Integer.MAX_VALUE, ExecutorServiceC07671cp.A01, TimeUnit.MILLISECONDS, new SynchronousQueue(), new ThreadFactoryC07721cu("source-unlimited", AbstractC08931fl.A00, false))), r19.A07);
        }
        List<AnonymousClass1fS<Object>> list = r19.A0C;
        if (list == null) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        r19.A0C = unmodifiableList;
        ComponentCallbacks2C07631cl r10 = new ComponentCallbacks2C07631cl(applicationContext, r19.A01, r19.A05, r19.A02, r19.A03, new C08411eb(r19.A0B), r19.A0A, r19.A00, r19.A0D, r19.A0C);
        Iterator it4 = emptyList.iterator();
        while (it4.hasNext()) {
            it4.next();
        }
        applicationContext.registerComponentCallbacks(r10);
        A0A = r10;
    }

    public final void onLowMemory() {
        C08381eW.A04();
        this.A04.A02(0);
        this.A02.A27();
        AnonymousClass1hX r1 = this.A03;
        synchronized (r1) {
            AnonymousClass1hX.A03(r1, 0);
        }
    }

    public final void onTrimMemory(int i) {
        long j;
        C08381eW.A04();
        for (AnonymousClass1g0 r0 : this.A07) {
            r0.onTrimMemory(i);
        }
        C07821dS r4 = this.A04;
        if (i >= 40) {
            r4.A02(0);
        } else if (i >= 20 || i == 15) {
            synchronized (r4) {
                j = r4.A01;
            }
            r4.A02(j / 2);
        }
        this.A02.AAm(i);
        AnonymousClass1hX r1 = this.A03;
        synchronized (r1) {
            if (i >= 40) {
                AnonymousClass1hX.A03(r1, 0);
            } else if (i >= 20 || i == 15) {
                AnonymousClass1hX.A03(r1, r1.A01 / 2);
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/1f5;Lcom/bumptech/glide/load/engine/cache/MemoryCache;LX/1di;Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;LX/1eb;LX/1S9;ILcom/bumptech/glide/Glide$RequestOptionsFactory;Ljava/util/Map<Ljava/lang/Class<*>;LX/1fp<**>;>;Ljava/util/List<LX/1fS<Ljava/lang/Object;>;>;ZZ)V */
    public ComponentCallbacks2C07631cl(@NonNull Context context, @NonNull AbstractC08671f5 r39, @NonNull C07821dS r40, @NonNull AbstractC07941di r41, @NonNull AnonymousClass1hX r42, @NonNull C08411eb r43, @NonNull AnonymousClass1S9 r44, C08101dy r45, @NonNull Map map, @NonNull List list) {
        List<C07751cx<?>> list2;
        this.A09 = r39;
        this.A02 = r41;
        this.A03 = r42;
        this.A04 = r40;
        this.A06 = r43;
        this.A05 = r44;
        this.A08 = r45;
        Resources resources = context.getResources();
        C07641cm r0 = new C07641cm();
        this.A01 = r0;
        AnonymousClass1gR r2 = new AnonymousClass1gR();
        C07931dh r1 = r0.A05;
        synchronized (r1) {
            r1.A00.add(r2);
        }
        if (Build.VERSION.SDK_INT >= 27) {
            C07641cm r02 = this.A01;
            C09121gf r22 = new C09121gf();
            C07931dh r12 = r02.A05;
            synchronized (r12) {
                r12.A00.add(r22);
            }
        }
        List<AbstractC08251eH> A002 = this.A01.A00();
        AnonymousClass1gB r27 = new AnonymousClass1gB(context, A002, r41, r42);
        AnonymousClass1dO r13 = new AnonymousClass1dO(r41, new C07911df());
        AnonymousClass1gC r6 = new AnonymousClass1gC(this.A01.A00(), resources.getDisplayMetrics(), r41, r42);
        C07551cd r17 = new C07551cd(r6);
        C07541cc r8 = new C07541cc(r6, r42);
        AnonymousClass1dB r14 = new AnonymousClass1dB(context);
        C07171bj r26 = new C07171bj(resources);
        C07151bh r25 = new C07151bh(resources);
        C07161bi r24 = new C07161bi(resources);
        C07191bl r23 = new C07191bl(resources);
        AnonymousClass1dD r03 = new AnonymousClass1dD(r42);
        AnonymousClass1dE r222 = new AnonymousClass1dE();
        C08571er r21 = new C08571er();
        ContentResolver contentResolver = context.getContentResolver();
        C07641cm r122 = this.A01;
        C06711au r11 = new C06711au();
        C07891dd r9 = r122.A04;
        synchronized (r9) {
            list2 = r9.A00;
            list2.add(new C07751cx<>(ByteBuffer.class, r11));
        }
        C06721av r112 = new C06721av(r42);
        synchronized (r9) {
            list2.add(new C07751cx<>(InputStream.class, r112));
        }
        r122.A06("Bitmap", ByteBuffer.class, Bitmap.class, r17);
        r122.A06("Bitmap", InputStream.class, Bitmap.class, r8);
        this.A01.A06("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new C07561ce(r6));
        C07641cm r62 = this.A01;
        r62.A06("Bitmap", ParcelFileDescriptor.class, Bitmap.class, r13);
        r62.A06("Bitmap", AssetFileDescriptor.class, Bitmap.class, new AnonymousClass1dO(r41, new C07901de()));
        C07141bg<?> r123 = C07141bg.A00;
        r62.A04(Bitmap.class, Bitmap.class, r123);
        r62.A06("Bitmap", Bitmap.class, Bitmap.class, new AnonymousClass1d9());
        r62.A03(Bitmap.class, r03);
        r62.A06("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new AnonymousClass1dK(resources, r17));
        r62.A06("BitmapDrawable", InputStream.class, BitmapDrawable.class, new AnonymousClass1dK(resources, r8));
        r62.A06("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new AnonymousClass1dK(resources, r13));
        r62.A03(BitmapDrawable.class, new AnonymousClass1dF(r41, r03));
        r62.A06("Gif", InputStream.class, AnonymousClass1gA.class, new AnonymousClass1dJ(A002, r27, r42));
        r62.A06("Gif", ByteBuffer.class, AnonymousClass1gA.class, r27);
        r62.A03(AnonymousClass1gA.class, new C08581es());
        r62.A04(AnonymousClass1gD.class, AnonymousClass1gD.class, r123);
        r62.A06("Bitmap", AnonymousClass1gD.class, Bitmap.class, new AnonymousClass1dP(r41));
        r62.A06("legacy_append", Uri.class, Drawable.class, r14);
        r62.A06("legacy_append", Uri.class, Bitmap.class, new AnonymousClass1dC(r14, r41));
        r62.A02(new C07781d4());
        r62.A04(File.class, ByteBuffer.class, new C06861b9());
        r62.A04(File.class, InputStream.class, new C06831b6());
        r62.A06("legacy_append", File.class, File.class, new C07531cb());
        r62.A04(File.class, ParcelFileDescriptor.class, new C06801b3());
        r62.A04(File.class, File.class, r123);
        r62.A02(new C07601ci(r42));
        this.A01.A02(new C07731cv());
        C07641cm r63 = this.A01;
        Class cls = Integer.TYPE;
        r63.A04(cls, InputStream.class, r26);
        r63.A04(cls, ParcelFileDescriptor.class, r24);
        r63.A04(Integer.class, InputStream.class, r26);
        r63.A04(Integer.class, ParcelFileDescriptor.class, r24);
        r63.A04(Integer.class, Uri.class, r25);
        r63.A04(cls, AssetFileDescriptor.class, r23);
        r63.A04(Integer.class, AssetFileDescriptor.class, r23);
        r63.A04(cls, Uri.class, r25);
        r63.A04(String.class, InputStream.class, new C07001bS());
        r63.A04(Uri.class, InputStream.class, new C07001bS());
        r63.A04(String.class, InputStream.class, new C07231bp());
        r63.A04(String.class, ParcelFileDescriptor.class, new C07221bo());
        r63.A04(String.class, AssetFileDescriptor.class, new C07211bn());
        r63.A04(Uri.class, InputStream.class, new C06961bO());
        r63.A04(Uri.class, InputStream.class, new C07311bx(context.getAssets()));
        r63.A04(Uri.class, ParcelFileDescriptor.class, new C07291bv(context.getAssets()));
        r63.A04(Uri.class, InputStream.class, new C06911bJ(context));
        r63.A04(Uri.class, InputStream.class, new C06921bK(context));
        if (Build.VERSION.SDK_INT >= 29) {
            this.A01.A04(Uri.class, InputStream.class, new C07111bd(context));
            this.A01.A04(Uri.class, ParcelFileDescriptor.class, new C07101bc(context));
        }
        C07641cm r64 = this.A01;
        r64.A04(Uri.class, InputStream.class, new C07271bt(contentResolver));
        r64.A04(Uri.class, ParcelFileDescriptor.class, new C07261bs(contentResolver));
        r64.A04(Uri.class, AssetFileDescriptor.class, new C07241bq(contentResolver));
        r64.A04(Uri.class, InputStream.class, new C06951bN());
        r64.A04(URL.class, InputStream.class, new C06981bQ());
        r64.A04(Uri.class, File.class, new C06881bF(context));
        r64.A04(AnonymousClass1Rx.class, InputStream.class, new AnonymousClass1cH());
        r64.A04(byte[].class, ByteBuffer.class, new C07351c4());
        r64.A04(byte[].class, InputStream.class, new AnonymousClass1c3());
        r64.A04(Uri.class, Uri.class, r123);
        r64.A04(Drawable.class, Drawable.class, r123);
        r64.A06("legacy_append", Drawable.class, Drawable.class, new C07811dR());
        r64.A05(Bitmap.class, BitmapDrawable.class, new AnonymousClass1dG(resources));
        r64.A05(Bitmap.class, byte[].class, r222);
        r64.A05(Drawable.class, byte[].class, new AnonymousClass1dI(r41, r222, r21));
        r64.A05(AnonymousClass1gA.class, byte[].class, r21);
        AnonymousClass1dO r65 = new AnonymousClass1dO(r41, new C07861da());
        this.A01.A06("legacy_append", ByteBuffer.class, Bitmap.class, r65);
        this.A01.A06("legacy_append", ByteBuffer.class, BitmapDrawable.class, new AnonymousClass1dK(resources, r65));
        this.A00 = new C08731fB(context, r42, this.A01, new AnonymousClass1e0(), r45, map, list, r39);
    }
}
