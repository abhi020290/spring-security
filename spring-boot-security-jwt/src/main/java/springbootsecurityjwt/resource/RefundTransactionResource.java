package springbootsecurityjwt.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springbootsecurityjwt.pojo.Payload;

@RestController
@RequestMapping("/api")
public class RefundTransactionResource {

    @RequestMapping(value = "/refundTransaction",method= RequestMethod.POST)
    ResponseEntity<String> handleTransaction(@RequestBody Payload payload) {
        return ResponseEntity.status(204).body("Refund Transaction Completed for Id " + payload.getOrderId());
    }
}
