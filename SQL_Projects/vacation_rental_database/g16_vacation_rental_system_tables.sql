DROP TRIGGER SELF_BOOKING_TRIGGER;
DROP TRIGGER BOOKING_DATE_TRIGGER;
DROP TRIGGER COMMENT_AUTHOR_TRIGGER;
DROP TABLE Transactions;
DROP TABLE Comments;
DROP TABLE Bookings;
DROP TABLE RentalPhotos;
DROP TABLE RentalDescriptions;
DROP TABLE Rentals;
DROP TABLE Users;
DROP TABLE Locations;

CREATE TABLE Locations (
    LocationId NUMBER NOT NULL PRIMARY KEY,
    ParentId NUMBER NOT NULL,
    Name VARCHAR2(64) NOT NULL,
    LocationType VARCHAR2(8),
    CONSTRAINT fk_location_location FOREIGN KEY (ParentId) REFERENCES Locations(LocationId),
    CONSTRAINT check_location_type CHECK (LocationType in ('COUNTRY', 'CITY', 'DISTRICT'))
);
CREATE TABLE Users (
    UserId NUMBER NOT NULL PRIMARY KEY,
    Email VARCHAR2(254) NOT NULL UNIQUE,
    Password VARCHAR2(100) NOT NULL,
    Role VARCHAR2(5),
    Firstname VARCHAR2(100) NOT NULL,
    Lastname VARCHAR2(100) NOT NULL,
    Gender VARCHAR2(6),
    LocationId NUMBER NOT NULL,
    Address VARCHAR2(256) NOT NULL,
    PhoneNumber VARCHAR2(16) NOT NULL UNIQUE,
    IdentificationNumber VARCHAR2(32) NOT NULL UNIQUE,
    CONSTRAINT fk_user_location FOREIGN KEY (LocationId) REFERENCES Locations(LocationId),
    CONSTRAINT check_role CHECK(Role in ('ADMIN', 'USER')),
    CONSTRAINT check_gender CHECK(Gender in ('MALE', 'FEMALE', 'OTHER'))
);
CREATE TABLE Rentals (
    RentalId NUMBER NOT NULL PRIMARY KEY,
    HouseholderId NUMBER NOT NULL,
    Title VARCHAR2(128) NOT NULL,
    Description VARCHAR2(1024),
    Price NUMBER NOT NULL,
    LocationId NUMBER NOT NULL,
    Address VARCHAR2(256) NOT NULL,
    IsPassive NUMBER(1) DEFAULT 0 NOT NULL,
    CONSTRAINT fk_rental_user FOREIGN KEY (HouseholderId) REFERENCES Users(UserId),
    CONSTRAINT check_price CHECK (Price > 0),
    CONSTRAINT fk_rental_location FOREIGN KEY (LocationId) REFERENCES Locations(LocationId)
);
CREATE TABLE RentalDescriptions (
    RentalDescriptionId NUMBER NOT NULL PRIMARY KEY,
    RentalId NUMBER NOT NULL,
    PropertyType VARCHAR2(32) NOT NULL,
    BedCount NUMBER NOT NULL,
    RoomCount NUMBER NOT NULL,
    OccupantCount NUMBER,
    SmokingAllowed NUMBER(1),
    PetAllowed NUMBER(1),
    HasWifi NUMBER(1),
    HasGarden NUMBER(1),
    HasBalcony NUMBER(1),
    CONSTRAINT check_property_type CHECK (
        PropertyType in (
            'ROOM',
            'COMMON_ROOM',
            'APARTMENT',
            'DUBLEX',
            'TRIPLEX',
            'HOTEL_ROOM',
            'TREEHOUSE',
            'BUNGALOW'
        )
    ),
    CONSTRAINT fk_rentaldesc_rental FOREIGN KEY (RentalId) REFERENCES Rentals(RentalId)
);
CREATE TABLE RentalPhotos (
    RentalDescriptionId NUMBER NOT NULL,
    URL VARCHAR2(256) NOT NULL,
    Description VARCHAR(128) NOT NULL,
    CONSTRAINT pk_rental_photos PRIMARY KEY (RentalDescriptionId, URL),
    CONSTRAINT fk_rentalphotos_rentaldesc FOREIGN KEY (RentalDescriptionId) REFERENCES RentalDescriptions(RentalDescriptionId)
);
CREATE TABLE Bookings (
    BookingId NUMBER NOT NULL PRIMARY KEY,
    CustomerId NUMBER NOT NULL,
    RentalId NUMBER NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Status VARCHAR2(8) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT check_status CHECK (Status in ('REFUNDED', 'PASSIVE', 'ACTIVE')),
    CONSTRAINT check_date CHECK (StartDate < EndDate),
    CONSTRAINT fk_booking_customer FOREIGN KEY (CustomerId) REFERENCES Users(UserId),
    CONSTRAINT fk_booking_rental FOREIGN KEY (RentalId) REFERENCES Rentals(RentalId)
);
CREATE TABLE Comments (
    CommentId NUMBER NOT NULL PRIMARY KEY,
    RentalId NUMBER NOT NULL,
    UserId NUMBER NOT NULL,
    Text VARCHAR2(1024),
    RentalRating NUMBER(4) NOT NULL,
    HouseholderRating NUMBER(4) NOT NULL,
    CONSTRAINT fk_comment_rental FOREIGN KEY (RentalId) REFERENCES Rentals(RentalId),
    CONSTRAINT fk_comment_user FOREIGN KEY (UserId) REFERENCES Users(UserId),
    CONSTRAINT check_rental_rating CHECK(
        RentalRating BETWEEN 0 AND 10
    ),
    CONSTRAINT check_householder_rating CHECK(
        HouseholderRating BETWEEN 0 AND 10
    )
);
CREATE TABLE Transactions (
    BookingId NUMBER NOT NULL,
    Amount NUMBER NOT NULL,
    TransactionDate DATE NOT NULL,
    TransactionType VARCHAR(7) NOT NULL,
    TransactionNumber VARCHAR(50) NOT NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (BookingId, TransactionNumber),
    CONSTRAINT fk_transaction_booking FOREIGN KEY (BookingId) REFERENCES Bookings(BookingId),
    CONSTRAINT check_transaction_type CHECK (TransactionType in ('REFUND', 'PAYMENT'))
);

