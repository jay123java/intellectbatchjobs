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


select po.*  from ffs_po_header po left outer join FFS_BOOKING_CARGO booking on po.po_number  = BOOKING.PO_NUMBER inner join USER_ALERT_PREFERENCES pref on pref.COMPANY_CODE =PO.COMPANY_CODE AND pref.IS_ACTIVE= 1 inner join 
ALERTTYPE alt on alt.ID =  pref.ALERT_TYPE_ID and alt.ALERTCODE='BOOKING' inner join FFS_USER_MASTER usr on usr.USERID = pref.USER_ID
where  booking.po_number is null and  to_date(to_char(target_ship_Date,'DD/MM/YYYY'),'DD/MM/YYYY') - to_date(to_char(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY')  = PREF.DAYS_BEFORE


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
  COMPANY_CODE varchar2(10) NOT NULL,
  CONSTRAINT Alerts_pk PRIMARY KEY (ID),
  CONSTRAINT fk_AlertType FOREIGN KEY (ALERT_TYPE_ID) REFERENCES ALERTTYPE(ID)
);

create sequence USER_ALERT_PREFERENCES_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1;


CREATE SEQUENCE SEQ_ALERTS
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10


