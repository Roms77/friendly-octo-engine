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
    id UUID PRIMARY KEY,
    name VARCHAR(30)
);
alter table bonsai add foreign key (owner_id) references owner(id);

create table watering(
     id UUID PRIMARY KEY,
     datetime DATE
);
alter table bonsai add foreign key (last_watering_id) references watering(id);

create table pruning(
     id UUID PRIMARY KEY,
     datetime DATE
);
alter table bonsai add foreign key (last_pruning_id) references pruning(id);

create table repotting(
     id UUID PRIMARY KEY,
     datetime DATE
);
alter table bonsai add foreign key (last_repotting_id) references repotting(id);