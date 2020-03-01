SELECT
  id,
  question_text
FROM question
ORDER BY rand()
LIMIT ?;