# Game Store Management

[![License](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

## Description

This app demonstrate the use of microservices, Eureka server and feign client

## **Game Store URL Paths**

### 1. Service-Registry (Eureka Server): http://localhost:8761
### 2. Catalog (Console, Game, TShirt): http://localhost:7474
> **GET**
> - ***/console***
> - ***/console/{id}***
> - ***/console/manufacturer/{manufacturer}***   e.g. Sony, Microsoft, Nintendo
> - ***/game***
> - ***/game/{id}***
> - ***/game/esrbrating/{esrb}***   e.g. teens, 10+, everyone
> - ***/game/title/{title}***       e.g. "Super Mario", "Need For Speed Unbound"
> - ***/game/studio/{studio}***     e.g. "Electornic Arts", "Blizzard"
> - ***/tshirt***
> - ***/tshirt/{id}***
> - ***/tshirt/size/{size}***       e.g. Large, Medium, Small
> - ***/tshirt/color/{color}***     e.g. Blue, Red, Yellow, Green

> **POST**
> - ***/console*** </br>
>     *Fields:*
>     * model
>     * manufacturer
>     * memoryAmount
>     * processor
>     * price
>     * quantity
>
> - ***/game*** </br>
>     *Fields:*
>     * title
>     * esrbRating
>     * description
>     * studio
>     * price
>     * quantity
>
> - ***/tshirt*** <br>
>     *Fields:*
>     * size
>     * color
>     * description
>     * price
>     * quantity

> **PUT**
> - ***/console*** </br>
>     *Fields:*
>     * id
>     * model
>     * manufacturer
>     * memoryAmount
>     * processor
>     * price
>     * quantity
>
> - ***/game*** </br>
>     *Fields:*
>     * id
>     * title
>     * esrbRating
>     * description
>     * studio
>     * price
>     * quantity
>
> - ***/tshirt*** <br>
>     *Fields:*
>     * id
>     * size
>     * color
>     * description
>     * price
>     * quantity

> **DELETE**
> - ***/console/{id}***
> - ***/game/{id}***
> - ***/tshirt/{id}***

### 3. Invoice (with Feign client for Console, Game, TShirt): http://localhost:7475
> **GET**
> - ***/invoice***
> - ***/invoice/{id}***
> - ***/invoice/cname/{name}***    **where:** name is a customer name

> **POST**
> - invoice

## **Tech/Framework/Topic**

> - Spring Boot
> - MVC
> - MockMvc Test
> - Mockito Test
> - Exception Error Handling
> - Throws Customized Exception
> - Microservices
> - Service Registry (Eureka Server)
> - Feign client
> - Service layer
> - View model
> - Repository
> - Spring JPA
> - JSR 303 validation
> - MySQL
> - REST API / JSON

## License <a id="license"></a>

This project is licensed under the MIT license

## Link <a id="link"></a>

[Check my portfolio](https://full-stack-developer-react.herokuapp.com/)

## Questions <a id="questions"></a>

For questions about my work experience or a latest copy of my resume, please reachout to me via email at gjojob@yahoo.com or to my linkedin account https://www.linkedin.com/in/jojo-bautista/
