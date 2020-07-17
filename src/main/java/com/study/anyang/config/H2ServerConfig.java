package com.study.anyang.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Profile("local")
@Configuration
public class H2ServerConfig {

    public Server H2ServerConfig() throws SQLException {
        return Server.createTcpServer().start();
    }

}
