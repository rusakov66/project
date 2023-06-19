CREATE TABLE IF NOT EXISTS USERS (
    ID UUID PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS PROJECTS (
    ID UUID PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION TEXT,
    START_DATE DATE,
    END_DATE DATE
);

CREATE TABLE IF NOT EXISTS TASKS (
    ID UUID PRIMARY KEY,
    PROJECT_ID UUID NOT NULL,
    NAME VARCHAR(255),
    DESCRIPTION TEXT,
    DUE_DATE DATE,
    STATUS VARCHAR(50) NOT NULL,
    ASSIGNED_USER_ID UUID,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS(ID),
    FOREIGN KEY (ASSIGNED_USER_ID) REFERENCES USERS(ID)
);

