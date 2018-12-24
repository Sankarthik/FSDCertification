CREATE TABLE parent_task
(
ID INT(9) PRIMARY KEY,
TASK VARCHAR(100)
);


CREATE TABLE task
(
ID INT(9) PRIMARY KEY AUTO_INCREMENT,
TASK VARCHAR(100),
START_DATE DATE,
END_DATE DATE,
PRIORITY INT(2),
PARENT_TASK_ID INT(9),
FOREIGN KEY(PARENT_TASK_ID) REFERENCES parent_task (ID)
);

CREATE TABLE users
(
EMPLOYEE_ID INT(9) PRIMARY KEY NOT NULL,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL
);