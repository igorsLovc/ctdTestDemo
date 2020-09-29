package ctdDemo.service;

import java.util.List;

import ctdDemo.model.PaymentInfoField;


public interface PaymentProcessingService {

	void process(List<PaymentInfoField> paymentFormDatas);

}
