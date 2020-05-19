SELECT
  player_name,
  COUNT(*) as games_won,
  SUM(score) AS score_sum
FROM quiz_winner
GROUP BY player_name ORDER BY games_won DESC, score_sum DESC, player_name ASC
LIMIT ?;