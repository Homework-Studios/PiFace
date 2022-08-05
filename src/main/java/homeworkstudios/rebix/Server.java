package homeworkstudios.rebix;

import java.net.ServerSocket;

public class Server {

    ServerSocket serverSocket;

    public Server Start(int port) {
        System.out.println("Server is starting...");


        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                while (true) {
                    new Client(serverSocket.accept());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return this;
    }



    public ServerSocket getServerSocket() {
        return serverSocket;
    }


}
