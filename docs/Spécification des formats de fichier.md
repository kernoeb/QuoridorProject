## Spécification des formats de fichier

<p style='text-align: justify;'>
L’enregistrement d’une partie, que l’utilisateur de l’application souhaiterait reprendre ultérieurement, se fera à travers un fichier sérialisé (objet) où les divers éléments nécessaires à la reprise de cette partie seront sauvegardés dans une instance de la classe Game. Cette instance contient les éléments nécessaires au bon déroulement du jeu, tels que la position actuelle des éléments du jeu (pions et murs) ou encore l’état actuel des tours, c’est-à-dire le joueur qui doit jouer à la reprise du jeu.
</p>

<p style='text-align: justify;'>
Ce fichier contiendra uniquement la dernière partie non terminée, si elle existe. Dans le cas contraire, une nouvelle partie sera lancée automatiquement. Ainsi, la récupération (lecture du fichier) et la reprise (utilisation des informations récupérées) de cette partie se feront dans la classe Quoridor.
</p>
---
<p style='text-align: justify;'>
Si cette méthode n'est pas concluante, nous enregistrerons les données au sein d'un fichier binaire (contenant les informations du plateau) et d'un fichier JSON (contenant les informations sur les joueurs) qui pourrait prendre la forme suivante :
</p>


```json
{
  "1": {
    "X": "5",
    "Y": "3",
    "nbRestingFences": "4",
    "currentTurn": "true"
  },
  "2": {
    "X": "1",
    "Y": "5",
    "nbRestingFences": "6",
    "currentTurn": "false"
  }
}
```