# --- !Ups

ALTER TABLE transaction ADD COLUMN description varchar(255);
ALTER TABLE transaction ADD COLUMN creation_date timestamp not null DEFAULT NOW();

# --- !Downs

ALTER TABLE transaction DROP COLUMN description;
ALTER TABLE transaction DROP COLUMN creation_date;