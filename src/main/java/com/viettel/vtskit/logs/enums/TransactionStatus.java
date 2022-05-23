package com.viettel.vtskit.logs.enums;

public enum TransactionStatus {
    SUCCESS (0),
    FAILED (1);

    private int value;

    TransactionStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TransactionStatus resolve(String value){
        if(value == null){
            return null;
        }
        if(TransactionStatus.SUCCESS.toString().equals(value)){
            return TransactionStatus.SUCCESS;
        }else if(TransactionStatus.FAILED.toString().equals(value)){
            return TransactionStatus.FAILED;
        }else{
            return null;
        }
    }
}
