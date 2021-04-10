package retrofit;

import android.os.Build;
import android.os.Process;
import com.google.gson.Gson;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import retrofit.RestAdapter;
import retrofit.Utils;
import retrofit.android.AndroidApacheClient;
import retrofit.android.AndroidLog;
import retrofit.android.MainThreadExecutor;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/* access modifiers changed from: package-private */
public abstract class Platform {
    static final boolean HAS_RX_JAVA = hasRxJavaOnClasspath();
    private static final Platform PLATFORM = findPlatform();

    /* access modifiers changed from: package-private */
    public abstract Executor defaultCallbackExecutor();

    /* access modifiers changed from: package-private */
    public abstract Client.Provider defaultClient();

    /* access modifiers changed from: package-private */
    public abstract Converter defaultConverter();

    /* access modifiers changed from: package-private */
    public abstract Executor defaultHttpExecutor();

    /* access modifiers changed from: package-private */
    public abstract RestAdapter.Log defaultLog();

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException unused) {
        }
        if (System.getProperty("com.google.appengine.runtime.version") != null) {
            return new AppEngine();
        }
        return new Base();
    }

    /* access modifiers changed from: private */
    public static class Base extends Platform {
        private Base() {
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Converter defaultConverter() {
            return new GsonConverter(new Gson());
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Client.Provider defaultClient() {
            final Client client;
            if (Platform.hasOkHttpOnClasspath()) {
                client = OkClientInstantiator.instantiate();
            } else {
                client = new UrlConnectionClient();
            }
            return new Client.Provider() {
                /* class retrofit.Platform.Base.AnonymousClass1 */

                @Override // retrofit.client.Client.Provider
                public Client get() {
                    return client;
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Executor defaultHttpExecutor() {
            return Executors.newCachedThreadPool(new ThreadFactory() {
                /* class retrofit.Platform.Base.AnonymousClass2 */

                public Thread newThread(final Runnable runnable) {
                    return new Thread(new Runnable() {
                        /* class retrofit.Platform.Base.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            Thread.currentThread().setPriority(1);
                            runnable.run();
                        }
                    }, "Retrofit-Idle");
                }
            });
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Executor defaultCallbackExecutor() {
            return new Utils.SynchronousExecutor();
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public RestAdapter.Log defaultLog() {
            return new RestAdapter.Log() {
                /* class retrofit.Platform.Base.AnonymousClass3 */

                @Override // retrofit.RestAdapter.Log
                public void log(String str) {
                    System.out.println(str);
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public static class Android extends Platform {
        private Android() {
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Converter defaultConverter() {
            return new GsonConverter(new Gson());
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Client.Provider defaultClient() {
            final Client client;
            if (Platform.hasOkHttpOnClasspath()) {
                client = OkClientInstantiator.instantiate();
            } else if (Build.VERSION.SDK_INT < 9) {
                client = new AndroidApacheClient();
            } else {
                client = new UrlConnectionClient();
            }
            return new Client.Provider() {
                /* class retrofit.Platform.Android.AnonymousClass1 */

                @Override // retrofit.client.Client.Provider
                public Client get() {
                    return client;
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Executor defaultHttpExecutor() {
            return Executors.newCachedThreadPool(new ThreadFactory() {
                /* class retrofit.Platform.Android.AnonymousClass2 */

                public Thread newThread(final Runnable runnable) {
                    return new Thread(new Runnable() {
                        /* class retrofit.Platform.Android.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            Process.setThreadPriority(10);
                            runnable.run();
                        }
                    }, "Retrofit-Idle");
                }
            });
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform
        public RestAdapter.Log defaultLog() {
            return new AndroidLog("Retrofit");
        }
    }

    /* access modifiers changed from: private */
    public static class AppEngine extends Base {
        private AppEngine() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // retrofit.Platform.Base, retrofit.Platform
        public Client.Provider defaultClient() {
            final UrlFetchClient urlFetchClient = new UrlFetchClient();
            return new Client.Provider() {
                /* class retrofit.Platform.AppEngine.AnonymousClass1 */

                @Override // retrofit.client.Client.Provider
                public Client get() {
                    return urlFetchClient;
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public static boolean hasOkHttpOnClasspath() {
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static class OkClientInstantiator {
        private OkClientInstantiator() {
        }

        static Client instantiate() {
            return new OkClient();
        }
    }

    private static boolean hasRxJavaOnClasspath() {
        try {
            Class.forName("rx.Observable");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
