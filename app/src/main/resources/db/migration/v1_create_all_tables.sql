-- Tabela de personal trainers
CREATE TABLE trainer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tabela de exercícios
CREATE TABLE exercises (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   description TEXT,
   category VARCHAR(50) NOT NULL,
   banner VARCHAR(255),
   icon VARCHAR(255)
);

-- Tabela de clientes
CREATE TABLE clients (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     password VARCHAR(255) NOT NULL,
     age INT,
     gender VARCHAR(10),
     height FLOAT,
     weight FLOAT,
     trainer_id INT,
     FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);

-- Tabela de planos de treinos
CREATE TABLE training_plans (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    goal VARCHAR(50) NOT NULL,
    personal_trainer_id INT,
    client_id INT,
    FOREIGN KEY (personal_trainer_id) REFERENCES trainer(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

-- Tabela de relação entre planos de treinos e exercícios
CREATE TABLE plan_exercises (
    id SERIAL PRIMARY KEY,
    training_plan_id INT,
    exercise_id INT,
    FOREIGN KEY (training_plan_id) REFERENCES training_plans(id),
    FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);
