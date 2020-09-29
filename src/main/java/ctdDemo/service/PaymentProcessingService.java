package ctdDemo.service;

import java.util.List;

import ctdDemo.service.PaymentInfoField;

public interface PaymentProcessingService {

	void process(List<PaymentInfoField> paymentFormDatas);

}
