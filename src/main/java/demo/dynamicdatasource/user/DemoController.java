package demo.dynamicdatasource.user;

import demo.dynamicdatasource.util.DataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public void hello(String username, @RequestParam("datasourcename") String dataSourceName) {
        DataSourceHolder.setContextDataSourceName(dataSourceName);
        userService.modifyUser(username);
    }
}
