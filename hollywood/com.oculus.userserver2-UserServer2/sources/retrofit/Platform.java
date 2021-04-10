package retrofit;

import X.C0246hr;
import android.os.Process;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import retrofit.RestAdapter;
import retrofit.Utils;
import retrofit.android.AndroidLog;
import retrofit.android.MainThreadExecutor;
import retrofit.appengine.UrlFetchClient;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

public abstract class Platform {
    public static final boolean HAS_RX_JAVA = hasRxJavaOnClasspath();
    public static final Platform PLATFORM = findPlatform();

    public static class Android extends Platform {
        @Override // retrofit.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override // retrofit.Platform
        public Converter defaultConverter() {
            return new GsonConverter(new C0246hr());
        }

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
                    }, RestAdapter.IDLE_THREAD_NAME);
                }
            });
        }

        @Override // retrofit.Platform
        public RestAdapter.Log defaultLog() {
            return new AndroidLog("Retrofit");
        }

        @Override // retrofit.Platform
        public Client.Provider defaultClient() {
            final Client urlConnectionClient;
            if (Platform.hasOkHttpOnClasspath()) {
                urlConnectionClient = new OkClient();
            } else {
                urlConnectionClient = new UrlConnectionClient();
            }
            return new Client.Provider() {
                /* class retrofit.Platform.Android.AnonymousClass1 */

                @Override // retrofit.client.Client.Provider
                public Client get() {
                    return urlConnectionClient;
                }
            };
        }

        public Android() {
        }

        public /* synthetic */ Android(AnonymousClass1 r1) {
        }
    }

    public static class AppEngine extends Base {
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

        public AppEngine() {
        }

        public /* synthetic */ AppEngine(AnonymousClass1 r1) {
        }
    }

    public static class Base extends Platform {
        @Override // retrofit.Platform
        public Executor defaultCallbackExecutor() {
            return new Utils.SynchronousExecutor();
        }

        @Override // retrofit.Platform
        public Converter defaultConverter() {
            return new GsonConverter(new C0246hr());
        }

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
                    }, RestAdapter.IDLE_THREAD_NAME);
                }
            });
        }

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

        @Override // retrofit.Platform
        public Client.Provider defaultClient() {
            final Client urlConnectionClient;
            if (Platform.hasOkHttpOnClasspath()) {
                urlConnectionClient = new OkClient();
            } else {
                urlConnectionClient = new UrlConnectionClient();
            }
            return new Client.Provider() {
                /* class retrofit.Platform.Base.AnonymousClass1 */

                @Override // retrofit.client.Client.Provider
                public Client get() {
                    return urlConnectionClient;
                }
            };
        }

        public Base() {
        }

        public /* synthetic */ Base(AnonymousClass1 r1) {
        }
    }

    public static class OkClientInstantiator {
        public static Client instantiate() {
            return new OkClient();
        }
    }

    public abstract Executor defaultCallbackExecutor();

    public abstract Client.Provider defaultClient();

    public abstract Converter defaultConverter();

    public abstract Executor defaultHttpExecutor();

    public abstract RestAdapter.Log defaultLog();

    public static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            return new Android();
        } catch (ClassNotFoundException unused) {
            if (System.getProperty("com.google.appengine.runtime.version") != null) {
                return new AppEngine();
            }
            return new Base();
        }
    }

    public static boolean hasOkHttpOnClasspath() {
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean hasRxJavaOnClasspath() {
        try {
            Class.forName("rx.Observable");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static Platform get() {
        return PLATFORM;
    }
}
