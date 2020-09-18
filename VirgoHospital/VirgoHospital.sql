create database DS_VirgoHospital
go
use DS_VirgoHospital
go
create table PersonalDetails(
IDPersonalDetails int primary key identity,
MaritalStatus nvarchar(50),
NoOfDependents int,
HeightInCm int,
WeightInKg int,
BloodType nvarchar(5)
)
go
create table PersonAddress(
IDPersonAddress int primary key identity,
DoorNumber nvarchar(10),
Street nvarchar(50),
Area nvarchar(50),
City nvarchar(50),
State nvarchar(50),
PinCode nvarchar(50)
)
go
create table ContactDetails(
IDContactDetails int primary key identity,
TelephoneWork nvarchar(50),
TelephoneHome nvarchar(50),
Mobile nvarchar(50),
Pager nvarchar(50),
Fax nvarchar(50),
Email nvarchar(50),
PresentAddressID int foreign key references PersonAddress(IDPersonAddress),
PermanentAddressID int foreign key references PersonAddress(IDPersonAddress),
PersonalDetailsID int foreign key references PersonalDetails(IDPersonalDetails)
)
go

create table Lifestyle(
IDLifestyle int primary key identity,
Vegetarian bit,
Smoker bit,
ConsumeAlcoholicBeverage bit,
Stimulans nvarchar(50),
ConsumptionOfCoffeePerDay nvarchar(50),
ConsumptionOfSoftDrinksPerDay nvarchar(50),
RegularityOfMeals nvarchar(50),
EatingHomeOrOutside nvarchar(50)
)
go
create table ProfessionDetails(
IDProfessionDetails int primary key identity,
Occupation nvarchar(50),
GrossAnnualIncome money,
)
go
create table BasicComplaints(
IDBasicComplaints int primary key identity,
StatementOfComplaint nvarchar(50),
HistoryOfPreviousTreatment nvarchar(50),
PhysicianOrHospitalTreated nvarchar(50)
)
go
create table ImportantMedicalComplaints(
IDImportantMedicalComplaints int primary key identity,
Diabetic nvarchar(100),
Hypertensive nvarchar(100),
CardiacCondition nvarchar(100),
RespiratoryCondition nvarchar(100),
DigestiveCondition nvarchar(100),
OrthopedicCondition nvarchar(100),
MuscularCondition nvarchar(100),
NeurologicalCondition nvarchar(100),
KnownAllergies nvarchar(100),
AdverseReactionToDrugs nvarchar(100),
HistoryOfMajorSurgeries nvarchar(100)
)

go
create table NextOfKin(
IDNextOfKin int primary key identity,
FirstName nvarchar(50) not null,
MiddleName nvarchar(50),
Surname nvarchar(50) not null,
RelationshipWithPatient nvarchar(50) not null,
ContactDetailsID int foreign key references ContactDetails(IDContactDetails),
ContactAddressID int foreign key references PersonAddress(IDPersonAddress)
)
go
create table ReceptionExecutive(
IDReceptionExecutive int primary key identity,
FirstName nvarchar(50),
Surname nvarchar(50),
Title nvarchar(10)
)
go
create table Patient(
IDPatient int primary key identity,
DateOfCreation datetime not null,
FirstName nvarchar(50) not null,
Surname nvarchar(50) not null,
Sex nvarchar(50) not null,
DOB nvarchar(50) not null,
FullRegistration nvarchar(50),
ContactDetailsID int foreign key references ContactDetails(IDContactDetails),
ProfessionDetailsID int foreign key references ProfessionDetails(IDProfessionDetails),
BasicComplaintsID int foreign key references BasicComplaints(IDBasicComplaints),
ImportantMedicalComplaintsID int foreign key references ImportantMedicalComplaints(IDImportantMedicalComplaints),
LifestyleID int foreign key references Lifestyle(IDLifestyle),
NextOfKinID int foreign key references NextOfKin(IDNextOfKin)
)
go
create table Specialization(
IDSpecialization int primary key identity,
NameOfSpecialization nvarchar(50)
)
go
create table Specialist(
IDSpecialist int primary key identity,
FirstName nvarchar(50),
Surname nvarchar(50),
Title nvarchar(10),
SpecializationID int foreign key references Specialization(IDSpecialization)
)
go
create table GeneralPhysician(
IDGeneralPhysician int primary key identity,
FirstName nvarchar(50),
Surname nvarchar(50),
Title nvarchar(10)
)
go
create table Payment(
IDPayment int primary key identity,
Amount money
)
go
create table SpecialTreatment(
IDSpecialTreatment int primary key identity,
TypeOfTreatment nvarchar(max),
SpecialistID int foreign key references Specialist(IDSpecialist),

)
go
create table GeneralTreatment(
IDGeneralTreatment int primary key identity,
TypeOfTreatment nvarchar(max),
GeneralPhysicianID int foreign key references GeneralPhysician(IDGeneralPhysician),

)
go

