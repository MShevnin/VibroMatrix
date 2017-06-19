package ru.sash0k.bluetooth_terminal.mobi.core.handlers;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;

public class UserFullHandler extends BaseHandler<TdApi.UserFull> {
    public static final int HANDLER_ID = Const.USER_FULL_HANDLER_ID;

    @Override
    public TdApi.UserFull resultHandler(TdApi.TLObject object) {
        return (TdApi.UserFull) object;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
