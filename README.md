# TotalPecas-Backlog

Uma API para o app TotalPecas um e-commerce

## Endpoints
- Peca
    -[Cadastrar](#cadastrar-despesa)
    -[Detalhar](#detalhar-produto)
    -Apagar todos os itens
    -Listar
    
---

### Cadastrar despesa
`POST` /api/produto

| campo | tipo | obrigatório | descricao
|-------|------|:-------------:|----------
| preco | float | sim | o valor do produto, deve ser maior que zero |
| titulo | string | sim | título do produto, deve conter entre 30 e 60 caracteres |
| categoria_id | int | sim | código de uma categoria previamente cadastrada |
| estado_id | int | sim | código de um estado previamente cadastrado |
| 


**Exemplo de corpo de requisição**

```js
{
  preco: 100.00,
  titulo: `Parachoque dianteiro freemont`,
  categoria_id: 1,
  estado_id: 1,
}
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 201 | produto cadastrado com sucesso |
| 400 | os campos enviados são inválidos |

---

###Detalhar produto

`GET` /api/produto/{id}

**Exemplo de Corpo de resposta** 
```js
{
  preco: 100.00,
  titulo: `Parachoque dianteiro freemont`,
  categoria:{
      categoria_id: 1,
      nome: 'Parachoque'
  }
  estado:{
      estado_id: 1,
      nome: 'Usado'
  }
  }
```

**Códigos de resposta**

| codigo | descricao |
|--------|-----------|
| 200 | dados retornados com sucesso |
| 404 | não existe produto com id informado |
