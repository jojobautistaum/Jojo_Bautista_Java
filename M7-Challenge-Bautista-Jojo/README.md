# Online Music Store

[![License](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

## Description

This app demonstrate the use of microservices which are deployed in Heroku

## **Music Store Paths**

### 1. Catalog Service (Album, Track, Artist, Label ): https://jojo-music-store-catalog.herokuapp.com/
> **GET**
> - ***/album***
> - ***/album/{id}***
> - ***/artist***
> - ***/artist/{id)***
> - ***/label***
> - ***/label/{id}***
> - ***/track***
> - ***/track/{id}***

> **POST**
> - ***/album*** </br>
>     *Fields:*
>     * title
>     * artistId
>     * releaseDate      e.g. "2012-11-05"
>     * labelId
>     * listPrice
>    
> - ***/artist*** </br>
>     *Fields:*
>     * name
>     * instagram
>     * twitter
>     
> - ***/label*** <br>
>     *Fields:*
>     * name
>     * website
> 
> - ***/track*** <br>
>     *Fields:*
>     * albumId
>     * title
>     * runTime

> **PUT**
> - ***/album*** </br>
>     *Fields:*
>     * id
>     * title
>     * artistId
>     * releaseDate      e.g. "2012-11-05"
>     * labelId
>     * listPrice
>    
> - ***/artist*** </br>
>     *Fields:*
>     * id
>     * name
>     * instagram
>     * twitter
>     
> - ***/label*** <br>
>     *Fields:*
>     * id
>     * name
>     * website
> 
> - ***/track*** <br>
>     *Fields:*
>     * id
>     * albumId
>     * title
>     * runTime

> **DELETE**
> - ***/album/{id}***
> - ***/artist/{id)***
> - ***/label/{id}***
> - ***/track/{id}***

### 2. Recommendation Service (Album, Track, Artist, Label ): https://jojo-musicstore-recommendation.herokuapp.com/
> **GET**
> - ***/albumRecommendation***
> - ***/albumRecommendation/{id}***
> - ***/artistRecommendation***
> - ***/artistRecommendation/{id)***
> - ***/labelRecommendation***
> - ***/labelRecommendation/{id}***
> - ***/trackRecommendation***
> - ***/trackRecommendation/{id}***

> **POST**
> - ***/albumRecommendation*** </br>
>     *Fields:*
>     * albumId
>     * userId
>     * liked 
>        
> - ***/artistRecommendation*** </br>
>     *Fields:*
>     * artistId
>     * userId
>     * liked 
>     
> - ***/labelRecommendation*** <br>
>     *Fields:*
>     * labelId
>     * userId
>     * liked 
> 
> - ***/trackRecommendation*** <br>
>     *Fields:*
>     * trackId
>     * userId
>     * liked 

> **PUT**
> - ***/albumRecommendation*** </br>
>     *Fields:*
>     * id
>     * albumId
>     * userId
>     * liked 
>        
> - ***/artistRecommendation*** </br>
>     *Fields:*
>     * id
>     * artistId
>     * userId
>     * liked 
>     
> - ***/labelRecommendation*** <br>
>     *Fields:*
>     * id
>     * labelId
>     * userId
>     * liked 
> 
> - ***/trackRecommendation*** <br>
>     *Fields:*
>     * id
>     * trackId
>     * userId
>     * liked 

> **DELETE**
> - ***/albumRecommendation/{id}***
> - ***/artistRecommendation/{id)***
> - ***/labelRecommendation/{id}***
> - ***/trackRecommendation/{id}***


## **Tech/Framework/Topic**

> - Spring Boot
> - MVC
> - MockMvc Test
> - Mockito Test
> - Exception Error Handling
> - Throws Customized Exception
> - Microservices
> - Repository
> - Spring JPA
> - JSR 303 validation
> - MySQL
> - REST API / JSON
> - Heroku
> - Containerization

## License <a id="license"></a>

This project is licensed under the MIT license

## Link <a id="link"></a>

[Check my portfolio](https://full-stack-developer-react.herokuapp.com/)

## Questions <a id="questions"></a>

For questions about my work experience or a latest copy of my resume, please reachout to me via email at gjojob@yahoo.com or to my linkedin account https://www.linkedin.com/in/jojo-bautista/
