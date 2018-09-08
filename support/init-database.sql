CREATE TABLE orders (
   id SERIAL PRIMARY KEY,
   customer VARCHAR(128) NOT NULL,
   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE items (
   id SERIAL PRIMARY KEY,
   order_id INTEGER NOT NULL REFERENCES orders(id),
   name VARCHAR (128) NOT NULL,
   amount INTEGER NOT NULL,
   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);