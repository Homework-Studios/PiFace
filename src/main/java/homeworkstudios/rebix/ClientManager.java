package homeworkstudios.rebix;


import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ClientManager {

    public static HashMap<String, Client> clients = new HashMap<>();
    public static void initNewClient(String ip, String password, String name, String clientName) {

        String command = utils.convertURLToString("https://raw.githubusercontent.com/Homework-Studios/PiFace/main/python/commandArray");

        System.out.println("command: " + command);
        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(name, ip, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel sftpChannel = session.openChannel("sftp");
            sftpChannel.connect();
            ChannelSftp sftp = (ChannelSftp) sftpChannel;
            sftp.put(PiFace.host + "%%%%%" + PiFace.port + "%%%%%" + PiFace.AUTHENTFICATION_KEY, "/home/pi/information.txt" + "%%%%%" + clientName);
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("rm PiFace.py \n wget https://raw.githubusercontent.com/Homework-Studios/PiFace/main/python/PiFace.py \n python3 PiFace.py");
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
        } catch (SftpException e) {
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
