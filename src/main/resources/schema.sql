DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation
(
    id             INTEGER NOT NULL,
    license_id     BIGINT,
    vehicle_id     VARCHAR(255),
    location_start DATE,
    location_end   DATE,
    estimate_km    INTEGER,
    actual_km      INTEGER,
    deposit        INTEGER,
    status         VARCHAR(255),
    PRIMARY KEY (id)
);