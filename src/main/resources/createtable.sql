create table bonsai (
     id UUID PRIMARY KEY,
     name VARCHAR(30),
     species VARCHAR(30),
     acquisition_date DATE,
     acquisition_age INTEGER,
     status VARCHAR(20)
);

create table owner(
    id UUID PRIMARY KEY,
    name VARCHAR(30)
);

create table watering(
     id UUID PRIMARY KEY,
     bonsai_id UUID,
     datetime DATE,
     foreign key (bonsai_id) references bonsai(id)
);

create table pruning(
     id UUID PRIMARY KEY,
     bonsai_id UUID,
     datetime DATE,
     foreign key (bonsai_id) references bonsai(id)
);

create table repotting(
     id UUID PRIMARY KEY,
     bonsai_id UUID,
     datetime DATE,
     foreign key (bonsai_id) references bonsai(id)
);
