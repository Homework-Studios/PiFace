package homeworkstudios.rebix;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Client {

    public static boolean Authenticated = false;
    Socket socket;
    public Client(Socket socket) {
        this.socket = socket;

        new Thread(() -> {
            try {
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = reader.readLine();
                    if (line != null)
                        onMessageRecieved(line);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void onMessageRecieved(String m) {
        String message = new String(m.getBytes(), StandardCharsets.UTF_8);
        System.out.println("Message Recieved: " + message);
        if (!Authenticated) {
            if (Objects.equals(message.split("%%%%%")[0], PiFace.AUTHENTFICATION_KEY)) {
                Authenticated = true;
                ClientManager.addClient(message.split("%%%%%")[1], this);
            }
            else {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void sendMessage(String message) {
        String newMessage = new String(message.getBytes(StandardCharsets.UTF_8));
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