create table Appointment(
IDAppointment int primary key identity,
DayOfAppointment date,
PaymentID int foreign key references Payment(IDPayment),
ReceptionExecutiveID int foreign key references ReceptionExecutive(IDReceptionExecutive),
)
go
create table MedicalPerscription(
IDMedicalPerscription int primary key identity,
Name nvarchar(50)
)
go
create table Treatment(
IDTreatment int primary key identity,
PatientID int foreign key references Patient(IDPatient),
SpecialTreatmentID int foreign key references SpecialTreatment(IDSpecialTreatment),
GeneralTreatmentID int foreign key references GeneralTreatment(IDGeneralTreatment),
AppointmentID int foreign key references Appointment(IDAppointment),
MedicalPerscriptionID int foreign key references MedicalPerscription(IDMedicalPerscription),
Diagnosis nvarchar(max)
)
go

create table AdminUser(
IDUser int primary key identity,
Username nvarchar(50),
Password nvarchar(50)
)
go
insert into AdminUser values ('admin','admin')
go
create table Test(
IDTest int primary key identity,
Name nvarchar(50),
Result nvarchar(50),
PatientID int foreign key references Patient(IDPatient)
)
go

create proc insertGeneralPhysician
@name nvarchar(50),
@surname nvarchar(50),
@title nvarchar(50),
@id int OUTPUT
as
begin
insert into GeneralPhysician(FirstName, Surname, Title) values (@name,@surname,@title)
select @id = scope_identity() from GeneralPhysician
end
go
create procedure deleteGeneralPhysician
@id int
as
begin
alter table GeneralTreatment nocheck constraint all
alter table Treatment nocheck constraint all
delete from GeneralPhysician where IDGeneralPhysician=@id
alter table GeneralTreatment check constraint all
alter table Treatment check constraint all
end
go

create proc updateGeneralPhysician
@name nvarchar(50),
@surname nvarchar(50),
@title nvarchar(50),
@id int 
as
begin
update GeneralPhysician set FirstName=@name,Surname=@surname,Title=@title where IDGeneralPhysician=@id
end
go

create proc getGeneralPhysician
@id int
as
select * from GeneralPhysician where IDGeneralPhysician=@id
go

create proc getGeneralPhysicians
as
select * from GeneralPhysician
go
create proc getPatientsForGeneralPhysician
@id int
as
begin
select DISTINCT p.IDPatient,p.FirstName,p.Surname,p.Sex,p.DOB,p.DateOfCreation from Patient as p
inner join Treatment as t
on t.PatientID=p.IDPatient
inner join GeneralTreatment as g
on g.IDGeneralTreatment=t.GeneralTreatmentID
where g.GeneralPhysicianID=@id
end

go



create proc getPatients
as
begin
select p.IDPatient,p.FirstName,p.Surname,p.Sex,p.DOB,p.DateOfCreation from Patient as p
end
go
--insert into GeneralPhysician(FirstName, Surname, Title) values ('Test1','Test1','dr')

--insert into Patient(DateOfCreation, FirstName, Surname, Sex,DOB, OPID) values (GETDATE(),'Dario','Simic','M','03.01.1999','DS3424232')
--insert into GeneralTreatment(TypeOfTreatment, GeneralPhysicianID) values ('Test',1)
--go
--insert into Treatment(PatientID,GeneralTreatmentID, Diagnosis) values (2,1,'2222')

--select * from Patient
--delete from Patient
--delete from Treatment
--select * from GeneralPhysician

