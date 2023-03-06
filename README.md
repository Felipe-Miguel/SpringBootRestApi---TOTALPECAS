# TotalPecas-Backlog

Uma API para o app TotalPecas um e-commerce

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
    - [Depositar](#depositar)
    - [Detalhar](#detalhar-transação)
    - [Detalhar-Todos](#detalhar-transações)
    - [Sacar](#sacar)
    - [Tranferir](#transferir)
---

### Cadastrar produto
`POST` /api/produto

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| id_produto | int | sim | id de um produto gerado pelo sistema |
| categoria_id | int | sim | código de uma categoria previamente cadastrada |
| titulo | string | sim | título do produto, deve conter entre 30 e 60 caracteres |
| preco | float | sim | o valor do produto, deve ser maior que zero |
| marca_id | int | sim | código de uma marca previamente cadastrada |
| modelo_id | int | sim | código de um modelo previamente cadastrada |
| anos | int | sim | os anos do modelo que o produto serve, não pode ultrapassar o ano atual |
| estado_id | int | sim | código de um estado previamente cadastrado |


**Exemplo de corpo de requisição**

```js
{
  id_produto: 1,
  categoria_id: 1,
  titulo: `Parachoque dianteiro freemont`,
  preco: 100.00,
  marca_id: 1,
  modelo_id: 1,
  anos: [2012,2013,2014]
  estado_id: 1,
  
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

---

### Detalhar produto

`GET` /api/produto/{id}

**Exemplo de Corpo de resposta** 
```js
{
  categoria:{
      categoria_id: 1,
      nome: 'Parachoque'
  },
  titulo: `Parachoque dianteiro freemont`,
  preco: 100.00,
  marca:{
      marca_id: 1,
      nome: 'Fiat'
  },
  modelo:{
      modelo_id: 1,
      nome: 'Freemont'
  },
  anos:[2014,2015,2016]
  },
  estado:{
      estado_id: 1,
      nome: 'Usado'
  },
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

**Exemplo de corpo de requisição**

```js
{
   id_produto:1,
   categoria_id: 1,
   titulo: "Novo nome do produto",
   preco: 149.99,
   marca_id: 456,
   modelo_id: 789,
   anos: [2019, 2020, 2021],
   estado_id: 2
  
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

---

### Excluir produto

`DELETE` /api/produtos/{id}

**Exemplo de corpo de requisição**

```js
{
     id_produto:1
  
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 204 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso, mas não há conteúdo a ser retornado na resposta|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---


### Cadastrar usuario
`POST` /api/usuario

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| id_usuario | int | sim | id de um usuario gerado pelo sistema |
| nome | string | sim | nome do usuário, deve conter pelo menos dois nomes |
| email | string | sim | email do usuário |
| celular | string | sim | celular do usuário |
| cpf/cnpj | string | sim | cpf ou cnpj do usuário |
| cep | string | sim |cep do endereço do usuário |
| logradouro | string | sim | nome do logradouro do usuário |
| numero | int | sim | número do logradouoro do usuário |


**Exemplo de corpo de requisição**

```js
{
  id_usuario: 1,
  nome: "Nome do usuario",
  email: "exemplo@gmail.com",
  celular: "(11)99999-9999",
  cpf: "123.456.789-10",
  cep: "12345-678",
  logradouro: "Avenida Paulista",
  numero: 123
  
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

---


### Detalhar usuario
`GET` /api/usuario/{id}

**Exemplo de Corpo de resposta** 

```js
{

  id_usuario: 1,
  nome: "Nome do usuario",
  email: "exemplo@gmail.com",
  celular: "(11)99999-9999",
  cpf: "123.456.789-10",
  cep: "12345-678",
  logradouro: "Avenida Paulista",
  numero: 123
  
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

---

### Alterar usuario

`PUT` /api/usuario/{id}

**Exemplo de corpo de requisição**

```js
{

  id_usuario: 1,
  nome: "Nome do usuario alterado",
  email: "exemplo@gmail.com",
  celular: "(11)99999-9999",
  cpf: "123.456.789-10",
  cep: "12345-678",
  logradouro: "Avenida Paulista",
  numero: 123
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

### Excluir usuario

`DELETE` /api/usuario/{id}

**Exemplo de corpo de requisição**

```js
{
  id_usuario: 1 
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 204 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso, mas não há conteúdo a ser retornado na resposta|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---

### Depositar
`POST` /api/carteira

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| id_carteira | int | sim | id de uma carteira gerado pelo sistema |
| tipo_id | id | sim | qual tipo de transação foi feita, informação previamente cadastrada no sistema e preenchida automaticamente|
| conta_tranferencia | id | sim | qual conta irá receber o saque ou a transferencia|
| valor | float | sim | valor que será depositado |
| saldo | float | sim | saldo da carteira |


**Exemplo de corpo de requisição**

```js
{
  tipo_id:1
  id_carteira: 1,
  valor: 230.00,
  saldo: 100
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

---


### Detalhar transação
`GET` /api/carteira/{id}

**Exemplo de Corpo de resposta** 

```js
{
    
  tipo:{
    tipo_id:1,
    tipo: "Depósito"
  }
  id_carteira: 1,
  valor: 230.00,
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

---

### Detalhar transações
`GET` /api/carteira

**Exemplo de Corpo de resposta** 

```js
   [ 
    {
  tipo:{
  tipo_id:1,
  tipo: "Depósito"
  }
  id_carteira: 1,
  valor: 230.00,
},
  tipo:{
  tipo_id:2,
  tipo: "Saque"
  }
  id_carteira: 1,
  valor: 230.00,
  conta_tranferencia: 99999-3
]
```



### Sacar

`POST` /api/carteira

**Exemplo de corpo de requisição**

```js
{

tipo_id:2
id_carteira: 1,
valor: 300.00,
conta_tranferencia: 99999-3
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

### Transferir

`POST` /api/carteira

**Exemplo de corpo de requisição**

```js
{
tipo:3
id_carteira: 1,
valor: 300.00,
conta_tranferencia: 99999-3
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso. |
| 202 | este código é usado quando a requisição DELETE foi aceita pelo servidor, mas a exclusão do recurso ainda não foi concluída. Isso pode acontecer quando a exclusão leva algum tempo para ser concluída|
| 204 | este código é usado para indicar que a requisição DELETE foi bem-sucedida e o recurso foi excluído com sucesso, mas não há conteúdo a ser retornado na resposta|
| 400  | ste código é usado quando a requisição DELETE não pode ser processada devido a um erro no formato ou na sintaxe da requisição| 
| 401  | este código é usado quando a requisição DELETE requer autenticação, mas as credenciais fornecidas não são válidas ou não foram fornecidas|
| 404  | este código é usado quando o recurso que a requisição DELETE está tentando excluir não pode ser encontrado| 
| 500  | este código é usado quando ocorre um erro interno do servidor ao processar a requisição DELETE|

---
