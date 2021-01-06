# the-dining-savages-problem

A tribe of savages eats communal dinners from a large pot that
can hold M servings of stewed missionary. When a savage wants to
eat, he helps himself from the pot, unless it is empty. If the pot is
empty, the savage wakes up the cook and then waits until the cook
has refilled the pot.

The synchronization constraints are:

• Savages cannot invoke getServingFromPot if the pot is empty.
• The cook can invoke putServingsInPot only if the pot is empty.
