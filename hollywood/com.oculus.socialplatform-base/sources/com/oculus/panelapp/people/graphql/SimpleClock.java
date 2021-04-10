package com.oculus.panelapp.people.graphql;

public class SimpleClock implements Clock {
    @Override // com.oculus.panelapp.people.graphql.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
