package com.hiranwj.pdfgenerator.controller;

import com.hiranwj.pdfgenerator.service.PdfGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PdfExportController {

    private final PdfGeneratorService pdfGeneratorService;

    public PdfExportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    public void generatePdf(HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dataFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dataFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }
}
