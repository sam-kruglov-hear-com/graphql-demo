# Demo for graphql

Test data:

```graphql
{
    getStuff(id: 12){
       name
       subStuff{
          subName
       }
       extraStuff{
          extraName
       }
    }
}
```

## How to run

1. `./mvnw spring-boot:run`
2. open [`localhost:4000/playground`](http://localhost:4000/playground)
3. in the bottom, open HTTP HEADERS and set the mock authority to desired value
   (comma-separated list like `"""`, `"read"`, `"read,write"`).

## Todo

1. get data
2. get data when query wants more access rights. Result is null and message says access denied.
3. get data when extraStuff wants more access rights. Result contains everything else except the extraStuff and the
   error says access denied.
   
