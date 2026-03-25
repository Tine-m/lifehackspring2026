CREATE TABLE IF NOT EXISTS public.teamE_recipe (
             recipe_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
             recipe_name VARCHAR(100) NOT NULL,
             method TEXT,
             why_it_works TEXT
    );

CREATE TABLE IF NOT EXISTS public.teamE_ingredient (
             ingredient_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
             ingredient_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.teamE_categories (
             category_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
             category_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.teamE_recipe_ingredient (
             recipe_id INTEGER,
             ingredient_id INTEGER,
             quantity VARCHAR(50),

    PRIMARY KEY (recipe_id, ingredient_id),

    FOREIGN KEY (recipe_id) REFERENCES public.teamE_recipe(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES public.teamE_ingredient(ingredient_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.teamE_users_recipe (
             user_id INTEGER,
             recipe_id,

             PRIMARY KEY (user_id, recipe_id),

    FOREIGN KEY (user_id) REFERENCES public.teamE_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES public.teamE_recipe(recipe_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.teamE_recipe_categories (
            recipe_id INTEGER,
            category_id,

            PRIMARY KEY (recipe_id, category_id),

    FOREIGN KEY (recipe_id) REFERENCES public.teamE_recipe(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES public.teamE_categories(category_id) ON DELETE CASCADE
    );