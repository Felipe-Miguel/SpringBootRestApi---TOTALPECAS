# TotalPecas-Backlog

Documentação da API da Total Peças, um sistema de e-Commerce voltado totalmente para o setor de auto peças. Com a Total Peças você possuí uma plataforma voltada para o ramo, logo, você não possuí os problemas das plataformas convencionais como falta de organização em suas categorias, um sistema de transporte e cotação de frete totalmente despreparados para o mercado automotivo e além de tudo, uma plataforma que entende as necessidades do mercado e conhece o negócio. Nesta documentação, você encontrará informações detalhadas sobre as rotas disponíveis, os parâmetros necessários e as respostas retornadas pela API.

## Contribuidores
- Felipe Miguel Ortega de Souza
- Isadora Antunes Lourenço Pereira

## Endpoints
- Peca
    - [Cadastrar](#cadastrar-produto)
    - [Detalhar](#detalhar-produto)
    - [Alterar](#Alterar-produto)
    - [Excluir](#Excluir-produto)
- Usuario
    - [Cadastrar](#cadastrar-usuario)
    - [Detalhar](#detalhar-usuario)
    - [Alterar](#alterar-usuario)
    - [Excluir](#excluir-usuario)
- Carteira
    - [Cadastrar](#cadastrar-carteira)
    - [Detalhar](#detalhar-carteira)
    - [Detalhar-Todos](#listar-transações)
    - [Alterar](#alterar-carteira)
    - [Deletar](#deletar-carteira)
---

### Cadastrar produto
`POST` /api/produtos

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| categoria_id | int | sim | código de uma categoria previamente cadastrada |
| titulo | string | sim | título do produto, deve conter entre 30 e 60 caracteres |
| preco | float | sim | o valor do produto, deve ser maior que zero |
| marca_id | int | sim | código de uma marca previamente cadastrada |
| modelo_id | int | sim | código de um modelo previamente cadastrada |
| anos | ArrayList<Integer> | sim | os anos do modelo que o produto serve, não pode ultrapassar o ano atual |
| estado_id | int | sim | código de um estado previamente cadastrado |


**Exemplo de corpo de requisição**

```json
{
  "categoria_id": 1,
  "titulo": "Parachoque dianteiro freemont",
  "preco": 100.00,
  "marca_id": 1,
  "modelo_id": 1,
  "anos": [2012,2013,2014],
  "estado_id": 1,
  
}
```
**Exemplo de corpo de resposta**

```json
{
  "id":1,
  "categoria_id": 1,
  "titulo": "Parachoque dianteiro freemont",
  "preco": 100.00,
  "marca_id": 1,
  "modelo_id": 1,
  "anos": [2012,2013,2014],
  "estado_id": 1,
  
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta|
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---

### Detalhar produto

`GET` /api/produtos/{id}
    
**Parâmetros de caminho**

id - código do produto a ser detalhado

**Exemplo de Corpo de resposta** 
```json
{
  "categoria":{
      "categoria_id": 1,
      "nome": "Parachoque"
  },
  "titulo": "Parachoque dianteiro freemont",
  "preco": 100.00,
  "marca":{
      "marca_id": 1,
      "nome": "Fiat"
  },
  "modelo":{
      "modelo_id": 1,
      "nome": "Freemont"
  },
  "anos":[2014,2015,2016],
  "estado":{
      "estado_id": 1,
      "nome": "Usado"
    }
  }
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta| 
| 204 | A requisição GET foi bem-sucedida, mas não há conteúdo a ser retornado na resposta| 
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

### Listar produto

`GET` /api/produtos

**Exemplo de Corpo de resposta** 
```json
[
{
  "id":1,
  "categoria":{
      "categoria_id": 1,
      "nome": "Parachoque"
  },
  "titulo": "Parachoque dianteiro freemont",
  "preco": 100.00,
  "marca":{
      "marca_id": 1,
      "nome": "Fiat"
  },
  "modelo":{
      "modelo_id": 1,
      "nome": "Freemont"
  },
  "anos":[2014,2015,2016],
  "estado":{
      "estado_id": 1,
      "nome": "Usado"
    }
  },
    {
  "id":2,
  "categoria":{
      "categoria_id": 2,
      "nome": "Farol"
  },
  "titulo": "Farol dianteiro freemont",
  "preco": 200.00,
  "marca":{
      "marca_id": 1,
      "nome": "Fiat"
  },
  "modelo":{
      "modelo_id": 1,
      "nome": "Freemont"
  },
  "anos":[2010,2011,2012],
  "estado":{
      "estado_id": 2,
      "nome": "Novo"
    }
  }
 ]
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta| 
| 204 | A requisição GET foi bem-sucedida, mas não há conteúdo a ser retornado na resposta| 
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---

### Alterar produto

`PUT` /api/produtos/{id}
 
    **Parâmetros de caminho**

id - código do produto a ser alterado

**Exemplo de corpo de requisição**

```json
{
   "categoria_id": 1,
   "titulo": "Novo nome do produto",
   "preco": 149.99,
   "marca_id": 456,
   "modelo_id": 789,
   "anos": [2019, 2020, 2021],
   "estado_id": 2
}
```
    
**Exemplo de corpo de resposta**

```json
{
   "id":1,
   "categoria_id": 1,
   "titulo": "Novo nome do produto",
   "preco": 149.99,
   "marca_id": 456,
   "modelo_id": 789,
   "anos": [2019, 2020, 2021],
   "estado_id": 2
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição PUT foi bem-sucedida e o recurso foi atualizado com sucesso|
| 201 | A requisição PUT foi bem-sucedida e um novo recurso foi criado|
| 400 | A requisição PUT não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição PUT requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição PUT é proibida pelo servidor, geralmente porque o usuário não tem permissão para atualizar o recurso|
| 404 | O recurso que a requisição PUT está tentando atualizar não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição PUT|

---

### Excluir produto

`DELETE` /api/produtos/{id}

**Parâmetros de caminho**

id - código do produto a ser excluido

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---


### Cadastrar usuario
`POST` /api/usuarios

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| nome | string | sim | nome do usuário, deve conter pelo menos dois nomes |
| email | string | sim | email do usuário |
| celular | string | sim | celular do usuário |
| cpf/cnpj | string | sim | cpf ou cnpj do usuário |
| cep | string | sim |cep do endereço do usuário |
| logradouro | string | sim | nome do logradouro do usuário |
| numero | int | sim | número do logradouoro do usuário |


**Exemplo de corpo de requisição**

```json
{
  "nome": "Nome do usuario",
  "email": "exemplo@gmail.com",
  "celular": "(11)99999-9999",
  "cpf": "123.456.789-10",
  "cep": "12345-678",
  "logradouro": "Avenida Paulista",
  "numero": 123
  
}
```
    
**Exemplo de corpo de resposta**

```json
{
  "id": 1,
  "nome": "Nome do usuario",
  "email": "exemplo@gmail.com",
  "celular": "(11)99999-9999",
  "cpf": "123.456.789-10",
  "cep": "12345-678",
  "logradouro": "Avenida Paulista",
  "numero": 123
  
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta|
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---


### Detalhar usuario
`GET` /api/usuarios/{id}
  
    
    **Parâmetros de caminho**

id - código do usuario a ser detalhado

**Exemplo de Corpo de resposta** 

```json
{

  "id_usuario": 1,
  "nome": "Nome do usuario",
  "email": "exemplo@gmail.com",
  "celular": "(11)99999-9999",
  "cpf": "123.456.789-10",
  "cep": "12345-678",
  "logradouro": "Avenida Paulista",
  "numero": 123
  
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta|
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---
    
### Listar usuarios
`GET` /api/usuarios

**Exemplo de Corpo de resposta** 

```json
[    
    {
      "id_usuario": 1,
      "nome": "Nome do usuario",
      "email": "exemplo@gmail.com",
      "celular": "(11)99999-9999",
      "cpf": "123.456.789-10",
      "cep": "12345-678",
      "logradouro": "Avenida Paulista",
      "numero": 123
    },
     {
      "id_usuario": 2,
      "nome": "Nome do usuario 2",
      "email": "exemplo2@gmail.com",
      "celular": "(12)99999-9999",
      "cpf": "145.456.789-10",
      "cep": "12455-678",
      "logradouro": "Rua das flores",
      "numero": 479
    }
[
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta|
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---

### Alterar usuario

`PUT` /api/usuarios/{id}

        
    **Parâmetros de caminho**

id - código do usuario a ser alterado
    
**Exemplo de corpo de requisição**

```json
{
  "nome": "Nome do usuario alterado",
  "email": "exemplo@gmail.com",
  "celular": "(11)99999-9999",
  "cpf": "123.456.789-10",
  "cep": "12345-678",
  "logradouro": "Avenida Paulista",
  "numero": 123
}
```

**Exemplo de corpo de resposta**

```json
{
  "id_usuario": 1,
  "nome": "Nome do usuario alterado",
  "email": "exemplo@gmail.com",
  "celular": "(11)99999-9999",
  "cpf": "123.456.789-10",
  "cep": "12345-678",
  "logradouro": "Avenida Paulista",
  "numero": 123
}
    
**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição PUT foi bem-sucedida e o recurso foi atualizado com sucesso|
| 201 | A requisição PUT foi bem-sucedida e um novo recurso foi criado|
| 400 | A requisição PUT não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição PUT requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição PUT é proibida pelo servidor, geralmente porque o usuário não tem permissão para atualizar o recurso|
| 404 | O recurso que a requisição PUT está tentando atualizar não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição PUT|

### Excluir usuario

`DELETE` /api/usuarios/{id}

**Parâmetros de caminho**

id - código do usuario a ser excluido

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---

### Cadastrar carteira
`POST` /api/carteiras

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| tipo_id | int | sim | qual tipo de transação foi feita, informação previamente cadastrada no sistema e preenchida automaticamente|
| valor | float | sim | valor que será depositado |
| saldo | float | sim | saldo da carteira |


**Exemplo de corpo de requisição**

```json
{
  "tipo_id":1
  "id_carteira": 1,
  "valor": 230.00,
  "saldo": 100
}
```

**Exemplo de corpo de resposta**

```json
{
  "id":1,
  "tipo_id":1
  "id_carteira": 1,
  "valor": 230.00,
  "saldo": 100
}
```
    
**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta|
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---


### Detalhar carteira
`GET` /api/carteiras/{id}
    
     
    **Parâmetros de caminho**

id - código da carteira a ser detalhada

**Exemplo de Corpo de resposta** 

```json
{
  "id":1,
  "tipo":{
    "tipo_id":1,
    "tipo": "Depósito"
  },
  "valor": 230.00,
  "saldo": 290.00
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição GET foi bem-sucedida e o servidor retornou os dados solicitados no corpo da resposta| 
| 400 | A requisição GET não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição GET requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição GET é proibida pelo servidor, geralmente porque o usuário não tem permissão para acessar o recurso solicitado|
| 404 | O recurso solicitado na requisição GET não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição GET|

---

### Listar carteiras
`GET` /api/carteira

**Exemplo de Corpo de resposta** 

```json
   [ 
    {
      "id": 1,
      "tipo":{
        "tipo_id":1,
        "tipo": "Depósito"
      }
      "valor": 230.00,
      "saldo": 290.00 
    },
   {
      "id": 2,
      "tipo":{
        "tipo_id":2,
        "tipo": "Saque"
      }
      "valor": 230.00,
      "saldo": 250.00
    }
]
```



### Alterar Carteira

`PUT` /api/carteira/{id}
 
        **Parâmetros de caminho**

id - código da carteira a ser alterada

**Exemplo de corpo de requisição**

```json
{
    "tipo_id":1,
    "valor": 300.00,
    "saldo": 745.00
}
```
**Exemplo de corpo de resposta**

```json
{
    "id": 1,
    "tipo_id":1,
    "valor": 300.00,
    "saldo": 745.00
}
```
**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | A requisição PUT foi bem-sucedida e o recurso foi atualizado com sucesso|
| 201 | A requisição PUT foi bem-sucedida e um novo recurso foi criado|
| 204 | A requisição PUT foi bem-sucedida e o recurso foi atualizado com sucesso, mas não há conteúdo a ser retornado na resposta|
| 400 | A requisição PUT não pode ser processada devido a um erro no formato ou na sintaxe da requisição|
| 401 | A requisição PUT requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 403 | A requisição PUT é proibida pelo servidor, geralmente porque o usuário não tem permissão para atualizar o recurso|
| 404 | O recurso que a requisição PUT está tentando atualizar não pode ser encontrado|
| 500 | Ocorreu um erro interno do servidor ao processar a requisição PUT|

### Deletar carteira

`DELETE` /api/carteiras/{id}

**Parâmetros de caminho**

id - código da carteira a ser excluida

**Códigos de resposta**

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---
