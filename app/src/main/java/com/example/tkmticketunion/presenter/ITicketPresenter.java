package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBasePresenter;

public interface ITicketPresenter extends IBasePresenter<ITicketCallback> {

    /**
     * 生成淘口令
     * @param title
     * @param url
     */
    void getTicket(String title, String url);
}
