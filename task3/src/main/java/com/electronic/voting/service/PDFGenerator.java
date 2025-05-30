package com.electronic.voting.service;

import com.electronic.voting.model.Voting;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
    public File generateVotingResults(Voting voting, String outputPath) throws IOException, DocumentException {
        String fileName = outputPath + File.separator +
                voting.getTitle().replaceAll("\\s+", "_") + "_" +
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();
        document.add(new Paragraph("Voting Results: " + voting.getTitle()));
        document.add(new Paragraph("Period: " + voting.getStartDate() + " to " + voting.getEndDate()));
        // Add more details as needed
        document.close();

        return new File(fileName);
    }
}