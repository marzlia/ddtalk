use ddtalk;

INSERT INTO `ddtalk`.`learner_plan` 
(
  `learner_plan_id`,
  `learner_id`,
  `treatment_description`,
  `date_start_plan`,
  `treatment_frequency`,
  `data_collect_frequency`,
  `target_num_fluency_probes`,
  `target_enable_retention_probes`)
  VALUES
  (1,1,'My Treatment Plan', '2015-06-05T00:00:00', 'weekly', 'session', 2,'Y');
  
  
INSERT INTO `ddtalk`.`learner_plan_objective`
(`learner_plan_objective_id`,
`learner_plan_id`,
`objective_id`,
`objective_type_id`)
VALUES
(10,1,1,2);

INSERT INTO `ddtalk`.`learner_plan_objective_data`
(`learner_plan_objective_data_id`,
`learner_plan_objective_id`,
`target_id`,
`condition_id`,
`criteria_id`,
`mastery_value`)
VALUES
(10,10,1,1,1,85);

INSERT INTO `ddtalk`.`learner_plan_objective`
(`learner_plan_objective_id`,
`learner_plan_id`,
`objective_id`,
`objective_type_id`)
VALUES
(11,1,400,1);

INSERT INTO `ddtalk`.`learner_plan_objective_data`
(`learner_plan_objective_data_id`,
`learner_plan_objective_id`,
`target_id`,
`condition_id`,
`criteria_id`,
`mastery_value`)
VALUES
(11,11,1,1,1,130);

INSERT INTO `ddtalk`.`conditions`
(`condition_id`,
`description`)
VALUES
(1,
'First condition');

INSERT INTO `ddtalk`.`conditions`
(`description`)
VALUES
('Second condition');

INSERT INTO `ddtalk`.`conditions`
(`description`)
VALUES
('Third condition');

INSERT INTO `ddtalk`.`domains`
(`domain_id`,
`description`)
VALUES
(1,
'First domain');

INSERT INTO `ddtalk`.`domains`
(`domain_id`,
`description`)
VALUES
(2,
'Second domain');

INSERT INTO `ddtalk`.`domains`
(`domain_id`,
`description`)
VALUES
(3,
'Third domain');

INSERT INTO `ddtalk`.`objectives`
(`objective_id`,
`domain_id`,
`description`)
VALUES
(1,1,
'First objective');

INSERT INTO `ddtalk`.`objectives`
(`objective_id`,
`domain_id`,
`description`)
VALUES
(2,2,
'Second objective');

INSERT INTO `ddtalk`.`objectives`
(`objective_id`,
`domain_id`,
`description`)
VALUES
(3,3,
'Third objective');

INSERT INTO `ddtalk`.`domain_objectives`
(`domain_id`,
`objective_id`)
VALUES
(1,1);

INSERT INTO `ddtalk`.`domain_objectives`
(`domain_id`,
`objective_id`)
VALUES
(2,2);

INSERT INTO `ddtalk`.`domain_objectives`
(`domain_id`,
`objective_id`)
VALUES
(3,3);

INSERT INTO `ddtalk`.`target`
(`target_id`,
`description`)
VALUES
(1,'first target');

INSERT INTO `ddtalk`.`target`
(`target_id`,
`description`)
VALUES
(2,'second target');

INSERT INTO `ddtalk`.`objective_targets`
(`objective_id`,
`target_id`)
VALUES
(1,1);

INSERT INTO `ddtalk`.`criteria`
(`description`)
VALUES
('test criteria');

INSERT INTO `ddtalk`.`criteria`
(`description`)
VALUES
('2nd test criteria');

INSERT INTO `ddtalk`.`criteria`
(`description`)
VALUES
('3rd test criteria');

INSERT INTO `ddtalk`.`login_user`
(`user_id`,
`username`,
`password`,
`enabled`)
VALUES
(1,'pete','test', 1);

INSERT INTO `ddtalk`.`login_user_role`
(`user_id`,
`rolename`)
VALUES
(1,'USER');

INSERT INTO `ddtalk`.`login_user`
(`user_id`,
`username`,
`password`,
`enabled`)
VALUES
(2,'karen','love', 1);

INSERT INTO `ddtalk`.`login_user_role`
(`user_id`,
`rolename`)
VALUES
(2,'USER');

INSERT INTO `ddtalk`.`learner_user_access`
(`learner_id`,
`user_id`)
VALUES
(2,1);

