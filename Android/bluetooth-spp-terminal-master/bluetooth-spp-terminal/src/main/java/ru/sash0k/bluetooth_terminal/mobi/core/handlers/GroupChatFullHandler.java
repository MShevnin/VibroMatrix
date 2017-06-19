package ru.sash0k.bluetooth_terminal.mobi.core.handlers;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;

public class GroupChatFullHandler extends BaseHandler<TdApi.GroupChatFull> {
    public static final int HANDLER_ID = Const.GROUP_CHAT_FULL_HANDLER_ID;

    @Override
    public TdApi.GroupChatFull resultHandler(TdApi.TLObject object) {
        return (TdApi.GroupChatFull) object;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
