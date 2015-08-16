use ddtalk;

DROP TABLE learners;
DROP TABLE learner_user_access;
DROP TABLE learner_plan;
DROP TABLE learner_plan_objective;
DROP TABLE learner_plan_objective_target;
DROP TABLE learner_session;
DROP TABLE learner_session_objective;
DROP TABLE learner_session_objective_target;
DROP TABLE domains;
DROP TABLE objectives;
DROP TABLE objective_type;
DROP TABLE criteria;
DROP TABLE conditions;
DROP TABLE prompt_code;
DROP TABLE login_user;
DROP TABLE login_user_role;

CREATE TABLE IF NOT EXISTS learners (
  learner_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(25) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  dob DATE DEFAULT NULL,
  grade varchar(10) DEFAULT NULL,
  school varchar(50) DEFAULT NULL,
  student_id varchar(50) DEFAULT NULL,
  date_initial_eval DATE DEFAULT NULL,
  status varchar(15) DEFAULT NULL,
  PRIMARY KEY (learner_id)
);

CREATE TABLE IF NOT EXISTS learner_user_access (
  learner_id int(11) NOT NULL,
  user_id int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS learner_plan (
  learner_plan_id int(11) NOT NULL AUTO_INCREMENT,
  learner_id int(11) NOT NULL,
  treatment_description varchar(255) DEFAULT NULL,  
  date_start_plan DATE DEFAULT NULL,
  treatment_frequency varchar(25) DEFAULT NULL,
  data_collect_frequency varchar(25) DEFAULT NULL,  
  target_num_fluency_probes int(11),
  target_enable_retention_probes varchar(2) DEFAULT NULL,
  PRIMARY KEY (learner_plan_id)
);

CREATE TABLE IF NOT EXISTS learner_plan_objective (
  learner_plan_objective_id int(11) NOT NULL AUTO_INCREMENT,
  learner_plan_id int(11) NOT NULL,
  objective_id int(11) NOT NULL,  
  objective_type_id int(11) NOT NULL,
  condition_id int(11),
  criteria_id int(11),
  mastery_value int(11),  
  PRIMARY KEY (learner_plan_objective_id)
);

CREATE TABLE IF NOT EXISTS learner_plan_objective_target (
  learner_plan_objective_target_id int(11) NOT NULL AUTO_INCREMENT,
  learner_plan_objective_id int(11) NOT NULL,
  description varchar(512) DEFAULT NULL,
  PRIMARY KEY (learner_plan_objective_target_id)
);

CREATE TABLE IF NOT EXISTS domains (
  domain_id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (domain_id)
);

CREATE TABLE IF NOT EXISTS objectives (
  objective_id int(11) NOT NULL AUTO_INCREMENT,  
  domain_id int(11) NOT NULL,
  description varchar(512) DEFAULT NULL,
  PRIMARY KEY (objective_id)
);

CREATE TABLE IF NOT EXISTS objective_type (
  objective_type_id int(11) NOT NULL AUTO_INCREMENT,  
  type_id varchar(5) DEFAULT NULL,
  description varchar(55) DEFAULT NULL,
  PRIMARY KEY (objective_type_id)
);

CREATE TABLE IF NOT EXISTS criteria (
  criteria_id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (criteria_id)
);

CREATE TABLE IF NOT EXISTS conditions (
  condition_id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (condition_id)
);

CREATE TABLE IF NOT EXISTS prompt_code (
  prompt_code_id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (prompt_code_id)
);

CREATE TABLE login_user (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(20) NOT NULL,
  enabled int(1) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE login_user_role (
  user_id int(11) NOT NULL,
  rolename varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS learner_session (
  learner_session_id int(11) NOT NULL AUTO_INCREMENT,
  learner_plan_id int(11) NOT NULL,
  session_date DATE DEFAULT NULL,
  PRIMARY KEY (learner_session_id)
);

CREATE TABLE IF NOT EXISTS learner_session_objective (
  learner_session_objective_id int(11) NOT NULL AUTO_INCREMENT,
  learner_session_id int(11) NOT NULL,
  learner_plan_objective_id int(11) NOT NULL,
  session_value int(11),
  mastery_date DATE DEFAULT NULL,
  PRIMARY KEY (learner_session_objective_id)
);

CREATE TABLE IF NOT EXISTS learner_session_objective_target(
  learner_session_objective_target_id int(11) NOT NULL AUTO_INCREMENT,
  learner_session_objective_id int(11) NOT NULL,
  learner_plan_objective_target_id int(11) NOT NULL,
  prompt_code_id int(11),
  session_value int(11),
  mastery_date DATE DEFAULT NULL,
  PRIMARY KEY (learner_session_objective_target_id)
);

  