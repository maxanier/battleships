BATTLESHIPS

A Java Client/Server implementation of Battleships.

RULES:
-square field (variable size)
-number and size of ships depends on field size
-players can see 2 game fields, one where they put their own ships and one where they can choose which cell to attack
-by turns: turn ends if player hits water
-players are notified whether they hit water or a ship

SEQUENCE:
-clients register themselves to the server
-server transmitts field size and ship data(count and size)
-players choose placement of the ships (client checks if valid)
-clients transmit placement to server
-server chooses starting player

While all players own at least one ship{
-server notifies clients who is next
-player chooses on second game field which cell to attack
-client submits it to the server
-server renders field
-server notifies clients about updates
-clients show updates
}

-server notifies clients who wins and goes back into lobby mode
-client shows winner and offers option to reconnect