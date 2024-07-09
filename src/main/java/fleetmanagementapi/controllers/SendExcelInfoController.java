package fleetmanagementapi.controllers;

import fleetmanagementapi.dto.TrajectoriesDto;
import fleetmanagementapi.service.TrajectoriesService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/export")
public class SendExcelInfoController {

    @Autowired
    private TrajectoriesService trajectoriesService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportToExcel(
            @RequestParam("taxiId") Integer taxiId,
            @RequestParam("date") String date,
            @RequestParam("email") String email
    ) {
        // Crear objeto Pageable para paginación
        Pageable pageable = PageRequest.of(0, 10);

        // Lógica para generar el archivo Excel y enviar por correo electrónico
        byte[] excelFile = generateExcelFile(taxiId, date, pageable);
        // Enviar el correo electrónico con el archivo adjunto
        sendEmailWithAttachment(email, excelFile);
        return ResponseEntity.ok().body(excelFile); // Retorna el archivo Excel generado
    }

    private byte[] generateExcelFile(Integer taxiId, String date, Pageable pageable) {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        // Crear una hoja dentro del libro
        Sheet sheet = workbook.createSheet("Ubicaciones de Vehículo " + taxiId);

        // Obtener los datos de ubicaciones según el taxiId y la fecha
        List<TrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectoriesByIdDate(taxiId, date, pageable.getPageNumber(), pageable.getPageSize());

        // Encabezados de las columnas
        String[] headers = {"ID", "Placa", "Latitud", "Longitud", "Fecha y Hora"};

        // Establecer estilo para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);


        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (TrajectoriesDto trajectory : trajectoriesList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(trajectory.getId());
            row.createCell(1).setCellValue(trajectory.getPlate());
            row.createCell(2).setCellValue(trajectory.getLatitude());
            row.createCell(3).setCellValue(trajectory.getLongitude());
            row.createCell(4).setCellValue(trajectory.getDate().toString());
        }


        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Convertir el libro de Excel a un array de bytes
        byte[] excelBytes;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            excelBytes = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            excelBytes = new byte[0]; // Manejo de error: retorno de un array vacío en caso de error
        }

        return excelBytes;
    }

    private void sendEmailWithAttachment(String email, byte[] excelFile) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Trajectories Excel Report");
            helper.setText("Attached is the Excel file with trajectories data.");

            // Adjuntar el archivo Excel
            helper.addAttachment("trajectories.xlsx", new ByteArrayResource(excelFile));

            javaMailSender.send(message);
            System.out.println("Email sent to " + email + " with Excel attachment.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email with Excel attachment to " + email + ": " + e.getMessage());
        }
    }
}
