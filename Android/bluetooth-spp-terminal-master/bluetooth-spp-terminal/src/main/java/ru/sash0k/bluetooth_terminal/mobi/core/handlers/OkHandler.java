package ru.sash0k.bluetooth_terminal.mobi.core.handlers;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

import org.drinkless.td.libcore.telegram.TdApi;

public class OkHandler extends BaseHandler<TdApi.Ok> {
    public static final int HANDLER_ID = Const.OK_HANDLER_ID;

    @Override
    public TdApi.Ok resultHandler(TdApi.TLObject object) {
        return (TdApi.Ok) object;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
