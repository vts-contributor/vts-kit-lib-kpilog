package com.viettel.vtskit.logs.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryCallback {
    void bindParameters(PreparedStatement statement) throws SQLException;
}
