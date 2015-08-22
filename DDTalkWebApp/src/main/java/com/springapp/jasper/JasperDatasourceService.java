package com.springapp.jasper;

/**
 * Created by peter on 8/18/15.
 */
import java.util.ArrayList;
import java.util.List;

import com.springapp.model.LearnerPlan;
import com.springapp.model.LearnerPlanObjective;
import com.springapp.service.LearnerPlanService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JasperDatasourceService {

    @Autowired
    LearnerPlanService learnerPlanService;

    /**
     * Returns a data source that's wrapped within {@link JRDataSource}
     * @return
     */
    public JRDataSource getDataSource() {
        List<LearnerPlan> plans = learnerPlanService.getAllLearnerPlans();
        // Return wrapped collection
        return new JRBeanCollectionDataSource(plans);
    }
}