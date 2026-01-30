
CREATE TABLE PREFERENCE (
       APP_ID           integer,
       NAM_TX           varchar(20),
       DSC_TX           varchar(30),
       PRIMARY KEY (APP_ID, NAM_TX)
);

CREATE TABLE PREFERENCE_VALUE (
       APP_ID           integer,
       USR_ID           varchar(8),
       NAM_TX           varchar(20),
       VLU_TX           varchar(30),
       PRIMARY KEY (APP_ID, USR_ID, NAM_TX)
);

ALTER TABLE PREFERENCE_VALUE
       ADD CONSTRAINT PREFERENCE_VALUE_FK FOREIGN KEY (APP_ID, NAM_TX) REFERENCES PREFERENCE;

INSERT INTO PREFERENCE VALUES (1, 'Theme', 'Web Style');
INSERT INTO PREFERENCE VALUES (1, 'Color', 'Theme Color');

INSERT INTO PREFERENCE_VALUE VALUES (1, 'APPDEV', 'Theme', 'Crazy');
INSERT INTO PREFERENCE_VALUE VALUES (1, 'APPDEV', 'Color', 'Purple');

INSERT INTO PREFERENCE_VALUE VALUES (1, 'ADMIN', 'Theme', 'Default');
INSERT INTO PREFERENCE_VALUE VALUES (1, 'ADMIN', 'Color', 'Blue');

INSERT INTO PREFERENCE_VALUE VALUES (1, 'USER', 'Theme', 'Default');
INSERT INTO PREFERENCE_VALUE VALUES (1, 'USER', 'Color', 'Red');

create table csr_state_reporting_detail (
	csrd_key	integer,
	state_code	varchar(2) not null,
	state_name	varchar(20),
	schedule_key	integer,
	method_key		integer,
	reporting_username	varchar(30),
	reporting_password	varchar(30),
	reporting_filename_format	varchar(50),
	reporting_date_format		varchar(50),
	reporting_remote_url		varchar(100),
	reporting_remote_directory	varchar(200),
	reporting_local_directory	varchar(200),
	reporting_email_addresses	varchar(200),
	email_type_key				integer,
	reporting_active_flag		char(1),
	primary key (csrd_key)
);

alter table csr_state_reporting_detail
	add constraint csr_state_reporting_detail_unique_state unique (state_code);

create table csr_state_reporting_method (
	method_key	integer,
	description	varchar(100),
	primary key (method_key)
);

create table csr_state_reporting_schedule (
	schedule_key	integer,
	description	varchar(100),
	primary key (schedule_key)
);

create table csr_state_reporting_email_type (
	email_type_key	integer,
	description	varchar(100),
	primary key (email_type_key)
);

ALTER TABLE csr_state_reporting_detail
       ADD CONSTRAINT csr_state_reporting_detail_fk1 FOREIGN KEY (schedule_key) REFERENCES csr_state_reporting_schedule;
ALTER TABLE csr_state_reporting_detail
       ADD CONSTRAINT csr_state_reporting_detail_fk2 FOREIGN KEY (method_key) REFERENCES csr_state_reporting_method;
ALTER TABLE csr_state_reporting_detail
       ADD CONSTRAINT csr_state_reporting_detail_fk3 FOREIGN KEY (email_type_key) REFERENCES csr_state_reporting_email_type;

insert into trexone_dw_data.csr_state_reporting_schedule values (1, 'Weekly');
insert into trexone_dw_data.csr_state_reporting_schedule values (2, 'Bi-weekly');
insert into trexone_dw_data.csr_state_reporting_schedule values (3, 'Monthly');
insert into trexone_dw_data.csr_state_reporting_schedule values (4, 'Bi-Monthly');
insert into trexone_dw_data.csr_state_reporting_schedule values (5, 'Semi-Monthly');

insert into trexone_dw_data.csr_state_reporting_method values (1, 'FTP');
insert into trexone_dw_data.csr_state_reporting_method values (2, 'sFTP');
insert into trexone_dw_data.csr_state_reporting_method values (3, 'Manual');

insert into trexone_dw_data.csr_state_reporting_email_type values (1, 'Notification');
insert into trexone_dw_data.csr_state_reporting_email_type values (2, 'Attachment');

insert into trexone_dw_data.csr_state_reporting_detail values (1, 'IN', 'Indiana', 1, 3, null, null, '${state}-${date}.dat', 'MMddyyyy', null, null, null, null, 2, '1');

create table csr_reporting_history (
	crh_key		integer,
	state_code	varchar(2) not null,
	generation_date		timestamp,
	file_name	varchar(50),
	file_directory	varchar(100),
	agency_submission_date	timestamp,
	primary key (crh_key)
);

create table csr_state_reporting_contact (
	csrc_key	integer,
	csrd_key	integer,
	first_name	varchar(50),
	last_name	varchar(50),
	phone_number	varchar(20),
	email_address	varchar(100),
	comments	varchar(200),
	primary key (csrc_key)
);

alter table csr_state_reporting_contact
	add constraint reporting_contact_fk1 foreign key (csrd_key) references csr_state_reporting_detail;
	
create sequence reporting_contact_seq start with 1 increment by 1;
create sequence reporting_detail_seq start with 1 increment by 1;
create sequence reporting_history_seq start with 1 increment by 1;