package au.gov.notify.javademo;

import au.gov.notify.NotificationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController {
    private Logger LOGGER = LoggerFactory.getLogger(SmsController.class);


    @PostMapping("/hello-world")
    public ResponseEntity<Void> helloWorld(@RequestBody HelloWorldRequest request) {
        LOGGER.info(String.format("Hello world %s aged %d", request.getName(), request.getAge()));
        return ResponseEntity.accepted().build();
    }


    @PostMapping("/send")
    public ResponseEntity<Void> sendSms(@RequestBody HelloWorldRequest request) {
        LOGGER.info(String.format("Hello world %s aged %d", request.getName(), request.getAge()));

        NotificationClient client = new NotificationClient(
                System.getenv("NOTIFY_API_KEY"),
                "https://notify-api-stg.apps.y.cld.gov.au"
                );


        try {
            Map personalisation = new HashMap<String, String>();
            personalisation.put("name", "Joe");

            client.sendSms("3c3520cb-bbf0-4261-93c2-b18ee0e27bec",
                    "0431795350",
                    personalisation,
                    "ref"
            );
        } catch (Exception e) {
            LOGGER.error("Error sending sms", e);
        }

        LOGGER.info("base url = " + client.getBaseUrl());
        return ResponseEntity.accepted().build();
    }

}
