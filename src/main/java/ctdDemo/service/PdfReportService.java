package ctdDemo.service;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;


import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PdfReportService<S extends PaymentInfoField> {
	
    public void render(String src, String dest, List<S> payments) throws Exception {
    	PdfReader reader = new PdfReader(src);
        PdfDocument pdfDoc = new PdfDocument(reader, new PdfWriter(dest));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
       
        for(S payment: payments) {
        	form.getField(payment.getField()).setValue(payment.getValue()+"");
        }
        
        
        pdfDoc.close();
    }

}
