CREATE TABLE ALERTTYPE
(
 ID number(10) NOT NULL,
  ALERTCODE varchar2(10) NOT NULL,
  DESCRIPTION varchar2(100) NOT NULL,
  CONSTRAINT alertId_pk PRIMARY KEY (ID)
);

insert into ALERTTYPE values(1, 'BOOKING', 'Describes the pos that have not been generated booking yet');
insert into ALERTTYPE values(2, 'HOUSEBILL', 'Describes the booking that have not been gnerated housebill yet');
insert into ALERTTYPE values(3, 'MASTERBILL', 'Describes the HouseBill that have not been generated masterbi yet');




CREATE TABLE ALERTS
(
  ID number(10) NOT NULL,
  ALERT_TYPE_ID number(10) NOT NULL,
  ALERT_NUMBER varchar2(20) NOT NULL,
  TRANSPORT_MODE varchar2(10) NOT NULL,
  ORIGIN_COUNTRY varchar2(10) NOT NULL,
  EXPORT_COUNTRY varchar2(10) NOT NULL,
    SHIPPING_TERM varchar2(10) NOT NULL, 
  CONSIGNEE_CODE varchar2(10) NOT NULL,
  CUSTOMER_NAME varchar2(50),
  CONSTRAINT Alerts_pk PRIMARY KEY (ID),
  CONSTRAINT fk_AlertType FOREIGN KEY (ALERT_TYPE_ID) REFERENCES ALERTTYPE(ID)
);
x
CREATE SEQUENCE SEQ_ALERTS
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10


