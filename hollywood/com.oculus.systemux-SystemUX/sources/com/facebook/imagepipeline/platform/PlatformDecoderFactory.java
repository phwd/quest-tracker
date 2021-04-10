package com.facebook.imagepipeline.platform;

import android.os.Build;
import androidx.core.util.Pools;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import java.lang.reflect.InvocationTargetException;

public class PlatformDecoderFactory {
    public static PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
            return new OreoDecoder(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads, new Pools.SynchronizedPool(flexByteArrayPoolMaxNumThreads));
        } else if (Build.VERSION.SDK_INT >= 21 || !NativeCodeSetup.getUseNativeCode()) {
            int flexByteArrayPoolMaxNumThreads2 = poolFactory.getFlexByteArrayPoolMaxNumThreads();
            return new ArtDecoder(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads2, new Pools.SynchronizedPool(flexByteArrayPoolMaxNumThreads2));
        } else {
            if (z) {
                try {
                    if (Build.VERSION.SDK_INT < 19) {
                        return (PlatformDecoder) Class.forName("com.facebook.imagepipeline.platform.GingerbreadPurgeableDecoder").getConstructor(new Class[0]).newInstance(new Object[0]);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Wrong Native code setup, reflection failed.", e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException("Wrong Native code setup, reflection failed.", e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException("Wrong Native code setup, reflection failed.", e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Wrong Native code setup, reflection failed.", e4);
                } catch (InstantiationException e5) {
                    throw new RuntimeException("Wrong Native code setup, reflection failed.", e5);
                }
            }
            return (PlatformDecoder) Class.forName("com.facebook.imagepipeline.platform.KitKatPurgeableDecoder").getConstructor(FlexByteArrayPool.class).newInstance(poolFactory.getFlexByteArrayPool());
        }
    }
}
