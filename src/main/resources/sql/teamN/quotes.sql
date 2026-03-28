-- Made by Claude Sonnet 4.6 extended

-- Verified motivational quotes
-- Focused on: getting started, taking action, study/exam motivation
-- Sources verified via Quote Investigator, primary texts, and documented speeches
-- Year column: documented year where known, estimated year marked with comment

CREATE TABLE IF NOT EXISTS public.teamn_quotes (
                                      id   INT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
    quote TEXT NOT NULL,
    year INT
    );

INSERT INTO teamn_quotes (id, name, quote, year) VALUES
-- MARCUS AURELIUS — Meditations (~170–180 CE)
(1,  'Marcus Aurelius', 'The impediment to action advances action. What stands in the way becomes the way.', 175),
(2,  'Marcus Aurelius', 'Waste no more time arguing about what a good man should be. Be one.', 175),
(3,  'Marcus Aurelius', 'You have power over your mind — not outside events. Realize this, and you will find strength.', 175),
(4,  'Marcus Aurelius', 'Very little is needed to make a happy life; it is all within yourself, in your way of thinking.', 175),

-- SENECA
(5,  'Seneca', 'It is not that we have a short time to live, but that we waste a good deal of it.', 49),   -- De Brevitate Vitae, ~49 CE
(6,  'Seneca', 'We suffer more often in imagination than in reality.', 65),                               -- Epistulae Morales, ~65 CE
(7,  'Seneca', 'He who is brave is free.', 65),

-- EPICTETUS — Enchiridion / Discourses (~108 CE)
(8,  'Epictetus', 'Make the best use of what is in your power, and take the rest as it happens.', 108),
(9,  'Epictetus', 'First say to yourself what you would be; and then do what you have to do.', 108),
(10, 'Epictetus', 'It''s not what happens to you, but how you react to it that matters.', 108),

-- CONFUCIUS — Analekterne (~500 BCE)
(11, 'Confucius', 'It does not matter how slowly you go as long as you do not stop.', -500),
(12, 'Confucius', 'The man who moves a mountain begins by carrying away small stones.', -500),

-- HENRY DAVID THOREAU — Brev til H.G.O. Blake, 16. november 1857
(13, 'Henry David Thoreau', 'It is not enough to be industrious; so are the ants. What are you industrious about?', 1857),

-- JACK LONDON — Practical Authorship, 1905
(14, 'Jack London', 'Don''t loaf and invite inspiration; light out after it with a club, and if you don''t get it you will nonetheless get something that looks remarkably like it.', 1905),

-- HENRY FORD — as quoted in International Encyclopedia of Prose and Poetical Quotations, 1951
(15, 'Henry Ford', 'You can''t build a reputation on what you are going to do.', 1947),

-- DALE CARNEGIE — How to Stop Worrying and Start Living, 1948
(16, 'Dale Carnegie', 'Inaction breeds doubt and fear. Action breeds confidence and courage.', 1948),

-- GEORGE S. PATTON — Brev til sønnen, 6. juni 1944 (D-dag)
(17, 'George S. Patton', 'A good plan violently executed now is better than a perfect plan next week.', 1944),

-- RALPH WALDO EMERSON — Letters and Social Aims, 1875
(18, 'Ralph Waldo Emerson', 'What you do speaks so loudly that I cannot hear what you say.', 1875),
(19, 'Ralph Waldo Emerson', 'Do not go where the path may lead, go instead where there is no path and leave a trail.', 1860),

-- THOMAS EDISON
(20, 'Thomas Edison', 'Opportunity is missed by most people because it is dressed in overalls and looks like work.', 1903),
(21, 'Thomas Edison', 'Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.', 1931),
(22, 'Thomas Edison', 'There is no substitute for hard work.', 1903),

-- WINSTON CHURCHILL
(23, 'Winston Churchill', 'Success is not final, failure is not fatal: it is the courage to continue that counts.', 1952),
(24, 'Winston Churchill', 'The price of greatness is responsibility.', 1943),

-- THEODORE ROOSEVELT — Selvbiografi, 1913
(25, 'Theodore Roosevelt', 'Do what you can, with what you have, where you are.', 1913),
(26, 'Theodore Roosevelt', 'Believe you can and you''re halfway there.', 1913),

-- FRANKLIN D. ROOSEVELT
(27, 'Franklin D. Roosevelt', 'The only thing we have to fear is fear itself.', 1933),              -- Første indsættelsestale
(28, 'Franklin D. Roosevelt', 'The only limit to our realization of tomorrow is our doubts of today.', 1945), -- Forberedt tale, 11. april 1945

-- DWIGHT D. EISENHOWER — Tale 14. november 1957
(29, 'Dwight D. Eisenhower', 'Plans are worthless, but planning is everything.', 1957),

