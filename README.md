📦 Empacotador API – Projeto de Empacotamento de Pedidos
Esta API REST foi desenvolvida para empacotar pedidos de produtos em caixas disponíveis, otimizando o uso do espaço. Implementada em Java Spring Boot, com containerização via Docker e segurança baseada em autenticação JWT.

🚀 Como executar localmente com Docker
Requisitos
Docker instalado

Porta 8080 livre na máquina

Passos
Para criar a imagem localmente, execute:

docker build -t bobwallan/empacotador-api:1.0 .

Para executar a aplicação, rode:

docker run -p 8080:8080 bobwallan/empacotador-api:1.0

Após isso, a API estará disponível no endereço:
http://localhost:8080

🔐 Autenticação JWT
A API utiliza JWT para proteger endpoints. Para acessar, siga:

Faça uma requisição POST para /auth/login com o corpo JSON:

{
"username": "admin",
"password": "senha123"
}

Você receberá um token JWT no response.

Inclua esse token no cabeçalho Authorization das próximas requisições:

Authorization: Bearer <seu-token-aqui>

📦 Endpoint Principal
POST /api/pedidos
Recebe pedidos com produtos e retorna a distribuição otimizada em caixas.

Exemplo de requisição:

[
{
"id": "pedido1",
"produtos": [
{"id": "produto1", "altura": 10, "largura": 20, "comprimento": 15},
{"id": "produto2", "altura": 5, "largura": 10, "comprimento": 8}
]
}
]

Exemplo de resposta:

[
{
"id": "pedido1",
"caixas": [
{
"caixa": "Caixa 1",
"produtos": ["produto1", "produto2"]
}
]
}
]

🔁 Atualização do Projeto (Para Desenvolvedores)
Sempre que atualizar o código, siga os passos para gerar e publicar a nova versão da imagem Docker:

Compile o projeto com Maven:

mvn clean package

Crie a imagem Docker:

docker build -t bobwallan/empacotador-api:<versao> .

Publique no Docker Hub:

docker push bobwallan/empacotador-api:<versao>

Informe os usuários para atualizarem suas imagens.

📫 Contato
Desenvolvedor: Wallan Peixoto

Email: bobwallan2@gmail.com

WhatsApp: (27) 99256-7995
