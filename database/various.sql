select * FROM objectives;
select * from objective_type;

select * from learners;
select * from learner_plan;
select * from learner_plan_objective;
select * from learner_plan_objective_target;

DELETE from learner_plan;
DELETE from learner_plan_objective;
DELETE from learner_plan_objective_target;

select * from learner_session;
select * from learner_session_objective;
select * from learner_session_objective_target;

use ddtalk;
DELETE from learner_session;
DELETE from learner_session_objective;
DELETE from learner_session_objective_target;

select * from ddtalk.learner_plan;
DELETE from learner_plan;

select * from prompt_code;


select * from login_user;
select * from login_user_role;
select * from learner_user_access;


use ddtalk;
select *  from criteria;
DELETE from criteria;

INSERT INTO login_user (username, password, enabled, role) VALUES ('p', 't', 1, 'USER' );
INSERT INTO login_user_role VALUES (1, 'USER' );

INSERT INTO login_user (username, password, enabled, role) VALUES ('admin', 'a', 1, 'ADMIN' );
INSERT INTO login_user_role VALUES (2, 'USER' );

INSERT INTO login_user (username, password, enabled, role) VALUES ('karen', 'love', 1, 'USER' );
INSERT INTO login_user_role VALUES (3, 'USER' );


update learner_plan_objective set mastered = 'N' where learner_plan_objective_id = '10';
update learner_plan_objective set mastered = 'N' where learner_plan_objective_id = '12';