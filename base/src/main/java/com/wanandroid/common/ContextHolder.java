package com.wanandroid.common;

import android.content.Context;

/**
 * @author TangRui
 * @description: 提供Context
 * @date:2022/2/23 10:42
 */
public class ContextHolder {
    private Context context;
    private static final Singleton<ContextHolder> INSTANCE = new Singleton<ContextHolder>() {
        @Override
        protected ContextHolder create() {
            return new ContextHolder();
        }
    };

    public static ContextHolder getInstance() {
        return INSTANCE.get();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
