package ru.sash0k.bluetooth_terminal.mobi.core.handlers;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;
import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;

public class UserHandler extends BaseHandler<TdApi.User> {

    public static final int HANDLER_ID = Const.USER_HANDLER_ID;

    @Override
    public TdApi.User resultHandler(TdApi.TLObject object) {
        if (object.getConstructor() == TdApi.User.CONSTRUCTOR) {
            return (TdApi.User) object;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
