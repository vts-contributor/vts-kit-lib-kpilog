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
}
