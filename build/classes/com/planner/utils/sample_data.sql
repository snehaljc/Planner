-- LOCATIONS
insert into Locations (City, Area) values ("Nashik", "K K Wagh");
insert into Locations (City, Area) values ("Nashik", "Dwarka");
insert into Locations (City, Area) values ("Nashik", "Shalimar");

-- USERS
insert into users (fname, lname, email, pwd) values ("test", "test", "test@test.com", "test");

-- Events

insert into Events (Name, LocationId, CreatedBy, Address, Date, Time, Description) values ("Birthday party at my place", 1, 1, "litereally in kk college",current_date(),current_time(),"usual bday party bring some gifts");

insert into Events (Name, LocationId, CreatedBy, Address, Date, Time, Description) values ("GD practice session", 2, 1, "My home address",current_date(),current_time(),"Lets practice some group discussion for placements");
