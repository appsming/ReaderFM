package com.wxkj.readerfm.beans;

import java.io.Serializable;

/**
 * Created by taosong on 17/8/4.
 */

public class UserInfo  implements Serializable {

    private boolean login;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
