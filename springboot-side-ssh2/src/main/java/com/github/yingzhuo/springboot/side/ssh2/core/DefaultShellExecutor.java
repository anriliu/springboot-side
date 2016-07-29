package com.github.yingzhuo.springboot.side.ssh2.core;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.github.yingzhuo.springboot.side.ssh2.exception.ShellException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;

public class DefaultShellExecutor implements ShellExecutor {

    private static final String[] DEFAULT_ARGS = new String[0];

    private String hostname;
    private Integer port = 22;
    private String username;
    private String password;

    @Override
    public void execute(String shellPath) {
        execute(shellPath, DEFAULT_ARGS);
    }

    @Override
    public void execute(String shellPath, String... args) {
        Validate.notBlank(shellPath);

        final StringBuilder cmds = new StringBuilder(shellPath);
        if (args != null && args.length != 0) {
            cmds.append(" ");
            cmds.append(StringUtils.join(args, " "));
        }

        Session session = null;
        Connection connection = null;
        try {
            connection = login();
            session = connection.openSession();
            session.execCommand(cmds.toString());
        } catch (IOException ex) {
            throw new ShellException(ex);
        } finally {
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }

    private Connection login() throws IOException {
        Connection connection = new Connection(hostname, port);
        connection.connect();

        if (StringUtils.isNotBlank(password)) {
            connection.authenticateWithPassword(username, password);
        } else {
            connection.authenticateWithNone(username);
        }

        return connection;
    }

//    private String processStdout(InputStream in, Charset charset) throws IOException {
//        byte[] buf = new byte[1024];
//        StringBuilder sb = new StringBuilder();
//        while (in.read(buf) != -1) {
//            sb.append(new String(buf, charset));
//        }
//        return sb.toString();
//    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
