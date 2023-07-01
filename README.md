# Vehicle Register API :car:

### Descrição do projeto :memo:

API REST que faz CRUD de cadastro de carros.

### STATUS :technologist:
- API (Finalizado)
- Testes:
    - VehicleController
    - VehicleService
    - Assemblers
- Documentação pelo SpringDoc/Swagger (Finalizado)
- Docker (Finalizado)
- Deploy AWS (Finalizado)
- Pipeline integrada ao Sonarcloud(Finalizada)

Obs: Não foi possível concluir todos os testes da API devido ao prazo, essa é a razão da pipeline estar quebrando na cobertura.

### Pré-requisitos :thumbsup:

- JDK 11 e Maven
- Banco MySQL
- IntelliJ ou Eclipse, preferencialmente

### Foi utilizado :point_down:

- Postman
- Docker

### Caso ocorra problema para subir o Docker em Windows:
- Clone com: git clone https://github.com/lucascalebe/vehicle-register.git --config core.autocrlf=input

### subir aplicação local via DOCKER :point_down:

- Iniciar docker no PC
- executar docker-compose up no diretório da raiz do projeto.
- Caso deseja subir apenas o MySql, execute docker-compose up vehicle-register-mysql

### Instalação da aplicação :point_down:

- IntelliJ/Eclipse: Importar como projeto Maven.
- mvn install

### Iniciar aplicação pela IDE :point_down:

- Rodar a classe VehicleRegisterApplication
Obs: Certifique-se de que esteja rodando o mySQL

## Comportamento da API: :anger:

`GET /vehicles?brand=FIAT&year=2023&sold=false&vehicle=argo`

##### Response

      HTTP/1.1 200 OK
      Content-Type: application/json
      
      {
        "content": [
            {
                "id": 2,
                "vehicle": "ARGO",
                "brand": "FIAT",
                "year": 2023,
                "description": "carro novo",
                "sold": false
            }
        ],
        "size": 10,
        "totalElements": 1,
        "totalPages": 1,
        "number": 0
      }



`GET /vehicles/1`

##### Response

      HTTP/1.1 200 OK
      Content-Type: application/json
      
      {
        "id": 1,
        "vehicle": "Uno Atualizado",
        "brand": "FIAT",
        "year": 2010,
        "description": "Carro verde atualizado",
        "sold": true
      }




`POST /vehicles`

#### Body
    
    {
      "vehicle": "Fusion",
      "brand": "FORD",
      "year": 2018,
      "description": "carro muito bom",
      "sold": true
    }

##### Response

    HTTP/1.1 201 CREATED
    Content-Type: application/json

    {
      "id": 3,
      "vehicle": "Fusion",
      "brand": "FORD",
      "year": 2018,
      "description": "carro muito bom",
      "sold": true
    } 




`PUT /vehicles/1`

  #### Body
    
    {
      "vehicle": "Fusion",
      "brand": "FORD",
      "year": 2018,
      "description": "atualizado",
      "sold": false
    }

##### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    {
      "id": 1,
      "vehicle": "Fusion",
      "brand": "FORD",
      "year": 2018,
      "description": "atualizado",
      "sold": false
    }



`PATCH /vehicles/1`

  #### Body
    
    {
      "year": 2002
    }

##### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    {
      "id": 1,
      "vehicle": "Fusion",
      "brand": "FORD",
      "year": 2002,
      "description": "atualizado",
      "sold": true
    }



`DELETE /vehicles/1`

  ##### Response

    HTTP/1.1 204 NO_CONTENT
    Content-Type: application/json
