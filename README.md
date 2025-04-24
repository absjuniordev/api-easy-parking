# 🅿️ Easy Parking API

### 🚗 Sistema de Gerenciamento de Estacionamento via API RESTful

**Easy Parking** é uma poderosa API RESTful desenvolvida para o controle e automação de estacionamentos. Com ela, é possível configurar tarifas, registrar entradas e saídas de veículos, calcular permanência, aplicar valores de pernoite e realizar cobranças de forma automática e eficiente.

![Banner do Projeto](assets/img/banner.png)

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **H2 Database** – para testes e desenvolvimento local
- **PostgreSQL** – para ambientes de produção
- **Swagger (OpenAPI 3.1)** – documentação interativa da API

---

## 🚀 Funcionalidades

- Cadastro de usuários e operadores
- Cadastro de caixas de pagamento
- Criação e configuração de tarifas para carros e motos
- Registro de entrada de veículos com geração automática de tickets
- Cálculo automático de tempo de permanência e valores a pagar
- Regras de cobrança para pernoite com horários e valores definidos
- Consulta de tickets por placa
- Registro de pagamento de tickets
- Interface interativa com Swagger

---

## 🛠 Como Rodar o Projeto

### Pré-requisitos

- Java 17 instalado
- PostgreSQL (caso deseje usar banco de produção)
- Maven ou Gradle instalado

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/absjuniordev/api-easy-parking.git
   ```

2. **Acesse o diretório do projeto**
   ```bash
   cd api-easy-parking
   ```

3. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run
   ```

4. A API estará disponível em:  
   📍 `http://localhost:8081`

---

## 🧪 Configuração Inicial (Passo a Passo)

### 1️⃣ Criar um usuário
**POST** `/api/user`
```json
{
  "name": "Fau",
  "password": "1234"
}
```

### 2️⃣ Criar o caixa de pagamento
**POST** `/api/parking`
```json
{
  "name": "CAIXA 01"
}
```

### 3️⃣ Criar tarifa
**POST** `/api/fare`
```json
{
  "valueCarFare": 10,
  "valueBikeFare": 7,
  "additionalCarValue": 1,
  "additionalBikeValue": 1,
  "overnightCar": 25,
  "overnightBike": 18,
  "withdrawalTime": "00:10",
  "minimumStay": "03:00",
  "additionalStay": "01:00",
  "overnight": 25,
  "startOvernight": 0,
  "endOvernight": 6
}
```

### 4️⃣ Criar ticket de entrada de veículo
**POST** `/api/tickets`
```json
{
  "plate": "JRL-5B55",
  "vehicleType": "BIKE"
}
```

---

## 📊 Exemplos de Uso

### Buscar tickets por placa
**GET** `/api/plate/{plate}`

### Visualizar tempo de permanência, valores devidos e efetuar pagamento
**POST** `/api/payments/{ticketId}`  
(_Veja detalhes e estrutura de retorno no Swagger_)

---

## 📚 Documentação Interativa

Acesse a documentação completa da API no Swagger:

👉 **[Swagger UI](http://localhost:8081/swagger-ui.html)**

Você poderá testar todos os endpoints diretamente pelo navegador.

---

## 🧑‍💻 Contribuindo

Contribuições são bem-vindas!  
Siga os passos abaixo:

1. Faça um **fork** deste repositório
2. Crie uma nova branch:
   ```bash
   git checkout -b feature/nova-funcionalidade
   ```
3. Commit suas alterações:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Push para o seu fork:
   ```bash
   git push origin feature/nova-funcionalidade
   ```
5. Abra um **Pull Request**

---

## 📧 Contato

- Desenvolvedor: **Arnaldo Junior**
- Email: [abs.junnior@hotmail.com](mailto:abs.junnior@hotmail.com)
- WhatsApp: [Clique aqui para conversar](https://wa.me/5571993346500?text=)

---

> Essa API foi desenvolvida com o propósito de facilitar o controle e operação de estacionamentos de forma moderna, simples e eficaz. 🚀