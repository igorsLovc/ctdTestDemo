package ctdDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class PaymentInfoField implements ctdDemo.service.PaymentInfoField{
	private @Id @GeneratedValue Long id;
	private String field;
	private String fieldName;
	private String value;
	private String type;	
	
	@SuppressWarnings("unused")
	private PaymentInfoField() {
		
	}
	public PaymentInfoField(String field,String fieldName, String value) {
		super();
		this.field = field;
		this.fieldName = fieldName;
		this.value = value;
	}
	
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
