package homeworkstudios.rebix;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utils {
    public static String convertURLToString(String url) {
        List<String> list = new ArrayList<>();
        try {
            for(Scanner scanner = new Scanner(new URL(url).openStream()); scanner.hasNext(); )
                list.add(scanner.nextLine());
            return list.get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
