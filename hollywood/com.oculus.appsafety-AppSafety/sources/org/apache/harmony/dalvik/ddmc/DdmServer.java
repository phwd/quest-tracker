package org.apache.harmony.dalvik.ddmc;

import java.util.HashMap;

public class DdmServer {
    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 2;
    private static HashMap<Integer, ChunkHandler> mHandlerMap = new HashMap<>();
    private static volatile boolean mRegistrationComplete = false;
    private static boolean mRegistrationTimedOut = false;

    private static native void nativeSendChunk(int i, byte[] bArr, int i2, int i3);

    private DdmServer() {
    }

    public static void registerHandler(int type, ChunkHandler handler) {
        if (handler != null) {
            synchronized (mHandlerMap) {
                if (mHandlerMap.get(Integer.valueOf(type)) == null) {
                    mHandlerMap.put(Integer.valueOf(type), handler);
                } else {
                    throw new RuntimeException("type " + Integer.toHexString(type) + " already registered");
                }
            }
            return;
        }
        throw new NullPointerException("handler == null");
    }

    public static ChunkHandler unregisterHandler(int type) {
        ChunkHandler remove;
        synchronized (mHandlerMap) {
            remove = mHandlerMap.remove(Integer.valueOf(type));
        }
        return remove;
    }

    public static void registrationComplete() {
        synchronized (mHandlerMap) {
            mRegistrationComplete = true;
            mHandlerMap.notifyAll();
        }
    }

    public static void sendChunk(Chunk chunk) {
        nativeSendChunk(chunk.type, chunk.data, chunk.offset, chunk.length);
    }

    private static void broadcast(int event) {
        synchronized (mHandlerMap) {
            for (ChunkHandler handler : mHandlerMap.values()) {
                if (event == 1) {
                    handler.connected();
                } else if (event == 2) {
                    handler.disconnected();
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    private static Chunk dispatch(int type, byte[] data, int offset, int length) {
        ChunkHandler handler;
        synchronized (mHandlerMap) {
            while (!mRegistrationComplete && !mRegistrationTimedOut) {
                try {
                    mHandlerMap.wait(1000);
                    if (!mRegistrationComplete) {
                        mRegistrationTimedOut = true;
                    }
                } catch (InterruptedException e) {
                }
            }
            handler = mHandlerMap.get(Integer.valueOf(type));
        }
        if (handler == null) {
            return null;
        }
        return handler.handleChunk(new Chunk(type, data, offset, length));
    }
}
