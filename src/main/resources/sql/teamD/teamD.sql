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
    ingredient_name     TEXT PRIMARY KEY,
    ingredient_category TEXT NOT NULL
);

CREATE TABLE teamD_recipe_ingredients (
    recipe_id       INT REFERENCES teamD_recipes(id),
    ingredient_name TEXT REFERENCES teamD_ingredients(ingredient_name),
    PRIMARY KEY (recipe_id, ingredient_name)
);