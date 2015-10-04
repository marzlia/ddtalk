package com.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import java.util.Properties;

/**
 * Created by peter on 8/23/15.
 */
@Configuration
public class JasperConfig {

    @Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {

        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
        resolver.setPrefix("classpath:reports/");
        resolver.setSuffix(".jasper");

        String [] viewNames = new String[1];
        viewNames[0] = "report_ddtalk_*";
        resolver.setViewNames(viewNames);

        resolver.setViewClass(JasperReportsPdfView.class);

        Properties properties = new Properties();
        properties.put("chartSubReport", "classpath:reports/report_ddtalk_session_chart.jasper");
        resolver.setSubReportUrls(properties);

        resolver.setOrder(0);
        return resolver;
    }
}
