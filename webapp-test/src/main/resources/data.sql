/*create table subscriber
(
	email varchar(255) not null,
	source varchar(255) not null,
	destination varchar(255) not null,
	subscription varchar(255) not null,
	primary key(email)
);*/

/*CREATE TABLE flight
(
	flightNumber integer not null,
	originPoint varchar(255) not null,
	destinationPoint varchar(255) not null,
	dipatureDate timestamp,
	price integer not null,
	sysDate timestamp,
	primary key(flightNumber)
); */

insert into subscriber (email, source, destination, subscription) values('ratna@gmail.com','delhi','bangalore','daily');
insert into subscriber (email, source, destination, subscription) values('bikku@gmail.com','delhi','bangalore','monthly');
insert into subscriber (email, source, destination, subscription) values('pakku@gmail.com','mumbai','bangalore','daily');
insert into subscriber (email, source, destination, subscription) values('chandu@gmail.com','delhi','bangalore','monthly');
insert into subscriber (email, source, destination, subscription) values('rajesh@gmail.com','tanuku','sing','daily');
insert into subscriber (email, source, destination, subscription) values('karuna@gmail.com','delhi','bangalore','weekly');
insert into subscriber (email, source, destination, subscription) values('amma@gmail.com','delhi','bangalore','monthly');



insert into Flight (flight_number, origin_point, destination_point, dipature_date, price, sys_date) values (123,'delhi','bangalore',to_timestamp('29 Apr 2019 10:00:00'),30000,to_timestamp('26 Apr 2019 11:39:00'));

insert into Flight (flight_number, origin_point, destination_point, dipature_date, price, sys_date) values (125,'delhi','bangalore',to_timestamp('29 Apr 2019 10:00:00'),40000,to_timestamp('24 Apr 2019 11:39:00'));



commit;