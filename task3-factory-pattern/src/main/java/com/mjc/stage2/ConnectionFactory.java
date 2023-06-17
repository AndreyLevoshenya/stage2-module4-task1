package com.mjc.stage2;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection();
}
