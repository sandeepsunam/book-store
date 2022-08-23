# book-store
Hertz assignment

## tools
- maven
- java 11 or above
- postman / insomnia

## step to run
- download the zip
- unzip the folder
- cd into the root folder
- execute 'mvn clean install' (installs all the dependencies)
- execute 'java -jar ./target/bookstore-*'

## Resources

### Members : 

Post: http://localhost:8080/members
payload example :
{
	"firstName" : "sandeep",
	"lastName"  : "sunam"
}

Get: http://localhost:8080/members

Get: http://localhost:8080/members/{memberId}
example: http://localhost:8080/members/1

## BookStore:

GET: http://localhost:8080/books (lists all the book in the store)

POST: http://localhost:8080/books (Adding books to store)
payload example:
{
	"book" : "Art of living",
	"author" : "sri sri",
	"category" : [
			"meditation",
			"well being"
		]
}

POST: http://localhost:8080/members/2/book/4 (member 2 has borrowed book 4, maximum 3 books is allowed to borrow)

PUT: http://localhost:8080/members/2/return-book (returns all the borrowed books by member 2 and sets the quantity back to 1 in the bookstore)