--1) Name (Surname, Middle name, First name):
--2) Sex (M/F):
--3) Date of Birth (dd/mm/yy):
--4) Brief Statement of Complaint:
--5) Contact Telephone Number 1:
-- Contact Telephone Number 2:
--6) Name of Next-of-Kin (Surname, Middle name, First name):
--Relationship to Outpatient:

create proc fastRegistryOfPatient
@name nvarchar(50),
@surname nvarchar(50),
@sex nvarchar(50),
@dob nvarchar(50),
@briefsoc nvarchar(50),
@tel1 nvarchar(50),
@tel2 nvarchar(50),
@HistoryOfPerviousTreatment nvarchar(50),
@PhysicianHospitalTreated nvarchar(50),
@nameOfKin nvarchar(50),
@middleNameOfKin nvarchar(50),
@lastNameOfKin nvarchar(50),
@relationToPatient nvarchar(50),
@id int OUTPUT
as
begin
declare @idOfBrief int
declare @idOfContact int
declare @idOfKin int
insert into BasicComplaints(StatementOfComplaint, HistoryOfPreviousTreatment, PhysicianOrHospitalTreated)
values (@briefsoc,@HistoryOfPerviousTreatment,@PhysicianHospitalTreated)
select @idOfBrief = SCOPE_IDENTITY() from BasicComplaints
insert into ContactDetails(TelephoneHome, Mobile)
values (@tel1,@tel2)
select @idOfContact = SCOPE_IDENTITY() from ContactDetails
insert into NextOfKin(FirstName, MiddleName, Surname, RelationshipWithPatient)
values (@nameOfKin,@middleNameOfKin,@lastNameOfKin,@relationToPatient)
select @idOfKin = SCOPE_IDENTITY() from NextOfKin
insert into Patient(DateOfCreation, FirstName, Surname, Sex, DOB, FullRegistration, ContactDetailsID, BasicComplaintsID, NextOfKinID)
values (getdate(),@name,@surname,@sex,@dob,'No',@idOfContact,@idOfBrief,@idOfKin)
select @id = SCOPE_IDENTITY() from Patient
end
go
--select * from Patient
--select * from ContactDetails
--delete from patient

create proc getPatient 
@id int
as select p.IDPatient,p.FirstName,p.Surname,p.Sex,p.DOB,p.DateOfCreation from Patient as p
where p.IDPatient=@id
go
create proc insertSpecialist
@name nvarchar(50),
@surname nvarchar(50),
@title nvarchar(10),
@spec int,
@id int output
as
begin
insert into Specialist values (@name,@surname,@title,@spec)
select @id = SCOPE_IDENTITY() from Specialist
end
go
create proc updatePatient
@id int,
@name nvarchar(50),
@surname nvarchar(50),
@DOB nvarchar(50),
@sex nvarchar(50)
as
update Patient set FirstName=@name,Surname=@surname,DOB=@DOB,Sex=@sex
where IDPatient=@id
go
create proc getSpecializations
as
select * from Specialization
go
create proc getSpecialization
@id int
as
select * from Specialization
where IDSpecialization = @id
go
create proc getSpecialist
@id int
as
select * from Specialist as p
where p.IDSpecialist=@id
--select * from Specialist
go
create proc getMedicalPerscriptions
as
select * from MedicalPerscription
go
create proc getAllAppointments
as
select * from Appointment
go
create proc checkUser
@u nvarchar(50),
@pw nvarchar(50)
as
select * from AdminUser as au
where au.Username = @u and au.Password =  @pw
go
create proc getSpecialists
as
select s.IDSpecialist,s.FirstName,s.Surname,s.Title,s.SpecializationID,sp.NameOfSpecialization from Specialist as s
inner join Specialization as sp
on sp.IDSpecialization=SpecializationID
go
create proc createAppointment
@idGP int,
@idP int,
@date date,
@app int OUTPUT
as
begin
insert into Appointment(DayOfAppointment) values (@date)
declare @idAp int
select @app=SCOPE_IDENTITY() from Appointment
insert into GeneralTreatment(GeneralPhysicianID) values (@idGP)
select @idAp=SCOPE_IDENTITY() from GeneralTreatment
insert into Treatment(PatientID,GeneralTreatmentID,AppointmentID) values (@idP,@idAp,@app)
end
go

