package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* renamed from: PJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PJ {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f8683a = null;
    public static UG1 b = null;
    public static AH1 c = null;
    public static String d = null;
    public static int e = -1;
    public static final ThreadLocal f = new ThreadLocal();
    public static final LJ g = new C4537rC1();
    public static final MJ h = new YE1();
    public static final MJ i = new BG1();
    public final Context j;

    public PJ(Context context) {
        Objects.requireNonNull(context, "null reference");
        this.j = context;
    }

    public static int a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(valueOf.length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static PJ c(Context context, MJ mj, String str) {
        Cursor cursor;
        ThreadLocal threadLocal = f;
        NJ nj = (NJ) threadLocal.get();
        NJ nj2 = new NJ(null);
        threadLocal.set(nj2);
        try {
            KJ a2 = mj.a(context, str, g);
            int i2 = a2.f8359a;
            int i3 = a2.b;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            int i4 = a2.c;
            if (i4 == 0 || ((i4 == -1 && a2.f8359a == 0) || (i4 == 1 && a2.b == 0))) {
                int i5 = a2.f8359a;
                int i6 = a2.b;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i5);
                sb2.append(" and remote version is ");
                sb2.append(i6);
                sb2.append(".");
                throw new JJ(sb2.toString(), null);
            } else if (i4 == -1) {
                PJ j2 = j(context, str);
                Cursor cursor2 = nj2.f8539a;
                if (cursor2 != null) {
                    cursor2.close();
                }
                threadLocal.set(nj);
                return j2;
            } else if (i4 == 1) {
                try {
                    PJ e2 = e(context, str, a2.b);
                    Cursor cursor3 = nj2.f8539a;
                    if (cursor3 != null) {
                        cursor3.close();
                    }
                    threadLocal.set(nj);
                    return e2;
                } catch (JJ e3) {
                    String valueOf = String.valueOf(e3.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    int i7 = a2.f8359a;
                    if (i7 == 0 || mj.a(context, str, new OJ(i7)).c != -1) {
                        throw new JJ("Remote load failed. No local fallback found.", e3, null);
                    }
                    return j(context, str);
                }
            } else {
                int i8 = a2.c;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i8);
                throw new JJ(sb3.toString(), null);
            }
        } finally {
            cursor = nj2.f8539a;
            if (cursor != null) {
                cursor.close();
            }
            f.set(nj);
        }
    }

    public static int d(Context context, String str, boolean z) {
        Boolean bool;
        ClassLoader classLoader;
        try {
            synchronized (PJ.class) {
                Boolean bool2 = f8683a;
                if (bool2 == null) {
                    try {
                        Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteModule$DynamiteLoaderClassLoader.class.getName());
                        Field declaredField = loadClass.getDeclaredField("sClassLoader");
                        synchronized (loadClass) {
                            ClassLoader classLoader2 = (ClassLoader) declaredField.get(null);
                            if (classLoader2 != null) {
                                if (classLoader2 == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        f(classLoader2);
                                    } catch (JJ unused) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int i2 = i(context, str, z);
                                    String str2 = d;
                                    if (str2 != null) {
                                        if (!str2.isEmpty()) {
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                classLoader = new DelegateLastClassLoader(d, ClassLoader.getSystemClassLoader());
                                            } else {
                                                classLoader = new KG1(d, ClassLoader.getSystemClassLoader());
                                            }
                                            f(classLoader);
                                            declaredField.set(null, classLoader);
                                            f8683a = Boolean.TRUE;
                                            return i2;
                                        }
                                    }
                                    return i2;
                                } catch (JJ unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                    bool2 = bool;
                                    f8683a = bool2;
                                    if (!bool2.booleanValue()) {
                                        return g(context, str, z);
                                    }
                                    try {
                                        return i(context, str, z);
                                    } catch (JJ e2) {
                                        String valueOf = String.valueOf(e2.getMessage());
                                        Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
                                        return 0;
                                    }
                                }
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        String valueOf2 = String.valueOf(e3);
                        StringBuilder sb = new StringBuilder(valueOf2.length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(valueOf2);
                        Log.w("DynamiteModule", sb.toString());
                        bool2 = Boolean.FALSE;
                    }
                }
            }
        } catch (Throwable th) {
            try {
                Objects.requireNonNull(context, "null reference");
            } catch (Exception e4) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", e4);
            }
            throw th;
        }
    }

    public static PJ e(Context context, String str, int i2) {
        Boolean bool;
        VY vy;
        try {
            synchronized (PJ.class) {
                bool = f8683a;
            }
            if (bool == null) {
                throw new JJ("Failed to determine which loading route to use.", null);
            } else if (bool.booleanValue()) {
                return h(context, str, i2);
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
                sb.append("Selected remote version of ");
                sb.append(str);
                sb.append(", version >= ");
                sb.append(i2);
                Log.i("DynamiteModule", sb.toString());
                UG1 k = k(context);
                if (k != null) {
                    Parcel d2 = k.d(6, k.c());
                    int readInt = d2.readInt();
                    d2.recycle();
                    if (readInt >= 2) {
                        vy = k.e0(new BinderC0773Mq0(context), str, i2);
                    } else {
                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                        vy = k.f(new BinderC0773Mq0(context), str, i2);
                    }
                    if (BinderC0773Mq0.f(vy) != null) {
                        return new PJ((Context) BinderC0773Mq0.f(vy));
                    }
                    throw new JJ("Failed to load remote module.", null);
                }
                throw new JJ("Failed to create IDynamiteLoader.", null);
            }
        } catch (RemoteException e2) {
            throw new JJ("Failed to load remote module.", e2, null);
        } catch (JJ e3) {
            throw e3;
        } catch (Throwable th) {
            try {
                Objects.requireNonNull(context, "null reference");
            } catch (Exception e4) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", e4);
            }
            throw new JJ("Failed to load remote module.", th, null);
        }
    }

    public static void f(ClassLoader classLoader) {
        AH1 ah1;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                ah1 = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof AH1) {
                    ah1 = (AH1) queryLocalInterface;
                } else {
                    ah1 = new AH1(iBinder);
                }
            }
            c = ah1;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new JJ("Failed to instantiate dynamite loader", e2, null);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public static int g(Context context, String str, boolean z) {
        UG1 k = k(context);
        if (k == null) {
            return 0;
        }
        try {
            Parcel d2 = k.d(6, k.c());
            int readInt = d2.readInt();
            d2.recycle();
            if (readInt >= 2) {
                BinderC0773Mq0 mq0 = new BinderC0773Mq0(context);
                Parcel c2 = k.c();
                AbstractC4546rF1.b(c2, mq0);
                c2.writeString(str);
                c2.writeInt(z);
                Parcel d3 = k.d(5, c2);
                int readInt2 = d3.readInt();
                d3.recycle();
                return readInt2;
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            BinderC0773Mq0 mq02 = new BinderC0773Mq0(context);
            Parcel c3 = k.c();
            AbstractC4546rF1.b(c3, mq02);
            c3.writeString(str);
            c3.writeInt(z ? 1 : 0);
            Parcel d4 = k.d(3, c3);
            int readInt3 = d4.readInt();
            d4.recycle();
            return readInt3;
        } catch (RemoteException e2) {
            String valueOf = String.valueOf(e2.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    public static PJ h(Context context, String str, int i2) {
        AH1 ah1;
        Boolean valueOf;
        VY vy;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        synchronized (PJ.class) {
            ah1 = c;
        }
        if (ah1 != null) {
            NJ nj = (NJ) f.get();
            if (nj == null || nj.f8539a == null) {
                throw new JJ("No result cursor", null);
            }
            Context applicationContext = context.getApplicationContext();
            Cursor cursor = nj.f8539a;
            new BinderC0773Mq0(null);
            synchronized (PJ.class) {
                valueOf = Boolean.valueOf(e >= 2);
            }
            if (valueOf.booleanValue()) {
                BinderC0773Mq0 mq0 = new BinderC0773Mq0(applicationContext);
                BinderC0773Mq0 mq02 = new BinderC0773Mq0(cursor);
                Parcel c2 = ah1.c();
                AbstractC4546rF1.b(c2, mq0);
                c2.writeString(str);
                c2.writeInt(i2);
                AbstractC4546rF1.b(c2, mq02);
                Parcel d2 = ah1.d(3, c2);
                vy = BinderC0773Mq0.d(d2.readStrongBinder());
                d2.recycle();
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                BinderC0773Mq0 mq03 = new BinderC0773Mq0(applicationContext);
                BinderC0773Mq0 mq04 = new BinderC0773Mq0(cursor);
                Parcel c3 = ah1.c();
                AbstractC4546rF1.b(c3, mq03);
                c3.writeString(str);
                c3.writeInt(i2);
                AbstractC4546rF1.b(c3, mq04);
                Parcel d3 = ah1.d(2, c3);
                vy = BinderC0773Mq0.d(d3.readStrongBinder());
                d3.recycle();
            }
            Context context2 = (Context) BinderC0773Mq0.f(vy);
            if (context2 != null) {
                return new PJ(context2);
            }
            throw new JJ("Failed to get module context", null);
        }
        throw new JJ("DynamiteLoaderV2 was not cached.", null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a3 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a4 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int i(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 180
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.PJ.i(android.content.Context, java.lang.String, boolean):int");
    }

    public static PJ j(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new PJ(context.getApplicationContext());
    }

    public static UG1 k(Context context) {
        UG1 ug1;
        synchronized (PJ.class) {
            UG1 ug12 = b;
            if (ug12 != null) {
                return ug12;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    ug1 = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof UG1) {
                        ug1 = (UG1) queryLocalInterface;
                    } else {
                        ug1 = new UG1(iBinder);
                    }
                }
                if (ug1 != null) {
                    b = ug1;
                    return ug1;
                }
            } catch (Exception e2) {
                String valueOf = String.valueOf(e2.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final IBinder b(String str) {
        try {
            return (IBinder) this.j.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new JJ(str.length() != 0 ? "Failed to instantiate module class: ".concat(str) : new String("Failed to instantiate module class: "), e2, null);
        }
    }
}
