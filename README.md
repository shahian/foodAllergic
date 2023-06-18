# foodAllergic
scenario:
When the user is allergic to a food, he enters the site and in his user profile the first ingredients that
He is sensitive to it and chooses it.

The system, after the user has selected his sensitive raw materials, in his path of movement on the site to every
The food in which those raw materials are used, is displayed with a label of sensitivity.

We considered a series of foods as substitutes for it when he asks us for a substitute proposal, the foods that we defined in the section as substitutes to him
is shown.


Assuming that the user after logging into the system and selecting food allergies and calling the service:

api/v1/checkSensitivity,

The list of food with allergies and the list of substitutes are displayed to the user
