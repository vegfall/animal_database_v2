package userpackage;

import java.util.Map;

public class UserRepository {
    private static Map<String, String> users = Map.of(
            "User1", "Password1",
            "User2", "Password2"
    );

    public static Map<String, String> getUsers() {
        return users;
    }
}
