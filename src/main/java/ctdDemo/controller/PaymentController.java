package ctdDemo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ctdDemo.service.PaymentInfoField;
import ctdDemo.service.PaymentProcessingService;

@RestController
public class PaymentController {
	
	@Qualifier("PaymentProcessingServiceImpl")
	@Autowired 
	PaymentProcessingService paymentProcessingService;
	
    @RequestMapping(value = "/paymentProcess",
            method = RequestMethod.POST)
    public void paymentProcess(@RequestBody List<PaymentInfoField> paymentFormDatas) {
    	paymentProcessingService.process(paymentFormDatas);
       
    }
 
}
