package ctdDemo.service;

import java.util.List;

import javax.ws.rs.core.Response;

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
	
	@Value("${mail.recipient}")
	private String recipient;
	
	@Override
	public void process(List<PaymentInfoField> paymentFormDatas) throws Exception {
		pdfReportService.render(pdfSource, "fillForm.pdf", paymentFormDatas);
		emailService.sendMessageWithAttachment(recipient, "Payment information", "You received new payment with attachment", "fillForm.pdf");
		
	}

}
