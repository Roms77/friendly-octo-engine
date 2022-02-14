create table users (
     id UUID PRIMARY KEY,
     username VARCHAR(30),
     password VARCHAR(200),
     enabled BOOLEAN
);

create table authorities(
    id UUID,
    authority VARCHAR(10),
    PRIMARY KEY (id, authority),
    FOREIGN KEY (id) REFERENCES users(id)
);