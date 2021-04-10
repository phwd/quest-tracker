package com.oculus.auth.handler;

import bolts.Task;

public interface LoginHandler {
    Task<Void> afterLoginAsync();
}
