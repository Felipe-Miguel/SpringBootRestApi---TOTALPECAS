# TotalPecas-Backlog

Uma API para o app TotalPecas um e-commerce

## Endpoints
- Produto
    -[Cadastrar](#cadastrar-despesa)
    -[Detalhar](#detalhar-produto)
    -[Alterar](#alterar-produto)
    -[Excluir](#excluir-produto)
    
---

### Cadastrar produto
`POST` /api/produto

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
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
| 201 | produto cadastrado com sucesso |
| 400 | os campos enviados são inválidos |

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
| 200 | dados retornados com sucesso |
| 404 | não existe produto com id informado |

---

### Alterar produto

`PUT` /api/produtos/{id}

**Exemplo de corpo de requisição**

```js
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

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | dados retornados com sucesso |
| 404 | não existe produto com id informado |

---

### Excluir produto

`DELETE` /api/produtos/{id}

**Exemplo de corpo de requisição**

```js
{
     "categoria_id": 1,
  
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


  


