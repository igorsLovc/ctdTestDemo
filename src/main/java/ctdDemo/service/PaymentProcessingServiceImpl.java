package ctdDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ctdDemo.model.PaymentInfoField;

@Component("PaymentProcessingServiceImpl")
public class PaymentProcessingServiceImpl implements PaymentProcessingService{

	@Qualifier("EmailServiceImpl")
	@Autowired 
	EmailService emailService;
	
	@Autowired 
	PdfReportService<PaymentInfoField> pdfReportService;
	
	@Value("${pdf.source}")
	private String pdfSource;
	
	@Override
	public void process(List<PaymentInfoField> paymentFormDatas) {
		 try {

			 pdfReportService.render(pdfSource, "fillForm.pdf", paymentFormDatas);
			 emailService.sendMessageWithAttachment("igor26.78@gmail.com", "Payment information", "You received new payment with attachment", "fillForm.pdf");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
