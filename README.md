# 📦 Empacotador API – Otimizador de Pedidos em Caixas

API REST desenvolvida em **Java com Spring Boot**, focada em empacotar pedidos de produtos nas caixas disponíveis de forma otimizada, economizando espaço e tempo. Totalmente containerizada com Docker e com segurança via JWT.

---

## 🚀 Como Executar Localmente com Docker

### ✅ Requisitos
- Docker instalado
- Porta `8080` livre

### ▶️ Passo a Passo

**1. Build da imagem:**
    docker build -t bobwallan/empacotador-api:1.0 .

**2. Execução da aplicação:**
    docker run -p 8080:8080 bobwallan/empacotador-api:1.0

**3. Acesse a API:**
    http://localhost:8080

---

## 🔐 Autenticação com JWT

A API protege os endpoints com autenticação JWT. Veja como utilizar:

**1. Faça login:**
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "senha123"
}

**2. No response, você receberá um token JWT.**

**3. Para acessar os endpoints protegidos, envie o token no header:**
Authorization: Bearer <seu-token-aqui>

---

## 📦 Endpoint Principal

### POST /api/pedidos

Recebe pedidos com lista de produtos e retorna a distribuição otimizada em caixas.

**Exemplo de requisição:**
[
  {
    "id": "pedido1",
    "produtos": [
      {"id": "produto1", "altura": 10, "largura": 20, "comprimento": 15},
      {"id": "produto2", "altura": 5, "largura": 10, "comprimento": 8}
    ]
  }
]

**Exemplo de resposta:**
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

---

## 🔁 Atualização do Projeto (Para Desenvolvedores)

Sempre que atualizar o código, siga os passos abaixo para gerar e publicar uma nova imagem Docker:

**1. Compile o projeto com Maven:**
    mvn clean package

**2. Gere a nova imagem Docker:**
    docker build -t bobwallan/empacotador-api:<versao> .

**3. Publique no Docker Hub:**
    docker push bobwallan/empacotador-api:<versao>

**4. Avise os usuários para atualizarem a imagem local.**

---

## 📫 Contato

Desenvolvedor: Wallan Peixoto  
Email: bobwallan2@gmail.com  
WhatsApp: (27) 99256-7995