create proc deleteSpecialist
@id int
as
begin
alter table SpecialTreatment nocheck constraint all
alter table Treatment nocheck constraint all
delete from Specialist where IDSpecialist=@id
alter table SpecialTreatment check constraint all
alter table Treatment check constraint all
end
go
create proc getSpecializationId
@id int,
@idSP int OUTPUT,
@name nvarchar(50) OUTPUT
as
begin
select @idSP=s.SpecializationID,@name=sp.NameOfSpecialization from Specialist as s
inner join Specialization as sp
on sp.IDSpecialization=s.SpecializationID
where IDSpecialist=@id
end
go
create proc updateSpecialist
@name nvarchar(50),
@surname nvarchar(50),
@title nvarchar(10),
@id int
as
begin
update Specialist set FirstName=@name,Surname=@surname,Title=@title
where IDSpecialist=@id
end
go
create proc getAppointments
as
begin
select * from Appointment as a
inner join Treatment as t
on t.AppointmentID=a.IDAppointment
inner join Patient as p
on p.IDPatient=t.PatientID
order by a.DayOfAppointment desc
end
go
create proc getPayment
@id int
as
select * from Payment
where IDPayment=@id
go
create proc deletePatient
@id int
as
begin
alter table Patient nocheck constraint all
alter table Treatment nocheck constraint all
alter table Appointment nocheck constraint all
alter table GeneralTreatment nocheck constraint all
delete Patient where IDPatient=@id
alter table Patient check constraint all
alter table Treatment check constraint all
alter table Appointment check constraint all
alter table GeneralTreatment check constraint all
end
go
create proc deleteAppointment
@id int
as
begin
alter table Treatment nocheck constraint all
alter table Appointment nocheck constraint all
delete from Appointment where IDAppointment=@id
alter table Treatment check constraint all
alter table Appointment check constraint all
end
go
create proc getUpcomingAppointments
as
begin
select * from Appointment as a
inner join Treatment as t
on t.AppointmentID=a.IDAppointment
inner join Patient as p
on p.IDPatient=t.PatientID
where a.DayOfAppointment > getdate()
order by a.DayOfAppointment desc
end
go
create proc getDetailedPatient
@id int
as
begin
select * from Patient
where IDPatient = @id
end