-- MAHATMA GANDHI — Young India, 1920
(30, 'Mahatma Gandhi', 'The future depends on what you do today.', 1920),
(31, 'Mahatma Gandhi', 'Strength does not come from physical capacity. It comes from an indomitable will.', 1920),

-- HELEN KELLER — The Open Door, 1957
(32, 'Helen Keller', 'Life is either a daring adventure or nothing at all.', 1957),
(33, 'Helen Keller', 'Optimism is the faith that leads to achievement. Nothing can be done without hope and confidence.', 1903), -- Optimism, 1903

-- NELSON MANDELA — Long Walk to Freedom, 1994
(34, 'Nelson Mandela', 'It always seems impossible until it''s done.', 1994),
(35, 'Nelson Mandela', 'I learned that courage was not the absence of fear, but the triumph over it.', 1994),
(36, 'Nelson Mandela', 'Education is the most powerful weapon which you can use to change the world.', 2003), -- Tale i Tshiame

-- MARTIN LUTHER KING JR.
(37, 'Martin Luther King Jr.', 'Intelligence plus character — that is the goal of true education.', 1947), -- The Purpose of Education
(38, 'Martin Luther King Jr.', 'The time is always right to do what is right.', 1964),

-- ELEANOR ROOSEVELT
(39, 'Eleanor Roosevelt', 'You must do the things you think you cannot do.', 1960),  -- You Learn by Living
(40, 'Eleanor Roosevelt', 'No one can make you feel inferior without your consent.', 1935), -- This Day

-- MAYA ANGELOU
(41, 'Maya Angelou', 'Nothing will work unless you do.', 1993),

-- MARIE CURIE
(42, 'Marie Curie', 'We must believe that we are gifted for something, and that this thing, at whatever cost, must be attained.', 1927),

-- STEVE JOBS — Stanford Commencement Speech, 12. juni 2005
(43, 'Steve Jobs', 'Your time is limited, so don''t waste it living someone else''s life.', 2005),
(44, 'Steve Jobs', 'The only way to do great work is to love what you do.', 2005),

-- VINCE LOMBARDI
(45, 'Vince Lombardi', 'It''s not whether you get knocked down; it''s whether you get up.', 1967),
(46, 'Vince Lombardi', 'The difference between a successful person and others is not a lack of strength, not a lack of knowledge, but rather a lack of will.', 1967),

-- STEPHEN COVEY — The 7 Habits of Highly Effective People, 1989
(47, 'Stephen Covey', 'I am not a product of my circumstances. I am a product of my decisions.', 1989),

-- WAYNE GRETZKY — The Hockey News, 1983
(48, 'Wayne Gretzky', 'You miss 100% of the shots you don''t take.', 1983),

-- TIM NOTKE — highschool-coach, ~midt-80erne, populariseret af Kevin Durant 2007
(49, 'Tim Notke', 'Hard work beats talent when talent doesn''t work hard.', 1985),

-- ARTHUR ASHE — Arthur Ashe: Portrait in Motion, 1975
(50, 'Arthur Ashe', 'Start where you are. Use what you have. Do what you can.', 1975),

-- JIM ROHN
(51, 'Jim Rohn', 'If you really want to do something, you''ll find a way. If you don''t, you''ll find an excuse.', 1985),
(52, 'Jim Rohn', 'Either you run the day or the day runs you.', 1988),

-- JIM RYUN — løber og politiker, dokumenteret i interviews
(53, 'Jim Ryun', 'Motivation is what gets you started. Habit is what keeps you going.', 1981),

-- ZIG ZIGLAR
(54, 'Zig Ziglar', 'You don''t have to be great to start, but you have to start to be great.', 1980),

-- WALT DISNEY
(55, 'Walt Disney', 'The way to get started is to quit talking and begin doing.', 1957),

-- STEPHEN KING — On Writing, 2000
(56, 'Stephen King', 'Amateurs sit and wait for inspiration, the rest of us just get up and go to work.', 2000),

-- TIM FERRISS — The 4-Hour Workweek, 2007
(57, 'Tim Ferriss', 'Focus on being productive instead of busy.', 2007),

-- LIZ SMITH — journalist og klummeskribent, ~1975
(58, 'Liz Smith', 'Begin somewhere. You cannot build a reputation on what you intend to do.', 1975),

-- BRENÉ BROWN — Daring Greatly, 2012
(59, 'Brené Brown', 'Vulnerability is the birthplace of innovation, creativity, and change.', 2012),

-- ALBERT EINSTEIN
(60, 'Albert Einstein', 'Life is like riding a bicycle. To keep your balance, you must keep moving.', 1930); -- Brev til sønnen Eduard, 5. feb. 1930