CREATE TRIGGER SELF_BOOKING_TRIGGER
BEFORE INSERT ON Bookings FOR EACH ROW
DECLARE householder_id number;
BEGIN
    SELECT HouseholderId INTO householder_id FROM Rentals WHERE RentalId = :new.RentalId;

    IF (householder_id = :new.CustomerId) THEN
        RAISE_APPLICATION_ERROR(-20000, 'Booking your rental is not allowed.');
    END IF;          
END;
/
CREATE TRIGGER BOOKING_DATE_TRIGGER
BEFORE INSERT ON Bookings FOR EACH ROW
DECLARE colliding_bookings number;
BEGIN
    SELECT COUNT(*) 
        INTO colliding_bookings
        FROM Bookings 
        WHERE RentalId = :new.RentalId 
            AND Status = 'ACTIVE' 
            AND ((:new.StartDate BETWEEN StartDate AND EndDate) 
                  OR (:new.EndDate BETWEEN StartDate AND EndDate) 
                  OR (StartDate >= :new.StartDate AND EndDate <= :new.EndDate));

    IF (colliding_bookings>0) THEN
        RAISE_APPLICATION_ERROR(-20000, 'Rental is unavailable in given dates.');
    END IF;          
END;
/
CREATE TRIGGER COMMENT_AUTHOR_TRIGGER
BEFORE INSERT ON Comments FOR EACH ROW
DECLARE booking_count number;
BEGIN
    SELECT COUNT(*)
        INTO booking_count
        FROM Bookings 
        WHERE CustomerId = :new.UserId AND RentalId = :new.RentalId;

    IF (booking_count = 0) THEN
        RAISE_APPLICATION_ERROR(-20000, 'Author must rent before doing comment.');
    END IF;          
END;
/