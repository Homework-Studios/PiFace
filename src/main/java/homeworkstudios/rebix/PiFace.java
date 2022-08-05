package homeworkstudios.rebix;

public class PiFace {
    public static Server server;

    public static int port = 42219;
    public static String host = "localhost";
    public static String AUTHENTFICATION_KEY = "16532";
    public static void main(String[] args) {
        //These are only placeholders for the actual values.
        //You will need to replace these with the actual values.
        //Later you will be able to get the values from preseted files.
        initNewClient("192.168.178.39","pi","pi");

    }

    public static void initNewClient(String ip, String password, String name) {
        ClientManager.initNewClient(ip, password, name);
    }

    public static void startServer() {
        server = new Server().Start(42219);
    }
    public static void init() {

    }
}