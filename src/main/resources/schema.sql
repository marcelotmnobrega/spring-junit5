CREATE TABLE IF NOT EXISTS products (
    id INTEGER  NOT NULL AUTO_INCREMENT,
    name VARCHAR(128)   NOT NULL,
    quantity INTEGER   NOT NULL,
    version INTEGER   NOT NULL,
    primary key (id)
);