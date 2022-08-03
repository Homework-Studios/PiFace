package homeworkstudios.rebix;

public class PiFace {
    public static String CLIENT_INFORMATION_STRING = "";
    public static String AUTHENTFICATION_KEY = "16532";
    public static void main(String[] args) {
        //These are only placeholders for the actual values.
        //You will need to replace these with the actual values.
        //Later you will be able to get the values from preseted files.
        CLIENT_INFORMATION_STRING = "192.192.168.137.1:42217";
        initNewClient("192.168.178.39","pi","pi");

    }

    public static void initNewClient(String ip, String password, String name) {
        ClientManager.initNewClient(ip, password, name);
    }

    public static void init() {

    }
}