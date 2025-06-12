package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.entities.Patient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelExportService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public byte[] criarPlanilhaXLSX(List<Anamnese> anamneses, List<Patient> pacientes) throws IOException {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            CellStyle numberStyle = workbook.createCellStyle();
            numberStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));

            criarAba(workbook, "Relatório de Pacientes",
                    new String[]{"Nome", "CPF", "E-mail", "Telefone", "Data de Nascimento"},
                    pacientes, (row, paciente) -> {
                        String dataNascimento = (paciente.getBirthDate() != null) ? paciente.getBirthDate().format(DATE_FORMATTER) : "N/A";

                        row.createCell(0).setCellValue(paciente.getName());
                        row.createCell(1).setCellValue(paciente.getCpf());
                        row.createCell(2).setCellValue(paciente.getEmail());
                        row.createCell(3).setCellValue(paciente.getPhoneNumber());
                        row.createCell(4).setCellValue(dataNascimento);
                    });

            criarAba(workbook, "Relatório de Anamnese",
                    new String[]{"Paciente", "Data da Anamnese", "Queixas Principais",
                            "Histórico Médico", "Observações", "Peso", "Altura",
                            "Circ. Cintura", "Circ. Quadril", "Perc. Gordura",
                            "Massa Muscular", "IMC", "Relação Cintura/Quadril"},
                    anamneses, (row, item) -> {
                        String nomePaciente = (item.getPatient() != null) ? item.getPatient().getName() : "N/A";
                        String dataAnamnese = (item.getAnamnesisDate() != null) ? item.getAnamnesisDate().format(DATE_FORMATTER) : "N/A";

                        row.createCell(0).setCellValue(nomePaciente);
                        row.createCell(1).setCellValue(dataAnamnese);
                        row.createCell(2).setCellValue(item.getMainComplaints());
                        row.createCell(3).setCellValue(item.getMedicalHistory());
                        row.createCell(4).setCellValue(item.getObservations());

                        createNumericCell(row, 5, item.getWeight(), numberStyle);
                        createNumericCell(row, 6, item.getHeight(), numberStyle);
                        createNumericCell(row, 7, item.getWaistCircumference(), numberStyle);
                        createNumericCell(row, 8, item.getHipCircumference(), numberStyle);
                        createNumericCell(row, 9, item.getBodyFatPercentage(), numberStyle);
                        createNumericCell(row, 10, item.getMuscleMass(), numberStyle);

                        double imcCorrigido = item.getBodyMassIndex() * 10000;
                        createNumericCell(row, 11, imcCorrigido, numberStyle);

                        createNumericCell(row, 12, item.getWaistHipRatio(), numberStyle);
                    });

            workbook.write(out);
            return out.toByteArray();
        }
    }

    /**
     * Método auxiliar para criar uma célula com valor numérico e aplicar um estilo.
     */
    private void createNumericCell(Row row, int column, double value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private <T> void criarAba(SXSSFWorkbook workbook, String sheetName, String[] headers, List<T> data, RowMapper<T> rowMapper) {
        SXSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.trackAllColumnsForAutoSizing();

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (T item : data) {
            Row row = sheet.createRow(rowNum++);
            rowMapper.mapRow(row, item);
        }

        for (int i = 0; i < headers.length; i++){
            sheet.autoSizeColumn(i);
        }
    }

    @FunctionalInterface
    interface RowMapper<T> {
        void mapRow(Row row, T item);
    }
}