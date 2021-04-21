# User API 
Desafio Java de implementação de uma API REST utilizando Spring Framework

* API de cadastro de usuários, endereços e listagem de endereços de um usuário específico

### Estrutura do Projeto

As classes foram divididas em pacotes de acordo com suas responsabilidades.

* Model: classes modelos ou seja as classes dos objetos que usadas no sistema
* Repository: repositórios para acessar os dados no banco de dados
* Service: classe que definem regras de negócio para manipulação dos Models
* Dto: classes em que serão consumidas pela API
* Controller: também chamado de Resource, classes que definem a interação com a API por meio dos endpoints
* Exception: classes de exceções
* Integration: integração com a API ViaCep
* Config: configuração do Bean Validation e do Swagger

![estrutura_projeto](https://github.com/mayzacatrinck/user-api/blob/master/images/estrutura_projeto.png)

### A API foi documentada no swagger

![swagger](https://github.com/mayzacatrinck/user-api/blob/master/images/swagger.png)
