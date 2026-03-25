CREATE TABLE teamD_recipes (
    id           SERIAL PRIMARY KEY,
    title        TEXT NOT NULL,
    url          TEXT UNIQUE NOT NULL,
    image_url    TEXT,
    total_time   TEXT,
    yields       TEXT,
    instructions TEXT
);

CREATE TABLE teamD_ingredients (
    name     TEXT PRIMARY KEY,
    category TEXT NOT NULL
);

CREATE TABLE teamD_recipe_ingredients (
    recipe_id       INT REFERENCES teamD_recipes(id),
    ingredient_name TEXT REFERENCES teamD_ingredients(name),
    PRIMARY KEY (recipe_id, ingredient_name)
);