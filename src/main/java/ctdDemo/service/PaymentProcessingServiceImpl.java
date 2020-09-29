package ctdDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("PaymentProcessingServiceImpl")
public class PaymentProcessingServiceImpl implements PaymentProcessingService{

	@Qualifier("EmailServiceImpl")
	@Autowired 
	EmailService emailService;
	
	@Autowired 
	PdfReportService<PaymentInfoField> pdfReportService;
	
	@Override
	public void process(List<PaymentInfoField> paymentFormDatas) {
		 try {
			 String source = Provider.getProvider().getProperty("pdf.source");
			 pdfReportService.render(source, "fillForm.pdf", paymentFormDatas);
			 emailService.sendMessageWithAttachment("igor26.78@gmail.com", "test", "tt", "fillForm.pdf");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
