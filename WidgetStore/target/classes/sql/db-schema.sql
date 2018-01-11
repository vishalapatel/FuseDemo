service mariadb start;

SELECT o.ID, c.firstname, c.lastname, c.street, c.ID, oi.type, oi.price, oi.quantity 
FROM Orders o 
INNER JOIN Customers c ON o.customer_id=c.ID 
RIGHT JOIN OrderItems oi ON oi.order_id=o.ID ;

INSERT INTO Orders ()customer_id) VALUES ('customer_id');
loop on order items...
INSERT INTO OrderItems (order_id, type, price, quantity) VALUES ('returned order_id', type, price, quantity)

CREATE TABLE orders2 (ID INT(11) AUTO_INCREMENT NOT NULL, customer_id INT(11), PRIMARY KEY(ID));