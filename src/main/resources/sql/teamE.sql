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
             recipe_id,

             PRIMARY KEY (user_id, recipe_id),

    FOREIGN KEY (user_id) REFERENCES public.teame_users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES public.teame_recipe(recipe_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.teame_recipe_categories (
            recipe_id INTEGER,
            category_id,

            PRIMARY KEY (recipe_id, category_id),

    FOREIGN KEY (recipe_id) REFERENCES public.teame_recipe(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES public.teame_categories(category_id) ON DELETE CASCADE
    );


INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Jewellery cleaning',
        'Put some aluminium foil with the shiny side upwards inside a pie dish or something similar,
    it wants to be flat but also deep. Then lay your silver or gold (never both at the same time,
    and avoid gold plated jewellery) onto the foil spaced out.
    Cover it all with baking sods or preferably bicarbonate soda. Add a bit of salt (this helps the
    ion exchange rate) and then boiling water calmly into the dish (as to not move the jewellery
    around). (optional acid here)
    Now wait about 10 minutes, and then carefully fish the jewellery out and rub with a soft
    cloth. It should now have been cleaned from metal tarnish.',
        'Silver and gold are higher up on the list of the electrochemical series and are naturally
        resistant to corrosion by air, water and acid. Aluminium is below both of them in the series.
        This is the basics of how you clean jewellery using this method. Cleaning jewellery with
        aluminium foil, baking soda, and hot water is an electrochemical reaction (reductionoxidation) that reverses tarnish. Silver sulphide ( 𝐴𝑔2𝑆 ) on the jewellery transfers its sulphur
        to the aluminium foil, reducing the silver back to its metallic state, producing aluminium
        sulphide and hydrogen gas.
        Basically you switch the pretty metal of the aluminium and give it to the silver, and the ugly
        silver tarnish is transferred to the aluminium.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Lemon cleaning solution',
        'You can make as much as you need, the ratio is:
    Add 1 tablespoon of citric acid powder to one cup of hot water (does not need to be
    boiling).
    You can chop up some lemon and add to the mixture for extra aromatics (scent) and more
    acid. Do not add too much, as lemon naturally has some sugar and can make the solution a
    little sticky.
    Shake the solution very well in the spray bottle, also shake before use.
    ',
        'Citric acid being names after especially lemons but also all other citrus fruit that contains
        this specific acid, works like any other acid, except it is a mild acid and thus not too harsh on
        the surfaces applied, nor dangerous for the human body. (Do not get directly in your eyes
        however, that will be an uncomfortable experience).
        It is perfect for removing limescale, cleaning mirror and glass surfaces, sanitizing hard
        surfaces and cleaning the inside of your dishwasher. ');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Oven degreasing',
        'Mix ingredients in mixing bottle, and then shake well. Spray onto oven surfaces and let it sit
    for at least 10 minutes, then wipe down.',
        'This eco-friendly oven cleaner works through a combination of acidic breakdown, surfactant
        action, and natural steam, designed to dissolve baked-on grease and loosen charred food
        residue without the need for toxic, chemical-heavy cleaners.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Degreasing surfaces',
        'Make a paste with the baking or bicarbonate soda, add only a little water at a time until you
    have a paste texture, it should not be liquid.
    Smear onto the surface you wish to degrease; let it sit on for at least 10 minutes. Then spray
    or gently pour white vinegar onto the pasted surface and wipe away with a damp cloth rag.',
        'This method works because it combines the alkaline degreasing power of baking soda, the
        acidic cutting power of vinegar, and the physical force of a chemical reaction to lift grime.
        Baking soda is a mild alkali, which is ideal for dissolving organic compounds like grease, while
        vinegar cuts through dirt and mineral deposits.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Removing blood stains in cloth',
        'This method works best if it done immediately or very soon after the blood has stained the
    cloth.
    Put the cloth into a container with cold water, as cold as possible is best. Optionally add ice
    to the mix. If it is a tiny splatter, apply ice cube directly to it instead and let it slowly melt
    over the stain.
    Let the coolness do its thing and wait (you can leave it there for 2-3 hours if you wish, but
    wait at least 1 hour).
    Once it has sat there rub the stain, if all the blood does not come out by rubbing it, then add
    a little lemon juice or a water citric acid solution and rub it in. Then wash with cool water
    again.',
        'Blood coagulates in the cold, meaning it clumps. Once it is clumped it is easier to rub out as
        it comes off in flakes.
        The acid helps with dissolving the remaining blood if any is left.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Removal of oil or grease from cloth',
        'Sprinkle your chosen powder over the stain and let it sit there to absorb the oil. Then rub
    the stain with dishwashing soap and gently wash with water.
    Wash the clothes in a normal washing machine next.',
        'The powders lift and absorb the oil making it easier to wash the remaining oil or grease out
        of your clothes, hopefully leaving no lasting stain.');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Red wine or fruit stains in cloth',
        'Add the salt and/or vinegar to the stain and let it sit for 10 minutes at least. Then wash
    gently with dishwashing soap and water.
    Wash in normal washing machine afterwards.',
        'The salt absorbs the liquid and thus also the pigment. The vinegar begins to dissolve the
        organic component in the wine or juice and thus removes some of the colour');

INSERT INTO public.teame_recipe (recipe_name, method, why_it_works)
VALUES ('Lime descaling',
        'Add vinegar and water as you like, the ratio should be about 50/50.
    Apply to any surface with visible lime scaling, such as in the bathroom, or your kettle.
    For the kettle you can also add water and then vinegar and let it boil in the kettle.',
        'Lime is a natural alkali, and thus reacts strongly with acid. Once they have reacted they
        become neutral (depending how strong the alkali and acid was).');


INSERT INTO teame_categories (category_name)
VALUES ('Jewellery cleaning');

INSERT INTO teame_categories (category_name)
VALUES ('Mirror and glass surfaces');

INSERT INTO teame_categories (category_name)
VALUES ('Hard surfaces');

INSERT INTO teame_categories (category_name)
VALUES ('Soft surfaces');

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (1,1);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (2,2);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (2,3);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (3,3);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (4,3);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (5,4);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (6,4);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (7,4);

INSERT INTO public.teame_recipe_categories (recipe_id, category_id)
VALUES (8,3);

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Aluminium foil');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Baking soda or bicarbonate soda');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Salt');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Boiling water');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('White vinegar');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Citric acid');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Hot water');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Spray bottle');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Dish soap');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Tea tree oil');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Cloth rag');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Cold water');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Lemon juice');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Ice Cube');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Baby powder');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Cornstarch');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Chalk powder');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Water');

INSERT INTO public.teame_ingredient (ingredient_name)
VALUES ('Lemon');


INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (1,1, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (1,2, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (1,3, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (1,4, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (2,6, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (2,7, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (2,8, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (2,19, 1);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (3,6, '200 g');

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (3,7, '150 mL');

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (3,9, '20 mL');

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (3,10, '10 - 20 drops');

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (3,8, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (4,5, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (4,8, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (4,2, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (4,18, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (4,11, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (5,12, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (5,13, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (5,14, null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (6,15,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (6,16,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (6,17,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (7,3,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (7,5,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (8,5,null);

INSERT INTO public.teame_recipe_ingredient (recipe_id, ingredient_id, quantity)
VALUES (8,18,null);