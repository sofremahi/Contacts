-- Lionel Messi
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Lionel Messi', 'lionelmessi@gmail.com', 'Football Player', '+34 612 345 678', '1', 'Active', NULL);

-- Neymar Jr.
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Neymar Jr.', 'neymarjr@gmail.com', 'Football Player', '+55 21 91234 5678', '2', 'Active', NULL);

-- Cristiano Ronaldo
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Cristiano Ronaldo', 'cristianoronaldo@gmail.com', 'Football Player', '+351 91 234 5678', '3', 'Active', NULL);

-- Sergio Busquets
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Sergio Busquets', 'sergiobusquets@gmail.com', 'Football Player', '+34 651 234 567', '4', 'Active', NULL);

-- Lewis Hamilton
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Lewis Hamilton', 'lewishamilton@gmail.com', 'F1 Driver', '+44 7400 123456', '5', 'Active', NULL);

-- Charles Leclerc
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Charles Leclerc', 'charlesleclerc@gmail.com', 'F1 Driver', '+377 6 78 90 12', '6', 'Active', NULL);

-- Max Verstappen
INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, EMAIL, TITLE, PHONE, CONTACT_NUMBER, STATUS, PHOTO_URL)
VALUES (SYS_GUID(), 'Max Verstappen', 'maxverstappen@gmail.com', 'F1 Driver', '+31 6 12345678', '7', 'Active', NULL);


INSERT INTO TB_USERS (USER_ID, USERNAME, EMAIL, PASSWORD)
VALUES (1, 'username', 'user@email.com', '$2a$10$IBdLjQTCcGXY4UokNbtSWelkLMYwYtwaR6f5ItE/sdFJ3o9mmbLlW');

INSERT INTO TB_USERS (USER_ID, USERNAME, EMAIL, PASSWORD)
VALUES (2, 'admin', 'admin@email.com', '$2a$10$1Inzup9xhJ93bmvlvvd/P.K816LG9k4aD4SzEAsXB9zFLCFZagk02');

INSERT INTO TB_ROLES (ROLE_ID, ROLE)
VALUES (1, 'ROLE_USER');

INSERT INTO TB_ROLES (ROLE_ID, ROLE)
VALUES (2, 'ROLE_ADMIN');

-- Assign ROLE_USER to username
INSERT INTO USERS_ROLES (USER_ID, ROLE_ID)
VALUES (1, 1);

-- Assign ROLE_ADMIN to admin
INSERT INTO USERS_ROLES (USER_ID, ROLE_ID)
VALUES (2, 2);
