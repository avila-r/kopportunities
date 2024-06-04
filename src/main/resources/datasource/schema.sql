-- Jobs --
create table if not exists jobs (
    id uuid not null unique primary key,
    title varchar(100) not null,
    description text not null,
    company varchar(50) not null,
    location varchar(100) not null,
    type varchar(50) not null,
    posted_date timestamp not null,
    salary decimal(10, 2) not null,
    requirements text not null,
    responsibilities text not null,
    status varchar(50) not null
);

-- Job Applications --
create table if not exists applications (
    id uuid not null unique primary key,
    job_id uuid references jobs(id),
    applicant_name varchar(50) not null,
    applicant_email varchar(50) not null,
    resume_url varchar(100) not null,
    cover_letter text not null,
    applied_date timestamp not null,
    status varchar(50) not null
);