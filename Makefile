
# Default host used
HOST = 				localhost # maglethong.ddns.net #

# Default port used
PORT = 				12377 

# SQL Driver Package
DRIVER_PACKAGE = 	lib/postgresql-9.4-1205.jdbc4.jar

# SQL Driver Load instruction
DRIVER_LOAD = 		org.postgresql.Driver


all: bs bc

server: bs
bs: 
	@javac -cp bin -d bin src/server/database/*.java
	@javac -cp bin -d bin src/server/requests/*.java
	@javac -cp bin -d bin src/server/*.java

client: bc
bc:
#	@javac -cp bin -d bin src/client/core/base/*.java
#	@javac -cp bin -d bin src/client/core/requests/*.java
	@javac -cp bin -d bin src/client/*.java


runserver: rs
rs:
	@java -cp bin:$(DRIVER_PACKAGE) -Djwbc.drivers=$(DRIVER_LOAD) server.Server $(PORT)


runterminalclient: rtc
rtc:
	@java -cp bin client.UserConnection andy@yopmail.com qwerty $(HOST) $(PORT)
