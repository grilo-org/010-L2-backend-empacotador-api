📦 Empacotador API - Seu Manoel
API REST para empacotamento de pedidos de produtos com base em caixas disponíveis.

🚀 Como Rodar Localmente com Docker
Pré-requisitos
Docker instalado na máquina

Porta 8080 disponível

Build da Imagem Docker (opcional)
Se quiser criar a imagem localmente, execute no terminal:

docker build -t bobwallan/empacotador-api:1.0 .

Executando a Aplicação
Execute a imagem diretamente do Docker Hub:

docker run -p 8080:8080 bobwallan/empacotador-api:1.0

A aplicação estará disponível em: http://localhost:8080

🔐 Autenticação
Antes de usar os endpoints protegidos, é necessário autenticar-se para obter um token JWT.

Requisição:
POST /auth/login
Content-Type: application/json

Corpo da Requisição:
{
"username": "admin",
"password": "senha123"
}

Resposta:
Você receberá um token JWT. Use-o no cabeçalho das requisições subsequentes:

Authorization: Bearer seu-token-aqui

📦 Endpoint Principal
POST /api/pedidos
Esse endpoint é utilizado para empacotar pedidos e receber como resposta a distribuição dos produtos nas caixas.

Exemplo de Corpo da Requisição:
[
{
"id": "pedido1",
"produtos": [
{
"id": "produto1",
"altura": 10,
"largura": 20,
"comprimento": 15
},
{
"id": "produto2",
"altura": 5,
"largura": 10,
"comprimento": 8
}
]
}
]

Exemplo de Resposta:
[
{
"id": "pedido1",
"caixas": [
{
"caixa": "Caixa 1",
"produtos": [
"produto1",
"produto2"
]
}
]
}
]

🔁 Atualizando o Projeto (Desenvolvedores)
Sempre que houver alterações no projeto e for necessário publicar uma nova versão da imagem Docker:

Compile o projeto com Maven:

mvn clean package

Gere a nova imagem Docker:

docker build -t bobwallan/empacotador-api:<versao> .

Envie a nova imagem para o Docker Hub:

docker push bobwallan/empacotador-api:<versao>

Depois disso, informe os clientes para usarem a nova versão da imagem Docker.

📫 Contato
Desenvolvedor: Wallan Peixoto
E-mail: bobwallan2@gmail.com
Telefone / WhatsApp: (27) 99256-7995