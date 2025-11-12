# Food Recipes

## Tecnologias
- Java `17`
- Spring Boot `3.5.7` (Web, Data JPA, Validation)
- MapStruct `1.6.3` para mapeamento entre DTOs e entidades
- Springdoc OpenAPI `2.5.0` para documentação Swagger
- PostgreSQL 18
- Spring Data Envers para auditoria
- Maven como build tool

## Arquitetura
- Camadas: `API (Controller)` → `Service` → `Repository` → `Domain (Entities)` → `DTO/Mapper`.
- CRUD de receitas e ingredientes com relação `Receita (1) : (N) Ingrediente`.
- Exclusão lógica via flag `active` na superclasse `GenericEntity`.
- Auditoria de alterações com Envers em todas as entidades.

## Principais Componentes
- `ReceitaApi` expõe endpoints REST em `src/main/java/imd/ufrn/foodrecipes/api/ReceitaApi.java`.
- `ReceitaServiceImpl` implementa "regras de negócio" em `src/main/java/imd/ufrn/foodrecipes/domain/service/ReceitaServiceImpl.java`.
- `ReceitaRepository` herda operações e consultas ativas em `src/main/java/imd/ufrn/foodrecipes/domain/repository/ReceitaRepository.java`.
- `Receita` e `Ingrediente` modelam o domínio em `src/main/java/imd/ufrn/foodrecipes/domain/entity`.
- `ReceitaMapper` (MapStruct) em `src/main/java/imd/ufrn/foodrecipes/api/mapper/ReceitaMapper.java`.
- DTOs em `src/main/java/imd/ufrn/foodrecipes/api/dto` com validação Jakarta.

## Endpoints
- Base path: `http://localhost:8888/api`
- `POST /receitas` cria receita com ingredientes
- `GET /receitas/{id}` busca por ID
- `GET /receitas` lista paginada 
- `PUT /receitas/{id}` atualiza receita
- `DELETE /receitas/{id}` exclusão lógica

Exemplo `POST /receitas`:
```
{
  "nome": "Bolo de Cenoura",
  "categoria": "Sobremesa",
  "tempoPreparo": 60,
  "porcoes": 8,
  "instrucoes": "Misture, asse e sirva",
  "ingredientes": [
    { "nome": "Cenoura", "quantidade": "3", "unidadeMedida": "unidade" },
    { "nome": "Farinha", "quantidade": "2", "unidadeMedida": "xicaras" }
  ]
}
```

## Configuração
- Arquivo `application.yml`: `src/main/resources/application.yml`
- Banco de dados:
  - URL: `jdbc:postgresql://localhost:5433/food_recipes`
  - Usuário: `${DB_USERNAME:postgres}`
  - Senha: `${DB_PASSWORD:postgres}`
- JPA: `ddl-auto: update`, `open-in-view: false`, Dialeto PostgreSQL.
- Envers: schema de auditoria `audit`.

Crie o banco e o schema (no `psql`):
```
CREATE DATABASE food_recipes;
\c food_recipes
CREATE SCHEMA audit;
```

## Swagger (OpenAPI)
- UI: `http://localhost:8888/api/swagger-ui/index.html`
- Esquema e metadados configurados em `src/main/java/imd/ufrn/foodrecipes/config/OpenApiConfig.java`.

## Auditoria (Envers)
- Habilitada com `@Audited` nas entidades.
- Tabelas de auditoria são geradas no schema `audit` com sufixo `_AUD`.

## Exclusão Lógica
- Implementada no repositório genérico com sobrecarga de `delete*` para marcar `active=false`.
- Consultas de listagem/por ID consideram apenas registros ativos.
