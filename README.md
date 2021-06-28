# spring-hateoas-example
Simple Spring HATEOAS example


<br />

# Table of contents
- [spring-hateoas-example](#spring-hateoas-example)
- [Table of contents](#table-of-contents)
- [URL hostname interpolation](#url-hostname-interpolation)
- [Pagination](#pagination)
    - [2nd page, 20 elements](#2nd-page-20-elements)
- [Pagination and sorting](#pagination-and-sorting)
    - [3rd page, 10 elements, sorting by name desc](#3rd-page-10-elements-sorting-by-name-desc)
    - [Get top 1 oldest person](#get-top-1-oldest-person)

<br />

# URL hostname interpolation

<br />

> curl -X 'GET' http://KL-WIN:8080/person/1

``` json
{
   "(...)": "(...)",
   "_links":{
      "self":{
         "href":"http://KL-WIN:8080/person/1"
      },
      "cars":{
         "href":"http://KL-WIN:8080/person/1/cars"
      }
   }
}
```

<br />

> curl -X 'GET' http://localhost:8080/person/1

``` json
{
   "(...)": "(...)",
   "_links":{
      "self":{
         "href":"http://localhost:8080/person/1"
      },
      "cars":{
         "href":"http://localhost:8080/person/1/cars"
      }
   }
}
```

<br />

> curl -X 'GET' http://127.0.0.1:8080/person/1

``` json
{
   "(...)": "(...)",
   "_links":{
      "self":{
         "href":"http://127.0.0.1:8080/person/1"
      },
      "cars":{
         "href":"http://127.0.0.1:8080/person/1/cars"
      }
   }
}
```

<br />

# Pagination

<br />

### 2nd page, 20 elements
> curl -X 'GET' http://127.0.0.1:8080/person?page=1&size=20

``` json
{
   "_embedded":{
      "personModelList":[
         {
            "id":1,
            "name":"Ola Bosco",
            "birthdate":"1931-02-20",
            "_links":{
               "self":{
                  "href":"http://127.0.0.1:8080/person/1"
               },
               "cars":{
                  "href":"http://127.0.0.1:8080/person/1/cars"
               }
            }
         },
         {
            "(...)":"(...)"
         }
      ]
   },
   "_links":{
      "first":{
         "href":"http://127.0.0.1:8080/person?page=0&size=20"
      },
      "self":{
         "href":"http://127.0.0.1:8080/person?page=0&size=20"
      },
      "next":{
         "href":"http://127.0.0.1:8080/person?page=1&size=20"
      },
      "last":{
         "href":"http://127.0.0.1:8080/person?page=19&size=20"
      }
   },
   "page":{
      "size":20,
      "totalElements":392,
      "totalPages":20,
      "number":0
   }
}
```

<br />

# Pagination and sorting

<br />

### 3rd page, 10 elements, sorting by name desc
> curl -X 'GET' http://127.0.0.1:8080/person?page=2&size=10&sort=name,desc

``` json
{
   "_embedded":{
      "personModelList":[
         {
            "id":375,
            "name":"Sharlene Hamill",
            "(...)":"(...)"
         },
         {
            "id":370,
            "name":"Shante Fadel",
            "(...)":"(...)"
         }
      ]
   },
   "_links":{
      "first":{
         "href":"http://127.0.0.1:8080/person?page=0&size=20&sort=name,desc"
      },
      "prev":{
         "href":"http://127.0.0.1:8080/person?page=1&size=20&sort=name,desc"
      },
      "self":{
         "href":"http://127.0.0.1:8080/person?page=2&size=20&sort=name,desc"
      },
      "next":{
         "href":"http://127.0.0.1:8080/person?page=3&size=20&sort=name,desc"
      },
      "last":{
         "href":"http://127.0.0.1:8080/person?page=19&size=20&sort=name,desc"
      }
   },
   "page":{
      "size":20,
      "totalElements":392,
      "totalPages":20,
      "number":2
   }
}
```

<br />

### Get top 1 oldest person
> curl -X 'GET' http://127.0.0.1:8080/person?page=0&size=1&sort=birthdate,desc
``` json
{
   "_embedded":{
      "personModelList":[
         {
            "id":305,
            "name":"Pierre Gottlieb",
            "birthdate":"2003-06-20",
            "_links":{
               "self":{
                  "href":"http://127.0.0.1:8080/person/305"
               },
               "cars":{
                  "href":"http://127.0.0.1:8080/person/305/cars"
               }
            }
         }
      ]
   },
   "_links":{
      "first":{
         "href":"http://127.0.0.1:8080/person?page=0&size=1&sort=birthdate,desc"
      },
      "self":{
         "href":"http://127.0.0.1:8080/person?page=0&size=1&sort=birthdate,desc"
      },
      "next":{
         "href":"http://127.0.0.1:8080/person?page=1&size=1&sort=birthdate,desc"
      },
      "last":{
         "href":"http://127.0.0.1:8080/person?page=391&size=1&sort=birthdate,desc"
      }
   },
   "page":{
      "size":1,
      "totalElements":392,
      "totalPages":392,
      "number":0
   }
}
```