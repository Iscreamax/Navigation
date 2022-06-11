use navigation;
INSERT INTO cities (id,name) VALUES (1,'Minsk');
INSERT INTO cities (id,name) VALUES (2,'Zhodino');

INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (1,'Malinovka',53.86326,27.534132,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (2,'Petrovschina',53.864017,27.541872,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (3,'Mihalovo',53.865525,27.530279,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (4,'Grushevka',53.869969,27.533942,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (5,'Lenina',53.872698,27.527161,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (6,'Octobrs',53.877734,27.544222,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (7,'Pobedy',53.879906,27.535609,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (8,'Pervomayskay',53.88438,27.534633,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (9,'Partizanskay',53.882232,27.519533,1);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (10,'Mogilevskay',53.880577,27.583528,1);

INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (11,'Vokzal',54.012878,28.143555,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (12,'Truda',54.010578,28.137389,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (13,'Electrosety',54.007365,28.114147,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (14,'Kamenchikova',54.017558,28.061178,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (15,'Krupskay',54.025152,28.100018,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (16,'Nagornaya',54.028656,28.110058,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (17,'Gromyko',54.025311,28.083132,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (18,'Makayenka',54.035311,28.084132,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (19,'Kiseleva',54.045311,28.108132,2);
INSERT INTO bus_stops(id,name,latitude,longitude,cities_id) VALUES (20,'Kurchatova',54.017365,28.104147,2);

INSERT INTO buses(number, max_count_of_passengers, start_time, end_time) values ('C111', 70, '05.00', '20.00' );
INSERT INTO buses(number, max_count_of_passengers, start_time, end_time) values ('C342', 70, '06.00', '20.00' );
INSERT INTO buses(number, max_count_of_passengers, start_time, end_time) values ('C525', 70, '08.00', '17.00' );
INSERT INTO buses(number, max_count_of_passengers, start_time, end_time) values ('I774', 40, '00.00', '24.00' );
INSERT INTO buses(number, max_count_of_passengers, start_time, end_time) values ('I999', 100, '00.00', '24.00' );

INSERT INTO routes(buses_id, bus_stops_id) values (1,19),(1,18),(1,15),(1,12),(1,13),(1,14);
INSERT INTO routes(buses_id, bus_stops_id) values (2,1),(2,10),(2,9),(2,8);
INSERT INTO routes(buses_id, bus_stops_id) values (3,1),(3,4),(3,7),(3,8),(3,10);
INSERT INTO routes(buses_id, bus_stops_id) values (4,1),(4,5),(4,10),(4,20),(4,19);
INSERT INTO routes(buses_id, bus_stops_id) values (5,1),(5,2),(5,3),(5,4),(5,5),(5,9),(5,8),(5,7),(5,6),(5,10),(5,14),
(5,13),(5,12),(5,11),(5,20),(5,15),(5,17),(5,18),(5,16),(5,19);
select * from routes;