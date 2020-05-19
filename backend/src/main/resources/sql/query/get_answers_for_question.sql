SELECT
  id,
  answer_text,
  is_correct
FROM answer
WHERE question_id=?
ORDER BY random();