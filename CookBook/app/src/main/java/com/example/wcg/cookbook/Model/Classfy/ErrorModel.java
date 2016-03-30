package com.example.wcg.cookbook.Model.Classfy;

import java.security.PublicKey;

/**
 * Created by wcg on 16/3/8.
 */
public class ErrorModel {

    private String errNum;
    private String errMsg;


    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static ErrorModel requestFail(String errorMsg){
        ErrorModel errorModel  =new ErrorModel();
        errorModel.errMsg = errorMsg;
        errorModel.errNum = "0";
        return errorModel;
    }

}
