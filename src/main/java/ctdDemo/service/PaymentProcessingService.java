package ctdDemo.service;

import java.util.List;

import javax.ws.rs.core.Response;

import ctdDemo.model.PaymentInfoField;


public interface PaymentProcessingService {

	void process(List<PaymentInfoField> paymentFormDatas) throws Exception;

}