go
create proc getTreatmentForApp
@id int
as
begin
select t.IDTreatment,t.SpecialTreatmentID,t.MedicalPerscriptionID,t.GeneralTreatmentID,t.AppointmentID,t.Diagnosis,p.* from Treatment as t
inner join Appointment as a
on a.IDAppointment=t.AppointmentID
inner join Patient as p
on p.IDPatient=t.PatientID
inner join GeneralTreatment as gt
on gt.IDGeneralTreatment=t.GeneralTreatmentID
inner join MedicalPerscription as m
on m.IDMedicalPerscription=t.MedicalPerscriptionID
where a.IDAppointment=2
end
go
create proc getBasicComplaints
@id int
as
begin
select * from BasicComplaints
where IDBasicComplaints=@id
end
go
go
create proc getContactDetails
@id int
as
begin
select * from ContactDetails
where IDContactDetails=@id
end
go
go
create proc getGeneralTreatment
@id int
as
begin
select * from GeneralTreatment
where IDGeneralTreatment=@id
end
go
go
create proc getImportantMedCom
@id int
as
begin
select * from ImportantMedicalComplaints
where IDImportantMedicalComplaints=@id
end
go
go
create proc getLifeStyle
@id int
as
begin
select * from Lifestyle
where IDLifestyle=@id
end
go
go
create proc getMedicalPerscription
@id int
as
begin
select * from MedicalPerscription
where IDMedicalPerscription=@id
end
go
go
create proc getNextOfKin
@id int
as
begin
select * from NextOfKin
where IDNextOfKin=@id
end
go
create proc getPersonAddress
@id int
as
begin
select * from PersonAddress
where IDPersonAddress=@id
end
go
go
create proc getPersonalDetails
@id int
as
begin
select * from PersonalDetails
where IDPersonalDetails=@id
end
go
go
create proc getProfessionDetails
@id int
as
begin
select * from ProfessionDetails
where IDProfessionDetails=@id
end
go
create proc getSpecialTreatment
@id int
as
begin
select * from SpecialTreatment
where IDSpecialTreatment=@id
end
go
create proc getAppointment
@id int
as
begin
select * from Appointment
where @id=IDAppointment
end
go
create proc updateBasicComplaints
@id int,
@soc nvarchar(50),
@hopt nvarchar(50),
@poht nvarchar(50)
as
begin
update BasicComplaints set HistoryOfPreviousTreatment=@hopt,StatementOfComplaint=@soc,
PhysicianOrHospitalTreated=@poht
where IDBasicComplaints=@id
end
go
create proc updateContactDetails
@id int,
@tel1 nvarchar(50),
@tel2 nvarchar(50),
@mob nvarchar(50),
@pager nvarchar(50),
@fax nvarchar(50),
@email nvarchar(50)
as
begin
update ContactDetails set TelephoneHome=@tel2,TelephoneWork=@tel1,
Mobile=@mob,Pager=@pager,Fax=@fax,Email=@email
where IDContactDetails=@id
end
go
create proc updateGeneralTreatment
@id int,
@tot nvarchar(50)
as
begin
update GeneralTreatment set TypeOfTreatment=@tot
where IDGeneralTreatment=@id
end
go
create proc updateImportantMedicalComplaint
@id int,
@a nvarchar(100),
@b nvarchar(100),
@c nvarchar(100),
@d nvarchar(100),
@e nvarchar(100),
@f nvarchar(100),
@g nvarchar(100),
@h nvarchar(100),
@i nvarchar(100),
@j nvarchar(100),
@k nvarchar(100)
as
begin
update ImportantMedicalComplaints set
Diabetic=@a,
Hypertensive=@b,
CardiacCondition=@c,
RespiratoryCondition=@d,
DigestiveCondition=@e,
OrthopedicCondition=@f,
MuscularCondition=@g,
NeurologicalCondition=@h,
KnownAllergies=@i,
AdverseReactionToDrugs=@j,
HistoryOfMajorSurgeries=@k
where IDImportantMedicalComplaints=@id
end
go
create proc updateLifestyle
@id int,
@a bit,
@b bit,
@c bit,
@d nvarchar(50),
@e nvarchar(50),
@f nvarchar(50),
@g nvarchar(50),
@h nvarchar(50)
as
begin
update Lifestyle set Vegetarian=@a,
Smoker=@b,
ConsumeAlcoholicBeverage=@c,
Stimulans=@d,
ConsumptionOfCoffeePerDay=@e,
ConsumptionOfSoftDrinksPerDay=@f,
RegularityOfMeals=@g,
EatingHomeOrOutside=@h
where IDLifestyle=@id
end
go
create proc updateMedicalPerscription
@id int,
@name nvarchar(50)
as
begin
update MedicalPerscription set Name=@name
where IDMedicalPerscription=@id
end
go
create proc updateNextOfKin
@id int,
@fn nvarchar(50),
@mn nvarchar(50),
@sn nvarchar(50),
@rwp nvarchar(50)
as
begin
update NextOfKin set FirstName=@fn,MiddleName=@mn,
Surname=@sn,RelationshipWithPatient=@rwp
where IDNextOfKin=@id
end
go
create proc updatePayment
@id int,
@amount money
as
begin
update Payment set Amount=@amount
where IDPayment=@id
end
go
create proc addToPayment
@id int,
@amount money
as
begin
update Payment set Amount+=@amount
where IDPayment=@id
end
go
create proc updatePersonAddress
@id int,
@a nvarchar(10),
@b nvarchar(50),
@c nvarchar(50),
@d nvarchar(50),
@e nvarchar(50),
@f nvarchar(50)
as
begin
update PersonAddress set DoorNumber=@a,
Street=@b,Area=@c,
City=@d,State=@e,PinCode=@f
where IDPersonAddress=@id
end
go
create proc updatePersonalDetails
@id int,
@a nvarchar(50),
@b int,
@c int,
@d int,
@e nvarchar(5)
as
begin
update PersonalDetails set MaritalStatus=@a,
NoOfDependents=@b,
HeightInCm=@c,WeightInKg=@d,
BloodType=@e
where IDPersonalDetails=@id
end
go
create proc updateProfessionDetails
@id int,
@a nvarchar(50),
@b money
as
begin
update ProfessionDetails set Occupation=@a,
GrossAnnualIncome=@b
where IDProfessionDetails=@id
end
go
create proc updateSpecialTreatment
@id int,
@a nvarchar(max)
as
begin
update SpecialTreatment set TypeOfTreatment=@a
where IDSpecialTreatment=@id
end
go
create proc insertSpecialization
@a nvarchar(50)
as
begin
insert into Specialization(NameOfSpecialization) values(@a)
end
go
create proc updateTreatment
@id int,
@a nvarchar(50),
@idmed int
as
begin
update Treatment set Diagnosis=@a,MedicalPerscriptionID=@idmed
where IDTreatment=@id
end
go
create proc updateSpecialization
@id int,
@name nvarchar(50)
as
begin
update Specialization set NameOfSpecialization=@name
where IDSpecialization=@id
end
go
create proc insertMedicalPerscription
@name nvarchar(50),
@id int OUTPUT
as
begin
insert into MedicalPerscription(Name) values (@name)
select @id=SCOPE_IDENTITY() from MedicalPerscription
end
go
create proc insertBasicComplaint
@soc nvarchar(50),
@hopt nvarchar(50),
@poht nvarchar(50),
@id int OUTPUT
as
begin
insert into BasicComplaints(StatementOfComplaint,HistoryOfPreviousTreatment,
PhysicianOrHospitalTreated) values (@soc,@hopt,@poht)
select @id=SCOPE_IDENTITY() from BasicComplaints
end
go
create proc insertContactDetail
@tel1 nvarchar(50),
@tel2 nvarchar(50),
@mob nvarchar(50),
@pager nvarchar(50),
@fax nvarchar(50),
@email nvarchar(50),
@id int OUTPUT
as
begin
insert into ContactDetails(TelephoneWork, TelephoneHome, Mobile, Pager, Fax, Email)
values (@tel1,@tel2,@mob,@pager,@fax,@email)
select @id=SCOPE_IDENTITY() from ContactDetails
end
go
create proc insertImportantMedComp
@a nvarchar(100),
@b nvarchar(100),
@c nvarchar(100),
@d nvarchar(100),
@e nvarchar(100),
@f nvarchar(100),
@g nvarchar(100),
@h nvarchar(100),
@i nvarchar(100),
@j nvarchar(100),
@k nvarchar(100),
@id int OUTPUT
as
begin
insert ImportantMedicalComplaints(Diabetic, Hypertensive, CardiacCondition, RespiratoryCondition, DigestiveCondition, 
OrthopedicCondition, MuscularCondition, NeurologicalCondition, KnownAllergies, AdverseReactionToDrugs, HistoryOfMajorSurgeries)
values(@a,@b,@c,@d,@e,@f,@g,@h,@i,@j,@k)
select @id=SCOPE_IDENTITY() from ImportantMedicalComplaints
end
go
create proc insertLifestyle
@a bit,
@b bit,
@c bit,
@d nvarchar(50),
@e nvarchar(50),
@f nvarchar(50),
@g nvarchar(50),
@h nvarchar(50),
@id int OUTPUT
as
begin
insert into Lifestyle(Vegetarian, Smoker, ConsumeAlcoholicBeverage, Stimulans, ConsumptionOfCoffeePerDay, ConsumptionOfSoftDrinksPerDay, RegularityOfMeals, EatingHomeOrOutside)
values (@a,@b,@c,@d,@e,@f,@g,@h)
select @id=SCOPE_IDENTITY() from Lifestyle
end
go
create proc insertNextOfKin
@a nvarchar(50),
@b nvarchar(50),
@c nvarchar(50),
@d nvarchar(50),
@id int OUTPUT
as
begin
insert into NextOfKin(FirstName, MiddleName, Surname, RelationshipWithPatient)
values (@a,@b,@c,@d)
select @id= SCOPE_IDENTITY() from NextOfKin
end
go
create proc insertPayment
@amount money,
@id int output
as
begin
insert into Payment(Amount) values (@amount)
select @id=SCOPE_IDENTITY() from Payment
end
go
create proc insertPersonAddress
@a nvarchar(50),
@b nvarchar(50),
@c nvarchar(50),
@d nvarchar(50),
@e nvarchar(50),
@f nvarchar(50),
@id int output
as
begin
insert into PersonAddress(DoorNumber, Street, Area, City, State, PinCode)
values (@a,@b,@c,@d,@e,@f)
select @id=SCOPE_IDENTITY() from PersonAddress
end
go
create proc insertPersonalDetails
@a nvarchar(50),
@b int,
@c int,
@d int,
@e nvarchar(5),
@id int output
as
begin
insert into PersonalDetails(MaritalStatus, NoOfDependents, HeightInCm,
WeightInKg,BloodType)
values (@a,@b,@c,@d,@e)
select @id=SCOPE_IDENTITY() from PersonalDetails
end
go
create proc insertProfessionDetails
@a nvarchar(50),
@b money,
@id int output
as
begin
insert into ProfessionDetails(Occupation,GrossAnnualIncome)
values (@a,@b)
select @id=SCOPE_IDENTITY() from ProfessionDetails
end
go
create proc insertSpecialTreatment
@a nvarchar(max),
@id int output
as
begin
insert SpecialTreatment(TypeOfTreatment)
values (@a)
select @id=SCOPE_IDENTITY() from SpecialTreatment
end
go
create proc getAppForPatient
@id int
as
begin
select a.* from Appointment as a
inner join Treatment as t 
on t.AppointmentID=a.IDAppointment
inner join Patient as p
on p.IDPatient=t.PatientID
where IDPatient=@id
end
go
create proc getTestsForPatient
@id int
as
begin
select t.* from Test as t
inner join Patient as p
on p.IDPatient=t.PatientID
where t.PatientID=@id
end
go
create proc insertTest
@name nvarchar(50),
@result nvarchar(50),
@idP int
as
begin
insert into Test(Name,Result,PatientID) values (@name,@result,@idP)
end
go
create proc updateTest
@id int,
@name nvarchar(50),
@result nvarchar(50)
as
begin
update Test set Name=@name,Result=@result
where PatientID=@id
end
go
create proc getMedPerForPatient
@id int
as
begin
select m.* from MedicalPerscription as m
inner join Treatment as t
on m.IDMedicalPerscription=t.MedicalPerscriptionID
where t.PatientID = @id
end
go
insert into Patient(DateOfCreation, FirstName, Surname, Sex, DOB)
values ('1.1.2019','Dario','Simic','Male',GETDATE())
go
insert into MedicalPerscription(Name) values ('Xanax')
go
insert into Specialization values ('Neurolog')
go
insert into GeneralPhysician(FirstName, Surname, Title) values ('Ruzak','Nasic','Dr.')
go
insert into Payment(Amount) values(100)
go
insert into Appointment(DayOfAppointment, PaymentID) values (GETDATE(),1)
go
insert into GeneralTreatment(TypeOfTreatment, GeneralPhysicianID) values ('Heart Massage',1)
go
insert into Specialist(FirstName,Surname,Title,SpecializationID) values ('Darko','Maric','Dr.',1)
go
insert into SpecialTreatment(TypeOfTreatment,SpecialistID) values ('Pulmologija', 1)
go
insert into Test(Name,Result,PatientID) values ('Heart Check','High blood pressure',1)
go
insert into Treatment(PatientID, SpecialTreatmentID, GeneralTreatmentID, AppointmentID, MedicalPerscriptionID, Diagnosis)
values  (1,1,1,1,1,'Boli')
go
insert into Patient(DateOfCreation, FirstName, Surname, Sex, DOB)
values ('2.6.2019','Ivan','Ivic','Male',GETDATE())
go
insert into MedicalPerscription(Name) values ('Normabel, Ibuprofen')
go
insert into Specialization values ('Kardiolog')
go
insert into GeneralPhysician(FirstName, Surname, Title) values ('Zeljko','Grgic','Dr. Sc.')
go
insert into Payment(Amount) values(200)
go
insert into Appointment(DayOfAppointment, PaymentID) values (GETDATE(),2)
go
insert into GeneralTreatment(TypeOfTreatment, GeneralPhysicianID) values ('liver Check',2)
go
insert into Specialist(FirstName,Surname,Title,SpecializationID) values ('Ante','Majic','Dr.',2)
go
insert into SpecialTreatment(TypeOfTreatment,SpecialistID) values ('Liverologija', 2)
go
insert into Test(Name,Result,PatientID) values ('Liver Check','Bad',2)
go
insert into Treatment(PatientID, SpecialTreatmentID, GeneralTreatmentID, AppointmentID, MedicalPerscriptionID, Diagnosis)
values  (2,2,2,2,2,'Liver problems')
go