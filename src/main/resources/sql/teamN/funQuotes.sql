CREATE TABLE IF NOT EXISTS public.teamn_funquotes
(
    funquote_id        SERIAL PRIMARY KEY,
    funquote_setup     TEXT NOT NULL,
    funquote_punchLine TEXT NOT NULL
);

INSERT INTO teamn_funquotes (funquote_setup, funquote_punchLine)
VALUES ('Hvem er historiens mindst sultne statsminister?', 'Mætte Frederiksen.'),
       ('Hvad kalder man en ko uden ben?', 'Ground beef.'),
       ('Hvad sagde manden da han gik ind i bussen?', 'Av!'),
       ('Hvad gør en due, når den har travlt?', 'Den laver en to-do-liste.'),
       ('Hvad kalder man en boomerang, der ikke vender tilbage?', 'En pind.'),
       ('Hvorfor tager nordmænd altid bearnaisesovs med til fest?', 'Fordi der er estragon i den!'),
       ('Min kammerat David mistede sit ID i dag.', 'Nu hedder han bare Dav'),
       ('Nogle siger, at de ikke kan sove, når de drikker kaffe',
        'Jeg har det lige omvendt. Jeg kan ikke drikke kaffe, når jeg sover.');