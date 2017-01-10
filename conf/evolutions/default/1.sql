# --- !Ups

create table token (
  id                        bigint not null,
  date                      timestamp not null,
  token                     varchar(255) not null,
  user_id                   bigint,
  constraint pk_token primary key (id))
;

create table transaction (
  id                        bigint not null,
  amount                    double not null,
  date                      timestamp not null,
  type                      varchar(7) not null,
  category_id               bigint not null,
  owner_id                  bigint not null,
  constraint ck_transaction_type check (type in ('INCOME','OUTCOME')),
  constraint pk_transaction primary key (id))
;

create table transaction_category (
  id                        bigint not null,
  name                      varchar(255) not null,
  date                      timestamp not null,
  owner_id                  bigint not null,
  constraint pk_transaction_category primary key (id))
;

create table user (
  id                        bigint not null,
  mail                      varchar(255) not null,
  password                  varchar(255) not null,
  constraint uq_user_mail unique (mail),
  constraint pk_user primary key (id))
;

create sequence token_seq;

create sequence transaction_seq;

create sequence transaction_category_seq;

create sequence user_seq;

alter table token add constraint fk_token_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_token_user_1 on token (user_id);
alter table transaction add constraint fk_transaction_category_2 foreign key (category_id) references transaction_category (id) on delete restrict on update restrict;
create index ix_transaction_category_2 on transaction (category_id);
alter table transaction add constraint fk_transaction_owner_3 foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_transaction_owner_3 on transaction (owner_id);
alter table transaction_category add constraint fk_transaction_category_owner_4 foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_transaction_category_owner_4 on transaction_category (owner_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists token;

drop table if exists transaction;

drop table if exists transaction_category;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists token_seq;

drop sequence if exists transaction_seq;

drop sequence if exists transaction_category_seq;

drop sequence if exists user_seq;

