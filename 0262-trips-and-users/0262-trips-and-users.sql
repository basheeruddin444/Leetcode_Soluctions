# Write your MySQL query statement below
SELECT
    t.request_at AS Day,
    ROUND(
        SUM(t.status != 'completed') / COUNT(*),
        2
    ) AS `Cancellation Rate`
FROM Trips t
JOIN Users u1
    ON t.client_id = u1.users_id
JOIN Users u2
    ON t.driver_id = u2.users_id
WHERE u1.banned = 'No'
  AND u2.banned = 'No'
  AND t.request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY t.request_at
ORDER BY t.request_at;