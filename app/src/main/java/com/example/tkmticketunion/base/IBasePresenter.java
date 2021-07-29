package com.example.tkmticketunion.base;

public interface IBasePresenter<T> {
    /**
     * presenter绑定view
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * presenter解绑view
     */
    void unregisterViewCallback();
}
