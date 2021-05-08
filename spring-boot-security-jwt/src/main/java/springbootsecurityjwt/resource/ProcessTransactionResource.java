package springbootsecurityjwt.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springbootsecurityjwt.pojo.Payload;

@RestController
@RequestMapping("/api")
public class ProcessTransactionResource {

    @RequestMapping(value = "/processTransaction",method= RequestMethod.POST)
    ResponseEntity<String> handleTransaction(@RequestBody Payload payload){
        return ResponseEntity.status(201).body("Process Transaction Completed for Id "+payload.getOrderId());
    }
}
