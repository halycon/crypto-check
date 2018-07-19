package cryptocheck.common.model.service;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = -417598463582601853L;

    public boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
