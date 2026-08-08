# Write your MySQL query statement below
WITH user_category AS (
    SELECT DISTINCT
        p.user_id,
        i.category
    FROM ProductPurchases p
    JOIN ProductInfo i
        ON p.product_id = i.product_id
),
category_pairs AS (
    SELECT
        a.user_id,
        a.category AS category1,
        b.category AS category2
    FROM user_category a
    JOIN user_category b
        ON a.user_id = b.user_id
       AND a.category < b.category
)
SELECT
    category1,
    category2,
    COUNT(DISTINCT user_id) AS customer_count
FROM category_pairs
GROUP BY category1, category2
HAVING COUNT(DISTINCT user_id) >= 3
ORDER BY customer_count DESC, category1, category2;