package ctdDemo.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import ctdDemo.model.Bank;
import ctdDemo.model.PaymentInfoField;



@Component 
public class Initialization implements CommandLineRunner{

	
	private final BankRepository bankRepository;

	private final PaymentinfoFieldRepo paymentinfoFieldRepo;

	@Autowired 
	public Initialization(BankRepository bankRepository,  PaymentinfoFieldRepo paymentinfoFieldRepo) {
		this.bankRepository = bankRepository;
		
		this.paymentinfoFieldRepo = paymentinfoFieldRepo;
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		Bank bank = new Bank("AS \"Citadele banka\"", "Reģ. nr. 40103303559" , "Republikas laukums 2A, Rīga, LV-1010, Latvija");
		this.bankRepository.save(bank);
		try(BufferedReader file = new BufferedReader(new FileReader("fieldsMap.csv"))
		){
			String line = file.readLine();
			while(line!=null) {
				String[] f = line.split(";");
				PaymentInfoField paymentInfoField = new PaymentInfoField(f[0]+"",f[1],null,f[2],f[3]);
	    		this.paymentinfoFieldRepo.save(paymentInfoField);
				line = file.readLine();
			}
			
		}
	}

}
