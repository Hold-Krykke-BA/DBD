package holdkrykke.rest;

import holdkrykke.dataLayer.IDataController;
import holdkrykke.dataLayer.controller.DataControllerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ExamSpringAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExamSpringAppApplication.class, args);
    }

    IDataController datactr = DataControllerImpl.getInstance();

    @GetMapping("/hello")
    public List<String> sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        datactr.getUserIDs();
        return datactr.getUserIDs();
    }

}
