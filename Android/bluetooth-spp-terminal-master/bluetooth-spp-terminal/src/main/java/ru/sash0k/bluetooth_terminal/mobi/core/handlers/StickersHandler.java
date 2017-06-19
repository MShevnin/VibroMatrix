package ru.sash0k.bluetooth_terminal.mobi.core.handlers;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.core.handlers.BaseHandler;

public class StickersHandler extends BaseHandler<TdApi.Stickers> {
    public static final int HANDLER_ID = Const.STICKERS_HANDLER_ID;

    @Override
    public TdApi.Stickers resultHandler(TdApi.TLObject object) {
        if (object.getConstructor() == TdApi.Stickers.CONSTRUCTOR) {
            return (TdApi.Stickers) object;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
