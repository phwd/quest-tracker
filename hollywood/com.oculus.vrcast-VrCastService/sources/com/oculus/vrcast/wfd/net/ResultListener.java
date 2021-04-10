package com.oculus.vrcast.wfd.net;

public interface ResultListener<T, E> {
    public static final ResultListener NullListener = new ResultListener() {
        /* class com.oculus.vrcast.wfd.net.ResultListener.AnonymousClass1 */

        @Override // com.oculus.vrcast.wfd.net.ResultListener
        public void onFailure(Object obj) {
        }

        @Override // com.oculus.vrcast.wfd.net.ResultListener
        public void onSuccess(Object obj) {
        }
    };

    void onFailure(E e);

    void onSuccess(T t);
}
