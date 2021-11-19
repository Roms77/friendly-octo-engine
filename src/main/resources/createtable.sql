create table bonsai (
     id UUID PRIMARY KEY,
     name VARCHAR(30),
     species VARCHAR(30),
     acquisition_date DATE,
     acquisition_age INTEGER,
     status VARCHAR(20),
     owner_id UUID,
     last_watering_id UUID,
     last_pruning_id UUID,
     last_repotting_id UUID,
);

create table owner(
    id UUID PRIMARY KEY
    name VARCHAR(30)
)

create table watering(
     id UUID PRIMARY KEY,
     datetime DATE
)

create table pruning(
     id UUID PRIMARY KEY,
     datetime DATE
)

create table repotting(
     id UUID PRIMARY KEY,
     datetime DATE
)