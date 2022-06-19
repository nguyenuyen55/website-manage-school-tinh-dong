package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class FilesExporter {
    public void setReponseHeader(HttpServletResponse response, String contentType,
                                 String extenison, String prifex) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String file = prifex + timeStamp + extenison;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file;
        response.setHeader(headerKey, headerValue);
    }

    public void exportToPDF(List<HocSinh> hocSinhList, HttpServletResponse response) throws DocumentException, IOException {
        setReponseHeader(response, "application/pdf", ".pdf", "DanhSachHS_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("Danh Sách Hoc Sinh", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(105f);
        table.setSpacingBefore(10);

        writeHocSinhHeader(table);
        writeHocSinhData(table,hocSinhList);
        document.add(table);
        document.close();
    }

    private void writeHocSinhData(PdfPTable table, List<HocSinh> hocSinhList) {
        for (HocSinh hocSinh : hocSinhList) {
            table.addCell(hocSinh.getId());
            table.addCell(hocSinh.getTen());
            table.addCell(hocSinh.getNgaySinh());
            table.addCell(hocSinh.getSdtBoMe());
            table.addCell(hocSinh.getDiaChi());
        }
    }


    private void writeHocSinhHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        Font font = FontFactory.getFont("Times New Roman",FontFactory.COURIER_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Mã HS", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Họ và tên", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Ngày sinh", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Số điện thoại", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Địa chỉ", font));
        table.addCell(cell);

    }
}
