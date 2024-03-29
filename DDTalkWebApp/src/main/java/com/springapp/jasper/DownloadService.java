package com.springapp.jasper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {

    public static final String TEMPLATE = "/users.jrxml";

    @Autowired
    private JasperDatasourceService datasource;

    @Autowired
    private ExporterService exporter;


    public void download(String type, HttpServletResponse response) {

        try {
            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("Title", "User Report");

            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(TEMPLATE);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource.getDataSource());

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 7. Export report
            exporter.export(type, jp, response, baos);

            // 8. Write to reponse stream
            write(response, baos);

        } catch (JRException jre) {
            throw new RuntimeException(jre);
        }
    }

    /**
     * Writes the report to the output stream
     */
    private void write(HttpServletResponse response, ByteArrayOutputStream baos) {

        try {

            // Retrieve output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}