SELECT
  login,
  admin_password,
  admin_name
FROM quiz_admin
WHERE login=?
  AND admin_password=?;