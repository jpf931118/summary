import com.jipf.app.controller.UserController;
import com.jipf.ioc.context.IBeanContext;
import com.jipf.ioc.context.impl.BeanContext;

/**
 * 项目启动入口
 */
public class Application {

    public static void main(String[] args) {
        IBeanContext beanContext = new BeanContext("application");
        UserController userController = (UserController) beanContext.getBean("userController");
        userController.print("...biubiubiu...");
    }
}
