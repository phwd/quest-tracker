package com.squareup.okhttp.internal;

import com.squareup.okhttp.Route;
import java.util.LinkedHashSet;
import java.util.Set;

public final class RouteDatabase {
    public final Set<Route> failedRoutes = new LinkedHashSet();

    public synchronized void connected(Route route) {
        this.failedRoutes.remove(route);
    }

    public synchronized void failed(Route route) {
        this.failedRoutes.add(route);
    }

    public synchronized int failedRoutesCount() {
        return this.failedRoutes.size();
    }

    public synchronized boolean shouldPostpone(Route route) {
        return this.failedRoutes.contains(route);
    }
}
