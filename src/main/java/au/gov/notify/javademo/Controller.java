package au.gov.notify.javademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private Logger LOGGER = LoggerFactory.getLogger(Controller.class);


    @PostMapping("/hello-world")
    public ResponseEntity<Void> helloWorld(@RequestBody HelloWorldRequest request) {
        LOGGER.info(String.format("Hello world %s aged %d", request.getName(), request.getAge()));
        return ResponseEntity.accepted().build();
    }
}
