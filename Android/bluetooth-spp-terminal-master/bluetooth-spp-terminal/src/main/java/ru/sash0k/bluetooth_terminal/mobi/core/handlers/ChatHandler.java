package ru.sash0k.bluetooth_terminal.mobi.core.handlers;


import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;
import ru.sash0k.bluetooth_terminal.mobi.model.Const;

public class ChatHandler extends BaseHandler<TdApi.Chat> {
    public static final int HANDLER_ID = Const.CHAT_HANDLER_ID;

    @Override
    public TdApi.Chat resultHandler(TdApi.TLObject object) {
        if (object.getConstructor() == TdApi.Chat.CONSTRUCTOR) {
            return (TdApi.Chat) object;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
