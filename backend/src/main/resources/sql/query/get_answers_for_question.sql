SELECT
  id,
  answer_text,
  is_correct
FROM answer
INNER JOIN question ON answer.question_id=?;