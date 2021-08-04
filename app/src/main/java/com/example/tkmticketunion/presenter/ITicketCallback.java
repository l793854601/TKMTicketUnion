package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBaseView;
import com.example.tkmticketunion.model.domain.Ticket;

public interface ITicketCallback extends IBaseView {
    /**
     * 获取淘口令成功
     * @param ticket
     */
    void onGetTicketSuccess(Ticket ticket);
}
