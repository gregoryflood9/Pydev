package org.python.pydev.core;

public class BaseModuleRequest implements IModuleRequestState {

    private boolean acceptTypeshed;

    public BaseModuleRequest(boolean acceptTypeshed) {
        this.acceptTypeshed = acceptTypeshed;
    }

    @Override
    public boolean getAcceptTypeshed() {
        return acceptTypeshed;
    }

}
