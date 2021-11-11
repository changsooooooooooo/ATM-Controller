drop table account cascade;
drop table card cascade;

create table if not exists card (
    id bigint auto_increment not null,
    card_id varchar(255) not null,
    pin_number bigint not null,
    primary key(id)
);
commit;

create table if not exists account (
    id bigint auto_increment not null,
    card_id bigint not null,
    account_bank ENUM('NH', 'SHINHAN', 'KB', 'WOORI', 'HANA') not null,
    balance bigint,
    primary key(id),
    foreign key (card_id) references card(id)
);
commit;
