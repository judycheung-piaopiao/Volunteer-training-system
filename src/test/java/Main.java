import com.entity.User;
import com.service.UserService;
import org.junit.Test;

public class Main {
    @Test
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("Admin");
        user.setUserlevel("3");
        user.setPassword("Password");
        System.out.println(UserService.Userlogin(user));
    }
}
