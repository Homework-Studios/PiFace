package homeworkstudios.rebix;


import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class ClientManager {

    public static void initNewClient(String ip, String password, String name) {

        String command = utils.convertURLToString("https://raw.githubusercontent.com/Homework-Studios/PiFace/main/python/commandArray");

        System.out.println("command: " + command);
        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(name, ip, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("wget https://raw.githubusercontent.com/Homework-Studios/PiFace/main/python/PiFace.py \n python3 PiFace.py");
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } catch (JSchException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
