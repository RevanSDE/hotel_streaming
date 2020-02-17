CREATE KEYSPACE 'hotel_streaming'
            WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 2};

USE hotel_streaming;

CREATE TABLE room
(
    hotel_name text,
    room_name text,
    price double,
    status text,
    from_date text,
    to_date text,
    rate double,
    rate_date text,
    total_price double,
    current_timestamp timestamp,
    PRIMARY KEY (hotel_name, room_name, current_timestamp)
);
