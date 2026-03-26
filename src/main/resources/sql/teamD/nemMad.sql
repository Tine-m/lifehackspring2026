CREATE TABLE teamd_recipes (
    id           SERIAL PRIMARY KEY,
    title        TEXT NOT NULL,
    url          TEXT UNIQUE NOT NULL,
    image_url    TEXT,
    total_time   TEXT,
    yields       TEXT,
    instructions TEXT
);

CREATE TABLE teamd_ingredients (
    ingredient_id       INT PRIMARY KEY,
    ingredient_name     TEXT NOT NULL UNIQUE,
    ingredient_category TEXT NOT NULL
);

CREATE TABLE teamd_ingredient_aliases (
    raw_ingredient_name TEXT PRIMARY KEY ,
    ingredient_id       INT NOT NULL REFERENCES teamd_ingredients(ingredient_id) ON DELETE CASCADE
);

CREATE TABLE teamd_recipe_ingredients (
    recipe_id           INT NOT NULL REFERENCES teamd_recipes(id) ON DELETE CASCADE,
    ingredient_id INT NOT NULL REFERENCES teamd_ingredients(ingredient_id) ON DELETE CASCADE,
    PRIMARY KEY (recipe_id, ingredient_id)
);