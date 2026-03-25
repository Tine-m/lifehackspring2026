-- Script til at oprette tabel
DROP TABLE IF EXISTS quiz;

CREATE TABLE quiz (
                      id SERIAL PRIMARY KEY,
                      question_text TEXT NOT NULL,
                      answer_correct VARCHAR(255) NOT NULL,
                      answer_wrong1 VARCHAR(255),
                      answer_wrong2 VARCHAR(255),
                      img_correct VARCHAR(255),
                      img_wrong VARCHAR(255),
                      sound_correct VARCHAR(255),
                      sound_wrong VARCHAR(255)
);