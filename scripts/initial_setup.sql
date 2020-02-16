CREATE KEYSPACE 'hotel_streaming'
            WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 2};

USE hotel_streaming;

CREATE TABLE room
(
    hotelName: text,
    roomName: text,
    price: double,
    status: text,
    fromDate: text,
    toDate: text,
    rate: double,
    totalPrice:double,
    currentTimestamp: timestamp,
    PRIMARY KEY (hotelName, roomName, currentTimestamp)
);

