package ru.sash0k.bluetooth_terminal.mobi.core.handlers;


import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;
import ru.sash0k.bluetooth_terminal.mobi.model.Const;

public class ChatsHandler extends BaseHandler<TdApi.Chats> {
    public static final int HANDLER_ID = Const.CHATS_HANDLER_ID;
    @Override
    public TdApi.Chats resultHandler(TdApi.TLObject object) {
        if (object.getConstructor() == TdApi.Chats.CONSTRUCTOR) {
            return (TdApi.Chats) object;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
