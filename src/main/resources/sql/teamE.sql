CREATE TABLE IF NOT EXISTS public.teame_users (
    user_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.teame_recipe (
    recipe_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    recipe_name VARCHAR(100) NOT NULL,
    method TEXT,
    why_it_works TEXT
    );

CREATE TABLE IF NOT EXISTS public.teame_ingredient (
    ingredient_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ingredient_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.teame_categories (
    category_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.teame_recipe_ingredient (
    recipe_id INTEGER,
    ingredient_id INTEGER,
    quantity VARCHAR(50),
    PRIMARY KEY (recipe_id, ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES public.teame_recipe(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES public.teame_ingredient(ingredient_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.teame_users_recipe (
    user_id INTEGER,
    recipe_id INTEGER,
    PRIMARY KEY (user_id, recipe_id),
    FOREIGN KEY (user_id) REFERENCES public.teame_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES public.teame_recipe(recipe_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.teame_recipe_categories (
    recipe_id INTEGER,
    category_id INTEGER,
    PRIMARY KEY (recipe_id, category_id),
    FOREIGN KEY (recipe_id) REFERENCES public.teame_recipe(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES public.teame_categories(category_id) ON DELETE CASCADE
    );

INSERT INTO public.teame_users (username, password, role)
VALUES
    ('jon', '1234', 'user'),
    ('bingo', '1234', 'admin'),
    ('ole', '1234', 'postgres'),
    ('dennis', '1234', 'postgres');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Jewellery cleaning',
        'Put some aluminium foil with the shiny side upwards inside a pie dish or something similar,
        it wants to be flat but also deep. Then lay your silver or gold (never both at the same time,
        and avoid gold plated jewellery) onto the foil spaced out.
        Cover it all with baking sods or preferably bicarbonate soda. Add a bit of salt (this helps the
        ion exchange rate) and then boiling water calmly into the dish (as to not move the jewellery
        around).
        Now wait about 10 minutes, and then carefully fish the jewellery out and rub with a soft
        cloth.',
        'Silver sulphide transfers to aluminium via redox reaction.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Lemon cleaning solution',
        'Add 1 tablespoon of citric acid powder to one cup of hot water.
        Optionally add lemon.',
        'Citric acid removes limescale and cleans surfaces.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Oven degreasing',
        'Mix ingredients in bottle, spray, let sit 10 minutes, wipe.',
        'Acidic breakdown and surfactants dissolve grease.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Degreasing surfaces',
        'Make baking soda paste, apply, add vinegar, wipe clean.',
        'Alkali + acid reaction lifts grease.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Removing blood stains in cloth',
        'Soak in cold water, optionally ice, rub, add lemon if needed.',
        'Cold coagulates blood, acid dissolves residue.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Removal of oil or grease from cloth',
        'Apply powder, then dish soap, wash.',
        'Powder absorbs oil.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Red wine or fruit stains in cloth',
        'Apply salt or vinegar, wash.',
        'Salt absorbs, vinegar dissolves pigment.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Lime descaling',
        'Use 50/50 vinegar and water.',
        'Acid neutralizes limescale.');

INSERT INTO teame_categories (category_name)
VALUES ('Jewellery cleaning'),
       ('Mirror and glass surfaces'),
       ('Hard surfaces'),
       ('Soft surfaces');

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES
    (1,1),
    (2,2),
    (2,3),
    (3,3),
    (4,3),
    (5,4),
    (6,4),
    (7,4),
    (8,3);

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES
    ('Aluminium foil'),
    ('Baking soda or bicarbonate soda'),
    ('Salt'),
    ('Boiling water'),
    ('White vinegar'),
    ('Citric acid'),
    ('Hot water'),
    ('Spray bottle'),
    ('Dish soap'),
    ('Tea tree oil'),
    ('Cloth rag'),
    ('Cold water'),
    ('Lemon juice'),
    ('Ice Cube'),
    ('Baby powder'),
    ('Cornstarch'),
    ('Chalk powder'),
    ('Water'),
    ('Lemon');

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES
    (1,1,null),
    (1,2,null),
    (1,3,null),
    (1,4,null),
    (2,6,null),
    (2,7,null),
    (2,8,null),
    (2,19,'1'),
    (3,6,'200 g'),
    (3,7,'150 mL'),
    (3,9,'20 mL'),
    (3,10,'10 - 20 drops'),
    (3,8,null),
    (4,5,null),
    (4,8,null),
    (4,2,null),
    (4,18,null),
    (4,11,null),
    (5,12,null),
    (5,13,null),
    (5,14,null),
    (6,15,null),
    (6,16,null),
    (6,17,null),
    (7,3,null),
    (7,5,null),
    (8,5,null),
    (8,18,null);