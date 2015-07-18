package com.springapp.mvc;

import com.springapp.config.MainConfig;
import com.springapp.model.Learner;
import com.springapp.model.LearnerPlan;
import com.springapp.service.LearnerPlanService;
import com.springapp.service.LearnerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class JpaTests {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        LearnerService learnerService = (LearnerService)context.getBean("learnerService");

        List<Learner> learners =  learnerService.getAllLearners();
        for ( Learner learner : learners ) {

        }

        LearnerPlanService learnerPlanService = (LearnerPlanService)context.getBean("learnerPlanService");
        List<LearnerPlan> learnerPlans =  learnerPlanService.getAllLearnerPlans();
        for ( LearnerPlan learnerPlan : learnerPlans) {

        }

//        List<LearnerPlan> plansForLearner =  learnerPlanService.getAllPlansForLearnerId(new Long(1));
//        System.out.println(plansForLearner);
//
//        plansForLearner =  learnerPlanService.getAllPlansForLearnerId(new Long(2));
//        System.out.println(plansForLearner);




    }
}
