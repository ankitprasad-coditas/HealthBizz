
CREATE TABLE roles (
    id serial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE permissions (
    id serial PRIMARY KEY,
    name varchar(30)
);

CREATE TABLE roles_permissions (
    role_id int,
    permission_id int,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id),
    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE country (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head BOOLEAN DEFAULT FALSE
);

CREATE TABLE state (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head BOOLEAN DEFAULT FALSE,
    country_id BIGINT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE
);

CREATE TABLE district (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head BOOLEAN DEFAULT FALSE,
    state_id BIGINT NOT NULL,
    FOREIGN KEY (state_id) REFERENCES state(id) ON DELETE CASCADE
);

CREATE TABLE taluka (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head BOOLEAN DEFAULT FALSE,
    district_id BIGINT NOT NULL,
    FOREIGN KEY (district_id) REFERENCES district(id) ON DELETE CASCADE
);

CREATE TABLE city (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    data_collector BOOLEAN DEFAULT FALSE,
    taluka_id BIGINT NOT NULL,
    FOREIGN KEY (taluka_id) REFERENCES taluka(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    contact_number BIGINT,
    role_id int,
    country_id BIGINT,
    state_id BIGINT,
    district_id BIGINT,
    taluka_id BIGINT,
    city_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE SET NULL,
    FOREIGN KEY (state_id) REFERENCES state(id) ON DELETE SET NULL,
    FOREIGN KEY (district_id) REFERENCES district(id) ON DELETE SET NULL,
    FOREIGN KEY (taluka_id) REFERENCES taluka(id) ON DELETE SET NULL,
    FOREIGN KEY (city_id) REFERENCES city(id) ON DELETE SET NULL
);

CREATE TABLE survey_data (
    id BIGSERIAL PRIMARY KEY,
    users_id BIGINT NOT NULL,
    business_name VARCHAR(255),
    business_type VARCHAR(255),
    abha_id VARCHAR(255),
    address TEXT,
    city VARCHAR(255),
    taluka VARCHAR(255),
    district VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    contact_number VARCHAR(255),
    email VARCHAR(255),
    head_doctor VARCHAR(255),
    year_established INT,
    emergency_services BOOLEAN,
    number_of_beds INT,
    number_of_doctors INT,
    date_of_survey TIMESTAMP,
    FOREIGN KEY (users_id) REFERENCES users (id) ON DELETE CASCADE
);
