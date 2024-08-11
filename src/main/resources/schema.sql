

CREATE TABLE CONTACTS (
                          CONTACT_ID VARCHAR2(36) PRIMARY KEY,  -- UUID as a string of 36 characters
                          CONTACT_NAME VARCHAR2(255),
                          EMAIL VARCHAR2(255),
                          TITLE VARCHAR2(255),
                          PHONE VARCHAR2(20),
                          CONTACT_NUMBER VARCHAR2(20),
                          STATUS VARCHAR2(50),
                          PHOTO_URL VARCHAR2(512)
);

-- Optional: Creating a unique index on the CONTACT_ID column, although it's already unique as the primary key.
CREATE UNIQUE INDEX CONTACTS_ID_UK ON CONTACTS (CONTACT_ID);

-- Trigger to automatically generate UUID for the CONTACT_ID column
CREATE OR REPLACE TRIGGER CONTACTS_BI
    BEFORE INSERT ON CONTACTS
    FOR EACH ROW
BEGIN
    IF :NEW.CONTACT_ID IS NULL THEN
        :NEW.CONTACT_ID := SYS_GUID();  -- Use SYS_GUID() to generate a unique ID
    END IF;
END;
/
