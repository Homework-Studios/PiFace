package homeworkstudios.rebix;

public class PiFace {

    public static void main(String[] args) {
        initNewClient("192.168.178.39","pi","pi");
    }

    public static void initNewClient(String ip, String password, String name) {
        ClientManager.initNewClient(ip, password, name);
    }

    public static void init() {

    }
}