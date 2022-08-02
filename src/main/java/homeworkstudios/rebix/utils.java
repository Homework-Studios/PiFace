package homeworkstudios.rebix;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class utils {
    public static List<String> convertURLToList( String url) {
        List<String> list = new ArrayList<>();
        try {
            for(Scanner scanner = new Scanner(new URL(url).openStream()); scanner.hasNext(); )
                list.add(scanner.nextLine());
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
