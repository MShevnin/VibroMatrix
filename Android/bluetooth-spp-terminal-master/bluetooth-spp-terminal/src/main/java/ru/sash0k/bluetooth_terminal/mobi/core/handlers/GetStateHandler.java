package ru.sash0k.bluetooth_terminal.mobi.core.handlers;


import org.drinkless.td.libcore.telegram.TdApi;

import ru.sash0k.bluetooth_terminal.mobi.model.Const;
import ru.sash0k.bluetooth_terminal.mobi.model.Enums;

public class GetStateHandler extends BaseHandler<Enums.StatesEnum> {
    public static final int HANDLER_ID = Const.AUTH_HANDLER_ID;

    @Override
    public Enums.StatesEnum resultHandler(TdApi.TLObject object) {

        if (object.getConstructor() == TdApi.AuthStateOk.CONSTRUCTOR) {
            return Enums.StatesEnum.OK;
        }
        if (object.getConstructor() == TdApi.AuthStateWaitSetCode.CONSTRUCTOR) {
            return Enums.StatesEnum.WaitSetCode;
        }
        if (object.getConstructor() == TdApi.AuthStateWaitSetName.CONSTRUCTOR) {
            return Enums.StatesEnum.WaitSetName;
        }
        if (object.getConstructor() == TdApi.AuthStateWaitSetPhoneNumber.CONSTRUCTOR) {
            return Enums.StatesEnum.WaitSetPhoneNumber;
        }
        return null;
    }

    @Override
    public int getHandlerId() {
        return HANDLER_ID;
    }
}
