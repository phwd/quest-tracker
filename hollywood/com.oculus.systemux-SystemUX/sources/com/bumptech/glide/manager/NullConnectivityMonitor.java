package com.bumptech.glide.manager;

class NullConnectivityMonitor implements ConnectivityMonitor {
    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    NullConnectivityMonitor() {
    }
}
