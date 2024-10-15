
# Traffic Management API

## Descrição

Esta API foi construída utilizando **Spring Boot** e **Maven**. Ela fornece funcionalidades para gerenciar semáforos e sensores.

## Requisitos

- **Java 17**
- **Maven 3.9.9** ou superior
- **Spring Boot 3.3.1**
- **Banco de Dados Oracle**

## Configuração

1. **Clone o repositório**

   ```bash
   git clone https://github.com/devitalo97/traffic-management-fiap.git
   cd traffic-management-fiap
   ```

2. **Configurar variáveis de ambiente**

   Certifique-se de configurar as variáveis de ambiente necessárias. Você pode definir as configurações no arquivo `application.properties`.

   Exemplo de variáveis:
   ```properties
   spring.application.name=traffic_management_spring_boot
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
   spring.flyway.baselineOnMigrate=
   spring.flyway.locations=
   spring.jpa.show-sql=
   spring.jpa.properties.hibernate.format_sql=
   server.error.include-message=
   server.error.include-stacktrace=
   server.error.include-binding-errors=
   server.address=
   spring.security.user.name=
   spring.security.user.password=
   jwt.secret=
   ```

3. **Instalar dependências**

   Execute o Maven para baixar as dependências:

   ```bash
   mvn clean install
   ```

## Executando a API

Para rodar a API localmente, utilize o seguinte comando:

```bash
mvn spring-boot:run
```

A API estará acessível no endereço: `http://localhost:8080`

## Documentação da API

### Endpoints
#### Traffic Light
| Método | Endpoint       | Descrição                          | Exemplo |
|--------|----------------|------------------------------------|-------------------------|
| GET    | `/api/traffic-light`   | Retorna todos os itens             | -                       |
| GET    | `/api/traffic-light/{id}` | Retorna um item pelo ID           | -                       |
| POST   | `/api/traffic-light`   | Cria um novo item                  | `{ "lat": -18.739267137971055, "lng": -39.74751897551345, "status": "red" }`  |
| PUT    | `/api/traffic-light` | Atualiza um item existente        | `{ "lat": -29.3607451963726, "lng": -51.09864755838017, "status": "yellow", "id": 8 }`  |
| DELETE | `/api/traffic-light/{id}` | Remove um item                   | -                       |

#### Auth
| Método | Endpoint       | Descrição                          | Exemplo |
|--------|----------------|------------------------------------|-------------------------|
| POST    | `/auth/sign-up` | Retorna um item pelo ID           | `{ "name": "rm99999", "email": "rm99999@fiap.com.br", "password": "123456@", "role": "ACCOUNT" }` |
| POST   | `/auth/sign-in`   | Retorna todos os itens             | `{ "email": "rm99999@fiap.com.br", "password": "123456@" }`  |


## Compilação para Produção

Para gerar o arquivo `.jar` da aplicação:

```bash
mvn clean package
```

### Docker e Docker Compose

Este projeto utiliza Docker para simplificar a configuração e o deployment tanto em ambiente local quanto em produção. A seguir estão as instruções e explicações sobre os arquivos Docker relacionados.

### 1. `docker-compose.local.yml`

Este arquivo configura o ambiente de desenvolvimento local. Ele cria um contêiner Docker para a aplicação, que será acessível através da porta `8080` no seu ambiente local. O Docker Compose irá utilizar o arquivo `Dockerfile.local` para construir o ambiente.

- **Comando para rodar em ambiente local**:
  ```bash
  docker-compose -f docker/docker-compose.local.yml up --build -d
  ```

### 2. `docker-compose.prod.yml`

Este arquivo define o ambiente de produção. Em vez de construir a aplicação do zero, ele usa uma imagem Docker pré-construída que contém a aplicação já empacotada e pronta para rodar. Essa imagem é puxada do Docker Hub.

- **Comando para rodar em produção**:
  ```bash
  docker-compose -f docker/docker-compose.prod.yml up -d
  ```

### 3. `Dockerfile.local`

Este Dockerfile é utilizado para construir o contêiner da aplicação em ambiente de desenvolvimento. O processo de build inclui:

1. Utilizar uma imagem do Maven com JDK 21 para construir a aplicação.
2. Copiar as dependências e o código-fonte para o contêiner.
3. Executar o Maven para empacotar a aplicação sem rodar os testes.
4. Usar uma imagem JDK leve (Alpine) para rodar o JAR gerado.

- **Comando para construir a aplicação localmente com Docker**:
  ```bash
  docker build -f docker/Dockerfile.local -t traffic-management:local .
  ```

### 4. `Dockerfile.prod`

Este Dockerfile é utilizado no ambiente de produção. Ao contrário do ambiente local, ele apenas copia o arquivo JAR já empacotado e o executa em uma imagem leve baseada no JDK 21 (Alpine). Isso garante que a aplicação em produção seja otimizada e utilize menos recursos.

- **Comando para construir a imagem de produção**:
  ```bash
  docker build -f docker/Dockerfile.prod -t traffic-management:prod .
  ```


### Executando a Aplicação com `docker run`

Caso você prefira rodar a aplicação diretamente sem utilizar o Docker Compose, o comando `docker run` pode ser usado. Abaixo estão os exemplos de como rodar a aplicação em diferentes ambientes:

#### 1. Rodando em Ambiente de Desenvolvimento

Para rodar a aplicação utilizando a imagem criada localmente a partir do `Dockerfile.local`:

```bash
docker run -p 8080:8080 traffic-management:local
```

Esse comando mapeia a porta `8080` do contêiner para a porta `8080` da sua máquina local, permitindo acessar a aplicação em `http://localhost:8080`.

#### 2. Rodando em Ambiente de Produção

Se você já tiver construído a imagem de produção, use o seguinte comando para rodar a aplicação:

```bash
docker run -p 8080:8080 traffic-management:prod
```

Assim como no ambiente de desenvolvimento, o contêiner estará disponível em `http://localhost:8080`, mas usando a configuração otimizada para produção.

> **Nota**: Caso você queira rodar a aplicação utilizando a imagem de produção que já está no Docker Hub, pode utilizar o seguinte comando:
> ```bash
> docker run -p 8080:8080 rm93287/traffic_management:10f4fd8845cc47d5f936702dce5a7e6fae1fe012
> ```
